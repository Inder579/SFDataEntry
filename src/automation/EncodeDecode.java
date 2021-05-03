package automation;

import org.apache.commons.codec.binary.Base64;

public class EncodeDecode {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		String str1="User@goeasy.com.spl";
		 // Encode data on your side using BASE64
		  byte[] encodedString1 = Base64.encodeBase64(str1.getBytes());
		  System.out.println("encoded value is " + new String(encodedString1));

		  // Decode data on other side, by processing encoded data
		  byte[] decodedString1 = Base64.decodeBase64(encodedString1);
		  System.out.println("Decoded value is " + new String(decodedString1));
		  
		  String str2="Hello@32";
			 // Encode data on your side using BASE64
			  byte[] encodedString2 = Base64.encodeBase64(str2.getBytes());
			  System.out.println("encoded value is " + new String(encodedString2));

			  // Decode data on other side, by processing encoded data
			  byte[] decodedString2 = Base64.decodeBase64(encodedString2);
			  System.out.println("Decoded value is " + new String(decodedString2));
		  

	}

}
