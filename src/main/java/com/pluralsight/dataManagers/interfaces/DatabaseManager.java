package com.pluralsight.dataManagers.interfaces;

import java.sql.Connection;
import java.sql.SQLException;

public interface DatabaseManager {
    void createTable();
    void deleteTable();
    Connection getConnection() throws SQLException;
}
