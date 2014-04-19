package core.instructions;

import utilities.Converter;

public class JSR extends Decoder{
public static final String OPCODE_VALUE = "001110";
	
	public JSR(String binString) {
		super(binString);
	}
	
	@Override
	public void Execute() {
		long pcIndex=Converter.binToLong(cpu.PC.getBinString());// get PC
		cpu.getRegister(3).setBits(Converter.longToBin(pcIndex+1),0); //R3<-PC+1
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
		//R0 should contain pointer to arguments
		
	}
}
