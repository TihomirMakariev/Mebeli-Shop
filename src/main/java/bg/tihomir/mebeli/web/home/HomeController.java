package bg.tihomir.mebeli.web.home;

import bg.tihomir.mebeli.model.dto.view.KitchenViewModel;
import bg.tihomir.mebeli.model.dto.view.LivingRoomViewModel;
import bg.tihomir.mebeli.service.KitchenService;
import bg.tihomir.mebeli.service.LivingRoomService;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;


@Controller
@RequestMapping
public class HomeController {

    private final LivingRoomService livingRoomService;
    private final KitchenService kitchenService;

    public HomeController(LivingRoomService livingRoomService,
                          KitchenService kitchenService) {
        this.livingRoomService = livingRoomService;
        this.kitchenService = kitchenService;
    }

    @GetMapping("/")
    public String home(Model model,
                       @PageableDefault(page = 0, size = 4)
                       Pageable pageable) {

        if (!model.containsAttribute("livingRoomViewModel")) {
            model.addAttribute("livingRoomViewModel", new LivingRoomViewModel());
        }

        if (!model.containsAttribute("kitchenViewModel")) {
            model.addAttribute("kitchenViewModel", new KitchenViewModel());
        }

        model.addAttribute("livingRoomViewModel",
                livingRoomService.getAllLivingRooms(pageable));

        model.addAttribute("kitchenViewModel",
                kitchenService.getAllKitchens(pageable));


        return "index";
    }

    @GetMapping("/home/about")
    public String about(){

        return "about";
    }
}
