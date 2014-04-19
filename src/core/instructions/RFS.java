package core.instructions;

import utilities.Converter;

public class RFS extends Decoder{
public static final String OPCODE_VALUE = "001111";
	
	public RFS(String binString) {
		super(binString);
	}
	
	@Override
	public void Execute() {
		cpu.PCFlag=false;
		//cpu.getRegister(0).setBits("000000"+immedTransfer,0); //R0<-Immed
		cpu.getRegister(0).setBits(Converter.longToBin(address),0);
		cpu.PC.setBits(cpu.getRegister(3).getBinString(),0);//PC<-c(R3);
	}
}
