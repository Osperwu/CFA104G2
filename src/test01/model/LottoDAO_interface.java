package test01.model;

import java.sql.Connection;
import java.util.List;

import org.jdom.Element;

import com.alibaba.druid.Constants;
import com.alibaba.druid.pool.DruidDataSource;
import com.alibaba.druid.pool.DruidPooledConnection;

import tools.sql.DruidConnectionPoolManager;

public interface LottoDAO_interface {

	public void insert(LottoVO lottoVO);
	public List<LottoVO> getALL();
	
	//content pool
	
	     public static void init(Element root, String encryptkey) throws Exception {
	          if(encryptkey == null) {
	               DruidConnectionPoolManager.register_datasources(root, null, DruidConnectionPoolManager.password_encrypt_method_none);
	          } else {
	               DruidConnectionPoolManager.register_datasources(root, encryptkey, DruidConnectionPoolManager.password_encrypt_method_aes256);
	          }
	     }

	     //error
	     public static Connection getConnection() throws Exception {
//	          return DruidConnectionPoolManager.getConnection(Constants.DS_ACCOUNTSSYSTEM);
	          return DruidConnectionPoolManager.getConnection(Constants.DRUID_STAT_SQL_MAX_SIZE);
	     }

	     public static DruidPooledConnection getDSConnection(String alias) throws Exception {
	          return DruidConnectionPoolManager.getDSConnection(alias);
	     }

	     public static DruidDataSource getDatasource(String alias) throws Exception {
	          return DruidConnectionPoolManager.getDatasource(alias);
	     }

	     public static char getDBType(String alias) throws Exception {
	          return DruidConnectionPoolManager.getDatasource(alias).getDbType().charAt(0);
	     }

	     public static final char dbservertype_sqlserver = 's';
	     public static final char dbservertype_db2 = 'd';
	     public static final char dbservertype_oracle = 'o';
	     public static final char dbservertype_mysql = 'm';

	     public static boolean isDuplicated(int sqlcode) {
	          return isDuplicated(dbservertype_sqlserver, sqlcode);
	     }
	     
	     public static boolean isDuplicated(char dbservertype, int sqlcode) {
	          switch(dbservertype) {
	               case dbservertype_sqlserver:
	                    // sqlserver
	                    if(sqlcode == 2627 || sqlcode == 2601) {
	                         return true;
	                    } else {
	                         return false;
	                    }
	               case dbservertype_db2:
	                    // db2
	                    if(sqlcode == -803) {
	                         return true;
	                    } else {
	                         return false;
	                    }
	               case dbservertype_mysql:
	                    // mysql
	                    if(sqlcode == 1062) {
	                         return true;
	                    } else {
	                         return false;
	                    }
	               case dbservertype_oracle:
	               default:
	                    return false;
	          }
	     }
	}

