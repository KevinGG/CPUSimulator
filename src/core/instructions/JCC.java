package core.instructions;

import utilities.Converter;

public class JCC extends Decoder{
public static final String OPCODE_VALUE = "001100";
	
	public JCC(String binString) {
		super(binString);
	}
	
	@Override
	public void Execute() {
		long cc=register;	
		if(cc==1){
			cpu.PCFlag=false;
				if(!indirectEnabled){//I==0
					String sEA=Converter.longToBin(EA);
					cpu.PC.setBits(sEA, 0);
				}
				else{
					//get memory
					memEl = memory.getCellByIndex((int)EA);
					String cEA=memEl.getBinString();
					cpu.PC.setBits(cEA, 0);
					//some registers
					cpu.MAR.setBits(Converter.longToBin(EA), 0);
					cpu.MBR.setBits(memEl.getBinString(), 0);
				}	
			
		}
	}
}
