package wpa2hasher;

import java.math.BigInteger;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.PBEKeySpec;

public class WPA2Hasher {
	
	private String ssid;
	private String password;
	private boolean minimal = false;
	
	public WPA2Hasher(String SSID, String PASSWORD) {
		ssid = SSID;
		password = PASSWORD;
	}
	
	public WPA2Hasher(String SSID, String PASSWORD, boolean MINIMAL) {
		ssid = SSID;
		password = PASSWORD;
		minimal = MINIMAL;
	}
	
	public void start() {
		try {
			System.out.println(generate());
			if(!minimal) {
				exit();
			}
		} catch(Exception ex) {
			System.err.println("WARN: " + ex.getMessage());
		}
	}
	
	public String generate() {
		try {
			return toHex(SecretKeyFactory.getInstance("PBKDF2WithHmacSHA1").generateSecret(
					new PBEKeySpec(password.toCharArray(), ssid.getBytes(), 4096, 256)).getEncoded());
		}catch(Exception ex) {return "ERROR";}
	}
	
	private void exit() {
		System.out.println("\nHash generated!");
		System.out.println("https://github.com/supamonkey2000/WPA2Hasher");
	}
	
	private String toHex(byte[] array) {
		try {
			String hex = new BigInteger(1,array).toString(16);
			int paddingLength = (array.length * 2) - hex.length();
			if(paddingLength > 0) {
				return String.format("%0" + paddingLength + "d", 0) + hex;
			}else {
				return hex;
			}
		}catch(Exception ex) {}
		return "WARN: Unknown error occured";
	}
	
	public static void main(String[] args) {
		if(args.length < 2) {
			System.out.println("Incorrect usage! Example:");
			System.out.println("> java -jar wpa2h.jar <ssid> <password>");
			try {
				Thread.sleep(4000);
			}catch(Exception iex) {
				System.err.println("WARN: " + iex.getMessage());
			}
			//DEBUG CODE:
			//WPA2Hasher wpah = new WPA2Hasher("MyWifiNetwork", "SecurePassword");
			//wpah.start();
		} else {
			if(args[1].length() < 8) {
				System.out.println("Recommended password length is 8 charachters or more!");
			}
			if(args.length > 2) {
				if(args[2].contains("-m")) {
					WPA2Hasher wpah = new WPA2Hasher(args[0], args[1], true);
					wpah.start();
				} else {
					System.out.println("Unknown parameter!");
				}
			} else {
				System.out.println("Calculating WPA/WPA2 Hash using PBKDF2/HMAC SHA1...\n");
				WPA2Hasher wpah = new WPA2Hasher(args[0], args[1]);
				wpah.start();
			}
		}
	}
}
