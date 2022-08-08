package com.github.jakub686.recipiesSpring;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.NamedQuery;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "recipe")
@NamedQuery(name = "Recipe.findByCategory", query = "select u from Recipe u where lower(u.category) = ?1 order by u.date desc")
@NamedQuery(name = "Recipe.findByName", query = "select u from Recipe u where lower(u.name) LIKE '%' || ?1 || '%' order by u.date desc")


public class Recipe {
    @JsonIgnore
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @Column
    @NotBlank
    private String name;
    @Column
    @NotBlank
    private String category;
    @Column
    private LocalDateTime date;
    @Column
    @NotBlank
    private String description;
    @Column
    @NotNull
    @Size(min = 1)
    private String[] ingredients;
    @Column
    @NotNull
    @Size(min = 1)
    private String[] directions;
}