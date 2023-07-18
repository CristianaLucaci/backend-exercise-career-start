package msg.careerstart.exercise.controller;

import lombok.NonNull;
import msg.careerstart.exercise.domain.Dog;
import msg.careerstart.exercise.dto.DogSimpleDto;
import msg.careerstart.exercise.service.DogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RequestMapping("/dogs")
@Validated
@RestController
public class DogController {

    @Autowired
    private DogService dogService;

    @GetMapping
    public ResponseEntity<DogSimpleDto> createDog(@RequestParam @NonNull String name, @RequestParam @NonNull String breed) {
        DogSimpleDto dogSimple = dogService.createDog(name, breed);
        return new ResponseEntity<>(dogSimple, HttpStatus.CREATED);
    }

    //TODO: maybe use this later
//    @GetMapping
//    public void getOtherPrincipals() {
//        Principal principal = SecurityContextHolder.getContext().getAuthentication();
//
//        Optional<ApplicationUser> applicationUser = applicationUserRepository.findApplicationUserByUsername(principal.getName());
//
//        List<DogMaster> exercises = new ArrayList<>();
//        if(applicationUser.isPresent()){
//            exercises = applicationUser.get().getExercises();
//
//        }
//
//        return exercises;
//    }

    @GetMapping("/{name}")
    public ResponseEntity<DogSimpleDto> getDogByName(@PathVariable String name) {
        DogSimpleDto dog = dogService.getDogByName(name);
        return new ResponseEntity<>(dog, HttpStatus.OK);
    }

    @PutMapping("/{name}/treats")
    public ResponseEntity<DogSimpleDto> patch(@PathVariable String name, @RequestParam int treats) {
        if (treats < 0 || treats > 10) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        DogSimpleDto dogSimple = dogService.giveDogTreats(name, treats);
            return new ResponseEntity<>(dogSimple, HttpStatus.OK);

    }

    /**
     * Don't worry, it just removes the entity, does not hurt the doggo ;)
     * @param name String, unique
     * @return void
     */
    @DeleteMapping("/{name}")
    public ResponseEntity<Void> deleteDogByName(@PathVariable String name) {
        dogService.deleteDogByName(name);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }


}
