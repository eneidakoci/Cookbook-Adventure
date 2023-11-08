package com.cookbook.domain.mapper.impl;

import com.cookbook.domain.dto.MemberDTO;
import com.cookbook.domain.dto.MemberRequest;
import com.cookbook.domain.dto.ProfileDTO;
import com.cookbook.domain.dto.ProfileRequest;
import com.cookbook.domain.entity.MemberEntity;
import com.cookbook.domain.entity.ProfileEntity;
import com.cookbook.domain.mapper.MemberMapper;

public class MemberMapperImpl implements MemberMapper {
    @Override
    public MemberDTO memberEntityToDto(MemberEntity entity) {
        if (entity == null) {
            return null;
        }
        MemberDTO memberDTO = new MemberDTO();
        memberDTO.setDeleted(entity.isDeleted());
        memberDTO.setCreatedDate(entity.getCreatedDate());
        memberDTO.setLastModified(entity.getLastModified());
        memberDTO.setEmail(entity.getEmail());
        memberDTO.setLastName(entity.getLastName());
        memberDTO.setProfile(entity.getProfile());
        memberDTO.setMemberId(entity.getMemberId());
        return memberDTO;
    }

    @Override
    public MemberEntity memberDtoToEntity(MemberDTO dto) {
        if (dto == null) {
            return null;
        }
        MemberEntity memberEntity = new MemberEntity();
        memberEntity.setDeleted(dto.isDeleted());
        memberEntity.setCreatedDate(dto.getCreatedDate());
        memberEntity.setLastModified(dto.getLastModified());
        memberEntity.setEmail(dto.getEmail());
        memberEntity.setLastName(dto.getLastName());
        memberEntity.setProfile(dto.getProfile());
        memberEntity.setMemberId(dto.getMemberId());
        return memberEntity;
    }

    @Override
    public MemberEntity memberRequestToEntity(MemberRequest memberRequest) {
        MemberEntity member = new MemberEntity();
        member.setDeleted(memberRequest.isDeleted());
        member.setCreatedDate(memberRequest.getCreatedDate());
        member.setLastModified(memberRequest.getLastModified());
        member.setEmail(memberRequest.getEmail());
        member.setLastName(memberRequest.getLastName());
        member.setName(memberRequest.getName());
        return member;
    }


}
