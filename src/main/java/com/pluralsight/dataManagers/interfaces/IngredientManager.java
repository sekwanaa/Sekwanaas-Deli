package com.pluralsight.dataManagers.interfaces;

import com.pluralsight.models.Ingredient;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public interface IngredientManager {
    void addIngredient(Ingredient ingredient);

    List<Ingredient> getAllIngredients();

    Connection getConnection() throws SQLException;
}
