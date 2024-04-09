package bg.tihomir.mebeli.web.kitchenRoom;

import bg.tihomir.mebeli.exeption.ObjectNotFoundException;
import bg.tihomir.mebeli.model.dto.binding.AddOrEditKitchenDTO;
import bg.tihomir.mebeli.model.dto.binding.SearchKitchenDTO;
import bg.tihomir.mebeli.model.dto.view.KitchenViewModel;
import bg.tihomir.mebeli.model.user.MebeliUserDetails;
import bg.tihomir.mebeli.repository.KitchenRepository;
import bg.tihomir.mebeli.repository.UserRepository;
import bg.tihomir.mebeli.service.KitchenService;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;
import javax.validation.constraints.AssertTrue;
import java.security.Principal;


@Controller
@RequestMapping("/kitchens")
public class KitchenController {


    private final KitchenRepository kitchenRepository;
    private final KitchenService kitchenService;
    private final UserRepository userRepository;
    private final ModelMapper modelMapper;

    public KitchenController(KitchenRepository kitchenRepository,
                             KitchenService kitchenService,
                             UserRepository userRepository,
                             ModelMapper modelMapper) {

        this.kitchenRepository = kitchenRepository;
        this.kitchenService = kitchenService;
        this.userRepository = userRepository;
        this.modelMapper = modelMapper;
    }


    @GetMapping("/all")
    public String allKitchens(@Valid SearchKitchenDTO searchKitchenDTO,
                              BindingResult bindingResult,
                              RedirectAttributes redirectAttributes,
                              Model model,
                              @PageableDefault(page = 0, size = 4)
                              Pageable pageable) {

        if (!model.containsAttribute("kitchenViewModel")) {
            model.addAttribute("kitchenViewModel", new KitchenViewModel());
        }

        if (bindingResult.hasErrors()) {
            redirectAttributes.addFlashAttribute("searchKitchenDTO", searchKitchenDTO);
            redirectAttributes.addFlashAttribute("org.springframework.validation.BindingResult.searchKitchenDTO", bindingResult);

            return "redirect:/kitchens/all";
        }


        if (!model.containsAttribute("searchKitchenDTO")) {
            model.addAttribute("searchKitchenDTO", new SearchKitchenDTO());
        }

        Page<KitchenViewModel> filteredKitchens =
                kitchenService.searchKitchen(searchKitchenDTO, pageable);

        model.addAttribute("kitchenViewModel", filteredKitchens);

        // Add attribute indicating if kitchens were found
        model.addAttribute("kitchenFound", !filteredKitchens.isEmpty());


        return "room-kitchen";
    }

    @GetMapping("/add")
    public String addKitchen(Model model) {

        if (!model.containsAttribute("addKitchenDTO")) {
            model.addAttribute("addKitchenDTO", new AddOrEditKitchenDTO());
        }

        return "room-kitchen-add";
    }

    @PostMapping("/add")
    public String confirmAddKitchen(@Valid AddOrEditKitchenDTO addOrEditKitchenDTO,
                                    BindingResult bindingResult,
                                    RedirectAttributes redirectAttributes,
                                    @AuthenticationPrincipal MebeliUserDetails userDetails) {

        if (bindingResult.hasErrors()) {
            redirectAttributes.addFlashAttribute("addKitchenDTO", addOrEditKitchenDTO);
            redirectAttributes.addFlashAttribute("org.springframework.validation.BindingResult.addKitchenDTO", bindingResult);

            return "redirect:/kitchens/add";
        }

        this.kitchenService.addKitchen(addOrEditKitchenDTO, userDetails);

        return "redirect:/kitchens/all";
    }

    @GetMapping("/details/{id}")
    public String detailsKitchen(@PathVariable("id") Long id,
                                 Model model,
                                 Principal principal) {

        String currentUser = principal != null ? principal.getName() : null;

        KitchenViewModel kitchenViewModel = kitchenService
                .findById(id, currentUser)
                .orElseThrow(() -> new ObjectNotFoundException("Kitchen with id "
                        + id + " not found!"));

        model.addAttribute("kitchenViewModel", kitchenViewModel);

        return "room-kitchen-details";

    }

    @ModelAttribute("addOrEditKitchenDTO")
    public AddOrEditKitchenDTO addOrEditKitchenDTO(){
        return new AddOrEditKitchenDTO();
    }

    @PreAuthorize("@kitchenService.isOwner(#principal.name, #id)")
    @GetMapping("/update/{id}")
    public String updateKitchen(Principal principal,
                                @PathVariable("id") Long id, Model model) {
        if (!model.containsAttribute("addOrEditKitchenDTO")) {
            model.addAttribute("addOrEditKitchenDTO", AddOrEditKitchenDTO.class);
        }

        model.addAttribute("addOrEditKitchenDTO",
                kitchenService.getById(id));

        return "room-kitchen-update";

    }

    @PostMapping("/update/{id}")
    public String confirmUpdate(@PathVariable("id") Long id,
                                @Valid AddOrEditKitchenDTO addOrEditKitchenDTO,
                                BindingResult bindingResult,
                                RedirectAttributes redirectAttributes) {

        if (bindingResult.hasErrors()) {
            redirectAttributes.addFlashAttribute("addOrEditKitchenDTO", addOrEditKitchenDTO);
            redirectAttributes.addFlashAttribute("org.springframework.validation.BindingResult.addOrEditKitchenDTO",
                    bindingResult);

            return "redirect:/kitchens/update/{id}";
        }

        KitchenViewModel kitchenViewModel =
                this.modelMapper.map(addOrEditKitchenDTO, KitchenViewModel.class);

        this.kitchenService.updateKitchen(kitchenViewModel);
        kitchenViewModel.setId(id);

        return "redirect:/kitchens/details/{id}";
    }

    @PreAuthorize("@kitchenService.isOwner(#principal.name, #id)")
    @DeleteMapping("/details/{id}")
    public String deleteKitchen(Principal principal,
                                @PathVariable("id") Long id) {
        kitchenService.deleteKitchen(id);

        return "redirect:/kitchens/all";

    }
}
