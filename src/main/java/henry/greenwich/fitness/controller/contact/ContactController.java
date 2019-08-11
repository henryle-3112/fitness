package henry.greenwich.fitness.controller.contact;

import henry.greenwich.fitness.model.contact.Contact;
import henry.greenwich.fitness.service.contact.ContactService;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
public class ContactController {
    /**
     * interact with contact's data
     */
    private ContactService contactService;

    /**
     * @param contactService - inject contactService
     */
    public ContactController(ContactService contactService) {
        this.contactService = contactService;
    }

    /**
     * @param id - contact's id that user want to get
     * @return selected contact
     */
    @GetMapping(value = "/contacts/{id}", produces = {MediaType.APPLICATION_JSON_VALUE})
    @ResponseBody
    public Contact getContact(@PathVariable Long id) {
        return this.contactService.getContactById(id);
    }

    /**
     * @param contact - that user want to update to the database
     * @return contact - that was updated to the database
     */
    @PostMapping(value = "/contacts/update", produces = {MediaType.APPLICATION_JSON_VALUE})
    @ResponseBody
    public Contact updateContact(@RequestBody Contact contact) {
        return this.contactService.updateContact(contact);
    }
}
