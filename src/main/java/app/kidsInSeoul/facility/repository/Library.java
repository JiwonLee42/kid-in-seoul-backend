package app.kidsInSeoul.facility.repository;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@DiscriminatorValue("library")
public class Library extends Facility{
    //private String name;
    private String regionGu;
    private String street;
    private String phoneNum;
    private String homepageUrl;
    private String postNum;
    private BigDecimal latitude;
    private BigDecimal longitude;
    private String operatingTime;

}
