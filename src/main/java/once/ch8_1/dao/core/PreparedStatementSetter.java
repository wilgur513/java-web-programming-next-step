package once.ch8_1.dao.core;

import java.sql.PreparedStatement;
import java.sql.SQLException;

public interface PreparedStatementSetter {
    void setValues(PreparedStatement pstmt) throws SQLException;
}
