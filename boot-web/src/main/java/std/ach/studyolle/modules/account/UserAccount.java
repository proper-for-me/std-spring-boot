package std.ach.studyolle.modules.account;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;

import java.util.Collection;
import java.util.List;

@Getter
public class UserAccount extends User {

    private Account account;

    public UserAccount(Account account) {

        super(  account.getNickname(), account.getPassword(), List.of(new SimpleGrantedAuthority("ROLE_USER")));
        account = account;
    }
}
