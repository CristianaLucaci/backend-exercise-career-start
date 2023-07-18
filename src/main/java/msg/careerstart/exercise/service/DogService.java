
package msg.careerstart.exercise.service;

import msg.careerstart.exercise.domain.Dog;
import msg.careerstart.exercise.dto.DogSimpleDto;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;


public interface DogService {

    public DogSimpleDto createDog(String name, String breed);

    public DogSimpleDto getDogByName(String name);

    public DogSimpleDto giveDogTreats(String name, int treats);

    public void deleteDogByName(String name);
}
