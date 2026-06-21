package ec.edu.espe.users.utils;

import org.springframework.stereotype.Component;

import ec.edu.espe.users.dtos.PersonRequestDto;
import ec.edu.espe.users.dtos.PersonResponseDto;
import ec.edu.espe.users.entities.Person;

@Component
public class PersonMapper {

    public Person toEntity(PersonRequestDto request) {
        return Person.builder()
                .dni(request.getDni())
                .firstName(request.getFirstName())
                .middleName(request.getMiddleName())
                .lastName(request.getLastName())
                .email(request.getEmail())
                .phone(request.getPhone())
                .address(request.getAddress())
                .nationality(request.getNationality())
                .active(request.getActive())
                .build();
    }

    public PersonResponseDto toResponseDto(Person person) {
        return PersonResponseDto.builder()
                .id(person.getId())
                .dni(person.getDni())
                .firstName(person.getFirstName())
                .middleName(person.getMiddleName())
                .lastName(person.getLastName())
                .email(person.getEmail())
                .phone(person.getPhone())
                .address(person.getAddress())
                .nationality(person.getNationality())
                .active(person.getActive())
                .createdAt(person.getCreatedAt())
                .updatedAt(person.getUpdatedAt())
                .build();
    }
}
