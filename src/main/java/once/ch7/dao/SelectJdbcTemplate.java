package once.ch7.dao;

import once.ch7.ConnectionManager;
import once.ch7.model.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class SelectJdbcTemplate {
    public List query(UserDao userDao) throws SQLException {
        Connection con = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;

        try {
            List<User> result = new ArrayList<>();
            con = ConnectionManager.getConnection();
            pstmt = con.prepareStatement(userDao.createQueryForList());
            rs = pstmt.executeQuery();

            while(rs.next()) {
                result.add(userDao.mapRow(rs));
            }

            return result;
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

    public Object queryForObject(String userId, UserDao userDao) throws SQLException {
        Connection con = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        try {
            con = ConnectionManager.getConnection();
            pstmt = con.prepareStatement(userDao.createQueryForObject());

            userDao.setValuesForQuery(userId, pstmt);

            rs = pstmt.executeQuery();

            User user = null;
            if (rs.next()) {
                user = userDao.mapRow(rs);
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
