package core.instructions;

public class STR extends Decoder {
	public static final String OPCODE_VALUE = "000010";
	
	public STR(String binString) {
		super(binString);
	}

	@Override
	public void Execute() {
		// get content from register
		String content=cpu.getRegister((int)register).getBinString();
		System.out.println("R1:" +content);
		// store it into EA of memory
		memory.setMemory(content, (int)EA);
	}

}
