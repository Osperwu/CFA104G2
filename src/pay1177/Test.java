package pay1177;

import java.util.Iterator;
import java.util.Set;
import java.util.TreeMap;

import com.alibaba.druid.sql.ast.statement.SQLWithSubqueryClause.Entry;

public class Test {
	public static void main(String[] args) {

		String amount = "200.5";

		String merchantnumber = "201387";

		String ordernumber = "54613351";

		String paytitle = "這是測試";

		String paymemo = "321";

		TreeMap<String,String> map = new TreeMap<>();

//		map.put("a", "amount="+amount);
//		map.put("b", "&merchantnumber="+merchantnumber);
//		map.put("c", "&ordernumber="+ordernumber);
//		map.put("d", "&paytitle="+paytitle);
//		map.put("d", "&paymemo="+paymemo);
		
		map.put("amount", amount);
		map.put("merchantnumber", merchantnumber);
		map.put("ordernumber", ordernumber);
		map.put("paytitle", paytitle);
		map.put("paymemo", paymemo);

		Set<String> keySet = map.keySet();
		
		StringBuffer buf=new StringBuffer();
		for (String key : keySet) {
			String value = map.get(key);
			
			buf.append(key+"="+value+"&");
			
			System.out.println(key+"="+value);
		}
		buf.deleteCharAt(buf.length() - 1).toString();
		System.out.println(buf);
		
		
	}
}
