package ec.edu.espe.users.services;

import java.util.List;
import java.util.UUID;

import ec.edu.espe.users.dtos.PersonCreateResponseDto;
import ec.edu.espe.users.dtos.PersonRequestDto;
import ec.edu.espe.users.dtos.PersonResponseDto;

public interface PersonService {

    List<PersonResponseDto> getAll();

    PersonResponseDto getById(UUID id);

    PersonCreateResponseDto create(PersonRequestDto request);

    PersonResponseDto update(UUID id, PersonRequestDto request);

    void toggleActive(UUID id);
}
