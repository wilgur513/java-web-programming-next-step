package once.ch7.dao;

import once.ch7.ConnectionManager;
import once.ch7.model.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public abstract class SelectJdbcTemplate {
    public List query() throws SQLException {
        Connection con = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;

        try {
            List result = new ArrayList();
            con = ConnectionManager.getConnection();
            pstmt = con.prepareStatement(createQuery());
            rs = pstmt.executeQuery();

            while(rs.next()) {
                result.add(mapRow(rs));
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

    public Object queryForObject(String userId) throws SQLException {
        Connection con = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        try {
            con = ConnectionManager.getConnection();
            pstmt = con.prepareStatement(createQuery());

            setValues(userId, pstmt);

            rs = pstmt.executeQuery();

            Object result = null;
            if (rs.next()) {
                result = mapRow(rs);
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

    public abstract String createQuery();
    public abstract void setValues(String userId, PreparedStatement pstmt) throws SQLException;
    public abstract Object mapRow(ResultSet rs) throws SQLException;
}
