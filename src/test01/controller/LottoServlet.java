package test01.controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Arrays;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
//import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.core.config.ConfigurationSource;
import org.apache.logging.log4j.core.config.Configurator;
import org.omg.DynamicAny.DynAnyOperations;

import com.sun.org.apache.bcel.internal.generic.DALOAD;

import test01.model.LottoDAO;
import test01.model.LottoJDBCDAO;
import test01.model.LottoVO;

/**
 * Servlet implementation class lottoServlet
 */
//@WebServlet("/lottoServlet")
public class LottoServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doPost(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// TODO Auto-generated method stub

		req.setCharacterEncoding("UTF-8");
		String action = req.getParameter("action");

		if ("check".equals(action)) {

			// 錯誤訊息
			List<String> errorMsgs = new LinkedList<String>();
			req.setAttribute("errorMsgs", errorMsgs);

			// 產生6個亂數
			HashSet<Integer> set = new HashSet<Integer>();
			while (set.size() < 6) {
				set.add((int) (Math.random() * 49 + 1));
			}

			// 將set轉成陣列
			Integer[] ans = set.toArray(new Integer[set.size()]);

			for (Integer anss : ans) {
				System.out.print(anss + ",");
			}

			try {
				/* 取出投注號碼 */
				int[] chs = new int[6];
				for (int i = 0; i < chs.length; i++) {
					Integer a = new Integer(req.getParameter("ch" + i).trim());
					System.out.print("選取號碼:" + a + " ");
					chs[i] = a;

					/* 範圍錯誤 */
					if (a < 1 || a > 49) {
						errorMsgs.add("數字範圍錯誤，請選擇1~49");
						RequestDispatcher failureView = req.getRequestDispatcher("/front-end/Lotto.jsp");
						failureView.forward(req, resp);
						System.out.println("數字範圍錯誤，請選擇1~49");
					}
					// 判斷輸入數字是否有與前面重複
					for (int j = 0; j < i; j++) {
						if (chs[j] == chs[i]) {
							System.out.print("數字重複，重新輸入");
							errorMsgs.add("數字重複，重新輸入");
							RequestDispatcher failureView = req.getRequestDispatcher("/front-end/Lotto.jsp");
							failureView.forward(req, resp);
							i--;
							break;
						}
					}
				}

				/* 算出中獎總數 */
				int sum = 0;
				for (int i = 0; i < ans.length; i++) {
					for (int j = 0; j < chs.length; j++) {
						if (ans[i] == chs[j]) {
							sum++;
						}
					}
				}
				System.out.println("算出中獎總數" + sum);

				// 寫入資料庫
				LottoVO lottoVO = new LottoVO();
				lottoVO.setAns(set.toString());
				lottoVO.setChoose(Arrays.toString(chs));
				lottoVO.setResult(sum);

				/* 將兌獎結果記錄到log4j2 */
//				ConfigurationSource source;
				String relativePath = "D:/log4jtest/log4j2.xml";
				File log4jFile = new File(relativePath);
				try {
					if (log4jFile.exists()) {
						ConfigurationSource source = new ConfigurationSource(new FileInputStream(log4jFile), log4jFile);
						Configurator.initialize(null, source);
						Logger log = LogManager.getLogger("practice");
//						Logger log = LogManager.getFormatterLogger("practice");
						System.out.println("log4j2新增成功");
						log.info("開獎號碼:" + set.toString() + "投注號碼" + Arrays.toString(chs) + "中獎總數" + sum);
						
					} else {
						System.out.println("loginit failed");
						System.exit(1);
					}
				} catch (Exception e) {
					e.printStackTrace();
					System.exit(0);
				}

//				//JDBC版本
//				LottoJDBCDAO dao = new LottoJDBCDAO();
//				dao.insert(lottoVO);
//				System.out.println("資料庫新增成功");
				
				//連線池版本
				LottoDAO dao = new LottoDAO();
				LottoDAO.init();
				dao.insert(lottoVO);
				System.out.println("資料庫新增成功");


				req.setAttribute("sum", sum);
				req.setAttribute("set", set);
				req.setAttribute("chs", Arrays.toString(chs));
				req.setAttribute("ans", Arrays.toString(ans));

				String url = "/front-end/Lotto.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url);
				successView.forward(req, resp);

			} catch (Exception e) {
//				errorMsgs.add("比對資料失敗:" + e.getMessage());
				errorMsgs.add("請勿空白並且填入數字");
				RequestDispatcher failureView = req.getRequestDispatcher("/front-end/Lotto.jsp");
				failureView.forward(req, resp);
			}

		}
	}

}
