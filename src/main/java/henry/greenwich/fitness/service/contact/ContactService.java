package henry.greenwich.fitness.service.contact;

import henry.greenwich.fitness.model.about.About;
import henry.greenwich.fitness.model.contact.Contact;
import henry.greenwich.fitness.repository.contact.ContactRepository;
import org.springframework.stereotype.Service;

@Service
public class ContactService {
    /**
     * contactRepository - interact with contact's data
     */
    private ContactRepository contactRepository;

    /**
     * @param contactRepository - inject contact's repository
     */
    public ContactService(ContactRepository contactRepository) {
        this.contactRepository = contactRepository;
    }

    /**
     * @param id - contact's id
     * @return selected contact
     */
    public Contact getContactById(Long id) {
        return this.contactRepository.findContactById(id);
    }

    /**
     * @param contact -  that user want to update
     * @return contact - that was updated to the database
     */
    public Contact updateContact(Contact contact) {
        return this.contactRepository.saveAndFlush(contact);
    }
}
