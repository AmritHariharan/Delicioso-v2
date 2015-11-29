package com.spacewheel.deliciosov20;

import java.util.Comparator;

/**
 * Created by Amrit on 16/9/15.
 */
public class RecipeComparator implements Comparator<Recipe> {

    @Override
    public int compare(Recipe lhs, Recipe rhs) {
        return lhs.getRecipeTitle().compareTo(rhs.getRecipeTitle());
    }
}
