package bg.tihomir.mebeli.model.mapper;

import bg.tihomir.mebeli.model.dto.binding.UserRegisterDTO;
import bg.tihomir.mebeli.model.entity.UserEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface UserMapper {

    @Mapping(target = "active", constant = "true")
    UserEntity userDtoToUserEntity(UserRegisterDTO userRegisterDTO);

}
