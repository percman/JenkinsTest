package utiltest;

import static org.junit.Assert.assertNotNull;

import java.sql.Connection;

import org.apache.log4j.Logger;
import org.junit.BeforeClass;
import org.junit.Test;

import designpattern.ConnectionUtil;

public class ConnectionUtilTest {
	static Logger logger;
	public static ConnectionUtil connectionUtil;

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		logger = Logger.getLogger(Connection.class);
		connectionUtil = ConnectionUtil.getInstance(logger);
	}

	@Test
	public void testGetInstance() {	
		assertNotNull(connectionUtil.getConnection());
	}
}
