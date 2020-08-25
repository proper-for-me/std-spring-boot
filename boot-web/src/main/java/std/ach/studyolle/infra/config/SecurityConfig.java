package std.ach.studyolle.infra.config;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.autoconfigure.security.servlet.PathRequest;
import org.springframework.boot.autoconfigure.web.servlet.WebMvcProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@Configuration
@EnableWebSecurity
@Slf4j
@RequiredArgsConstructor
public class SecurityConfig extends WebSecurityConfigurerAdapter {

//    private final UserDetailsService userDetailsService;
//    private final DataSource dataSource;


    @Override
    public void configure(WebSecurity web) throws Exception {

        log.debug("****************************************");
        log.debug(PathRequest.toStaticResources().atCommonLocations().toString());
        web.ignoring()
                .antMatchers("/")
                .antMatchers("/signup", "/check-email-token")
                .requestMatchers(PathRequest.toStaticResources().atCommonLocations())
        // .antMatchers("/", "/login", "/signup", "/check-email-token",
        // "/email-login", "/login-by-email", "/search/study")
        ;

    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()
                .antMatchers("/**").permitAll()
                .mvcMatchers("/", "/login", "/signup", "/check-email-token",
                        "/email-login", "/login-by-email", "/search/study").permitAll()
                .mvcMatchers(HttpMethod.GET, "/profile/*").permitAll()
                .anyRequest().authenticated();
        http.formLogin()
                .loginPage("/login").permitAll();
//
        http.logout()
                .logoutSuccessUrl("/");

//        http.rememberMe()
//                .userDetailsService(userDetailsService)
//                .tokenRepository(tokenRepository());
    }

//    @Bean
//    public PersistentTokenRepository tokenRepository() {
//        JdbcTokenRepositoryImpl jdbcTokenRepository = new JdbcTokenRepositoryImpl();
//        jdbcTokenRepository.setDataSource(dataSource);
//        return jdbcTokenRepository;
//    }
//
//    @Override
//    public void configure(WebSecurity web) throws Exception {
//        web.ignoring()
//                .mvcMatchers("/node_modules/**")
//                .requestMatchers(PathRequest.toStaticResources().atCommonLocations());
//    }
}
