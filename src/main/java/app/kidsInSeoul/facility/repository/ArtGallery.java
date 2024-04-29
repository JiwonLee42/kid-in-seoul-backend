package app.kidsInSeoul.facility.repository;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
public class ArtGallery {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "art_gallery_id")
    private Long id;
    private String name;
    private String address;
    private String eduSpot;
    private String url;
    private int adultFee;
    private int childFee;
}
