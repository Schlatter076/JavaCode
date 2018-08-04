package c211.serialException;

public class InputStreamCloseFailException extends Exception {

  /**
   * 
   */
  private static final long serialVersionUID = 1L;
  
  public InputStreamCloseFailException() {}

  @Override
  public String toString() {
    // TODO 自动生成的方法存根
    return "关闭串口对象输入流（InputStream）时出错！";
  }
  
  
  
}