package kg.baiysh.personneltesting.dto;


import kg.baiysh.personneltesting.dto.utils.DTOEntity;
import kg.baiysh.personneltesting.entity.enums.ESafetyGroup;
import lombok.Data;


import java.time.LocalDateTime;

@Data
public class PersonnelDTO implements DTOEntity {
    private String id;                          // auto create
    private String personnelName;
    private int personnelNumber;
    private LocalDateTime createdDate;          //auto crate
    private String positionId;
    private ESafetyGroup electricalSafetyGroup; //default non
}
