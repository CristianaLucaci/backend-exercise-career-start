package msg.careerstart.exercise.domain;

import lombok.Builder;
import lombok.Data;
import msg.careerstart.exercise.shared.DogBreed;

import javax.persistence.*;

@Table(name = "dogs")
@Data
@Builder
public class Dog {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true)
    private String name;

    @Enumerated(EnumType.STRING)
    private DogBreed breed;

    public Integer treats;

}

