package c211.serialException;

public class ReadDataFromSerialFailException extends Exception {
  
  private static final long serialVersionUID = 1L;
  
  public ReadDataFromSerialFailException() {}

  @Override
  public String toString() {
    // TODO 自动生成的方法存根
    return "从串口读取数据时出错！";
  }
  
}