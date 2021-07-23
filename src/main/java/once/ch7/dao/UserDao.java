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
        Connection con = null;
        PreparedStatement pstmt = null;
        
        try {
            con = ConnectionManager.getConnection();
            pstmt = con.prepareStatement(createQueryForInsert());
            setValuesForInsert(user, pstmt);
            pstmt.executeUpdate();
        } finally {
            if (pstmt != null) {
                pstmt.close();
            }

            if (con != null) {
                con.close();
            }
        }
    }

    private String createQueryForInsert() {
        return "INSERT INTO USERS VALUES (?, ?, ?, ?)";
    }

    private void setValuesForInsert(User user, PreparedStatement pstmt) throws SQLException {
        pstmt.setString(1, user.getUserId());
        pstmt.setString(2, user.getPassword());
        pstmt.setString(3, user.getName());
        pstmt.setString(4, user.getEmail());
    }

    public void update(User user) throws SQLException {
        Connection con = null;
        PreparedStatement pstmt = null;

        try {
            con = ConnectionManager.getConnection();
            pstmt = con.prepareStatement(createQueryForUpdate());
            setValuesForUpdate(user, pstmt);
            pstmt.executeUpdate();
        } finally {
            if (pstmt != null) {
                pstmt.close();
            }

            if (con != null) {
                con.close();
            }
        }
    }

    private String createQueryForUpdate() {
        return "update USERS set userId=?, password=?, name=?, email=? where userId=?";
    }

    private void setValuesForUpdate(User user, PreparedStatement pstmt) throws SQLException {
        pstmt.setString(1, user.getUserId());
        pstmt.setString(2, user.getPassword());
        pstmt.setString(3, user.getName());
        pstmt.setString(4, user.getEmail());
        pstmt.setString(5, user.getUserId());
    }

    public List<User> findAll() throws SQLException {
        List<User> result = new ArrayList<>();
        Connection con = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;

        try {
            con = ConnectionManager.getConnection();
            pstmt = con.prepareStatement("select * from USERS");
            rs = pstmt.executeQuery();

            while(rs.next()) {
                result.add(
                    new User(rs.getString("userId"), rs.getString("password"),
                            rs.getString("name"), rs.getString("email")));
            }

        } finally {
            rs.close();
            pstmt.close();
            con.close();
        }

        return result;
    }

    public User findByUserId(String userId) throws SQLException {
        Connection con = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        try {
            con = ConnectionManager.getConnection();
            String sql = "SELECT userId, password, name, email FROM USERS WHERE userid=?";
            pstmt = con.prepareStatement(sql);
            pstmt.setString(1, userId);

            rs = pstmt.executeQuery();

            User user = null;
            if (rs.next()) {
                user = new User(rs.getString("userId"), rs.getString("password"), rs.getString("name"),
                        rs.getString("email"));
            }

            return user;
        } finally {
            if (rs != null) {
                rs.close();
            }
            if (pstmt != null) {
                pstmt.close();
            }
            if (con != null) {
                con.close();
            }
        }
    }
}
