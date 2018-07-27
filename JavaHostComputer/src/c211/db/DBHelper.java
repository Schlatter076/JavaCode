package c211.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.sql.SQLException;

/**
 * 数据库帮助类
 * @author Loyer
 * @coding: utf8
 */
public class DBHelper {
  //数据库连接字符串
  private final static String url = "jdbc:sqlserver://localhost:1433;databaseName=ectestsys";
  private final static String username = "sa";
  private final static String password = "SQLserver0";
  /**
   * 提供数据库连接方法
   * @return getConnectionCommon()
   * @throws Exception
   */
  public static Connection getConnection() throws Exception {
    return getConnectionCommon();
  }
  /**
   * 定义数据库连接方法
   * @return connection
   * @throws Exception
   */
  public static Connection getConnectionCommon() throws Exception {
    Connection connection = null;  //连接对象
    try {
      //加载驱动
      Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
      connection = DriverManager.getConnection(url, username, password);
    } catch(ClassNotFoundException e) {
      e.printStackTrace();
      throw new Exception();
    } catch(SQLException e) {
      e.printStackTrace();
      throw new Exception();
    }
    return connection;
  }
  /**
   * 数据库关闭连接方法
   * @param res
   * @param stmt
   * @param connection
   */
  public static void close(ResultSet res, Statement stmt, Connection connection) {
    if(res != null) {
      try {
        res.close();
      } catch(SQLException e) {
        e.printStackTrace();
      }
    }
    if(stmt != null) {
      try {
        stmt.close();
      } catch(SQLException e) {
        e.printStackTrace();
      }
    }
    if(connection != null) {
      try {
        connection.close();
      } catch(SQLException e) {
        e.printStackTrace();
      }
    }
  }
}
