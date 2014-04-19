package core.instructions;

import utilities.Converter;

public class SIR extends Decoder{
public static final String OPCODE_VALUE = "000111";
	
	public SIR(String binString) {
		super(binString);
	}
	
	@Override
	public void Execute() {
		//get content from register
		String content=cpu.getRegister((int)register).getBinString();
		long cR=Converter.binToLong(content);
	   //long lImmed=Converter.binToLong(immedArithNLogical);
		long lImmed=address;
	   long lr=cR-lImmed;//r<-c(r)+Immed
		if(lImmed!=0){
		cpu.getRegister((int)register).setBits(Converter.longToBin(lr), 0);
		}
	}
}
