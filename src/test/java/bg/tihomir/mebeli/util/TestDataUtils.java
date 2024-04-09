package bg.tihomir.mebeli.util;


import bg.tihomir.mebeli.model.entity.LivingRoomEntity;
import bg.tihomir.mebeli.model.entity.UserEntity;
import bg.tihomir.mebeli.model.entity.UserRoleEntity;
import bg.tihomir.mebeli.model.enums.CategoryEnum;
import bg.tihomir.mebeli.model.enums.ColorEnum;
import bg.tihomir.mebeli.model.enums.UserRoleEnum;
import bg.tihomir.mebeli.repository.LivingRoomRepository;
import bg.tihomir.mebeli.repository.UserRepository;
import bg.tihomir.mebeli.repository.UserRoleRepository;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;

@Component
public class TestDataUtils {


    private final UserRepository userRepository;
    private final UserRoleRepository userRoleRepository;
    private final LivingRoomRepository livingRoomRepository;

    public TestDataUtils(UserRepository userRepository,
                         UserRoleRepository userRoleRepository,
                         LivingRoomRepository livingRoomRepository) {
        this.userRepository = userRepository;
        this.userRoleRepository = userRoleRepository;
        this.livingRoomRepository = livingRoomRepository;
    }

    private void initRoles() {
        if (userRoleRepository.count() == 0) {
            UserRoleEntity adminRole = new UserRoleEntity().setUserRole(UserRoleEnum.ADMIN);
            UserRoleEntity userRole = new UserRoleEntity().setUserRole(UserRoleEnum.USER);

            userRoleRepository.save(adminRole);
            userRoleRepository.save(userRole);
        }
    }

    public UserEntity createTestAdmin(String email) {

        initRoles();

        UserEntity admin = new UserEntity()
                .setEmail(email)
                .setFirstName("Admin")
                .setLastName("Adminov")
                .setActive(true)
                .setUserRoles(userRoleRepository.findAll());

        return userRepository.save(admin);
    }

    public UserEntity createTestUser(String email) {

        initRoles();

        UserEntity user = new UserEntity()
                .setEmail(email)
                .setFirstName("User")
                .setLastName("Userov")
                .setActive(true)
                .setUserRoles(userRoleRepository.findAll());

        return userRepository.save(user);
    }



    public LivingRoomEntity createTestLivingRoom(UserEntity author) {

        LivingRoomEntity livingRoom = new LivingRoomEntity()
                .setName("Test_Диван")
                .setColor(ColorEnum.GREEN)
                .setPrice(BigDecimal.TEN)
                .setImageUrl("http://image.com/image.jpg")
                .setCategory(CategoryEnum.LIVING_ROOM)
                .setAuthor(author);

        return livingRoomRepository.save(livingRoom);
    }

    public void cleanerDataBesa () {
        livingRoomRepository.deleteAll();
        userRepository.deleteAll();
        userRoleRepository.deleteAll();
    }


}
