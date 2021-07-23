package once.ch7.dao;

import once.ch7.model.User;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class UserDao {
    public void insert(User user) {
        new JdbcTemplate().update("INSERT INTO USERS VALUES (?, ?, ?, ?)", user.getUserId(), user.getPassword(), user.getName(), user.getEmail());
    }

    public void update(User user) {
        new JdbcTemplate().update("update USERS set userId=?, password=?, name=?, email=? where userId=?",
                user.getUserId(), user.getPassword(), user.getName(), user.getEmail(), user.getUserId());
    }

    public List<User> findAll() {
        return new JdbcTemplate().query("select * from USERS", (rs) -> createUser(rs));
    }

    public User findByUserId(String userId) {
        return new JdbcTemplate().queryForObject("SELECT userId, password, name, email FROM USERS WHERE userid=?",
                (rs) -> createUser(rs), userId);
    }

    private User createUser(ResultSet rs) throws SQLException {
        return new User(rs.getString("userId"), rs.getString("password"), rs.getString("name"), rs.getString("email"));
    }

}
