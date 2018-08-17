package c211.test;

import java.awt.Container;
import java.awt.EventQueue;
import java.awt.GridLayout;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
 
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JPanel;

import c211.client.SignIn;
/**
 * 
 * JComboBox选择日期
 *
 */
public class ChooseDate {
  private JFrame jf;
  private JComboBox combo1;
  private JComboBox combo2;
  private JComboBox combo3;
 
  public static void main(String[] args) {
    EventQueue.invokeLater(new Runnable() {
      public void run() {
        try {
          ChooseDate window = new ChooseDate();
          window.jf.setVisible(true);
        } catch (Exception e) {
          e.printStackTrace();
        }
      }
    });
  }
  
  // 构造函数
  public ChooseDate() {
    init();
  }
 
  // 初始化函数
  private void init() {
    // Frame框架,相框,这里是窗口框类型
    jf = new JFrame("ChooseDay");
    //获取面板容器
    Container c =jf.getContentPane();
    JPanel jp = new JPanel();
    c.add(jp);
    
    jp.setLayout(new GridLayout(1,3));
    
    //分别设置3个combobox内的值
    String[] arr1=new String[1000];//数组时个对象,对象在使用前要初始化
    for(int i=0;i<1000;i++){
      arr1[i] = i+2000+"年";
    }   
    combo1 = new JComboBox(arr1);
    
    String [] arr2 = new String[12];
    for(int i=0;i<12;i++){
      arr2[i]=i+1+"月";
    }
    combo2 = new JComboBox(arr2);
    
    String[] arr3 = new String[31];
    for(int i=0;i<31;i++){
      arr3[i]=i+1+"日";
    }
    combo3 = new JComboBox(arr3);
    
    //设置显示窗口初始坐标,长和宽
    jf.setBounds(500,300,500, 80);
    jp.add(combo1);
    jp.add(combo2);
    jp.add(combo3);
    
    myEvent();
    jf.setVisible(true);
  }
  
  private void myEvent(){
    jf.addWindowListener(new WindowAdapter(){
      public void windowClosing(WindowEvent e){
        System.exit(0);
      }     
    });
  }
}