package com.github.vlshat.example;

import org.springframework.beans.factory.annotation.Autowired;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UserDAO {

    private DataSource dataSource;

    public UserDAO(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    public boolean isEmailExist(String email) {

        boolean isExist = false;

        try (Connection connection  = dataSource.getConnection()) {
            final PreparedStatement select = connection.prepareStatement("SELECT EXISTS (SELECT * FROM user WHERE email=?)");
            select.setString(1, email);
            select.executeQuery();
            final ResultSet set = select.getResultSet();
            isExist = set.next();

        } catch (SQLException e) {

        }

        return isExist;
    }

}
