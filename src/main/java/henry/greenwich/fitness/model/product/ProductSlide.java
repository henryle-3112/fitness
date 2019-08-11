package henry.greenwich.fitness.model.product;

import javax.persistence.*;
import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "product_slide")
public class ProductSlide {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "product_slide_image")
    private String productSlideImage;

    @Column(name = "product_slide_link")
    private String productSlideLink;

    @Column(name = "product_slide_description")
    private String productSlideDescription;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "product_slide_created_date")
    private Date productSlideCreatedDate;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "product_slide_modified_date")
    private Date productSlideModifiedDate;

    @Column(name = "product_slide_status")
    private int productSlideStatus;

    @Column(name = "product_slide_title")
    private String ProductSlideTitle;
}
