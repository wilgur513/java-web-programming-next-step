package once.ch7.dao;

import once.ch7.ConnectionManager;
import once.ch7.model.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class UserDao {
    public void insert(User user) throws SQLException {
        JdbcTemplate template = new JdbcTemplate() {
            @Override
            public void setValues(PreparedStatement pstmt) throws SQLException {
                pstmt.setString(1, user.getUserId());
                pstmt.setString(2, user.getPassword());
                pstmt.setString(3, user.getName());
                pstmt.setString(4, user.getEmail());
            }
        };

        template.update("INSERT INTO USERS VALUES (?, ?, ?, ?)");
    }

    public void update(User user) throws SQLException {
        JdbcTemplate template = new JdbcTemplate() {
            @Override
            public void setValues(PreparedStatement pstmt) throws SQLException {
                pstmt.setString(1, user.getUserId());
                pstmt.setString(2, user.getPassword());
                pstmt.setString(3, user.getName());
                pstmt.setString(4, user.getEmail());
                pstmt.setString(5, user.getUserId());
            }
        };

        template.update("update USERS set userId=?, password=?, name=?, email=? where userId=?");
    }

    public List<User> findAll() throws SQLException {
        SelectJdbcTemplate template = new SelectJdbcTemplate(){
            @Override
            public void setValues(PreparedStatement pstmt) {

            }

            @Override
            public Object mapRow(ResultSet rs) throws SQLException {
                return new User(rs.getString("userId"), rs.getString("password"),
                        rs.getString("name"), rs.getString("email"));
            }
        };

        return template.query("select * from USERS");
    }


    public User findByUserId(String userId) throws SQLException {
        SelectJdbcTemplate template = new SelectJdbcTemplate() {
            @Override
            public void setValues(PreparedStatement pstmt) throws SQLException {
                pstmt.setString(1, userId);
            }

            @Override
            public Object mapRow(ResultSet rs) throws SQLException {
                return new User(rs.getString("userId"), rs.getString("password"),
                        rs.getString("name"), rs.getString("email"));
            }
        };

        return (User) template.queryForObject("SELECT userId, password, name, email FROM USERS WHERE userid=?");
    }

}
