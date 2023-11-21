package com.cookbook.domain.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/*spring project me 2 endpointe. njeri required qe useri te jete i loguar, ndersa tjt qe te jete admin.*/
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "member")
public class MemberEntity extends BaseEntity{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Integer memberId;

    @Column(nullable = false)
    private String name;
    @Column(nullable = false)
    private String lastName;
    @Column(unique = true)
    private String email;

    @OneToOne
    @JoinColumn(name="user_id", referencedColumnName = "userId")
    private UserEntity userEntity;
}