package core;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.logging.Logger;

import utilities.Converter;
import core.instructions.Instruction;

public class Memory {
	private List<MemoryElement> cells;
	private List<Instruction> instructions;
	private static Memory memory = null;
    private int bankNum=0;
	
	private static final Logger logger = Logger.getLogger(Memory.class.getName());

	private Memory() {
		logger.info("Building Memory: " + this.hashCode());
		cells = new ArrayList<MemoryElement>();
		instructions = new LinkedList<Instruction>();

		for (int i = 0; i < CPU.DATALIMIT; i++) {
			cells.add(new MemoryElement(i * 16));
		}

		memoryBanking(4);//4 banks
		logger.info("Memory built: " + this.hashCode());
	}

	public static Memory getInstance() {
		if (memory == null) {
			memory = new Memory();
		}

		return memory;
	}

	private void memoryBanking(int bankNum){
		this.bankNum=bankNum;
	}
	
//	public MemoryElement getCellByAddress(long address) {
//		int index = (int)(address/16);
//		return getCellByIndex(index);
//	}

	public MemoryElement getCellByIndex(int index) {
		return cells.get(index);
	}
	
	public MemoryElement getMemoryByIndexBanking(int index){
		return cells.get(index%4+index/4*4);//this represents the process of banking
	}

	public Instruction getInstruction(FixedBitSet address) {
		return instructions.get((int)(Converter.binToLong(address.getBinString()) / 16));
	}
	
	public boolean setMemory(String bits, int index){//set 16-bit binary String into index of the memory
		System.out.println("BITS: " + bits);
		System.out.println("INDEX: " + index);
		MemoryElement m = cells.get(index);
		m.setBits(bits, 0);
		cells.set(index, m);
		return true;
	}
	
	public String toString() {
		String s = "";
		int index = 0;
		for (MemoryElement m : cells) {
			s += index + " ---> " + m.getValue() + "\n";
			index++;
		}
		return s;
	}
}
