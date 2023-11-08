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
    private String profileLink;

    public MemberRequest(String username, String profileLink){
        this.name = name;
        this.profileLink = profileLink;
    }
}
