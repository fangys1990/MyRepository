package com.jdbc.base.procedure;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Types;

import com.jdbc.base.common.ConnectionManager;
import com.jdbc.base.common.SqlConsts;

/**
 * @Author Fangys
 * @Desc  JDBC存储过程调用
 * @Date 2016年2月1日 下午2:27:30
 * @Version 1.x 
 */
public class ProcedureTest {
	
/**	
	CREATE PROCEDURE my_test(IN pi_userid INT,
		IN pi_status INT,
		IN pi_switch INT,
		OUT iResult VARCHAR(2),
		OUT iTime VARCHAR(100))
		
		BEGIN
		DECLARE temp_time VARCHAR(100);
		SET iResult = '00';
		
		SELECT NOW() INTO temp_time;
		DECLARE EXIT HANDLER FOR SQLEXCEPTION ROLLBACK;
     	START TRANSACTION;
		
		PREPARE updateStr FROM 'update user_info a set a.status=? where a.userid=?';
		SET @param_1=pi_status;
		SET @param_2=pi_userid;
		EXECUTE updateStr USING @param_1, @param_2;

		IF NOT ISNULL(pi_switch) THEN
			SET iTime = temp_time;
		ELSE
			SET iTime = '2012-01-01 09:00:00';
		END IF;
		COMMIT;
		SET iResult = '01';
	END
 * @throws SQLException 
*/
	
	public static void main(String[] args) throws SQLException {
		Connection conn = ConnectionManager.getInstance().getConnection();
		
		CallableStatement  cs = conn.prepareCall("{call my_test(?,?,?,?,?)}");
		cs.setInt(1, 5);
		cs.setInt(2, 11);
		cs.setInt(3, 1);
		cs.registerOutParameter(4, Types.VARCHAR);
		cs.registerOutParameter(5, Types.VARCHAR);
		
		cs.execute();
		
		String iResult = cs.getString(4);
		String iTime = cs.getString(5);
		System.out.println("iResult:" + iResult + " iTime:" + iTime);
		
	}
}
