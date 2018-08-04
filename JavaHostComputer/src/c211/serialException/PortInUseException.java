package c211.serialException;

public class PortInUseException extends Exception {
  
  private static final long serialVersionUID = 1L;
  
  public PortInUseException() {}

  @Override
  public String toString() {
    // TODO 自动生成的方法存根
    return "端口可能被占用，打开串口操作失败！";
  }
  
}