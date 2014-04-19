package core.instructions;

import java.util.logging.Logger;

import utilities.Converter;
import core.CPU;
import core.MemoryElement;
import core.Register;

public abstract class Decoder extends Instruction {
	protected static CPU cpu = CPU.getInstance();
	final static int OPCODE_BITS = 6;
	final static int INDIRECT_BIT = 7;
	final static int IX_BIT = 8;
	final static int REGISTER_BITS = 10;
	final static int ADDRESS_BITS = 16;

	private static final Logger logger = Logger.getLogger(Decoder.class.getName());

	// Memory address with which the instruction is operating
	protected long address;

	protected MemoryElement memEl;
	protected MemoryElement memEl2;

	protected boolean indirectEnabled = false;
	protected boolean indexEnabled = false;
	protected long register;
	
	//protected String immedTransfer;
    //protected String immedArithNLogical;
	//Effective Address
	protected long EA;
    protected long rx, ry;
    protected long LR,R,AL,count;
	protected long DevID;
	
	public Decoder(String binString) {
		cpu.IR.setBits(binString, 0);
		
		//Parse binary string into its specified parts.

		//get 10-bit immed for Transfer instr RFS
		//immedTransfer=binString.substring(6, 16);
		
		//get 8-bit immed for Arithmetic & Logical instr AIR, SIR
		//immedArithNLogical= binString.substring(8,16);
		
		//Mark this instruction as indirect or not
		String I = binString.substring(6, 7);
		if (I.equals("1")) {
			indirectEnabled = true;
		}

		//Mark this instruction as indexing or not
		String IX = binString.substring(7, 8);
		if (IX.equals("1")) {
			indexEnabled = true;
		}

		//Acquire the register bits and get the register from the cpu
		String Reg = binString.substring(8, 10);
		register = Converter.binToLong(Reg);

		//store the address from this instruction
		address = Converter.binToLong(binString.substring(10, 16));
		
		//get Rx, Ry
		rx=Converter.binToLong(binString.substring(6,8));
		ry=Converter.binToLong(binString.substring(8,10));
		
		//RRC & SRC
		LR=Converter.binToLong(binString.substring(6,7));
		R=Converter.binToLong(binString.substring(7,9));
		AL=Converter.binToLong(binString.substring(9,10));
		count=Converter.binToLong(binString.substring(12,16));
		
		//Device
		DevID=Converter.binToLong(binString.substring(11,16));
		
		
		EA = calculateEA();
		System.out.println(EA);
		System.out.println("BINSTRING: " + binString);
		System.out.println("RESULTING REGISTER: " + (int)register);
	}

	public long calculateEA(){
		//is indirection bit set or not
		if (!indirectEnabled) {
			//is index bit set or not
			if (!indexEnabled) {
				EA = address;
			}
			else {
				long cX0 = cpu.X0.getValue();
				EA = cX0 + address;
			}
		}
		else {
			if (!indexEnabled) {
				MemoryElement m = memory.getCellByIndex((int) address);
				System.out.println("address:"+address);
				EA = m.getValue();
			}
			else {
				long cX0 = cpu.X0.getValue();
				long cX0Offset = cX0 + address;
				MemoryElement m = memory.getCellByIndex((int) cX0Offset);
				EA = m.getValue();
			}
		}
		return EA;
	}

	public abstract void Execute();
}
