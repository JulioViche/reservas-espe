package ec.edu.espe.users.services.impl;

import java.security.SecureRandom;
import java.util.List;
import java.util.UUID;
import java.util.stream.IntStream;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import ec.edu.espe.users.dtos.PersonCreateResponseDto;
import ec.edu.espe.users.dtos.PersonRequestDto;
import ec.edu.espe.users.dtos.PersonResponseDto;
import ec.edu.espe.users.entities.Person;
import ec.edu.espe.users.entities.User;
import ec.edu.espe.users.repositories.PersonRepository;
import ec.edu.espe.users.repositories.UserRepository;
import ec.edu.espe.users.services.PersonService;
import ec.edu.espe.users.utils.PersonMapper;
import ec.edu.espe.users.utils.UserMapper;

@Service
public class PersonServiceImpl implements PersonService {

    private final PersonRepository personRepository;
    private final UserRepository userRepository;
    private final PersonMapper personMapper;
    private final UserMapper userMapper;
    private final PasswordEncoder passwordEncoder;

    public PersonServiceImpl(PersonRepository personRepository, UserRepository userRepository,
            PersonMapper personMapper, UserMapper userMapper, PasswordEncoder passwordEncoder) {
        this.personRepository = personRepository;
        this.userRepository = userRepository;
        this.personMapper = personMapper;
        this.userMapper = userMapper;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    @Transactional(readOnly = true)
    public List<PersonResponseDto> getAll() {
        return personRepository.findAll().stream()
                .map(personMapper::toResponseDto)
                .toList();
    }

    @Override
    @Transactional(readOnly = true)
    public PersonResponseDto getById(UUID id) {
        Person person = personRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Persona no encontrada con id: " + id));
        return personMapper.toResponseDto(person);
    }

    @Override
    @Transactional
    public PersonCreateResponseDto create(PersonRequestDto request) {
        if (personRepository.existsByEmail(request.getEmail())) {
            throw new IllegalArgumentException("El email ya está registrado");
        }

        Person person = personMapper.toEntity(request);
        person = personRepository.save(person);

        String username = generateUsername(person);
        String rawPassword = generatePassword();
        String passwordHash = passwordEncoder.encode(rawPassword);

        User user = User.builder()
                .username(username)
                .passwordHash(passwordHash)
                .active(true)
                .person(person)
                .build();
        userRepository.save(user);
        person.setUser(user);

        return PersonCreateResponseDto.builder()
                .person(personMapper.toResponseDto(person))
                .username(username)
                .generatedPassword(rawPassword)
                .build();
    }

    @Override
    @Transactional
    public PersonResponseDto update(UUID id, PersonRequestDto request) {
        Person person = personRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Persona no encontrada con id: " + id));

        if (!person.getEmail().equals(request.getEmail())
                && personRepository.existsByEmail(request.getEmail())) {
            throw new IllegalArgumentException("El email ya está registrado por otra persona");
        }

        person.setDni(request.getDni());
        person.setFirstName(request.getFirstName());
        person.setMiddleName(request.getMiddleName());
        person.setLastName(request.getLastName());
        person.setEmail(request.getEmail());
        person.setPhone(request.getPhone());
        person.setAddress(request.getAddress());
        person.setNationality(request.getNationality());
        person.setActive(request.getActive());
        cascadeUserActive(person);

        return personMapper.toResponseDto(personRepository.save(person));
    }

    @Override
    @Transactional
    public void toggleActive(UUID id) {
        Person person = personRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Persona no encontrada con id: " + id));
        person.setActive(!person.getActive());
        cascadeUserActive(person);
    }

    private void cascadeUserActive(Person person) {
        if (!person.getActive() && person.getUser() != null) {
            person.getUser().setActive(false);
        }
    }

    private static final String PASSWORD_CHARS =
            "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789!@#$%^&*()_+-=[]{}|;:,.<>?";

    private String generatePassword() {
        SecureRandom random = new SecureRandom();
        return IntStream.range(0, 14)
                .map(i -> PASSWORD_CHARS.charAt(random.nextInt(PASSWORD_CHARS.length())))
                .collect(StringBuilder::new, StringBuilder::append, StringBuilder::append)
                .toString();
    }

    private String generateUsername(Person person) {
        StringBuilder prefix = new StringBuilder();
        prefix.append(Character.toLowerCase(person.getFirstName().charAt(0)));
        if (person.getMiddleName() != null && !person.getMiddleName().isBlank()) {
            prefix.append(Character.toLowerCase(person.getMiddleName().charAt(0)));
        }
        String base = prefix.toString() + person.getLastName().toLowerCase();

        if (!userRepository.existsByUsername(base)) {
            return base;
        }

        int suffix = 2;
        while (userRepository.existsByUsername(base + suffix)) {
            suffix++;
        }
        return base + suffix;
    }
}
