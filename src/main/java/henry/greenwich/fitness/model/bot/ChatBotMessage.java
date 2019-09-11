package henry.greenwich.fitness.model.bot;

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
@Table(name = Constants.CHAT_BOT_MESSAGE_TABLE)
public class ChatBotMessage {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = Constants.CHAT_BOT_MESSAGE_ID)
    private Long id;

    @Column(name = Constants.CHAT_BOT_MESSAGE_MESSAGE)
    private String message;
    
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = Constants.CHAT_BOT_MESSAGE_USER_PROFILE_ID)
    public UserProfile userProfile;
}
