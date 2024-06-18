package com.pluralsight.dataManagers;

import com.pluralsight.dataManagers.interfaces.IngredientManager;
import com.pluralsight.models.Ingredient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

@Component
public class IngredientManagerImpl implements IngredientManager {
    private final DataSource dataSource;

    @Autowired
    public IngredientManagerImpl(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    @Override
    public void addIngredient(Ingredient ingredient) {

    }

    @Override
    public List<Ingredient> getAllIngredients() {
        return List.of();
    }

    @Override
    public Connection getConnection() throws SQLException {
        return dataSource.getConnection();
    }
}
