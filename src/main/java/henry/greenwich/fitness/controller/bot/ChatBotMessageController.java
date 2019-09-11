package henry.greenwich.fitness.controller.bot;

import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import henry.greenwich.fitness.model.bot.ChatBotMessage;
import henry.greenwich.fitness.service.bot.ChatBotMessageService;

@Controller
@RequestMapping("chat-bot-management")
public class ChatBotMessageController {
    private ChatBotMessageService chatBotMessageService;

    /**
     * @param chatBotMessageService - inject chatBotMessageService
     */
    public ChatBotMessageController(ChatBotMessageService chatBotMessageService) {
        this.chatBotMessageService = chatBotMessageService;
    }

    @PostMapping(value = "/messages", produces = {MediaType.APPLICATION_JSON_VALUE})
    @ResponseBody
    public ChatBotMessage addChatBotMessage(@RequestBody ChatBotMessage chatBotMessage) {
        return this.chatBotMessageService.addChatBotMessage(chatBotMessage);
    }

}
