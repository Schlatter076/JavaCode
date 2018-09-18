package c211.test;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JMenuBar;
import javax.swing.JOptionPane;
import javax.swing.JMenu;
import javax.swing.JPanel;
import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Container;

import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JSplitPane;
import javax.swing.JLayeredPane;
import java.awt.Color;
import javax.swing.JToolBar;

public class ContrlDemo {

  private JFrame frame;
  private JMenuBar menuBar;
  private JMenu menu1;
  private JMenu menu2;
  private JPanel testPanel;
  private JPanel debugPanel;
  private JButton debugButt;
  private Container container;
  /**
   * Launch the application.
   */
  public static void main(String[] args) {
    EventQueue.invokeLater(new Runnable() {
      public void run() {
        try {
          ContrlDemo window = new ContrlDemo();
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
  public ContrlDemo() {
    initialize();
  }

  /**
   * Initialize the contents of the frame.
   */
  private void initialize() {
    frame = new JFrame();
    frame.setTitle("菜单测试");
    frame.setBounds(100, 100, 827, 425);
    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    container = frame.getContentPane();
    
    menuBar = new JMenuBar();
    frame.setJMenuBar(menuBar); 
    menu1 = new JMenu("menu1");
    menu1.addMouseListener(new MouseAdapter() {
      @Override
      public void mouseClicked(MouseEvent e) {
        //JOptionPane.showMessageDialog(null, "测试");
        container.removeAll();
        container.add(testPanel);
        container.repaint();
      }
    });
    
    testPanel = new JPanel();
    container.add(testPanel, BorderLayout.CENTER);
    testPanel.setLayout(null);
    
    debugPanel = new JPanel();
    debugButt = new JButton("调试");
    debugPanel.add(debugButt);
    
    menuBar.add(menu1);
    
    menu2 = new JMenu("menu2");
    menu2.addMouseListener(new MouseAdapter() {
      @Override
      public void mouseClicked(MouseEvent e) {
        //JOptionPane.showMessageDialog(null, "调试");
        container.removeAll();
        //container.add(debugPanel, BorderLayout.CENTER);
        DebugPanel dPanel = new DebugPanel();
        container.add(dPanel, BorderLayout.CENTER);
        container.repaint();
      }
    });
    menuBar.add(menu2);
    
  }
  class DebugPanel extends JPanel {
    private static final long serialVersionUID = 1L;
    public DebugPanel() {
      JButton debugButt = new JButton("调试");
      add(debugButt);
    }
  }
}
