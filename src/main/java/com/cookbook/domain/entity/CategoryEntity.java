package com.cookbook.domain.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "category")
public class CategoryEntity extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer categoryId;
    @Column(nullable = false)
    private String name;

    @ManyToMany(mappedBy = "categories")
    private List<RecipeEntity> recipeEntities = new ArrayList<>();

    public CategoryEntity(String name) {
        this.name = name;
    }
}
