package std.sec.config;

import lombok.extern.slf4j.Slf4j;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.stereotype.Component;

@EnableWebSecurity
@Component
@Slf4j
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()
                .antMatchers("/", "/main", "/login?fail")
                .permitAll()
            .and()
                .formLogin()

                .loginPage("/main").permitAll()
                .loginProcessingUrl("/loginProcess")
                .defaultSuccessUrl("/loginSuccess")
                .failureUrl("/loginFail")
            .and()
                .logout().permitAll()
                .logoutRequestMatcher(new AntPathRequestMatcher("user/logout"))
                .invalidateHttpSession(true)
            .and()
                .exceptionHandling()
                ;
    }
}
