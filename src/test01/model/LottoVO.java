package test01.model;

import java.io.Serializable;
import java.sql.Date;


public class LottoVO implements Serializable{

	//各欄位名稱
	private String ans;
	private String choose;
	private Date time;
	private Integer result;
	
	
	public String getAns() {
		return ans;
	}
	public void setAns(String ans) {
		this.ans = ans;
	}
	public String getChoose() {
		return choose;
	}
	public void setChoose(String choose) {
		this.choose = choose;
	}
	public Date getTime() {
		return time;
	}
	public void setTime(Date time) {
		this.time = time;
	}
	public Integer getResult() {
		return result;
	}
	public void setResult(Integer result) {
		this.result = result;
	}
	@Override
	public String toString() {
		return "LottoVO [ans=" + ans + ", choose=" + choose + ", time=" + time + ", result=" + result + "]";
	}
	
	

}
