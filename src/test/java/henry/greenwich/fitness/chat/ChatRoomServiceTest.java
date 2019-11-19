package henry.greenwich.fitness.chat;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import henry.greenwich.fitness.model.chat.ChatRoom;
import henry.greenwich.fitness.repository.chat.ChatRoomRepository;
import henry.greenwich.fitness.service.chat.ChatRoomService;

public class ChatRoomServiceTest {
	private ChatRoom chatRoom;
	
	@Mock
	private ChatRoomRepository chatRoomRepository;
	
	@InjectMocks
	private ChatRoomService chatRoomService;
	
	@BeforeEach
	public void setup() {
		MockitoAnnotations.initMocks(this);
		
		this.chatRoom = new ChatRoom(1L, "Chat Room", "normal");
	}
	
	@Test
	public void testAddChatRoom() {
		when(this.chatRoomRepository.saveAndFlush(this.chatRoom)).thenReturn(this.chatRoom);
		
		ChatRoom insertedChatRoom = this.chatRoomService.addChatRoom(this.chatRoom);
		
		assertNotNull(insertedChatRoom);
	}
	
	@Test
	public void testGetChatRoom() {
		when(this.chatRoomRepository.findChatRoomById(1L)).thenReturn(this.chatRoom);
		
		ChatRoom selectedChatRoom = this.chatRoomService.getChatRoom(1L);
		
		assertNotNull(selectedChatRoom);
	}

}
