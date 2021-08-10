package kg.baiysh.personneltesting.payload.dto;


import kg.baiysh.personneltesting.entity.Personnel;
import kg.baiysh.personneltesting.entity.enums.ESafetyGroup;
import kg.baiysh.personneltesting.payload.utils.DTOEntity;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class PersonnelDTO implements DTOEntity {
    private String id;                          // auto create
    private String personnelName;
    private int personnelNumber;
    private LocalDateTime createdDate;          //auto crate
    private String positionName;
    private ESafetyGroup electricalSafetyGroup; //default non
    private String userName;

    public static PersonnelDTO convertToDto(Personnel personnel){
        PersonnelDTO personnelDTO = new PersonnelDTO();
        personnelDTO.setId(personnel.getId());
        personnelDTO.setPersonnelName(personnel.getPersonnelName());
        personnelDTO.setPersonnelNumber(personnel.getPersonnelNumber());
        personnelDTO.setCreatedDate(personnel.getCreatedDate());
        personnelDTO.setPositionName(personnel.getPosition().getPositionName());
        personnelDTO.setElectricalSafetyGroup(personnel.getElectricalSafetyGroup());
        personnelDTO.setUserName(personnel.getUser().getUsername());
        return personnelDTO;
    }


}
