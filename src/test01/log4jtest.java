package test01;

import java.io.File;
import java.io.FileInputStream;
import java.util.Arrays;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.core.config.ConfigurationSource;
import org.apache.logging.log4j.core.config.Configurator;


public class log4jtest {


	    //傳入目前類別名稱，封裝等級自訂
//	    private static final Logger logger = Logger.getLogger(log4jtest.class.getClass());

	    public static void main(String[] args) {        

	    	/* 將兌獎結果記錄到log4j2 */
//			ConfigurationSource source;
			String relativePath = "D:/log4jtest/log4j2.xml";
			File log4jFile = new File(relativePath);
			try {
				if (log4jFile.exists()) {
					ConfigurationSource source = new ConfigurationSource(new FileInputStream(log4jFile), log4jFile);
					Configurator.initialize(null, source);
					Logger log = LogManager.getLogger("practice");
					System.out.println("log4j2新增成功");
					log.info("test");

				} else {
					System.out.println("loginit failed");
					System.exit(1);
				}
			} catch (Exception e) {
				e.printStackTrace();
				System.exit(0);
			}
	    }
	
}
