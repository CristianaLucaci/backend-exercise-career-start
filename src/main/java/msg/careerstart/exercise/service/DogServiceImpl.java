package msg.careerstart.exercise.service;

import msg.careerstart.exercise.domain.Dog;
import msg.careerstart.exercise.dto.DogSimple;
import msg.careerstart.exercise.mapper.DogTranslator;
import msg.careerstart.exercise.repository.DogRepository;
import msg.careerstart.exercise.shared.DogBreed;
import org.springframework.beans.factory.annotation.Autowired;

import javax.persistence.EntityNotFoundException;

public class DogServiceImpl implements DogService {

    Integer INITIAL_NR_OF_TREATS = 0;

    @Autowired
    private DogRepository dogRepository;

    private final DogTranslator dogMapper = DogTranslator.INSTANCE;

    public DogSimple createDog(String name, String breed) {
        DogBreed dogBreed = DogBreed.valueOf(breed);
        Dog dog = Dog.builder().name(name).breed(dogBreed).treats(INITIAL_NR_OF_TREATS).build();
        return dogMapper.toDTO(dogRepository.save(dog));
    }

    @Override
    public Dog getDogByName(String name) {
        Dog dog = dogRepository.findByName(name);
        if(dog != null){
            return dog;
        }
        throw new EntityNotFoundException("No such doggo here!");
    }

    @Override
    public DogSimple giveDogTreats(String name, int treats) {
        Dog dog = dogRepository.findByName(name);
        if (dog != null) {
            dog.setTreats(treats);
            return dogMapper.toDTO(dogRepository.save(dog));
        }
        throw new EntityNotFoundException("No such doggo here!");
    }

    @Override
    public void deleteDogByName(String name) {
        Dog dog = dogRepository.findByName(name);
        if (dog != null) {
            dogRepository.delete(dog);
        }
    }

//    private void makeHousingArraangementsForStrayDoggos() {
//        StrayDog scruffy = new StrayDog("Scruffy", STRAY);
//
//        List<House> availableHousing = housingRepository.getAvailableHousingInArea();
//
//        if(availableHousing.isEmpty()) {
//            throw new HouseNotFoundException();
//        }
//    }

}

