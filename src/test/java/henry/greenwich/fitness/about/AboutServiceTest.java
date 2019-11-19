package henry.greenwich.fitness.about;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.when;

import java.util.Date;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import henry.greenwich.fitness.model.about.About;
import henry.greenwich.fitness.repository.about.AboutRepository;
import henry.greenwich.fitness.service.about.AboutService;

public class AboutServiceTest {

	@InjectMocks
	private AboutService aboutService;

	@Mock
	private AboutRepository aboutRepository;

	@BeforeEach
	public void setup() {
		MockitoAnnotations.initMocks(this);
	}

	@Test
	public void testGetAboutById() {
		when(this.aboutRepository.findAboutById(1L))
				.thenReturn(new About(1L, "About", "About", "About", "About", "About", new Date(), 1));
		About selectedAbout = this.aboutRepository.findAboutById(1L);
		assertNotNull(selectedAbout);
	}

}
