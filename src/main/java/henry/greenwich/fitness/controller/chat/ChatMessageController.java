package henry.greenwich.fitness.controller.chat;

import java.util.List;

import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import henry.greenwich.fitness.model.chat.ChatMessage;
import henry.greenwich.fitness.service.chat.ChatMessageService;

@Controller
@RequestMapping("chat-management")
public class ChatMessageController {
    private ChatMessageService chatMessageService;

    /**
     * @param chatMessageService - inject chatMessageService
     */
    public ChatMessageController(ChatMessageService chatMessageService) {
        this.chatMessageService = chatMessageService;
    }

    /**
     * 
     * @param chatRoomId - chat's room's id that will be used to get chat's messages
     * @return chat's messages
     */
    @GetMapping(value = "/messages", produces = { MediaType.APPLICATION_JSON_VALUE })
    @ResponseBody
    public List<ChatMessage> getChatMessages(@RequestParam(required = false) Long chatRoomId) {
        return this.chatMessageService.getChatMessages(chatRoomId);
    }

    /**
     * 
     * @param chatMessage - chat's message that will be added to the database
     * @return inserted chat's message
     */
    @PostMapping(value = "/messages", produces = { MediaType.APPLICATION_JSON_VALUE })
    @ResponseBody
    public ChatMessage addChatMessage(@RequestBody ChatMessage chatMessage) {
        return this.chatMessageService.addChatMessage(chatMessage);
    }

}
