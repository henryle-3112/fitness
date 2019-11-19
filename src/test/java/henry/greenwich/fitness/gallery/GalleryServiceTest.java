package henry.greenwich.fitness.gallery;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import henry.greenwich.fitness.model.gallery.Gallery;
import henry.greenwich.fitness.model.user.UserProfile;
import henry.greenwich.fitness.repository.gallery.GalleryRepository;
import henry.greenwich.fitness.service.gallery.GalleryService;
import henry.greenwich.fitness.service.user.UserProfileService;

public class GalleryServiceTest {
	private UserProfile userProfile;
	private List<Object> galleriesObjectList;

	@Mock
	private GalleryRepository galleryRepository;

	@Mock
	private UserProfileService userProfileService;

	@InjectMocks
	private GalleryService galleryService;

	@BeforeEach
	private void setup() {
		MockitoAnnotations.initMocks(this);

		this.userProfile = new UserProfile(1L, "David", "", 1, 1, 1000);
		when(this.userProfileService.getUserProfile(1L)).thenReturn(this.userProfile);

		Object[] galleryObjectArr = new Object[] { 1, "Image", "Title", new Date(), new Date(), 1, "Thumbnail", 1 };
		this.galleriesObjectList = new ArrayList<>();
		this.galleriesObjectList.add(galleryObjectArr);
	}

	@Test
	public void testGetGalleries() {
		when(this.galleryRepository.getGalleries(1, "Title")).thenReturn(this.galleriesObjectList);

		List<Gallery> galleries = this.galleryService.getGalleries(1, "Title");

		assertEquals(1, galleries.size());
	}

	@Test
	public void testGalleriesByPage() {
		when(this.galleryRepository.getGalleriesByPage(1, "Title", 0)).thenReturn(galleriesObjectList);

		List<Gallery> galleries = this.galleryService.getGalleriesByPage(0, 1, "Title");

		assertEquals(1, galleries.size());
	}
	
	@Test
	public void getNumberOfGalleries() {
		List<Object> nGalleriesObjectList = new ArrayList<>();
		nGalleriesObjectList.add(new Integer(8));
		
		when(this.galleryRepository.getNumberOfGalleries(1, "Title")).thenReturn(nGalleriesObjectList);
		
		int nGalleries = this.galleryService.getNumberOfGalleries(1, "Title");
		
		assertEquals(8, nGalleries);
	}

}
