package henry.greenwich.fitness.gift;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
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
import henry.greenwich.fitness.model.gift.UserGift;
import henry.greenwich.fitness.model.user.UserProfile;
import henry.greenwich.fitness.repository.gift.UserGiftRepository;
import henry.greenwich.fitness.service.gift.GiftTypeService;
import henry.greenwich.fitness.service.gift.UserGiftService;

public class UserGiftServiceTest {
	private GiftType giftType;
	private UserGift userGift;
	private List<Object> userGiftsObjectList;
	
	@Mock
	private UserGiftRepository userGiftRepository;
	
	@Mock
	private GiftTypeService giftTypeService;
	
	@InjectMocks
	private UserGiftService userGiftService;
	
	@BeforeEach
	private void setup() {
		MockitoAnnotations.initMocks(this);
		
		this.giftType = new GiftType(1L, "");
		when(this.giftTypeService.getGiftType(1L)).thenReturn(this.giftType);
		
		this.userGift = new UserGift(1L, 1, new Gift(), new UserProfile());
		
		Object[] userGiftsObjectArr = new Object[] {1, "", 1, 1, ""};
		this.userGiftsObjectList = new ArrayList<Object>();
		this.userGiftsObjectList.add(userGiftsObjectArr);
	}
	
	@Test
	public void testAddUserGift() {
		when(this.userGiftRepository.saveAndFlush(this.userGift)).thenReturn(this.userGift);
		
		UserGift insertedUserGift = this.userGiftService.addUserGift(this.userGift);
		
		assertNotNull(insertedUserGift);
	}
	
	@Test
	public void testUpdateUserGift() {
		when(this.userGiftRepository.saveAndFlush(this.userGift)).thenReturn(this.userGift);
		
		UserGift updatedUserGift = this.userGiftService.updateUserGift(this.userGift);
		
		assertNotNull(updatedUserGift);
	}
	
	@Test
	public void testGetGifts() {
		when(this.userGiftRepository.getGifts(1, "", 1, 1)).thenReturn(this.userGiftsObjectList);
		
		List<Gift> userGifts = this.userGiftService.getGifts(1, "", 1, 1);
		
		assertEquals(1, userGifts.size());
	}
	
	@Test
	public void testGetGiftsByPage() {
		when(this.userGiftRepository.getGiftsByPage(1, "", 1, 1, 0)).thenReturn(this.userGiftsObjectList);
		
		List<Gift> userGifts = this.userGiftService.getGiftsByPage(1, "", 1, 1, 0);
		
		assertEquals(1, userGifts.size());
	}
	
	@Test
	public void testGetNumberOfGifts() {
		List<Object> nGiftsObjectList = new ArrayList<>();
		nGiftsObjectList.add(new Integer(8));
		
		when(this.userGiftRepository.getNumberOfGifts(1, "", 1, 1)).thenReturn(nGiftsObjectList);
		
		int nGifts = this.userGiftService.getNumberOfGifts(1, "", 1, 1);
		
		assertEquals(8, nGifts);
	}
	
	
}
