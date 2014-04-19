package core.instructions;

import utilities.Converter;

public class DIV extends Decoder {
	public static final String OPCODE_VALUE = "010101";

	public DIV(String binString) {
		super(binString);
	}

	public void Execute() {
		long cRx = cpu.getRegister((int) rx).getValue();
		long cRy = cpu.getRegister((int) ry).getValue();
		if(cRy!=0){
			long rRx = cRx / cRy;
			long rRy = cRx % cRy;
			cpu.getRegister((int) rx).setBits(Converter.longToBin(rRx), 0);
			cpu.getRegister((int) (rx + 1)).setBits(Converter.longToBin(rRy), 0);
		}
		else{
			cpu.CC.setBits("0010", 0);
		}
	}
}
