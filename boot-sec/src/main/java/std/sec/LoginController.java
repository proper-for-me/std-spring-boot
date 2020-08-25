package std.sec;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

@Controller
@Slf4j
public class LoginController {

    @RequestMapping(value="/main", method= RequestMethod.GET)
    public String loginByGet(Model model, HttpServletRequest req){
        model.addAttribute("message",req.getServletContext());
        return "main";
    }

    @GetMapping("/loginSuccess")
    @ResponseBody
    public String loginSuccess(HttpServletRequest req){
        log.debug("loginSuccess");
        return "loginSuccess";

    }


    @GetMapping("/loginFail")
    @ResponseBody
    public String loginFail(HttpServletRequest req){
        log.debug("loginFail");
        return "loginFail";

    }

    @RequestMapping("/loginProcess")
    public String loginProcess(HttpServletRequest req){
        log.debug("loginProcessaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa");
        return "loginFail";

    }


}
