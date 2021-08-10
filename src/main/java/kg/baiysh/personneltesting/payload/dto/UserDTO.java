package kg.baiysh.personneltesting.payload.dto;

import kg.baiysh.personneltesting.payload.utils.DTOEntity;
import kg.baiysh.personneltesting.entity.enums.ERole;
import kg.baiysh.personneltesting.entity.enums.EStatus;
import lombok.Data;

import java.util.Set;

@Data
public class UserDTO implements DTOEntity {
    private String id;
    private String username;
    private String email;
    private EStatus status;
    private Set<ERole> roles;
}
