package c211.client;

import c211.db.*;
import c211.serial.*;
import c211.serialException.InputStreamCloseFail;
import c211.serialException.NoSuchPort;
import c211.serialException.NotASerialPort;
import c211.serialException.OutputStreamCloseFail;
import c211.serialException.PortInUse;
import c211.serialException.ReadDataFromSerialFail;
import c211.serialException.SendToPortFail;
import c211.serialException.SerialPortParamFail;
import c211.serialException.TooManyListeners;
import gnu.io.SerialPort;
import gnu.io.SerialPortEvent;
import gnu.io.SerialPortEventListener;

import java.awt.EventQueue;
import java.awt.Toolkit;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;

import java.awt.AWTEvent;
import java.awt.Color;
import java.awt.Component;
import javax.swing.JTextField;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Point;

import javax.swing.SwingConstants;
import javax.swing.Timer;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import javax.swing.table.TableColumn;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.plot.PiePlot;

import Automation.BDaq.ByteByRef;
import Automation.BDaq.DaqException;
import Automation.BDaq.DeviceInformation;
import Automation.BDaq.DeviceTreeNode;
import Automation.BDaq.ErrorCode;
import Automation.BDaq.InstantDiCtrl;
import Automation.BDaq.InstantDoCtrl;

import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.TooManyListenersException;
import java.util.Vector;
import java.util.logging.FileHandler;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;
import java.awt.event.AWTEventListener;
import java.awt.event.ActionEvent;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JButton;
import javax.swing.JProgressBar;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import javax.swing.JCheckBox;

/**
 * 测试页面
 * 
 * @author Loyer
 * @coding utf8
 */
public class DataView {

  private String userName;
  private JFrame dataFrame;
  private JTextField headTxtField;
  private JPanel menuPanel;
  private JPanel runPanel;
  private JPanel serialPanel;
  private JPanel resultPanel;
  private JMenuBar menuBar;
  private JMenu fileMenu;
  private JMenuItem exitSysMnItm;
  private JMenu editMenu;
  private JMenu systemMenu;
  private JMenuItem openModelMnItm;
  private JMenuItem logoutMnItm;
  private JMenuItem userManageMnItm;
  private JMenuItem debugToolMnItm;
  private JMenuItem testOptionMnItm;
  private JMenuItem viewReportMnItm;
  private JMenuItem viewResultMnItm;
  private JMenuItem aboutMnItm;
  private JMenuItem helpMnItm;
  private JMenuItem signUpMnItm;
  private JMenuItem updateMnItm;
  private JTextField totalField;
  private JTextField okField;
  private JTextField ngField;
  private JTextField tTimeField;
  private JLabel totalLabel;
  private JLabel okLabel;
  private JLabel ngLabel;
  private JLabel tTimeLabel;
  private JTextField txtStop;
  private JScrollPane scrollPane;
  private JProgressBar progressBar;
  private JButton COM1Butt;
  private JButton COM2Butt;
  private JButton COM3Butt;
  private JButton COM4Butt;
  private JButton COM5Butt;
  private JButton COM6Butt;
  private JButton COM7Butt;
  private JButton COM8Butt;
  private JPanel nyPanel;
  private JLabel pNameLabel;
  private JLabel timeLabel;
  private ChartPanel chartPanel;
  private MyPieChart pieChart;
  private PiePlot piePlot;
  private JCheckBox nayinCbox;
  private JCheckBox spotTestCbox;
  private JTextField textField13;
  private JTextField textField16;

  private int ngCount = 0;
  private int okCount = 0;
  private int totalCount = 0;
  private int timeCount = 0;

  private JTable table;
  private MyTableCellRenderrer myTableCellRenderrer = null;
  private PortListener listener = new PortListener();

  private InstantDoCtrl instantDoCtrl = new InstantDoCtrl();
  private InstantDiCtrl instantDiCtrl = new InstantDiCtrl();
  private ByteByRef by;
  private byte pciState = 0x00;
  private List<Integer> mydatap01 = new ArrayList<Integer>();
  private List<Integer> mydatap02 = new ArrayList<Integer>();
  private List<Integer> mydatap03 = new ArrayList<Integer>();
  private int dataCountOfLi = 1000;
  private BufferedImage image;  //相当于c#Bitmap
  private Point point;  //c# Point F
  private Graphics graphics; //c#  Graphics
  private JLabel picLabel; // c# pictureBox
  private ImageIcon picImage; //c#  pictureBox.image
  private double xcResult1 = 0.0d;
  private double xcResult2 = 0.0d;
  private double xcResult3 = 0.0d;
  private double V_result = 0.0d;
  private double R_result= 0.0d;
  private int R_testTimes = 0;
  private int rtimes01 = 0;
  private int rtimes02 = 0;
  private int rtimes03 = 0;
  private int timeInterValRSTjueyuan = 0;
  private List<Double> R_TEST = new ArrayList<Double>();
  private Timer timer1 = new Timer(500, new Timer1Listener());
  private Timer timer2 = new Timer(50, new Timer2Listener());
  private Timer timer3 = new Timer(1, new Timer3Listener());
  private boolean printBUT1_LL = false;
  private boolean printBut2_LL = false;
  private boolean printBut3_LL = false;
  private boolean BUT1_SET = false;
  private boolean BUT2_SET = false;
  private boolean BUT3_SET = false;
  private boolean ALLOWTEST_R1 = false;
  private boolean ALLOWTEST_R2 = false;
  private boolean ALLOWTEST_R3 = false;
  private String JUETYANNUM = "";
  private String testState = "STOP";
  private boolean ALLDIALOGISSHOW = false;
  private int s4sendtimes = 0;
  private boolean boolXC1 = false;
  private boolean boolXC2 = false;
  private boolean boolXC3 = false;
  private boolean jueyuanTestBool01 = false;
  private boolean jueyuanTestBool02 = false;
  private boolean ALLjueyuanTestBool01 = false;
  private boolean ALLjueyuanTestBool02 = false;
  private boolean outExcel;
  
  protected SerialPort COM1;
  protected SerialPort COM2;
  protected SerialPort COM3;
  protected SerialPort COM4;
  protected SerialPort COM7;
  protected SerialPort COM8;
  private boolean s1ok = false;
  private boolean s2ok = false;
  private boolean s3ok = false;
  private boolean s4ok = false;
  private boolean s7ok = false;
  private boolean s8ok = false; 
  
  protected final USBHelper usb = new USBHelper();
  public static FileHandler logFileHandler;
  protected static final String CLASS_NAME = DataView.class.getName();
  public static final Logger LOGGER = Logger.getLogger(CLASS_NAME);
  public final String LOG_FOLDER = "log/";
  public static SimpleFormatter logFormatter;
  
  /**
   * 提供实例化本类方法
   * 
   * @param user
   *          接收登录用户名，以区分权限
   */
  
  public static void getDataView(String user) {
    EventQueue.invokeLater(new Runnable() {
      public void run() {
        try {
          DataView window = new DataView(user);
          window.dataFrame.setVisible(true);
          window.initLoad(); 
        } catch (Exception e) {
          e.printStackTrace();
        }
      }
    });
  }
  /**
   * 本类构造器，接收登录页面传进User值
   */
  public DataView(String user) {
    try {
      Path logPath = Paths.get(LOG_FOLDER);
      if (Files.notExists(logPath)) {
        Files.createDirectory(logPath);
      }
      logFileHandler = new FileHandler(String.format("%s%sLog.txt", LOG_FOLDER, DataView.class.getSimpleName()));
      logFormatter = new SimpleFormatter();
      logFileHandler.setFormatter(logFormatter);
      LOGGER.addHandler(logFileHandler);
      
      this.userName = user;
      initialize();
      
      LOGGER.log(Level.INFO, String.format("登陆用户为： %s.", userName));
      LOGGER.setLevel(Level.SEVERE);
    } catch(SecurityException | IOException | UnsatisfiedLinkError e) {
      LOGGER.log(Level.SEVERE, e.getMessage(), e);
    }
   
  }
  /**
   * 初始化应用
   */
  public void initLoad() {
    initPort();
    initCountAndPieChart();
    
    image = new BufferedImage(picLabel.getWidth(), picLabel.getHeight(), BufferedImage.TYPE_INT_RGB);
    graphics = image.getGraphics();
    drawPICInit();
    point = new Point(0, picLabel.getHeight());
    
    ifProductPrint();
    jueyuanTestReset();
    timer1.start();
    timer3.start();
  }
  
  /**
   * 打开万用表
   */
  public void openMultimeter() {
    try {
      USBHelper.setUpClass();
      String ResourceName = "USB0::0x1AB1::0x09C4::DM3R194802656::INSTR";
      usb.setLogFile();
      usb.getVisaResourceManagerHandle();
      usb.openInstrument(ResourceName);
      usb.setTimeout();
    } catch (Exception e) {
      JOptionPane.showMessageDialog(null, "未发现万用表:" + e.getMessage());
    }
  }

  /**
   * 清除USB资源
   */
  public void clearResource() {
    usb.visaClear();
  }

  /**
   * 打开两线测电阻模式
   */
  public void openTwoLineR_FUC() {
    usb.visaWrite(":RATE:RESistance F");
    usb.visaWrite(":FUNCtion:RESistance");
  }

  /**
   * 打开直流电压测试模式
   */
  public void openDCvoltage_FUC() {
    usb.visaWrite(":FUNCtion:VOLTage:DC");
  }

  /**
   * 设置电阻测试量程
   * 
   * @param range
   *          量程档位
   */
  public void setR_range(String range) {
    usb.visaWrite(":RATE:RESistance F");
    usb.visaWrite(":MEASure:RESistance " + range); // 0 200欧姆 1 2k欧姆 2 20k欧姆
  }

  /**
   * 设置电压档量程
   * 
   * @param range
   *          档位
   */
  public void setV_range(String range) {
    usb.visaWrite(":MEASure:VOLTage:DC " + range); // 0 200mV 1 2V 2 20V
  }

  /**
   * 设置测试速率
   * 
   * @param rate
   */
  public void setRate(String rate) {
    usb.visaWrite(rate);
  }
  /**
   * 格式化测试值
   * @param value
   * @return
   */
  public String forMat(Double value) {
    NumberFormat numFormat = NumberFormat.getNumberInstance();
    numFormat.setMaximumFractionDigits(2);
    return numFormat.format(value).replaceAll(",", "");  //返回的字符串中不带","，防止格式转换错误
  }

  /**
   * 回读电阻值，填入表中测试值，并判断测试结果是否PASS
   * 
   * @param row
   *          行数
   */
  public void R_measure(int row) {
    usb.visaWrite(":MEASure:RESistance?");
    R_result = Double.parseDouble(usb.visaRead());
    double value_xiuzheng = R_result - 1.4d; // 修正回读值
    R_testTimes++;
    R_TEST.add(value_xiuzheng);
    if(R_testTimes > 1) {
      ALLOWTEST_R1 = false;
      ALLOWTEST_R2 = false;
      ALLOWTEST_R3 = false;
      R_testTimes = 0;
      Collections.sort(R_TEST);
      value_xiuzheng = R_TEST.get(0);
      R_TEST.clear();
      setValueAt(row, forMat(value_xiuzheng));
      setResValueAtRow(row);
    }    
  }

  /**
   * 回读电压值，填入表中测试值，并判断测试结果是否PASS
   * 
   * @param row
   *          行数
   */
  public void V_measure(int row) {
    usb.visaWrite(":measure:voltage:DC?");
    V_result = Double.parseDouble(usb.visaRead());
    setValueAt(row, forMat(V_result));
    setResValueAtRow(row);
  }
  /**
   * 设置最终结果
   */
  public void ifPass() {
    if(isPassed()) {
      setTxtStopFieldPASS();
    }
    /*else if(hasQuestionMark()) {
      if(!txtStop.getText().equals("NG")) {
        setTxtStopFieldNG();
      }
    }*/
  }
  /**
   * 初始化页面
   */
  private void initialize() {    
    dataFrame = new JFrame();
    // dataFrame.getContentPane().setBackground(new Color(245, 245, 220));
    // 自动获取屏幕分辨率
    // Dimension viewSize = Toolkit.getDefaultToolkit().getScreenSize();
    final int WIDTH = Toolkit.getDefaultToolkit().getScreenSize().width;
    final int HEIGHT = Toolkit.getDefaultToolkit().getScreenSize().height;
    dataFrame.setBounds(0, 0, WIDTH, HEIGHT);
    dataFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

    dataFrame.setResizable(false); // 窗口大小不可改
    dataFrame.setUndecorated(true);
    // 更换背景图片
    ImageIcon img_1 = new ImageIcon("src/run.jpg");
    JLabel imgLabel = new JLabel(img_1);
    dataFrame.getLayeredPane().add(imgLabel, new Integer(Integer.MIN_VALUE));
    imgLabel.setBounds(0, 0, img_1.getIconWidth(), img_1.getIconHeight()); // 背景图片的位置
    // 将contentPane设置成透明的
    ((JPanel) dataFrame.getContentPane()).setOpaque(false);

    // 替换窗口的咖啡图标
    Toolkit tk = Toolkit.getDefaultToolkit();
    // 全局添加键盘监听事件
    tk.addAWTEventListener(new AWTEventListener() {

      @Override
      public void eventDispatched(AWTEvent event) {
        // 当有键按下，且为F键(此时需按shift+f)时，刷新页面数据
        if (((KeyEvent) event).getID() == KeyEvent.KEY_PRESSED && ((KeyEvent) event).getKeyChar() == KeyEvent.VK_F) {
          initCountAndPieChart();
        }
      }
    }, AWTEvent.KEY_EVENT_MASK);
    Image img = tk.getImage("src/Kyokuto.png");

    dataFrame.setIconImage(img);
    dataFrame.getContentPane().setLayout(null);

    headTxtField = new JTextField();
    headTxtField.setEditable(false);
    headTxtField.setOpaque(false);
    headTxtField.setBounds(0, 0, WIDTH, HEIGHT / 9 - 5);
    headTxtField.setText("记忆开关测试系统");
    headTxtField.setHorizontalAlignment(SwingConstants.CENTER);
    headTxtField.setForeground(new Color(0, 0, 255));
    headTxtField.setFont(new Font("宋体", Font.BOLD, 50));
    headTxtField.setBackground(new Color(0, 204, 255));
    dataFrame.getContentPane().add(headTxtField);
    headTxtField.setColumns(10);

    resultPanel = new JPanel();
    resultPanel.setOpaque(false);
    resultPanel.setBounds(0, HEIGHT / 9, WIDTH * 10 / 16, HEIGHT * 9 / 72);
    resultPanel.setBackground(new Color(211, 211, 211));
    dataFrame.getContentPane().add(resultPanel);
    resultPanel.setLayout(null);

    totalField = new JTextField();
    totalField.setOpaque(false);
    totalField.setEditable(false);
    // totalField.setText("" + totalCount + "");
    totalField.setHorizontalAlignment(SwingConstants.CENTER);
    totalField.setForeground(new Color(139, 69, 19));
    totalField.setFont(new Font("等线", Font.PLAIN, 40));
    totalField.setBounds(WIDTH * 1 / 20, HEIGHT * 9 / 288, WIDTH * 6 / 64, HEIGHT * 9 / 144);
    totalField.setBackground(new Color(189, 183, 107));
    resultPanel.add(totalField);
    totalField.setColumns(10);

    okField = new JTextField();
    okField.setOpaque(false);
    okField.setEditable(false);
    // okField.setText("" + okCount + "");
    okField.setHorizontalAlignment(SwingConstants.CENTER);
    okField.setForeground(new Color(0, 204, 51));
    okField.setFont(new Font("等线", Font.PLAIN, 40));
    okField.setBounds(WIDTH * 31 / 160, HEIGHT * 9 / 288, WIDTH * 6 / 64, HEIGHT * 9 / 144);
    okField.setBackground(new Color(189, 183, 107));
    okField.setColumns(10);
    resultPanel.add(okField);

    ngField = new JTextField();
    ngField.setOpaque(false);
    ngField.setEditable(false);
    // ngField.setText("" + ngCount + "");
    ngField.setHorizontalAlignment(SwingConstants.CENTER);
    ngField.setFont(new Font("等线", Font.PLAIN, 40));
    ngField.setForeground(new Color(255, 0, 0));
    ngField.setBounds(WIDTH * 54 / 160, HEIGHT * 9 / 288, WIDTH * 6 / 64, HEIGHT * 9 / 144);
    ngField.setBackground(new Color(189, 183, 107));
    ngField.setColumns(10);
    resultPanel.add(ngField);

    tTimeField = new JTextField();
    tTimeField.setOpaque(false);
    tTimeField.setEditable(false);
    tTimeField.setText("" + timeCount + "");
    tTimeField.setHorizontalAlignment(SwingConstants.CENTER);
    tTimeField.setFont(new Font("等线", Font.PLAIN, 40));
    tTimeField.setForeground(new Color(0, 204, 51));
    tTimeField.setBounds(WIDTH * 77 / 160, HEIGHT * 9 / 288, WIDTH * 6 / 64, HEIGHT * 9 / 144);
    tTimeField.setBackground(new Color(189, 183, 107));
    tTimeField.setColumns(10);
    resultPanel.add(tTimeField);

    totalLabel = new JLabel("测试总数：");
    totalLabel.setFont(new Font("等线", Font.BOLD, 16));
    totalLabel.setForeground(new Color(50, 205, 50));
    totalLabel.setBounds(WIDTH * 1 / 20, 0, 110, HEIGHT * 9 / 288);
    resultPanel.add(totalLabel);

    okLabel = new JLabel("OK：");
    okLabel.setForeground(new Color(50, 205, 50));
    okLabel.setFont(new Font("等线", Font.BOLD, 16));
    okLabel.setBounds(WIDTH * 31 / 160, 0, 110, HEIGHT * 9 / 288);
    resultPanel.add(okLabel);

    ngLabel = new JLabel("NG：");
    ngLabel.setForeground(new Color(255, 0, 0));
    ngLabel.setFont(new Font("等线", Font.BOLD, 16));
    ngLabel.setBounds(WIDTH * 54 / 160, 0, 110, HEIGHT * 9 / 288);
    resultPanel.add(ngLabel);

    tTimeLabel = new JLabel("测试时间(S)：");
    tTimeLabel.setForeground(new Color(50, 205, 50));
    tTimeLabel.setFont(new Font("等线", Font.BOLD, 16));
    tTimeLabel.setBounds(WIDTH * 77 / 160, 0, 110, HEIGHT * 9 / 288);
    resultPanel.add(tTimeLabel);

    menuPanel = new JPanel();
    menuPanel.setOpaque(false); // 设置面板透明
    menuPanel.setBounds(WIDTH * 12 / 16 + 10, HEIGHT / 9, WIDTH * 4 / 16 - 10, HEIGHT * 8 / 9 - 5);
    menuPanel.setBackground(new Color(255, 250, 250));
    dataFrame.getContentPane().add(menuPanel);
    menuPanel.setLayout(null);
    // 获取当前面板长和高
    final int MW = menuPanel.getWidth();
    final int MH = menuPanel.getHeight();

    menuBar = new JMenuBar();
    menuBar.setOpaque(false);
    menuBar.setBounds(0, 0, MW * 6 / 16 + 5, MH / 35);
    menuBar.setForeground(Color.MAGENTA);
    menuBar.setBackground(new Color(255, 255, 204));
    menuPanel.add(menuBar);

    fileMenu = new JMenu("文件");
    fileMenu.setForeground(new Color(102, 153, 255));
    fileMenu.setIcon(new ImageIcon(DataView.class.getResource("/com/sun/java/swing/plaf/motif/icons/TreeOpen.gif")));
    fileMenu.setFont(new Font("等线", Font.PLAIN, 14));
    // fileMenu.setBounds(0, 0, MW/8, MH/36);
    menuBar.add(fileMenu);

    exitSysMnItm = new JMenuItem("退出系统");
    exitSysMnItm.setForeground(new Color(0, 0, 0));
    exitSysMnItm.setFont(new Font("等线", Font.PLAIN, 14));
    exitSysMnItm.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent e) {
        int num = JOptionPane.showConfirmDialog(null, "确认退出？", "提示", JOptionPane.YES_NO_OPTION);
        if (num == JOptionPane.YES_OPTION) {
          try {
            onClosing();
          } catch(Exception ex) {
            LOGGER.log(Level.INFO, ex.getMessage());
          }finally {
            System.exit(0);
          }
        }
      }
    });

    openModelMnItm = new JMenuItem("打开机型");
    openModelMnItm.setForeground(new Color(0, 0, 0));
    openModelMnItm.setFont(new Font("等线", Font.PLAIN, 14));
    fileMenu.add(openModelMnItm);

    userManageMnItm = new JMenuItem("用户管理");
    userManageMnItm.setForeground(new Color(0, 0, 0));
    userManageMnItm.setFont(new Font("等线", Font.PLAIN, 14));
    fileMenu.add(userManageMnItm);

    logoutMnItm = new JMenuItem("注销用户");
    logoutMnItm.setForeground(new Color(0, 0, 0));
    logoutMnItm.setFont(new Font("等线", Font.PLAIN, 14));
    fileMenu.add(logoutMnItm);
    fileMenu.add(exitSysMnItm);

    editMenu = new JMenu("编辑");
    editMenu.setForeground(new Color(102, 153, 255));
    editMenu.setFont(new Font("等线", Font.PLAIN, 14));
    // editMenu.setBounds(0, MW/8, MW/8, MH/36);
    menuBar.add(editMenu);

    debugToolMnItm = new JMenuItem("调试工具");
    debugToolMnItm.setForeground(new Color(0, 0, 0));
    debugToolMnItm.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent e) {
        if (userName.equals("admin"))
          USBHelperTool.getUsbHelperTool();
        else
          JOptionPane.showMessageDialog(null, "你没有权限操作", "警告", JOptionPane.WARNING_MESSAGE);
      }
    });
    debugToolMnItm.setFont(new Font("等线", Font.PLAIN, 14));
    editMenu.add(debugToolMnItm);

    testOptionMnItm = new JMenuItem("测试选项");
    testOptionMnItm.setForeground(new Color(0, 0, 0));
    testOptionMnItm.setFont(new Font("等线", Font.PLAIN, 14));
    editMenu.add(testOptionMnItm);

    viewReportMnItm = new JMenuItem("查看测试报告");
    viewReportMnItm.setForeground(new Color(0, 0, 0));
    viewReportMnItm.setFont(new Font("等线", Font.PLAIN, 14));
    editMenu.add(viewReportMnItm);

    viewResultMnItm = new JMenuItem("查看测试结果");
    viewResultMnItm.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent e) {
        if (userName.equals("admin")) {
          ViewResult.getViewResult();
          // initCountAndPieChart();
        } else
          JOptionPane.showMessageDialog(null, "你没有权限操作", "警告", JOptionPane.WARNING_MESSAGE);
      }
    });
    viewResultMnItm.setForeground(new Color(0, 0, 0));
    viewResultMnItm.setFont(new Font("等线", Font.PLAIN, 14));
    editMenu.add(viewResultMnItm);

    systemMenu = new JMenu("系统");
    systemMenu.setForeground(new Color(102, 153, 255));
    systemMenu.setFont(new Font("等线", Font.PLAIN, 14));
    // systemMenu.setBounds(0, MW*2/8, MW/8, MH/36);
    menuBar.add(systemMenu);

    aboutMnItm = new JMenuItem("About");
    aboutMnItm.setForeground(new Color(0, 0, 0));
    aboutMnItm.setFont(new Font("等线", Font.PLAIN, 14));
    systemMenu.add(aboutMnItm);

    helpMnItm = new JMenuItem("Help");
    helpMnItm.setForeground(new Color(0, 0, 0));
    helpMnItm.setFont(new Font("等线", Font.PLAIN, 14));
    systemMenu.add(helpMnItm);

    signUpMnItm = new JMenuItem("软件注册");
    signUpMnItm.setForeground(new Color(0, 0, 0));
    signUpMnItm.setFont(new Font("等线", Font.PLAIN, 14));
    systemMenu.add(signUpMnItm);

    updateMnItm = new JMenuItem("软件更新");
    updateMnItm.setForeground(new Color(0, 0, 0));
    updateMnItm.setFont(new Font("等线", Font.PLAIN, 14));
    systemMenu.add(updateMnItm);

    timeLabel = new JLabel();
    timeLabel.setText("我是时间标签");
    timeLabel.setForeground(Color.CYAN);
    timeLabel.setFont(new Font("等线", Font.BOLD | Font.ITALIC, 14));
    timeLabel.setBounds(MW / 2, MH * 31 / 32 - 10, MW / 2, MH / 32);
    menuPanel.add(timeLabel);

    pieChart = new MyPieChart(okCount, ngCount);
    chartPanel = pieChart.getChartPanel();
    chartPanel.setOpaque(false);
    // chartPanel.setBounds(0, MH * 11 / 32 - 20, MW - 10, MH * 20 / 32);
    chartPanel.setBounds(0, HEIGHT * 9 / 72 + 5, MW - 10, HEIGHT * 42 / 72);
    menuPanel.add(chartPanel);

    initCountAndPieChart();

    // 实时刷新时间
    new Thread() {
      public void run() {
        while (true) {
          try {
            Thread.sleep(1000);
          } catch (Exception e) {
            e.printStackTrace();
          }
          Date date = new Date();
          SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd   HH:mm:ss");
          timeLabel.setText(sdf.format(date));
        }
      }
    }.start();
    
    runPanel = new JPanel();
    runPanel.setOpaque(false);
    runPanel.setForeground(new Color(0, 204, 0));
    runPanel.setBounds(WIDTH * 10 / 16 + 5, HEIGHT / 9, WIDTH * 2 / 16, HEIGHT * 51 / 72 + 5);
    runPanel.setBackground(new Color(255, 255, 255));
    dataFrame.getContentPane().add(runPanel);
    runPanel.setLayout(null);

    txtStop = new JTextField();
    // txtStop.setOpaque(false);
    txtStop.setEditable(false);
    txtStop.setText("STOP");
    txtStop.setHorizontalAlignment(SwingConstants.CENTER);
    txtStop.setFont(new Font("Consolas", Font.BOLD | Font.ITALIC, 70));
    txtStop.setBounds(0, 0, WIDTH * 2 / 16, HEIGHT * 9 / 72);
    txtStop.setBackground(new Color(255, 255, 0));
    runPanel.add(txtStop);
    txtStop.setColumns(10);

    progressBar = new JProgressBar();
    progressBar.setMaximum(100);
    progressBar.setMinimum(0);
    progressBar.setValue(0);
    progressBar.setOpaque(false);
    progressBar.setForeground(new Color(0, 204, 51));
    progressBar.setStringPainted(true);
    progressBar.setBounds(0, HEIGHT * 9 / 72 + 5, WIDTH * 2 / 16, HEIGHT * 2 / 72);
    runPanel.add(progressBar);

    textField13 = new JTextField();
    textField13.setEditable(false);
    textField13.setForeground(Color.CYAN);
    textField13.setFont(new Font("等线", Font.PLAIN, 14));
    textField13.setBounds(WIDTH / 32, HEIGHT * 51 / 216 + 5, WIDTH / 16, HEIGHT * 2 / 72);
    runPanel.add(textField13);
    textField13.setColumns(10);
    textField13.setOpaque(false);
    textField13.setText("0");

    textField16 = new JTextField();
    textField16.setText("0");
    textField16.setEditable(false);
    textField16.setForeground(Color.CYAN);
    textField16.setFont(new Font("等线", Font.PLAIN, 14));
    textField16.setColumns(10);
    textField16.setBounds(WIDTH / 32, HEIGHT * 57 / 216 + 15, WIDTH / 16, HEIGHT * 2 / 72);
    runPanel.add(textField16);
    textField16.setOpaque(false);
    //textField16.setVisible(false);
    
    picLabel = new JLabel(picImage);
    picLabel.setBounds(0, (HEIGHT * 51 / 72 + 5)/2, WIDTH*2/16, (HEIGHT * 51 / 72 + 5)/2);
    runPanel.add(picLabel);

    serialPanel = new JPanel();
    serialPanel.setOpaque(false);
    serialPanel.setBounds(0, HEIGHT * 60 / 72, WIDTH * 12 / 16 + 5, HEIGHT * 12 / 72 - 5);
    serialPanel.setBackground(new Color(255, 228, 225));
    dataFrame.getContentPane().add(serialPanel);
    serialPanel.setLayout(null);
    // 获取该面板长高，以方便布局
    final int SW = serialPanel.getWidth();
    final int SH = serialPanel.getHeight();

    COM1Butt = new JButton("COM1");
    COM1Butt.setBackground(new Color(255, 228, 225));
    COM1Butt.setBounds(SW * 2 / 42, SH / 8, SW / 14, SH / 8);
    serialPanel.add(COM1Butt);

    COM2Butt = new JButton("COM2");
    COM2Butt.setBackground(new Color(255, 228, 225));
    COM2Butt.setBounds(SW * 7 / 42, SH / 8, SW / 14, SH / 8);
    serialPanel.add(COM2Butt);

    COM3Butt = new JButton("COM3");
    COM3Butt.setBackground(new Color(255, 228, 225));
    COM3Butt.setBounds(SW * 12 / 42, SH / 8, SW / 14, SH / 8);
    serialPanel.add(COM3Butt);

    COM4Butt = new JButton("COM4");
    COM4Butt.setBackground(new Color(255, 228, 225));
    COM4Butt.setBounds(SW * 17 / 42, SH / 8, SW / 14, SH / 8);
    serialPanel.add(COM4Butt);

    COM5Butt = new JButton("COM5");
    COM5Butt.setBackground(new Color(255, 228, 225));
    COM5Butt.setBounds(SW * 22 / 42, SH / 8, SW / 14, SH / 8);
    serialPanel.add(COM5Butt);

    COM6Butt = new JButton("COM6");
    COM6Butt.setBackground(new Color(255, 228, 225));
    COM6Butt.setBounds(SW * 27 / 42, SH / 8, SW / 14, SH / 8);
    serialPanel.add(COM6Butt);

    COM7Butt = new JButton("COM7");
    COM7Butt.setBackground(new Color(255, 228, 225));
    COM7Butt.setBounds(SW * 32 / 42, SH / 8, SW / 14, SH / 8);
    serialPanel.add(COM7Butt);

    COM8Butt = new JButton("COM8");
    COM8Butt.setBackground(new Color(255, 228, 225));
    COM8Butt.setBounds(SW * 37 / 42, SH / 8, SW / 14, SH / 8);
    serialPanel.add(COM8Butt);

    nyPanel = new JPanel();
    nyPanel.setOpaque(false);
    nyPanel.setBackground(new Color(0, 51, 204));
    nyPanel.setBounds(15, SH * 13 / 24, SW - 30, SH / 3);
    serialPanel.add(nyPanel);
    nyPanel.setLayout(null);

    pNameLabel = new JLabel("产品型号: C211");
    pNameLabel.setFont(new Font("等线", Font.BOLD, 30));
    pNameLabel.setForeground(new Color(204, 102, 255));
    pNameLabel.setBackground(new Color(0, 51, 204));
    pNameLabel.setBounds(0, 0, SW / 2, SH / 3);
    nyPanel.add(pNameLabel);

    nayinCbox = new JCheckBox("捺印");
    nayinCbox.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent e) {
        // 由于要触发事件，复选框状态定会改变，故而多加了判断
        JPasswordField pw = new JPasswordField();
        pw.setEchoChar('*');
        JOptionPane.showMessageDialog(null, pw, "请输入捺印密码", JOptionPane.PLAIN_MESSAGE);
        char[] pass = pw.getPassword();
        if (pass.length > 0 && pass.length <= 6) {
          if (String.valueOf(pass).equals("NY2018")) {
            if (nayinCbox.isSelected()) {
              nayinCbox.setSelected(true);
            } else
              nayinCbox.setSelected(false);
          } else {
            JOptionPane.showMessageDialog(null, "密码错误！");
            if (nayinCbox.isSelected()) {
              nayinCbox.setSelected(false);
            } else
              nayinCbox.setSelected(true);
          }
        } else {
          JOptionPane.showMessageDialog(null, "密码长度为6位！");
          if (nayinCbox.isSelected()) {
            nayinCbox.setSelected(false);
          } else
            nayinCbox.setSelected(true);
        }
        ifProductPrint();
      }
    });
    nayinCbox.setOpaque(false);
    nayinCbox.setForeground(new Color(204, 102, 255));
    nayinCbox.setSelected(true);
    nayinCbox.setFont(new Font("等线", Font.PLAIN, 18));
    nayinCbox.setBounds(SW / 2, SH / 12, SW / 8, SH / 6);
    nyPanel.add(nayinCbox);

    spotTestCbox = new JCheckBox("点测");
    spotTestCbox.setOpaque(false);
    spotTestCbox.setForeground(new Color(204, 102, 255));
    spotTestCbox.setFont(new Font("等线", Font.PLAIN, 18));
    spotTestCbox.setBounds(SW * 5 / 8, SH / 12, SW / 8, SH / 6);
    nyPanel.add(spotTestCbox);

    // JTable table = new JTable(17, 10);
    // table.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);//关闭表格列自动调整，此时水平滚动条可见
    table = completedTable(getTestTable());
    scrollPane = new JScrollPane(table); // 向滚动面板中添加JTable
    // scrollPane.setOpaque(false);
    // scrollPane.getViewport().setOpaque(false); //设置为透明
    scrollPane.setBounds(10, HEIGHT * 17 / 72 + 5, WIDTH * 10 / 16 - 10, HEIGHT * 42 / 72);
    dataFrame.getContentPane().add(scrollPane);
    
  }

  /**
   * 创建JTable方法
   * 
   * @return
   */
  public JTable getTestTable() {
    Vector<Object> rowNum = null, colNum = null;
    // 创建列对象
    colNum = new Vector<>();
    colNum.add("");
    colNum.add("序号");
    colNum.add("测试项目");
    colNum.add("上限");
    colNum.add("下限");
    colNum.add("测试值");
    colNum.add("单位");
    colNum.add("测试结果");
    colNum.add("备注");
    colNum.add("Column1");
    colNum.add("Column2");
    colNum.add("Column3");
    colNum.add("Column4");

    // 创建行对象
    rowNum = new Vector<>();
    List<C211Data> tableList = C211DataTools.getAllbyDb(); // 从数据库中获取c211表的内容
    for (Iterator<C211Data> i = tableList.iterator(); i.hasNext();) {
      C211Data rd = i.next();
      Vector<String> vt = new Vector<>();
      vt.add("");
      vt.add(rd.getPdxuhao());
      vt.add(rd.getTestitem());
      vt.add(rd.getMaxvalue());
      vt.add(rd.getMinvalue());
      vt.add(rd.getTestvalue());
      vt.add(rd.getDanwei());
      vt.add(rd.getTestresult());

      rowNum.add(vt);
    }

    JTable table = new JTable(rowNum, colNum);

    return table;
  }

  /**
   * 提供设置JTable方法
   * 
   * @param table
   * @return
   */
  public JTable completedTable(JTable table) {

    // table.setOpaque(false); //设置表透明
    JTableHeader jTableHeader = table.getTableHeader(); // 获取表头
    // 设置表头名称字体样式
    jTableHeader.setFont(new Font("", Font.PLAIN, 14));
    // 设置表头名称字体颜色
    jTableHeader.setForeground(Color.BLACK);
    // 表头不可拖动
    jTableHeader.setReorderingAllowed(false);
    // 列大小不可改变
    jTableHeader.setResizingAllowed(false);

    // 设置列宽
    TableColumn colNull = table.getColumnModel().getColumn(0);
    TableColumn colTestitem = table.getColumnModel().getColumn(2);
    TableColumn colMaxvalue = table.getColumnModel().getColumn(3);
    TableColumn colMinvalue = table.getColumnModel().getColumn(4);
    TableColumn colTestvalue = table.getColumnModel().getColumn(5);
    TableColumn colTestResult = table.getColumnModel().getColumn(7);
    colNull.setPreferredWidth(20);
    colTestitem.setPreferredWidth(150);
    colMaxvalue.setPreferredWidth(120);
    colMinvalue.setPreferredWidth(120);
    colTestvalue.setPreferredWidth(120);
    colTestResult.setPreferredWidth(120);

    table.setEnabled(false); // 内容不可编辑
    DefaultTableCellRenderer r = new DefaultTableCellRenderer(); // 设置
    r.setHorizontalAlignment(JLabel.CENTER); // 单元格内容
    table.setDefaultRenderer(Object.class, r); // 居中显示

    table.setRowHeight(27); // 设置行高
    // 增加一行空白行
    // AbstractTableModel tableModel = (AbstractTableModel) table.getModel();
    DefaultTableModel tableModel = (DefaultTableModel) table.getModel();
    tableModel.addRow(new Object[] { "*", "", "", "", "", "", "", "", "", "", "", "", "" });
    // table.setGridColor(Color.PINK); //设置网格颜色
    table.setForeground(Color.BLUE); // 设置文字颜色
    table.setFont(new Font("", Font.PLAIN, 12));
    table.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);// 关闭表格列自动调整

    return table;
  }

  /**
   * 初始化table值
   */
  public void initTable() {
    for (int i = 1; i <= 14; i++) {
      table.setValueAt("?", i, 5); // 清空测试值
      table.setValueAt("?", i, 7); // 清空测试结果
    }
  }
  /**
   * 设置测试值
   * 
   * @param row
   *          行数
   * @param value
   *          值
   */
  public void setValueAt(int row, double value) {
    table.setValueAt(value, row, 5);
  }

  /**
   * 重载测试值设置方法
   * 
   * @param row
   * @param value
   */
  public void setValueAt(int row, String value) {
    table.setValueAt(value, row, 5);
  }

  /**
   * 获取表中row(1~14)行的测试值
   * 
   * @param row
   *          行数
   * @return Double类型的数值
   */
  public double getValueAt(int row) {

    if (!table.getValueAt(row, 5).toString().equals("?")) {
      return Double.parseDouble(table.getValueAt(row, 5).toString());
    } else
      return -1;
  }
  /**
   * 判断表中测试值列和测试结果列中是否包含问号
   * @return 如果没有，返回true
   */
  public boolean hasQuestionMark() {
    for(int i = 1; i <= 14; i++) {
      if(table.getValueAt(i, 5).equals("?") || table.getValueAt(i, 7).equals("?")) {
        return false;
      }
    }
    return true;
  }
  
  /**
   * 判断表中row(1~14)行的测试结果是否为"PASS"
   * 
   * @param row
   *          行数
   * @return true or false
   */
  public boolean resultOkAtRow(int row) {
    if (table.getValueAt(row, 7).toString().equals("PASS")) {
      return true;
    } else
      return false;
  }

  /**
   * 根据测试值判断测试结果是否PASS
   * 
   * @param row
   *          行数
   */
  public void setResValueAtRow(int row) {
    switch (row) {
    case 1: {
      if (getValueAt(1) <= 1d)
        setTableTestResultPASS(1);
      else
        setTableTestResultNG(1);
    }
      break;
    case 2: {
      if (getValueAt(2) <= 13.5d && getValueAt(2) >= 13d)
        setTableTestResultPASS(2);
      else
        setTableTestResultNG(2);
    }
      break;
    case 3: {
      if (getValueAt(3) <= 3.50d && getValueAt(3) >= 2.50d)
        setTableTestResultPASS(3);
      else
        setTableTestResultNG(3);
    }
      break;
    case 4: {
      if (getValueAt(4) <= 2.2d && getValueAt(4) >= 1.6d)
        setTableTestResultPASS(4);
      else
        setTableTestResultNG(4);
    }
      break;
    case 5: {
      if (getValueAt(5) <= 2024.4d && getValueAt(5) >= 1983.6d)
        setTableTestResultPASS(5);
      else
        setTableTestResultNG(5);
    }
      break;
    case 6: {
      if (getValueAt(6) <= 4.7d && getValueAt(6) >= 4.3d)
        setTableTestResultPASS(6);
      else
        setTableTestResultNG(6);
    }
      break;
    case 7: {
      if (getValueAt(7) <= 3.50d && getValueAt(7) >= 2.50d)
        setTableTestResultPASS(7);
      else
        setTableTestResultNG(7);
    }
      break;
    case 8: {
      if (getValueAt(8) <= 2.2d && getValueAt(8) >= 1.6d)
        setTableTestResultPASS(8);
      else
        setTableTestResultNG(8);
    }
      break;
    case 9: {
      if (getValueAt(9) <= 640.34d && getValueAt(9) >= 627.66d)
        setTableTestResultPASS(9);
      else
        setTableTestResultNG(9);
    }
      break;
    case 10: {
      if (getValueAt(10) <= 8.5d && getValueAt(10) >= 8d)
        setTableTestResultPASS(10);
      else
        setTableTestResultNG(10);
    }
      break;
    case 11: {
      if (getValueAt(11) <= 3.50d && getValueAt(11) >= 2.50d)
        setTableTestResultPASS(11);
      else
        setTableTestResultNG(11);
    }
      break;
    case 12: {
      if (getValueAt(12) <= 2.2d && getValueAt(12) >= 1.6d)
        setTableTestResultPASS(12);
      else
        setTableTestResultNG(12);
    }
      break;
    case 13: {
      if (table.getValueAt(13, 5).toString().equals("ok"))
        setTableTestResultPASS(13);
      else
        setTableTestResultNG(13);
    }
      break;
    case 14: {
      if (table.getValueAt(14, 5).toString().equals("ok"))
        setTableTestResultPASS(14);
      else
        setTableTestResultNG(14);
    }
      break;
    }
  }
  /**
   * 初始化串口
   */
  public void initPort() {
    ArrayList<String> port = SerialPortTool.findPort();
    if (!port.contains("COM1")) {
      JOptionPane.showMessageDialog(null, "未发现串口1");
      COM1Butt.setBackground(Color.BLACK);
    } else {
      try {
        COM1 = SerialPortTool.getPort(1);
        s1ok = comWrite(COM1, "AA");
        COM1Butt.setBackground(Color.RED);
        listener.add(COM1, listener.COM1Listener());
      } catch (SerialPortParamFail e) {
        JOptionPane.showMessageDialog(null, "COM1:" + e.toString());
      } catch (NotASerialPort e) {
        JOptionPane.showMessageDialog(null, "COM1:" + e.toString());
      } catch (NoSuchPort e) {
        JOptionPane.showMessageDialog(null, "COM1:" + e.toString());
      } catch (PortInUse e) {
        JOptionPane.showMessageDialog(null, "COM1:" + e.toString());
      } catch (TooManyListeners e) {
        JOptionPane.showMessageDialog(null, "COM1:" + e.toString());
      }
    }
    if (!port.contains("COM2")) {
      JOptionPane.showMessageDialog(null, "未发现串口2");
      COM2Butt.setBackground(Color.BLACK);
    } else {
      try {
        COM2 = SerialPortTool.getPort(2);
        s2ok = comWrite(COM2, "BB");
        COM2Butt.setBackground(Color.RED);
        listener.add(COM2, listener.COM2Listener());
      } catch (SerialPortParamFail e) {
        JOptionPane.showMessageDialog(null, "COM2:" + e.toString());
      } catch (NotASerialPort e) {
        JOptionPane.showMessageDialog(null, "COM2:" + e.toString());
      } catch (NoSuchPort e) {
        JOptionPane.showMessageDialog(null, "COM2:" + e.toString());
      } catch (PortInUse e) {
        JOptionPane.showMessageDialog(null, "COM2:" + e.toString());
      } catch (TooManyListeners e) {
        JOptionPane.showMessageDialog(null, "COM2:" + e.toString());
      }
    }
    if (!port.contains("COM3")) {
      JOptionPane.showMessageDialog(null, "未发现串口3");
      COM3Butt.setBackground(Color.BLACK);
    } else {
      try {
        COM3 = SerialPortTool.getPort(3);
        s3ok = comWrite(COM3, "AA");
        COM3Butt.setBackground(Color.RED);
        listener.add(COM3, listener.COM3Listener());
      } catch (SerialPortParamFail e) {
        JOptionPane.showMessageDialog(null, "COM3:" + e.toString());
      } catch (NotASerialPort e) {
        JOptionPane.showMessageDialog(null, "COM3:" + e.toString());
      } catch (NoSuchPort e) {
        JOptionPane.showMessageDialog(null, "COM3:" + e.toString());
      } catch (PortInUse e) {
        JOptionPane.showMessageDialog(null, "COM3:" + e.toString());
      } catch (TooManyListeners e) {
        JOptionPane.showMessageDialog(null, "COM3:" + e.toString());
      }
    }
    if (!port.contains("COM4")) {
      JOptionPane.showMessageDialog(null, "未发现串口4");
      COM4Butt.setBackground(Color.BLACK);
    } else {
      try {
        COM4 = SerialPortTool.getPort(4);
        s4ok = comWrite(COM4, "BB");
        COM4Butt.setBackground(Color.RED);
        listener.add(COM4, listener.COM4Listener());
      } catch (SerialPortParamFail e) {
        JOptionPane.showMessageDialog(null, "COM4:" + e.toString());
      } catch (NotASerialPort e) {
        JOptionPane.showMessageDialog(null, "COM4:" + e.toString());
      } catch (NoSuchPort e) {
        JOptionPane.showMessageDialog(null, "COM4:" + e.toString());
      } catch (PortInUse e) {
        JOptionPane.showMessageDialog(null, "COM4:" + e.toString());
      } catch (TooManyListeners e) {
        JOptionPane.showMessageDialog(null, "COM4:" + e.toString());
      }
    }
    if (!port.contains("COM7")) {
      JOptionPane.showMessageDialog(null, "未发现串口7");
      COM7Butt.setBackground(Color.BLACK);
    } else {
      try {
        COM7 = SerialPortTool.getPort(5);
        s7ok = comWrite(COM7, "AA");
        COM7Butt.setBackground(Color.RED);
        listener.add(COM7, listener.COM7Listener());
      } catch (SerialPortParamFail e) {
        JOptionPane.showMessageDialog(null, "COM7:" + e.toString());
      } catch (NotASerialPort e) {
        JOptionPane.showMessageDialog(null, "COM7:" + e.toString());
      } catch (NoSuchPort e) {
        JOptionPane.showMessageDialog(null, "COM7:" + e.toString());
      } catch (PortInUse e) {
        JOptionPane.showMessageDialog(null, "COM7:" + e.toString());
      } catch (TooManyListeners e) {
        JOptionPane.showMessageDialog(null, "COM7:" + e.toString());
      }
    }
    if (!port.contains("COM8")) {
      JOptionPane.showMessageDialog(null, "未发现串口8");
      COM8Butt.setBackground(Color.BLACK);
    } else {
      try {
        COM8 = SerialPortTool.getPort(6);
        s8ok = comWrite(COM8, "BB");
        COM8Butt.setBackground(Color.RED);
        listener.add(COM8, listener.COM8Listener());
      } catch (SerialPortParamFail e) {
        JOptionPane.showMessageDialog(null, "COM8:" + e.toString());
      } catch (NotASerialPort e) {
        JOptionPane.showMessageDialog(null, "COM8:" + e.toString());
      } catch (NoSuchPort e) {
        JOptionPane.showMessageDialog(null, "COM8:" + e.toString());
      } catch (PortInUse e) {
        JOptionPane.showMessageDialog(null, "COM8:" + e.toString());
      } catch (TooManyListeners e) {
        JOptionPane.showMessageDialog(null, "COM8:" + e.toString());
      }
    }
    ArrayList<DeviceTreeNode> installedDevice_Di = instantDiCtrl.getSupportedDevices();
    ArrayList<DeviceTreeNode> installedDevice_Do = instantDiCtrl.getSupportedDevices();
    try {
      instantDiCtrl.setSelectedDevice(new DeviceInformation(installedDevice_Di.get(0).toString()));
      instantDoCtrl.setSelectedDevice(new DeviceInformation(installedDevice_Do.get(0).toString()));
      openMultimeter();
      Thread.sleep(100);
    } catch (InterruptedException e) {
      JOptionPane.showMessageDialog(null, "port:" + e.getMessage());
    } catch(DaqException e) {
      JOptionPane.showMessageDialog(null, "PCI:" + e.getMessage());
    }
    if (s1ok && s2ok && s3ok && s4ok && s7ok && s8ok) {
      setTxtStopFieldWAIT();
    }
  }

  /**
   * 串口发送数据(字符数组)
   * 
   * @param com
   *          串口对象
   * @param datas
   *          待发送数据
   * @return
   */
  public boolean comWrite(SerialPort com, char[] datas) {
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

  /**
   * 重载串口发送数据(16进制字符串)
   * 
   * @param com
   *          串口对象
   * @param hexString
   *          16进制字符串
   * @return
   */
  public boolean comWrite(SerialPort com, String hexString) {
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
  /**
   * 判断传回的字节值的16进制字符串是否与特定值相同
   * 
   * @param hex
   *          十六进制字节值
   * @param data
   *          特定十六进制字符串
   * @return 相等则返回true
   */
  public static boolean isEquals(byte hex, String data) {
    String s1 = String.format("%02x", hex);
    if (s1.equals(data))
      return true;
    else
      return false;
  }
  /**
   * 初始化界面计数值和饼图
   */
  public void initCountAndPieChart() {
    if (getRecorddata() != null) {
      okCount = getOkCount();
      ngCount = getNgCount();
      totalCount = getTotalCount();
      timeCount = getTimeCount();
      okField.setText(okCount + "");
      ngField.setText(ngCount + "");
      totalField.setText(totalCount + "");
      tTimeField.setText(timeCount + "");
      setPieChart(okCount, ngCount);
    } 
    else {
      okCount = 0;
      ngCount = 0;
      totalCount = 0;
      timeCount = 0;
      okField.setText(okCount + "");
      ngField.setText(ngCount + "");
      totalField.setText(totalCount + "");
      tTimeField.setText(timeCount + "");
      setPieChart(okCount, ngCount);
    }
  }
  /**
   * 刷新ok框数值
   */
  public void setOkFieldCount() {
    okCount++;
    okField.setText(okCount + "");
    setPieChart(okCount, ngCount);
    setTotalFieldCount();
  }

  /**
   * 刷新ng框数值
   */
  public void setNgFieldCount() {
    ngCount++;
    ngField.setText(ngCount + "");
    setPieChart(okCount, ngCount);
    setTotalFieldCount();
  }

  /**
   * 刷新总值
   */
  public void setTotalFieldCount() {
    totalCount = okCount + ngCount;
    totalField.setText(totalCount + "");
    addRecord();
  }
  /**
   * table渲染色，测试结果为"PASS"则设为绿色，"NG"为红色
   */
  public void setTableCellRenderer() {
    if (myTableCellRenderrer == null) {
      myTableCellRenderrer = new MyTableCellRenderrer();
      table.getColumnModel().getColumn(7).setCellRenderer(myTableCellRenderrer);
    } else
      table.getColumnModel().getColumn(7).setCellRenderer(myTableCellRenderrer);
  }

  public void setTxtStopFieldWAIT() {
    txtStop.setText("WAIT");
    txtStop.setBackground(new Color(255, 255, 0));
  }
  /**
   * 设置运行状态框为NG
   * @throws DaqException 
   */
  public void setTxtStopFieldNG() {
    txtStop.setText("NG");
    txtStop.setBackground(Color.RED);
    setNgFieldCount();
    PLCWarmming();
  }

  /**
   * 设置运行状态框为PASS
   */
  public void setTxtStopFieldPASS() {
    txtStop.setText("PASS");
    txtStop.setBackground(new Color(0, 204, 51));
    setOkFieldCount();
  }

  /**
   * 设置运行状态框为STOP
   */
  public void setTxtStopFieldSTOP() {
    txtStop.setText("STOP");
    txtStop.setBackground(new Color(255, 255, 0));
  }

  /**
   * 设置运行状态框为RUN
   */
  public void setTxtStopFieldRUN() {
    txtStop.setText("RUN");
    txtStop.setBackground(new Color(255, 255, 0));
  }
  /**
   * 设置表测试结果为PASS
   * 
   * @param i
   *          第几行(1~14)
   */
  public void setTableTestResultPASS(int i) {
    table.setValueAt("PASS", i, 7);
    setTableCellRenderer();
  }

  /**
   * 设置表测试结果为NG
   * 
   * @param i
   *          第几行(1~14)
   * @throws DaqException 
   */
  public void setTableTestResultNG(int i) {
    //IFNGBOOL = true;
    table.setValueAt("NG", i, 7);
    setTableCellRenderer();
    if (!spotTestCbox.isSelected()) {
      setTxtStopFieldNG();
    }
  }

  /**
   * 刷新饼图的方法
   * 
   * @param ok
   * @param ng
   */
  public void setPieChart(int ok, int ng) {
    piePlot = pieChart.getPiePlot();
    piePlot.setDataset(MyPieChart.getDataSet(ok, ng));
    piePlot.setSectionPaint("良品", new Color(0, 204, 51));
    piePlot.setSectionPaint("不良", Color.RED);
  }

  /**
   * 往recordtddata表中插入当天测试数据
   */
  public void addRecord() {
    LocalDate now = LocalDate.now();
    String[] str = { "C211", totalField.getText(), okField.getText(), ngField.getText(), tTimeField.getText(),
        now.toString() };
    
    try {
      List<Recorddata> list = ECTESTSYSTools.getRecordtdData(now.toString(), "C211");
      if(list.size() != 0) {
        String sql = "update recordtd set recordname='"+ str[0] +"',recordsum='"+str[1]+"',recordok='"+str[2]+"',"
            + "recordng='"+str[3]+"',recordts='"+str[4]+"',recordtime='"+str[5]+"' where recordtime='"+str[5]+"'";
        ECTESTSYSTools.updateRecord(sql);
      }
      else 
        ECTESTSYSTools.addrecord(str);
      //ECTESTSYSTools.deleterecord(now.toString(), "C211");
      //ECTESTSYSTools.addrecord(str);
    } catch (Exception e) {
      JOptionPane.showMessageDialog(null, e.getMessage());
    }
  }

  /**
   * 获取数据库里当天存的数据
   * 
   * @return
   */
  public Recorddata getRecorddata() {
    List<Recorddata> list = ECTESTSYSTools.getRecordtdData(LocalDate.now().toString(), "C211");
    Recorddata rd = null;
    for (Iterator<Recorddata> it = list.iterator(); it.hasNext();) {
      rd = it.next();
    }
    return rd;
  }

  public int getOkCount() {
    return Integer.parseInt(getRecorddata().getRecordok());
  }

  public int getNgCount() {
    return Integer.parseInt(getRecorddata().getRecordng());
  }

  public int getTotalCount() {
    return Integer.parseInt(getRecorddata().getRecordsum());
  }
  public int getTimeCount() {
    return Integer.parseInt(getRecorddata().getRecordts());
  }
  /**
   * 结果框显示值为PASS或者点测复选框被选中，判断测试值是否全部通过
   * @return 真假值
   */
  public boolean isPassed() {
    if (txtStop.getText().equals("RUN") || spotTestCbox.isSelected()) {
      if (resultOkAtRow(1) && resultOkAtRow(2) && resultOkAtRow(3) && resultOkAtRow(4) && resultOkAtRow(5)
          && resultOkAtRow(6) && resultOkAtRow(7) && resultOkAtRow(8) && resultOkAtRow(9) && resultOkAtRow(10)
          && resultOkAtRow(11) && resultOkAtRow(12) && resultOkAtRow(13) && resultOkAtRow(14)) {

        return true;
      }
      else
        return false;
    }
    else
      return false;
  }

  /**
   * 发送不良报警给PLC
   * @throws DaqException 
   */
  public void PLCWarmming() {
    pciState = (byte) (pciState | 0x01);
    sendMesToPLCByPCI(0, pciState);
  }

  /**
   * 取消报警
   * @throws DaqException 
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

  /**
   * 绝缘测试
   */
  public void jueyuanTest() {
    pciState = (byte) ((pciState | 0x02) & 0xFB);
    sendMesToPLCByPCI(0, pciState);
  }
  /**
   * 读取端口状态
   * @return
   * @throws InterruptedException
   */
  public byte readPortState() {
    byte portDatas = 0;
    by = new ByteByRef(portDatas);
    ErrorCode err = ErrorCode.Success;
    err = instantDiCtrl.Read(0, by);
    textField16.setText(portDatas + "");
    if (err != ErrorCode.Success) {
      handleError(err);
      try {
        timer1.wait();
      } catch (InterruptedException e) {
        e.printStackTrace();
      }
      return 0;
    }
    return by.value;
  }
  /**
   * 从textField16中提取值，然后发送
   */
  public void sendText16() {
    ErrorCode err = ErrorCode.Success;
    byte data = Byte.parseByte((textField16.getText()));
    err = instantDoCtrl.Write(0, data);
    if (err != ErrorCode.Success) {
      handleError(err);
    }
  }
  public void COM1DatasArrived() {
    try {
      Thread.sleep(5);  //加延时，防止读取数据缺失
      byte[] datas = SerialPortTool.readByte(COM1);
      if(BUT3_SET) {
        for(int i = 0; i < datas.length - 15; i++) {
          if(isEquals(datas[i], "02") && isEquals(datas[i + 1], "30") && isEquals(datas[i + 2], "33")
             && isEquals(datas[i + 3], "31") && isEquals(datas[i + 14], "0d") && isEquals(datas[i + 15], "0a")) {
            
            StringBuilder sb = new StringBuilder();
            sb.append(SerialPortTool.byteAsciiToChar(datas[i+8]));
            sb.append(SerialPortTool.byteAsciiToChar(datas[i + 9]));
            sb.append(SerialPortTool.byteAsciiToChar(datas[i + 10]));
            sb.append(SerialPortTool.byteAsciiToChar(datas[i + 11]));
            int BUT3_result = Integer.parseInt(sb.toString());
            rtimes03++;
            if(rtimes03 > (dataCountOfLi - 1))  rtimes03 = 0;
            mydatap03.set(rtimes03, BUT3_result);
            if(BUT3_result >= 700) {
              BUT3_SET = false;
              printBut3_LL = true;
              rtimes03 = 0;
            }
          }          
        }
      }
    } catch (ReadDataFromSerialFail e) {
      JOptionPane.showMessageDialog(null, e.toString());
    } catch (InputStreamCloseFail e) {
      JOptionPane.showMessageDialog(null, e.toString());
    } catch (InterruptedException e) {
      JOptionPane.showMessageDialog(null, e.toString());
    }
  }
  public void COM2DatasArrived() {
    return;  //保留
  }
  public void COM3DatasArrived() {
    return;  //保留
  }
  public void COM4DatasArriaved() {
    try {
      Thread.sleep(5);  //加延时，防止读取数据丢失
      byte[] datas = SerialPortTool.readByte(COM4);
      for (int i = 0; i < datas.length - 14; i++) {
        //plc start ask    01 10 00 01 00 08 10 00    60 00 81 00 68 00 84 00 79 00 80 00 67 00 62    E7 3C
        if (isEquals(datas[i], "60") && isEquals(datas[i + 1], "00") && isEquals(datas[i + 2], "81")
            && isEquals(datas[i + 3], "00") && isEquals(datas[i + 4], "68") && isEquals(datas[i + 5], "00")
            && isEquals(datas[i + 6], "84") && isEquals(datas[i + 7], "00") && isEquals(datas[i + 8], "79")
            && isEquals(datas[i + 9], "00") && isEquals(datas[i + 10], "80") && isEquals(datas[i + 11], "00")
            && isEquals(datas[i + 12], "67") && isEquals(datas[i + 13], "00") && isEquals(datas[i + 14], "62")) {
          // start---
          if(!txtStop.getText().equals("RUN")) {
            if(ALLDIALOGISSHOW == false) {
              ALLOWTEST_R1 = false;
              ALLOWTEST_R2 = false;
              ALLOWTEST_R3 = false;
              R_testTimes = 0;
              
              openTwoLineR_FUC();
              rtimes01 = 0;
              printBUT1_LL = false;
              printBut2_LL = false;
              printBut3_LL = false;
              BUT1_SET = false;
              BUT2_SET = false;
              BUT3_SET = false;
              for(int ik = 0; ik < dataCountOfLi; ik++) {
                mydatap01.set(ik, 0);
                mydatap02.set(ik, 0);
                mydatap03.set(ik, 0);
              }
              s4sendtimes = 0;
              JUETYANNUM = "";
              testState = "RUN";
              //txtStop.setText(testState);
              setTxtStopFieldRUN();
              outExcel = false;
              cancelWarmming();
              ALLDIALOGISSHOW = true;
              initTable();
              jueyuanTestBool01 = false;
              jueyuanTestBool02 = false;
              ALLjueyuanTestBool01 = false;
              ALLjueyuanTestBool02 = false;
              //IFNGBOOL = false;
              
              xcResult1 = 0;
              xcResult2 = 0;
              xcResult3 = 0;
              
              Thread.sleep(300);
              BUT1_SET = true;
            }
          }

        }
        //plc reset ask  01 10 00 01 00 08 10 00    60 00 83 00 50 00 67 00 83 00 87 00 67 00 62    07 E5 
        else if (isEquals(datas[i], "60") && isEquals(datas[i + 1], "00") && isEquals(datas[i + 2], "83")
            && isEquals(datas[i + 3], "00") && isEquals(datas[i + 4], "50") && isEquals(datas[i + 5], "00")
            && isEquals(datas[i + 6], "67") && isEquals(datas[i + 7], "00") && isEquals(datas[i + 8], "83")
            && isEquals(datas[i + 9], "00") && isEquals(datas[i + 10], "87") && isEquals(datas[i + 11], "00")
            && isEquals(datas[i + 12], "67") && isEquals(datas[i + 13], "00") && isEquals(datas[i + 14], "62")) {
          // start---
          if(ALLDIALOGISSHOW == false) {
            testState = "STOP";
            setTxtStopFieldSTOP();
            cancelWarmming();
            ALLDIALOGISSHOW = true;
          }

        }
        //plc s3wc ask   01 10 00 01 00 08 10 00     60 00 83 00 51 00 67 00 83 00 87 00 67 00 62     03 19
        else if (isEquals(datas[i], "60") && isEquals(datas[i + 1], "00") && isEquals(datas[i + 2], "83")
            && isEquals(datas[i + 3], "00") && isEquals(datas[i + 4], "51") && isEquals(datas[i + 5], "00")
            && isEquals(datas[i + 6], "67") && isEquals(datas[i + 7], "00") && isEquals(datas[i + 8], "83")
            && isEquals(datas[i + 9], "00") && isEquals(datas[i + 10], "87") && isEquals(datas[i + 11], "00")
            && isEquals(datas[i + 12], "67") && isEquals(datas[i + 13], "00") && isEquals(datas[i + 14], "62")) {
          // start---
          if(ALLDIALOGISSHOW == false) {
            testState = "STOP";
            setTxtStopFieldSTOP();
            ALLDIALOGISSHOW = true;
          }

        }
        //plc S1 R test ask     60 00 67 00 83 00 68 00 90 00 83 00 49 00 62
        else if (isEquals(datas[i], "60") && isEquals(datas[i + 1], "00") && isEquals(datas[i + 2], "67")
            && isEquals(datas[i + 3], "00") && isEquals(datas[i + 4], "83") && isEquals(datas[i + 5], "00")
            && isEquals(datas[i + 6], "68") && isEquals(datas[i + 7], "00") && isEquals(datas[i + 8], "90")
            && isEquals(datas[i + 9], "00") && isEquals(datas[i + 10], "83") && isEquals(datas[i + 11], "00")
            && isEquals(datas[i + 12], "49") && isEquals(datas[i + 13], "00") && isEquals(datas[i + 14], "62")) {
          // start---
          if((!txtStop.getText().equals("NG")) && (!txtStop.getText().equals("STOP"))) {
            if(table.getValueAt(1, 5).equals("?")) {
              //openMultimeter();
              openTwoLineR_FUC();
              setR_range("0"); //200欧
              ALLOWTEST_R1 = true;
            }
          }

        }
        //plc S1 V test ask     60 00 67 00 83 00 68 00 89 00 83 00 49 00 62 
        else if (isEquals(datas[i], "60") && isEquals(datas[i + 1], "00") && isEquals(datas[i + 2], "67")
            && isEquals(datas[i + 3], "00") && isEquals(datas[i + 4], "83") && isEquals(datas[i + 5], "00")
            && isEquals(datas[i + 6], "68") && isEquals(datas[i + 7], "00") && isEquals(datas[i + 8], "89")
            && isEquals(datas[i + 9], "00") && isEquals(datas[i + 10], "83") && isEquals(datas[i + 11], "00")
            && isEquals(datas[i + 12], "49") && isEquals(datas[i + 13], "00") && isEquals(datas[i + 14], "62")) {
          // start---
          if((!txtStop.getText().equals("NG")) && (!txtStop.getText().equals("STOP"))) {
            if(table.getValueAt(2, 5).equals("?")) {
              //openMultimeter();
              openDCvoltage_FUC();
              setV_range("2"); //20v
              V_measure(2);
            }
            BUT2_SET = true;
          }

        }
        //plc S2 R test ask     60 00 67 00 83 00 68 00 90 00 83 00 50 00 62
        else if (isEquals(datas[i], "60") && isEquals(datas[i + 1], "00") && isEquals(datas[i + 2], "67")
            && isEquals(datas[i + 3], "00") && isEquals(datas[i + 4], "83") && isEquals(datas[i + 5], "00")
            && isEquals(datas[i + 6], "68") && isEquals(datas[i + 7], "00") && isEquals(datas[i + 8], "90")
            && isEquals(datas[i + 9], "00") && isEquals(datas[i + 10], "83") && isEquals(datas[i + 11], "00")
            && isEquals(datas[i + 12], "50") && isEquals(datas[i + 13], "00") && isEquals(datas[i + 14], "62")) {
          // start---
          if((!txtStop.getText().equals("NG")) && (!txtStop.getText().equals("STOP"))) {
            if(table.getValueAt(5, 5).equals("?")) {
              //openMultimeter();
              openTwoLineR_FUC();
              setR_range("1"); //2000欧
              ALLOWTEST_R2 = true;
            }
          }

        }
        //plc S2 V test ask     60 00 67 00 83 00 68 00 89 00 83 00 50 00 62
        else if (isEquals(datas[i], "60") && isEquals(datas[i + 1], "00") && isEquals(datas[i + 2], "67")
            && isEquals(datas[i + 3], "00") && isEquals(datas[i + 4], "83") && isEquals(datas[i + 5], "00")
            && isEquals(datas[i + 6], "68") && isEquals(datas[i + 7], "00") && isEquals(datas[i + 8], "89")
            && isEquals(datas[i + 9], "00") && isEquals(datas[i + 10], "83") && isEquals(datas[i + 11], "00")
            && isEquals(datas[i + 12], "50") && isEquals(datas[i + 13], "00") && isEquals(datas[i + 14], "62")) {
          // start---
          if((!txtStop.getText().equals("NG")) && (!txtStop.getText().equals("STOP"))) {
            if(table.getValueAt(6, 5).equals("?")) {
              //openMultimeter();
              openDCvoltage_FUC();
              setV_range("2"); //20v
              V_measure(6);
            }
            BUT3_SET = true;
          }

        }
        //plc S3 R test ask     60 00 67 00 83 00 68 00 90 00 83 00 51 00 62
        else if (isEquals(datas[i], "60") && isEquals(datas[i + 1], "00") && isEquals(datas[i + 2], "67")
            && isEquals(datas[i + 3], "00") && isEquals(datas[i + 4], "83") && isEquals(datas[i + 5], "00")
            && isEquals(datas[i + 6], "68") && isEquals(datas[i + 7], "00") && isEquals(datas[i + 8], "90")
            && isEquals(datas[i + 9], "00") && isEquals(datas[i + 10], "83") && isEquals(datas[i + 11], "00")
            && isEquals(datas[i + 12], "51") && isEquals(datas[i + 13], "00") && isEquals(datas[i + 14], "62")) {
          // start---
          if((!txtStop.getText().equals("NG")) && (!txtStop.getText().equals("STOP"))) {
            if(table.getValueAt(9, 5).equals("?")) {
              //openMultimeter();
              openTwoLineR_FUC();
              setR_range("1"); //20000欧
              ALLOWTEST_R3 = true;
            }
          }

        }
        //plc S3 V test ask     60 00 67 00 83 00 68 00 89 00 83 00 51 00 62
        else if (isEquals(datas[i], "60") && isEquals(datas[i + 1], "00") && isEquals(datas[i + 2], "67")
            && isEquals(datas[i + 3], "00") && isEquals(datas[i + 4], "83") && isEquals(datas[i + 5], "00")
            && isEquals(datas[i + 6], "68") && isEquals(datas[i + 7], "00") && isEquals(datas[i + 8], "89")
            && isEquals(datas[i + 9], "00") && isEquals(datas[i + 10], "83") && isEquals(datas[i + 11], "00")
            && isEquals(datas[i + 12], "51") && isEquals(datas[i + 13], "00") && isEquals(datas[i + 14], "62")) {
          // start---
          if((!txtStop.getText().equals("NG")) && (!txtStop.getText().equals("STOP"))) {
            if(table.getValueAt(10, 5).equals("?")) {
              //openMultimeter();
              openDCvoltage_FUC();
              setV_range("2"); //20v
              V_measure(10);
            }
          }

        }
        //plc 绝缘1 test ask     60 00 67 00 83 00 74 00 49 00 83 00 49 00 62
        else if (isEquals(datas[i], "60") && isEquals(datas[i + 1], "00") && isEquals(datas[i + 2], "67")
            && isEquals(datas[i + 3], "00") && isEquals(datas[i + 4], "83") && isEquals(datas[i + 5], "00")
            && isEquals(datas[i + 6], "74") && isEquals(datas[i + 7], "00") && isEquals(datas[i + 8], "49")
            && isEquals(datas[i + 9], "00") && isEquals(datas[i + 10], "83") && isEquals(datas[i + 11], "00")
            && isEquals(datas[i + 12], "49") && isEquals(datas[i + 13], "00") && isEquals(datas[i + 14], "62")) {
          // start---
          if((!txtStop.getText().equals("NG")) && (!txtStop.getText().equals("STOP"))) {
            if(ALLjueyuanTestBool01 == false) {
              ALLjueyuanTestBool01 = true;
              JUETYANNUM = "JUYUANNUM_01";
              jueyuanTestBool01 = true;
            }
          }

        }
        //plc 绝缘2 test ask     60 00 67 00 83 00 74 00 50 00 83 00 49 00 62
        else if (isEquals(datas[i], "60") && isEquals(datas[i + 1], "00") && isEquals(datas[i + 2], "67")
            && isEquals(datas[i + 3], "00") && isEquals(datas[i + 4], "83") && isEquals(datas[i + 5], "00")
            && isEquals(datas[i + 6], "74") && isEquals(datas[i + 7], "00") && isEquals(datas[i + 8], "50")
            && isEquals(datas[i + 9], "00") && isEquals(datas[i + 10], "83") && isEquals(datas[i + 11], "00")
            && isEquals(datas[i + 12], "49") && isEquals(datas[i + 13], "00") && isEquals(datas[i + 14], "62")) {
          // start---
          if((!txtStop.getText().equals("NG")) && (!txtStop.getText().equals("STOP"))) {
            if(ALLjueyuanTestBool02 == false) {
              ALLjueyuanTestBool02 = true;
              JUETYANNUM = "JUYUANNUM_02";
              jueyuanTestBool02 = true;
            }
          }
          

        }
        //测行程01 10 00 01 00 08 10 00 [60   00 88   00 67   00 49   AA BB   00 00 00 00 00   62] 1B BE
        //plc 行程1 test     60 88 67 49 00 00 00 00 00 00 00 00 00 00 62
        else if (isEquals(datas[i], "60") && isEquals(datas[i + 1], "00") && isEquals(datas[i + 2], "88")
            && isEquals(datas[i + 3], "00") && isEquals(datas[i + 4], "67") && isEquals(datas[i + 5], "00")
            && isEquals(datas[i + 6], "49") && isEquals(datas[i + 14], "62")) {

          if((!txtStop.getText().equals("NG")) && (!txtStop.getText().equals("STOP"))) {
            if(datas[i + 8] < 0) {
              int value = (int)datas[i + 8] + 256;  //大于127的字节数为负，故而加256将其转换原值
              xcResult1 = (double)(value * 0.01);
            }
            else {
              xcResult1 = (double)((int)datas[i + 8] * 0.01);
            }
            boolXC1 = true;
          }
        }
        //plc 行程2 test     60 88 67 50 00 00 00 00 00 00 00 00 00 00 62
        else if (isEquals(datas[i], "60") && isEquals(datas[i + 1], "00") && isEquals(datas[i + 2], "88")
            && isEquals(datas[i + 3], "00") && isEquals(datas[i + 4], "67") && isEquals(datas[i + 5], "00")
            && isEquals(datas[i + 6], "50") && isEquals(datas[i + 14], "62")) {

          if((!txtStop.getText().equals("NG")) && (!txtStop.getText().equals("STOP"))) {
            if(datas[i + 8] < 0) {
              int value = (int)datas[i + 8] + 256;  //大于127的字节数为负，故而加256将其转换原值
              xcResult2 = (double)(value * 0.01);
            }
            else {
              xcResult2 = (double)((int)datas[i + 8] * 0.01);
            }
            boolXC2 = true;
          }
        }
        //plc 行程3 test     60 88 67 51 00 00 00 00 00 00 00 00 00 00 62
        else if (isEquals(datas[i], "60") && isEquals(datas[i + 1], "00") && isEquals(datas[i + 2], "88")
            && isEquals(datas[i + 3], "00") && isEquals(datas[i + 4], "67") && isEquals(datas[i + 5], "00")
            && isEquals(datas[i + 6], "51") && isEquals(datas[i + 14], "62")) {

          if((!txtStop.getText().equals("NG")) && (!txtStop.getText().equals("STOP"))) {
            if(datas[i + 8] < 0) {
              int value = (int)datas[i + 8] + 256;  //大于127的字节数为负，故而加256将其转换原值
              xcResult3 = (double)(value * 0.01);
            }
            else {
              xcResult3 = (double)((int)datas[i + 8] * 0.01);
            }
            boolXC3 = true;
          }
        }

      }
    } catch (ReadDataFromSerialFail e) {
      JOptionPane.showMessageDialog(null, e.toString());
    } catch (InputStreamCloseFail e) {
      JOptionPane.showMessageDialog(null, e.toString());
    } catch (InterruptedException e) {
      JOptionPane.showMessageDialog(null, e.toString());
    }
  }
  public void COM7DatasArrived() {
    try {
      Thread.sleep(5);  //加延时，防止读取数据丢失
      byte[] datas = SerialPortTool.readByte(COM7);
      if(BUT2_SET) {
        for(int i = 0; i < datas.length - 15; i++) {
          if(isEquals(datas[i], "02") && isEquals(datas[i + 1], "30") && isEquals(datas[i + 2], "32")
              && isEquals(datas[i + 3], "31") && isEquals(datas[i + 14], "0d") && isEquals(datas[i + 15], "0a")) {
            
            if((!txtStop.getText().equals("NG")) && (!txtStop.getText().equals("STOP"))) {
              StringBuilder sb = new StringBuilder();
              sb.append(SerialPortTool.byteAsciiToChar(datas[i + 8]));
              sb.append(SerialPortTool.byteAsciiToChar(datas[i + 9]));
              sb.append(SerialPortTool.byteAsciiToChar(datas[i + 10]));
              sb.append(SerialPortTool.byteAsciiToChar(datas[i + 11]));
              int BUT2_result = Integer.parseInt(sb.toString());
              
              rtimes02++;
              if(rtimes02 > (dataCountOfLi - 1))  rtimes02 = 0;
              mydatap02.set(rtimes02, BUT2_result);
              if(BUT2_result >= 700) {
                BUT2_SET = false;
                printBut2_LL = true;
                rtimes02 = 0;
              }
            }
          }
        }
      }
    } catch (ReadDataFromSerialFail e) {
      JOptionPane.showMessageDialog(null, e.toString());
    } catch (InputStreamCloseFail e) {
      JOptionPane.showMessageDialog(null, e.toString());
    } catch (InterruptedException e) {
      JOptionPane.showMessageDialog(null, e.toString());
    }
  }
  public void COM8DataasArrived() {
    try {
      Thread.sleep(5);  //加延时，防止读取数据丢失
      byte[] datas = SerialPortTool.readByte(COM8);
      if(BUT1_SET) {
        for(int i = 0; i < datas.length - 15; i++) {
          if(isEquals(datas[i], "02") && isEquals(datas[i + 1], "30") && isEquals(datas[i + 2], "31")
              && isEquals(datas[i + 3], "31") && isEquals(datas[i + 14], "0d") && isEquals(datas[i + 15], "0a")) {
            
            if((!txtStop.getText().equals("NG")) && (!txtStop.getText().equals("STOP"))) {
              StringBuilder sb = new StringBuilder();
              sb.append(SerialPortTool.byteAsciiToChar(datas[i + 8]));
              sb.append(SerialPortTool.byteAsciiToChar(datas[i + 9]));
              sb.append(SerialPortTool.byteAsciiToChar(datas[i + 10]));
              sb.append(SerialPortTool.byteAsciiToChar(datas[i + 11]));
              
              int BUT1_result = Integer.parseInt(sb.toString());
              rtimes01++;
              if(rtimes01 > (dataCountOfLi - 1))  rtimes01 = dataCountOfLi - 1;
              else {
                mydatap01.set(rtimes01, BUT1_result);
                if(BUT1_result >= 700) {
                  BUT1_SET = false;
                  printBUT1_LL = true;
                  rtimes01 = 0;
                }
              }
            }
          }
        }
      }
    } catch (ReadDataFromSerialFail e) {
      JOptionPane.showMessageDialog(null, e.toString());
    } catch (InputStreamCloseFail e) {
      JOptionPane.showMessageDialog(null, e.toString());
    } catch (InterruptedException e) {
      JOptionPane.showMessageDialog(null, e.toString());
    }
    
  }
  ////////////////////////////////////////////////////////////////////////////////////////////////
  public void drawPICInit() {
    for (int i = 0; i < dataCountOfLi; i++) {
      int fldata = (int) (i % 1200) / 2;
      mydatap01.add(fldata);
      mydatap02.add(fldata);
      mydatap03.add(fldata);
    }
  }
  
  public void drawPIC(List<Integer> lalidata, int dataofli) {
    EventQueue.invokeLater(new Runnable() {

      @Override
      public void run() {
        for(int i = 0; i < dataofli; i++) {
          if(i >= 1) {
            graphics.drawOval(point.x + i / 2 , point.y - lalidata.get(i), 1, 1);
          }
        }
      }
    });
    File picFile = new File("src/picImage.png");
    try {
      
      ImageIO.write(image, "PNG", picFile);
    } catch (IOException e) {
      JOptionPane.showMessageDialog(null, e.getMessage());
    } finally {
      if(picFile != null)
        picImage = new ImageIcon("src/picImage.png");
    }
  }
  /**
   * 拉力
   * @param datajh
   * @return
   */
  public int xingchengLL(List<Integer> datajh) {
    int XCLL = 0;
    List<Integer> list = new ArrayList<>();
    list.add(300);  //假定测试数据
    for(int i = 0; i < datajh.size(); i++) {
      if(datajh.get(i) < 500) {
        list.add(datajh.get(i));
      }
    }
    Collections.sort(list);
    XCLL = list.get(list.size() - 1);
    return XCLL;
    /*int XCLL = 0;
    List<Integer> diyipeck = new ArrayList<Integer>();
    for (int i = 1; i < datajh.size() - 1; i++) {
      if (datajh.get(i + 1) != 0 && datajh.get(i - 1) != 0 && datajh.get(i) < 500) {
        if (datajh.get(i) > datajh.get(i + 1)) {
          diyipeck.add(datajh.get(i));
        }
      }
    }
    if(diyipeck.size() == 0) {
      printBUT1_LL = false;
      printBut2_LL = false;
      printBut3_LL = false;
      return 0;
    }
    else {
      Collections.sort(diyipeck);
      XCLL = diyipeck.get(diyipeck.size() - 1);
      return XCLL;
    }*/
  }
  ///////////////////////////////////////////////////////////////////////////////////////////////////////
  /**
   * 通过PCI向PLC发送数据
   * 
   * @param channel
   * @param state
   * @throws DaqException 
   */
  public void sendMesToPLCByPCI(int channel, byte state) {
    ErrorCode err = ErrorCode.Success;
    err = instantDoCtrl.Write(channel, state);
    if (err != ErrorCode.Success) {
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
      JOptionPane.showMessageDialog(null, "PCI出错了：" + err.toString());
    }
  }

  /**
   * 如果捺印复选框被选中，则发送捺印请求，否则不钠印
   */
  public void ifProductPrint() {
    if (nayinCbox.isSelected()) {
      pciState = (byte) (pciState & 0xF7);
      sendMesToPLCByPCI(0, pciState);
    } else {
      pciState = (byte) (pciState | 0x08);
      sendMesToPLCByPCI(0, pciState);
    }
  }
  /**
   * 系统退出
   */
  public void onClosing() {
    cancelWarmming();
    jueyuanTestReset();
    timer1.stop();
    timer2.stop();
    timer3.stop();
    COM1.close();
    COM2.close();
    COM3.close();
    COM4.close();
    COM7.close();
    COM8.close();
    usb.visaClear();
  }
  ///////////////////////////////////////////////////////////////////////////
  class Timer1Listener implements ActionListener {

    @Override
    public void actionPerformed(ActionEvent e) {
      if(!txtStop.getText().equals("RUN")) {
        printBUT1_LL = false;
        BUT1_SET = false;
        rtimes01 = 0;
        ALLOWTEST_R1 = false;
        ALLOWTEST_R2 = false;
        ALLOWTEST_R3 = false;
      }
      if(ALLOWTEST_R1) {
        R_measure(1);
      }
      else if(ALLOWTEST_R2) {
        R_measure(5);
      }
      else if(ALLOWTEST_R3) {
        R_measure(9);
      }
      textField13.setText(R_testTimes + "");
      //addRecord();
      if(ALLDIALOGISSHOW) {
        ALLDIALOGISSHOW = false;
        txtStop.setText(testState);
      }
      if(txtStop.getText().equals("WAIT")) {
        timeCount = 0;
      }
      else if(txtStop.getText().equals("RUN")) {
        //设置进度条变化
        /*if(progressBar.getValue() >= 90) {
          progressBar.setValue(0);
        }
        progressBar.setValue(progressBar.getValue() + 15);*/
        timeCount++;
        tTimeField.setText((timeCount/2) + "");  //设置测试计时
        //setTxtStopFieldRUN();
      }
      else {
        progressBar.setValue(0);
        timeCount = 0;
        timeInterValRSTjueyuan++;
        if(timeInterValRSTjueyuan % 2 == 0) {
          jueyuanTestReset();
        }
      }
      /*else if(txtStop.getText().equals("PASS")){
        //setTxtStopFieldPASS();
        timeInterValRSTjueyuan++;
        if(timeInterValRSTjueyuan % 2 == 0) {
          jueyuanTestReset();
        }
      }
      else if(txtStop.getText().equals("NG")) {
        //setTxtStopFieldNG();
        timeInterValRSTjueyuan++;
        if(timeInterValRSTjueyuan % 2 == 0) {
          jueyuanTestReset();
        }
      }
      else if(txtStop.getText().equals("STOP")) {
        //setTxtStopFieldSTOP();
        timeInterValRSTjueyuan++;
        if(timeInterValRSTjueyuan % 2 == 0) {
          jueyuanTestReset();
        }
      }*/
      if(jueyuanTestBool01 || jueyuanTestBool02) {
        jueyuanTestBool01 = false;
        jueyuanTestBool02 = false;
        jueyuanTestReset();
        timer2.start();
      }
      //如果readPortState()返回值包含0x01
      if(readPortState() == (byte)0x01) {
        if(JUETYANNUM.equals("JUYUANNUM_01")) {
          setValueAt(13, "ok");
          setResValueAtRow(13);
        }
        else if(JUETYANNUM.equals("JUYUANNUM_02")) {
          JUETYANNUM = "";
          setValueAt(14, "ok");
          setResValueAtRow(14); 
        }
        outExcel = true;
        jueyuanTestReset();
      }
      else if(readPortState() == (byte)0x02) {
        if(JUETYANNUM.equals("JUYUANNUM_01")) {
          setValueAt(13, "failed");
          setResValueAtRow(13);
          //IFNGBOOL = true;
          //PLCWarmming();
        }
        else if(JUETYANNUM.equals("JUYUANNUM_02")) {
          JUETYANNUM = "";
          setValueAt(14, "failed");
          setResValueAtRow(14);
          //IFNGBOOL = true;
          //PLCWarmming();
        }
        outExcel = true;
      }
      if(testState.equals("RUN")) {
        s4sendtimes++;
        if(s4sendtimes < 4) {
          comWrite(COM4, "01 10 00 01 00 08 10 00 60 00 80 00 76 00 67 00 81 00 68 00 66 00 62 10 B9"); 
        }
        else 
          s4sendtimes = 11;
      }
      //拉力
      if(printBUT1_LL) {
        double value = xingchengLL(mydatap01) * 0.01 - 0.23;
        if(value > 0) {
          printBUT1_LL = false; 
          //drawPIC(mydatap01, dataCountOfLi);  //行程图
          setValueAt(3, forMat(value));
          setResValueAtRow(3);
        }
      }
      else if(printBut2_LL) {
        double value = xingchengLL(mydatap02) * 0.01 - 0.2;
        if(value > 0) {
          printBut2_LL = false;
          //drawPIC(mydatap02, dataCountOfLi);  //行程图
          setValueAt(7, forMat(value));
          setResValueAtRow(7);
        }
      }
      else if(printBut3_LL) {
        double value = xingchengLL(mydatap03) * 0.01 - 0.14; 
        if(value > 0) {
          printBut3_LL = false;
          //drawPIC(mydatap03, dataCountOfLi);  //行程图
          setValueAt(11, forMat(value));
          setResValueAtRow(11);
        }
      }
      //导出excl
      if(outExcel) {
        ECTESTSYSTools.recordtdDataOutToExcel();
      }
      ifPass();
    }
  }
  class Timer2Listener implements ActionListener {

    @Override
    public void actionPerformed(ActionEvent e) {
      jueyuanTest();
      timer2.stop();
    }
  }
  class Timer3Listener implements ActionListener {
    
    private int barValue = 0;

    @Override
    public void actionPerformed(ActionEvent e) {
      if(txtStop.getText().equals("RUN")) {
        if(barValue / 2 == 100) {
          barValue = 0;
        }
        barValue++;
        progressBar.setValue(barValue/2);
      }
      //行程
      if(boolXC1) {
        boolXC1 = false;
        if(table.getValueAt(4, 5).equals("?")) {
          double value = xcResult1 - 0.4;
          setValueAt(4, forMat(value));
          setResValueAtRow(4);
        }
      }
      if(boolXC2) {
        boolXC2 = false;
        if(table.getValueAt(8, 5).equals("?")) {
          double value = xcResult2 - 0.4;
          setValueAt(8, forMat(value));
          setResValueAtRow(8);
        }
      }
      if(boolXC3) {
        boolXC3 = false;
        if(table.getValueAt(12, 5).equals("?")) {
          double value = xcResult3 - 0.45;
          setValueAt(12, forMat(value));
          setResValueAtRow(12);
        }
      }
    }    
  }
  ///////////////////////////////////////////////////////////////////////////
  /**
   * 定义一个类用来渲染某一单元格 用法：获取某一列值，其中单元格值为"PASS"则设为绿色，若为"NG"则设为红色
   */
  class MyTableCellRenderrer extends DefaultTableCellRenderer {

    private static final long serialVersionUID = 1L;

    @Override
    public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus,
        int row, int column) {

      super.setHorizontalAlignment(JLabel.CENTER); // 该列居中显示
      Component comp = super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);

      if ("PASS".equals(value + "")) {
        comp.setBackground(new Color(0, 204, 51));
      } else if ("NG".equals(value + "")) {
        comp.setBackground(Color.RED);
      } else {
        comp.setBackground(Color.WHITE);// 这一行保证其他单元格颜色不变
      }
      return comp;
    }
  }
  //////////////////////////////////////////////////////////////////////////////
  /**
   * 串口对象监听类，内部类
   * @author Loyer
   *
   */
  class PortListener {
    /**
     * 添加监听器
     * 
     * @param port
     *          串口对象
     * @param listener
     *          串口监听器
     * @throws TooManyListeners
     *           监听类对象过多
     */
    public void add(SerialPort port, SerialPortEventListener listener) throws TooManyListeners {

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

    /**
     * 串口1监听对象
     */
    public SerialPortEventListener COM1Listener() {
      SerialPortEventListener listener = new SerialPortEventListener() {
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
      };
      return listener;
    }
    /**
     * 串口2监听对象
     */
    public SerialPortEventListener COM2Listener() {
      SerialPortEventListener listener = new SerialPortEventListener() {
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
            JOptionPane.showMessageDialog(null, "COM2:"+event.toString());
            break;
          case SerialPortEvent.DATA_AVAILABLE: {
            // 有数据到达-----可以开始处理
            COM2DatasArrived();
          }
            break;
          }
        }
      };
      return listener;
    }
    /**
     * 串口3监听对象
     */
    public SerialPortEventListener COM3Listener() {
      SerialPortEventListener listener = new SerialPortEventListener() {
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
            JOptionPane.showMessageDialog(null, "COM3:"+event.toString());
            break;
          case SerialPortEvent.DATA_AVAILABLE: {
            // 有数据到达-----可以开始处理
            COM3DatasArrived();
          }
            break;
          }
        }
      };
      return listener;
    }
    /**
     * 串口4监听对象
     */
    public SerialPortEventListener COM4Listener() {
      SerialPortEventListener listener = new SerialPortEventListener() {
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
            JOptionPane.showMessageDialog(null, "COM4:"+event.toString());
            break;
          case SerialPortEvent.DATA_AVAILABLE: {
            // 有数据到达-----可以开始处理
            COM4DatasArriaved();
          }
            break;
          }
        }
      };
      return listener;
    }
    /**
     * 串口7监听对象
     */
    public SerialPortEventListener COM7Listener() {
      SerialPortEventListener listener = new SerialPortEventListener() {
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
            JOptionPane.showMessageDialog(null, "COM7:"+event.toString());
            break;
          case SerialPortEvent.DATA_AVAILABLE: {
            // 有数据到达-----可以开始处理
            COM7DatasArrived();
          }
            break;
          }
        }
      };
      return listener;
    }
    /**
     * 串口8监听对象
     */
    public SerialPortEventListener COM8Listener() {
      SerialPortEventListener listener = new SerialPortEventListener() {
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
            JOptionPane.showMessageDialog(null, "COM8:"+event.toString());
            break;
          case SerialPortEvent.DATA_AVAILABLE: {
            // 有数据到达-----可以开始处理
            COM8DataasArrived();
          }
            break;
          }
        }
      };
      return listener;
    }

  }
}
