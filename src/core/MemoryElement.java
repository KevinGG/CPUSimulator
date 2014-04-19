package core;

import utilities.Converter;

public class MemoryElement extends FixedBitSet {
	private int address;

	public MemoryElement(int address) {
		super();
		this.address = address;
	}

	public int getAddress() {
		return address;
	}

	public long getValue() {
		return Converter.binToLong(this.getBinString());
	}
}
