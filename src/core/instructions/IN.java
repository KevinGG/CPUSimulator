package core.instructions;
import java.util.logging.Logger;

import core.Device;
import utilities.Converter;

public class IN extends Decoder {
	public static final String OPCODE_VALUE = "111101";
	private static final Logger logger = Logger.getLogger(IN.class.getName());
	public IN(String binString) {
		super(binString);
	}
	
	public void Execute() {// Add 2 text fields on UI		
		if(DevID==0){
			long v=0;// Keep this as the default value for v
			//when we hit the next step button, the integer value in text field 1 would be assigned to v
			//To-do take a variable from text field 1 as long v
			try
			{
				int flag=0;
				char tmp='a';
				char[] inputChar = Device.getInstance().getCharacter().toCharArray();
				StringBuffer buf=new StringBuffer(5);
				for(int ii=0;ii<inputChar.length;ii++){
					char c=inputChar[ii];
					if(c>='0' && c<='9'){
						buf.append(c);
					}
					else if(c>='a' && c<='z'){
						tmp=c;
						flag=1;
						break;
					}
				}
				if(flag==0){
					String sv=new String(buf);
					v=Long.parseLong(sv);
					logger.info("sv:"+sv);
					if(v>=1 && v<=32767){
						cpu.getRegister((int)R).setBits(Converter.longToBin(v), 0);
					}
					else{
						logger.info("No valid input read");
					}
				}
				else if(flag==1){
					long a=0;
					a=a+tmp;
					cpu.getRegister((int)R).setBits(Converter.longToBin(a), 0);
				}
			}
			catch (Exception e) {
				logger.info("Error reading input. Setting v to 0");
				cpu.getRegister((int)R).setBits(Converter.longToBin(0), 0);
			}
		}
		
	}
	
}