package kg.baiysh.personneltesting.payload.utils;

import org.modelmapper.ModelMapper;

public class DtoUtils {

    public static DTOEntity convertToDto(Object obj, DTOEntity mapper) {
        return new ModelMapper().map(obj, mapper.getClass());
    }

    public static Object convertToEntity(Object obj, DTOEntity mapper) {
        return new ModelMapper().map(mapper, obj.getClass());
    }
}

