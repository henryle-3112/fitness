package henry.greenwich.fitness.model.chat;

import henry.greenwich.fitness.constants.Constants;
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
@Table(name = Constants.CHAT_ROOM_TABLE)
public class ChatRoom {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = Constants.CHAT_ROOM_ID)
    private Long id;

    @Column(name = Constants.CHAT_ROOM_NAME)
    private String name;

    @Column(name = Constants.CHAT_ROOM_TYPE)
    private String type;
}
