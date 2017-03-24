package MobileDev;

import java.io.UnsupportedEncodingException;
import java.util.Random;
import java.util.Scanner;

public class EncryDecry {
	public static int randomNum=29; 
	public static String Encryption(String value)
	{
		Random rand = new Random();
	
		String pass=value;
		char[]c=pass.toCharArray();
	    //randomNum = rand.nextInt((150-1) + 1) + 1;
		char enc[]=new char[c.length];
		//char dec[]=new char[c.length];
		int eni=0;
		for(char c1:c)
		{
			int val=(int)c1+randomNum;
			System.out.println(val);
			enc[eni]=(char)val;
			eni++;
			
		}
		String encrString=String.valueOf(enc);
		System.out.println("encr--"+encrString);
		return encrString;
	}
	public static String decryption(String value)
	{
		int dei=0;
		String pass=value;
		char[]c=pass.toCharArray();
		char dec[]=new char[c.length];
		for(char c1:dec)
		{
			int val=(int)c1-randomNum;
			dec[dei]=(char)val;
			dei++;
			
		}
		String decryValue=String.valueOf(dec);
		System.out.println("desc--"+decryValue);
		return decryValue;
	}

}
