package c211.test;

import java.awt.Font;
import java.awt.Toolkit;
import java.text.DecimalFormat;
import java.text.NumberFormat;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.labels.StandardPieSectionLabelGenerator;
import org.jfree.chart.plot.PiePlot;
import org.jfree.data.general.DefaultPieDataset;

public class PieChart {
  ChartPanel frame1;
  

  public PieChart(int ok, int ng) {
    
    DefaultPieDataset data = getDataSet(ok, ng);
    JFreeChart chart = ChartFactory.createPieChart3D("产品不良率", data, true, true, false);
    // 设置百分比
    PiePlot pieplot = (PiePlot) chart.getPlot();
    DecimalFormat df = new DecimalFormat("0.00%");// 获得一个DecimalFormat对象，主要是设置小数问题
    NumberFormat nf = NumberFormat.getNumberInstance();// 获得一个NumberFormat对象
    StandardPieSectionLabelGenerator sp1 = new StandardPieSectionLabelGenerator("{0}  {2}", nf, df);// 获得StandardPieSectionLabelGenerator对象
    pieplot.setLabelGenerator(sp1);// 设置饼图显示百分比

    // 没有数据的时候显示的内容
    pieplot.setNoDataMessage("暂无数据显示");
    pieplot.setCircular(true);  
    pieplot.setLabelGap(0.02D);

    pieplot.setIgnoreNullValues(true);// 设置不显示空值
    pieplot.setIgnoreZeroValues(true);// 设置不显示负值
    frame1 = new ChartPanel(chart, true);
    
    chart.getTitle().setFont(new Font("宋体", Font.BOLD, 20));// 设置标题字体
    PiePlot piePlot = (PiePlot) chart.getPlot();// 获取图表区域对象
    
    //背景色　透明度     
    piePlot.setBackgroundAlpha(1.0f);     
    //前景色　透明度     
    piePlot.setForegroundAlpha(1.0f);
    //piePlot.setLabelLinksVisible(true);
    
    piePlot.setBackgroundImage(Toolkit.getDefaultToolkit().getImage("src/run.jpg"));
    
    piePlot.setLabelFont(new Font("等线", Font.BOLD, 14));// 解决乱码
    chart.getLegend().setItemFont(new Font("等线", Font.BOLD, 14));
  }

  private static DefaultPieDataset getDataSet(int ok, int ng) {
    DefaultPieDataset dataset = new DefaultPieDataset();
    dataset.setValue("不良", ng);
    dataset.setValue("良品", ok);
    //dataset.setValue("葡萄", 300);
    //dataset.setValue("香蕉", 400);
    //dataset.setValue("荔枝", 500);
    return dataset;
  }

  public ChartPanel getChartPanel() {
    return frame1;

  }
}