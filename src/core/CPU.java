package core;

import java.util.logging.Logger;

import core.instructions.Instruction;
import utilities.Converter;

public class CPU {
	public Memory mem;
	private Register[] gpr;
	private static final Logger logger = Logger.getLogger("CPU");
   
    
	public enum CPUStatus {READY, RUNNING, STOPPING, HALTED};
	private CPUStatus status;

	private static CPU cpu;

	public Register R0,R1,R2,R3,X0;//four GPRs & 1 index register
	public Register PC,CC,IR,MAR,MBR,MSR,MFR;//all other registers
	public Register FR0, FR1;//2 floating point registers

	public static final int DATALIMIT = 2048;
	
	public boolean PCFlag;

	/** Static initializer */
	static {
		cpu = null;
	}
	private CPU() {
		logger.info("Creating the CPU...");
		status = CPUStatus.READY;
		mem = Memory.getInstance();
		logger.info("Got Memory instance..");

		// Registers initialization
		gpr = new Register[4];
		gpr[0] = new Register("R0", 16);
		gpr[1] = new Register("R1", 16);
		gpr[2] = new Register("R2", 16);
		gpr[3] = new Register("R3", 16);
		X0 = new Register("XO", 16);
		PC = new Register("PC", 16);
		CC = new Register("CC", 4);
		IR = new Register("IR", 16);
		MAR = new Register("MAR", 16);
		MBR = new Register("MBR", 16);
		MSR = new Register("MSR", 16);
		MFR = new Register("MFR", 4);
		FR0 = new Register("FR0", 32);
		FR1 = new Register("FR1", 32);
		//set PC, we now use 1 for initialization
		PC.setBits(Converter.longToBin(1L), 0);

        PCFlag=true;
		logger.info("CPU Created.");
	}

	public void setStatus(CPUStatus status) {
		this.status = status;
	}
	
	public CPUStatus getStatus() {
		return this.status;
	}

	public static CPU getInstance() {
		if (cpu == null) {
			cpu = new CPU();
		}
		return cpu;
	}
	

	public Register getRegister(int index) {
		return gpr[index];
	}

	public Register[] getRegisters() {
		return gpr;
	}
	

	public void step() {
		try {
			cpu.PCFlag=true;
			long pcIndex=Converter.binToLong(PC.getBinString());// get PC
			
			Memory memem=Memory.getInstance();
			String instruction=memem.getCellByIndex((int)pcIndex).getBinString();
			System.out.println("knInstr:" + instruction);
			if (instruction == null || instruction == "") {
				this.setStatus(CPUStatus.HALTED);
				System.out.println("markH");
				return;
			}
			this.setStatus(CPUStatus.RUNNING);
			System.out.println("markR");
			Instruction.buildInstruction(instruction).Execute();
			System.out.println("mark1");
			if(cpu.PCFlag){
				PC.setBits(Converter.longToBin(pcIndex+1),0);//PC+1
			}
		} catch (Exception e) {
			this.setStatus(CPUStatus.HALTED);
			logger.info("Error");
		}
	}

}
