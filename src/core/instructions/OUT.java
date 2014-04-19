package core.instructions;
import java.util.logging.Logger;

import core.Device;
import utilities.Converter;

public class OUT extends Decoder {
	public static final String OPCODE_VALUE = "111110";
	private static final Logger logger = Logger.getLogger(OUT.class.getName());
	public OUT(String binString) {
		super(binString);
	}
	
	public void Execute() {
		if(DevID==1){
			String s=cpu.getRegister((int)R).getBinString();
			long v=Converter.binToLong(s);
			if(v>='a' && v<='z'){
				char c=(char)v;
				s=""+c;
			}
			else{
			    s=Long.toString(v);
			}
			try {
				Device.getInstance().setCharacter(s);
			}
			catch (Exception e) {
				logger.info("Error outputing character to device");
			}
		}
	}
	
}