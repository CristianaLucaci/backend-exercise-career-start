package msg.careerstart.exercise.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import msg.careerstart.exercise.shared.DogBreed;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class DogDto {

    private String name;
    private DogBreed breed;
    private Integer treats;

    /**
     * checks status of class
     */
    private void checkClassStatus() {
        System.out.println("DogDto working fine");
    }
}

