package com.fp.gan.core.utils;

import java.io.IOException;
import java.io.StringReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.jdom2.Document;
import org.jdom2.Element;
import org.jdom2.JDOMException;
import org.jdom2.input.SAXBuilder;
import org.xml.sax.InputSource;


public class XmlUtil {
    
	/***
	 * 解析字符串返回Map（只考虑一级）
	 * @param xmlDoc
	 * @return
	 */
    public static Map<String,String> parse(String xmlDoc) {   
       
    	 Map<String,String> map = new HashMap<String,String>(); 
    	 
        SAXBuilder saxb = new SAXBuilder();   
       
        try {   

        	//方便识别，全部小写
            StringReader xmlString = new StringReader(xmlDoc.toLowerCase());   
              
            InputSource source = new InputSource(xmlString);   
              
            Document doc = saxb.build(source);   
              
            Element root = doc.getRootElement();   
      
            List<Element> node = root.getChildren();   
            Element et = null;   
            
            for (int i = 0; i < node.size(); i++) {   
                et = (Element) node.get(i); 
                
                map.put(et.getName().trim(), et.getText().trim());
            }   
        } catch (JDOMException e) {   
            e.printStackTrace();   
        } catch (IOException e) {   
            e.printStackTrace();   
        }   
        return map;   
    } 
    
    public static String buildXml(Map<String, Object> map){
		
		StringBuilder sb=new StringBuilder();
		sb.append("<RESULTMSG>");
		sb.append(buildMap(map));
		sb.append("</RESULTMSG>");
		return sb.toString();
	}
    
    private static  String buildMap(Map<String, Object> map){
    	
    	Object obj;
		StringBuilder sb=new StringBuilder();
		
		for (String key : map.keySet()) {
			
			obj=map.get(key);
			if(obj instanceof String){
			   sb.append("<"+key+">" +map.get(key)+"</"+key+">");
			    
			}else if(obj instanceof Long){
				   sb.append("<"+key+">" +map.get(key)+"</"+key+">");
				    
			}else if(obj ==null){
				   sb.append("<"+key+">" +""+"</"+key+">");
				    
			}else if(obj instanceof HashMap){
				
				 sb.append("<"+key+">" +buildMap((Map<String, Object>)obj)+"</"+key+">");
			}else if(obj instanceof ArrayList){
				
				 //sb.append("<"+key+">" +buildMap((ArrayList<Map<String, Object>>)obj)+"</"+key+">");
				 sb.append(buildMap((ArrayList<Map<String, Object>>)obj));
				 
			}
		}
		return sb.toString();
    }
    
    private static  String buildMap(ArrayList<Map<String, Object>> list){
    	
    	StringBuilder sb=new StringBuilder();
    	for(Map<String, Object>  m:list){
    		
    		sb.append("<LIST>" +buildMap(m)+"</LIST>");
    	}
    	return sb.toString();
    }
}
