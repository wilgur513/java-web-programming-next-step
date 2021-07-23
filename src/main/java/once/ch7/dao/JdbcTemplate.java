package once.ch7.dao;

import once.ch7.ConnectionManager;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class JdbcTemplate {
    public void update(final String sql, final PreparedStatementSetter setter) {
        try(Connection con = ConnectionManager.getConnection();
            PreparedStatement pstmt = con.prepareStatement(sql)) {
            setter.setValues(pstmt);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            throw new DataAccessException();
        }
    }

    public List query(final String sql, final PreparedStatementSetter setter, final RowMapper rowMapper) {
        ResultSet rs = null;

        try (Connection con = ConnectionManager.getConnection();
            PreparedStatement pstmt = con.prepareStatement(sql);){
            List result = new ArrayList();
            setter.setValues(pstmt);
            rs = pstmt.executeQuery();

            while(rs.next()) {
                result.add(rowMapper.mapRow(rs));
            }

            return result;
        } catch (SQLException e){
          throw new DataAccessException();
        } finally {
            if (rs != null) {
                try {
                    rs.close();
                } catch (SQLException e) {
                    throw new DataAccessException();
                }
            }
        }
    }

    public Object queryForObject(final String sql, final PreparedStatementSetter setter, final RowMapper rowMapper) {
        ResultSet rs = null;
        try (Connection con = ConnectionManager.getConnection();
            PreparedStatement pstmt = con.prepareStatement(sql)){
            setter.setValues(pstmt);
            rs = pstmt.executeQuery();
            Object result = null;
            if (rs.next()) {
                result = rowMapper.mapRow(rs);
            }
            return result;
        } catch (SQLException e){
            throw new DataAccessException();
        } finally {
            if (rs != null) {
                try {
                    rs.close();
                } catch (SQLException e) {
                    throw new DataAccessException();
                }
            }
        }
    }
}
