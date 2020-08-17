package std.ach.studyolle.modules.account;

import lombok.Data;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;


@Data
public class SignUpForm {

    @NotNull
    @NotBlank
    @Pattern(regexp = "^[ㄱ-ㅎㄱ-힣-a-z0-9]{3,20}$")
    private String nickname;

    @NotNull
    @Email
    private String email;

    @NotBlank
    private String password;
}
