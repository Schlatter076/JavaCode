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
    
    List<Integer> listData = new ArrayList<>();
    for(int i = 0; i < 10; i++) {
      listData.add(i);
    }
    System.out.println(listData);
    listData.set(5, Integer.parseInt(String.valueOf(SerialPortTool.byteAsciiToChar(0x33))));
    System.out.println(listData);
    Collections.sort(listData);
    System.out.println(listData);
    
    StringBuilder strBuilder = new StringBuilder();
    strBuilder.append('3');
    strBuilder.append('0');
    strBuilder.append('3');
    strBuilder.append('3');
    
    System.out.println(Integer.parseInt(strBuilder.toString()) == 3033);
  }
    
}
