package kg.baiysh.personneltesting.controller;

import kg.baiysh.personneltesting.payload.dto.PersonnelDTO;
import kg.baiysh.personneltesting.payload.utils.DTOEntity;
import kg.baiysh.personneltesting.services.PersonnelService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;
import java.util.List;

@RestController
@RequestMapping("api/personnel")
@Slf4j
@RequiredArgsConstructor
public class PersonnelController {

    private final PersonnelService personnelService;


    @PostMapping("")
    @PreAuthorize("hasRole('USER')")
    public ResponseEntity<List<DTOEntity>> getPersonnel() {
        List<DTOEntity> positions = personnelService.getPersonnel();
        return new ResponseEntity<>(positions, HttpStatus.OK);
    }

    @PostMapping("/create")
    @PreAuthorize("hasRole('USER')")
    public ResponseEntity<DTOEntity> createPosition(@RequestBody PersonnelDTO personnelDTO, Principal principal) {
        DTOEntity dtoEntity = personnelService.createPersonnel(personnelDTO, principal);
        return new ResponseEntity<>(dtoEntity, HttpStatus.OK);
    }

    @PostMapping("/updateGroup")
    @PreAuthorize("hasRole('USER')")
    public ResponseEntity<DTOEntity> updateGroup(@RequestBody PersonnelDTO personnelDTO) {
        DTOEntity dtoEntity = personnelService.updateGroup(personnelDTO);
        return new ResponseEntity<>(dtoEntity, HttpStatus.OK);
    }
}
