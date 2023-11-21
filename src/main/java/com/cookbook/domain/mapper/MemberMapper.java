package com.cookbook.domain.mapper;

import com.cookbook.domain.dto.MemberDTO;
import com.cookbook.domain.dto.MemberRequest;
import com.cookbook.domain.entity.MemberEntity;

public class MemberMapper {
    public static MemberDTO memberEntityToDto(MemberEntity entity) {
        if ( entity == null ) {
            return null;
        }

        MemberDTO memberDTO = new MemberDTO();

        memberDTO.setCreatedDate( entity.getCreatedDate() );
        memberDTO.setLastModified( entity.getLastModified() );
        memberDTO.setDeleted( entity.isDeleted() );
        memberDTO.setMemberId( entity.getMemberId() );
        memberDTO.setName( entity.getName() );
        memberDTO.setLastName( entity.getLastName() );
        memberDTO.setEmail( entity.getEmail() );
        memberDTO.setMemberId(entity.getMemberId());

        return memberDTO;
    }

    public static MemberEntity memberDtoToEntity(MemberDTO dto) {
        if ( dto == null ) {
            return null;
        }

        MemberEntity memberEntity = new MemberEntity();

        memberEntity.setCreatedDate( dto.getCreatedDate() );
        memberEntity.setLastModified( dto.getLastModified() );
        memberEntity.setDeleted( dto.isDeleted() );
        memberEntity.setMemberId( dto.getMemberId() );
        memberEntity.setName( dto.getName() );
        memberEntity.setLastName( dto.getLastName() );
        memberEntity.setEmail( dto.getEmail() );
        memberEntity.setMemberId(dto.getMemberId());

        return memberEntity;
    }

    public static MemberEntity memberRequestToEntity(MemberRequest memberRequest) {
        if ( memberRequest == null ) {
            return null;
        }

        MemberEntity memberEntity = new MemberEntity();

        memberEntity.setCreatedDate( memberRequest.getCreatedDate() );
        memberEntity.setLastModified( memberRequest.getLastModified() );
        memberEntity.setDeleted( memberRequest.isDeleted() );
        memberEntity.setName( memberRequest.getName() );
        memberEntity.setLastName( memberRequest.getLastName() );
        memberEntity.setEmail( memberRequest.getEmail() );

        return memberEntity;
    }

}