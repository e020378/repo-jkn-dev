/**
 * 
 */
package test.connection.db;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import test.common.util.Constant;
import test.common.util.DbUtil;

/**
 * @author <a href="mailto:jerome.karmann-partner@arcelormittal.com?subject=DBUtilTestCase.java" alt="e020378">Jerome KARMANN</a>
 *
 */
public class DBUtilTestCase {

	private static Connection CONNECTION;

	@Before
	public void setUp() throws Exception {
		CONNECTION = DBUtilTestCase.getConnection();
	}

	public static Connection getConnection() throws SQLException {
		CONNECTION = DbUtil.createConnectionDevMaster();
		Assert.assertTrue("Failed to initialize connection", DbUtil.checkConnection(CONNECTION, Constant.TEST_QUERY_SYS));
		return CONNECTION;
	}

//	@Test
	public void testSelect() throws SQLException {
		DbUtil.changeDatabase(CONNECTION, "steeluser");
		int countExpected  = 5;
		String select = "SELECT top "+countExpected+" * FROM Person WHERE lastName like 'K%' order by lastName desc";
		List<List<Object>> result = DbUtil.executeQuery(CONNECTION, select);
		Assert.assertNotNull(result);
		Assert.assertSame("Wrong user count", countExpected, result.size());
		DbUtil.printDebug(result);
	}

	public void testChangeDatabase() throws SQLException {
		DbUtil.changeDatabase(CONNECTION, "steeluser_tech");
		int countExpected = 5;
		String select = "SELECT top "+countExpected+" * FROM log_integration WHERE nature_message='SALST'order by creationdt desc";
		List<List<Object>> result = DbUtil.executeQuery(CONNECTION, select);
		Assert.assertNotNull(result);
		Assert.assertSame("Wrong message count", countExpected, result.size());
		DbUtil.printDebug(result);
	}

	@Test
	public void testExecuteProcedureRead() throws SQLException {
		DbUtil.changeDatabase(CONNECTION, "steeluser");
		List<List<Object>> result = DbUtil.executeProcedureRead(CONNECTION, "sp_helptext", new String[]{"AUC_LEC_METALUNIT_CHARAC_DETAILS_BY_ID"});
		Assert.assertNotNull(result);
		DbUtil.printDebug(result);
		result = DbUtil.executeQuery(CONNECTION, "sp_helptext AUC_LEC_METALUNIT_CHARAC_DETAILS_BY_ID");
		Assert.assertNotNull(result);
		DbUtil.printDebug(result);
	}

//	@Test
//	sp_help 'AUC_LEC_METALUNIT_CHARAC_DETAILS_BY_ID'
	public void testSpHelp() throws SQLException {
		DbUtil.changeDatabase(CONNECTION, "steeluser");
//		List<List<Object>> result = DbUtil.executeProcedureRead(CONNECTION, "sp_helptext", new String[]{"AUC_LEC_METALUNIT_CHARAC_DETAILS_BY_ID"});
//		Assert.assertNotNull(result);
//		DbUtil.printDebug(result);
		List<List<Object>> result = DbUtil.executeQuery(CONNECTION, "sp_help AUC_LEC_METALUNIT_CHARAC_DETAILS_BY_ID");
		Assert.assertNotNull(result);
		DbUtil.printDebug(result);
	}
	/**
	 * @throws java.lang.Exception
	 */
	@AfterClass
	public static void tearDownAfterClass() throws Exception {
		DbUtil.closeConnection(CONNECTION);
	}
}
