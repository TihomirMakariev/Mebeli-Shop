package bg.tihomir.mebeli.service;

import bg.tihomir.mebeli.model.dto.binding.AddOrEditLivingRoomDTO;
import bg.tihomir.mebeli.model.dto.binding.SearchLivingRoomDTO;
import bg.tihomir.mebeli.model.dto.view.LivingRoomViewModel;
import bg.tihomir.mebeli.model.entity.LivingRoomEntity;
import bg.tihomir.mebeli.model.entity.UserEntity;
import bg.tihomir.mebeli.model.enums.UserRoleEnum;
import bg.tihomir.mebeli.model.user.MebeliUserDetails;
import bg.tihomir.mebeli.repository.LivingRoomRepository;
import bg.tihomir.mebeli.repository.LivingRoomSpecification;
import bg.tihomir.mebeli.repository.UserRepository;
import org.hibernate.ObjectNotFoundException;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class LivingRoomService {


    private final LivingRoomRepository livingRoomRepository;
    private final UserRepository userRepository;
    private final ModelMapper modelMapper;

    public LivingRoomService(LivingRoomRepository livingRoomRepository,
                             UserRepository userRepository,
                             ModelMapper modelMapper) {
        this.livingRoomRepository = livingRoomRepository;
        this.userRepository = userRepository;
        this.modelMapper = modelMapper;
    }

    //The same code as below. Created from ChatGPT
//    public boolean isOwner(String userName, Long livingRoomID) {
//        return userRepository
//                .findByEmail(userName)
//                .map(this::isAdmin)
//                .orElse(false)
//                ||
//                livingRoomRepository
//                        .findById(livingRoomID)
//                        .filter(livingRoomEntity -> livingRoomEntity
//                                .getAuthor()
//                                .getEmail()
//                                .equals(userName))
//                        .isPresent();
//    }

    public boolean isOwner(String userName, Long livingRoomID) {
        boolean isOwner = livingRoomRepository
                .findById(livingRoomID)
                .filter(livingRoomEntity -> livingRoomEntity
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

    private boolean isAdmin(UserEntity userEntity) {
        return userEntity.getUserRoles()
                .stream()
                .anyMatch(userRole -> userRole.getUserRole() == UserRoleEnum.ADMIN);
    }

    public Page<LivingRoomViewModel> getAllLivingRooms(Pageable pageable) {

        return livingRoomRepository
                .findAll(pageable)
                .map(livingRoomEntity ->
                        modelMapper.map(livingRoomEntity, LivingRoomViewModel.class));

    }

    public void addLivingRoom(AddOrEditLivingRoomDTO addLivingRoomDTO,
                              MebeliUserDetails userDetails) {
        // Fetch the author user entity
        UserEntity author = userRepository.findByEmail(userDetails.getUsername())
                .orElseThrow(() -> new RuntimeException("User not found: " + userDetails.getUsername()));

        // Map DTO to LivingRoomEntity
        LivingRoomEntity newLivingRoom = modelMapper.map(addLivingRoomDTO, LivingRoomEntity.class);

        //3 - Set the author
        newLivingRoom.setAuthor(author);

        //4 - Save the new kitchen
        this.livingRoomRepository.save(newLivingRoom);
    }


    public List<LivingRoomViewModel> findAllLivingRooms() {

        return livingRoomRepository
                .findAll()
                .stream()
                .map(livingRoomEntity -> modelMapper.map(livingRoomEntity, LivingRoomViewModel.class))
                .collect(Collectors.toList());
    }


    public Optional<LivingRoomViewModel> findById(Long id, String currentUser) {
        return livingRoomRepository
                .findById(id)
                .map(livingRoomEntity -> {
                    LivingRoomViewModel dto = modelMapper.map(livingRoomEntity, LivingRoomViewModel.class);
                    dto.setAuthorFirstName(livingRoomEntity.getAuthor().getFirstName());
                    dto.setAuthorLastName(livingRoomEntity.getAuthor().getLastName());
                    dto.setAuthor(livingRoomEntity.getAuthor());
                    dto.setCanDelete(isOwner(currentUser, livingRoomEntity.getId()));
                    return dto;
                });
    }

    public AddOrEditLivingRoomDTO getById(Long id) {
        return livingRoomRepository
                .findById(id)
                .map(livingRoomEntity -> modelMapper.map(livingRoomEntity, AddOrEditLivingRoomDTO.class))
                .orElse(null);
    }

    public void updateLivingRoom(LivingRoomViewModel
                                         livingRoomViewModel) {

        LivingRoomEntity livingRoomEntity = this.livingRoomRepository
                .findById(livingRoomViewModel.getId())
                .orElseThrow(() -> new ObjectNotFoundException(
                        livingRoomViewModel.getId(),
                        "Living room with id: " + livingRoomViewModel.getId() + " not found!"));

        livingRoomEntity.setName(livingRoomViewModel.getName());
        livingRoomEntity.setColor(livingRoomViewModel.getColor());
        livingRoomEntity.setPrice(livingRoomViewModel.getPrice());
        livingRoomEntity.setImageUrl(livingRoomViewModel.getImageUrl());
        livingRoomEntity.setCategory(livingRoomViewModel.getCategory());


        this.livingRoomRepository.save(livingRoomEntity);
    }

    public void deleteLivingRoomById(Long LivingRoomId) {
        livingRoomRepository.deleteById(LivingRoomId);
    }

    public Page<LivingRoomViewModel> searchLivingRoom(SearchLivingRoomDTO searchLivingRoomDTO,
                                                      Pageable pageable) {
        return livingRoomRepository
                .findAll(new LivingRoomSpecification(searchLivingRoomDTO), pageable)
                .map(livingRoom -> modelMapper.map(livingRoom, LivingRoomViewModel.class));
    }

}
