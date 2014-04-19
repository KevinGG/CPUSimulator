package core;

import utilities.Converter;

public class Register extends FixedBitSet {
	private int size;
	private String name;
	public Register(String name, int size) {
		this.size = size;
		this.name = name;
	}

	public String getName() {
		return this.name;
	}

	public long getValue() {
		return Converter.binToLong(this.getBinString());
	}
	
	public double get32Value(){
		return Converter.binToFloat(this.get32BinString());
	}
	
}
