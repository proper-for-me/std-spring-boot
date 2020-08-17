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

@Controller
@Slf4j
@RequiredArgsConstructor
public class AccountController {

	private final SignupFormValidator signupFormValidator;
	private final AccountRepository accountRepository;
	private final JavaMailSender javaMailSender;
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

		Account account = Account.builder()
				.email(signUpForm.getEmail())
				.nickname(signUpForm.getNickname())
				.password(signUpForm.getPassword())
				.studyCreatedByWeb(true)
				.studyEnrollmentResultByWeb(true)
				.studyUpdatedByWeb(true)
				.build()
				;
		Account newAccount = accountRepository.save(account);

		newAccount.generateEmailCheckToken();
		SimpleMailMessage simpleMailMessage = new SimpleMailMessage();
		simpleMailMessage.setTo(signUpForm.getEmail());
		simpleMailMessage.setSubject("회원가이인증");
		simpleMailMessage.setText("/check-email?token=" +
				newAccount.getEmailCheckToken() + "&email=" + newAccount.getEmail());
		javaMailSender.send(simpleMailMessage);
		return "account/signup" ;
	}

}
