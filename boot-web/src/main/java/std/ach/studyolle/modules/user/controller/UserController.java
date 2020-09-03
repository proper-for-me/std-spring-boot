package std.ach.studyolle.modules.user.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import lombok.RequiredArgsConstructor;
import std.ach.studyolle.modules.user.UserInfoDto;
import std.ach.studyolle.modules.user.UserService;

@RequiredArgsConstructor
@Controller
public class UserController {

  private final UserService userService;

  @PostMapping("/user")
  public String signup(UserInfoDto infoDto) { // 회원 추가
	  System.out.println("save");
    userService.save(infoDto);
    return "redirect:/";
  }

  @GetMapping("/login")
  public String login(UserInfoDto infoDto) { // 회원 추가
   // userService.save(infoDto);
    return "login";
  }

  @PostMapping("/login")
  public String postlogin(UserInfoDto infoDto) { // 회원 추가
   userService.findByEmail(infoDto.getEmail());
   System.out.println("xxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxx");
    return "login";
  }

  @GetMapping(value = "/logout")
  public String logoutPage(HttpServletRequest request, HttpServletResponse response) {
    new SecurityContextLogoutHandler().logout(request, response, SecurityContextHolder.getContext().getAuthentication());
    return "redirect:/login";
  }
}