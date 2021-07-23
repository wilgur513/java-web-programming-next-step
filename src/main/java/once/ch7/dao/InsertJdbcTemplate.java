package once.ch7.dao;

import once.ch7.ConnectionManager;
import once.ch7.model.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public abstract class InsertJdbcTemplate {
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

    abstract public String createQueryForInsert();
    abstract public void setValuesForInsert(User user, PreparedStatement pstmt) throws SQLException;
}
