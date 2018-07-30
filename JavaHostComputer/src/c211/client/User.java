package c211.client;

public class User {
  private String username;
  private String password;
  private int limit;
  private int userid;
  
  public User() {}
  public User(String username, String password, int limit, int userid) {
    this.username = username;
    this.password = password;
    this.limit = limit;
    this.userid = userid;
  }
  public void setUsername(String username) {
    this.username = username;
  }
  public String getUsername() {
    return username;
  }
  public void setPassword(String password) {
    this.password = password;
  }
  public String getPassword() {
    return password;
  }
  public void setLimit(int limit) {
    this.limit = limit;
  }
  public int getLimit() {
    return limit;
  }
  public void setUserid(int userid) {
    this.userid = userid;
  }
  public int getUserid() {
    return userid;
  }
  
}