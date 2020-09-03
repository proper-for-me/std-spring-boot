package std.ach.studyolle.modules.user;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserInfoDto {
  private String email;
  private String password;

  private String auth;
}