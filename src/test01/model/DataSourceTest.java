package test01.model;

import java.sql.Connection;

import org.junit.jupiter.api.Test;

import com.alibaba.druid.pool.DruidDataSource;

//////////////////////////////////////////////////////////
import static org.junit.Assert.*;
import java.io.InputStream;
import java.io.Reader;
import java.sql.Connection;
import java.util.Properties;
import javax.sql.DataSource;
import com.alibaba.druid.pool.DruidDataSource;
import com.alibaba.druid.pool.DruidDataSourceFactory;
import com.alibaba.druid.pool.DruidPooledConnection;

public class DataSourceTest {

	 // 直接創建連接池對象

    @Test

    public void testName() throws Exception {

         // 1.創建連接池對象

         DruidDataSource ds = new DruidDataSource();

         // 2.設置連接數據庫的賬號密碼

         ds.setDriverClassName("com.microsoft.sqlserver.jdbc.SQLServerDriver");

         ds.setUrl("jdbc:sqlserver://127.0.0.1:1433;DatabaseName=lotto;trustServerCertificate=true");

         ds.setUsername("osperwu");

         ds.setPassword("Aa030606");


         ds.setMaxActive(10);// 最大連接數

         // 3.獲取連接對象

         Connection conn = ds.getConnection();

         System.out.println(conn);

    }
    
    @Test

    public void testDataSourceByFactory() throws Exception {

 

         // 1.獲取類加載器用於加載clsspath下面的 配置文件

         ClassLoader classLoader = Thread.currentThread().getContextClassLoader();

         // 2.讀取druid.properties配置文件

         InputStream inputStream = classLoader.getResourceAsStream("druid.properties");

         // 3.創建Properties對象，並讀取配置文件對應的輸入流

         Properties p = new Properties();

         p.load(inputStream);

 

         // 4.創建連接池對象

         DataSource ds = DruidDataSourceFactory.createDataSource(p);

         // 5.獲取連接對象

         Connection conn = ds.getConnection();

         System.out.println(conn);

    }

 
}
