package myrres.foodOrganizer.jpa.entity;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import myrres.foodOrganizer.rest.api.RecipeCategory;

import java.util.Date;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "recipe")
public class Recipe {

    @Id
    @GeneratedValue
    private Long id;

    @Column(name = "name")
    private String name; //name of the recipe
    @Column(name = "description")
    private String description; //description of the recipe
    @Column(name = "ingredients")
    private String ingredients; //ingredients of the recipe
    @Column(name = "date")
    private Date date; //date of the recipe creation
    @Enumerated(EnumType.ORDINAL)
    private RecipeCategory category; //category of the recipe
    @Column(name = "published")
    private boolean published; //if the recipe is published or not
    @Column(name = "likes")
    private Integer likes; //number of likes of the recipe
    @Column(name = "id_user")
    private Long userId; //id of the user who created the recipe
    private String image; //image of the recipe

}
