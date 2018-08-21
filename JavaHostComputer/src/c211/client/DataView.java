package c211.client;

import c211.db.*;
import c211.serial.*;
import c211.serialException.NoSuchPort;
import c211.serialException.NotASerialPort;
import c211.serialException.PortInUse;
import c211.serialException.SerialPortParamFail;

import gnu.io.SerialPort;
import java.awt.EventQueue;
import java.awt.Toolkit;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import java.awt.Color;
import java.awt.Component;
import javax.swing.JTextField;
import java.awt.Font;
import javax.swing.SwingConstants;
import javax.swing.Timer;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import javax.swing.table.TableColumn;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.plot.PiePlot;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Vector;
import java.awt.event.ActionEvent;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JButton;
import javax.swing.JProgressBar;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
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
  private JButton testButt;
  private JProgressBar progressBar;
  private Timer timer;
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

  private int ngCount = 0;
  private int okCount = 0;
  private int totalCount = 0;
  private int timeCount = 0;

  private JTable table;
  private MyTableCellRenderrer myTableCellRenderrer = null;

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
        } catch (Exception e) {
          e.printStackTrace();
        }
      }
    });
  }

  public static void main(String[] args) {
    EventQueue.invokeLater(new Runnable() {
      public void run() {
        try {
          DataView window = new DataView();
          window.dataFrame.setVisible(true);
        } catch (Exception e) {
          e.printStackTrace();
        }
      }
    });
  }

  public DataView() {
    initialize();
  }

  /**
   * 本类构造器，接收登录页面传进User值
   */
  public DataView(String user) {
    this.userName = user;
    initialize();
    // initPort();
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
    // ImageIcon img_1 = new ImageIcon("src/back.jpg");
    // ImageIcon img_1 = new ImageIcon("src/img11.jpg");
    ImageIcon img_1 = new ImageIcon("src/run.jpg");
    // ImageIcon img_1 = new ImageIcon("src/water.png");
    JLabel imgLabel = new JLabel(img_1);
    dataFrame.getLayeredPane().add(imgLabel, new Integer(Integer.MIN_VALUE));
    imgLabel.setBounds(0, 0, img_1.getIconWidth(), img_1.getIconHeight()); // 背景图片的位置
    // 将contentPane设置成透明的
    ((JPanel) dataFrame.getContentPane()).setOpaque(false);

    // 替换窗口的咖啡图标
    // Toolkit tk = Toolkit.getDefaultToolkit();
    // Image img = tk.getImage("src/Kyokuto.png");
    dataFrame.setIconImage(Toolkit.getDefaultToolkit().getImage("src/Kyokuto.png"));
    dataFrame.getContentPane().setLayout(null);
    dataFrame.getContentPane().setLayout(null);

    headTxtField = new JTextField();
    headTxtField.setEditable(false);
    headTxtField.setOpaque(false);
    headTxtField.setBounds(0, 0, WIDTH, HEIGHT / 9 - 5);
    headTxtField.setText("记忆开关测试系统");
    headTxtField.setHorizontalAlignment(SwingConstants.CENTER);
    headTxtField.setForeground(new Color(0, 0, 255));
    headTxtField.setFont(new Font("等线", Font.BOLD, 50));
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
        if (num == JOptionPane.YES_OPTION)
          System.exit(0);
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
          //initCountAndPieChart();
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

    timer = new Timer(50, new ActionListener() {
      @Override
      public void actionPerformed(ActionEvent e) {
        if (e.getSource() == testButt) {
          timer.start();
          int value = 0;
          value++;
          progressBar.setValue(value);
        } else
          timer.stop();
      }
    });

    progressBar = new JProgressBar();
    progressBar.setMaximum(100);
    progressBar.setMinimum(0);
    progressBar.setValue(0);
    progressBar.setOpaque(false);
    progressBar.setForeground(new Color(0, 204, 51));
    progressBar.setStringPainted(true);
    progressBar.setBounds(0, HEIGHT * 9 / 72 + 5, WIDTH * 2 / 16, HEIGHT * 2 / 72);
    runPanel.add(progressBar);

    testButt = new JButton("点我");
    testButt.addMouseListener(new MouseAdapter() {
      @Override
      public void mouseClicked(MouseEvent e) {
        if (e.getButton() == MouseEvent.BUTTON1 && e.getClickCount() == 1) {
          // testButt.setEnabled(false);
          startTest();
        } else if (e.getButton() == MouseEvent.BUTTON2 && e.getClickCount() == 1) {
          // testButt.setEnabled(false);
          initPort();
        } else if (e.getButton() == MouseEvent.BUTTON3 && e.getClickCount() == 1) {
          // testButt.setEnabled(false);
          startTest_righiKey();
        } else
          JOptionPane.showMessageDialog(null, "别他妈胡乱操作好吗？");
      }
    });

    testButt.setForeground(new Color(102, 0, 0));
    testButt.setBackground(Color.GREEN);
    testButt.setBounds(49, 171, 93, 23);
    runPanel.add(testButt);

    serialPanel = new JPanel();
    serialPanel.setOpaque(false);
    serialPanel.setBounds(0, HEIGHT * 60 / 72, WIDTH * 12 / 16 + 5, HEIGHT * 12 / 72 - 5);
    // System.out.println(serialPanel.getHeight());
    // System.out.println(serialPanel.getWidth());
    serialPanel.setBackground(new Color(255, 228, 225));
    dataFrame.getContentPane().add(serialPanel);
    serialPanel.setLayout(null);
    // 获取该面板长高，以方便布局
    final int SW = serialPanel.getWidth();
    final int SH = serialPanel.getHeight();

    COM1Butt = new JButton("COM1");
    COM1Butt.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent e) {
        try {
          if (SerialPortTool.findPort().contains("COM1")) {
            COM1Butt.setBackground(Color.RED);
            SerialPort COM1 = SerialPortTool.getPort("COM1", 9600, 8, 1, 0);
            SerialPortTool.write(COM1, ":function:voltage:DC");
            SerialPortTool.write(COM1, ":measure:voltage:DC?");
            table.setValueAt(SerialPortTool.read(COM1), 3, 5);
            SerialPortTool.closePort(COM1);
          } else {
            JOptionPane.showMessageDialog(null, "未发现串口1", "警告", JOptionPane.WARNING_MESSAGE);
          }

        } catch (SerialPortParamFail e1) {
          e1.printStackTrace();
        } catch (NotASerialPort e1) {
          e1.printStackTrace();
        } catch (NoSuchPort e1) {
          e1.printStackTrace();
        } catch (PortInUse e1) {
          e1.printStackTrace();
        }
      }
    });
    COM1Butt.addMouseListener(new MouseAdapter() {
      @Override
      public void mouseClicked(MouseEvent e) {
        try {
          if (SerialPortTool.findPort().contains("COM1")) {
            COM1Butt.setBackground(Color.RED);
            SerialPort COM1 = SerialPortTool.getPort("COM1", 9600, 8, 1, 0);
            SerialPortTool.write(COM1, ":function:voltage:DC");
            SerialPortTool.write(COM1, ":measure:voltage:DC?");
            table.setValueAt(SerialPortTool.read(COM1), 3, 5);
            SerialPortTool.closePort(COM1);
          } else {
            JOptionPane.showMessageDialog(null, "未发现串口1", "警告", JOptionPane.WARNING_MESSAGE);
          }

        } catch (SerialPortParamFail e1) {
          e1.printStackTrace();
        } catch (NotASerialPort e1) {
          e1.printStackTrace();
        } catch (NoSuchPort e1) {
          e1.printStackTrace();
        } catch (PortInUse e1) {
          e1.printStackTrace();
        }
      }
    });
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
    COM4Butt.addMouseListener(new MouseAdapter() {
      @Override
      public void mouseClicked(MouseEvent e) {
        try {
          ArrayList<String> port = SerialPortTool.findPort();
          /*
           * for (Iterator<String> i = port.iterator(); i.hasNext();) { String s =
           * i.next(); System.out.println("可用串口：" + s); }
           */
          if (port.contains("COM4")) {
            SerialPort COM4 = SerialPortTool.getPort("COM4", 9600, 8, 1, 0);
            COM4Butt.setBackground(Color.RED);
            SerialPortTool.write(COM4, "13.2");
            table.setValueAt(SerialPortTool.read(COM4), 2, 5);
            SerialPortTool.closePort(COM4);
          } else {
            JOptionPane.showMessageDialog(null, "未发现串口4");
            // JOptionPane.showMessageDialog(null, "未发现串口4", "警告",
            // JOptionPane.WARNING_MESSAGE);
          }
        } catch (SerialPortParamFail e1) {
          e1.printStackTrace();
        } catch (NotASerialPort e1) {
          e1.printStackTrace();
        } catch (NoSuchPort e1) {
          e1.printStackTrace();
        } catch (PortInUse e1) {
          e1.printStackTrace();
        }
      }
    });
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
      }
    });
    // nayinCbox.isSelected();
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
    // colTestvalue.setMaxWidth(100);
    // colTestvalue.setMinWidth(100);

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
      if (getValueAt(1) <= 1d && getValueAt(1) >= 0)
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

  public void test_1() {
    table.setValueAt("0.5", 1, 5);
    table.setValueAt("13.2", 2, 5);
    table.setValueAt("3", 3, 5);
    table.setValueAt("2", 4, 5);
    table.setValueAt("2000", 5, 5);
    table.setValueAt("4.5", 6, 5);
    table.setValueAt("3", 7, 5);
    table.setValueAt("2", 8, 5);
    table.setValueAt("635.66", 9, 5);
    table.setValueAt("8.3", 10, 5);
    table.setValueAt("3", 11, 5);
    table.setValueAt("2", 12, 5);
    table.setValueAt("ok", 13, 5);
    table.setValueAt("ok", 14, 5);
  }

  public void test_2() {
    table.setValueAt("0.5", 1, 5);
    table.setValueAt("13.2", 2, 5);
    table.setValueAt("3", 3, 5);
    table.setValueAt("2", 4, 5);
    table.setValueAt("2030", 5, 5);
    table.setValueAt("4.5", 6, 5);
    table.setValueAt("3", 7, 5);
    table.setValueAt("2", 8, 5);
    table.setValueAt("635.66", 9, 5);
    table.setValueAt("8.3", 10, 5);
    table.setValueAt("3", 11, 5);
    table.setValueAt("2", 12, 5);
    table.setValueAt("ok", 13, 5);
    table.setValueAt("ok", 14, 5);
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
      COM1Butt.setBackground(Color.RED);
    }
    /*
     * if (!port.contains("COM2")) { JOptionPane.showMessageDialog(null, "未发现串口2");
     * COM2Butt.setBackground(Color.BLACK); } else {
     * COM2Butt.setBackground(Color.RED); }
     */
    if (!port.contains("COM3")) {
      JOptionPane.showMessageDialog(null, "未发现串口3");
      COM3Butt.setBackground(Color.BLACK);
    } else {
      COM3Butt.setBackground(Color.RED);
    }
    if (!port.contains("COM4")) {
      JOptionPane.showMessageDialog(null, "未发现串口4");
      COM4Butt.setBackground(Color.BLACK);
    } else {
      COM4Butt.setBackground(Color.RED);
    }
    /*
     * if (!port.contains("COM5")) { JOptionPane.showMessageDialog(null, "未发现串口5");
     * COM5Butt.setBackground(Color.BLACK); } else {
     * COM5Butt.setBackground(Color.RED); } if (!port.contains("COM6")) {
     * JOptionPane.showMessageDialog(null, "未发现串口6");
     * COM6Butt.setBackground(Color.BLACK); } else {
     * COM6Butt.setBackground(Color.RED); }
     */
    if (!port.contains("COM7")) {
      JOptionPane.showMessageDialog(null, "未发现串口7");
      COM7Butt.setBackground(Color.BLACK);
    } else {
      COM7Butt.setBackground(Color.RED);
    }
    if (!port.contains("COM8")) {
      JOptionPane.showMessageDialog(null, "未发现串口8");
      COM8Butt.setBackground(Color.BLACK);
    } else {
      COM8Butt.setBackground(Color.RED);
    }
  }

  /**
   * 初始化界面计数值和饼图
   */
  public void initCountAndPieChart() {
    if (getRecorddata() != null) {
      okCount = getOkCount();
      ngCount = getNgCount();
      totalCount = getTotalCount();
      okField.setText(okCount + "");
      ngField.setText(ngCount + "");
      totalField.setText(totalCount + "");
      setPieChart(okCount, ngCount);
    } else {
      okCount = 0;
      ngCount = 0;
      totalCount = 0;
      okField.setText(okCount + "");
      ngField.setText(ngCount + "");
      totalField.setText(totalCount + "");
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
   * 测试开始则计时
   */
  public void setTtimeFieldCount() {
    timeCount = 0;
    new Thread() {
      public void run() {
        try {
          while (txtStop.getText().equals("RUN")) {
            tTimeField.setText("" + timeCount + "");
            timeCount++; // 测试时间加1秒
            // for(int i = 0; i <= 100; i++)
            // progressBar.setValue(timeCount);
            // progressBar.set
            Thread.sleep(1000);
          }
        } catch (Exception e) {
          e.printStackTrace();
        }
      }
    }.start();
  }

  /**
   * 设置进度条变化
   */
  public void setProgressBar() {
    new Thread() {
      public void run() {
        try {
          int i = 0;
          while (txtStop.getText().equals("RUN")) {
            if (i < 100) {
              progressBar.setValue(i);
              i++;
            } else
              i = 0;
            Thread.sleep(10);
          }
          progressBar.setValue(0);
        } catch (Exception e) {
          e.printStackTrace();
        }
      }
    }.start();
  }

  /**
   * table渲染色，测试结果为"PASS"则设为绿色，"NG"为红色
   */
  public void setTableCellRenderer() {
    if(myTableCellRenderrer == null) {
      myTableCellRenderrer = new MyTableCellRenderrer();
      table.getColumnModel().getColumn(7).setCellRenderer(myTableCellRenderrer);
    }
    else
      table.getColumnModel().getColumn(7).setCellRenderer(myTableCellRenderrer);
  }

  /**
   * 设置运行状态框为NG
   */
  public void setTxtStopFieldNG() {
    txtStop.setText("NG");
    txtStop.setBackground(Color.RED);
  }

  /**
   * 设置运行状态框为PASS
   */
  public void setTxtStopFieldPASS() {
    txtStop.setText("PASS");
    txtStop.setBackground(new Color(0, 204, 51));
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
    setTtimeFieldCount();
    setProgressBar();
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
   */
  public void setTableTestResultNG(int i) {
    table.setValueAt("NG", i, 7);
    setTableCellRenderer();
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

  // 往recordtddata表中插入当天测试数据
  public void addRecord() {
    LocalDate now = LocalDate.now();
    String[] str = { "C211", totalField.getText(), okField.getText(), ngField.getText(), tTimeField.getText(),
        now.toString() };
    // String[] str = {"C211", totalCount+"", okCount+"", ngCount+"", timeCount+"",
    // now.toString()};
    try {
      ECTESTSYSTools.deleterecord(now.toString(), "C211");
      ECTESTSYSTools.addrecord(str);
    } catch (Exception e) {
      JOptionPane.showMessageDialog(null, e.getMessage());
    }
  }

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

  /**
   * 流程控制
   */
  public void startTest() {
    setTxtStopFieldRUN();
    initTable();
    new Thread() {
      public void run() {
        try {
          Thread.sleep(2000);
          test_2();
          for (int i = 1; i <= 14; i++) {
            setResValueAtRow(i);
          }
          if (!(resultOkAtRow(1) && resultOkAtRow(2) && resultOkAtRow(3) && resultOkAtRow(4) && resultOkAtRow(5)
              && resultOkAtRow(6) && resultOkAtRow(7) && resultOkAtRow(8) && resultOkAtRow(9) && resultOkAtRow(10)
              && resultOkAtRow(11) && resultOkAtRow(12) && resultOkAtRow(13) && resultOkAtRow(14))) {

            setTxtStopFieldNG();
            if (nayinCbox.isSelected())
              JOptionPane.showMessageDialog(null, "假装捺印^_^");
            else
              JOptionPane.showMessageDialog(null, "不用捺印@_@");

            setNgFieldCount();
          }
        } catch (InterruptedException e) {
          e.printStackTrace();
        }
      }
    }.start();

  }

  public void startTest_righiKey() {
    setTxtStopFieldRUN();
    initTable();
    new Thread() {
      public void run() {
        try {
          Thread.sleep(2000);
          test_1();
          for (int i = 1; i <= 14; i++) {
            setResValueAtRow(i);
          }
          if (resultOkAtRow(1) && resultOkAtRow(2) && resultOkAtRow(3) && resultOkAtRow(4) && resultOkAtRow(5)
              && resultOkAtRow(6) && resultOkAtRow(7) && resultOkAtRow(8) && resultOkAtRow(9) && resultOkAtRow(10)
              && resultOkAtRow(11) && resultOkAtRow(12) && resultOkAtRow(13) && resultOkAtRow(14)) {

            setTxtStopFieldPASS();
            if (nayinCbox.isSelected())
              JOptionPane.showMessageDialog(null, "假装捺印^_^");
            else
              JOptionPane.showMessageDialog(null, "不用捺印@_@");

            setOkFieldCount();
          }
        } catch (InterruptedException e) {
          e.printStackTrace();
        }
      }
    }.start();

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
  ///////////////////////////////////////////////////////////////////////////
}
