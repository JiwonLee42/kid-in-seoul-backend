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

    private String regionName;

    private String address;


    private String phoneNum;

    private String feature;

    private String type;

    private String faxNum;

    private String postNum;
    
    private Long regionId;

    @Builder
    public KindergardenResponseDto(Kindergarden kindergarden) {
        this.id = kindergarden.getId();
        this.name = kindergarden.getName();
        this.regionName = kindergarden.getRegionName();
        this.address = kindergarden.getAddress();
        this.phoneNum = kindergarden.getPhoneNum();
        this.feature = kindergarden.getFeature();
        this.type = kindergarden.getType();
        this.faxNum = kindergarden.getMaxNum();
        this.postNum = kindergarden.getPostNum();
        this.regionId = kindergarden.getRegion().getId();
    }

}
