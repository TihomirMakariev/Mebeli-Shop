package bg.tihomir.mebeli.service;


import bg.tihomir.mebeli.model.entity.UserEntity;
import bg.tihomir.mebeli.model.entity.UserRoleEntity;
import bg.tihomir.mebeli.model.user.MebeliUserDetails;
import bg.tihomir.mebeli.repository.UserRepository;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

public class MebeliUserDetailsService implements UserDetailsService {

    private final UserRepository userRepository;

    public MebeliUserDetailsService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username)
            throws UsernameNotFoundException {
        return userRepository
                .findByEmail(username)
                .map(this::map)
                .orElseThrow();
    }


    private UserDetails map(UserEntity userEntity) {
        return new MebeliUserDetails(
                userEntity.getId(),
                userEntity.getFirstName(),
                userEntity.getLastName(),
                userEntity.getEmail(),
                userEntity.getPassword(),
                userEntity
                        .getUserRoles()
                        .stream().map(this::map)
                        .toList()
        );
    }

    private GrantedAuthority map(UserRoleEntity userRole) {
        return new SimpleGrantedAuthority("ROLE_" +
                userRole.getUserRole().name());
    }


}
