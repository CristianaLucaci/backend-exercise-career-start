package msg.careerstart.exercise.repository;

import msg.careerstart.exercise.domain.Dog;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DogRepository extends JpaRepository<Dog, Long> {
    Dog findByName(String name);
}

