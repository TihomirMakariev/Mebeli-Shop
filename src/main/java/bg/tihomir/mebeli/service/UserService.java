package bg.tihomir.mebeli.service;

import bg.tihomir.mebeli.model.dto.binding.UserRegisterDTO;
import bg.tihomir.mebeli.model.dto.view.UserProfileView;
import bg.tihomir.mebeli.model.entity.UserEntity;
import bg.tihomir.mebeli.model.entity.UserRoleEntity;
import bg.tihomir.mebeli.model.enums.UserRoleEnum;
import bg.tihomir.mebeli.model.mapper.UserMapper;
import bg.tihomir.mebeli.repository.UserRepository;
import bg.tihomir.mebeli.repository.UserRoleRepository;
import org.modelmapper.ModelMapper;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Locale;


@Service
public class UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final UserMapper userMapper;
    private final UserDetailsService userDetailsService;
    private final ModelMapper modelMapper;
    private final UserRoleRepository userRoleRepository;

    public UserService(UserRepository userRepository,
                       PasswordEncoder passwordEncoder,
                       UserMapper userMapper,
                       UserDetailsService userDetailsService,
                       ModelMapper modelMapper,
                       UserRoleRepository userRoleRepository) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.userMapper = userMapper;
        this.userDetailsService = userDetailsService;
        this.modelMapper = modelMapper;
        this.userRoleRepository = userRoleRepository;
    }

    public void registerAndLogin(UserRegisterDTO userRegisterDTO,
                                 Locale preferredLocale) {

        UserEntity newUser = userMapper.userDtoToUserEntity(userRegisterDTO);
        UserRoleEntity userRoleEntity = this.userRoleRepository.findByUserRole(UserRoleEnum.USER);

        newUser.setUserRoles(List.of(userRoleEntity));
        newUser.setPassword(passwordEncoder.encode(userRegisterDTO.getPassword()));

        this.userRepository.save(newUser);
        login(newUser);

    }

    private void login(UserEntity userEntity) {
        UserDetails userDetails =
                userDetailsService.loadUserByUsername(userEntity.getEmail());

        Authentication auth =
                new UsernamePasswordAuthenticationToken(
                        userDetails,
                        userDetails.getPassword(),
                        userDetails.getAuthorities()
                );

        SecurityContextHolder.
                getContext().
                setAuthentication(auth);
    }

    public UserProfileView findById(Long id) {

        return userRepository.findById(id)
                .map(user -> modelMapper.map(user, UserProfileView.class))
                .orElseThrow(null);
    }

    public void initializeRolesAndUsers(){
        initializeRoles();
        initializeUsers();
    }
    public void initializeRoles() {
        if (this.userRoleRepository.count() == 0) {
            UserRoleEntity adminRole = new UserRoleEntity();
            adminRole.setUserRole(UserRoleEnum.ADMIN);

            UserRoleEntity userRole = new UserRoleEntity();
            userRole.setUserRole(UserRoleEnum.USER);

            this.userRoleRepository.saveAll(List.of(adminRole, userRole));
        }
    }

    public void initializeUsers() {
        if (this.userRepository.count() == 0) {
            UserEntity admin = new UserEntity();
            admin.setFirstName("Admin");
            admin.setLastName("Adminov");
            admin.setEmail("admin@mail.com");
            admin.setActive(true);
            admin.setImageUrl(null);
            admin.setPassword(this.passwordEncoder.encode("12345"));

            admin.setUserRoles(List.of(
                    this.userRoleRepository.findByUserRole(UserRoleEnum.ADMIN),
                    this.userRoleRepository.findByUserRole(UserRoleEnum.USER))
            );
            this.userRepository.save(admin);

            UserEntity user = new UserEntity();
            user.setFirstName("Tihomir");
            user.setLastName("Makariev");
            user.setEmail("tihomir@mail.com");
            user.setActive(true);
            user.setImageUrl(null);
            user.setPassword(this.passwordEncoder.encode("12345"));

            user.setUserRoles(List.of(
                    this.userRoleRepository.findByUserRole(UserRoleEnum.USER))
            );
            this.userRepository.save(user);
        }
    }

}


