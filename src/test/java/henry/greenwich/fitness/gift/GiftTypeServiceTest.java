package henry.greenwich.fitness.gift;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import henry.greenwich.fitness.model.gift.GiftType;
import henry.greenwich.fitness.repository.gift.GiftTypeRepository;
import henry.greenwich.fitness.service.gift.GiftTypeService;

public class GiftTypeServiceTest {
	@Mock
	private GiftTypeRepository giftTypeRepository;
	
	@InjectMocks
	private GiftTypeService giftTypeService;
	
	@BeforeEach
	private void setup() {
		MockitoAnnotations.initMocks(this);
	}
	
	@Test
	public void testGetGiftType() {
		GiftType giftType = new GiftType(1L, "");
		
		when(this.giftTypeRepository.findGiftTypeById(1L)).thenReturn(giftType);
		
		GiftType selectedGiftType = this.giftTypeService.getGiftType(1L);
		
		assertNotNull(selectedGiftType);
	}
}
