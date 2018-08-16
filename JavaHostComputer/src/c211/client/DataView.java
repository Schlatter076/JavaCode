package c211.client;

import c211.db.*;
import c211.serial.*;

import java.awt.EventQueue;
import java.awt.Image;
import java.awt.Toolkit;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import java.awt.Color;
import javax.swing.JTextField;
import java.awt.Font;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import javax.swing.table.TableColumn;
import javax.swing.JPanel;
import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import java.awt.event.ActionListener;
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
import javax.swing.JComboBox;

/**
 * 测试页面
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
  private JTextField txtC_1;
  private JProgressBar progressBar;
  private JButton COM1Butt;
  private JButton COM2Butt;
  private JButton COM3Butt;
  private JButton COM4Butt;
  private JButton COM5Butt;
  private JButton COM6Butt;
  private JButton COM7Butt;
  private JButton COM8Butt;
  
  /**
   * 提供实例化本类方法
   * @param user 接收登录用户名，以区分权限
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
  }

  /**
   * 初始化页面
   */
  private void initialize() {
    dataFrame = new JFrame();
    //dataFrame.getContentPane().setBackground(new Color(245, 245, 220));
    //自动获取屏幕分辨率
    //Dimension viewSize = Toolkit.getDefaultToolkit().getScreenSize();
    final int WIDTH = Toolkit.getDefaultToolkit().getScreenSize().width;
    final int HEIGHT = Toolkit.getDefaultToolkit().getScreenSize().height;
    dataFrame.setBounds(0, 0, WIDTH, HEIGHT);
    dataFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    
    dataFrame.setResizable(false);  //窗口大小不可改
    dataFrame.setUndecorated(true);
    
    //更换背景图片 
    //ImageIcon img_1 = new ImageIcon("src/run.jpg");
    ImageIcon img_1 = new ImageIcon("src/water.png");
    JLabel imgLabel = new JLabel(img_1);
    dataFrame.getLayeredPane().add(imgLabel, new Integer(Integer.MIN_VALUE)); 
    imgLabel.setBounds(0,0,img_1.getIconWidth(), img_1.getIconHeight()); //背景图片的位置
    //将contentPane设置成透明的 
    ((JPanel)dataFrame.getContentPane()).setOpaque(false);
    
    //替换窗口的咖啡图标
    //Toolkit tk = Toolkit.getDefaultToolkit();
    //Image img = tk.getImage("src/Kyokuto.png");
    dataFrame.setIconImage(Toolkit.getDefaultToolkit().getImage("src/Kyokuto.png"));
    dataFrame.getContentPane().setLayout(null);
    dataFrame.getContentPane().setLayout(null);
    
    headTxtField = new JTextField();
    headTxtField.setEditable(false);
    headTxtField.setBounds(0, 0, WIDTH, HEIGHT/9 - 5);
    headTxtField.setText("C211测试系统");
    headTxtField.setHorizontalAlignment(SwingConstants.CENTER);
    headTxtField.setForeground(new Color(255, 20, 147));
    headTxtField.setFont(new Font("等线", Font.BOLD, 50));
    headTxtField.setBackground(new Color(0, 204, 255));
    dataFrame.getContentPane().add(headTxtField);
    headTxtField.setColumns(10);
    
    resultPanel = new JPanel();
    resultPanel.setBounds(0, HEIGHT/9, WIDTH*10/16, HEIGHT*9/72);
    resultPanel.setBackground(new Color(211, 211, 211));
    dataFrame.getContentPane().add(resultPanel);
    resultPanel.setLayout(null);
    
    totalField = new JTextField();
    totalField.setEditable(false);
    totalField.setText("0");
    totalField.setHorizontalAlignment(SwingConstants.CENTER);
    totalField.setForeground(new Color(0, 128, 0));
    totalField.setFont(new Font("等线", Font.PLAIN, 40));
    totalField.setBounds(WIDTH*1/20, HEIGHT*9/288, WIDTH*6/64, HEIGHT*9/144);
    totalField.setBackground(new Color(189, 183, 107));
    resultPanel.add(totalField);
    totalField.setColumns(10);
    
    okField = new JTextField();
    okField.setEditable(false);
    okField.setText("0");
    okField.setHorizontalAlignment(SwingConstants.CENTER);
    okField.setForeground(new Color(0, 128, 0));
    okField.setFont(new Font("等线", Font.PLAIN, 40));
    okField.setBounds(WIDTH*31/160, HEIGHT*9/288, WIDTH*6/64, HEIGHT*9/144);
    okField.setBackground(new Color(189, 183, 107));
    okField.setColumns(10);
    resultPanel.add(okField);
    
    ngField = new JTextField();
    ngField.setEditable(false);
    ngField.setText("0");
    ngField.setHorizontalAlignment(SwingConstants.CENTER);
    ngField.setFont(new Font("等线", Font.PLAIN, 40));
    ngField.setForeground(new Color(255, 0, 0));
    ngField.setBounds(WIDTH*54/160, HEIGHT*9/288, WIDTH*6/64, HEIGHT*9/144);
    ngField.setBackground(new Color(189, 183, 107));
    ngField.setColumns(10);
    resultPanel.add(ngField);
    
    tTimeField = new JTextField();
    tTimeField.setEditable(false);
    tTimeField.setText("0");
    tTimeField.setHorizontalAlignment(SwingConstants.CENTER);
    tTimeField.setFont(new Font("等线", Font.PLAIN, 40));
    tTimeField.setForeground(new Color(0, 128, 0));
    tTimeField.setBounds(WIDTH*77/160, HEIGHT*9/288, WIDTH*6/64, HEIGHT*9/144);
    tTimeField.setBackground(new Color(189, 183, 107));
    tTimeField.setColumns(10);
    resultPanel.add(tTimeField);
    
    totalLabel = new JLabel("测试总数：");
    totalLabel.setFont(new Font("等线", Font.BOLD, 16));
    totalLabel.setForeground(new Color(50, 205, 50));
    totalLabel.setBounds(WIDTH*1/20, 0, 110, HEIGHT*9/288);
    resultPanel.add(totalLabel);
    
    okLabel = new JLabel("OK：");
    okLabel.setForeground(new Color(50, 205, 50));
    okLabel.setFont(new Font("等线", Font.BOLD, 16));
    okLabel.setBounds(WIDTH*31/160, 0, 110, HEIGHT*9/288);
    resultPanel.add(okLabel);
    
    ngLabel = new JLabel("NG：");
    ngLabel.setForeground(new Color(255, 0, 0));
    ngLabel.setFont(new Font("等线", Font.BOLD, 16));
    ngLabel.setBounds(WIDTH*54/160, 0, 110, HEIGHT*9/288);
    resultPanel.add(ngLabel);
    
    tTimeLabel = new JLabel("测试时间：");
    tTimeLabel.setForeground(new Color(50, 205, 50));
    tTimeLabel.setFont(new Font("等线", Font.BOLD, 16));
    tTimeLabel.setBounds(WIDTH*77/160, 0, 110, HEIGHT*9/288);
    resultPanel.add(tTimeLabel);
    
    menuPanel = new JPanel();
    menuPanel.setBounds(WIDTH*12/16 + 10, HEIGHT/9, WIDTH*4/16 - 10, HEIGHT*8/9 - 5);
    menuPanel.setBackground(new Color(216, 191, 216));
    dataFrame.getContentPane().add(menuPanel);
    menuPanel.setLayout(null);
    //获取当前面板长和高
    int MW = menuPanel.getWidth();
    int MH = menuPanel.getHeight();
    
    menuBar = new JMenuBar();
    menuBar.setBounds(10, 10, MW*6/16, MH/36);
    menuBar.setForeground(new Color(102, 204, 0));
    menuBar.setBackground(new Color(255, 255, 255));
    menuPanel.add(menuBar);
    
    fileMenu = new JMenu("文件");
    fileMenu.setForeground(new Color(0, 0, 0));
    fileMenu.setIcon(new ImageIcon(DataView.class.getResource("/com/sun/java/swing/plaf/motif/icons/TreeOpen.gif")));
    fileMenu.setFont(new Font("等线", Font.PLAIN, 14));
    //fileMenu.setBounds(0, 0, MW/8, MH/36);
    menuBar.add(fileMenu);
    
    exitSysMnItm = new JMenuItem("退出系统");
    exitSysMnItm.setForeground(new Color(0, 0, 0));
    exitSysMnItm.setFont(new Font("等线", Font.PLAIN, 14));
    exitSysMnItm.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent e) {
        int num = JOptionPane.showConfirmDialog(null, "确认退出？", "提示", JOptionPane.YES_NO_OPTION);
        if(num == JOptionPane.YES_OPTION)
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
    editMenu.setForeground(new Color(0, 0, 0));
    editMenu.setFont(new Font("等线", Font.PLAIN, 14));
    //editMenu.setBounds(0, MW/8, MW/8, MH/36);
    menuBar.add(editMenu);
    
    debugToolMnItm = new JMenuItem("调试工具");
    debugToolMnItm.setForeground(new Color(0, 0, 0));
    debugToolMnItm.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent e) {
        if(userName.equals("admin"))
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
    viewResultMnItm.setForeground(new Color(0, 0, 0));
    viewResultMnItm.setFont(new Font("等线", Font.PLAIN, 14));
    editMenu.add(viewResultMnItm);
    
    systemMenu = new JMenu("系统");
    systemMenu.setForeground(new Color(0, 0, 0));
    systemMenu.setFont(new Font("等线", Font.PLAIN, 14));
    //systemMenu.setBounds(0, MW*2/8, MW/8, MH/36);
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
    
    JComboBox timeBox = new JComboBox();
    timeBox.setBackground(new Color(255, 255, 204));
    timeBox.setEditable(true);
    timeBox.setForeground(new Color(0, 153, 255));
    timeBox.setBounds(10, MH/36 + 20, MW/2, MH/32);
    menuPanel.add(timeBox);
    
    runPanel = new JPanel();
    runPanel.setForeground(new Color(0, 204, 0));
    runPanel.setBounds(WIDTH*10/16 + 5, HEIGHT/9, WIDTH*2/16, HEIGHT*51/72+5);
    runPanel.setBackground(new Color(255, 255, 255));
    runPanel.setOpaque(false);
    dataFrame.getContentPane().add(runPanel);
    runPanel.setLayout(null);
    
    txtStop = new JTextField();
    txtStop.setEditable(false);
    txtStop.setText("STOP");
    txtStop.setHorizontalAlignment(SwingConstants.CENTER);
    txtStop.setFont(new Font("Consolas", Font.BOLD | Font.ITALIC, 70));
    txtStop.setBounds(0, 0, WIDTH*2/16, HEIGHT*9/72);
    txtStop.setBackground(new Color(255, 255, 0));
    runPanel.add(txtStop);
    txtStop.setColumns(10);
    
    progressBar = new JProgressBar();
    progressBar.setForeground(new Color(0, 204, 51));
    progressBar.setStringPainted(true);
    progressBar.setBounds(0, HEIGHT*9/72 + 5, WIDTH*2/16, HEIGHT*2/72);
    runPanel.add(progressBar);
    
    testButt = new JButton("点我");
    testButt.addMouseListener(new MouseAdapter() {
      @Override
      public void mouseClicked(MouseEvent e) {
        //progressBar.setString("%");
        txtStop.setText("RUN");
        new Thread(){
          public void run(){
            for(int i=0;i<=100;i++){
              try{
                Thread.sleep(100);
              }catch(InterruptedException e){
                e.printStackTrace();
              }
                  progressBar.setValue(i);
                  //String str = 
                  tTimeField.setText("" + i + "");
                  
            }
            //progressBar.setString("OVER");
            txtStop.setText("STOP");
            //tTimeField.setText("0");
            
          }
        }.start();
      }
    });
    
    testButt.setForeground(new Color(102, 0, 0));
    testButt.setBackground(new Color(153, 255, 255));
    testButt.setBounds(49, 171, 93, 23);
    runPanel.add(testButt);
    
    serialPanel = new JPanel();
    serialPanel.setBounds(0, HEIGHT*60/72, WIDTH*12/16 + 5, HEIGHT*12/72-5);
    //System.out.println(serialPanel.getHeight());
    //System.out.println(serialPanel.getWidth());
    serialPanel.setBackground(new Color(255, 228, 225));
    dataFrame.getContentPane().add(serialPanel);
    serialPanel.setLayout(null);
    
    //获取该面板长高，以方便布局
    int SW = serialPanel.getWidth();
    int SH = serialPanel.getHeight();
    
    COM1Butt = new JButton("COM1");
    COM1Butt.setBackground(new Color(255, 228, 225));
    COM1Butt.setBounds(SW*2/42, SH/8, SW/14, SH/8);
    serialPanel.add(COM1Butt);
    
    COM2Butt = new JButton("COM2");
    COM2Butt.setBackground(new Color(255, 228, 225));
    COM2Butt.setBounds(SW*7/42, SH/8, SW/14, SH/8);
    serialPanel.add(COM2Butt);
    
    COM3Butt = new JButton("COM3");
    COM3Butt.setBackground(new Color(255, 228, 225));
    COM3Butt.setBounds(SW*12/42, SH/8, SW/14, SH/8);
    serialPanel.add(COM3Butt);
    
    COM4Butt = new JButton("COM4");
    COM4Butt.setBackground(new Color(255, 228, 225));
    COM4Butt.setBounds(SW*17/42, SH/8, SW/14, SH/8);
    serialPanel.add(COM4Butt);
    
    COM5Butt = new JButton("COM5");
    COM5Butt.setBackground(new Color(255, 228, 225));
    COM5Butt.setBounds(SW*22/42, SH/8, SW/14, SH/8);
    serialPanel.add(COM5Butt);
    
    COM6Butt = new JButton("COM6");
    COM6Butt.setBackground(new Color(255, 228, 225));
    COM6Butt.setBounds(SW*27/42, SH/8, SW/14, SH/8);
    serialPanel.add(COM6Butt);
    
    COM7Butt = new JButton("COM7");
    COM7Butt.setBackground(new Color(255, 228, 225));
    COM7Butt.setBounds(SW*32/42, SH/8, SW/14, SH/8);
    serialPanel.add(COM7Butt);
    
    COM8Butt = new JButton("COM8");
    COM8Butt.setBackground(new Color(255, 228, 225));
    COM8Butt.setBounds(SW*37/42, SH/8, SW/14, SH/8);
    serialPanel.add(COM8Butt);
    
    JPanel nyPanel = new JPanel();
    nyPanel.setBackground(new Color(0, 51, 204));
    nyPanel.setBounds(15, SH*13/24, SW-30, SH/3);
    serialPanel.add(nyPanel);
    nyPanel.setLayout(null);
    
    txtC_1 = new JTextField();
    txtC_1.setText("C211");
    txtC_1.setHorizontalAlignment(SwingConstants.LEFT);
    txtC_1.setForeground(new Color(255, 0, 0));
    txtC_1.setFont(new Font("等线", Font.BOLD, 30));
    txtC_1.setBackground(new Color(0, 51, 204));
    txtC_1.setBounds((SW-30)/6, 0, (SW-30)/3, SH/3);
    nyPanel.add(txtC_1);
    txtC_1.setColumns(10);
    
    JLabel pNameLabel = new JLabel("产品型号:");
    pNameLabel.setFont(new Font("等线", Font.BOLD, 30));
    pNameLabel.setForeground(new Color(255, 255, 204));
    //pNameLabel.setText(text);
    pNameLabel.setBackground(new Color(0, 51, 204));
    pNameLabel.setBounds(0, 0, (SW-30)/6, SH/3);
    nyPanel.add(pNameLabel);
   
    //JTable table = new JTable(17, 10);  
    //table.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);//关闭表格列自动调整，此时水平滚动条可见 
    //JTable table = completedTable(getTestTable());
    //table.
    scrollPane = new JScrollPane(completedTable(getTestTable()));  //向滚动面板中添加JTable
    scrollPane.setBounds(10, HEIGHT*17/72+5, WIDTH*10/16 - 10, HEIGHT*42/72);
    dataFrame.getContentPane().add(scrollPane);
    
  }
  /**
   * 创建JTable方法
   * @return
   */
  public JTable getTestTable() {
    Vector<Object> rowNum = null,colNum = null;
    //创建列对象
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
    
    //创建行对象
    rowNum = new Vector<>();    
    List<C211Data> tableList = C211DataTools.getAllbyDb(); //从数据库中获取c211表的内容
    for(Iterator<C211Data> i = tableList.iterator(); i.hasNext(); ) {
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
   * @param table
   * @return
   */
  public JTable completedTable(JTable table) {
    
    JTableHeader jTableHeader = table.getTableHeader(); //获取表头
    // 设置表头名称字体样式
    jTableHeader.setFont(new Font("", Font.PLAIN, 14));
    // 设置表头名称字体颜色
    jTableHeader.setForeground(Color.BLACK);
    
    //设置列宽
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
    //colTestvalue.setMaxWidth(100);
    //colTestvalue.setMinWidth(100);

    table.setEnabled(false);  //内容不可编辑
    DefaultTableCellRenderer   r   =   new   DefaultTableCellRenderer();  //设置
    r.setHorizontalAlignment(JLabel.CENTER);  //单元格内容
    table.setDefaultRenderer(Object.class,   r); //居中显示
    
    table.setRowHeight(27);  //设置行高
    //增加一行空白行
    DefaultTableModel tableModel = (DefaultTableModel) table.getModel(); 
    tableModel.addRow(new Object[]{"*", "", "", "", "", "", "", "", "", "", "", "", ""}); 
    //table.setGridColor(Color.PINK);   //设置网格颜色
    table.setForeground(Color.BLUE);  //设置文字颜色
    table.setFont(new Font("", Font.PLAIN, 12));
    table.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);//关闭表格列自动调整
    
    return table;
  }
  /**
   * 流程控制
   */
  public void startTest() {
    
  }
}
