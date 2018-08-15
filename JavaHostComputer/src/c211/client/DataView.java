package c211.client;

import c211.db.*;
import c211.serial.*;

import java.awt.Dimension;
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
import javax.swing.ScrollPaneConstants;
import javax.swing.JButton;
import javax.swing.JProgressBar;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class DataView {

  private String userName;
  private JFrame dataFrame;
  private JTextField txtC;
  private JPanel menuPanel;
  private JPanel runPanel;
  private JPanel serialPanel;
  private JMenuBar menuBar;
  private JMenu mnNewMenu;
  private JMenuItem mntmNewMenuItem;
  private JMenu mnNewMenu_1;
  private JMenu mnNewMenu_2;
  private JMenuItem mntmNewMenuItem_1;
  private JMenuItem menuItem;
  private JMenuItem mntmNewMenuItem_2;
  private JMenuItem testToolMenu;
  private JMenuItem menuItem_2;
  private JMenuItem menuItem_3;
  private JMenuItem menuItem_4;
  private JTextField textField_1;
  private JTextField textField_2;
  private JTextField textField_3;
  private JTextField textField_4;
  private JLabel lblNewLabel;
  private JLabel lblOk;
  private JLabel lblNg;
  private JLabel label_2;
  private JTextField txtStop;
  private JScrollPane scrollPane;
  private JButton COM7Butt;
  private JButton COM8Butt;
  private JButton testButt;
  
  
  public static void dataShow(String user) {
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
  /**
   * Launch the application.
   */
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
   * Create the application.
   */
  public DataView(String user) {
    this.userName = user;
    initialize();
  }

  /**
   * Initialize the contents of the frame.
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
    
    
    Toolkit tk = Toolkit.getDefaultToolkit();
    //替换窗口的咖啡图标
    Image img = tk.getImage("src/Kyokuto.png");
    dataFrame.setIconImage(img);
    dataFrame.getContentPane().setLayout(null);
    dataFrame.getContentPane().setLayout(null);
    
    txtC = new JTextField();
    txtC.setEditable(false);
    txtC.setBounds(0, 0, WIDTH, HEIGHT/9 - 5);
    txtC.setText("C211测试系统");
    txtC.setHorizontalAlignment(SwingConstants.CENTER);
    txtC.setForeground(new Color(255, 20, 147));
    txtC.setFont(new Font("等线", Font.BOLD, 50));
    txtC.setBackground(new Color(0, 204, 255));
    dataFrame.getContentPane().add(txtC);
    txtC.setColumns(10);
    
    JPanel resultPanel = new JPanel();
    resultPanel.setBounds(0, HEIGHT/9, WIDTH*10/16, HEIGHT*9/72);
    resultPanel.setBackground(new Color(211, 211, 211));
    dataFrame.getContentPane().add(resultPanel);
    resultPanel.setLayout(null);
    
    textField_1 = new JTextField();
    textField_1.setEditable(false);
    textField_1.setText("0");
    textField_1.setHorizontalAlignment(SwingConstants.CENTER);
    textField_1.setForeground(new Color(0, 128, 0));
    textField_1.setFont(new Font("等线", Font.PLAIN, 40));
    textField_1.setBounds(60, 39, 160, 60);
    textField_1.setBackground(new Color(189, 183, 107));
    resultPanel.add(textField_1);
    textField_1.setColumns(10);
    
    textField_2 = new JTextField();
    textField_2.setEditable(false);
    textField_2.setText("0");
    textField_2.setHorizontalAlignment(SwingConstants.CENTER);
    textField_2.setForeground(new Color(0, 128, 0));
    textField_2.setFont(new Font("等线", Font.PLAIN, 40));
    textField_2.setBounds(300, 39, 160, 60);
    textField_2.setBackground(new Color(189, 183, 107));
    textField_2.setColumns(10);
    resultPanel.add(textField_2);
    
    textField_3 = new JTextField();
    textField_3.setEditable(false);
    textField_3.setText("0");
    textField_3.setHorizontalAlignment(SwingConstants.CENTER);
    textField_3.setFont(new Font("等线", Font.PLAIN, 40));
    textField_3.setForeground(new Color(255, 0, 0));
    textField_3.setBounds(540, 39, 160, 60);
    textField_3.setBackground(new Color(189, 183, 107));
    textField_3.setColumns(10);
    resultPanel.add(textField_3);
    
    textField_4 = new JTextField();
    textField_4.setEditable(false);
    textField_4.setText("0");
    textField_4.setHorizontalAlignment(SwingConstants.CENTER);
    textField_4.setFont(new Font("等线", Font.PLAIN, 40));
    textField_4.setForeground(new Color(0, 128, 0));
    textField_4.setBounds(770, 39, 160, 60);
    textField_4.setBackground(new Color(189, 183, 107));
    textField_4.setColumns(10);
    resultPanel.add(textField_4);
    
    lblNewLabel = new JLabel("测试总数：");
    lblNewLabel.setFont(new Font("等线", Font.BOLD, 16));
    lblNewLabel.setForeground(new Color(50, 205, 50));
    lblNewLabel.setBounds(60, 10, 110, 25);
    resultPanel.add(lblNewLabel);
    
    lblOk = new JLabel("OK：");
    lblOk.setForeground(new Color(50, 205, 50));
    lblOk.setFont(new Font("等线", Font.BOLD, 16));
    lblOk.setBounds(300, 10, 99, 25);
    resultPanel.add(lblOk);
    
    lblNg = new JLabel("NG：");
    lblNg.setForeground(new Color(255, 0, 0));
    lblNg.setFont(new Font("等线", Font.BOLD, 16));
    lblNg.setBounds(540, 10, 99, 25);
    resultPanel.add(lblNg);
    
    label_2 = new JLabel("测试时间：");
    label_2.setForeground(new Color(50, 205, 50));
    label_2.setFont(new Font("等线", Font.BOLD, 16));
    label_2.setBounds(770, 10, 122, 25);
    resultPanel.add(label_2);
    
    menuPanel = new JPanel();
    menuPanel.setBounds(WIDTH*12/16 + 10, HEIGHT/9, WIDTH*4/16, HEIGHT*8/9 - 5);
    menuPanel.setBackground(new Color(216, 191, 216));
    dataFrame.getContentPane().add(menuPanel);
    menuPanel.setLayout(null);
    
    menuBar = new JMenuBar();
    menuBar.setBounds(10, 25, 116, 28);
    menuBar.setForeground(new Color(255, 228, 181));
    menuBar.setBackground(new Color(245, 222, 179));
    menuPanel.add(menuBar);
    
    mnNewMenu = new JMenu("文件");
    mnNewMenu.setFont(new Font("黑体", Font.PLAIN, 14));
    menuBar.add(mnNewMenu);
    
    mntmNewMenuItem = new JMenuItem("退出系统");
    mntmNewMenuItem.setFont(new Font("黑体", Font.PLAIN, 14));
    mntmNewMenuItem.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent e) {
        int num = JOptionPane.showConfirmDialog(null, "确认退出？", "提示", JOptionPane.YES_NO_OPTION);
        if(num == JOptionPane.YES_OPTION)
          System.exit(0);
      }
    });
    
    mntmNewMenuItem_1 = new JMenuItem("打开机型");
    mntmNewMenuItem_1.setFont(new Font("黑体", Font.PLAIN, 14));
    mnNewMenu.add(mntmNewMenuItem_1);
    
    mntmNewMenuItem_2 = new JMenuItem("用户管理");
    mntmNewMenuItem_2.setFont(new Font("黑体", Font.PLAIN, 14));
    mnNewMenu.add(mntmNewMenuItem_2);
    
    menuItem = new JMenuItem("注销用户");
    menuItem.setFont(new Font("黑体", Font.PLAIN, 14));
    mnNewMenu.add(menuItem);
    mnNewMenu.add(mntmNewMenuItem);
    
    mnNewMenu_1 = new JMenu("编辑");
    mnNewMenu_1.setFont(new Font("黑体", Font.PLAIN, 14));
    menuBar.add(mnNewMenu_1);
    
    testToolMenu = new JMenuItem("调试工具");
    testToolMenu.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent e) {
        if(userName.equals("admin"))
          USBHelperTool.main(null);
        else
          JOptionPane.showMessageDialog(null, "你没有权限操作", "警告", JOptionPane.WARNING_MESSAGE);
      }
    });
    testToolMenu.setFont(new Font("黑体", Font.PLAIN, 14));
    mnNewMenu_1.add(testToolMenu);
    
    menuItem_2 = new JMenuItem("测试选项");
    menuItem_2.setFont(new Font("黑体", Font.PLAIN, 14));
    mnNewMenu_1.add(menuItem_2);
    
    menuItem_3 = new JMenuItem("查看测试报告");
    menuItem_3.setFont(new Font("黑体", Font.PLAIN, 14));
    mnNewMenu_1.add(menuItem_3);
    
    menuItem_4 = new JMenuItem("查看测试结果");
    menuItem_4.setFont(new Font("黑体", Font.PLAIN, 14));
    mnNewMenu_1.add(menuItem_4);
    
    mnNewMenu_2 = new JMenu("系统");
    mnNewMenu_2.setFont(new Font("黑体", Font.PLAIN, 14));
    menuBar.add(mnNewMenu_2);
    
    JMenuItem mntmAbout = new JMenuItem("About");
    mntmAbout.setFont(new Font("黑体", Font.PLAIN, 14));
    mnNewMenu_2.add(mntmAbout);
    
    JMenuItem mntmHelp = new JMenuItem("Help");
    mntmHelp.setFont(new Font("黑体", Font.PLAIN, 14));
    mnNewMenu_2.add(mntmHelp);
    
    JMenuItem menuItem_5 = new JMenuItem("软件注册");
    menuItem_5.setFont(new Font("黑体", Font.PLAIN, 14));
    mnNewMenu_2.add(menuItem_5);
    
    JMenuItem menuItem_6 = new JMenuItem("软件更新");
    menuItem_6.setFont(new Font("黑体", Font.PLAIN, 14));
    mnNewMenu_2.add(menuItem_6);
    
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
    txtStop.setFont(new Font("等线", Font.BOLD, 70));
    txtStop.setBounds(0, 0, WIDTH*2/16, HEIGHT*9/72);
    txtStop.setBackground(new Color(255, 255, 0));
    runPanel.add(txtStop);
    txtStop.setColumns(10);
    
    JProgressBar progressBar = new JProgressBar();
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
            }
            //progressBar.setString("OVER");
            txtStop.setText("STOP");
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
    serialPanel.setBackground(new Color(255, 228, 225));
    dataFrame.getContentPane().add(serialPanel);
    serialPanel.setLayout(null);
    
    JButton COM1Butt = new JButton("COM1");
    COM1Butt.setBackground(new Color(255, 228, 225));
    COM1Butt.setBounds(24, 10, 93, 23);
    serialPanel.add(COM1Butt);
    
    JButton COM2Butt = new JButton("COM2");
    COM2Butt.setBackground(new Color(255, 228, 225));
    COM2Butt.setBounds(161, 10, 93, 23);
    serialPanel.add(COM2Butt);
    
    JButton COM3Butt = new JButton("COM3");
    COM3Butt.setBackground(new Color(255, 228, 225));
    COM3Butt.setBounds(299, 10, 93, 23);
    serialPanel.add(COM3Butt);
    
    JButton COM4Butt = new JButton("COM4");
    COM4Butt.setBackground(new Color(255, 228, 225));
    COM4Butt.setBounds(434, 10, 93, 23);
    serialPanel.add(COM4Butt);
    
    JButton COM5Butt = new JButton("COM5");
    COM5Butt.setBackground(new Color(255, 228, 225));
    COM5Butt.setBounds(568, 10, 93, 23);
    serialPanel.add(COM5Butt);
    
    JButton COM6Butt = new JButton("COM6");
    COM6Butt.setBackground(new Color(255, 228, 225));
    COM6Butt.setBounds(691, 10, 93, 23);
    serialPanel.add(COM6Butt);
    
    COM7Butt = new JButton("COM7");
    COM7Butt.setBackground(new Color(255, 228, 225));
    COM7Butt.setBounds(819, 10, 93, 23);
    serialPanel.add(COM7Butt);
    
    COM8Butt = new JButton("COM8");
    COM8Butt.setBackground(new Color(255, 228, 225));
    COM8Butt.setBounds(938, 10, 93, 23);
    serialPanel.add(COM8Butt);
   
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
    colTestResult.setPreferredWidth(150);
    //colTestvalue.setMaxWidth(100);
    //colTestvalue.setMinWidth(100);

    table.setEnabled(false);  //内容不可编辑
    DefaultTableCellRenderer   r   =   new   DefaultTableCellRenderer();  //设置
    r.setHorizontalAlignment(JLabel.CENTER);  //单元格内容
    table.setDefaultRenderer(Object.class,   r); //居中显示
    
    table.setRowHeight(30);  //设置行高
    //增加一行空白行
    DefaultTableModel tableModel = (DefaultTableModel) table.getModel(); 
    tableModel.addRow(new Object[]{"*", "", "", "", "", "", "", "", "", "", "", "", ""}); 
    //table.setGridColor(Color.PINK);   //设置网格颜色
    table.setForeground(Color.BLUE);  //设置文字颜色
    table.setFont(new Font("", Font.PLAIN, 12));
    table.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);//关闭表格列自动调整
    return table;
  }
}
