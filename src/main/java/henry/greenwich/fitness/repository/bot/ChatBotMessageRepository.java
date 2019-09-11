package henry.greenwich.fitness.repository.bot;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import henry.greenwich.fitness.model.bot.ChatBotMessage;;


@Repository
public interface ChatBotMessageRepository extends JpaRepository<ChatBotMessage, Long> {
}
