package henry.greenwich.fitness.service.gift;

import henry.greenwich.fitness.model.gift.GiftType;
import henry.greenwich.fitness.repository.gift.GiftTypeRepository;
import org.springframework.stereotype.Service;

@Service
public class GiftTypeService {
    private GiftTypeRepository giftTypeRepository;

    /**
     * @param giftTypeRepository - inject giftTypeRepository
     */
    public GiftTypeService(GiftTypeRepository giftTypeRepository) {
        this.giftTypeRepository = giftTypeRepository;
    }

    /**
     * @param id - gift's type's id that will be used to get selected gift's type
     * @return selected gift's type
     */
    public GiftType getGiftType(Long id) {
        return this.giftTypeRepository.findGiftTypeById(id);
    }
}
