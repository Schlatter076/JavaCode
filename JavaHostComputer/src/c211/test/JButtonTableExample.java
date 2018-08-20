package c211.test;

import java.awt.*;  
import java.awt.event.*;  
import javax.swing.*;  
import javax.swing.table.*;  
  
/** 
* @version 1.0 11/09/98 
*/  
public class JButtonTableExample extends JFrame {  
  /**
   * 
   */
  private static final long serialVersionUID = 1L;
  public JButtonTableExample(){  
    super( "JButtonTable Example" );  
      
    DefaultTableModel dm = new DefaultTableModel();  
    dm.setDataVector(new Object[][]{{"button 1","foo"},  
                                    {"button 2","bar"}},  
                     new Object[]{"Button","String"});  
                       
    JTable table = new JTable(dm);  
    table.getColumn("Button").setCellRenderer(new ButtonRenderer());  
    table.getColumn("Button").setCellEditor(new ButtonEditor(new JCheckBox()));  
    JScrollPane scroll = new JScrollPane(table);  
    getContentPane().add( scroll );  
    setSize( 400, 100 );  
    setVisible(true);  
  }  
  public static void main(String[] args) {  
    JButtonTableExample frame = new JButtonTableExample();  
    frame.addWindowListener(new WindowAdapter() {  
      public void windowClosing(WindowEvent e) {  
        System.exit(0);  
      }  
    });  
  }  
}
class ButtonRenderer extends JButton implements TableCellRenderer {  
  /**
   * 
   */
  private static final long serialVersionUID = 1L;

  public ButtonRenderer() {  
    setOpaque(true);  
  }  
    
  public Component getTableCellRendererComponent(JTable table, Object value,  
                   boolean isSelected, boolean hasFocus, int row, int column) {  
    if (isSelected) {  
      setForeground(table.getSelectionForeground());  
      setBackground(table.getSelectionBackground());  
    } else{  
      setForeground(table.getForeground());  
      setBackground(UIManager.getColor("Button.background"));  
    }  
    setText( (value ==null) ? "" : value.toString() );  
    return this;  
  }  
}
class ButtonEditor extends DefaultCellEditor {  
  /**
   * 
   */
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
      button.setForeground(table.getSelectionForeground());  
      button.setBackground(table.getSelectionBackground());  
    } else{  
      button.setForeground(table.getForeground());  
      button.setBackground(table.getBackground());  
    }  
    label = (value ==null) ? "" : value.toString();  
    button.setText( label );  
    isPushed = true;  
    return button;  
  }  
  public Object getCellEditorValue() {  
    if (isPushed)  {  
      //   
      //   
      JOptionPane.showMessageDialog(button ,label + ": Ouch!");  
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



