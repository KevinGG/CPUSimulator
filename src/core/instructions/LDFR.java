package core.instructions;

import utilities.Converter;

public class LDFR extends Decoder{
public static final String OPCODE_VALUE = "101000";
	
	public LDFR(String binString){
		super(binString);
	}
	
	public void Execute(){
		// TODO Auto-generated method stub
				if(!indirectEnabled){//I==0
					memEl = memory.getCellByIndex((int)EA);
				    memEl2 = memory.getCellByIndex((int)EA+1);
				    String highbits=memEl.getBinString();
					String lowbits=memEl2.getBinString();
					String bit32=highbits+lowbits;
					if(register==0){
						cpu.FR0.setBits(bit32, 0);
					}
					else if(register==1){
						cpu.FR1.setBits(bit32, 0);
					}
				}
				else{
					memEl = memory.getCellByIndex((int)EA);
					memEl2 = memory.getCellByIndex((int)EA+1);
					String highbits=memEl.getBinString();
					String lowbits=memEl2.getBinString();
					memEl= memory.getCellByIndex((int)Converter.binToLong(highbits));
					memEl2= memory.getCellByIndex((int)Converter.binToLong(lowbits));
					highbits=memEl.getBinString();
					lowbits=memEl2.getBinString();
					String bit32=highbits+lowbits;
					if(register==0){
						cpu.FR0.setBits(bit32, 0);
					}
					else if(register==1){
						cpu.FR1.setBits(bit32, 0);
					}
				}
				cpu.MAR.setBits(Converter.longToBin(EA), 0);
				cpu.MBR.setBits(memEl.getBinString(), 0);
	}
}
