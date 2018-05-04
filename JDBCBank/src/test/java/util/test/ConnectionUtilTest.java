package util.test;

import static org.junit.Assert.*;

import java.sql.Connection;

import org.apache.log4j.Logger;
import org.junit.BeforeClass;
import org.junit.Test;

import util.ConnectionUtil;

public class ConnectionUtilTest {
	static Connection c;
	static Logger logger;
	
	@BeforeClass
	public static void setUp() {
		logger = Logger.getLogger(ConnectionUtilTest.class);
		c = ConnectionUtil.getConnection(logger);
	}
	
	@Test
	public void testGetConnection() {
		assertFalse(c == null);
		assertTrue(c.toString() instanceof String);
	}
}