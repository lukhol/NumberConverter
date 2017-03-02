package com.numberconverter;

import com.numberconverter.NumberConverter;

public class NumberConverter {
	public static void main(String[] args){
		NumberConverter nc = new NumberConverter();
		/*
		String hex = nc.DECtoHEX("39435");
		System.out.println(hex);
		System.out.println(nc.HEXtoDEC(hex));
		
		String hex2 = nc.DECtoHEX("256987");
		System.out.println(hex2);
		System.out.println(nc.HEXtoDEC(hex2));
		
		System.out.println(nc.DECtoBIN("498025"));
		System.out.println(nc.BINtoDEC("1111001100101101001"));
		
		nc.HEXtoBIN("FA4");
		*/
		
		//System.out.println(nc.checkHex("32AG"));
	}
	
	private char[] hexSign = { '0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'A', 'B', 'C', 'D', 'E', 'F' };
	private int[] binPower = { 1, 2, 4, 8, 16, 32, 64, 128, 256, 512, 1024, 2048, 4096, 8192, 16384, 32768, 65536, 131072, 262144, 524288, 1048576, 2097152, 4194304, 8388608, 16777216, 33554432, 67108864, 134217728, 268435456, 536870912, 1073741824};
	private long[] hexPower = { 1, 16, 256, 4096, 65536, 1048576, 16777216, 268435456, 4294967296L, 68719476736L};
	
	public String DECtoHEX(String dec){
		if(!checkDec(dec)) return "B³¹d";
		String hex = "";
		int limit = 9;
		int decInt = Integer.parseInt(dec);
		int highestHexPower = 0;
		
		for(int i = 0 ; i <= limit ; i++){
			if(hexPower[i] > decInt){
				highestHexPower = i;
				break;
			}
		}
		
		for(int i = highestHexPower - 1 ; i >= 0 ; i--){
			 hex += hexSign[(int)(decInt/hexPower[i])];
			 decInt %= hexPower[i];
		}
		
		return hex;
	}
	public String HEXtoDEC(String hex){
		hex = hex.toUpperCase();
		if(!checkHex(hex)) return "B³¹d";
		String dec = "";
		int decInt = 0;
		for(int i = hex.length() - 1 , j = 0; i >= 0 ; i--, j++){
			//System.out.println("i: " + i + ", j = " + j);
			int intTempChar = hex.charAt(j) - 48;
			
			if(intTempChar == 17) intTempChar = 10;
			else if(intTempChar == 18) intTempChar = 11;
			else if(intTempChar == 19) intTempChar = 12;
			else if(intTempChar == 20) intTempChar = 13;
			else if(intTempChar == 21) intTempChar = 14;
			else if(intTempChar == 22) intTempChar = 15;
			
			decInt += hexPower[i] * intTempChar;
		}
		dec = Integer.toString(decInt);
		return dec;
	}
	
	public String DECtoBIN(String dec){
		if(!checkDec(dec)) return "B³¹d";
		String bin = "";
		int decInt = Integer.parseInt(dec);
		while(decInt >= 1){
			if(decInt%2 == 1) bin += '1';
			else bin += '0';
			
			decInt /= 2;
		}
		String bin2 = "";
		for(int i = bin.length() - 1; i >= 0 ; i--){
			bin2 += bin.charAt(i);
		}
		return bin2;
	}
	public String BINtoDEC(String bin){
		if(!checkBin(bin)) return "B³¹d";
		String dec = "";
		int decInt = 0;
		for(int i = bin.length() - 1 , j = 0; i >= 0 ; i--, j++){
			decInt += ((int)(bin.charAt(i))-48) * binPower[j];
		}
		dec = Integer.toString(decInt);
		return dec;
	}

	public String HEXtoBIN(String hex){
		hex = hex.toUpperCase();
		if(!checkHex(hex)) return "B³¹d";
		String bin = "";
		bin = DECtoBIN(HEXtoDEC(hex));
		return bin;
	}
	public String BINtoHEX(String bin){
		bin = bin.toUpperCase();
		if(!checkBin(bin)) return "B³¹d";
		return DECtoHEX(BINtoDEC(bin));
	}
	
	private boolean checkBin(String bin){
		if(bin.length() == 0) return false;
		for(int i = 0 ; i < bin.length() ; i++){
			if(bin.charAt(i) != '0' && bin.charAt(i) != '1') return false;
		}
		return true;
	}
	private boolean checkDec(String dec){
		boolean ifGood = true;
		if(dec.length() == 0) return false;
		for(int i = 0 ; i < dec.length() ; i++){
			if(!(dec.charAt(i) >=  '0' && dec.charAt(i) <= '9')) ifGood = false;
		}
		return ifGood;
	}
	private boolean checkHex(String hex){
		boolean ok = true;
		if(hex.length() == 0) return false;
		for(int i = 0 ; i < hex.length() ; i++){
			if(!(hex.charAt(i) >=  '0' && hex.charAt(i) <= '9') && !(hex.charAt(i) >= 'A' && hex.charAt(i) <= 'F')) ok = false;
		}
		return ok;
	}
}
