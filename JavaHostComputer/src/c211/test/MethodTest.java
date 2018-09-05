package c211.test;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import c211.serial.SerialPortTool;

public class MethodTest {
  
  public static void main(String[] args) {
    System.out.println(SerialPortTool.byteAsciiToChar(0x54));
    List<Integer> list = new ArrayList<Integer>();
    list.add(600);
    for(int i = 0; i < 11; i++) {
      list.add(i);
    }
    for(int i = 90; i > 84; i--) {
      list.add(i);
    }
    System.out.println(list+ ":::" + list.size());
    Collections.sort(list);
    System.out.println(list+ ":::" + list.size());
    System.out.println(list.get(list.size() - 1));
  }
    
}
