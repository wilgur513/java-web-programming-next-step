package once.ch8.dao.core;

import once.ch8.ConnectionManager;

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

    public void update(final String sql, Object... values) {
        try(Connection con = ConnectionManager.getConnection();
            PreparedStatement pstmt = con.prepareStatement(sql)) {
            createPreparedStatementSetter(values).setValues(pstmt);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            throw new DataAccessException();
        }
    }

    public <T> List<T> query(final String sql, final PreparedStatementSetter setter, final RowMapper<T> rowMapper) {
        ResultSet rs = null;

        try (Connection con = ConnectionManager.getConnection();
            PreparedStatement pstmt = con.prepareStatement(sql);){
            List<T> result = new ArrayList<>();
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

    public <T> List<T> query(final String sql, final RowMapper<T> rowMapper, final Object... values) {
        ResultSet rs = null;

        try (Connection con = ConnectionManager.getConnection();
             PreparedStatement pstmt = con.prepareStatement(sql);){
            List<T> result = new ArrayList<>();
            createPreparedStatementSetter(values).setValues(pstmt);
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

    public <T> T queryForObject(final String sql, final PreparedStatementSetter setter, final RowMapper<T> rowMapper) {
        ResultSet rs = null;
        try (Connection con = ConnectionManager.getConnection();
            PreparedStatement pstmt = con.prepareStatement(sql)){
            setter.setValues(pstmt);
            rs = pstmt.executeQuery();
            T result = null;
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

    public <T> T queryForObject(final String sql, final RowMapper<T> rowMapper, final Object... values) {
        ResultSet rs = null;
        try (Connection con = ConnectionManager.getConnection();
             PreparedStatement pstmt = con.prepareStatement(sql)){
            createPreparedStatementSetter(values).setValues(pstmt);
            rs = pstmt.executeQuery();
            T result = null;
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

    private PreparedStatementSetter createPreparedStatementSetter(Object... values) {
        return new PreparedStatementSetter() {
            @Override
            public void setValues(PreparedStatement pstmt) throws SQLException {
                for(int i = 0; i < values.length; i++) {
                    pstmt.setObject(i + 1, values[i]);
                }
            }
        };
    }
}
