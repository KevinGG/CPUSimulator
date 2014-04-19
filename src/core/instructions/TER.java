package core.instructions;
import utilities.Converter;

public class TER extends Decoder {
	public static final String OPCODE_VALUE = "010110";
	
	public TER(String binString) {
		super(binString);
	}
	
	public void Execute() {
		long cRx = cpu.getRegister((int) rx).getValue();
		long cRy = cpu.getRegister((int) ry).getValue();
		if(cRx==cRy){
			cpu.CC.setBits("0001", 0);
		}
		else{
			cpu.CC.setBits("0000", 0);
		}
	}
}
