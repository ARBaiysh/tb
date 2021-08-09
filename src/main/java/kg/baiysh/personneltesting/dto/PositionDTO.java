package kg.baiysh.personneltesting.dto;

import kg.baiysh.personneltesting.dto.utils.DTOEntity;
import lombok.Data;

@Data
public class PositionDTO implements DTOEntity {
    private String id;
    private String name;
}
