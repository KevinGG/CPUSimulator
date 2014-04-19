package core.instructions;

public class RRC extends Decoder{
public static final String OPCODE_VALUE = "100000";
	
	public RRC(String binString) {
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
				buf.append(crr[i]);
			}
			String result=new String(buf);
			cpu.getRegister((int) R).setBits(result, 0);
		}
		else{
				for(int i=15-(int)count;i<15;i++){
					buf.append(crr[i]);
				}
				for(int i=0;i<=15-count;i++){
					buf.append(crr[i]);
				}
				String result=new String(buf);
				cpu.getRegister((int) R).setBits(result, 0);	
		}
	}
}
