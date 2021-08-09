package kg.baiysh.personneltesting.controller;

import kg.baiysh.personneltesting.dto.PositionDTO;
import kg.baiysh.personneltesting.dto.utils.DTOEntity;
import kg.baiysh.personneltesting.services.PositionService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/position")
@Slf4j
@RequiredArgsConstructor
public class PositionController {

    private final PositionService positionService;


    @PostMapping("")
    @PreAuthorize("hasRole('USER')")
    public ResponseEntity<List<DTOEntity>> getPositions() {
        List<DTOEntity> positions = positionService.getPositions();
        return new ResponseEntity<>(positions, HttpStatus.OK);
    }

    @PostMapping("/create")
    @PreAuthorize("hasRole('USER')")
    public ResponseEntity<DTOEntity> createPosition(@RequestBody PositionDTO dtoPosition) {
        DTOEntity dtoEntity = positionService.createPosition(dtoPosition);
        return new ResponseEntity<>(dtoEntity, HttpStatus.OK);
    }
}
