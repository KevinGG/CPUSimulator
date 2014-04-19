package core.instructions;

public class STX extends Decoder {
	public static final String OPCODE_VALUE = "101010";
	
	public STX(String binString) {
		super(binString);
	}

	@Override
	public void Execute() {
		// get content from X0
		String content=cpu.X0.getBinString();
		//store into  memory
		memory.setMemory(content, (int)EA);
	}
}
