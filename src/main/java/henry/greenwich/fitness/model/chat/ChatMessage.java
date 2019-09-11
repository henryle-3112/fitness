package henry.greenwich.fitness.model.chat;

import henry.greenwich.fitness.constants.Constants;
import henry.greenwich.fitness.model.user.UserProfile;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = Constants.CHAT_MESSAGE_TABLE)
public class ChatMessage {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = Constants.CHAT_MESSAGE_ID)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = Constants.CHAT_MESSAGE_CHAT_ROOM_ID)
    public ChatRoom chatRoom;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = Constants.CHAT_MESSAGE_USER_PROFILE_ID)
    public UserProfile userProfile;

    @Column(name = Constants.CHAT_MESSAGE_MESSAGE)
    private String message;
}
