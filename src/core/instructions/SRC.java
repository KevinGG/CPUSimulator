package core.instructions;

public class SRC extends Decoder{
public static final String OPCODE_VALUE = "011111";
	
	public SRC(String binString) {
		super(binString);
	}
	
	public void Execute() {
		StringBuffer buf=new StringBuffer(16);
		String rr=cpu.getRegister((int) R).getBinString();
		char[] crr=rr.toCharArray();
		if(LR==1){
			for(int i=(int)count;i<=15;i++){
				buf.append(crr[i]);
			}
			for(int i=0;i<count;i++){
				buf.append("0");
			}
			String result=new String(buf);
			cpu.getRegister((int) R).setBits(result, 0);
		}
		else{
			if(AL==1){
				for(int i=0;i<count;i++){
					buf.append("0");
				}
				for(int i=0;i<=15-count;i++){
					buf.append(crr[i]);
				}
				String result=new String(buf);
				cpu.getRegister((int) R).setBits(result, 0);
			}
			else{
				for(int i=0;i<count;i++){
					buf.append(crr[0]);
				}
				for(int i=0;i<=15-count;i++){
					buf.append(crr[i]);
				}
				String result=new String(buf);
				cpu.getRegister((int) R).setBits(result, 0);
			}
		}
		
	}
}
