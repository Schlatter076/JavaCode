package c211.client;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Iterator;
import java.util.List;
import java.util.Vector;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import javax.swing.table.TableColumn;

import c211.db.C211RecoupData;
import c211.db.RecoupDataTool;

public class C211Recoup {

  private JFrame frame;
  private JTable table;
  private JButton modifyButt;
  private DefaultTableModel dtm;
  private JScrollPane scrollPane;
  
  /**
   * c211补偿值对象
   */
  public static void getRecoup() {
    EventQueue.invokeLater(new Runnable() {
      public void run() {
        try {
          C211Recoup window = new C211Recoup();
          window.frame.setVisible(true);
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
          C211Recoup window = new C211Recoup();
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
  public C211Recoup() {
    initialize();
  }

  /**
   * Initialize the contents of the frame.
   */
  private void initialize() {
    frame = new JFrame();
    frame.setTitle("C211补偿值");
    frame.setBounds(100, 100, 706, 173);
    frame.setResizable(false); // 窗口大小不可改
    Toolkit tk = Toolkit.getDefaultToolkit();
    Image img = tk.getImage(frame.getClass().getResource("/Kyokuto.png"));// 替换窗口的咖啡图标
    frame.setIconImage(img);
    frame.getContentPane().setLayout(null);
    frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
    
    dtm = new DefaultTableModel(addRowDatas(), addColName());
    table = new JTable(dtm);
    table.setBackground(new Color(224, 255, 255));
    
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
    colNull.setPreferredWidth(15); // 设置第一列的列宽

    DefaultTableCellRenderer r = new DefaultTableCellRenderer(); // 设置
    r.setHorizontalAlignment(JLabel.CENTER); // 单元格内容
    table.setDefaultRenderer(Object.class, r); // 居中显示
    table.setRowHeight(25); // 设置行高
    table.setForeground(new Color(139, 69, 19)); // 设置文字颜色
    table.setFont(new Font("等线", Font.PLAIN, 14));
    
    scrollPane = new JScrollPane(table);
    scrollPane.setBounds(0, 0, 700, 84);
    frame.getContentPane().add(scrollPane);
    
    modifyButt = new JButton("修改");
    modifyButt.setBackground(new Color(224, 255, 255));
    modifyButt.setFont(new Font("宋体", Font.PLAIN, 16));
    modifyButt.setBounds(590, 94, 100, 37);
    frame.getContentPane().add(modifyButt);
    
    modifyButt.addActionListener(new ActionListener() {
      
      @Override
      public void actionPerformed(ActionEvent e) {
        if(isModify()) {
          JOptionPane.showMessageDialog(null, "修改成功");
        }
        else
          JOptionPane.showMessageDialog(null, "修改成功");
      }
    });
    
  }
  /**
   * 是否修改成功补偿值
   * @return
   */
  public boolean isModify() {
    double[] data = new double[8];
    try {
      for(int i = 0; i < 8; i++) {
        data[i] = Double.parseDouble(table.getValueAt(0, i + 1).toString());
      }
      //double[] datas = {0.23, 0.2, 0.14, 1.4, 0.0, 0.4, 0.4, 0.4};
      RecoupDataTool.updateC211_recoup(data);
      return true;
    } catch (Exception e1) {
      return false;
    }
  }
  /**
   * 列名
   * @return 列名集合
   */
  public Vector<String> addColName() {
    Vector<String> vt = new Vector<>();
    vt.add("*");
    vt.add("拉力1");
    vt.add("拉力2");
    vt.add("拉力3");
    vt.add("电阻");
    vt.add("电压");
    vt.add("行程1");
    vt.add("行程2");
    vt.add("行程3");
    vt.add("产品型号");
    return vt;
  }
  /**
   * 行数据
   * @return 行数据集合
   */
  public Vector<Object> addRowDatas() {
    Vector<Object> rowDatas = new Vector<>();
    List<C211RecoupData> list = RecoupDataTool.getC211RecoupData();
    if(list != null) {
      for(Iterator<C211RecoupData> it = list.iterator(); it.hasNext(); ) {
        Vector<String> vt = new Vector<>();
        C211RecoupData cd = it.next();
        vt.add(" ");
        vt.add(String.valueOf(cd.getPULL1()));
        vt.add(String.valueOf(cd.getPULL2()));
        vt.add(String.valueOf(cd.getPULL3()));
        vt.add(String.valueOf(cd.getRES()));
        vt.add(String.valueOf(cd.getVOL()));
        vt.add(String.valueOf(cd.getSTROKE1()));
        vt.add(String.valueOf(cd.getSTROKE2()));
        vt.add(String.valueOf(cd.getSTROKE3()));
        vt.add(cd.getName());
        
        rowDatas.add(vt);
      }
    }
    return rowDatas;
  }
}
