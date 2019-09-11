package henry.greenwich.fitness.repository.chat;

import henry.greenwich.fitness.model.chat.ChatRoom;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ChatRoomRepository extends JpaRepository<ChatRoom, Long> {
    /**
     * 
     * @param id - chat room id that will be used to get selected chat's room
     * @return selected chat's room
     */
    ChatRoom findChatRoomById(Long id);
}
