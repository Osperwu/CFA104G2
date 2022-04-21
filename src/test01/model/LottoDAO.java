package test01.model;

import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.activation.DataSource;
import javax.print.Doc;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;

import org.apache.poi.xssf.streaming.SXSSFCell;
import org.jdom.Document;
import org.jdom.Element;
import org.jdom.JDOMException;
import org.jdom.input.SAXBuilder;

import com.alibaba.druid.pool.DruidDataSource;
import com.sun.org.apache.bcel.internal.generic.I2F;
import com.sun.org.apache.bcel.internal.generic.NEW;
import com.sun.org.apache.xml.internal.security.Init;

public class LottoDAO implements LottoDAO_interface {

	String driver = "com.microsoft.sqlserver.jdbc.SQLServerDriver";
	String url = "jdbc:sqlserver://127.0.0.1:1433;DatabaseName=lotto;trustServerCertificate=true";
	String userid = "osperwu";
	String passwd = "Aa030606";
	public static DruidDataSource dataSource = null;

	private static final String INSERT_STMT = "INSERT INTO dbo.lottotable" + " (ans," + "choose," + "time," + "result) "
			+ "VALUES (?, ?, GETDATE(), ?)";

	private static final String GET_ALL_STMT =

			"SELECT * from dbo.lottotable";

	public static void testalibaba() throws Exception {

		// 1.創建連接池對象

		DruidDataSource ds = new DruidDataSource();

		// 2.設置連接數據庫的賬號密碼

		ds.setDriverClassName("com.microsoft.sqlserver.jdbc.SQLServerDriver");

		ds.setUrl("jdbc:sqlserver://127.0.0.1:1433;DatabaseName=lotto;trustServerCertificate=true");

		ds.setUsername("osperwu");

		ds.setPassword("Aa030606");

		ds.setMaxActive(10);// 最大連接數

		// 3.獲取連接對象

		Connection conn = null;
		try {
			conn = ds.getConnection();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		System.out.println(conn);

	}

	public static void init() {

		File file = null;
		Document document;
		FileInputStream fis;
		InputStreamReader isr = null;
		SAXBuilder sax = new SAXBuilder();

		file = new File("D:\\osperWork\\apconf\\datasource.xml");
		String path = file.getPath();
		System.out.println("datasource.xml的檔案路徑" + path);

		try {
			fis = new FileInputStream(file);
			isr = new InputStreamReader(fis, "utf-8");
			document = sax.build(isr);

			Element test = document.getRootElement();
			DBObject.init(test, null);

			Connection conn = DBObject.getConnection();

			if (!conn.isClosed()) {
				System.out.println("連線池設定成功");
			}

			conn.close();
			isr.close();
			fis.close();

		} catch (FileNotFoundException e) {
			System.out.println("讀取檔案失敗");
			e.printStackTrace();
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (JDOMException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	@Override
	public void insert(LottoVO lottoVO) {
//	 // 1.創建連接池對象
//
//        DruidDataSource ds = new DruidDataSource();
//		
//     // 2.設置連接數據庫的賬號密碼
//
//        ds.setDriverClassName(driver);
//
//        ds.setUrl(url);
//
//        ds.setUsername(userid);
//
//        ds.setPassword(passwd);
//
//        ds.setMaxActive(10);// 最大連接數
//        
     // 3.獲取連接對象
        Connection con = null;
		PreparedStatement pstmt = null;
		try {
			//原版
//			con = ds.getConnection();
			
//			init();
			//都在xml做好了，直接拿連線
			con = DBObject.getConnection();
		
			pstmt = con.prepareStatement(INSERT_STMT);
			pstmt.setString(1, lottoVO.getAns());
			pstmt.setString(2, lottoVO.getChoose());
			pstmt.setInt(3, lottoVO.getResult());

			pstmt.executeUpdate();
			System.out.println("insert成功");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			if (pstmt != null) {
				try {
					pstmt.close();
				} catch (SQLException se) {
					se.printStackTrace(System.err);
				}
			}
			if (con != null) {
				try {
					con.close();
				} catch (Exception e) {
					e.printStackTrace(System.err);
				}
			}
		}
	}

	@Override
	public List<LottoVO> getALL() {

		List<LottoVO> list = new ArrayList<>();
		LottoVO lottoVO = null;

		
		//0.連線池測試
		try {
//			Connection ds = DBObject.getConnection();
		} catch (Exception e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
//		// 1.創建連接池對象
//
//		DruidDataSource ds = new DruidDataSource();
//
//		// 2.設置連接數據庫的賬號密碼
//
//		ds.setDriverClassName(driver);
//
//		ds.setUrl(url);
//
//		ds.setUsername(userid);
//
//		ds.setPassword(passwd);
//
//		ds.setMaxActive(10);// 最大連接數

		// 3.獲取連接對象
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			//原版
//			con = ds.getConnection();
			
			//都在xml做好了，直接拿連線
			try {
				con = DBObject.getConnection();
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			System.out.println("連線成功");
			pstmt = con.prepareStatement(GET_ALL_STMT);
			rs = pstmt.executeQuery();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			System.out.println("連線失敗");
			e.printStackTrace();
		}

		try {
			while (rs.next()) {
				lottoVO = new LottoVO();
				lottoVO.setAns(rs.getString("ans"));
				lottoVO.setChoose(rs.getString("choose"));
				lottoVO.setTime(rs.getDate("time"));
				lottoVO.setResult(rs.getInt("result"));
				list.add(lottoVO); // Store the row in the list
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			if (pstmt != null) {
				try {
					pstmt.close();
				} catch (SQLException se) {
					se.printStackTrace(System.err);
				}
			}
			if (con != null) {
				try {
					con.close();
				} catch (Exception e) {
					e.printStackTrace(System.err);
				}
			}
		}
		return list;
	}

	// main方法做測試
	public static void main(String[] args) {

		LottoDAO dao = new LottoDAO();

//		LottoDAO_interface dao = new LottoDAO();
		// test 連線是否成功
//		try {
//			testalibaba();
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
//		System.out.println("succes");

		// 測試getALL
		dao.init();
//		List<LottoVO> list = dao.getALL();
//		for(LottoVO a : list) {
//			System.out.println(a.getAns());
//			System.out.println(a.getChoose());
//			System.out.println(a.getTime());
//			System.out.println(a.getResult());
//		}

		// 測試INSERT
//		LottoVO lottoVO = new LottoVO();
//		lottoVO.setAns("[23, 8, 41, 12, 29, 31]");
//		lottoVO.setChoose("[12, 1, 2, 3, 4, 5]");
//		lottoVO.setResult(6);
//		dao.insert(lottoVO);


	}

}
