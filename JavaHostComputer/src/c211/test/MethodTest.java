package c211.test;

import java.util.ArrayList;
import java.util.Iterator;
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

  static SerialPortTool portTool = SerialPortTool.getSerialPortTool();

  public static void main(String[] args) throws SerialPortParamFail, NotASerialPort, NoSuchPort, PortInUse {
    StringBuilder strBuilder = new StringBuilder();
    strBuilder.append("01 10 00 01 00 08 10 00 60 00 80 00 76 00 67 00 81 00 68 00 66 00 62 10 B9");
    // strBuilder.delete(0, strBuilder.length());
    String builStr = strBuilder.toString();
    System.out.println(builStr);
    byte[] builByte = toByteArray(builStr);

    for (byte b : builByte) {
      System.out.print(b + " ");
    }
    System.out.println();
    System.out.println(bytesToHex(builByte).toUpperCase());

    ArrayList<String> port = SerialPortTool.findPort();
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
    SerialPortTool.closePort(COM1);
    SerialPort COM4 = SerialPortTool.getPort("COM4", 9600, 8, 1, 0);
    try {
      SerialPortTool.write(COM4, bytes);
      byte[] comBytes = SerialPortTool.readByte(COM4);
      for(byte b: comBytes) {
        System.out.print(b + " ");
      }
    } catch (SendToPortFail e) {
      e.printStackTrace();
    } catch (OutputStreamCloseFail e) {
      e.printStackTrace();
    } catch (ReadDataFromSerialFail e) {
      e.printStackTrace();
    } catch (InputStreamCloseFail e) {
      e.printStackTrace();
    }
    SerialPortTool.closePort(COM4);
    
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
    final byte[] byteArray = new byte[hexString.length() / 2];
    int k = 0;
    for (int i = 0; i < byteArray.length; i++) {// 因为是16进制，最多只会占用4位，转换成字节需要两个16进制的字符，高位在先
      byte high = (byte) (Character.digit(hexString.charAt(k), 16) & 0xff);
      byte low = (byte) (Character.digit(hexString.charAt(k + 1), 16) & 0xff);
      String str = Integer.toHexString((high << 4 | low));
      byteArray[i] = Byte.parseByte(str);
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
    }
    return sb.toString();
  }

}
