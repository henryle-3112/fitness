package henry.greenwich.fitness.controller.contact;

import henry.greenwich.fitness.model.contact.Contact;
import henry.greenwich.fitness.service.contact.ContactService;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("contact-management")
public class ContactController {
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
}
