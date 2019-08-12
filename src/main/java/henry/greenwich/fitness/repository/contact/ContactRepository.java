package henry.greenwich.fitness.repository.contact;

import henry.greenwich.fitness.model.contact.Contact;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ContactRepository extends JpaRepository<Contact, Long> {
    /**
     * @param id - contact's is
     * @return selected contact
     */
    Contact findContactById(Long id);
}
