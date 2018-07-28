package c211.db;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

public class RecordOneDayTools {
  
  Connection connection = null;
  Statement stmt = null;
  PreparedStatement pstmt = null;
  ResultSet res = null;

  public int truncateRecordoneday()
  {
    int result = 0;
    String sql = "truncate table recordtdoneday";
    try {
      connection = DBHelper.getConnection();
      stmt = connection.createStatement();
      result = stmt.executeUpdate(sql);
    } catch(Exception e) {
      e.printStackTrace();
    }
    return result;
  }
  
  
}