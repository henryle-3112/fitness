package henry.greenwich.fitness.repository.chat;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import henry.greenwich.fitness.model.chat.ChatMessage;
import henry.greenwich.fitness.model.chat.ChatRoom;

@Repository
public interface ChatMessageRepository extends JpaRepository<ChatMessage, Long> {

    /**
     * 
     * @param chatRoom - chat's room that will be used to get chat's messages
     * @return chat's messages
     */
    List<ChatMessage> findChatMessagesByChatRoom(ChatRoom chatRoom);
}
