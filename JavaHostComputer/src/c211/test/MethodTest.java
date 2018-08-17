package c211.test;

import java.util.ArrayList;
import java.util.Iterator;

import c211.serial.SerialPortTool;
import c211.serialException.NoSuchPort;
import c211.serialException.NotASerialPort;
import c211.serialException.PortInUse;
import c211.serialException.SerialPortParamFail;
import gnu.io.SerialPort;

public class MethodTest {
  
  static SerialPortTool portTool = SerialPortTool.getSerialPortTool();
  
  public static void main(String[] args) throws SerialPortParamFail, NotASerialPort, NoSuchPort, PortInUse {
    
    ArrayList<String> port = SerialPortTool.findPort();
    for (Iterator<String> i = port.iterator(); i.hasNext();) {
      String s = i.next();
      System.out.println("可用串口：" + s);
    }
    SerialPort COM1 = SerialPortTool.getPort(1);
    //SerialPort COM1 = SerialPortTool.getPort("COM1", 9600, 8, 1, 0);
    System.out.println("COM1波特率：" + COM1.getBaudRate());
    System.out.println("COM1校验位：" + COM1.getParity());
    SerialPortTool.write(COM1, ":function:voltage:DC");
    SerialPortTool.write(COM1, ":measure:voltage:DC?");
    System.out.println("COM1>>>>>>>>>>>>>>>>>>>>>>" + SerialPortTool.read(COM1));
    SerialPortTool.closePort(COM1);
    
   /* SerialPort COM4 = SerialPortTool.getPort("COM4", 9600, 8, 1, 0);
    System.out.println("COM4波特率：" + COM4.getBaudRate());
    System.out.println("COM4校验位：" + COM4.getParity());
    System.out.println(SerialPortTool.write(COM4, "1.2345"));
    System.out.println("COM4>>>>>>>>>>>>>>>>>>>>>>" + SerialPortTool.read(COM4));
    SerialPortTool.closePort(COM4);*/
  }

}
