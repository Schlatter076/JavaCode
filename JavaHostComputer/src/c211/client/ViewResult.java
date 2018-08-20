package c211.client;

import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Image;
import java.awt.Toolkit;
import java.util.Iterator;
import java.util.List;
import java.util.Vector;

import javax.swing.DefaultCellEditor;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import javax.swing.table.TableCellRenderer;
import javax.swing.table.TableColumn;

import c211.db.ECTESTSYSTools;
import c211.db.Recorddata;

import java.awt.Color;
import java.awt.Component;

import javax.swing.JScrollPane;
import javax.swing.JComboBox;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.time.LocalDate;

public class ViewResult {

  private JFrame frame;
  private JTable table;
  private JButton viewAllButt;
  private DefaultTableModel dtm;
  private JScrollPane scrollPane;
  private JComboBox<String> timeCbox;
  private JButton checkButt;
  private JButton exitButt;
  
  /**
   * 获取查看测试结果页面
   */
  public static void getViewResult() {
    EventQueue.invokeLater(new Runnable() {
      public void run() {
        try {
          ViewResult window = new ViewResult();
          window.frame.setVisible(true);
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
          ViewResult window = new ViewResult();
          window.frame.setVisible(true);
        } catch (Exception e) {
          e.printStackTrace();
        }
      }
    });
  }

  public ViewResult() {
    initialize();
  }

  /**
   * Initialize the contents of the frame.
   */
  private void initialize() {
    frame = new JFrame();
    frame.setTitle("查看测试结果");
    frame.setBounds(100, 100, 774, 532);
    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    frame.setResizable(false); // 窗口大小不可改
    //frame.setUndecorated(true);
    frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
    Toolkit tk = Toolkit.getDefaultToolkit();
    Image img = tk.getImage("src/Kyokuto.png"); //替换窗口的咖啡图标
    frame.setIconImage(img);
    frame.getContentPane().setLayout(null);
    
    Object[] colNames = {" ", "名称", "总数", "合格数", "失败数", "测试时长(秒)", "测试日期", "修改"};
    Object[][] rowDatas = {{"*", "C211", "200", "195", "5", "53", "2018-01-04", "修改"}};
    
    dtm = new DefaultTableModel(rowDatas, colNames);
    table = new JTable(dtm);
    table.setBackground(new Color(224, 255, 255));
    
    table.getColumn("修改").setCellRenderer(new ButtonRenderer());             //这两句
    table.getColumn("修改").setCellEditor(new ButtonEditor(new JCheckBox()));  //在table中添加JButton
    
    JTableHeader jTableHeader = table.getTableHeader(); // 获取表头
    // 设置表头名称字体样式
    jTableHeader.setFont(new Font("", Font.PLAIN, 14));
    // 设置表头名称字体颜色
    jTableHeader.setForeground(Color.BLACK);
    // 表头不可拖动
    jTableHeader.setReorderingAllowed(false);
    // 列大小不可改变
    jTableHeader.setResizingAllowed(false);
    
    TableColumn colNull = table.getColumnModel().getColumn(0);
    colNull.setPreferredWidth(15);  //设置第一列的列宽
    
    DefaultTableCellRenderer r = new DefaultTableCellRenderer(); // 设置
    r.setHorizontalAlignment(JLabel.CENTER); // 单元格内容
    table.setDefaultRenderer(Object.class, r); // 居中显示
    table.setRowHeight(25); // 设置行高
    table.setForeground(new Color(139, 69, 19)); // 设置文字颜色
    table.setFont(new Font("等线", Font.PLAIN, 14));

    dtm.addRow(addRowDatas("2018-01-05"));
    dtm.addRow(addRowDatas("2018-01-06"));
    dtm.addRow(addRowDatas("2018-01-07"));
    dtm.addRow(addRowDatas("2018-01-08"));
    dtm.addRow(addRowDatas("2018-01-09"));
    dtm.addRow(addRowDatas(LocalDate.now().toString()));
    
    scrollPane = new JScrollPane(table);
    scrollPane.setBounds(20, 42, 728, 439);
    frame.getContentPane().add(scrollPane);
    
    viewAllButt = new JButton("显示所有");
    viewAllButt.setBackground(new Color(224, 255, 255));
    viewAllButt.setFont(new Font("等线", Font.PLAIN, 14));
    viewAllButt.setBounds(20, 10, 93, 23);
    frame.getContentPane().add(viewAllButt);
    
    timeCbox = new JComboBox<String>();
    timeCbox.setBackground(new Color(255, 255, 255));
    timeCbox.setForeground(new Color(0, 0, 0));
    timeCbox.setFont(new Font("等线", Font.PLAIN, 14));
    timeCbox.setBounds(342, 11, 151, 21);
    frame.getContentPane().add(timeCbox);
    
    for(int i = 0; i < table.getRowCount(); i ++)
      timeCbox.addItem(getTableValueAt(i));
    
    checkButt = new JButton("查找");
    checkButt.setBackground(new Color(224, 255, 255));
    checkButt.setFont(new Font("等线", Font.PLAIN, 14));
    checkButt.setBounds(503, 9, 75, 23);
    frame.getContentPane().add(checkButt);
    
    exitButt = new JButton("退出");
    exitButt.setBackground(new Color(224, 255, 255));
    exitButt.setFont(new Font("等线", Font.PLAIN, 14));
    exitButt.setBounds(655, 9, 93, 23);
    exitButt.addMouseListener(new MouseAdapter() {
      @Override
      public void mouseClicked(MouseEvent e) {
        frame.dispose();
      }
    });
    frame.getContentPane().add(exitButt);
  }
  /**
   * 获取向表中插入的数据
   * @param recordtime 数据库里记录的时间
   * @return Vector
   */
  public static Vector<String> addRowDatas(String recordtime) {
    Vector<String> vt = new Vector<>();
    List<Recorddata> tableList = ECTESTSYSTools.getRecordtdData(recordtime, "C211");
    if(tableList != null) {
      for(Iterator<Recorddata> it = tableList.iterator(); it.hasNext();) {
        Recorddata rd = it.next();
        vt.add(" ");
        vt.add(rd.getRecordname());
        vt.add(rd.getRecordsum());
        vt.add(rd.getRecordok());
        vt.add(rd.getRecordng());
        vt.add(rd.getRecordts());
        vt.add(rd.getRecordtime());
        vt.add("修改");
      }
    }
    return vt;
  }
  /**
   * 获取表的模型，用来增加表数据
   * @return
   */
  public DefaultTableModel getDefaultTableModel() {
    return dtm;
  }
  /**
   * 获取测试结果表
   * @return
   */
  public JTable getTable() {
    return table;
  }
  /**
   * 获取测试日期单元格的数据
   * @param row 行数(从0开始)
   * @return 字符串型的单元格数据
   */
  public String getTableValueAt(int row) {
    return table.getValueAt(row, 6).toString();
  }
  
}
/////////////////////////////////////////////////////////////////////////////
class ButtonRenderer extends JButton implements TableCellRenderer {  

  private static final long serialVersionUID = 1L;

  public ButtonRenderer() {  
    setOpaque(true);  
  }  
    
  public Component getTableCellRendererComponent(JTable table, Object value,  
                   boolean isSelected, boolean hasFocus, int row, int column) {  
    
    super.setHorizontalAlignment(JLabel.CENTER); // 该列居中显示
    if (isSelected) {  
      setForeground(new Color(139, 69, 19));  
      setBackground(new Color(0, 204, 51));  
    } else{  
      setForeground(table.getForeground());  
      setBackground(table.getBackground());  
    }  
    setText( (value ==null) ? "" : value.toString() );  
    return this;  
  }  
}
class ButtonEditor extends DefaultCellEditor {  

  private static final long serialVersionUID = 1L;
  protected JButton button;  
  private String    label;  
  private boolean   isPushed;  
  public ButtonEditor(JCheckBox checkBox) {  
    super(checkBox);  
    button = new JButton();  
    button.setOpaque(true);  
    button.addActionListener(new ActionListener() {  
      public void actionPerformed(ActionEvent e) {  
        fireEditingStopped();  
      }  
    });  
  }  
  public Component getTableCellEditorComponent(JTable table, Object value,  
                   boolean isSelected, int row, int column) {  
    if (isSelected) {  
      button.setForeground(new Color(139, 69, 19));  
      button.setBackground(new Color(0, 204, 51));  
    } else{  
      button.setForeground(table.getForeground());  
      button.setBackground(table.getBackground());  
    }  
    label = (value ==null) ? "" : value.toString();  
    button.setText( label );  
    isPushed = true;  
    return button;  
  }
  //点击按键时触发
  public Object getCellEditorValue() {  
    if (isPushed)  {  
      
      //   
      //   
      JOptionPane.showMessageDialog(button ,label + "成功");  
      // System.out.println(label + ": Ouch!");  
    }  
    isPushed = false;  
    return new String( label ) ;  
  }  
    
  public boolean stopCellEditing() {  
    isPushed = false;  
    return super.stopCellEditing();  
  }  
  protected void fireEditingStopped() {  
    super.fireEditingStopped();  
  }  
}
