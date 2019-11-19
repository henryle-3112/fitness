package henry.greenwich.fitness.contact;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.when;

import java.util.Date;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import henry.greenwich.fitness.model.contact.Contact;
import henry.greenwich.fitness.repository.contact.ContactRepository;
import henry.greenwich.fitness.service.contact.ContactService;

public class ContactServiceTest {

	@Mock
	private ContactRepository contactRepository;

	@InjectMocks
	private ContactService contactService;

	@BeforeEach
	private void setup() {
		MockitoAnnotations.initMocks(this);
	}

	@Test
	public void testGetContactById() {
		when(this.contactRepository.findContactById(1L))
				.thenReturn(new Contact(1L, "Contact", "Contact", "Contact", "Contact", "Contact", new Date(), 1));
		Contact selectedContact = this.contactService.getContactById(1L);
		assertNotNull(selectedContact);
	}

}
