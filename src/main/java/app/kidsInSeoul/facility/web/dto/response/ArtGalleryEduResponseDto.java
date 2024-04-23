package app.kidsInSeoul.facility.web.dto.response;

import app.kidsInSeoul.facility.repository.ArtGalleryEdu;
import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.Column;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.*;

import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ArtGalleryEduResponseDto {
    private Long id;
    private String name;
    private String target;
    private String eduSpot;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd", timezone = "Asia/Seoul")
    private LocalDate eduStart;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd", timezone = "Asia/Seoul")
    private LocalDate eduEnd;
    private LocalDate content;
    private String eduLimit;
    private String url;
    private String eduFee;
    private LocalDate recruitStart;
    private LocalDate recruitEnd;

    public ArtGalleryEduResponseDto(ArtGalleryEdu artGalleryEdu) {
    }
}
