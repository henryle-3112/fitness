package henry.greenwich.fitness.service.chat;

import java.util.List;

import org.springframework.stereotype.Service;

import henry.greenwich.fitness.model.chat.ChatMessage;
import henry.greenwich.fitness.model.chat.ChatRoom;
import henry.greenwich.fitness.repository.chat.ChatMessageRepository;

@Service
public class ChatMessageService {
    private ChatMessageRepository chatMessageRepository;
    private ChatRoomService chatRoomService;

    /**
     * @param chatMessageRepository - inject chatMessageRepository
     */
    public ChatMessageService(ChatMessageRepository chatMessageRepository, ChatRoomService chatRoomService) {
        this.chatMessageRepository = chatMessageRepository;
        this.chatRoomService = chatRoomService;
    }

    /**
     * 
     * @param chatRoomId - chat's rooms id that will be used to get chat's messages
     * @return chat's messages
     */
    public List<ChatMessage> getChatMessages(Long chatRoomId) {
        ChatRoom chatRoom = this.getChatRoom(chatRoomId);
        return this.chatMessageRepository.findChatMessagesByChatRoom(chatRoom);
    }

    /**
     * @param - chatMessage - chat's message that will be added to the database
     */
    public ChatMessage addChatMessage(ChatMessage chatMessage) {
        return this.chatMessageRepository.saveAndFlush(chatMessage);
    }

    /**
     * 
     * @param chatRoomId - chat's room's id that will be used to get selected chat's
     *                   room
     * @return selected chat's room
     */
    private ChatRoom getChatRoom(Long chatRoomId) {
        return this.chatRoomService.getChatRoom(chatRoomId);
    }

}
