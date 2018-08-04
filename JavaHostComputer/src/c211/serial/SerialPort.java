package c211.serial;


public class SerialPort {
  private int xuhao;
  private String portname;
  private String baudrate;
  private String databits;
  private String stopbits;
  private String parity;
  
  /**
   * 构造器
   */
  public SerialPort() {}
  public SerialPort(int xuhao, String portname, String baudrate, String databits, String stopbits, String parity) {
    this.xuhao = xuhao;
    this.portname = portname;
    this.baudrate = baudrate;
    this.databits = databits;
    this.stopbits = stopbits;
    this.parity = parity;
  }
  public void setXuhao(int xuhao) {
    this.xuhao = xuhao;
  }
  public int getXuhao() {
    return xuhao;
  }
  public void setPortname(String portname) {
    this.portname = portname;
  }
  public String getPortname() {
    return portname;
  }
  public void setBaudrate(String baudrate) {
    this.baudrate = baudrate;
  }
  public String getBaudrate() {
    return baudrate;
  }
  public void setDatabits(String databits) {
    this.databits = databits;
  }
  public String getDatabits() {
    return databits;
  }
  public void setStopbits(String stopbits) {
    this.stopbits = stopbits;
  }
  public String getStopbits() {
    return stopbits;
  }
  public void setParity(String parity) {
    this.parity = parity;
  }
  public String getParity() {
    return parity;
  } 
  
}