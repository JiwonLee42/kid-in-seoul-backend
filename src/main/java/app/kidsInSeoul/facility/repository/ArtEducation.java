package app.kidsInSeoul.facility.repository;


import jakarta.persistence.Column;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class ArtEducation {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long education_id;

    private String name;

    private String target;

    private String edu_spot;
    private String edu_start;
    private String edu_end;

    @Column(name = "content", columnDefinition = "TEXT")
    private String content;

    private int edu_limit;

    private String url;

    private String edu_fee;


}
