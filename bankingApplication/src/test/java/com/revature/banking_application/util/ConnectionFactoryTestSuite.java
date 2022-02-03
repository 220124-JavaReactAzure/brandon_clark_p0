package com.revature.banking_application.util;

import java.sql.Connection;

import org.junit.Assert;
import org.junit.Test;

public class ConnectionFactoryTestSuite {
	
	@Test
	public void test_getConnection_returnValidConnection_giveProvidedCredentials() {
		try(Connection conn = ConnectionFactory.getInstance().getConnection()){
			System.out.println(conn);
			Assert.assertNotNull(conn);
		}catch (Exception e) {
			
		}
	}

}
