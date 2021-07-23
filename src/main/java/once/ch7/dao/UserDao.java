package once.ch7.dao;

import once.ch7.model.User;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class UserDao {
    public void insert(User user) {
        JdbcTemplate template = new JdbcTemplate();

        template.update("INSERT INTO USERS VALUES (?, ?, ?, ?)",
                new PreparedStatementSetter() {
                    @Override
                    public void setValues(PreparedStatement pstmt) throws SQLException {
                        pstmt.setString(1, user.getUserId());
                        pstmt.setString(2, user.getPassword());
                        pstmt.setString(3, user.getName());
                        pstmt.setString(4, user.getEmail());
                    }
                });
    }

    public void update(User user) {
        JdbcTemplate template = new JdbcTemplate();

        template.update("update USERS set userId=?, password=?, name=?, email=? where userId=?",
                new PreparedStatementSetter() {
                    @Override
                    public void setValues(PreparedStatement pstmt) throws SQLException {
                        pstmt.setString(1, user.getUserId());
                        pstmt.setString(2, user.getPassword());
                        pstmt.setString(3, user.getName());
                        pstmt.setString(4, user.getEmail());
                        pstmt.setString(5, user.getUserId());
                    }
                });
    }

    public List<User> findAll() {
        PreparedStatementSetter setter = new PreparedStatementSetter() {
            @Override
            public void setValues(PreparedStatement pstmt) throws SQLException {

            }
        };

        RowMapper mapper = new RowMapper() {
            @Override
            public Object mapRow(ResultSet rs) throws SQLException {
                return new User(rs.getString("userId"), rs.getString("password"),
                        rs.getString("name"), rs.getString("email"));
            }
        };

        return new JdbcTemplate().query("select * from USERS", setter, mapper);
    }


    public User findByUserId(String userId) {
        PreparedStatementSetter setter = new PreparedStatementSetter() {
            @Override
            public void setValues(PreparedStatement pstmt) throws SQLException {
                pstmt.setString(1, userId);
            }
        };

        RowMapper mapper = new RowMapper() {
            @Override
            public Object mapRow(ResultSet rs) throws SQLException {
                return new User(rs.getString("userId"), rs.getString("password"),
                        rs.getString("name"), rs.getString("email"));
            }
        };

        JdbcTemplate template = new JdbcTemplate();

        return (User) template.queryForObject("SELECT userId, password, name, email FROM USERS WHERE userid=?",
                setter, mapper);
    }

}
