package msg.careerstart.exercise.mapper;

import msg.careerstart.exercise.domain.Dog;
import msg.careerstart.exercise.dto.DogDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper
public interface DogMapper {
    DogMapper INSTANCE = Mappers.getMapper(DogMapper.class);

    @Mapping(target = "id", ignore = true)
    Dog toEntity(DogDto dogSimple);

    DogDto toDTO(Dog dog);
}
