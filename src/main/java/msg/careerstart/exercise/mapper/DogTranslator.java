package msg.careerstart.exercise.mapper;

import msg.careerstart.exercise.domain.Dog;
import msg.careerstart.exercise.dto.DogSimple;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper
public interface DogTranslator {
    DogTranslator INSTANCE = Mappers.getMapper(DogTranslator.class);

    @Mapping(target = "id", ignore = true)
    Dog toEntity(DogSimple dogSimple);

    DogSimple toDTO(Dog dog);
}

