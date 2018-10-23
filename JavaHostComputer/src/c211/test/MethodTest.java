package c211.test;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.TooManyListenersException;

import javax.swing.JFrame;
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
import javax.swing.JTextArea;
import javax.swing.Timer;

public class MethodTest {
  
  static SerialPort COM1;
  protected JFrame  dataFrame;
  protected JTextArea textArea;
  protected Timer timer1 = new Timer(500, new Timer1Listener());
  
  public static void main(String[] args) throws Exception, NoSuchPort, PortInUse, Exception {
    main_1();
    /*EventQueue.invokeLater(new Runnable() {
      public void run() {
        try {
          MethodTest window = new MethodTest();
          window.dataFrame.setVisible(true); 
        } catch (Exception e) {
          e.printStackTrace();
        }
      }
    });*/
  }
  public MethodTest() {
    dataFrame = new JFrame();
    dataFrame.setBounds(100, 100, 617, 485);
    dataFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    dataFrame.getContentPane().setLayout(null);
    
    textArea = new JTextArea();
    textArea.setBounds(23, 10, 550, 426);
    dataFrame.getContentPane().add(textArea);
  }
  public static void main_1() throws Exception, NotASerialPort, NoSuchPort, PortInUse {
    ArrayList<String> port = SerialPortTool.findPort();
    for(Iterator<String> it = port.iterator(); it.hasNext();) {
      System.out.println(it.next());
    }
    COM1 = SerialPortTool.getPort("COM1", 9600, 8, 1, 0);
    //Thread.sleep(50);
    /*byte[] data = {0x01, 0x10, 0x00, 0x01, 0x00, 0x08, 0x10, 0x00, 0x60, 0x00, (byte) 0x80, 0x00,
        0x76, 0x00, 0x67, 0x00, (byte) 0x81, 0x00, 0x68, 0x00, 0x66, 0x00, 0x62, 0x10, (byte) 0xB9};
    SerialPortTool.write(COM1, data);*/
    //comWrite(COM1, "01 10 00 01 00 08 10 00 60 00 80 00 76 00 67 00 81 00 68 00 66 00 62 10 B9");
    add(COM1, new SerialPortEventListener() {
      
      @Override
      public void serialEvent(SerialPortEvent event) {
        switch (event.getEventType()) {
        case SerialPortEvent.BI:  //10 通讯中断
          JOptionPane.showMessageDialog(null, "COM1:" + "通讯中断!");
          break;
        case SerialPortEvent.OE:  // 7 溢位（溢出）错误
          JOptionPane.showMessageDialog(null, "COM1:" + "溢位（溢出）错误!");
          break;
        case SerialPortEvent.FE:  // 9 帧错误
          JOptionPane.showMessageDialog(null, "COM1:" + "帧错误!");
          break;
        case SerialPortEvent.PE:  // 8 奇偶校验错误
          JOptionPane.showMessageDialog(null, "COM1:" + "奇偶校验错误!");
          break;
        case SerialPortEvent.CD:  // 6 载波检测
          JOptionPane.showMessageDialog(null, "COM1:" + "载波检测!");
          break;
        case SerialPortEvent.CTS:  // 3 清除待发送数据
          JOptionPane.showMessageDialog(null, "COM1:" + "清除待发送数据!");
          break;
        case SerialPortEvent.DSR:  // 4 待发送数据准备好了
          JOptionPane.showMessageDialog(null, "COM1:" + "待发送数据准备好了!");
          break;
        case SerialPortEvent.RI:  // 5 振铃指示
          JOptionPane.showMessageDialog(null, "COM1:" + "振铃指示!");
          break;
        case SerialPortEvent.OUTPUT_BUFFER_EMPTY:  // 2 输出缓冲区已清空
          JOptionPane.showMessageDialog(null, "COM1:" + "输出缓冲区已清空");
          break;
        case SerialPortEvent.DATA_AVAILABLE: {
          // 有数据到达-----可以开始处理
          COM1DatasArrived();
        }
          break;
        }
      }
    });
    //comWrite(COM1, "01 10 00 01 00 08 10 00 60 00 80 00 76 00 67 00 81 00 68 00 66 00 62 10 B9");
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
      for(byte ch: datas) {
        System.out.print(ch + ",");
      }
      for (int i = 0; i < datas.length - 14; i++) {
        if (isEquals(datas[i], "60") && isEquals(datas[i + 1], "00") && isEquals(datas[i + 2], "83")
            && isEquals(datas[i + 3], "00") && isEquals(datas[i + 4], "67") && isEquals(datas[i + 5], "00")
            && isEquals(datas[i + 6], "68") && isEquals(datas[i + 7], "00") && isEquals(datas[i + 8], "90")
            && isEquals(datas[i + 9], "00") && isEquals(datas[i + 10], "49") && isEquals(datas[i + 11], "00")
            && isEquals(datas[i + 12], "83") && isEquals(datas[i + 13], "00") && isEquals(datas[i + 14], "62")) {
          
          System.out.println("我来询问一下！");
        }
        else if (isEquals(datas[i], "60") && isEquals(datas[i + 1], "00") && isEquals(datas[i + 2], "81")
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
        else if (isEquals(datas[i], "60") && isEquals(datas[i + 1], "00") && isEquals(datas[i + 2], "67")
            && isEquals(datas[i + 3], "00") && isEquals(datas[i + 4], "83") && isEquals(datas[i + 5], "00")
            && isEquals(datas[i + 6], "68") && isEquals(datas[i + 7], "00") && isEquals(datas[i + 8], "90")
            && isEquals(datas[i + 9], "00") && isEquals(datas[i + 10], "49") && isEquals(datas[i + 11], "00")
            && isEquals(datas[i + 12], "83") && isEquals(datas[i + 13], "00") && isEquals(datas[i + 14], "62")) {
          
          System.out.println("测试结束");
          System.exit(0);
        }
        else
          comWrite(COM1, "01 10 00 01 00 08 10 00 60 00 80 00 76 00 67 00 81 00 68 00 66 00 62 10 B9");
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
  class Timer1Listener implements ActionListener {

    @Override
    public void actionPerformed(ActionEvent e) {
      try {
        main_1();
      } catch (NotASerialPort e1) {
        e1.printStackTrace();
      } catch (NoSuchPort e1) {
        e1.printStackTrace();
      } catch (PortInUse e1) {
        e1.printStackTrace();
      } catch (Exception e1) {
        e1.printStackTrace();
      }
    }
    
  }
    
}
