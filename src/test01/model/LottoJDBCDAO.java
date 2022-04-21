package test01.model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class LottoJDBCDAO implements LottoDAO_interface{
	
	String driver = "com.microsoft.sqlserver.jdbc.SQLServerDriver";
	String url = "jdbc:sqlserver://127.0.0.1:1433;DatabaseName=lotto;trustServerCertificate=true";
	String userid = "osperwu";
	String passwd = "Aa030606";
	
	
	private static final String INSERT_STMT = 
			"INSERT INTO dbo.lottotable"
			+ " (ans,"
			+ "choose,"
			+ "time,"
			+ "result) "
			+ "VALUES (?, ?, GETDATE(), ?)";
	
	private static final String GET_ALL_STMT = 
//			"SELECT"
//			+ "ans,"
//			+ "choose,"
//			+ "time,"
//			+ "result"
//			+ "FROM dbo.lottotable";
			
			"SELECT * from dbo.lottotable";
	

	@Override
	public void insert(LottoVO lottoVO) {
		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(INSERT_STMT);

			pstmt.setString(1, lottoVO.getAns());
			pstmt.setString(2, lottoVO.getChoose());
			pstmt.setInt(3, lottoVO.getResult());

			pstmt.executeUpdate();

			// Handle any driver errors
		} catch (ClassNotFoundException e) {
			throw new RuntimeException("Couldn't load database driver. " + e.getMessage());
			// Handle any SQL errors
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. " + se.getMessage());
			// Clean up JDBC resources
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
	}

	@Override
	public List<LottoVO> getALL() {
		List<LottoVO> list = new ArrayList<>();
		LottoVO lottoVO = null;

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		

		try {
			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(GET_ALL_STMT);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				lottoVO = new LottoVO();
				lottoVO.setAns(rs.getString("ans"));
				lottoVO.setChoose(rs.getString("choose"));
				lottoVO.setTime(rs.getDate("time"));
				lottoVO.setResult(rs.getInt("result"));
				list.add(lottoVO); // Store the row in the list
			}

			// Handle any driver errors
		} catch (ClassNotFoundException e) {
			throw new RuntimeException("Couldn't load database driver. " + e.getMessage());
			// Handle any SQL errors
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. "
					+ se.getMessage());
			// Clean up JDBC resources
		} finally {
			if (rs != null) {
				try {
					rs.close();
				} catch (SQLException se) {
					se.printStackTrace(System.err);
				}
			}
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

	public static void main(String[] args) {
		// 載入JDBC驅動
		String driverName = "com.microsoft.sqlserver.jdbc.SQLServerDriver"; //新版
//		String driverName =	"com.microsoft.jdbc.sqlserver.SQLServerDriver"; //舊版
		
//		String dbURL = "jdbc:microsoft:sqlserver://127.0.0.1:1433;DatabaseName=test;trustServerCertificate=true"; // 連線伺服器和資料庫test
		String dbURL = "jdbc:sqlserver://localhost;DatabaseName=lotto;trustServerCertificate=true"; // 連線伺服器和資料庫test
//						jdbc:sqlserver://localhost:1433;DatabaseName=dbname
//		String userName = "sa"; // 預設使用者名稱
//		String userPwd = "@1177tech"; // 密碼
		String userName = "osperwu"; // 預設使用者名稱
		String userPwd = "Aa030606"; // 密碼
//		Connection dbConn;
		try {
			
			Class.forName(driverName);
			System.out.println("載入JDBC驅動成功");
			Connection dbConn = DriverManager.getConnection(dbURL, userName, userPwd);
			System.out.println("url+帳號密碼成功");
			System.out.println("Connection Successful!"); // 如果連線成功 控制檯輸出Connection Successful!
		
			LottoDAO_interface dao = new LottoJDBCDAO();
			List<LottoVO> list = dao.getALL();
			for(LottoVO a : list) {
				System.out.println(a.getAns());
				System.out.println(a.getChoose());
				System.out.println(a.getTime());
				System.out.println(a.getResult());
			}
		
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
