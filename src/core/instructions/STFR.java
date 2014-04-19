package core.instructions;

import utilities.Converter;

public class STFR extends Decoder{
public static final String OPCODE_VALUE = "101011";//Modified to 43 for duplicated usage
	public STFR(String binString){
		super(binString);
	}
	public void Execute(){
		// get content from register
		String bit32="";
		if(register==0){
				 bit32=cpu.FR0.getBinString();
		}
		else if(register==1){
			     bit32=cpu.FR1.getBinString();
		}
				String highbits=bit32.substring(0,16);
				String lowbits=bit32.substring(16);
				if(!indirectEnabled){//I=0
				// store it into EA of memory
					memory.setMemory(highbits, (int)EA);
					memory.setMemory(lowbits, (int)EA+1);
				}
				else{
					memEl = memory.getCellByIndex((int)EA);
					long cEA=Converter.binToLong(memEl.getBinString());
					memory.setMemory(highbits, (int)cEA);
					memory.setMemory(lowbits, (int)cEA+1);
				}
	}
}
