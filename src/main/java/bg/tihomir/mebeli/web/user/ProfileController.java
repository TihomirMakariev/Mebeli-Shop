package bg.tihomir.mebeli.web.user;

import bg.tihomir.mebeli.model.dto.view.UserProfileView;
import bg.tihomir.mebeli.service.UserService;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/users")
public class ProfileController {

    private final UserService userService;
    private final ModelMapper modelMapper;

    public ProfileController(UserService userService,
                             ModelMapper modelMapper) {
        this.userService = userService;
        this.modelMapper = modelMapper;
    }

    @GetMapping("/profile/{id}")
    public String profile(@PathVariable Long id, Model model){

        model.addAttribute("profile", modelMapper
                .map(userService.findById(id), UserProfileView.class));

        return "profile";
    }

}
