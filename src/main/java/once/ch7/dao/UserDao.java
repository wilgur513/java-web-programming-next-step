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
        JdbcTemplate template = new JdbcTemplate("INSERT INTO USERS VALUES (?, ?, ?, ?)") {
            @Override
            public void setValues(PreparedStatement pstmt) throws SQLException {
                pstmt.setString(1, user.getUserId());
                pstmt.setString(2, user.getPassword());
                pstmt.setString(3, user.getName());
                pstmt.setString(4, user.getEmail());
            }
        };

        template.update();
    }

    public void update(User user) throws SQLException {
        JdbcTemplate template = new JdbcTemplate("update USERS set userId=?, password=?, name=?, email=? where userId=?") {
            @Override
            public void setValues(PreparedStatement pstmt) throws SQLException {
                pstmt.setString(1, user.getUserId());
                pstmt.setString(2, user.getPassword());
                pstmt.setString(3, user.getName());
                pstmt.setString(4, user.getEmail());
                pstmt.setString(5, user.getUserId());
            }
        };

        template.update();
    }

    public List<User> findAll() throws SQLException {
        return new SelectJdbcTemplate().query(this);
    }

    String createQueryForList() {
        return "select * from USERS";
    }

    User mapRow(ResultSet rs) throws SQLException {
        User user = new User(rs.getString("userId"), rs.getString("password"),
                            rs.getString("name"), rs.getString("email"));
        return user;
    }

    public User findByUserId(String userId) throws SQLException {
        return (User) new SelectJdbcTemplate().queryForObject(userId, this);
    }

    String createQueryForObject() {
        return "SELECT userId, password, name, email FROM USERS WHERE userid=?";
    }

    void setValuesForQuery(String userId, PreparedStatement pstmt) throws SQLException {
        pstmt.setString(1, userId);
    }
}
