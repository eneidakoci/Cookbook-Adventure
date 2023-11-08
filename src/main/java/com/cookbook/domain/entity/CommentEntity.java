package com.cookbook.domain.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "comment")
public class CommentEntity extends BaseEntity{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer commentId;

    @Column(name = "comment_text", columnDefinition = "TEXT")
    private String commentText;

    @ManyToOne
    @JoinColumn(name = "member_id")
    private MemberEntity memberEntity;

    @ManyToOne
    @JoinColumn(name = "recipe_id")
    private RecipeEntity recipeEntity;

}
