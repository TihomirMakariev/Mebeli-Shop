package bg.tihomir.mebeli.web.user;

import bg.tihomir.mebeli.model.dto.binding.UserRegisterDTO;
import bg.tihomir.mebeli.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.LocaleResolver;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

@Controller
@RequestMapping("/users")
public class UserRegistrationController {


    private final UserService userService;
    private final LocaleResolver localeResolver;

    public UserRegistrationController(UserService userService,
                                      LocaleResolver localeResolver) {
        this.userService = userService;
        this.localeResolver = localeResolver;
    }

    @ModelAttribute
    public UserRegisterDTO userRegisterDTO(){
        return new UserRegisterDTO();
    }


    @GetMapping("/register")
    public String register(){
        return "auth-register";
    }

    @PostMapping("/register")
    public String register(@Valid UserRegisterDTO userRegisterDTO,
                           BindingResult bindingResult,
                           RedirectAttributes redirectAttributes,
                           HttpServletRequest httpServletRequest) {

        if (bindingResult.hasErrors()) {
            redirectAttributes.addFlashAttribute("userRegisterDTO", userRegisterDTO);
            redirectAttributes.addFlashAttribute("org.springframework.validation.BindingResult.userRegisterDTO",
                    bindingResult);
            return "redirect:/users/register";
        }
        this.userService.registerAndLogin(userRegisterDTO,
                localeResolver.resolveLocale(httpServletRequest));
        return "redirect:/";
    }

}
