package kg.baiysh.personneltesting.services;


import kg.baiysh.personneltesting.dto.PersonnelDTO;
import kg.baiysh.personneltesting.dto.utils.DTOEntity;
import kg.baiysh.personneltesting.dto.utils.DtoUtils;
import kg.baiysh.personneltesting.entity.Personnel;
import kg.baiysh.personneltesting.entity.enums.ESafetyGroup;
import kg.baiysh.personneltesting.exceptions.ApiRequestException;
import kg.baiysh.personneltesting.repository.PersonnelRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class PersonnelService {
    private final PersonnelRepository personnelRepository;

    public DTOEntity createPersonnel(PersonnelDTO personnelDTO) {
        if (personnelRepository.existsByPersonnelNumber(personnelDTO.getPersonnelNumber())) {
            throw new ApiRequestException("The personnel " + personnelDTO.getPersonnelNumber() + ":" + personnelDTO.getPersonnelName() + " already exist.");
        }
        Personnel personnel = new Personnel();
        personnel.setPersonnelName(personnelDTO.getPersonnelName());
        personnel.setPersonnelNumber(personnelDTO.getPersonnelNumber());
        personnel.setPositionId(personnelDTO.getPositionId());
        personnel.setElectricalSafetyGroup(ESafetyGroup.NON);

        return DtoUtils.convertToDto(personnelRepository.save(personnel), new PersonnelDTO());
    }

    public List<DTOEntity> getPersonnel() {
        List<Personnel> personnelList = personnelRepository.findAll();
        List<DTOEntity> dtoEntities = new ArrayList<>();

        personnelList.forEach(personnel -> dtoEntities.add(DtoUtils.convertToDto(personnel, new PersonnelDTO())));
        return dtoEntities;
    }


}
