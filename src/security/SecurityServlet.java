package security;

import java.io.IOException;
import java.math.BigInteger;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;
import java.util.Set;

import javax.crypto.spec.SecretKeySpec;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.math.BigInteger;
import java.security.MessageDigest;

//@WebServlet(name="SecurityServlet", urlPatterns={"/SecurityServlet"},loadOnStartup=10)

public class SecurityServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public SecurityServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doPost(req, resp);
	}

	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		req.setCharacterEncoding("UTF-8");
		String action = req.getParameter("action");

		if ("aessub".equals(action)) {
			String content = new String(req.getParameter("aes256").trim());
//			String password = new String(req.getParameter("aes256pass").trim());

			
			String encryptDbPwd = AES256Util.encode(content);
			
//			byte[] encryptResult = AES256.encrypt(content, password);  
//	        String aString = AES256.parseByte2HexStr(encryptResult); 
		
	        req.setAttribute("aesubres", encryptDbPwd);
			String url = "/front-end/Security.jsp";
			RequestDispatcher successView = req.getRequestDispatcher(url);
			successView.forward(req, resp);
			System.out.println("aes加密完成");
		}
		
		if ("aesdecrypt".equals(action)) {
			String content = new String(req.getParameter("aes256d").trim());
//			String password = new String(req.getParameter("aes256passd").trim());
			
			String decrypt = AES256Util.decode(content);
			
			
			req.setAttribute("aesdecrypt", decrypt);
			String url = "/front-end/Security.jsp";
			RequestDispatcher successView = req.getRequestDispatcher(url);
			successView.forward(req, resp);
			System.out.println("aes解密完成");
		}
		
		if ("md5sub".equals(action)) {
			String str = new String(req.getParameter("md5").trim());

			MessageDigest md5 = null;
			try {
				md5 = MessageDigest.getInstance("MD5");
			} catch (Exception e) {
				e.printStackTrace();
			}
			char[] charArray = str.toCharArray();
			byte[] byteArray = new byte[charArray.length];

			for (int i = 0; i < charArray.length; i++)
				byteArray[i] = (byte) charArray[i];
			byte[] md5Bytes = md5.digest(byteArray);
			StringBuffer hexValue = new StringBuffer();
			for (int i = 0; i < md5Bytes.length; i++) {
				int val = ((int) md5Bytes[i]) & 0xff;
				if (val < 16)
					hexValue.append("0");
				hexValue.append(Integer.toHexString(val));
			}
			String md5hash = hexValue.toString();

			req.setAttribute("md5sub", md5hash);
			String url = "/front-end/Security.jsp";
			RequestDispatcher successView = req.getRequestDispatcher(url);
			successView.forward(req, resp);
			System.out.println("md5加密完成");
		}

		if ("md5decrypt".equals(action)) {
			System.out.println("md5decrypt ok");
			String str = new String(req.getParameter("md5d").trim());

			///////////////////////////////////////
			char[] a = str.toCharArray();
			for (int i = 0; i < a.length; i++) {
				a[i] = (char) (a[i] ^ 't');
			}
			String s = new String(a);

			char[] b = s.toCharArray();
			for (int j = 0; j < b.length; j++) {
				b[j] = (char) (b[j] ^ 't');
			}
			String h = new String(b);
			///////////////////////////////////////
//			String dateString = str;
//			MessageDigest md5 = null;
//	        byte[] digest = MessageDigest.getInstance("md5").digest(dateString.getBytes("utf-8"));
//	        String md5code = new BigInteger(1, digest).toString(16);
//	        // 如果生成数字未满32位，需要前面补0
//	        for (int i = 0; i < 32 - md5code.length(); i++) {
//	            md5code = "0" + md5code;
//	        }
//	        
//	        String md5 = str;
//			char[] cArray=md5.toCharArray();
//			for (int i = 0; i < cArray.length; i++) {
//				if (cArray[i] >= 48 && cArray[i] <= 57) {
//					cArray[i] = (char) (105 - cArray[i]);
//				}
//			}
//
//			String h =String.valueOf(cArray);
			
			req.setAttribute("md5decrypt", h);
			String url = "/front-end/Security.jsp";
			RequestDispatcher successView = req.getRequestDispatcher(url);
			successView.forward(req, resp);
			System.out.println("md5解密完成");
		}

		if ("shasub".equals(action)) {
			String str = new String(req.getParameter("sha").trim());
			
			MessageDigest messageDigest;
			String encodeStr = "";
			try {
				messageDigest = MessageDigest.getInstance("SHA-256");
				messageDigest.update(str.getBytes(StandardCharsets.UTF_8));
				encodeStr = byte2Hex(messageDigest.digest());
			} catch (NoSuchAlgorithmException e) {
				e.printStackTrace();
			}
			String sha = encodeStr.toString();
			req.setAttribute("shasub", sha);
			String url = "/front-end/Security.jsp";
			RequestDispatcher successView = req.getRequestDispatcher(url);
			successView.forward(req, resp);
			System.out.println("sha加密完成");
			
		}
	}
//SHA256
	private String byte2Hex(byte[] bytes) {

		StringBuilder stringBuilder = new StringBuilder();
		String temp;
		for(byte aByte : bytes) {
			temp = Integer.toHexString(aByte & 0xff);
			if(temp.length() == 1) {
				stringBuilder.append("0");
			}
			stringBuilder.append(temp);
		}
		return stringBuilder.toString();
	}
//MD5
	public static String md5(String dateString) throws Exception {
//		MessageDigest md5 = null;
		byte[] digest = MessageDigest.getInstance("md5").digest(dateString.getBytes("utf-8"));
		String md5code = new BigInteger(1, digest).toString(16);
		// 如果生成数字未满32位，需要前面补0
		for (int i = 0; i < 32 - md5code.length(); i++) {
			md5code = "0" + md5code;
		}
		return md5code;
	}
}