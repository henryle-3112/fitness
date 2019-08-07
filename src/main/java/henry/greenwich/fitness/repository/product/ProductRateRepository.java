package henry.greenwich.fitness.repository.product;

import henry.greenwich.fitness.model.product.Product;
import henry.greenwich.fitness.model.product.ProductRate;
import henry.greenwich.fitness.model.user.UserProfile;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductRateRepository extends JpaRepository<ProductRate, Long> {

    /**
     * @param userProfile - user's profile that user want to get selected product's
     *                    rate
     * @param product     - product that user want to get selected product's rate
     * @return selected product's rate
     */
    ProductRate findProductRateByUserProfileAndProduct(UserProfile userProfile, Product product);
}
