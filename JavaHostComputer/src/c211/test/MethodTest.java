package c211.test;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.TooManyListenersException;

import javax.swing.JOptionPane;

import c211.serial.SerialPortTool;
import c211.serialException.InputStreamCloseFail;
import c211.serialException.NoSuchPort;
import c211.serialException.NotASerialPort;
import c211.serialException.OutputStreamCloseFail;
import c211.serialException.PortInUse;
import c211.serialException.ReadDataFromSerialFail;
import c211.serialException.SendToPortFail;
import c211.serialException.TooManyListeners;
import gnu.io.SerialPort;
import gnu.io.SerialPortEvent;
import gnu.io.SerialPortEventListener;

public class MethodTest {
  
  static SerialPort COM1;
  
  public static void main(String[] args) throws Exception, NotASerialPort, NoSuchPort, PortInUse {
    ArrayList<String> port = SerialPortTool.findPort();
    for(Iterator<String> it = port.iterator(); it.hasNext();) {
      System.out.println(it.next());
    }
    COM1 = SerialPortTool.getPort("COM1", 9600, 8, 1, 0);
    add(COM1, new SerialPortEventListener() {
      
      @Override
      public void serialEvent(SerialPortEvent event) {
        switch (event.getEventType()) {
        case SerialPortEvent.BI:
        case SerialPortEvent.OE:
        case SerialPortEvent.FE:
        case SerialPortEvent.PE:
        case SerialPortEvent.CD:
        case SerialPortEvent.CTS:
        case SerialPortEvent.DSR:
        case SerialPortEvent.RI:
        case SerialPortEvent.OUTPUT_BUFFER_EMPTY:
          JOptionPane.showMessageDialog(null, "COM1:"+event.toString());
          break;
        case SerialPortEvent.DATA_AVAILABLE: {
          // 有数据到达-----可以开始处理
          COM1DatasArrived();
        }
          break;
        }
      }
    });
    comWrite(COM1, "01 10 00 01 00 08 10 00 60 00 80 00 76 00 67 00 81 00 68 00 66 00 62 10 B9");
    while(true);
    
    /*System.out.println(SerialPortTool.byteAsciiToChar(0x54));
    List<Integer> list = new ArrayList<Integer>();
    list.add(600);
    for(int i = 0; i < 11; i++) {
      list.add(i);
    }
    for(int i = 90; i > 84; i--) {
      list.add(i);
    }
    System.out.println(list+ ":::" + list.size());
    Collections.sort(list);
    System.out.println(list+ ":::" + list.size());
    System.out.println(list.get(list.size() - 1));
    
    List<Integer> listData = new ArrayList<>();
    for(int i = 0; i < 10; i++) {
      listData.add(i);
    }
    System.out.println(listData);
    listData.set(5, Integer.parseInt(String.valueOf(SerialPortTool.byteAsciiToChar(0x33))));
    System.out.println(listData);
    Collections.sort(listData);
    System.out.println(listData);
    
    StringBuilder strBuilder = new StringBuilder();
    strBuilder.append('3');
    strBuilder.append('0');
    strBuilder.append('3');
    strBuilder.append('3');
    
    System.out.println(Integer.parseInt(strBuilder.toString()) == 3033);*/
  }
  public static void COM1DatasArrived() {
    try {
      System.out.println("有数据到达！");
      Thread.sleep(50);
      byte[] datas = SerialPortTool.readByte(COM1);
      /*for(byte ch: datas) {
        System.out.print(ch + ",");
      }*/
      for (int i = 0; i < datas.length - 14; i++) {
        if (isEquals(datas[i], "60") && isEquals(datas[i + 1], "00") && isEquals(datas[i + 2], "81")
            && isEquals(datas[i + 3], "00") && isEquals(datas[i + 4], "68") && isEquals(datas[i + 5], "00")
            && isEquals(datas[i + 6], "84") && isEquals(datas[i + 7], "00") && isEquals(datas[i + 8], "79")
            && isEquals(datas[i + 9], "00") && isEquals(datas[i + 10], "80") && isEquals(datas[i + 11], "00")
            && isEquals(datas[i + 12], "67") && isEquals(datas[i + 13], "00") && isEquals(datas[i + 14], "62")) {
          
          System.out.println("开始测试");
        }
        else if (isEquals(datas[i], "60") && isEquals(datas[i + 1], "00") && isEquals(datas[i + 2], "83")
            && isEquals(datas[i + 3], "00") && isEquals(datas[i + 4], "50") && isEquals(datas[i + 5], "00")
            && isEquals(datas[i + 6], "67") && isEquals(datas[i + 7], "00") && isEquals(datas[i + 8], "83")
            && isEquals(datas[i + 9], "00") && isEquals(datas[i + 10], "87") && isEquals(datas[i + 11], "00")
            && isEquals(datas[i + 12], "67") && isEquals(datas[i + 13], "00") && isEquals(datas[i + 14], "62")) {
          
          System.out.println("停止测试");
        }
        else if (isEquals(datas[i], "60") && isEquals(datas[i + 1], "00") && isEquals(datas[i + 2], "67")
            && isEquals(datas[i + 3], "00") && isEquals(datas[i + 4], "83") && isEquals(datas[i + 5], "00")
            && isEquals(datas[i + 6], "68") && isEquals(datas[i + 7], "00") && isEquals(datas[i + 8], "90")
            && isEquals(datas[i + 9], "00") && isEquals(datas[i + 10], "83") && isEquals(datas[i + 11], "00")
            && isEquals(datas[i + 12], "49") && isEquals(datas[i + 13], "00") && isEquals(datas[i + 14], "62")) {
          
          System.out.println("电阻测试");
        }
      }
    } catch (ReadDataFromSerialFail e) {
      e.printStackTrace();
    } catch (InputStreamCloseFail e) {
      e.printStackTrace();
    } catch (InterruptedException e) {
      e.printStackTrace();
    }
    
  }
  public static boolean isEquals(byte hex, String data) {
    String s1 = String.format("%02x", hex);
    if (s1.equals(data))
      return true;
    else
      return false;
  }
  public static boolean comWrite(SerialPort com, String hexString) {
    byte[] datas = SerialPortTool.toByteArray(hexString);
    try {
      SerialPortTool.write(com, datas);
      return true;
    } catch (SendToPortFail e) {
      JOptionPane.showMessageDialog(null, e.toString());
      return false;
    } catch (OutputStreamCloseFail e) {
      JOptionPane.showMessageDialog(null, e.toString());
      return false;
    }
  }
  public static boolean isEquals(char hex, String data) {
    String s1 = String.format("%02x", (int)hex);
    if (s1.equals(data))
      return true;
    else
      return false;
  }
  public static void add(SerialPort port, SerialPortEventListener listener) throws TooManyListeners {

    try {
      // 给串口添加监听器
      port.addEventListener(listener);
      // 设置当有数据到达时唤醒监听接收线程
      port.notifyOnDataAvailable(true);
      // 设置当通信中断时唤醒中断线程
      port.notifyOnBreakInterrupt(true);

    } catch (TooManyListenersException e) {
      throw new TooManyListeners();
    }
  }
    
}
