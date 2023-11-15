package com.cookbook.domain.dto;

import com.cookbook.domain.entity.Role;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Objects;
import java.util.Set;
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class UserDTO {
    public Integer userId;
    private String username;
    private Set<Role> authorities;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        UserDTO userDTO = (UserDTO) o;
        return Objects.equals(userId, userDTO.userId) && Objects.equals(username, userDTO.username) && Objects.equals(authorities, userDTO.authorities);
    }

    @Override
    public int hashCode() {
        return Objects.hash(userId, username, authorities);
    }
}
