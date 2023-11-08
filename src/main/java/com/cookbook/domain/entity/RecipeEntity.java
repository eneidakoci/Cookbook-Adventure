package com.cookbook.domain.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity(name = "recipe")
public class RecipeEntity extends BaseEntity{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer recipeId;

    @ManyToMany
    @JoinTable(
            name = "recipe_category",
            joinColumns = @JoinColumn(name = "recipe_id"),
            inverseJoinColumns = @JoinColumn(name = "category_id")
    )
    private Set<CategoryEntity> categories = new HashSet<>();

    @Column(name = "recipe_name", length = 255, nullable = false)
    private String recipeName;

    @Column(name = "ingredients", length = 255, nullable = false)
    private String ingredients;

    @Column(name = "description", columnDefinition = "TEXT")
    private String description;

    @Column(name = "likes")
    private Integer likes;
}
