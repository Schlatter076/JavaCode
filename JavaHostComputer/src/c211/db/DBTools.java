package c211.db;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;

/**
 * 数据库工具类
 * @author Loyer
 * @coding: utf8
 */
public class DBTools {
  Connection connection = null;
  PreparedStatement pstmt = null;
  ResultSet res = null;
  /**
   * 提供testsystem表插入方法
   * @param buzhou
   * @param buzhouname
   * @param testdata
   * @param min
   * @param max
   * @param result
   * @param condition
   * @param date
   * @return boolean
   */
  public boolean testsystemInsert(String buzhou, String buzhouname, String testdata, String min, String max, String result, String condition, String date) {
    
    int getBack = 0;
    String sql = "insert into testsystem values(?, ?, ?, ?, ?, ?, ?, ?)";
    
    try {
      connection = DBHelper.getConnection();
      pstmt = connection.prepareStatement(sql);
      
      pstmt.setString(1, buzhou);
      pstmt.setString(2, buzhouname);
      pstmt.setString(3, testdata);
      pstmt.setString(4, min);
      pstmt.setString(5, max);
      pstmt.setString(6, result);
      pstmt.setString(7, condition);
      pstmt.setString(8, date);
      
      getBack = pstmt.executeUpdate(); 
    } catch(SQLException e) {
      e.printStackTrace();
      return false;
      
    } catch(Exception e) {
      e.printStackTrace();
      return false;
      
    } finally {
      DBHelper.close(res, pstmt, connection);
    }
    
    return true;  
  }
  
  public static void main(String[] args) {
    DBTools dbt = new DBTools();
    for(int i = 1; i <= 15; i++) {
      if(i <= 9)
        dbt.testsystemInsert("00003-0" + i, null, null, null, null, null, null, "2018-07-27");
      else
        dbt.testsystemInsert("00003-" + i, null, null, null, null, null, null, "2018-07-27");
    }
  }
  
}