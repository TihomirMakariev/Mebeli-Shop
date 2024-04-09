package bg.tihomir.mebeli.service;

import bg.tihomir.mebeli.model.dto.binding.AddOrEditKitchenDTO;
import bg.tihomir.mebeli.model.dto.binding.SearchKitchenDTO;
import bg.tihomir.mebeli.model.dto.view.KitchenViewModel;
import bg.tihomir.mebeli.model.entity.KitchenEntity;
import bg.tihomir.mebeli.model.entity.UserEntity;
import bg.tihomir.mebeli.model.enums.UserRoleEnum;
import bg.tihomir.mebeli.model.user.MebeliUserDetails;
import bg.tihomir.mebeli.repository.KitchenRepository;
import bg.tihomir.mebeli.repository.KitchenSpecification;
import bg.tihomir.mebeli.repository.UserRepository;
import org.hibernate.ObjectNotFoundException;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Optional;


@Service
public class KitchenService {


    private final KitchenRepository kitchenRepository;
    private final UserRepository userRepository;
    private final ModelMapper modelMapper;

    public KitchenService(KitchenRepository kitchenRepository,
                          UserRepository userRepository,
                          ModelMapper modelMapper) {
        this.kitchenRepository = kitchenRepository;
        this.userRepository = userRepository;
        this.modelMapper = modelMapper;
    }



    public void addKitchen(AddOrEditKitchenDTO addOrEditKitchenDTO,
                           MebeliUserDetails userDetails) {

        //1 - Fetch the author user entity
        UserEntity author = userRepository.findByEmail(userDetails.getUsername())
                .orElseThrow(()-> new RuntimeException("User not found: " + userDetails.getUsername()));

        //2 - Map DTO to KitchenEntity
        KitchenEntity newKitchen = modelMapper.map(addOrEditKitchenDTO, KitchenEntity.class);

        //3 - Set the author
        newKitchen.setAuthor(author);

        //4 - Save the new kitchen
        this.kitchenRepository.save(newKitchen);
    }

    public Optional<KitchenViewModel> findById(Long id, String currentUser) {
        return kitchenRepository
                .findById(id)
                .map(kitchenEntity -> {
                    KitchenViewModel dto = modelMapper.map(kitchenEntity, KitchenViewModel.class);
                    dto.setAuthorFirstName(kitchenEntity.getAuthor().getFirstName());
                    dto.setAuthorLastName(kitchenEntity.getAuthor().getLastName());
                    dto.setAuthor(kitchenEntity.getAuthor());
                    dto.setCanDelete(isOwner(currentUser, kitchenEntity.getId()));
                    return dto;
                });
    }

    public Page<KitchenViewModel> getAllKitchens(Pageable pageable) {

        return kitchenRepository
                .findAll(pageable)
                .map(kitchenEntity ->
                        modelMapper.map(kitchenEntity, KitchenViewModel.class));

    }

    public boolean isOwner(String userName, Long kitchenID) {
        boolean isOwner =  kitchenRepository
                .findById(kitchenID)
                .filter(kitchenEntity -> kitchenEntity
                        .getAuthor()
                        .getEmail()
                        .equals(userName))
                .isPresent();

        if (isOwner) {
            return true;
        }

        return userRepository
                .findByEmail(userName)
                .filter(this::isAdmin)
                .isPresent();
    }

    private boolean isAdmin (UserEntity userEntity) {
        return userEntity.getUserRoles()
                .stream()
                .anyMatch(userRole -> userRole.getUserRole() == UserRoleEnum.ADMIN);
    }


    public AddOrEditKitchenDTO getById(Long id) {
        return kitchenRepository
                .findById(id)
                .map(kitchenEntity -> modelMapper.map(kitchenEntity, AddOrEditKitchenDTO.class))
                .orElse(null);
    }

    public void updateKitchen(KitchenViewModel kitchenViewModel) {

        KitchenEntity updatedKitchenEntity = this.kitchenRepository
                .findById(kitchenViewModel.getId())
                .orElseThrow(()-> new ObjectNotFoundException(kitchenViewModel.getId(),
                        "Kitchen with id: " + kitchenViewModel.getId() + " not found!"));

        updatedKitchenEntity.setId(kitchenViewModel.getId());
        updatedKitchenEntity.setName(kitchenViewModel.getName());
        updatedKitchenEntity.setColor(kitchenViewModel.getColor());
        updatedKitchenEntity.setPrice(kitchenViewModel.getPrice());
        updatedKitchenEntity.setImageUrl(kitchenViewModel.getImageUrl());
        updatedKitchenEntity.setCategory(kitchenViewModel.getCategory());

        this.kitchenRepository.save(updatedKitchenEntity);
    }

    public void deleteKitchen(Long id) {
        kitchenRepository.deleteById(id);
    }

    public Page<KitchenViewModel> searchKitchen(SearchKitchenDTO searchKitchenDTO,
                                                Pageable pageable) {
        return kitchenRepository
                .findAll(new KitchenSpecification(searchKitchenDTO), pageable)
                .map(kitchenEntity -> modelMapper.map(kitchenEntity, KitchenViewModel.class));
    }

}
