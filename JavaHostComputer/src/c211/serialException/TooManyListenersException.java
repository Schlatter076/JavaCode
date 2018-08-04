package c211.serialException;

public class TooManyListenersException extends Exception {

  private static final long serialVersionUID = 1L;

  public TooManyListenersException() {}

  @Override
  public String toString() {
    // TODO 自动生成的方法存根
    return "串口监听类数量过多！添加操作失败";
  } 
  
}