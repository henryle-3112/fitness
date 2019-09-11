package henry.greenwich.fitness.controller.chat;

import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import henry.greenwich.fitness.model.chat.ChatRoom;
import henry.greenwich.fitness.service.chat.ChatRoomService;

@Controller
@RequestMapping("chat-management")
public class ChatRoomController {
    private ChatRoomService chatRoomService;

    /**
     * @param ChatRoomService - inject ChatRoomService
     */
    public ChatRoomController(ChatRoomService chatRoomService) {
        this.chatRoomService = chatRoomService;
    }

    /**
     * 
     * @param chatRoom - chat's room that will be added to the database
     * @return inserted chat's room
     */
    @PostMapping(value = "/rooms", produces = { MediaType.APPLICATION_JSON_VALUE })
    @ResponseBody
    public ChatRoom addChatRoom(@RequestBody ChatRoom chatRoom) {
        return this.chatRoomService.addChatRoom(chatRoom);
    }

}
