package core.instructions;

import utilities.Converter;

public class LDA extends Decoder {
	public static final String OPCODE_VALUE = "000011";
	
	public LDA(String binString) {
		super(binString);
	}

	@Override
	public void Execute() {
		// TODO Auto-generated method stub
		if(!indirectEnabled){//I==0
			cpu.getRegister((int)register).setBits(Converter.longToBin(EA),0);
		}
		else{
			memEl = memory.getCellByIndex((int)EA);
			//set the bits into the register
			cpu.getRegister((int)register).setBits(memEl.getBinString(), 0);
			//some registers
			cpu.MAR.setBits(Converter.longToBin(EA), 0);
			cpu.MBR.setBits(memEl.getBinString(), 0);
		}
		
	}
}
