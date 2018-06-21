package org.launchcode.models;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.List;

/**
 * Created by LaunchCode
 */
@Entity //flag that tells SpringBoot you want to store this in a database and it should wore thing up to do that
public class Cheese {


    //these two annotations below together say that this should
    // be an id field to create primary key column in a data field it will generate for us
    @Id
    @GeneratedValue
    private int id;

    @NotNull
    @Size(min=3, max=15)
    private String name;

    @NotNull
    @Size(min=1, message = "Description must not be empty")
    private String description;


    @ManyToOne
    private Category category;



    //In order to tell Hibernate how to store and populate objects from the list, we specify that the field should be mappedBy the cheeses field of the Menu class.
    @ManyToMany(mappedBy = "cheeses")
    private List<Menu> menus;

    public Cheese(String name, String description) {
        this.name = name;
        this.description = description;
    }

    public Cheese() { }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }


    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }


}
