package core.instructions;
import utilities.Converter;

public class NOT extends Decoder {
	public static final String OPCODE_VALUE = "011001";
	
	public NOT(String binString) {
		super(binString);
	}
	
	public void Execute() {
		String cRx = cpu.getRegister((int) rx).getBinString();
		char[] x=cRx.toCharArray();
		StringBuffer buf = new StringBuffer(16);
		for(int i=0;i<16;i++){
			if(x[i]=='1'){
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
