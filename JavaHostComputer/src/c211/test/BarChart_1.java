package c211.test;

import java.awt.EventQueue;

import javax.swing.JFrame;

import javax.swing.JScrollPane;

public class BarChart_1 {

  private JFrame frame;

  /**
   * Launch the application.
   */
  public static void main(String[] args) {
    EventQueue.invokeLater(new Runnable() {
      public void run() {
        try {
          BarChart_1 window = new BarChart_1();
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
  public BarChart_1() {
    initialize();
  }

  /**
   * Initialize the contents of the frame.
   */
  private void initialize() {
    frame = new JFrame();
    frame.setBounds(100, 100, 766, 545);
    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    frame.getContentPane().setLayout(null);

    BarChart barChart = new BarChart();
    // frame.getContentPane().add(barChart.getChartPanel());

    JScrollPane scrollPane = new JScrollPane(barChart.getChartPanel());
    scrollPane.setBounds(0, 0, 750, 506);
    frame.getContentPane().add(scrollPane);
  }
}
