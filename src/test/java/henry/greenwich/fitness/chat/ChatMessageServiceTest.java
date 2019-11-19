package henry.greenwich.fitness.chat;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.when;

import java.util.ArrayList;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import henry.greenwich.fitness.model.chat.ChatMessage;
import henry.greenwich.fitness.model.chat.ChatRoom;
import henry.greenwich.fitness.model.user.UserProfile;
import henry.greenwich.fitness.repository.chat.ChatMessageRepository;
import henry.greenwich.fitness.repository.chat.ChatRoomRepository;
import henry.greenwich.fitness.service.chat.ChatMessageService;
import henry.greenwich.fitness.service.chat.ChatRoomService;

public class ChatMessageServiceTest {
	private ChatRoom chatRoom;
	private UserProfile userProfile;
	
	@Mock
	private ChatMessageRepository chatMessageRepository;
	
	@Mock
	private ChatRoomService chatRoomService;
	
	@InjectMocks
	private ChatMessageService chatMessageService;
	
	@BeforeEach
	private void setup() {
		MockitoAnnotations.initMocks(this);
		
		this.chatRoom =  new ChatRoom(1L, "ChatRoom", "Normal");
		this.userProfile = new UserProfile(1L, "David", "", 1, 1, 1000);
	}
	
	@Test
	public void testGetChatMessages() {
		when(this.chatRoomService.getChatRoom(1L)).thenReturn(chatRoom);
		
		ChatMessage chatMessage = new ChatMessage(1L, this.chatRoom, this.userProfile, "Chat Message Content");
		
		ArrayList<ChatMessage> chatMessages = new ArrayList<>();
		chatMessages.add(chatMessage);
		
		when(this.chatMessageRepository.findChatMessagesByChatRoom(this.chatRoom)).thenReturn(chatMessages);
		
		chatMessages = (ArrayList<ChatMessage>) this.chatMessageService.getChatMessages(1L);
		
		assertEquals(1, chatMessages.size());
		
	}
	
	@Test
	public void testAddChatMessage() {
		ChatMessage chatMessage = new ChatMessage(1L, this.chatRoom, this.userProfile, "Chat Message Content");
		when(this.chatMessageRepository.saveAndFlush(chatMessage)).thenReturn(chatMessage);
		
		ChatMessage insertedChatMessage = this.chatMessageService.addChatMessage(chatMessage);
		
		assertNotNull(insertedChatMessage);
	}
}
