package kg.baiysh.personneltesting.dto;

import kg.baiysh.personneltesting.dto.utils.DTOEntity;
import kg.baiysh.personneltesting.entity.enums.ERole;
import kg.baiysh.personneltesting.entity.enums.EStatus;
import lombok.Data;

import java.util.Set;

@Data
public class UserDTO implements DTOEntity {
    private String id;
    private String username;
    private EStatus status;
    private Set<ERole> roles;
}
