package msg.careerstart.exercise.service.impl;

import msg.careerstart.exercise.domain.Dog;
import msg.careerstart.exercise.dto.DogDto;
import msg.careerstart.exercise.mapper.DogMapper;
import msg.careerstart.exercise.repository.DogRepository;
import msg.careerstart.exercise.service.DogService;
import msg.careerstart.exercise.shared.DogBreed;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;

@Service
public class DogServiceImpl implements DogService {

    Integer INITIAL_NR_OF_TREATS = 0;

    @Autowired
    private DogRepository dogRepository;

    private final DogMapper dogMapper = DogMapper.INSTANCE;

    public DogDto createDog(String name, String breed) {
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
    public DogDto giveDogTreats(String name, int treats) {
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

