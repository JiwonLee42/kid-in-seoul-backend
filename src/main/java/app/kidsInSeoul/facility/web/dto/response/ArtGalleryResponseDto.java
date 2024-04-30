package app.kidsInSeoul.facility.web.dto.response;

import app.kidsInSeoul.facility.repository.ArtGallery;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ArtGalleryResponseDto {
    private Long id;
    private String name;
    private String address;
    private String phoneNum;
    private String url;
    private int adultFee;
    private int childFee;

    public ArtGalleryResponseDto(ArtGallery artGalleryEdu) {
    }
}
