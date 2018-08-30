package c211.test;

import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Vector;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

import Automation.BDaq.ByteByRef;
import Automation.BDaq.DaqException;
import Automation.BDaq.DeviceInformation;
import Automation.BDaq.DeviceTreeNode;
import Automation.BDaq.DioPort;
import Automation.BDaq.ErrorCode;
import Automation.BDaq.InstantDiCtrl;
import Automation.BDaq.InstantDoCtrl;
import c211.client.DataView;
import c211.client.ViewResult;
import c211.db.ECTESTSYSTools;
import c211.db.Recorddata;
import c211.serial.SerialPortTool;
import c211.serialException.InputStreamCloseFail;
import c211.serialException.NoSuchPort;
import c211.serialException.NotASerialPort;
import c211.serialException.OutputStreamCloseFail;
import c211.serialException.PortInUse;
import c211.serialException.ReadDataFromSerialFail;
import c211.serialException.SendToPortFail;
import c211.serialException.SerialPortParamFail;
import gnu.io.SerialPort;

public class MethodTest {
  
  public static InstantDoCtrl instantDoCtrl;
  public static InstantDiCtrl instantDiCtrl;
  private int portCount;
  private byte[] portData;
  private static byte pciState = 0x00;

  static SerialPortTool portTool = SerialPortTool.getSerialPortTool();

  public static void main(String[] args) throws SerialPortParamFail, NotASerialPort, NoSuchPort, PortInUse {
    /*List<Recorddata> list = ECTESTSYSTools.getRecordtdData("2018-08-27", "C211");
    System.out.println(list.size());
    
    Vector<String> vector = ViewResult.addRowDatas("2018-08-27");
    System.out.println(vector.size());
    
    byte[] ch = {0x02,0x30,0x33,0x31};
    String str = SerialPortTool.bytesToHex(ch);
    System.out.println(str);
    System.out.println(ch[2]);
    System.out.println(DataView.isEquals(ch[0], "02"));
    System.out.println(DataView.isEquals(ch[1], "30"));
    System.out.println(DataView.isEquals(ch[2], "33"));
    
    char c = byteAsciiToChar(02);
    System.out.println(c);*/
    /*StringBuilder strBuilder = new StringBuilder();
    strBuilder.append("01 10 00 01 00 08 10 00 60 00 80 00 76 00 67 00 81 00 68 00 66 00 62 10 B9");
    // strBuilder.delete(0, strBuilder.length());
    String builStr = strBuilder.toString();
    System.out.println(builStr);
    byte[] builByte = toByteArray(builStr);

    for (byte b : builByte) {
      System.out.print(b + " ");
    }
    System.out.println();
    System.out.println(bytesToHex(builByte).toUpperCase());*/
    
    

    /*ArrayList<String> port = SerialPortTool.findPort();
    for (Iterator<String> i = port.iterator(); i.hasNext();) {
      String s = i.next();
      System.out.println("可用串口：" + s);
    }
    // SerialPort COM1 = SerialPortTool.getPort(1);
    SerialPort COM1 = SerialPortTool.getPort("COM1", 9600, 8, 1, 0);
    System.out.println("COM1波特率：" + COM1.getBaudRate());
    System.out.println("COM1校验位：" + COM1.getParity());
    byte[] bytes = { 0x3A, 0x66, 0x75, 0x6E, 0x63, 0x74, 0x69, 0x6F, 0x6E, 0x3A, 0x76, 0x6F, 0x6C, 0x74, 0x61, 0x67,
        0x65, 0x3A, 0x44, 0x43, 0x0D, 0x0A, 0x3A, 0x6D, 0x65, 0x61, 0x73, 0x75, 0x72, 0x65, 0x3A, 0x76, 0x6F, 0x6C,
        0x74, 0x61, 0x67, 0x65, 0x3A, 0x44, 0x43, 0x3F, 0x0D, 0x0A, 0x3A, 0x63, 0x61, 0x6C, 0x63, 0x75, 0x6C, 0x61,
        0x74, 0x65, 0x3A, 0x66, 0x75, 0x6E, 0x63, 0x74, 0x69, 0x6F, 0x6E, 0x20, 0x6E, 0x6F, 0x6E, 0x65 };
    System.out.println(new String(bytes));
    try {
      SerialPortTool.write(COM1, bytes);
    } catch (SendToPortFail e) {
      e.printStackTrace();
    } catch (OutputStreamCloseFail e) {
      e.printStackTrace();
    }
    //System.out.println(SerialPortTool.read(COM1));
    SerialPortTool.closePort(COM1);
    //char[] ch = {0xFF, 0x80, 0x60, 0xF3};
    String str = "01 10 00 01 00 08 10 00 60 00 80 00 76 00 67 00 81 00 68 00 66 00 62 10 B9";
    byte[] strByte = toByteArray(str);
    for(byte b: strByte) {
      String s = String.format("%02x", b);
      System.out.print(s + " ");
      //System.out.print(Integer.toHexString(b) + " ");
    }
    System.out.println();
    System.out.println(bytesToHex(strByte));
    System.out.println(bytesToHex(bytes));
    //String s1 = String.format("%1$tY-%1$tm-%1$te", new Date());
    //System.out.println(s1);
    byte[] ch = {0x01, 0x10, 0x00, 0x01, 0x00, 0x08, 0x10, 0x00, 0x60, 0x00, (byte) 0x80, 0x00, 0x76,
        0x00, 0x67, 0x00, (byte) 0x81, 0x00, 0x68, 0x00, 0x66, 0x00, 0x62, 0x10, (byte) 0xB9};
    for(byte b: ch) {
      String s = String.format("%02x", b);
      //System.out.print(Byte.parseByte(s) + " ");
      System.out.print(s + " ");
    }
    System.out.println();*/
    //System.out.println(Byte.parseByte(String.format("%02x", "b9")));
    /*SerialPort COM4 = SerialPortTool.getPort("COM4", 19200, 8, 1, 2);
    try {
      SerialPortTool.write(COM4, ch);
      Thread.sleep(500);
      byte[] comBytes = SerialPortTool.readByte(COM4);
      //System.out.print(String.copyValueOf(comBytes));
      for(byte b: ch) {
        String s = String.format("%x", b);
        System.out.print(s + " ");
      }
    } catch (SendToPortFail e) {
      e.printStackTrace();
    } catch (OutputStreamCloseFail e) {
      e.printStackTrace();
    } catch (ReadDataFromSerialFail e) {
      e.printStackTrace();
    } catch (InputStreamCloseFail e) {
      e.printStackTrace();
    } catch (InterruptedException e) {
      e.printStackTrace();
    }
    SerialPortTool.closePort(COM4);
    */
    instantDoCtrl = new InstantDoCtrl();
    ArrayList<DeviceTreeNode> installedDevice = instantDoCtrl.getSupportedDevices();
    /*for(DeviceTreeNode de: installedDevice) {
      System.out.println(de);
    }*/
    DeviceTreeNode treeNode = installedDevice.get(0);
    try {
      instantDoCtrl.setSelectedDevice(new DeviceInformation(treeNode.toString()));
    } catch (DaqException e) {
      e.printStackTrace();
    }
    System.out.println(treeNode);
    byte b = 0x01;
    //for(int i = 0; i < 10; i++)
    System.out.println(instantDoCtrl.Write(0, (byte)0));
    byte by = 0;
    ByteByRef ref = new ByteByRef(by);
    System.out.println(instantDoCtrl.Read(0, ref));
    System.out.println(ref.value);
    System.out.println(b == 0x01);
    System.out.println(ErrorCode.ErrorFuncNotInited);//The function is not initialized and can't be started.
    System.out.println(ErrorCode.ErrorFuncNotSpted);//The required function is not supported.
    System.out.println(ErrorCode.Success);//The operation is completed successfully.
    
  }
  /**
   * 通过PCI向PLC发送数据
   * 
   * @param channel
   * @param state
   */
  public void sendMesToPLCByPCI(int channel, byte state) {
    ErrorCode err = ErrorCode.Success;
    err = instantDoCtrl.Write(channel, state);
    //instantDiCtrl.;
    if (!err.equals(ErrorCode.Success)) {
      handleError(err);
    }
  }
  
  public void sendMesToPLCByPCI(int channel, byte[] state) {
    ErrorCode err = ErrorCode.Success;
    int portCount = instantDoCtrl.getPortCount();
    err = instantDoCtrl.Write(channel, portCount ,state);
    //instantDiCtrl.;
    if (!err.equals(ErrorCode.Success)) {
      handleError(err);
    }
  }

  /**
   * 错误信息处理
   * 
   * @param err
   *          ErrorCode
   */
  public void handleError(ErrorCode err) {
    if (err != ErrorCode.Success) {
      System.out.println("Error:"+err.toString());
      //JOptionPane.showMessageDialog(null, "PCI出错了：" + err.toString());
    }
  }
  /**
   * 发送不良报警给PLC
   */
  public void PLCWarmming() {
    //pciState = (byte) (pciState | 0x01);
    byte[] bytes = {(byte) (pciState | 0x01)};
    sendMesToPLCByPCI(0, bytes);
  }

  /**
   * 取消报警
   */
  public void cancelWarmming() {
    pciState = (byte) (pciState & 0xFE);
    sendMesToPLCByPCI(0, pciState);
  }

  /**
   * 绝缘测试复位
   */
  public void jueyuanTestReset() {
    pciState = (byte) ((pciState | 0x04) & 0xFD);
    sendMesToPLCByPCI(0, pciState);
  }
  
  
  
  public static char byteAsciiToChar(int ascii){
    char ch = (char)ascii;
    return ch;
  }

  /**
   * 16进制的字符串表示转成字节数组
   * 
   * @param hexString
   *          16进制格式的字符串
   * @return 转换后的字节数组
   **/
  public static byte[] toByteArray(String hexString) {

    hexString = hexString.replaceAll(" ", ""); // 去掉字符串中所有空格
    hexString = hexString.toLowerCase();
    int len = 0;
    if(hexString.length() % 2 == 0) {
      len = hexString.length() / 2;
    }
    else {
      len = hexString.length() /2 + 1;
    }
    byte[] byteArray = new byte[len];
    int k = 0;
    for (int i = 0; i < byteArray.length; i++) {// 因为是16进制，最多只会占用4位，转换成字节需要两个16进制的字符，高位在先
      byte high = (byte) (Character.digit(hexString.charAt(k), 16) & 0xff);
      byte low = (byte) (Character.digit(hexString.charAt(k + 1), 16) & 0xff);
      byteArray[i] = (byte)(high << 4 | low);
      //String str = Integer.toHexString((high << 4 | low)).format("0x" + "[0-9A-Fa-f]{2}", byteArray);
      //byteArray[i] = Byte.parseByte(str);
      k += 2;
    }

    return byteArray;
  }

  public static String bytesToHex(byte[] bytes) {
    StringBuffer sb = new StringBuffer();
    for (int i = 0; i < bytes.length; i++) {
      String hex = Integer.toHexString(bytes[i] & 0xFF);
      if (hex.length() < 2) {
        sb.append(0);
      }
      sb.append(hex);
      sb.append(' ');
    }
    return sb.toString();
  }

}
