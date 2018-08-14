package c211.serial;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.Iterator;
import java.util.List;
import java.util.TooManyListenersException;

import gnu.io.CommPort;
import gnu.io.CommPortIdentifier;
import gnu.io.NoSuchPortException;
import gnu.io.PortInUseException;
import gnu.io.SerialPort;
import gnu.io.SerialPortEvent;
import gnu.io.SerialPortEventListener;
import gnu.io.UnsupportedCommOperationException;

import c211.db.DBHelper;
import c211.serialException.*;
import c211.client.*;

public class SerialPortTool implements SerialPortEventListener {

  private static SerialPortTool spTool = null;
  DBHelper db = new DBHelper();
  protected static BufferedReader reader;
  protected static PrintWriter writer;
  protected static SerialPort port;
  AbstractReadCallback callback;
  
  private static InputStream is;

  static {
    // 初始化时创建一个串口对象
    if (spTool == null)
      spTool = new SerialPortTool();
  }
  private SerialPortTool() {
  } // 私有化构造器，不允许其他类创建

  /**
   * 获取串口工具对象
   * 
   * @return 串口工具对象
   */
  public static SerialPortTool getSerialPortTool() {
    if (spTool == null) {
      spTool = new SerialPortTool();
    }
    return spTool;
  }
  /**
   * 查找可用端口
   * 
   * @return 可用端口名称列表
   */
  public static final ArrayList<String> findPort() {

    // 获取当前所有可用串口
    @SuppressWarnings("unchecked")
    Enumeration<CommPortIdentifier> portList = CommPortIdentifier.getPortIdentifiers();
    ArrayList<String> portNameList = new ArrayList<>(); // 用来存储串口名
    while (portList.hasMoreElements()) {
      String portName = portList.nextElement().getName();
      portNameList.add(portName);
    }
    return portNameList;
  }
  /**
   * 通过序号获取串口
   * @param xuhao 1-->COM1, 2-->COM2
   * @return 串口对象
   * @throws SerialPortParamFail
   * @throws NotASerialPort
   * @throws NoSuchPort
   * @throws PortInUse
   */
  public static SerialPort getPort(int xuhao) throws SerialPortParamFail, NotASerialPort, NoSuchPort, PortInUse {
    SerialPorts myPort = null;
    List<SerialPorts> list = UserTools.getportnameByxuhao(xuhao);
    for(Iterator<SerialPorts> it = list.iterator(); it.hasNext();) {
      myPort = it.next();
    }
    try {
      CommPortIdentifier comm = CommPortIdentifier.getPortIdentifier(myPort.getPortname()); //通过端口名识别端口
      CommPort commPort = comm.open(myPort.getPortname(), 2000);  //打开端口，设置超时
      //如果是串口
      if(commPort instanceof SerialPort) {
        port = (SerialPort) commPort;
        try {
          port.setSerialPortParams(Integer.parseInt(myPort.getBaudrate()), Integer.parseInt(myPort.getDatabits()), 
              Integer.parseInt(myPort.getStopbits()), Integer.parseInt(myPort.getParity()));
        } catch(UnsupportedCommOperationException e) {
          throw new SerialPortParamFail();
        }
        return port;
      } else {
        throw new NotASerialPort(); //不是串口
      } 
    } catch(NoSuchPortException e) {
      throw new NoSuchPort();
    } catch(PortInUseException ex) {
      throw new PortInUse();
    }  
  }
  /**
   * 重载获取串口方法
   * @param sPort 串口号
   * @param baudRate 波特率
   * @param dataBits 数据位
   * @param stopBits 停止位
   * @param parity 校验位(1->奇校验，2->偶校验，0->无校验)
   * @return 当前串口
   * @throws SerialPortParamFail
   * @throws NotASerialPort
   * @throws NoSuchPort
   * @throws PortInUse
   */
  public static SerialPort getPort(String portName, int baudRate, int dataBits, 
      int stopBits, int parity) throws SerialPortParamFail, NotASerialPort, NoSuchPort, PortInUse {
    
    
    try {
      CommPortIdentifier comm = CommPortIdentifier.getPortIdentifier(portName); //通过端口名识别端口
      CommPort commPort = comm.open(portName, 2000);  //打开端口，设置超时
      //如果是串口
      if(commPort instanceof SerialPort) {
        port = (SerialPort) commPort;
        try {
          port.setSerialPortParams(baudRate, dataBits, stopBits, parity);
        } catch(UnsupportedCommOperationException e) {
          throw new SerialPortParamFail();
        }
        return port;
      } else {
        throw new NotASerialPort(); //不是串口
      } 
    } catch(NoSuchPortException e) {
      throw new NoSuchPort();
    } catch(PortInUseException ex) {
      throw new PortInUse();
    }  
  }
  /**
   * 向串口发送数据
   * @param command 待发送的命令
   * @return 如果成功返回true
   */
  public static boolean write(SerialPort sPort, String command) {
    SerialPort sp = sPort;
    try {
      writer = new PrintWriter(new OutputStreamWriter(sp.getOutputStream(), "UTF-8"), true);
      writer.println(command);
      return true;
    } catch (UnsupportedEncodingException e) {
      e.printStackTrace();
      return false;
    } catch (IOException e) {
      e.printStackTrace();
      return false;
    }
  }
  /**
   * 
   * @param sPort
   * @param callback
   * @param charset
   * @return
   */
  public SerialPortTool read(SerialPort sPort, AbstractReadCallback callback, Charset charset) {
    try {
      this.callback = callback;
      reader = new BufferedReader(new InputStreamReader(sPort.getInputStream(), charset));
      is = new BufferedInputStream(port.getInputStream());
      sPort.addEventListener(this);
      sPort.notifyOnDataAvailable(true);
      return this;
    } catch (UnsupportedEncodingException e) {
      e.printStackTrace();
    } catch (IOException e) {
      e.printStackTrace();
    } catch (TooManyListenersException e) {
      e.printStackTrace();
    }
    return this;
  }
  
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
      callback.onError(event);
      break;
    case SerialPortEvent.DATA_AVAILABLE:
      synchronized (reader) {
        // 数据到达，回调
        callback.call(reader, is);
      }
    }
  }
  /**
   * 关闭串口
   * @param serialPort  串口对象
   */
  public static void closePort(SerialPort serialPort) {
    if (serialPort != null) {
      serialPort.close();
      serialPort = null;
    }
  }
  /**
   * 测试本类方法
   * @param args
   * @throws SerialPortParamFail
   * @throws NotASerialPort
   * @throws NoSuchPort
   * @throws PortInUse
   * @throws SendToPortFail
   * @throws OutputStreamCloseFail
   * @throws ReadDataFromSerialFail
   * @throws c211.serialException.TooManyListeners 
   */
  public static void main(String[] args) throws SerialPortParamFail, NotASerialPort, NoSuchPort, PortInUse,
      SendToPortFail, OutputStreamCloseFail, ReadDataFromSerialFail, c211.serialException.TooManyListeners {
    
    System.out.println(SerialPortEvent.DATA_AVAILABLE);
    ArrayList<String> port = findPort();
    for (Iterator<String> i = port.iterator(); i.hasNext();) {
      String s = i.next();
      System.out.println(s);
    }
    SerialPort COM1 = getPort("COM1", 9600, 8, 1, 0);
    //SerialPort COM1 = getPort(1);  /*openPort("COM1", "EVEN", 9600);*/
    System.out.println("波特率：" + COM1.getBaudRate());
    System.out.println("校验位：" + COM1.getParity());
    write(COM1, ":function:voltage:DC");
    write(COM1, ":measure:voltage:DC?");
    CallBack cb = new CallBack();
    new SerialPortTool().read(COM1, cb, Charset.forName("UTF-8"));
    System.out.println(cb.getResult());  //获取值
    closePort(COM1);
  }

}