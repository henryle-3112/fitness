package henry.greenwich.fitness.service.bot;

import org.springframework.stereotype.Service;

import henry.greenwich.fitness.model.bot.ChatBotMessage;
import henry.greenwich.fitness.repository.bot.ChatBotMessageRepository;

@Service
public class ChatBotMessageService {
    private ChatBotMessageRepository chatBotMessageRepository;

    /**
     * @param chatBotMessageRepository - inject chatBotMessageRepository
     */
    public ChatBotMessageService(ChatBotMessageRepository chatBotMessageRepository) {
        this.chatBotMessageRepository = chatBotMessageRepository;
    }

    /**
     * 
     * @param chatBotMessage - chat's bot's message that will be added
     * @return inserted chat's bot's message
     */
    public ChatBotMessage addChatBotMessage(ChatBotMessage chatBotMessage) {
        return this.chatBotMessageRepository.saveAndFlush(chatBotMessage);
    }
}
