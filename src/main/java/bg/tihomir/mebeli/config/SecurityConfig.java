package bg.tihomir.mebeli.config;

import bg.tihomir.mebeli.repository.UserRepository;
import bg.tihomir.mebeli.service.MebeliUserDetailsService;
import org.modelmapper.ModelMapper;
import org.springframework.boot.autoconfigure.security.servlet.PathRequest;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class SecurityConfig {

    //Here we have to expose 3 things:
    // 1. PasswordEncoder
    // 2. SecurityFilterChain
    // 3. UserDetailsService

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity httpSecurity) throws Exception{

     httpSecurity
             // define which requests are allowed and which not
             .authorizeRequests()
             // everyone can download static resources (css, js, images)
             .requestMatchers(PathRequest.toStaticResources().atCommonLocations()).permitAll()
             // everyone can login and register
             .antMatchers("/", "/users/login", "/users/register").permitAll()
             .antMatchers("/living-rooms/add", "/living-rooms/edit").authenticated()
             .antMatchers("/living-rooms/details/{id}", "/living-rooms/all").permitAll()
             .antMatchers("/kitchens/details/{id}", "/kitchens/all").permitAll()
             .antMatchers("/kitchens/add","/kitchens/edit").authenticated()
             .antMatchers("/home/about").permitAll()
             // all other pages are available for logger in users
             .anyRequest()
             .authenticated()
     .and()
             // configuration of form login
             .formLogin()
             // the custom login form
             .loginPage("/users/login")
             // the name of the username form field
             .usernameParameter(UsernamePasswordAuthenticationFilter.SPRING_SECURITY_FORM_USERNAME_KEY)
             // the name of the password form field
             .passwordParameter(UsernamePasswordAuthenticationFilter.SPRING_SECURITY_FORM_PASSWORD_KEY)
             // where to go in case that the login is successful
             .defaultSuccessUrl("/")
             // where to go in case that the login failed
             .failureForwardUrl("/users/login-error")
     .and()
             // configure logout
             .logout()
             // which is the logout url, must be POST request
             .logoutUrl("/users/logout")
             // on logout go to the home page
             .logoutSuccessUrl("/")
             // invalidate the session and delete the cookies
             .invalidateHttpSession(true)
             .deleteCookies("JSESSIONID");


        return httpSecurity.build();
    }

    @Bean
    public UserDetailsService userDetailsService (UserRepository userRepository){
        return new MebeliUserDetailsService(userRepository);
    }

    @Bean
    public ModelMapper modelMapper(){
        return new ModelMapper();
    }


}
