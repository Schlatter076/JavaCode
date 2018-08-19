package c211.test;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JScrollPane;

import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;

import java.awt.BorderLayout;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class PieChart_1 {

  private JFrame frame;
  private int okCount = 10;
  private int ngCount = 1;
  private JScrollPane scrollPane;
  private PieChart pieChart;
  private ChartPanel chartPanel;

  /**
   * Launch the application.
   */
  public static void main(String[] args) {
    EventQueue.invokeLater(new Runnable() {
      public void run() {
        try {
          PieChart_1 window = new PieChart_1();
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
  public PieChart_1() {
    initialize();
  }

  /**
   * Initialize the contents of the frame.
   */
  private void initialize() {
    frame = new JFrame();
    frame.setBounds(100, 100, 727, 583);
    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    frame.getContentPane().setLayout(null);
    
    pieChart = new PieChart(10, 2);
    chartPanel = pieChart.getChartPanel();
    
    scrollPane = new JScrollPane(chartPanel);
    scrollPane.setBounds(0, 0, 711, 458);
    frame.getContentPane().add(scrollPane); 
    
    JButton btnNewButton = new JButton("测试按钮");
    btnNewButton.addMouseListener(new MouseAdapter() {
      @Override
      public void mouseClicked(MouseEvent e) {
        
        /*okCount += 2;
        ngCount++;
        //pieChart = new PieChart(okCount, ngCount);
        //chartPanel = pieChart.getChartPanel();
        scrollPane = new JScrollPane(new PieChart(okCount, ngCount).getChartPanel());
        //scrollPane.setBounds(0, 0, 711, 458);
        frame.getContentPane().add(scrollPane); */
      }
    });
    
    btnNewButton.setBounds(267, 486, 93, 23);
    frame.getContentPane().add(btnNewButton);
    
  }
}
