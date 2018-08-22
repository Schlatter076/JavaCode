package c211.db;

import java.io.File;
import java.sql.Connection;
import java.sql.Statement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import java.util.List;

import jxl.Workbook;
import jxl.write.Label;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;

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
  
  public static void recordtdDataOutToExcel() {
    try {
      WritableWorkbook wwb = null;
      //创建可写入的Excel工作簿
      String fileName = "log/testData.xls";
      File file = new File(fileName);
      if(!file.exists()) {
        file.createNewFile();
      }
      //以fileName为文件名来创建一个Workbook
      wwb = Workbook.createWorkbook(file);
      
      //创建工作表
      WritableSheet ws = wwb.createSheet("testData", 0);
      
      //查询数据库中所有的数据
      List<Recorddata> list = getRecordtdData();
      //要插入到的excl表格的行号，默认从0开始
      Label labelRecordname = new Label(0, 0, "产品名称");
      Label labelRecordsum = new Label(1, 0, "测试总数");
      Label labelRecordok = new Label(2, 0, "良品数");
      Label labelRecordng = new Label(3, 0, "不良数");
      Label labelRecordts = new Label(4, 0, "测试时长/S");
      Label labelRecordtime = new Label(5, 0, "日期");
      ws.addCell(labelRecordname);
      ws.addCell(labelRecordsum);
      ws.addCell(labelRecordok);
      ws.addCell(labelRecordng);
      ws.addCell(labelRecordts);
      ws.addCell(labelRecordtime);
      for(int i = 0; i < list.size(); i++) {
        Label labelRecordname_i = new Label(0, i+1, list.get(i).getRecordname() + "");
        Label labelRecordsum_i = new Label(1, i+1, list.get(i).getRecordsum());
        Label labelRecordok_i = new Label(2, i+1, list.get(i).getRecordok());
        Label labelRecordng_i = new Label(3, i+1, list.get(i).getRecordng());
        Label labelRecordts_i = new Label(4, i+1, list.get(i).getRecordts());
        Label labelRecordtime_i = new Label(5, i+1, list.get(i).getRecordtime());
        
        ws.addCell(labelRecordname_i);
        ws.addCell(labelRecordsum_i);
        ws.addCell(labelRecordok_i);
        ws.addCell(labelRecordng_i);
        ws.addCell(labelRecordts_i);
        ws.addCell(labelRecordtime_i);
      }
      //写进文档
      wwb.write();
      //关闭Excel工作簿对象
      wwb.close();
    } catch(Exception e) {
      e.printStackTrace();
    }
  }
  
}