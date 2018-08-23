package c211.client;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

import java.awt.AWTEvent;
import java.awt.Color;
import java.awt.Font;
import java.awt.Image;
import java.awt.Toolkit;

import javax.swing.JButton;
import javax.swing.UIManager;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JPasswordField;
import javax.swing.JComboBox;
import javax.swing.SwingConstants;
import java.awt.event.ActionListener;
import java.awt.event.AWTEventListener;
import java.awt.event.ActionEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class SignIn {

  private JFrame signInFrame;
  private JTextField productTextF;
  private JPasswordField passwordField;
  private JLabel productLabel;
  private JButton addProductButt;
  private JLabel idLabel;
  private JComboBox<String> idField;
  private JLabel passwordLabel;
  private JButton signInButt;
  private JButton exitButt;
  private User commom;
  private User admin;
  private boolean isDataView = false;

  /**
   * 主方法，运行登录界面
   * 
   * @param args
   */
  public static void main(String[] args) {
    EventQueue.invokeLater(new Runnable() {
      public void run() {
        try {
          SignIn window = new SignIn();
          window.signInFrame.setVisible(true);
        } catch (Exception e) {
          e.printStackTrace();
        }
      }
    });
  }

  /**
   * 构造器
   */
  public SignIn() {
    initialize();
  }

  /**
   * 初始化界面
   */
  private void initialize() {
    signInFrame = new JFrame();
    signInFrame.setFont(new Font("SimSun-ExtB", Font.PLAIN, 12));
    signInFrame.setTitle("\u767B\u5F55");
    signInFrame.setBounds(100, 100, 438, 326);
    signInFrame.setResizable(false); //窗口大小不可更改
    signInFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); 
    
    Toolkit tk = Toolkit.getDefaultToolkit();
    //全局添加键盘监听事件
    tk.addAWTEventListener(new AWTEventListener() {
      
      @Override
      public void eventDispatched(AWTEvent event) {
        if(!isDataView) {
        //当有键按下，且为ENTER键时，点击登录按钮
          if(((KeyEvent) event).getID() == KeyEvent.KEY_PRESSED && ((KeyEvent) event).getKeyChar() == KeyEvent.VK_ENTER) {
            signInButt.doClick();
          }
        }
      }
    }, AWTEvent.KEY_EVENT_MASK);
    
    Image img = tk.getImage("src/Kyokuto.png"); //替换窗口的咖啡图标
    signInFrame.setIconImage(img);
    signInFrame.getContentPane().setLayout(null);

    productTextF = new JTextField();
    productTextF.setEditable(false);
    productTextF.setText("C211");
    productTextF.setHorizontalAlignment(SwingConstants.CENTER);
    productTextF.setForeground(Color.RED);
    productTextF.setBounds(34, 40, 362, 40);
    productTextF.setFont(new Font("楷体", Font.PLAIN, 26));
    productTextF.setBackground(Color.BLACK);
    signInFrame.getContentPane().add(productTextF);
    productTextF.setColumns(8);
    
    productLabel = new JLabel("产品型号:");
    productLabel.setBounds(35, 11, 82, 27);
    productLabel.setFont(new Font("等线", Font.PLAIN, 14));
    signInFrame.getContentPane().add(productLabel);
    
    addProductButt = new JButton("\u6DFB\u52A0\u4EA7\u54C1\u578B\u53F7");
    addProductButt.setBackground(new Color(224, 255, 255));
    addProductButt.addMouseListener(new MouseAdapter() {
      @Override
      public void mouseClicked(MouseEvent e) {
        JOptionPane.showMessageDialog(addProductButt, "目前只有一个机种", "提示", JOptionPane.NO_OPTION);
      }
    });
    addProductButt.setBounds(259, 90, 137, 37);
    addProductButt.setFont(new Font("等线", Font.PLAIN, 14));
    signInFrame.getContentPane().add(addProductButt);
    
    idLabel = new JLabel("账号:");
    idLabel.setBounds(34, 113, 54, 21);
    idLabel.setFont(new Font("等线", Font.PLAIN, 14));
    signInFrame.getContentPane().add(idLabel);
    
  //获取用户名和密码
    passwordField = new JPasswordField();  //新建密码区域
    //passwordField.setRequestFocusEnabled(true);
    //passwordField.requestFocus(true);
    commom = UserTools.getUserByName("commom");
    admin = UserTools.getUserByName("admin");
    idField = new JComboBox<String>();
    idField.setFont(new Font("等线", Font.PLAIN, 16));
    idField.setBackground(new Color(255, 250, 250));
    idField.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent e) {
        if(!idField.getEditor().getItem().equals(commom.getUsername()))
          passwordField.setText("");
        else
          passwordField.setText(commom.getPassword());
      }
    });
    idField.addItem(commom.getUsername());
    idField.addItem(admin.getUsername());
    //idField.setMaximumRowCount(2);    
    idField.setBounds(34, 135, 362, 27);
    idField.setEditable(true);
    signInFrame.getContentPane().add(idField);
    
    passwordLabel = new JLabel("密码:");
    passwordLabel.setBounds(34, 166, 54, 15);
    passwordLabel.setFont(new Font("等线", Font.PLAIN, 14));
    signInFrame.getContentPane().add(passwordLabel);
    
    //passwordField = new JPasswordField();
    passwordField.addKeyListener(new KeyAdapter() {
      @Override
      public void keyPressed(KeyEvent e) {
        //监听Enter键
        if(e.getKeyChar() == KeyEvent.VK_ENTER ) {
          //getSignInEvent();
          signInButt.doClick();
        }  
      }
    });
    passwordField.setText(commom.getPassword());
    //passwordField.setFocusable(true);
    //passwordField.setRequestFocusEnabled(true);
    passwordField.setColumns(10);
    passwordField.setEchoChar('*');
    passwordField.setBounds(34, 181, 362, 27);
    passwordField.setFont(new Font("等线", Font.BOLD, 16));
    signInFrame.getContentPane().add(passwordField);
    
    signInButt = new JButton("\u7528\u6237\u767B\u5F55");
    signInButt.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent e) {
        getSignInEvent();
      }
    });
    signInButt.setBounds(192, 229, 204, 40);
    signInButt.setForeground(UIManager.getColor("ComboBox.foreground"));
    signInButt.setFont(new Font("等线", Font.BOLD, 16));
    signInButt.setBackground(new Color(224, 255, 255));
    signInFrame.getContentPane().add(signInButt);
    
    exitButt = new JButton("\u9000\u51FA\u7CFB\u7EDF");
    exitButt.setBackground(new Color(224, 255, 255));
    exitButt.setForeground(UIManager.getColor("List.dropLineColor"));
    exitButt.setBounds(34, 229, 112, 40);
    exitButt.addMouseListener(new MouseAdapter() {
      @Override
      public void mouseClicked(MouseEvent e) {
          System.exit(0);
      }
    });
    exitButt.setFont(new Font("等线", Font.PLAIN, 14));
    signInFrame.getContentPane().add(exitButt);
    
  }

  /**
   * 登录事件
   */
  public void getSignInEvent() {
    boolean comUser = idField.getEditor().getItem().equals(commom.getUsername());
    // char[] 转换成String的方法:String str = String.valueOf(char[] ch)
    boolean comPwd = String.valueOf(passwordField.getPassword()).equals(commom.getPassword());
    boolean adminUser = idField.getEditor().getItem().equals(admin.getUsername());
    boolean adminPwd = String.valueOf(passwordField.getPassword()).equals(admin.getPassword());
    // 判断用户名和密码是否和数据库中保存的一致
    if ((comUser && comPwd) || (adminUser && adminPwd)) {
      isDataView = true;
      signInFrame.dispose();
      DataView.getDataView(idField.getEditor().getItem().toString());
    } else
      JOptionPane.showMessageDialog(null, "用户名或密码不正确", "错误", JOptionPane.ERROR_MESSAGE);
  }

}
