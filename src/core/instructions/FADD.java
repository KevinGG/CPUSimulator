package core.instructions;

import utilities.Converter;

public class FADD extends Decoder{
	public static final String OPCODE_VALUE = "100001";
	
	public FADD(String binString){
		super(binString);
	}
	
	public void Execute(){
		System.out.println("markIn");
		double fr=0.0;
		double dea=0.0;
		System.out.println(register);
		if(register==0){
			fr=Converter.binToFloat(cpu.FR0.get32BinString());
			System.out.println("fr0="+fr);
		}
		else if(register==1){
			fr=Converter.binToFloat(cpu.FR1.get32BinString());
			System.out.println("fr1="+fr);
		}
		if(!indirectEnabled){
			memEl=memory.getCellByIndex((int)EA);
			memEl2=memory.getCellByIndex((int)EA+1);
			String highbits=memEl.getBinString();
			String lowbits=memEl.getBinString();
			String bit32=highbits+lowbits;
			dea=Converter.binToFloat(bit32);
			fr=fr+dea;
			System.out.println("fr="+fr);
		}
		else{
			memEl=memory.getCellByIndex((int)EA);
			memEl2=memory.getCellByIndex((int)EA+1);
			String highbits=memEl.getBinString();
			String lowbits=memEl.getBinString();
			memEl= memory.getCellByIndex((int)Converter.binToLong(highbits));
			memEl2= memory.getCellByIndex((int)Converter.binToLong(lowbits));
			highbits=memEl.getBinString();
			lowbits=memEl2.getBinString();
			String bit32=highbits+lowbits;
			dea=Converter.binToFloat(bit32);
			fr=fr+dea;
		}
		if(register==0){
			cpu.FR0.setBits(Converter.floatToBin(fr), 0);
		}
		else if(register==1){
			cpu.FR1.setBits(Converter.floatToBin(fr), 0);
		}
		
		
		
	}
}
