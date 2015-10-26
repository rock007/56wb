package sam.wb.core.utils;

import java.text.SimpleDateFormat;
import java.util.Date;

public class DateUtil {

	private static SimpleDateFormat sd = new SimpleDateFormat("yyyyMMddHHmmss");

	private static SimpleDateFormat dtfull_sd = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	
	private static SimpleDateFormat yyyymmdd_sd = new SimpleDateFormat("yyyy-MM-dd");
	
	private static SimpleDateFormat yyyymmdd = new SimpleDateFormat("yyyyMMdd");
	
	private static SimpleDateFormat HHmmss_sd = new SimpleDateFormat("HH:mm:ss");

	public static String GetCurDT(){	            
          return sd.format(new java.util.Date());
	}
	
	public static String CurTime(Date m){	            
        return HHmmss_sd.format(m);
	}
	
	public static String yyyymmdd(Date m){	            
        return yyyymmdd_sd.format(m);
	}
	
	public static String fullDateStr(Date m){	            
        return dtfull_sd.format(m);
	}
	
	public static Date getDate(String m,SimpleDateFormat sdf){
		
		Date date=null;
        try  
        {  
             date = sdf.parse(m);  
        }  
        catch (Exception e)  
        {  
            System.out.println(e.getMessage());  
        } 
        return date;
	}
	
	public static Date parseDateYYYYMMdd(String m){
		
		return getDate(m,yyyymmdd);
	}
		
	
}
