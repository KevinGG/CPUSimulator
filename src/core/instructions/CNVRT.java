package core.instructions;

import utilities.Converter;

public class CNVRT extends Decoder{
public static final String OPCODE_VALUE = "100101";
	public CNVRT(String binString){
		super(binString);
	}
	public void Execute(){
		System.out.println("markINNNN");
		if(register==0){//To a fixed point number, the first 4 bits stand for the position of decimal point
			memEl=memory.getCellByIndex((int)EA);
			String bit16=memEl.getBinString();
			cpu.getRegister(0).setBits(bit16, 0);
		}
		else{
			memEl=memory.getCellByIndex((int)EA);
			double d=(double)Converter.binToLong(memEl.getBinString());
			String bit32=Converter.floatToBin(d);
			cpu.FR0.setBits(bit32, 0);
		}
	}
}
