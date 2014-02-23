package com.sky.tuan.enumeration;

import java.util.HashMap;
import java.util.Map;


public enum TuanTypeEnum {
   Lashou("拉手",1),Dianping("大众点评",2),Meituan("美团",3),Wowo("窝窝",4),
   Nuomi("糯米",5),Dida("滴答",6);
	   
	   private TuanTypeEnum(String name,int value)
	   {
		   this.value=value;
		   this.name=name;
	   }
	   private String name;
	   private int value;
	   
	   public int getValue()
	   {
		   return value;
	   }
	   
	   public static TuanTypeEnum valueOf(int value) {
	       switch (value) {

	       case 1:
	    	   return Lashou;
	       case 2:
	    	   return Dianping;
	       case 3:
	    	   return Meituan;
	       case 4:
	    	   return Wowo;
	       case 5:
	    	   return Nuomi;
	       case 6:
	    	   return Dida;
	       default:
	           return null;
	       }
	   }
	   
	   public static Map<Integer,String> toMap() {
		  Map<Integer,String> m=new HashMap<Integer,String>();
		  for(TuanTypeEnum s :TuanTypeEnum.values())
		  {
			 m.put(s.getValue(),s.toString());
		  }
		  return m;
	   }
	   
	   public String toString()
	   {
		return this.name;
	   }
}
