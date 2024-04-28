package app.kidsInSeoul.kindergarden.repository;


import app.kidsInSeoul.region.repository.Region;
import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@NoArgsConstructor
@Getter
public class Kindergarden {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "kg_id")
    private Long id;

    @Column(name = "kg_name")
    private String name;

    @Column(name = "region_gu")
    private String regionName;

    private String address;

    @Column(name = "phone_num")
    private String phoneNum;

    @ManyToOne
    @JoinColumn(name = "region_id", nullable = true)
    Region region;

    private String feature;

    private String type;

    @Column(name = "fax_num")
    private String faxNum;

    @Column(name = "post_num")
    private String postNum;



    @Builder
    public Kindergarden(String name, String address, String phoneNum, Region region, String feature, String type, String faxNum, String postNum) {
        this.name = name;
        this.address = address;
        this.phoneNum = phoneNum;
        this.region = region;
        this.feature = feature;
        this.type = type;
        this.faxNum = faxNum;
        this.postNum = postNum;
    }

}