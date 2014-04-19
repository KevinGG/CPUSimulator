package core.instructions;

import utilities.Converter;

public class LDX extends Decoder {
	public static final String OPCODE_VALUE = "101001";
	
	public LDX(String binString) {
		super(binString);
	}

	@Override
	public void Execute() {
		// get content from EA of memory
		memEl = memory.getCellByIndex((int)EA);
		//load into X0
		cpu.X0.setBits(memEl.getBinString(), 0);
		//some registers
		cpu.MAR.setBits(Converter.longToBin(EA), 0);
		cpu.MBR.setBits(memEl.getBinString(), 0);
	}
}
