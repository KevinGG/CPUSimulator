package core.instructions;

import utilities.Converter;

public class SMR extends Decoder{
public static final String OPCODE_VALUE = "000101";
	
	public SMR(String binString) {
		super(binString);
	}
	
	@Override
	public void Execute() {
		//get content from register
				String content=cpu.getRegister((int)register).getBinString();
				long cR=Converter.binToLong(content);
				//get memory
				memEl = memory.getCellByIndex((int)EA);
				String cEA=memEl.getBinString();
				long lcEA=Converter.binToLong(cEA);
				long lr=cR-lcEA;
				cpu.getRegister((int)register).setBits(Converter.longToBin(lr), 0);
				//some registers
				cpu.MAR.setBits(Converter.longToBin(EA), 0);
				cpu.MBR.setBits(memEl.getBinString(), 0);
	}
}
