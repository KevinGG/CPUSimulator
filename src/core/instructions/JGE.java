package core.instructions;

import utilities.Converter;

public class JGE extends Decoder{
	public static final String OPCODE_VALUE = "010001";
	
	public JGE(String binString) {
		super(binString);
	}
	
	public void Execute() {
		//get content from register
				String contentOld = cpu.getRegister((int)register).getBinString();
				char flag=contentOld.toCharArray()[0];
				String content=contentOld.substring(1,contentOld.length());
				String realContent="0"+content;
				long cR=Converter.binToLong(realContent);
				if(flag=='1'){
					cR=-cR;
				}
				if(cR>=0){
					cpu.PCFlag=false;
					if(!indirectEnabled){//I==0
						String sEA=Converter.longToBin(EA);
						cpu.PC.setBits(sEA, 0);
					}
					else{
						//get memory
						memEl = memory.getCellByIndex((int)EA);
						String cEA=memEl.getBinString();
						cpu.PC.setBits(cEA, 0);
						//some registers
						cpu.MAR.setBits(Converter.longToBin(EA), 0);
						cpu.MBR.setBits(memEl.getBinString(), 0);
					}	
				}
	}
}
