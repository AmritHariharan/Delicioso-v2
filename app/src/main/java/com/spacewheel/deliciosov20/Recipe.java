package com.spacewheel.deliciosov20;

import java.util.List;

/**
 * Created by Amrit on 16/9/15.
 */
public class Recipe {

    boolean hasImage = false;
    String recipeTitle = null;
    String recipeDescription = null;

    String ingredients = null;
    String method = null;
    String notes = null;
    byte[] imageId;
    String parentBook = null;

    public Recipe(String recipeTitle, String recipeDescription, String ingredients, String method, String notes, byte[] imageId, String parentBook ) {
        this.recipeTitle = recipeTitle;
        this.recipeDescription = recipeDescription;
        this.ingredients = ingredients;
        this.method = method;
        this.notes = notes;
        this.imageId = imageId;
        this.parentBook = parentBook;
        hasImage = true;
    }

    public Recipe(String recipeTitle, String recipeDescription, String ingredients, String method, String notes, String parentBook ) {
        this.recipeTitle = recipeTitle;
        this.recipeDescription = recipeDescription;
        this.ingredients = ingredients;
        this.method = method;
        this.notes = notes;
        this.parentBook = parentBook;
        hasImage = false;
    }

    public void addImage(byte[] image) {
        imageId = image;
    }

    public Recipe() {

    }

    public String getIngredients() {
        return ingredients;
    }

    public void setIngredients(String ingredients) {
        this.ingredients = ingredients;
    }

    public String getMethod() {
        return method;
    }

    public void setMethod(String method) {
        this.method = method;
    }

    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }

    public String getRecipeDescription() {
        return recipeDescription;
    }

    public void setRecipeDescription(String recipeDescription) {
        this.recipeDescription = recipeDescription;
    }

    public String getRecipeTitle() {
        return recipeTitle;
    }

    public void setRecipeTitle(String recipeTitle) {
        this.recipeTitle = recipeTitle;
    }

    public byte[] getImageId() {
        return imageId;
    }

    public void setImageId(byte[] imageId) {
        this.imageId = imageId;
    }

    public String getParentBook() {
        return parentBook;
    }

    public void setParentBook(String parentBook) {
        this.parentBook = parentBook;
    }
}
