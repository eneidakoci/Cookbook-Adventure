package com.cookbook.domain.dto;

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
   // private ProfileEntity profileEntity;

    public MemberRequest(String username){
        this.name = name;
       // this.profileEntity = profileEntity;
    }
}
