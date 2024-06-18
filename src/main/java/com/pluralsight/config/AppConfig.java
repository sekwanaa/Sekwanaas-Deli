package com.pluralsight.config;

import com.pluralsight.dataManagers.IngredientManagerImpl;
import com.pluralsight.dataManagers.interfaces.DatabaseManager;
import com.pluralsight.dataManagers.DatabaseManagerImpl;
import com.pluralsight.dataManagers.interfaces.IngredientManager;
import org.apache.commons.dbcp2.BasicDataSource;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;
import java.sql.SQLException;

@Configuration
public class AppConfig {

    @Bean
    public DataSource dataSource (@Value("${datasource.username}") String username,
                           @Value("${datasource.password}") String password,
                           @Value("${datasource.url}") String url) {
        try (BasicDataSource dataSource = new BasicDataSource()){
            dataSource.setUrl(url);
            dataSource.setUsername(username);
            dataSource.setPassword(password);

            return dataSource;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

    @Bean
    public DatabaseManager databaseManager(DataSource dataSource) {
        return new DatabaseManagerImpl(dataSource);
    }

    @Bean
    public IngredientManager ingredientManager(DataSource dataSource) {
        return new IngredientManagerImpl(dataSource);
    }
}
