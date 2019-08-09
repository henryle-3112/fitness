package henry.greenwich.fitness.controller.register;

import henry.greenwich.fitness.model.response.ResponseMessage;
import henry.greenwich.fitness.model.user.*;
import henry.greenwich.fitness.service.user.*;

import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("user-managment")
public class RegisterController {
    private RegisterService registerService;

    /**
     * @param registerService - inject registerService
     */
    public RegisterController(RegisterService registerService) {
        this.registerService = registerService;
    }

    /**
     * @param userAccount - userAccount that could be inserted to the database
     * @return response message
     */
    @PostMapping(value = "/register", produces = { MediaType.APPLICATION_JSON_VALUE })
    @ResponseBody
    public ResponseMessage register(@RequestBody UserAccount userAccount) {
        try {
            boolean isRegisteredSuccessfully = this.registerService.register(userAccount);
            if (isRegisteredSuccessfully) {
                return new ResponseMessage("successfully");
            }
            return new ResponseMessage("failure");
        } catch (Exception e) {
            return new ResponseMessage("failure");
        }
    }

}
