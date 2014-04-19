package core;

import java.util.BitSet;

public class FixedBitSet extends BitSet {//it's a word
	private BitSet bitset;
	protected int size;

	public FixedBitSet() {
		bitset = new BitSet();
		size = 16;
	}

	public void sizeSet(){
		this.size=32;
	}
	public void sizeReset(){
		this.size=16;
	}
	public void setBits(String bits, int start) {// set a String bits into bitset from the start index of bits
		int index = 0;

		for (int i = 0; i < bits.length(); i++) {
			index = i + start;

			if (index >= size) {
				return;
			}

			char c = bits.charAt(i);

			switch (c) {
			case '1':
				bitset.set(index, true);
				break;
			case '0':
				bitset.set(index, false);
				break;
			default:
				break;
			}
		}
	}
	
	public String getBinString() {//convert a bitset into a binary string
		StringBuffer buf = new StringBuffer(size);

		for (int i = 0; i < size; i++)
			if (bitset.get(i)) {
				buf.append("1");
			} else {
				buf.append("0");
			}

		return new String(buf);
	}
	
	public String get32BinString(){
		StringBuffer buf = new StringBuffer(32);

		for (int i = 0; i < 32; i++)
			if (bitset.get(i)) {
				buf.append("1");
			} else {
				buf.append("0");
			}

		return new String(buf);
	}
	
}
