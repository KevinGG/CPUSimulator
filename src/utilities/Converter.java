package utilities;


import java.math.BigInteger;

public class Converter {
	  public static long binToLong(String bits) {//convert a binary string into long decimal
		  if(bits.length()==16){
			  char flag=bits.toCharArray()[0];
			  if(flag=='1'){
				String content=bits.substring(1,bits.length());
				String realContent="0"+content;
				long cR=Converter.binToLong(realContent);
				if(flag=='1'){
					cR=-cR;
				}
				return cR;
			  }
			  else{
				return new BigInteger(bits, 2).longValue();
			  }
		  }
		  else{
			  return new BigInteger(bits, 2).longValue();
		  }
	  }
	  
	  public static String longToBin(long bits){//convert a long into a 16-bit binary String
		  if(bits<0){
		    StringBuffer sb=new StringBuffer();
		    sb.append('1');
		    String s = Long.toBinaryString(-bits);
		    if(s.length()<15){
				  for(int ii=0;ii<15-s.length();ii++){
					  sb.append('0');
				  }
			  s=new String(sb)+s;
			}
		    return s;
		  }
		  else{
			  String s= Long.toBinaryString(bits);
			  if(s.length()<16){
				  StringBuffer sb=new StringBuffer();
				  for(int ii=0;ii<16-s.length();ii++){
					  sb.append('0');
				  }
			  s=new String(sb)+s;
			  }
			  return s;
		  }
	}
	  
	  public static double binToFloat(String bits){
		  	char sign=bits.toCharArray()[0];
		  	double re=0.0;
		  	if(sign=='0'){ 
		  		 re=1.0;
		  	}
		  	else{
		  		 re=-1.0;
		  	}
		  	String exponent=bits.substring(1,8);
		  	String mantissa=bits.substring(8);
		  	long le=binToLong(exponent)-63;
		  	char [] man=mantissa.toCharArray();
		  	double lm=0;
		  	for(int i=0;i<24;i++){
		  		lm=lm+(man[i]-48)*Math.pow(2.0,-1-i);
		  	}
		  	lm+=1;
		  	re=re*lm*Math.pow(2.0, le);
		  	return re;
	  }
	  
	  public static String floatToBin(double bits){
		  String sign; 
		  if(bits<0){
			   sign="1";
			   bits=-bits;
		  }
		  else{
			  sign="0";
		  }
		  long intPart=(long) (bits/1);
		  double doublePart=bits-intPart;
		  String sInt="";
		  String sDouble="";
		  while(intPart>0){
			  long tmp=intPart%2;
			  sInt+=String.valueOf(tmp);
			  intPart=intPart/2;
		  }
		  sInt=reverse(sInt);
		 while(doublePart!=0){
			 doublePart=doublePart*2;
			 if(doublePart>=1){
				 sDouble+="1";
				 doublePart-=1;
			 }
			 else{
				 sDouble+="0";
			 } 
		 }
		 long exponent=0;
		 String all="";
		 String ma="";
		 if(sInt.length()<=0) sInt="0";
		  if(Long.valueOf(sInt)>0){
			  System.out.println("sInt:"+sInt);
			 exponent=sInt.length()-1+63;
			 all=sInt+sDouble;
			 ma=all.substring(1);
			 if(ma.length()>24){
				 ma=ma.substring(0,24);
			 }
			 System.out.println("ma:"+ma);
		  }
		  if(Long.valueOf(sInt)==0){
			 char[] cd=sDouble.toCharArray();
			 for(int i=0;i<cd.length;i++){
				 if(cd[i]=='1'){
					 exponent=-i-1+63;
					 break;
				 }
			 }
			 all=sInt+sDouble;
			 ma=all.substring((int)(1-exponent+63));
			 if(ma.length()>24){
				 ma=ma.substring(0,24);
			 }
			 System.out.println("ma:"+ma);
		  }
		  String se="";
	//	  System.out.println(sInt);
	//	  System.out.println(sDouble);
		  System.out.println("exponent:"+exponent);
		  while(exponent>0){
			  long tmp=exponent%2;
			  se+=String.valueOf(tmp);
			  exponent/=2;
		  }
		  se=reverse(se);
		  System.out.println(se);
		  String sse="";
		  if(se.length()<7){
			  for(int ii=0;ii<7-se.length();ii++){
				  sse+="0";
			  }
		  }
		  sse+=se;
		  System.out.println("exponent:"+sse);
		  String re=sign+sse+ma;
		  if(re.length()<32){
			  int t=32-re.length();
			  for(int ii=0;ii<t;ii++){
				  re+="0";
			  }
		  }
		  return re;
	  }
	  
	  public static String reverse(String bits){
		 StringBuffer sb=new StringBuffer();
		 char[] cbits=bits.toCharArray();
		 for(int i=cbits.length-1;i>=0;i--){
			 sb.append(cbits[i]);
		 }
		 String rebits=new String(sb);
		 return rebits;
	  }
	  
	  
}
