package kg.baiysh.personneltesting.services;


import kg.baiysh.personneltesting.dto.PositionDTO;
import kg.baiysh.personneltesting.dto.utils.DTOEntity;
import kg.baiysh.personneltesting.dto.utils.DtoUtils;
import kg.baiysh.personneltesting.entity.Position;
import kg.baiysh.personneltesting.repository.PositionRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class PositionService {
    private final PositionRepository positionRepository;

    public DTOEntity createPosition(PositionDTO dtoPosition) {
        Position position = positionRepository.save(new Position(dtoPosition.getName()));

        return DtoUtils.convertToDto(position, new PositionDTO());

    }

    public List<DTOEntity> getPositions() {
        List<Position> positions = positionRepository.findAll();

        List<DTOEntity> dtoEntities = new ArrayList<>();

        positions.forEach(position -> dtoEntities.add(DtoUtils.convertToDto(position, new PositionDTO())));
        return dtoEntities;
    }

}
