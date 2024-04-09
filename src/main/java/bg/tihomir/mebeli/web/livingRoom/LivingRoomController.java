package bg.tihomir.mebeli.web.livingRoom;


import bg.tihomir.mebeli.exeption.ObjectNotFoundException;
import bg.tihomir.mebeli.model.dto.binding.AddOrEditLivingRoomDTO;
import bg.tihomir.mebeli.model.dto.binding.SearchLivingRoomDTO;
import bg.tihomir.mebeli.model.dto.view.LivingRoomViewModel;
import bg.tihomir.mebeli.model.user.MebeliUserDetails;
import bg.tihomir.mebeli.repository.LivingRoomRepository;
import bg.tihomir.mebeli.repository.UserRepository;
import bg.tihomir.mebeli.service.LivingRoomService;
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
import java.security.Principal;

@Controller
@RequestMapping("/living-rooms")
public class LivingRoomController {


    private final UserRepository userRepository;
    private final LivingRoomRepository livingRoomRepository;
    private final LivingRoomService livingRoomService;
    private final ModelMapper modelMapper;
    private Pageable pageable;

    public LivingRoomController(UserRepository userRepository,
                                LivingRoomRepository livingRoomRepository,
                                LivingRoomService livingRoomService,
                                ModelMapper modelMapper) {
        this.userRepository = userRepository;
        this.livingRoomRepository = livingRoomRepository;
        this.livingRoomService = livingRoomService;
        this.modelMapper = modelMapper;
    }

    @ModelAttribute("livingRoomViewModel")
    public LivingRoomViewModel LivingRoomViewModel() {
        return new LivingRoomViewModel();
    }


    @GetMapping("/all")
    public String allLivingRooms(@Valid SearchLivingRoomDTO searchLivingRoomDTO,
                                   BindingResult bindingResult,
                                   RedirectAttributes redirectAttributes,
                                   Model model,
                                   @PageableDefault(page = 0, size = 4)
                                   Pageable pageable) {

        if (bindingResult.hasErrors()) {
            redirectAttributes.addFlashAttribute("searchLivingRoomDTO", searchLivingRoomDTO);
            redirectAttributes.addFlashAttribute("org.springframework.validation.BindingResult.searchLivingRoomDTO", searchLivingRoomDTO);

            return "redirect:/living-rooms/all";
        }

        if (!model.containsAttribute("searchLivingRoomDTO")) {
            model.addAttribute("searchLivingRoomDTO", new SearchLivingRoomDTO());
        }

        Page<LivingRoomViewModel> filteredLivingRooms =
                livingRoomService.searchLivingRoom(searchLivingRoomDTO, pageable);

        model.addAttribute("livingRoomViewModel", filteredLivingRooms);

        // Add attribute indicating if living rooms were found
        model.addAttribute("livingRoomsFound", !filteredLivingRooms.isEmpty());

        return "room-living-room";

    }

    @ModelAttribute("addLivingRoomDTO")
    public AddOrEditLivingRoomDTO addLivingRoomDTO() {
        return new AddOrEditLivingRoomDTO();
    }

    @GetMapping("/add")
    public String addLivingRoom() {
        return "room-living-room-add";
    }

    @PostMapping("/add")
    public String addLivingRoomConfirm(@Valid AddOrEditLivingRoomDTO addLivingRoomDTO,
                                       BindingResult bindingResult,
                                       RedirectAttributes redirectAttributes,
                                       @AuthenticationPrincipal MebeliUserDetails userDetails) {
        if (bindingResult.hasErrors()) {
            redirectAttributes.addFlashAttribute("addLivingRoomDTO", addLivingRoomDTO);
            redirectAttributes.addFlashAttribute("org.springframework.validation.BindingResult.addLivingRoomDTO",
                    bindingResult);

            return "redirect:/living-rooms/add";
        }

        this.livingRoomService.addLivingRoom(addLivingRoomDTO, userDetails);

        return "redirect:/living-rooms/all";
    }

    @GetMapping("/details/{id}")
    public String detailsLivingRoom(@PathVariable Long id,
                                    Model model,
                                    Principal principal) {

        String currentUser = principal != null ? principal.getName() : null;

        LivingRoomViewModel livingRoomViewModel =
                livingRoomService
                        .findById(id, currentUser)
                        .orElseThrow(() -> new ObjectNotFoundException("Offer with UUID " +
                                id + " not found!"));

        model.addAttribute("livingRoomViewModel", livingRoomViewModel);

        return "room-living-room-details";
    }

    @PreAuthorize("@livingRoomService.isOwner(#principal.name, #id)")
    @GetMapping("/update/{id}")
    public String updateLivingRoom(Principal principal,
                                   @PathVariable Long id,
                                   Model model) {

        model.addAttribute("addOrUpdateLivingRoomDTO",
                livingRoomService.getById(id));

        return "room-living-room-update";
    }


    @PostMapping("/update/{id}")
    public String updateConfirm(@PathVariable("id") Long id,
                                @Valid AddOrEditLivingRoomDTO addOrUpdateLivingRoomDTO,
                                BindingResult bindingResult,
                                RedirectAttributes redirectAttributes) {
        if (bindingResult.hasErrors()) {
            redirectAttributes.addFlashAttribute("addOrUpdateLivingRoomDTO", addOrUpdateLivingRoomDTO);
            redirectAttributes.addFlashAttribute("org.springframework.validation.BindingResult.addOrUpdateLivingRoomDTO",
                    bindingResult);

            return "redirect:/living-rooms/update/{id}";
        }

        LivingRoomViewModel livingRoomViewModel =
                this.modelMapper.map(addOrUpdateLivingRoomDTO, LivingRoomViewModel.class);

        this.livingRoomService.updateLivingRoom(livingRoomViewModel);
        livingRoomViewModel.setId(id);

        return "redirect:/living-rooms/details/{id}";
    }

    @PreAuthorize("@livingRoomService.isOwner(#principal.name, #id)")
    @DeleteMapping("/details/{id}")
    public String deleteLivingRoom(Principal principal,
                                   @PathVariable("id") Long id) {

        livingRoomService.deleteLivingRoomById(id);

        return "redirect:/living-rooms/all";
    }
}
