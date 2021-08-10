package kg.baiysh.personneltesting.services;


import kg.baiysh.personneltesting.entity.Personnel;
import kg.baiysh.personneltesting.entity.Position;
import kg.baiysh.personneltesting.entity.User;
import kg.baiysh.personneltesting.entity.enums.ESafetyGroup;
import kg.baiysh.personneltesting.exceptions.ApiRequestException;
import kg.baiysh.personneltesting.payload.dto.PersonnelDTO;
import kg.baiysh.personneltesting.payload.utils.DTOEntity;
import kg.baiysh.personneltesting.repository.PersonnelRepository;
import kg.baiysh.personneltesting.repository.PositionRepository;
import kg.baiysh.personneltesting.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.security.Principal;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class PersonnelService {
    private final PersonnelRepository personnelRepository;
    private final PositionRepository positionRepository;
    private final UserRepository userRepository;

    public DTOEntity createPersonnel(PersonnelDTO personnelDTO, Principal principal) {
        if (personnelRepository.existsByPersonnelNumber(personnelDTO.getPersonnelNumber())) {
            throw new ApiRequestException("The personnel " + personnelDTO.getPersonnelNumber() + ":" + personnelDTO.getPersonnelName() + " already exist.");
        }

        Position position = positionRepository.findUserByPositionName(personnelDTO.getPositionName())
                .orElseThrow(() -> new ApiRequestException("The position id " + personnelDTO.getPositionName() + " is not exist"));

        User user = userRepository.findUserByUsername(principal.getName()).get();

        Personnel personnel = new Personnel();
        personnel.setPersonnelName(personnelDTO.getPersonnelName());
        personnel.setPersonnelNumber(personnelDTO.getPersonnelNumber());
        personnel.setPosition(position);
        personnel.setElectricalSafetyGroup(ESafetyGroup.I);
        personnel.setUser(user);

        Personnel tempPersonnel = personnelRepository.save(personnel);

        return PersonnelDTO.convertToDto(tempPersonnel);
    }

    public List<DTOEntity> getPersonnel() {
        List<Personnel> personnelList = personnelRepository.findAll();
        List<DTOEntity> dtoEntities = new ArrayList<>();

        personnelList.forEach(personnel -> dtoEntities.add(PersonnelDTO.convertToDto(personnel)));
        return dtoEntities;
    }

    public DTOEntity updateGroup(PersonnelDTO personnelDTO) {
        Personnel personnel = personnelRepository.findById(personnelDTO.getId()).get();
        personnel.setElectricalSafetyGroup(personnelDTO.getElectricalSafetyGroup());
        return PersonnelDTO.convertToDto(personnel);
    }
}
