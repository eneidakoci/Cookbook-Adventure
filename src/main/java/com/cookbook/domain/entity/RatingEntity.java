package com.cookbook.domain.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.Check;

import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "rating")
public class RatingEntity extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer ratingId;

    @ManyToOne
    @JoinColumn(name = "member_id")
    private MemberEntity memberEntity;

    @ManyToOne
    @JoinColumn(name = "recipe_id")
    private RecipeEntity recipeEntity;

    @Check(constraints = "rate >= 1 AND rate <= 5")
    @Column(name = "rate", nullable = false)
    private int rate;

}
