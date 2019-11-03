package com.beerhouse.model;

import javax.persistence.*;

/**
 * The Beer model
 * @author Guilherme Rodrigues Bueno
 */
@Entity
@Table(name="Beer")
public class Beer {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "ingredients", nullable = false)
    private String ingredients;

    @Column(name = "alcohol_content", nullable = false)
    private String alcoholContent;

    @Column(name = "price", nullable = false)
    private Number price;

    @Column(name = "category", nullable = false)
    private String category;

    public Beer() {
    }

    public Beer(Integer id, String name, String ingredients, String alcoholContent, Number price, String category) {
        this.id = id;
        this.name = name;
        this.ingredients = ingredients;
        this.alcoholContent = alcoholContent;
        this.price = price;
        this.category = category;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getIngredients() {
        return ingredients;
    }

    public void setIngredients(String ingredients) {
        this.ingredients = ingredients;
    }

    public String getAlcoholContent() {
        return alcoholContent;
    }

    public void setAlcoholContent(String alcoholContent) {
        this.alcoholContent = alcoholContent;
    }

    public Number getPrice() {
        return price;
    }

    public void setPrice(Number price) {
        this.price = price;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }
}
