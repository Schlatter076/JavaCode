package c211.serial;

import java.awt.EventQueue;
import java.awt.Image;
import java.awt.Toolkit;

import javax.swing.JFrame;
import javax.swing.JTextArea;
import java.awt.Color;
import java.awt.Font;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.JScrollPane;
import javax.swing.ScrollPaneConstants;

public class SerialPortHelper {

  private JFrame frame;
  private JTextField textField;
  private JTextField orderField;
  private JTextField nameField;

  /**
   * Launch the application.
   */
  public static void main(String[] args) {
    EventQueue.invokeLater(new Runnable() {
      public void run() {
        try {
          SerialPortHelper window = new SerialPortHelper();
          window.frame.setVisible(true);
        } catch (Exception e) {
          e.printStackTrace();
        }
      }
    });
  }

  /**
   * Create the application.
   */
  public SerialPortHelper() {
    initialize();
  }

  /**
   * Initialize the contents of the frame.
   */
  private void initialize() {
    frame = new JFrame();
    frame.getContentPane().setBackground(new Color(255, 228, 225));
    frame.setBounds(100, 100, 681, 562);
    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    frame.setResizable(false); //窗口大小不可更改
    //frame.setUndecorated(true); //去掉标题栏
    frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE); 
    
    //更换背景图片 
    ImageIcon img_1 = new ImageIcon("src/back.jpg"); 
    JLabel imgLabel = new JLabel(img_1); //JLabel imgLabel = new JLabel(new ImageIcon("back.jpg"));
    frame.getLayeredPane().add(imgLabel, new Integer(Integer.MIN_VALUE)); 
    imgLabel.setBounds(0,0,img_1.getIconWidth(), img_1.getIconHeight()); //背景图片的位置
    //将contentPane设置成透明的 
    ((JPanel)frame.getContentPane()).setOpaque(false);
    
    Toolkit tk = Toolkit.getDefaultToolkit();
    Image img = tk.getImage("src/Kyokuto.png"); //替换窗口的咖啡图标
    frame.setIconImage(img);
    frame.getContentPane().setLayout(null);
    
    JButton viewButton = new JButton("查看可用USB资源");
    viewButton.setBounds(466, 10, 196, 148);
    viewButton.setBackground(new Color(102, 205, 170));
    viewButton.setForeground(new Color(255, 0, 255));
    viewButton.setFont(new Font("微软雅黑", Font.PLAIN, 20));
    frame.getContentPane().add(viewButton);
    
    textField = new JTextField();
    textField.setToolTipText("");
    textField.setFont(new Font("微软雅黑", Font.PLAIN, 40));
    textField.setForeground(new Color(0, 255, 0));
    textField.setHorizontalAlignment(SwingConstants.CENTER);
    textField.setBounds(10, 10, 425, 148);
    textField.setBackground(new Color(0, 0, 0));
    frame.getContentPane().add(textField);
    textField.setColumns(10);
    
    JLabel nameLabel = new JLabel("资源名称:");
    nameLabel.setBounds(10, 168, 75, 15);
    nameLabel.setForeground(new Color(0, 250, 154));
    nameLabel.setFont(new Font("微软雅黑", Font.PLAIN, 14));
    frame.getContentPane().add(nameLabel);
    
    JButton openButt = new JButton("打开USB接口");
    openButt.setBounds(550, 225, 112, 36);
    openButt.setBackground(new Color(102, 205, 170));
    openButt.setFont(new Font("微软雅黑", Font.PLAIN, 12));
    frame.getContentPane().add(openButt);
    
    JButton closeButt = new JButton("关闭USB接口");
    closeButt.setBounds(428, 225, 112, 36);
    closeButt.setBackground(new Color(102, 205, 170));
    closeButt.setFont(new Font("微软雅黑", Font.PLAIN, 12));
    frame.getContentPane().add(closeButt);
    JScrollPane scrollPane = new JScrollPane();
    scrollPane.setBounds(10, 364, 530, 159);
    scrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
    frame.getContentPane().add(scrollPane);
    
    JTextArea recieveField = new JTextArea();
    recieveField.setForeground(new Color(0, 0, 255));
    recieveField.setBackground(new Color(255, 245, 238));
    recieveField.setFont(new Font("微软雅黑", Font.PLAIN, 16));
    scrollPane.setViewportView(recieveField);
    
    JButton clearButt = new JButton("清除");
    clearButt.setBounds(556, 419, 106, 60);
    clearButt.setFont(new Font("微软雅黑", Font.PLAIN, 18));
    clearButt.setBackground(new Color(102, 205, 170));
    frame.getContentPane().add(clearButt);
    
    orderField = new JTextField();
    orderField.setText("*IDN?\\n");
    orderField.setForeground(new Color(0, 0, 255));
    orderField.setHorizontalAlignment(SwingConstants.CENTER);
    orderField.setFont(new Font("微软雅黑", Font.PLAIN, 14));
    orderField.setBounds(10, 280, 655, 27);
    frame.getContentPane().add(orderField);
    orderField.setColumns(10);
    
    JLabel orderLabel = new JLabel("字符会话指令：");
    orderLabel.setForeground(new Color(0, 250, 154));
    orderLabel.setFont(new Font("微软雅黑", Font.PLAIN, 14));
    orderLabel.setBounds(10, 253, 109, 23);
    frame.getContentPane().add(orderLabel);
    
    JButton writeButt = new JButton("写");
    writeButt.setBackground(new Color(102, 205, 170));
    writeButt.setFont(new Font("微软雅黑", Font.PLAIN, 14));
    writeButt.setBounds(578, 317, 84, 36);
    frame.getContentPane().add(writeButt);
    
    JButton readButt = new JButton("读");
    readButt.setFont(new Font("微软雅黑", Font.PLAIN, 14));
    readButt.setBackground(new Color(102, 205, 170));
    readButt.setBounds(484, 317, 84, 36);
    frame.getContentPane().add(readButt);
    
    JButton checkButt = new JButton("查询");
    checkButt.setFont(new Font("微软雅黑", Font.PLAIN, 14));
    checkButt.setBackground(new Color(102, 205, 170));
    checkButt.setBounds(390, 317, 84, 36);
    frame.getContentPane().add(checkButt);
    
    nameField = new JTextField();
    nameField.setText("GPIB0::2::INSTR");
    nameField.setForeground(new Color(0, 255, 0));
    nameField.setFont(new Font("微软雅黑", Font.PLAIN, 14));
    nameField.setHorizontalAlignment(SwingConstants.CENTER);
    nameField.setBackground(new Color(0, 0, 0));
    nameField.setBounds(10, 188, 652, 27);
    frame.getContentPane().add(nameField);
    nameField.setColumns(10);
    
    JLabel recieveLabel = new JLabel("接收区：");
    recieveLabel.setForeground(new Color(0, 250, 154));
    recieveLabel.setFont(new Font("微软雅黑", Font.PLAIN, 14));
    recieveLabel.setBounds(10, 341, 109, 23);
    frame.getContentPane().add(recieveLabel);
  }
  
}
