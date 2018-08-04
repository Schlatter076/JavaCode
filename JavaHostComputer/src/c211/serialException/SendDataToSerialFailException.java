package c211.serialException;

public class SendDataToSerialFailException extends Exception {
  
  private static final long serialVersionUID = 1L;
  
  public SendDataToSerialFailException() {}

  @Override
  public String toString() {
    // TODO 自动生成的方法存根
    return "往串口发送数据失败！";
  }
  
  
}