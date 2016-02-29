package com.fundation.xml;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.w3c.dom.Element;

/**
 * @Author Fangys
 * @Desc  使用w3c实现的标准dom api处理xml
 * @Date 2016年2月26日 下午2:07:27
 * @Version 1.x 
 */
public class W3CDomTest {
	
	public String createXml() throws ParserConfigurationException{
		DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
		DocumentBuilder db = dbf.newDocumentBuilder();
		Document doc = db.newDocument();
		Element root = doc.createElement("root");
		//root.appendChild("");
		return null;
	}
	
}
