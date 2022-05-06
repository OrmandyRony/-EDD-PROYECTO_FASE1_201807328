
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.text.SimpleDateFormat;
import java.util.Calendar;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author USUARIO
 */
public class Bloque {
    int index;
    String timeStamp;
    String dataEntrega;
    String previousHash;
    String hash;
    
    public Bloque (int index, String data, String previusHash) {
        this.index = index;
        this.dataEntrega = data;
        Calendar cal = Calendar.getInstance();
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss.SS");
        String strDate = sdf.format(cal.getTime());
        this.timeStamp = strDate;
    }

    public String convertirSHA256(String password) {
	MessageDigest md = null;
	try {
		md = MessageDigest.getInstance("SHA-256");
	} 
	catch (NoSuchAlgorithmException e) {		
		e.printStackTrace();
		return null;
	}
	    
	byte[] hash = md.digest(password.getBytes());
	StringBuffer sb = new StringBuffer();
	    
	for(byte b : hash) {        
		sb.append(String.format("%02x", b));
	}
	    
	return sb.toString();
}
}
