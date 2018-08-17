package c211.test;

import java.time.*;
/**
 * 打印当月日历
 * @author Jeffrey
 * coding: utf-8
 */
public class CalendarTest {
  public static void main(String[] args) {
    //从LocalDete类创建date对象
    LocalDate date = LocalDate.now();  
    //timeField.setForeground(new Color(51, 255, 0));
    //timeField.setFont(new Font("等线", Font.BOLD | Font.ITALIC, 14));
    System.out.println(date);
    //获取当前月份
    int month = date.getMonthValue();
    //获取当前日期
    int today = date.getDayOfMonth();
    //设置起始日期
    date = date.minusDays(today - 1);
    System.out.println(date);
    DayOfWeek weekDay = date.getDayOfWeek();
    int value = weekDay.getValue();//1 = Monday...7 = Sunday
    System.out.println(value);
    //打印日历头
    System.out.println("Mon Tue Wed Thu Fri Sat Sun");
    for(int i = 1; i < value; i++)
      System.out.print("    ");
    while(date.getMonthValue() == month) {
      System.out.printf("%3d", date.getDayOfMonth());
      if(date.getDayOfMonth() == today)
        System.out.print("*");
      else
        System.out.print(" ");
      date = date.plusDays(1);
      if(date.getDayOfWeek().getValue() == 1)
        System.out.println();
    }
    if(date.getDayOfWeek().getValue() != 1)
      System.out.println();
  }
}
