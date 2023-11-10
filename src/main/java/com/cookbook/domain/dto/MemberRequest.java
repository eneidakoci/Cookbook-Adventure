package com.cookbook.domain.dto;

import com.cookbook.domain.entity.ProfileEntity;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class MemberRequest extends BaseDomainRequest{
    private String name;
    private String lastName;
    private String email;
    private ProfileEntity profileEntity;

    public MemberRequest(String username, ProfileEntity profileEntity){
        this.name = name;
        this.profileEntity = profileEntity;
    }
}
