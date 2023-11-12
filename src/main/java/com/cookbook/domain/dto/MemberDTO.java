package com.cookbook.domain.dto;

//import com.cookbook.domain.entity.ProfileEntity;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.Objects;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class MemberDTO extends BaseDomainDTO{
    public Integer memberId;
    private String name;
    private String lastName;
    private String email;
    //private ProfileEntity profile;

    public MemberDTO(LocalDateTime createdDate, LocalDateTime lastModified, boolean deleted, Integer memberId, String name, String lastName, String email /*ProfileEntity profile*/) {
        super(createdDate, lastModified, deleted);
        this.memberId = memberId;
        this.name = name;
        this.lastName = lastName;
        this.email = email;
        //this.profile = profile;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        MemberDTO memberDTO = (MemberDTO) o;
        return Objects.equals(memberId, memberDTO.memberId) && Objects.equals(name, memberDTO.name) && Objects.equals(lastName, memberDTO.lastName) && Objects.equals(email, memberDTO.email);
    }

    @Override
    public int hashCode() {
        return Objects.hash(memberId, name, lastName, email);
    }


}
