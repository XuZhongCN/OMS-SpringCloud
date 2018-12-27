package com.cpscs.omsadmin.dto.do2dto;


import org.mapstruct.factory.Mappers;

import com.cpscs.omsadmin.domain.UserDO;
import com.cpscs.omsadmin.dto.UserDTO;

import java.util.List;

@org.mapstruct.Mapper
public interface UserConvert {
    UserConvert MAPPER = Mappers.getMapper(UserConvert.class);

    public UserDTO do2dto(UserDO person);

    public List<UserDTO> dos2dtos(List<UserDO> list);
}
