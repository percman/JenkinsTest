package designtest;

import static org.junit.Assert.assertNotNull;

import java.sql.Connection;

import org.apache.log4j.Logger;
import org.junit.BeforeClass;
import org.junit.Test;

import design.ConnectionUtil;

public class ConnectionUtilTest {
	static Logger logger;
	public static Connection connection;

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		logger = Logger.getLogger(Connection.class);
		connection = ConnectionUtil.connect(logger);
	}

	@Test
	public void testGetInstance() {	
		assertNotNull(connection);
	}
}
