package kg.baiysh.personneltesting.payload.dto;

import kg.baiysh.personneltesting.payload.utils.DTOEntity;
import lombok.Data;

@Data
public class PositionDTO implements DTOEntity {
    private String id;
    private String name;
}
