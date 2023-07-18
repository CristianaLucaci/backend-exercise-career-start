package msg.careerstart.exercise.mapper;

import msg.careerstart.exercise.domain.Dog;
import msg.careerstart.exercise.dto.DogSimpleDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper
public interface DogTranslatorMapper {
    DogTranslatorMapper INSTANCE = Mappers.getMapper(DogTranslatorMapper.class);

    @Mapping(target = "id", ignore = true)
    Dog toEntity(DogSimpleDto dogSimple);

    DogSimpleDto toDTO(Dog dog);
}

