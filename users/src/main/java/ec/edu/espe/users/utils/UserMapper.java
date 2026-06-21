package ec.edu.espe.users.utils;

import java.util.Collections;
import java.util.Optional;

import org.springframework.stereotype.Component;

import ec.edu.espe.users.dtos.UserRequestDto;
import ec.edu.espe.users.dtos.UserResponseDto;
import ec.edu.espe.users.entities.Person;
import ec.edu.espe.users.entities.User;

@Component
public class UserMapper {

    public User toEntity(UserRequestDto request, Person person, String passwordHash) {
        return User.builder()
                .username(request.getUsername())
                .passwordHash(passwordHash)
                .active(true)
                .person(person)
                .build();
    }

    public UserResponseDto toResponseDto(User user) {
        return UserResponseDto.builder()
                .idPerson(user.getIdPerson())
                .username(user.getUsername())
                .active(user.getActive())
                .lastLogin(user.getLastLogin())
                .createdAt(user.getCreatedAt())
                .updatedAt(user.getUpdatedAt())
                .personFirstName(user.getPerson().getFirstName())
                .personLastName(user.getPerson().getLastName())
                .personEmail(user.getPerson().getEmail())
                .roles(Optional.ofNullable(user.getUserRoles())
                        .map(roles -> roles.stream()
                                .map(ur -> ur.getRole().getName())
                                .toList())
                        .orElse(Collections.emptyList()))
                .build();
    }
}
