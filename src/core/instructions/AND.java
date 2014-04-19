package core.instructions;
import utilities.Converter;

public class AND extends Decoder {
	public static final String OPCODE_VALUE = "010111";
	
	public AND(String binString) {
		super(binString);
	}
	
	public void Execute() {
		String cRx = cpu.getRegister((int) rx).getBinString();
		String cRy = cpu.getRegister((int) ry).getBinString();
		char[] x=cRx.toCharArray();
		char[] y=cRy.toCharArray();
		StringBuffer buf = new StringBuffer(16);
		for(int i=0;i<16;i++){
			if(x[i]=='1' && y[i]=='1'){
				buf.append("1");
			}
			else{
				buf.append("0");
			}
		}
		String result= new String(buf);
		cpu.getRegister((int)rx).setBits(result, 0);
	}
}
