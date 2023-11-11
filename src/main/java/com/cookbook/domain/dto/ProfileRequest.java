package com.cookbook.domain.dto;

import com.cookbook.domain.entity.MemberEntity;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ProfileRequest extends BaseDomainRequest{
    private String profileLink;
    private MemberEntity memberEntity;
    private Integer profileId;
}
