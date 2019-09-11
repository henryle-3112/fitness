package henry.greenwich.fitness.model.chat;

import henry.greenwich.fitness.constants.Constants;
import henry.greenwich.fitness.model.coach.Coach;
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
@Table(name = Constants.PARTICIPANT_TABLE)
public class Participant {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = Constants.PARTICIPANT_ID)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = Constants.PARTICIPANT_COACH_ID)
    public Coach coach;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = Constants.PARTICIPANT_USER_PROFILE_ID)
    public UserProfile userProfile;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = Constants.PARTICIPANT_CHAT_ROOM_ID)
    public ChatRoom chatRoom;

    
}
