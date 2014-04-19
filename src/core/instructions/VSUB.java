package core.instructions;

import java.util.logging.Logger;

import core.Device;
import utilities.Converter;

public class VSUB extends Decoder{
public static final String OPCODE_VALUE = "100100";
private static final Logger logger = Logger.getLogger(VSUB.class.getName());
     public VSUB(String binString){
    	 super(binString);
     }
     
     public void Execute(){
    //	double fr=0.0;
 		double dea=0.0;
 		/*
 		if(register==0){
 			fr=Converter.binToFloat(cpu.FR0.get32BinString());
 		}
 		else if(register==1){
 			fr=Converter.binToFloat(cpu.FR1.get32BinString());
 		}
 		*/
 		if(!indirectEnabled){//I=0
 			System.out.println("markInn1");
 			int length=(int)Converter.binToLong(cpu.getRegister(0).getBinString());
 			memEl=memory.getCellByIndex((int)EA);
 			memEl2=memory.getCellByIndex((int)EA+1);
 		    int v1Add=(int)Converter.binToLong(memEl.getBinString());
 		    int v2Add=(int)Converter.binToLong(memEl2.getBinString());
 		   System.out.println("markInn2"+v1Add+"||"+v2Add);
 		    for(int i=0;i<length;i++){
 		    	long v1=Converter.binToLong((memory.getCellByIndex(v1Add+i).getBinString()));
 		    	long v2=Converter.binToLong((memory.getCellByIndex(v2Add+i).getBinString()));
 		    	v1=v1-v2;
 		    	memory.setMemory(Converter.longToBin(v1), v1Add+i);
 		    	try {
 					Device.getInstance().setCharacter(String.valueOf(i+1)+"  "+String.valueOf(v1+v2)+"  "+String.valueOf(v2)+"  "+String.valueOf(v1)+"\r\n");
 				}
 				catch (Exception e) {
 					logger.info("Error outputing character to device");
 				}
 		    }    
 		}
 		else{
 			int length=(int)Converter.binToLong(cpu.R0.getBinString());
 			memEl=memory.getCellByIndex((int)EA);
 			memEl2=memory.getCellByIndex((int)EA+1);
 		    int v1Add=(int)Converter.binToLong(memEl.getBinString());
 		    int v2Add=(int)Converter.binToLong(memEl2.getBinString());
 		    memEl=memory.getCellByIndex(v1Add);
 		    memEl2=memory.getCellByIndex(v2Add);
 		    v1Add=(int)Converter.binToLong(memEl.getBinString());
 		    v2Add=(int)Converter.binToLong(memEl2.getBinString());
 		    for(int i=0;i<length;i++){
		    	long v1=Converter.binToLong((memory.getCellByIndex(v1Add+i).getBinString()));
		    	long v2=Converter.binToLong((memory.getCellByIndex(v2Add+i).getBinString()));
		    	v1=v1-v2;
		    	memory.setMemory(Converter.longToBin(v1), v1Add+i);
		    	try {
 					Device.getInstance().setCharacter(String.valueOf(i+1)+"  "+String.valueOf(v1+v2)+"  "+String.valueOf(v2)+"  "+String.valueOf(v1)+"\r\n");
 				}
 				catch (Exception e) {
 					logger.info("Error outputing character to device");
 				}
		    }  
 		}
 		
 		
 		
 		
 		
 		
     }
}
