package pay1177;

import java.io.IOException;
import java.net.URL;
import java.util.TreeMap;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.compress.harmony.unpack200.bytecode.forms.NarrowClassRefForm;

import com.sun.net.ssl.HttpsURLConnection;

//@WebServlet("/payServlet")
public class payServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public payServlet() {
		super();
	}

	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		doPost(req, resp);
	}

	protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {

		req.setCharacterEncoding("UTF-8");
		String action = req.getParameter("action");
		
		HttpsURLConnection connection = null;
		String  httpURL ="";
		URL url = new URL(httpURL);
		connection = (HttpsURLConnection) url.openConnection();
		connection.setRequestMethod("POST");
		connection.setConnectTimeout(15000);
		connection.setReadTimeout(60000);
		
		
		if("submit".equals(action)) {
			
			String merchantnumber = new String(req.getParameter("merchantnumber").trim());
			String ordernumber = new String(req.getParameter("ordernumber").trim());
			
//			String merchantordernumber = new String(req.getParameter("merchantordernumber").trim());
			
			Integer amount = new Integer(req.getParameter("merchantnumber").trim());
			
			String currency = new String(req.getParameter("currency").trim());
//			String paymenttype = new String(req.getParameter("paymenttype").trim());
			String paymenttype = "creditcard";
			
			String paytitle = new String(req.getParameter("paytitle").trim());
			String paymemo = new String(req.getParameter("paymemo").trim());
			String duedate = new String(req.getParameter("duedate").trim());
			String id = new String(req.getParameter("id").trim());
			String lang = new String(req.getParameter("lang").trim());
			String payphone = new String(req.getParameter("payphone").trim());
			String timestamp = new String(req.getParameter("timestamp").trim());
			String checksum;
			
			
//			驗證碼
			TreeMap map = new TreeMap<>();
			map.put("a", merchantnumber);
			map.put("b", ordernumber);
			map.put("c", merchantordernumber);
			
			map.put("d", amount);
			
			map.put("e", currency);
			map.put("f", paymenttype);
			map.put("g", paytitle);
			map.put("h", paymemo);
			map.put("i", duedate);
			map.put("j", id);
			map.put("k", paytitle);
			map.put("l", paytitle);
			map.put("m", paytitle);
			
			
		}
	
	}

}
