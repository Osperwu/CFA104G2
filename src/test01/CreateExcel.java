package test01;

import java.io.ByteArrayOutputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.List;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.hssf.util.HSSFColor;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFCellStyle;
import org.apache.poi.xssf.usermodel.XSSFFont;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class CreateExcel {

	public static String battoex() throws IOException {

		String driverName = "com.microsoft.sqlserver.jdbc.SQLServerDriver"; // 新版
		String dbURL = "jdbc:sqlserver://localhost;DatabaseName=lotto;trustServerCertificate=true";
		String userName = "osperwu"; // 預設使用者名稱
		String userPwd = "Aa030606"; // 密碼
		String stmt = "select * from dbo.lottotable";

		Workbook xssbook = new XSSFWorkbook();
		String path="D:lotto.xls";
		XSSFSheet sheet = (XSSFSheet) xssbook.createSheet("所有兌獎紀錄");

		FileOutputStream fileOut = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			
			Class.forName(driverName);
			System.out.println("載入JDBC驅動成功");
			con = DriverManager.getConnection(dbURL, userName, userPwd);
			pstmt = con.prepareStatement(stmt);
			rs = pstmt.executeQuery();

			ResultSetMetaData rsmd = rs.getMetaData();// 得到結果集的欄位名
			int c = rsmd.getColumnCount();// 得到資料表的結果集的欄位的數量
			// 生成表單的第一行，即表頭
			XSSFRow row0 = sheet.createRow(0);// 建立第一行
			sheet.setColumnWidth(0, 100 * 100);
			sheet.setColumnWidth(1, 100 * 100);
			sheet.setColumnWidth(2, 100 * 100);
			sheet.setColumnWidth(3, 100 * 100);
			sheet.setColumnWidth(4, 100 * 100);

			for (int i = 0; i < c; i++) {
				XSSFCell cel = row0.createCell(i);// 建立第一行的第i列
				cel.setCellValue(rsmd.getColumnName(i + 1));

			}

			// 將資料表中的資料按行匯入進Excel表中
			int r = 1;
			while (rs.next()) {
				XSSFRow row = sheet.createRow(r++);// 建立非第一行的其他行
				for (int i = 0; i < c; i++) {// 仍然是c列，匯入第r行的第i列
					XSSFCell cel = row.createCell(i);
					// 以下兩種寫法均可
//					cel.setCellValue(rs.getString(rsmd.getColumnName(i+1)));
					cel.setCellValue(rs.getString(i + 1));
				}
			}

			// 這裡只是純產出excel檔案而以
			// 產出Excel 97（XLS）文件格式
//			Workbook hssfwb = new HSSFWorkbook();
//			fileOut = new FileOutputStream("D:/workbook.xls");
//			hssfwb.write(fileOut);
//			fileOut.close();

			// Excel 2007的（XLSX）文件格式

			fileOut = new FileOutputStream("D:/osperWork/deployed/schedule/workbook1.xlsx");
			xssbook.write(fileOut);
			xssbook.close();
		} catch (IOException ioe) {
			ioe.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			if (null != fileOut) {
				fileOut.close();
			}
		}
		return path;
	}
	public static void main(String[] args) throws IOException {
		battoex();
		System.out.println("0.0");
	}
}
