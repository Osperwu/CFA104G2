package security;
import java.util.Base64;

public class StringByteArrayExamples {

	
	public static void main(String[] args) 
    {
        //Original byte[]
        byte[] bytes = "423052585876777748316532696E4D726343616F77513D3D".getBytes();
         
        //Base64 Encoded
        String encoded = Base64.getEncoder().encodeToString(bytes);
         
        //Base64 Decoded
        byte[] decoded = Base64.getDecoder().decode(encoded);
         
        //Verify original content
        System.out.println( new String(decoded) );
        System.out.println(decoded);
        System.out.println(bytes);
        
        
        
    }
}
