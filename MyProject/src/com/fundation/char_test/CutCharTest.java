package com.fundation.char_test;

import java.io.UnsupportedEncodingException;

/**
 * @Author Fangys
 * @Desc  给定位置，切割字符串，不允许切割汉字
 * @Date 2016年2月25日 下午2:50:51
 * @Version 1.x 
 */
public class CutCharTest {
	public static void main(String[] args) throws UnsupportedEncodingException {
		String str = "我woshi已测通过ldfajodfiajo";
		int pos = trimGBK(str.getBytes("GBK"),8);
		System.out.println(str.substring(0,pos));
	}
	
	//任何字符在unicode编码中都占用两个字节。
	//而在一般编码方式中英文字符可以用一个字节表示，中文要用两个。
	//这样英文字符在unicode编码中多占用了一个没有用的字节,这个没用字节就用0代替。
	//如果unicode编码中字符的第一个字节是0那他就是英文字符。
	//不是0他就可能是除了英文字符之外的很多种语言的文字，不仅仅是中文。
	public static int trimGBK(byte[] ch, int pos){
		int num = 0;
		boolean isChineseChar = false;
		for(int i=0;i<pos;i++){
			if(ch[i]<0 && !isChineseChar){
				isChineseChar = true;
			}else{
				num++;
				isChineseChar = false;
			}
		}
		return num;
	}
}
