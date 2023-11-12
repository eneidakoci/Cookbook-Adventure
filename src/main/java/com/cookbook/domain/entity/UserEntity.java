//package com.cookbook.domain.entity;
//
//import com.cookbook.domain.enums.Role;
//import jakarta.persistence.*;
//import lombok.AllArgsConstructor;
//import lombok.Getter;
//import lombok.NoArgsConstructor;
//import lombok.Setter;
//import org.springframework.security.core.GrantedAuthority;
//import org.springframework.security.core.authority.SimpleGrantedAuthority;
//import org.springframework.security.core.userdetails.UserDetails;
//
//import java.util.Arrays;
//import java.util.Collection;
//import java.util.stream.Collectors;
//
//@Getter
//@Setter
//@Entity
//@AllArgsConstructor
//@NoArgsConstructor
//@Table(name = "user")
//public class UserEntity implements UserDetails {
//    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    public Integer userId;
//    @Column(unique = true)
//    private String username;
//    private String password;
//    @Enumerated(EnumType.STRING)
//    private Role role;
//    @OneToOne(mappedBy = "userEntity")
//    private MemberEntity memberEntity;
//
//    @Override
//    public Collection<? extends GrantedAuthority> getAuthorities() {
//        return Arrays.stream(roles.split(","))
//                .map(SimpleGrantedAuthority::new)
//                .collect(Collectors.toList());
//    }
//
//    private String[] newGrantedAuthority() {
//        switch (role) {
//            case ADMIN:
//                return new String[]{"ROLE_ADMIN", "ROLE_USER"};
//            case USER:
//                return new String[]{"ROLE_USER"};
//            case UNAUTHORIZED:
//                return new String[]{};
//            default:
//                return new String[]{};
//        }
//    }
//    @Override
//    public String getPassword() {
//        return password;
//    }
//
//    @Override
//    public String getUsername() {
//        return username;
//    }
//
//    @Override
//    public boolean isAccountNonExpired() {
//        return true;
//    }
//
//    @Override
//    public boolean isAccountNonLocked() {
//        return true;
//    }
//
//    @Override
//    public boolean isCredentialsNonExpired() {
//        return true;
//    }
//
//    @Override
//    public boolean isEnabled() {
//        return true;
//    }
//}
