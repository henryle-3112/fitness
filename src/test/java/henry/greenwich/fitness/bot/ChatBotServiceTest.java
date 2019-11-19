package henry.greenwich.fitness.bot;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import henry.greenwich.fitness.model.bot.ChatBotMessage;
import henry.greenwich.fitness.model.user.UserProfile;
import henry.greenwich.fitness.repository.bot.ChatBotMessageRepository;
import henry.greenwich.fitness.service.bot.ChatBotMessageService;

public class ChatBotServiceTest {
	
	@Mock
	private ChatBotMessageRepository chatBotMessageRepository;
	
	@InjectMocks
	private ChatBotMessageService chatBotMessageService;
	
	@BeforeEach
	private void setup() {
		MockitoAnnotations.initMocks(this);
	}
	
	@Test
	private void addChatBotMessage() {
		UserProfile userProfile = new UserProfile(1L, "David", "", 1, 1, 1000);
		ChatBotMessage chatBotMessage = new ChatBotMessage(1L, "I want to build my full body", userProfile);
		when(this.chatBotMessageRepository.saveAndFlush(chatBotMessage)).thenReturn(chatBotMessage);
		ChatBotMessage insertedChatBotMessage = this.chatBotMessageService.addChatBotMessage(chatBotMessage);
		assertNotNull(insertedChatBotMessage);
	}
}
