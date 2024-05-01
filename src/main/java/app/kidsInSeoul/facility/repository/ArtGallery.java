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
@DiscriminatorValue("art_gallery")
@Entity
public class ArtGallery extends Facility{
    //private String name;
    private String address;
    private String phoneNum;
    private String url;
    private int adultFee;
    private int childFee;
}
