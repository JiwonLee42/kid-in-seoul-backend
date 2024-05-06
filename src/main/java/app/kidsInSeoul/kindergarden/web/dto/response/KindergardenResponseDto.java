package app.kidsInSeoul.kindergarden.web.dto.response;

import app.kidsInSeoul.kindergarden.repository.Kindergarden;
import app.kidsInSeoul.region.repository.Region;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class KindergardenResponseDto {

    private Long id;

    private String name;

    private String address;


    private String phoneNum;

    private String feature;

    private String type;

    private String faxNum;

    private String postNum;
    private Long region_id;

    @Builder
    public KindergardenResponseDto(Kindergarden kindergarden) {
        this.id = id;
        this.name = name;
        this.address = address;
        this.phoneNum = phoneNum;
        this.region_id = region_id;
        this.feature = feature;
        this.type = type;
        this.faxNum = faxNum;
        this.postNum = postNum;
    }

}
