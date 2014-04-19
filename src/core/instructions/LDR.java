package core.instructions;

import utilities.Converter;


public class LDR extends Decoder {
	public static final String OPCODE_VALUE = "000001";
	
	public LDR(String binString) {
		super(binString);
	}

	@Override
	public void Execute() {
		//finally get the content from effective address
		memEl = memory.getCellByIndex((int)EA);
		//some registers
		cpu.MAR.setBits(Converter.longToBin(EA), 0);
		cpu.MBR.setBits(memEl.getBinString(), 0);
		
		//set the bits into the register
		cpu.getRegister((int)register).setBits(memEl.getBinString(), 0);
	}
}
