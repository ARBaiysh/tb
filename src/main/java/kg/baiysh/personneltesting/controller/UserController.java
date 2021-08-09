package kg.baiysh.personneltesting.controller;

import kg.baiysh.personneltesting.dto.utils.DTOEntity;
import kg.baiysh.personneltesting.dto.UserDTO;
import kg.baiysh.personneltesting.dto.utils.DtoUtils;
import kg.baiysh.personneltesting.entity.User;
import kg.baiysh.personneltesting.entity.enums.ERole;
import kg.baiysh.personneltesting.services.UserService;
import kg.baiysh.personneltesting.validations.ResponseErrorValidation;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.util.ObjectUtils;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;

@RestController
@RequestMapping("api/user")
@Slf4j
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;
    private final ResponseErrorValidation responseErrorValidation;


    @PostMapping()
    @PreAuthorize("hasRole('USER')")
    public ResponseEntity<DTOEntity> getCurrentUser(Principal principal) {
        User user = userService.getCurrentUser(principal);
        DTOEntity userDTO = new DtoUtils().convertToDto(user, new UserDTO());

        return new ResponseEntity<>(userDTO, HttpStatus.OK);
    }

    @PostMapping("/id/{userId}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<DTOEntity> getUserProfile(@PathVariable String userId) {
        User user = userService.getUserById(userId);
        DTOEntity userDTO = new DtoUtils().convertToDto(user, new UserDTO());

        return new ResponseEntity<>(userDTO, HttpStatus.OK);
    }

    @PostMapping("/email/{email}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<DTOEntity> getUserByEmail(@PathVariable String email) {
        User user = userService.getUserByEmail(email);
        DTOEntity userDTO = new DtoUtils().convertToDto(user, new UserDTO());

        return new ResponseEntity<>(userDTO, HttpStatus.OK);
    }

    @PostMapping("/editStatus")
    @PreAuthorize("hasRole('USER')")
    public ResponseEntity<Object> editStatus(@RequestBody UserDTO userDTO, BindingResult bindingResult) {
        ResponseEntity<Object> errors = responseErrorValidation.mapValidationService(bindingResult);

        if (!ObjectUtils.isEmpty(errors)) return errors;
        User user = userService.updateStatus(userDTO);

        DTOEntity userUpdated = new DtoUtils().convertToDto(user, new UserDTO());
        return new ResponseEntity<>(userUpdated, HttpStatus.OK);
    }

    @PostMapping("/addRoleToUser/{userId}:{role}")
    @PreAuthorize("hasRole('USER')")
    public ResponseEntity<?> addRoleToUser(@PathVariable String userId, @PathVariable String role) {

        User user;
        if (role.equals(ERole.ROLE_ADMIN.name())) {
            user = userService.addRoleToUser(userId, ERole.ROLE_ADMIN);
        } else if (role.equals(ERole.ROLE_MANAGER.name())) {
            user = userService.addRoleToUser(userId, ERole.ROLE_MANAGER);
        } else if (role.equals(ERole.ROLE_MODERATOR.name())) {
            user = userService.addRoleToUser(userId, ERole.ROLE_MODERATOR);
        } else {
            return new ResponseEntity<>(String.format("There is no %s role in the database", role), HttpStatus.BAD_REQUEST);
        }

        DTOEntity userUpdated = new DtoUtils().convertToDto(user, new UserDTO());
        return new ResponseEntity<>(userUpdated, HttpStatus.OK);
    }
}
