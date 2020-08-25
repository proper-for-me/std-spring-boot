package std.ach.studyolle.modules;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import std.ach.studyolle.modules.account.Account;

@Controller
public class MainController {

    @RequestMapping("/")
    public String home(@CurrentUser Account account, Model model){

        if(account != null){
            model.addAttribute(account);
        }

        return "index";
    }
}
