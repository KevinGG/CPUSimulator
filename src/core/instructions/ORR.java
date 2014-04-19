package core.instructions;
import utilities.Converter;

public class ORR extends Decoder {
	public static final String OPCODE_VALUE = "011000";
	
	public ORR(String binString) {
		super(binString);
	}
	
	public void Execute() {
		String cRx = cpu.getRegister((int) rx).getBinString();
		String cRy = cpu.getRegister((int) ry).getBinString();
		char[] x=cRx.toCharArray();
		char[] y=cRy.toCharArray();
		StringBuffer buf = new StringBuffer(16);
		for(int i=0;i<16;i++){
			if(x[i]=='0' && y[i]=='0'){
				buf.append("0");
			}
			else{
				buf.append("1");
			}
		}
		String result= new String(buf);
		cpu.getRegister((int)rx).setBits(result, 0);
	}
}
