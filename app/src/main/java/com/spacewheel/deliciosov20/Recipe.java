package com.spacewheel.deliciosov20;

import java.util.List;

/**
 * Created by Amrit on 16/9/15.
 */
public class Recipe {

    String recipeTitle;
    String recipeDescription;

    List<String> ingredients;
    List<String> ingredientQuantities;

    List<String> method;

    List<String> notes;

    public Recipe(List<String> ingredientQuantities, List<String> ingredients, List<String> method, List<String> notes, String recipeDescription, String recipeTitle) {
        this.ingredientQuantities = ingredientQuantities;
        this.ingredients = ingredients;
        this.method = method;
        this.notes = notes;
        this.recipeDescription = recipeDescription;
        this.recipeTitle = recipeTitle;
    }

    public Recipe() {

    }

    public List<String> getIngredientQuantities() {
        return ingredientQuantities;
    }

    public void setIngredientQuantities(List<String> ingredientQuantities) {
        this.ingredientQuantities = ingredientQuantities;
    }

    public List<String> getIngredients() {
        return ingredients;
    }

    public void setIngredients(List<String> ingredients) {
        this.ingredients = ingredients;
    }

    public List<String> getMethod() {
        return method;
    }

    public void setMethod(List<String> method) {
        this.method = method;
    }

    public List<String> getNotes() {
        return notes;
    }

    public void setNotes(List<String> notes) {
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
}
