package com.pluralsight.dataManagers;

import com.pluralsight.dataManagers.interfaces.DatabaseManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;

@Component
public class DatabaseManagerImpl implements DatabaseManager {
    DataSource dataSource;

    @Autowired
    public DatabaseManagerImpl(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    @Override
    public void createTable() {

    }

    @Override
    public void deleteTable() {

    }

    @Override
    public Connection getConnection() throws SQLException {
        return dataSource.getConnection();
    }
}
