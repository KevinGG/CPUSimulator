package core.instructions;
import utilities.Converter;

public class CHK extends Decoder {
	public static final String OPCODE_VALUE = "111111";
	
	public CHK(String binString) {
		super(binString);
	}
	
	public void Execute() {//This instruction is for studying purpose because no status could be generated
		String status="0000000000000001";//1 stands for ready, 0 for not ready 
		cpu.getRegister((int)R).setBits(status, 0);
	}
	
}