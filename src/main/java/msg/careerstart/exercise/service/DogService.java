package msg.careerstart.exercise.service;

import msg.careerstart.exercise.domain.Dog;
import msg.careerstart.exercise.dto.DogSimple;
import org.springframework.stereotype.Service;

@Service
public interface DogService {

    public DogSimple createDog(String name, String breed);

    public Dog getDogByName(String name);

    public DogSimple giveDogTreats(String name, int treats);

    public void deleteDogByName(String name);
}
