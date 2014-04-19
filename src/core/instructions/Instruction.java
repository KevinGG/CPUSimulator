package core.instructions;

import java.util.List;
import java.util.logging.Logger;

import core.CPU;
import core.Memory;
import core.MemoryElement;
import core.Register;

public abstract class Instruction {
	protected static Memory memory = Memory.getInstance();
	protected List<Integer> params;
	protected Register[] TR; //is not static because each instruction has got its own registers
	protected String name;
	protected static final Logger logger = Logger.getLogger(Instruction.class.getName());
    
	public Instruction() {
		TR = new Register[5];
		//initialization of temporary registers
		for (int i = 0; i < TR.length; i++) {
			TR[i] = new Register("TR " + i, 16);
		}
	}

	public static Instruction buildInstruction(String binString) {
		Instruction returnedObject = null;
		String opCodeFromString = getOpcodeFromBinString(binString);
		switch (opCodeFromString) {
		//load & store
		case LDR.OPCODE_VALUE:
			return new LDR(binString);
		case LDA.OPCODE_VALUE:
			return new LDA(binString);
		case LDX.OPCODE_VALUE:
			return new LDX(binString);
		case STR.OPCODE_VALUE:
			return new STR(binString);
		case STX.OPCODE_VALUE:
			return new STX(binString);
			
		//transfer
		case JZ.OPCODE_VALUE:
			return new JZ(binString);
		case JNE.OPCODE_VALUE:
			return new JNE(binString);
		case JCC.OPCODE_VALUE:
			return new JCC(binString);
		case JMP.OPCODE_VALUE:
			return new JMP(binString);
		case JSR.OPCODE_VALUE:
			return new JSR(binString);
		case RFS.OPCODE_VALUE:
			return new RFS(binString);
		case SOB.OPCODE_VALUE:
			return new SOB(binString);
		//arithmetic & logical
		case AMR.OPCODE_VALUE:
			return new AMR(binString);
		case SMR.OPCODE_VALUE:
			return new SMR(binString);
		case AIR.OPCODE_VALUE:
			return new AIR(binString);
		case SIR.OPCODE_VALUE:
			return new SIR(binString);
		case MUL.OPCODE_VALUE:
			return new MUL(binString);
		case DIV.OPCODE_VALUE:
			return new DIV(binString);
		case TER.OPCODE_VALUE:
			return new TER(binString);
		case AND.OPCODE_VALUE:
			return new AND(binString);
		case ORR.OPCODE_VALUE:
			return new ORR(binString);
		case NOT.OPCODE_VALUE:
			return new NOT(binString);
		case SRC.OPCODE_VALUE:
			return new SRC(binString);
		case RRC.OPCODE_VALUE:
			return new RRC(binString);
		case IN.OPCODE_VALUE:
			return new IN(binString);
		case OUT.OPCODE_VALUE:
			return new OUT(binString);
		case CHK.OPCODE_VALUE:
			return new CHK(binString);
		case JGE.OPCODE_VALUE:
			return new JGE(binString);
		case CNVRT.OPCODE_VALUE:
			return new CNVRT(binString);
		case FADD.OPCODE_VALUE:
			return new FADD(binString);
		case FSUB.OPCODE_VALUE:
			return new FSUB(binString);
		case LDFR.OPCODE_VALUE:
			return new LDFR(binString);
		case STFR.OPCODE_VALUE:
			return new STFR(binString);
		case VADD.OPCODE_VALUE:
			System.out.println("markFuck");
			return new VADD(binString);
		case VSUB.OPCODE_VALUE:
			return new VSUB(binString);
		
		}

		return returnedObject;
	}

	protected static String getOpcodeFromBinString(String binString) {
		return binString.substring(0,  6);
	}
	

	public abstract void Execute();
}
