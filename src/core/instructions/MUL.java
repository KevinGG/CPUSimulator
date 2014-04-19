package core.instructions;
import utilities.Converter;

public class MUL extends Decoder {
	public static final String OPCODE_VALUE = "010100";
	
	public MUL(String binString) {
		super(binString);
	}
	
	public void Execute() {
		long cRx=cpu.getRegister((int)rx).getValue();
		long cRy=cpu.getRegister((int)ry).getValue();
		long result= cRx*cRy;
	    cRx=result/65534;
		cRy=result%65534;
		cpu.getRegister((int)rx).setBits(Converter.longToBin(cRx),0);
		cpu.getRegister((int)(rx+1)).setBits(Converter.longToBin(cRy), 0);
	}
}
