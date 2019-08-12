package henry.greenwich.fitness.model.product;

import javax.persistence.*;
import java.util.Date;

import henry.greenwich.fitness.constants.Constants;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = Constants.PRODUCT_SLIDE_TABLE)
public class ProductSlide {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = Constants.PRODUCT_SLIDE_ID)
    private Long id;

    @Column(name = Constants.PRODUCT_SLIDE_IMAGE)
    private String productSlideImage;

    @Column(name = Constants.PRODUCT_SLIDE_LINK)
    private String productSlideLink;

    @Column(name = Constants.PRODUCT_SLIDE_DESCRIPTION)
    private String productSlideDescription;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = Constants.PRODUCT_SLIDE_CREATED_DATE)
    private Date productSlideCreatedDate;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = Constants.PRODUCT_SLIDE_MODIFIED_DATE)
    private Date productSlideModifiedDate;

    @Column(name = Constants.PRODUCT_SLIDE_STATUS)
    private int productSlideStatus;

    @Column(name = Constants.PRODUCT_SLIDE_TITLE)
    private String ProductSlideTitle;
}
