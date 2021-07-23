package once.ch7.dao;

import once.ch7.model.User;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class UserDao {
    public void insert(User user) {
        JdbcTemplate template = new JdbcTemplate();
        template.update("INSERT INTO USERS VALUES (?, ?, ?, ?)", user.getUserId(), user.getPassword(), user.getName(), user.getEmail());
    }

    public void update(User user) {
        JdbcTemplate template = new JdbcTemplate();

        template.update("update USERS set userId=?, password=?, name=?, email=? where userId=?",
                user.getUserId(), user.getPassword(), user.getName(), user.getEmail(), user.getUserId());
    }

    public List<User> findAll() {
        RowMapper<User> mapper = new RowMapper<User>() {
            @Override
            public User mapRow(ResultSet rs) throws SQLException {
                return new User(rs.getString("userId"), rs.getString("password"),
                        rs.getString("name"), rs.getString("email"));
            }
        };

        return new JdbcTemplate().query("select * from USERS", mapper);
    }


    public User findByUserId(String userId) {
        RowMapper<User> mapper = new RowMapper<User>() {
            @Override
            public User mapRow(ResultSet rs) throws SQLException {
                return new User(rs.getString("userId"), rs.getString("password"),
                        rs.getString("name"), rs.getString("email"));
            }
        };

        JdbcTemplate template = new JdbcTemplate();

        return template.queryForObject("SELECT userId, password, name, email FROM USERS WHERE userid=?",
                mapper, userId);
    }

}
