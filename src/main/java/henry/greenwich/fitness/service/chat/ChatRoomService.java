package henry.greenwich.fitness.service.chat;

import org.springframework.stereotype.Service;

import henry.greenwich.fitness.model.chat.ChatRoom;
import henry.greenwich.fitness.repository.chat.ChatRoomRepository;

@Service
public class ChatRoomService {
    private ChatRoomRepository chatRoomRepository;

    /**
     * @param chatRoomRepository - inject chatRoomRepository
     */
    public ChatRoomService(ChatRoomRepository chatRoomRepository) {
        this.chatRoomRepository = chatRoomRepository;
    }

    /**
     * 
     * @param chatRoom - chat's room that will be added
     * @return inserted chat's room
     */
    public ChatRoom addChatRoom(ChatRoom chatRoom) {
        return this.chatRoomRepository.saveAndFlush(chatRoom);
    }

    /**
     * 
     * @param chatRoomId - chat's room's id that will be used to get selected chat's
     *                   room
     * @return selected chat's room
     */
    public ChatRoom getChatRoom(Long chatRoomId) {
        return this.chatRoomRepository.findChatRoomById(chatRoomId);
    }

}
