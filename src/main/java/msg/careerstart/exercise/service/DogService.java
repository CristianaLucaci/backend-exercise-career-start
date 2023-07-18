package msg.careerstart.exercise.service;

import msg.careerstart.exercise.domain.Dog;
import msg.careerstart.exercise.dto.DogDto;

public interface DogService {

    public DogDto createDog(String name, String breed);

    public Dog getDogByName(String name);

    public DogDto giveDogTreats(String name, int treats);

    public void deleteDogByName(String name);
}
