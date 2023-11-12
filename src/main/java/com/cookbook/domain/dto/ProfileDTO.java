//package com.cookbook.domain.dto;
//import com.cookbook.domain.entity.MemberEntity;
//import lombok.AllArgsConstructor;
//import lombok.Getter;
//import lombok.NoArgsConstructor;
//import lombok.Setter;
//
//import java.time.LocalDateTime;
//import java.util.Objects;
//
//@Getter
//@Setter
//@AllArgsConstructor
//@NoArgsConstructor
//public class ProfileDTO extends BaseDomainDTO{
//    private Integer profileId;
//    private String profileLink;
//    private MemberEntity memberEntity;
//
//    public ProfileDTO(LocalDateTime createdDate, LocalDateTime lastModified, boolean deleted, Integer profileId, String profileLink, MemberEntity memberEntity) {
//        super(createdDate, lastModified, deleted);
//        this.profileId = profileId;
//        this.profileLink = profileLink;
//        this.memberEntity = memberEntity;
//    }
//
//    @Override
//    public boolean equals(Object o) {
//        if (this == o) return true;
//        if (o == null || getClass() != o.getClass()) return false;
//        ProfileDTO that = (ProfileDTO) o;
//        return Objects.equals(profileId, that.profileId) && Objects.equals(profileLink, that.profileLink) && Objects.equals(memberEntity, that.memberEntity);
//    }
//
//    @Override
//    public int hashCode() {
//        return Objects.hash(profileId, profileLink, memberEntity);
//    }
//}
