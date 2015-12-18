/**
 * 
 */
package test.connection.db;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.List;

import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import test.common.util.DbUtil;
import test.common.util.TableMappingUtil;
import test.common.util.dto.LogIntegrationDTO;

/**
 * @author <a href="mailto:jerome.karmann-partner@arcelormittal.com?subject=LogIntegrationTestCase.java" alt="e020378">Jerome KARMANN</a>
 *
 */
public class LogIntegrationTestCase {

	public static final String SELECT_TOP_X = "SELECT TOP {0} * FROM log_integration {1} order by {2} desc";
	private static Connection CONNECTION;
	/**
	 * @throws java.lang.Exception
	 */
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
	}

	/**
	 * @throws java.lang.Exception
	 */
	@Before
	public void setUp() throws Exception {
		CONNECTION = DBUtilTestCase.getConnection();
	}

	@Test
	public void testLogIntegration() throws SQLException {
		int count = 3;
		String query = MessageFormat.format(SELECT_TOP_X, count, "", 1);
		DbUtil.changeDatabase(CONNECTION, "steeluser_tech");
		ResultSet result = DbUtil.executeQueryResultSet(CONNECTION, query);
		List<LogIntegrationDTO> ouput = new ArrayList<LogIntegrationDTO>();
		while(result.next()){
			ouput.add(TableMappingUtil.resultSet2LogIntegrationDTO(result));
		}
		Assert.assertNotNull(result);
		Assert.assertSame("Wrong user count", count, ouput.size());
		LogIntegrationDTO entry = ouput.get(0);
		System.out.println(entry.getMessage());
	}

	/**
	 * @throws java.lang.Exception
	 */
	@AfterClass
	public static void tearDownAfterClass() throws Exception {
		DbUtil.closeConnection(CONNECTION);
	}

}
