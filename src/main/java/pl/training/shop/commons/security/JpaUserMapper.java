package pl.training.shop.commons.security;

import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface JpaUserMapper {

    User toDomain(UserEntity userEntity);

}
