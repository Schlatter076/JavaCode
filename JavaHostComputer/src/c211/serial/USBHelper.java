package c211.serial;

import visatype.VisatypeLibrary;

import java.io.File;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;

import static org.testng.Assert.*;
import jvisa.JVisa;
import jvisa.JVisaException;
import jvisa.JVisaReturnNumber;
import jvisa.JVisaReturnString;
import visa.VisaLibrary;

/**
 * 通过USB连接仪器的一些操作方法
 * @author hw076
 *
 */
public class USBHelper {
  static JVisa instance;
  long viSession = 0;
  long viInstrument = 0;
  long timeOut = 2000;
  String read;
 
  protected static final String CLASS_NAME = USBHelper.class.getName();
 
  public static final Logger LOGGER = Logger.getLogger(CLASS_NAME);
  
  static final String LOG_PATH = "log/c211log.txt";
  
  static File logFile; 
  
  public static void main(String[] args) throws Exception {
    setUpClass();
    USBHelper me = new USBHelper();
    me.setLogFile();
    //String[] resources = ResourceManager.GetLocalManager().FindResources("?*");
    me.getVisaResourceManagerHandle();
    me.openInstrument("USB0::6833::2500::DM3R200200081::0::INSTR");
    me.setTimeout();
    me.visaWrite(":measure:current:DC?");
    System.out.println(Double.parseDouble(me.visaRead()));
    me.visaClear();
    
  }
  public static void setUpClass() throws Exception{
    instance = new JVisa();
    assertEquals(instance.openDefaultResourceManager(), VisatypeLibrary.VI_SUCCESS);
    logFile = new File(LOG_PATH);
  }
  public void setLogFile() {
    if(logFile.exists()) {
      logFile.delete();
    }
    instance.setLogFile(LOG_PATH);
    JVisa.logFileHandler.setFormatter(new SimpleFormatter());
    JVisa.LOGGER.setLevel(Level.FINE);
  }
  public void getVisaResourceManagerHandle() {
    viSession = instance.getResourceManagerHandle();
    assertTrue(viSession > 0L, String.format("Resource manager handle is 0x%08X", viSession));
  }
  public void openInstrument(String instrument) {
    try {
      long expResult = VisatypeLibrary.VI_SUCCESS;
      long result = instance.openInstrument(instrument);
      assertEquals(result, expResult);
      viInstrument = instance.getVisaInstrumentHandle();
      
    } catch(Exception e) {
      LOGGER.log(Level.SEVERE, null, e);
    }
    
  }
  public void visaClear() {
    try {
      long expResult = 0L;
      assertEquals(instance.clear(), expResult);
    } catch(Exception e) {
      LOGGER.log(Level.SEVERE, null, e);
    }
  }
  public void setTimeout() {
    JVisaReturnNumber timeoutReturn = new JVisaReturnNumber((int) 0);
    int timeoutOriginal, timeoutNew = 5123;
    long expResult = VisatypeLibrary.VI_SUCCESS;

    long result = instance.getAttribute(VisaLibrary.VI_ATTR_TMO_VALUE, timeoutReturn, viInstrument);
    assertEquals(result, expResult);
    timeoutOriginal = timeoutReturn.returnNumber.intValue();
    assertEquals(timeoutOriginal, timeOut);

    result = instance.setAttribute(VisaLibrary.VI_ATTR_TMO_VALUE, timeoutNew, viInstrument);
    assertEquals(result, expResult);

    result = instance.getAttribute(VisaLibrary.VI_ATTR_TMO_VALUE, timeoutReturn, viInstrument);
    assertEquals(result, expResult);
    assertEquals(timeoutReturn.returnNumber.intValue(), timeoutNew);

    result = instance.setAttribute(VisaLibrary.VI_ATTR_TMO_VALUE, timeoutOriginal, viInstrument);
    assertEquals(result, expResult);
  }
  public void getVisaInstrumentHandle() {
    assertTrue(instance.getVisaInstrumentHandle() > 0L);
  }
  public void visaWrite(String command) {
    long expResult = VisatypeLibrary.VI_SUCCESS;
    try {
      assertEquals(instance.write(command), expResult);
    } catch(JVisaException e) {
      LOGGER.log(Level.SEVERE, null, e);
    }
  }
  public String visaRead() {
    try {
      JVisaReturnString response = new JVisaReturnString();
      long expResult = VisatypeLibrary.VI_SUCCESS;
      long result = instance.read(response);
      assertEquals(result, expResult);
      read = response.returnString;
    } catch(JVisaException e) {
      LOGGER.log(Level.SEVERE, null, e);
      fail(e.getMessage());
    }
    return read;
  }
  
}