package std.ach.studyolle.modules.account;

import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import javax.validation.Valid;
import java.time.LocalDateTime;

@Controller
@Slf4j
@RequiredArgsConstructor
public class AccountController {

	private final SignupFormValidator signupFormValidator;
	private final AccountService accountService;
	private final AccountRepository accountRepository;
	@InitBinder("signUpForm")
	public void initBinder(WebDataBinder webDataBinder){
		webDataBinder.addValidators(signupFormValidator);
	}

	@GetMapping("/")
	public String index(Model model) {
		log.debug(" rooot / 인덱스 ");
		return "index" ;
	}

	@GetMapping("/signup")
	public String signUpForm(Model model) {
		log.debug(" signUpForm / account/signup ");

		model.addAttribute("signUpForm", new SignUpForm());
		return "account/signup" ;
	}

	@PostMapping("/signup")
	public String signUpSubmit(@Valid SignUpForm signUpForm, Errors errors) {

		if(errors.hasErrors()){
			return "account/signup";
		}

		accountService.processNewAccount(signUpForm);
		return "redirect:/" ;
	}


	/*GET “/check-email-token” token=${token} email=${email} 요청 처리
	이메일이 정확하지 않은 경우에 대한 에러 처리
	토큰이 정확하지 않은 경우에 대한 에러 처리
	이메일과 토큰이 정확한 경우 가입 완료 처리
	가입 일시 설정
	이메일 인증 여부 true로 설정*/
	@GetMapping("/check-email-token")
	public String checkEmailToken(String token, String email, Model model){
		Account account = accountRepository.findByEmail(email);
		String view = "account/checked-email";
		if(account == null){
			model.addAttribute("error", "wrong email");
			return view;
		}

		if(!account.getEmailCheckToken().equals(token)){
			model.addAttribute("error", "wrong token");
			return view;
		}

		account.setEmailVerified(true);
		account.setJoinedAt(LocalDateTime.now());
		model.addAttribute("numberOfUser", accountRepository.count());
		model.addAttribute("nickname", account.getNickname());

		return view;
	}

}
