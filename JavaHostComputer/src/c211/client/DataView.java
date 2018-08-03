package c211.client;

import c211.db.*;

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

public class DataView {

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
  private JMenuItem menuItem_1;
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
  
  //写一个静态方法供登录页面调用
  public static void dataViewShow() {
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

  /**
   * Create the application.
   */
  public DataView() {
    initialize();
  }

  /**
   * Initialize the contents of the frame.
   */
  private void initialize() {
    dataFrame = new JFrame();
    //dataFrame.getContentPane().setBackground(new Color(245, 245, 220));
    //自动获取屏幕分辨率
    Dimension viewSize = Toolkit.getDefaultToolkit().getScreenSize();
    dataFrame.setBounds(0, 0, viewSize.width, viewSize.height);
    dataFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    
    dataFrame.setResizable(false);  //窗口大小不可改
    dataFrame.setUndecorated(true);
    
    //更换背景图片 
    //ImageIcon img_1 = new ImageIcon("src/ground.jpg");
    ImageIcon img_1 = new ImageIcon("src/run.jpg"); 
    //ImageIcon img_1 = new ImageIcon("src/back.jpg"); 
    JLabel imgLabel = new JLabel(img_1); //JLabel imgLabel = new JLabel(new ImageIcon("back.jpg"));
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
    txtC.setBounds(0, 0, 1600, 63);
    txtC.setText("C211测试系统");
    txtC.setHorizontalAlignment(SwingConstants.CENTER);
    txtC.setForeground(new Color(255, 20, 147));
    txtC.setFont(new Font("等线", Font.BOLD, 45));
    txtC.setBackground(new Color(0, 204, 255));
    dataFrame.getContentPane().add(txtC);
    txtC.setColumns(10);
    
    JPanel resultPanel = new JPanel();
    resultPanel.setBounds(0, 75, 1000, 112);
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
    lblNewLabel.setFont(new Font("等线", Font.BOLD, 20));
    lblNewLabel.setForeground(new Color(50, 205, 50));
    lblNewLabel.setBounds(60, 10, 110, 25);
    resultPanel.add(lblNewLabel);
    
    lblOk = new JLabel("OK：");
    lblOk.setForeground(new Color(50, 205, 50));
    lblOk.setFont(new Font("等线", Font.BOLD, 20));
    lblOk.setBounds(300, 10, 99, 25);
    resultPanel.add(lblOk);
    
    lblNg = new JLabel("NG：");
    lblNg.setForeground(new Color(255, 0, 0));
    lblNg.setFont(new Font("等线", Font.BOLD, 20));
    lblNg.setBounds(540, 10, 99, 25);
    resultPanel.add(lblNg);
    
    label_2 = new JLabel("测试时间：");
    label_2.setForeground(new Color(50, 205, 50));
    label_2.setFont(new Font("等线", Font.BOLD, 20));
    label_2.setBounds(770, 10, 122, 25);
    resultPanel.add(label_2);
    
    menuPanel = new JPanel();
    menuPanel.setBounds(1200, 75, 400, 815);
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
    
    menuItem_1 = new JMenuItem("测试工具");
    menuItem_1.setFont(new Font("黑体", Font.PLAIN, 14));
    mnNewMenu_1.add(menuItem_1);
    
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
    runPanel.setBounds(1005, 75, 185, 685);
    runPanel.setBackground(new Color(255, 255, 255));
    runPanel.setOpaque(false);
    dataFrame.getContentPane().add(runPanel);
    runPanel.setLayout(null);
    
    txtStop = new JTextField();
    txtStop.setEditable(false);
    txtStop.setText("STOP");
    txtStop.setHorizontalAlignment(SwingConstants.CENTER);
    txtStop.setFont(new Font("等线", Font.BOLD, 70));
    txtStop.setBounds(0, 0, 185, 113);
    txtStop.setBackground(new Color(255, 255, 0));
    runPanel.add(txtStop);
    txtStop.setColumns(10);
    
    serialPanel = new JPanel();
    serialPanel.setBounds(0, 770, 1190, 120);
    serialPanel.setBackground(new Color(255, 228, 225));
    dataFrame.getContentPane().add(serialPanel);
    serialPanel.setLayout(null);
    
    //JTable table = new JTable(17, 10);  
    //table.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);//关闭表格列自动调整，此时水平滚动条可见 
    
    scrollPane = new JScrollPane(completedTable(getTestTable()));  //向滚动面板中添加JTable
    scrollPane.setBounds(15, 197, viewSize.width*5/8 - 15, viewSize.height*5/8);
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
    colNull.setPreferredWidth(20);
    colTestitem.setPreferredWidth(150);
    colMaxvalue.setPreferredWidth(100);
    colMinvalue.setPreferredWidth(100);
    colTestvalue.setPreferredWidth(100);
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
