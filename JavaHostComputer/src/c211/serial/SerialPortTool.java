package c211.serial;

import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.UnsupportedEncodingException;
import java.io.Writer;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.TooManyListenersException;

import gnu.io.CommPort;
import gnu.io.CommPortIdentifier;
import gnu.io.NoSuchPortException;
import gnu.io.PortInUseException;
import gnu.io.SerialPort;
import gnu.io.SerialPortEventListener;
import gnu.io.UnsupportedCommOperationException;
import sun.misc.BASE64Decoder;
import sun.misc.BASE64Encoder;
import c211.serialException.*;

public class SerialPortTool {
  
  private static SerialPortTool spTool = null;
  
  static {
    //初始化时创建一个串口对象
    if(spTool == null)
      spTool = new SerialPortTool();  
  }
  private SerialPortTool() {} //私有化构造器，不允许其他类创建
  /**
   * 获取串口工具对象
   * @return 串口工具对象
   */
  public static SerialPortTool getSerialPortTool() {
    if(spTool == null) {
      spTool = new SerialPortTool();
    }
    return spTool;      
  }
  /**
   * 查找可用端口
   * @return 可用端口名称列表
   */
  public static final ArrayList<String> findPort() {
   
    //获取当前所有可用串口
    @SuppressWarnings("unchecked")
    Enumeration<CommPortIdentifier> portList = CommPortIdentifier.getPortIdentifiers();
    ArrayList<String> portNameList = new ArrayList<>(); //用来存储串口名
    while(portList.hasMoreElements()) {
      String portName = portList.nextElement().getName();
      portNameList.add(portName);
    }
    return portNameList;
    
  }
  /**
   *打开串口
   * @param portName 端口名称
   * @param parity 校验位(如:偶校验为"EVEN",无则为"NONE")
   * @param baudrate 波特率
   * @return 串口对象
   * @throws SerialPortParamFail
   * @throws NotASerialPort
   * @throws NoSuchPort
   * @throws PortInUse
   */
  public static final SerialPort openPort(String portName,String parity, int baudrate) throws SerialPortParamFail, NotASerialPort, NoSuchPort, PortInUse {
    try {
      //通过端口名识别端口
      CommPortIdentifier portIdentifier = CommPortIdentifier.getPortIdentifier(portName);
      //打开端口，设置超时
      CommPort commPort = portIdentifier.open(portName, 2000);
      //如果是串口
      if(commPort instanceof SerialPort) {
        SerialPort serialPort = (SerialPort) commPort;
        try {
          //设置串口参数
          if(parity.equals("EVEN")) {
            serialPort.setSerialPortParams(baudrate, SerialPort.DATABITS_8, SerialPort.STOPBITS_1, SerialPort.PARITY_EVEN);
          }
          else if(parity.equals("NONE")) {
            serialPort.setSerialPortParams(baudrate, SerialPort.DATABITS_8, SerialPort.STOPBITS_1, SerialPort.PARITY_NONE);
          } 
        } catch(UnsupportedCommOperationException e) {
          throw new SerialPortParamFail();
        }
        return serialPort;
      }
      else {
        //不是串口
        throw new NotASerialPort();
      }      
    } catch(NoSuchPortException e) {
      throw new NoSuchPort();
    } catch(PortInUseException ex) {
      throw new PortInUse();
    }
  } 
  /**
   * 关闭串口
   * @param serialPort
   */
  public static void closePort(SerialPort serialPort) {
    if(serialPort != null) {
      serialPort.close();
      serialPort = null;
    }
  }
  /**
   * 将字符串转换成字节数组
   * @param str
   * @return
   */
  public static byte[] hexStrToByteArray(String str) {
    BASE64Decoder dec = new BASE64Decoder();
    byte[] after = null;
    try {
      after = dec.decodeBuffer(str);// 使用BASE64解码
    } catch (IOException e) {
      e.printStackTrace();
    }
    return after;
  }
  
  public static String byteArrayToString(byte[] bytes) {
    BASE64Encoder enc=new BASE64Encoder();
    String mes=enc.encodeBuffer(bytes); //使用BASE64编码
    return mes;
  }
  /**
   * 发送命令到串口
   * @param serialPort 串口名
   * @param order 命令
   * @throws SendToPortFail
   * @throws OutputStreamCloseFail
   */
  public static void sendToPort(SerialPort serialPort, String order) throws SendToPortFail, OutputStreamCloseFail {
    /*byte[] orders = null;
    try {
      orders = order.getBytes("UTF-8");
    } catch (UnsupportedEncodingException e1) {
      // TODO 自动生成的 catch 块
      e1.printStackTrace();
    }
    OutputStream out = null;*/
    //char[] chOder = order.toCharArray();
    OutputStream out = null;
    BufferedWriter bw = null;
    try {
      out = serialPort.getOutputStream();
      bw = new BufferedWriter(new OutputStreamWriter(out));
      bw.write(order);
      out.flush();
      bw.flush();
    } catch(IOException e) {
      throw new SendToPortFail();
    } finally {
      try {
        if(out != null) {
          out.close();
          out = null;
        }
        else if(bw != null) {
          bw.close();
          bw = null;
        }
      } catch(IOException e) {
        throw new OutputStreamCloseFail();
      }
    }  
  }
  /**
   * 从串口读取数据
   * @param serialPort
   * @return 字节数组(存放读取到的数据)
   * @throws ReadDataFromSerialFail
   * @throws OutputStreamCloseFail
   */
  public static byte[] readFromPort(SerialPort serialPort) throws ReadDataFromSerialFail, OutputStreamCloseFail {
    InputStream in = null;
    byte[] bytes = null;
    try {
      in = serialPort.getInputStream();
      int buffLenth = in.available(); //获取buffer中的数据长度
      while(buffLenth != 0) {
        bytes = new byte[buffLenth];   //根据buffer中的数据长度创建数组用来存储数据
        in.read(bytes);
        buffLenth = in.available();
      }      
    } catch(IOException e) {
      throw new ReadDataFromSerialFail();
    } finally {
      try {
        if(in != null) {
          in.close();
          in = null;
        }
      } catch(IOException e) {
        throw new OutputStreamCloseFail();
      }
    }
    return bytes;
  }
  
  public static void addListener(SerialPort serialPort, SerialPortEventListener listener) throws TooManyListeners {
    try {
      //给串口添加监听器
      serialPort.addEventListener(listener);
      //设置当数据到达时唤醒监听接收线程
      serialPort.notifyOnDataAvailable(true);
      //通信中断时唤醒中断线程
      serialPort.notifyOnBreakInterrupt(true);
    } catch(TooManyListenersException e) {
      throw new TooManyListeners();
    }
  }
  public static void main(String[] args) throws SerialPortParamFail, NotASerialPort, NoSuchPort, PortInUse, SendToPortFail, OutputStreamCloseFail, ReadDataFromSerialFail {
   /* ArrayList<String> port = findPort();
    for(Iterator<String> i = port.iterator(); i.hasNext(); ) {
      String s = i.next();
      System.out.println(s);
    }
    SerialPort COM1 = openPort("COM1", "NONE", 9600);*/
    /*
    byte[] bytes = {0x2A, 0x52, 0x53, 0x54, 0x0D, 0x0A, 0x63, 0x6D, 
        0x64, 0x73, 0x65, 0x74, 0x20, 0x72, 0x69, 0x67, 
        0x6F, 0x6C, 0x0D, 0x0A, 0x3A, 0x46, 0x55, 0x4E, 
        0x43, 0x74, 0x69, 0x6F, 0x6E, 0x3A, 0x43, 0x4C, 
        0x45, 0x41, 0x52, 0x0D, 0x0A, 0x3A, 0x66, 0x75, 
        0x6E, 0x63, 0x74, 0x69, 0x6F, 0x6E, 0x3A, 0x43, 
        0x55, 0x52, 0x52, 0x45, 0x4E, 0x54, 0x3A, 0x44, 
        0x43, 0x0D, 0x0A, 0x3A, 0x6D, 0x65, 0x61, 0x73, 
        0x75, 0x72, 0x65, 0x3A, 0x43, 0x55, 0x52, 0x52, 
        0x45, 0x4E, 0x54, 0x3A, 0x44, 0x43, 0x3F, 0x0D, 
        0x0A, 0x3A, 0x63, 0x61, 0x6C, 0x63, 0x75, 0x6C, 
        0x61, 0x74, 0x65, 0x3A, 0x66, 0x75, 0x6E, 0x63, 
        0x74, 0x69, 0x6F, 0x6E, 0x20, 0x6E, 0x6F, 0x6E, 0x65}; */
    //String order = ":measure:voltage:DC?";
    byte[] b = hexStrToByteArray(":measure:voltage:DC?");
    for(byte by: b)
      System.out.println(by);
    System.out.println(byteArrayToString(b));
    /*sendToPort(COM1, "*RST");
    sendToPort(COM1, "cmdset rigol");
    sendToPort(COM1, ":FUNCtion:CLEAR");
    sendToPort(COM1, ":function:voltage:DC");
    sendToPort(COM1, ":measure:voltage:DC?");
    sendToPort(COM1, ":calculate:function none");
    System.out.println(readFromPort(COM1));*/
  }
  
}