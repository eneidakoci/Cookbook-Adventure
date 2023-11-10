package com.cookbook.domain.mapper;

import com.cookbook.domain.dto.MemberDTO;
import com.cookbook.domain.dto.MemberRequest;
import com.cookbook.domain.entity.MemberEntity;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
public interface MemberMapper {
    MemberMapper INSTANCE = Mappers.getMapper(MemberMapper.class);

    MemberDTO memberEntityToDto(MemberEntity entity);
    MemberEntity memberDtoToEntity(MemberDTO dto);
    MemberEntity memberRequestToEntity(MemberRequest memberRequest);
}
