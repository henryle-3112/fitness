package henry.greenwich.fitness.gift;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import henry.greenwich.fitness.model.gift.Gift;
import henry.greenwich.fitness.model.gift.GiftType;
import henry.greenwich.fitness.repository.gift.GiftRepository;
import henry.greenwich.fitness.service.gift.GiftService;
import henry.greenwich.fitness.service.gift.GiftTypeService;

public class GiftServiceTest {
	private GiftType giftType;
	private List<Object> giftObjectList;

	@Mock
	private GiftTypeService giftTypeService;

	@Mock
	private GiftRepository giftRepository;

	@InjectMocks
	private GiftService giftService;

	@BeforeEach
	private void setup() {
		MockitoAnnotations.initMocks(this);

		this.giftType = new GiftType(1L, "Voucher");
		when(this.giftTypeService.getGiftType(1L)).thenReturn(this.giftType);

		Object[] giftObjectArr = new Object[] { 1, "", 1, 1, "" };
		this.giftObjectList = new ArrayList<>();
		this.giftObjectList.add(giftObjectArr);
	}

	@Test
	public void testGetGifts() {
		when(this.giftRepository.getGifts("")).thenReturn(this.giftObjectList);

		List<Gift> gifts = this.giftService.getGifts("");

		assertEquals(1, gifts.size());
	}

	@Test
	public void testGetGiftsByPage() {
		when(this.giftRepository.getGiftsByPage("", 0)).thenReturn(this.giftObjectList);

		List<Gift> gifts = this.giftService.getGiftsByPage("", 0);

		assertEquals(1, gifts.size());
	}
	
	@Test
	public void getNumberOfGifts() {
		List<Object> nGiftsObjectList = new ArrayList<>();
		nGiftsObjectList.add(new Integer(8));
		
		when(this.giftRepository.getNumberOfGifts("")).thenReturn(nGiftsObjectList);
		
		int nGifts = this.giftService.getNumberOfGifts("");
		
		assertEquals(8, nGifts);
	}
}
