package app.kidsInSeoul.facility.web.dto.response;

import app.kidsInSeoul.facility.repository.ArtGallery;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ArtGalleryListDto {

    private Long id;
    private String name;
    private Long likeCount;
    private String facilityType;
    private String address;
    private String phoneNum;
    private String url;
    private int adultFee;
    private int childFee;

    public ArtGalleryListDto(ArtGallery artGallery) {
    }
}
