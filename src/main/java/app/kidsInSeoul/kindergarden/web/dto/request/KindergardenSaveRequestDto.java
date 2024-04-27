package app.kidsInSeoul.kindergarden.web.dto.request;


import app.kidsInSeoul.kindergarden.repository.Kindergarden;
import app.kidsInSeoul.region.repository.Region;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class KindergardenSaveRequestDto {

    private String name;

    private String address;

    private String feature;

    private String type;

    private Long regionId;
    private String faxNum;

    private String postNum;

    private String phoneNum;


    @Builder
    public KindergardenSaveRequestDto(Region region,String name, String address, String feature, String type, String faxNum,String postNum,String phoneNum){
        this.name = name;
        this.address = address;
        this.regionId = region.getRegion_id();
        this.feature = feature;
        this.type = type;
        this.faxNum = faxNum;
        this.postNum = postNum;
        this.phoneNum = phoneNum;
    }

    public Kindergarden toEntity(Region region) {
        return Kindergarden.builder()
                .name(name)
                .address(address)
                .region(region)
                .phoneNum(phoneNum)
                .feature(feature)
                .faxNum(faxNum)
                .postNum(postNum)
                .type(type)
                .phoneNum(phoneNum)
                .build();
    }
}
