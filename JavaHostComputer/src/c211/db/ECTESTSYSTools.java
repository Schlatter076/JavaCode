package c211.db;

import java.sql.Connection;
import java.sql.Statement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import java.util.List;
import java.util.ArrayList;

public class ECTESTSYSTools {
  Connection connection = null;
  Statement stmt = null;
  PreparedStatement pstmt = null;
  ResultSet res = null;
  /**
   * 提供获取testsystem表全部内容
   * @return
   */
  public static List<ECTESTSYStestdata> getAllbyDb() {
    List<ECTESTSYStestdata> list = new ArrayList<ECTESTSYStestdata>();
    try {
      DBHelper db = new DBHelper();
      String sql = "select * from testsystem";
      ResultSet res = db.Search(sql, null);
      while(res.next()) {
        String buzhou = res.getString(1);
        String buzhouname = res.getString(2);
        String testdata = res.getString(3);
        String min = res.getString(4);
        String max = res.getString(5);
        String result = res.getString(6);
        String condition = res.getString(7);
        String date = res.getString(8);
        
        list.add(new ECTESTSYStestdata(buzhou, buzhouname, testdata, min, max, result, condition, date)); 
      }
    } catch(Exception e) {
      e.printStackTrace();
    }
    return list;
  }
  /**
   * 删除testsystem表中指定的步骤
   * @param sql
   * @return
   * @throws Exception
   */
  public static int deleteBetteryData(String Bd) throws Exception {
    String sql = "delete from testsystem where buzhou = '" + Bd + "'";
    return new DBHelper().AddU(sql, null);
  }
  /**
   * 删除testsystem表中指定的步骤和日期
   * @param Bd1
   * @param Bd2
   * @return
   * @throws Exception
   */
  public static int deleteTwoBetteryData(String Bd1, String Bd2) throws Exception {
    String sql = "delete from testsystem where buzhou = '" + Bd1 + "'" + "and date='" + Bd2 + "'";
    return new DBHelper().AddU(sql, null);
  }
  /**
   * 删除testsystem表中指定的日期和Bd1与Bd2之间的步骤
   * @param Bdata
   * @param Bd1
   * @param Bd2
   * @return
   * @throws Exception
   */
  public static int deleteTwoBetteryData01(String Bdata, String Bd1, String Bd2) throws Exception {
    String sql = "delete from testsystem where date = '" + Bdata + "'" + "and buzhou Between'" + Bd1 + "'" + "and'" + Bd2 + "'";
    return new DBHelper().AddU(sql, null);
  }
  /**
   * 删除testsystem表中Bd1与Bd2之间的步骤
   * @param Bd1
   * @param Bd2
   * @return
   * @throws Exception
   */
  public static int AreaDeleteBetteryData(String Bd1, String Bd2) throws Exception {
    String sql = "delete from testsystem where buzhou Between'" + Bd1 + "'" + "and'" + Bd2 + "'";
    return new DBHelper().AddU(sql, null);
  }
  
  public static List<Recorddata> getRecordtdData() {
    List<Recorddata> list = new ArrayList<Recorddata>();
    try {
      DBHelper db = new DBHelper();
      String sql = "select * from recordtd";
      ResultSet res = db.Search(sql, null);
      while(res.next()) {
        String recordname = res.getString(1);
        String recordsum = res.getString(2);
        String recordok = res.getString(3);
        String recordng = res.getString(4);
        String recordts = res.getString(5);
        String recordtime = res.getString(6);
        
        list.add(new Recorddata(recordname, recordsum, recordok, recordng, recordts, recordtime)); 
      }
    } catch(Exception e) {
      e.printStackTrace();
    }
    return list;    
  }
  /**
   * 获取recordtd表中指定recordtime和recordname列
   * @param rtime
   * @param rname
   * @return
   */
  public static List<Recorddata> getRecordtdData(String rtime, String rname) {
    List<Recorddata> list = new ArrayList<Recorddata>();
    try {
      DBHelper db = new DBHelper();
      String sql = "select * from recordtd where recordtime='" + rtime + "' and recordname='" + rname + "'";
      ResultSet res = db.Search(sql, null);
      while(res.next()) {
        String recordname = res.getString(1);
        String recordsum = res.getString(2);
        String recordok = res.getString(3);
        String recordng = res.getString(4);
        String recordts = res.getString(5);
        String recordtime = res.getString(6);
        
        list.add(new Recorddata(recordname, recordsum, recordok, recordng, recordts, recordtime)); 
      }
    } catch(Exception e) {
      e.printStackTrace();
    }
    return list;    
  }
  /**
   * 删除recordtd表中指定的recordtime和recordname
   * @param recordtime
   * @param recordname
   * @return
   * @throws Exception
   */
  public static int deleterecord(String recordtime, String recordname) throws Exception {
    String sql = "delete  from recordtd where recordtime='" + recordtime + "' and recordname='" + recordname + "'";
    return new DBHelper().AddU(sql, null);
  }
  
  /**
   * 提供recordtd表插入方法
   * @param sql
   * @param str
   * @return
   * @throws Exception
   */
  public static int addrecord(String str[]) throws Exception{
    String sql = "insert into recordtd(recordname,recordsum,recordok,recordng,recordts,recordtime) values(?, ?, ?, ?, ?, ?)";
    return new DBHelper().AddU(sql, str);
  }
  /**
   * 提供producttypes表中插入productname方法
   * @param productname
   * @return
   * @throws Exception
   */
  public static int addProductsName(String productname) throws Exception {
    String sql = "insert into producttypes(productname) values('" + productname + "')";
    return new DBHelper().AddU(sql, null);
  }
  /**
   * 从producttypes表中删除productname为ductsName的数据
   * @param ductsName
   * @return
   * @throws Exception
   */
  public static int deleteProductsName(String ductsName) throws Exception {
    String sql = "delete from producttypes where productname='" + ductsName + "'";
    return new DBHelper().AddU(sql, null);
  }
  /**
   * 获取表C211中所有数据
   * @param productsName
   * @return
   */
  public static List<Producttestitemsdata> getproductsTestItems(String productsName) {
    List<Producttestitemsdata> list = new ArrayList<Producttestitemsdata>();
    try {
      DBHelper db = new DBHelper();
      String sql = "select * from " + productsName;
      ResultSet res = db.Search(sql, null);
      while(res.next()) {
        String pdxuhao = res.getString(1);
        String testitem = res.getString(2);
        String maxvalue = res.getString(3);
        String minvalue = res.getString(4);
        String testvalue = res.getString(5);
        String danwei = res.getString(6);
        String testresult = res.getString(7);
        
        list.add(new Producttestitemsdata(pdxuhao, testitem, maxvalue, minvalue, testvalue, danwei, testresult)); 
      }
    } catch(Exception e) {
      e.printStackTrace();
    }
    return list;    
  }
  /**
   * 获取recordtd表中所有数据
   * @param recordtdName
   * @return
   */
  public static List<Recorddata> getrecordtdItems(String recordtdName) {
    List<Recorddata> list = new ArrayList<Recorddata>();
    try {
      DBHelper db = new DBHelper();
      String sql = "select * from " + recordtdName;
      ResultSet res = db.Search(sql, null);
      while(res.next()) {
        String recordname = res.getString(1);
        String recordsum = res.getString(2);
        String recordok = res.getString(3);
        String recordng = res.getString(4);
        String recordts = res.getString(5);
        String recordtime = res.getString(6);
        
        list.add(new Recorddata(recordname, recordsum, recordok, recordng, recordts, recordtime)); 
      }
    } catch(Exception e) {
      e.printStackTrace();
    }
    return list;    
  }
  
}