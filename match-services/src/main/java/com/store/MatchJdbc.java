package com.store;

import com.model.User;
import com.model.UserBuilder;
import com.typesafe.config.Config;

import java.sql.*;


/**
 * Database operations we need:
 * 0. initDatabase? - run sql script to create database
 * 1. getUser - accesses users, userhobbies databases
 * 2. createUser - accesses users, userhobbies databases Fill out all rows (account for required and nonrequired registration fields)
 * 3. userLogin - accesses users, userhobbies, friends databases.
 * Others we might need: photoUpload
 */

public class MatchJdbc implements UserStore {

    private static final String GET_USER_STATEMENT =
            "SELECT * FROM users WHERE username = ?";

    private final Config config;

    public MatchJdbc(final Config config) {
        this.config = config;
    }

    // getUser - Finds all info of a user with the specified username in the DB. If there is no user, returns null.
    @Override
    public User getUser(final String username) {
        Connection connection;
        try {
            connection =
                    DriverManager.getConnection(
                            config.getString("mysql.jdbc"),
                            config.getString("mysql.user"),
                            config.getString("mysql.password"));

            PreparedStatement preparedStatement = connection.prepareStatement(GET_USER_STATEMENT);
            preparedStatement.setString(1, username);

            ResultSet resultSet = preparedStatement.executeQuery();

            if (resultSet.first()) {
                return new UserBuilder()
                        .username(resultSet.getString("username"))
                        .password(resultSet.getString("userpassword"))
                        .displayName(resultSet.getString("userdisplayname"))
                        //.aboutMe(resultSet.getString("aboutMe"))
                        .maxTravelDistance(resultSet.getInt("usermaxtraveldistance"))
                        .longitude(resultSet.getDouble("userlongitude"))
                        .latitude(resultSet.getDouble("userlatitude"))
                        .build();
            } else {
                return null;
            }
        } catch (SQLException e) {
            throw new RuntimeException("error fetching user", e);
        }
    }

    @Override
    public Boolean createUser(User newUser) {
        Connection connection = null;
        PreparedStatement statement;
        String sqlinsert = null;
        try {
            statement = connection.prepareStatement("SELECT username FROM users WHERE username = ?");
            statement.setString(1,newUser.username());

            statement.executeQuery();
        }
        catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
        try {
            connection = DriverManager.getConnection(
                config.getString("mysql.jdbc"),
                config.getString("mysql.user"),
                config.getString("mysql.password"));

            sqlinsert = "INSERT INTO users (userid, username, userdisplayname, userpassword, userhobbylist, usermaxtraveldistance,userlatitude, userlongitude)" + "VALUES (?,?,?,?,?,?,?,?)";

            PreparedStatement psinsert = connection.prepareStatement(sqlinsert, Statement.RETURN_GENERATED_KEYS);
            psinsert.setString(2,newUser.username());
            psinsert.setString(3,newUser.displayName());
            psinsert.setString(4,newUser.password());
            psinsert.setInt(6,newUser.maxTravelDistance());
            psinsert.setDouble(7,newUser.latitude());
            psinsert.setDouble(8,newUser.longitude());
        }
       catch (SQLException e) {
           e.printStackTrace();
       }
                             
        try{
            statement.execute(sqlinsert);
            return true;
         }
        catch (SQLException se) {
            se.printStackTrace();
            return false;
        }
    }
}