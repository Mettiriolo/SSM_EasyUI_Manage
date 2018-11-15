package com.yali.util;

import java.util.ArrayList;
import java.util.List;

public class StringUtil {
	public static boolean isEmpty(String str){
		if("".equals(str)|| str==null){
			return true;
		}else{
			return false;
		}
	}
	
	public static boolean isNotEmpty(String str){
		if(!"".equals(str)&&str!=null){
			return true;
		}else{
			return false;
		}
	}
	
	public static boolean existStrArr(String str,String []strArr){
		for(int i=0;i<strArr.length;i++){
			if(strArr[i].equals(str)){
				return true;
			}
		}
		return false;
	}
	
	public static List<Integer> stringToArry(String str){
		String[] str1=str.split(",");
		List<Integer> array=new ArrayList<Integer>(); 
		for(int i=0;i<str1.length;i++){
			array.add(Integer.parseInt(str1[i]));
		}
		return array;
	}
}
