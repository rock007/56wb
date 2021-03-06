package sam.wb.core.utils;

import java.util.UUID;

public class StringUtil {

	public static String makeUid(){
		
		 UUID uuid = UUID.randomUUID();
		 
		 return uuid.toString();
	}
 
	public static String[] shortKey(String url) {
	       
	       String key = "fuckyou" ;
	       
	       String[] chars = new String[] { "a" , "b" , "c" , "d" , "e" , "f" , "g" , "h" ,
	              "i" , "j" , "k" , "l" , "m" , "n" , "o" , "p" , "q" , "r" , "s" , "t" ,
	              "u" , "v" , "w" , "x" , "y" , "z" , "0" , "1" , "2" , "3" , "4" , "5" ,
	              "6" , "7" , "8" , "9" , "A" , "B" , "C" , "D" , "E" , "F" , "G" , "H" ,
	              "I" , "J" , "K" , "L" , "M" , "N" , "O" , "P" , "Q" , "R" , "S" , "T" ,
	              "U" , "V" , "W" , "X" , "Y" , "Z" 
	       };
	       
	       String sMD5EncryptResult = (EncriptUtil.md5(key + url));
	       String hex = sMD5EncryptResult;
	       String[] resUrl = new String[4]; 
	        
	       for ( int i = 0; i < 4; i++) {
	          
	           String sTempSubString = hex.substring(i * 8, i * 8 + 8);
	          
	           long lHexLong = 0x3FFFFFFF & Long.parseLong (sTempSubString, 16);
	           String outChars = "" ;
	          
	           for ( int j = 0; j < 6; j++) {

	              long index = 0x0000003D & lHexLong;
	              // 把取得的字符相加
	              outChars += chars[( int ) index];
	              // 每次循环按位右移 5 位
	              lHexLong = lHexLong >> 5;
	           }
	           // 把字符串存入对应索引的输出数组
	           resUrl[i] = outChars;
	       }
	       return resUrl;
	    }
	
}
