package com.opgave.aflevering.repositories;

import com.opgave.aflevering.models.User;
import org.springframework.stereotype.Repository;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

@Repository
public class UserRepository {
    private final Connection connection = DBManager.getConnection();

    public void addNewUser(User user) {
        String query = "insert into users(username, password) values (?, ?)";
        PreparedStatement preparedStatement;
        try {
            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, user.getUsername());
            preparedStatement.setString(2, user.getPassword());
            preparedStatement.executeUpdate();
        } catch (Exception ignore) {
        }
    }

    public int getInt(String query) {
        ResultSet resultSet = getResultSet(query);

        try {
            resultSet.next();
            return resultSet.getInt(1);

        } catch(SQLException e){
            e.printStackTrace();
        } finally {
            try {
                resultSet.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return 0;
    }

    private ResultSet getResultSet(String query) {
        Connection connection = DBManager.getConnection();
        PreparedStatement preparedStatement;
        ResultSet resultSet = null;

        try {
            preparedStatement = connection.prepareStatement(query);
            resultSet = preparedStatement.executeQuery();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return resultSet;
    }

    public boolean isPresent(String username) {
        String query = "SELECT EXISTS(SELECT 1 FROM users WHERE username = '" + username + "')";
        return getInt(query) == 1;
    }

    public boolean correctDetails(User user) {
        String query = "SELECT EXISTS(SELECT 1 FROM users WHERE username = '"
                + user.getUsername() + "' AND password = '"
                + user.getPassword() + "')";
        return getInt(query) == 1;
    }
}
