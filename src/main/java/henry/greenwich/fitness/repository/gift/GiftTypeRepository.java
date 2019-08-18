package henry.greenwich.fitness.repository.gift;

import henry.greenwich.fitness.model.gift.GiftType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface GiftTypeRepository extends JpaRepository<GiftType, Long> {
    GiftType findGiftTypeById(Long id);
}
