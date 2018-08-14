package c211.client;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import c211.db.*;
import c211.serial.*;

public class UserTools {
  /**
   * 获取串口信息
   * @param xh
   * @return
   */
  public static List<SerialPorts> getportnameByxuhao(int xh) {
    List<SerialPorts> list = new ArrayList<SerialPorts>();
    try {
      DBHelper db = new DBHelper();
      String sql = "select * from serialports where xuhao='" + xh + "'";
      ResultSet res = db.Search(sql, null);
      while(res.next()) {
        int xuhao = res.getInt(1);
        String portname = res.getString(2);
        String baudrate = res.getString(3);
        String databits = res.getString(4);
        String stopbits = res.getString(5);
        String parity = res.getString(6);
        list.add(new SerialPorts(xuhao, portname, baudrate, databits, stopbits, parity)); 
      }
    } catch(Exception e) {
      e.printStackTrace();
    }
    return list;
  }
  /**
   * 获取全部用户
   * @param name
   * @return
   */
  public User getUserByName(String name) {
    User usr = new User();
    try {
      DBHelper db = new DBHelper();
      String sql = "select * from [dbo].[user] where user_name='" + name + "'";
      ResultSet res = db.Search(sql, null);
      while(res.next()) {
        usr.setUserid(res.getInt(1));
        usr.setUsername(res.getString(2));
        usr.setPassword(res.getString(3));
        usr.setLimit(res.getInt(4));
      }
    } catch(Exception e) {
      e.printStackTrace();
    }
    return usr;
  }
  /**
   * 更新用户密码
   * @param user_name
   * @param password
   * @return
   * @throws Exception
   */
  public int updateUser(String user_name, String password) throws Exception {
    String sql = "update [dbo].[user] set password='" + password + "' where user_name='" + user_name + "' ";
    return new DBHelper().AddU(sql, null);
  }
  
}