package test.auction.um.sql;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.text.MessageFormat;
import java.util.List;

import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import test.common.util.Constant;
import test.common.util.DbUtil;
import test.common.util.dto.SqlParameterDTO;

/**
 * @author <a href="mailto:jerome.karmann-partner@arcelormittal.com?subject=AperamParamReclassementTest.java" alt="e020378">Jerome KARMANN</a>
 *
 */
public class AperamParamReclassementTestCase {

	public static Connection CONNECTION;
	public static Integer UM_ID_LAST;
	/**
	 * @throws java.lang.Exception
	 */
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
	}

	/**
	 * @throws java.lang.Exception
	 */
	@AfterClass
	public static void tearDownAfterClass() throws Exception {
	}

	/**
	 * @throws java.lang.Exception
	 */
	@Before
	public void setUp() throws Exception {
		CONNECTION = DbUtil.createConnectionDevMaster();
		UM_ID_LAST = readUmMaxIdFromValeurCaracteristique();
	}

	@Test
	public void umReadCodeValeurParamClassementTest() throws SQLException {
		final String statementCall = "{CALL LEC_LISTE_PARAM_CLASSEMENT (?)}";
		SqlParameterDTO param = DbUtil.createSqlParameter(1, Types.INTEGER, UM_ID_LAST, "Id_UM");
		ResultSet resultSet = DbUtil.executePrepareStatement(CONNECTION, statementCall, param);
		List<List<Object>> result = DbUtil.resultSet2List(resultSet, true);
		Assert.assertNotNull("No resultSet returned", result);
		Assert.assertTrue("Empty resultSet returned", result.size()>0);
		for(List<Object> row: result){
			Assert.assertTrue("No row data selected", row.size()>0);
			System.out.println(MessageFormat.format("{0} = {1}", row.get(0),row.get(1)));
		}
	}

	public static Integer readUmMaxIdFromValeurCaracteristique() throws SQLException{
		Integer idUm = null;
		List<List<Object>> result = DbUtil.executeQuery(CONNECTION, Constant.Query.UM_MAX_ID_FROM_VALEUR_CARACTERISTIQUE);
		Assert.assertNotNull("No resultSet returned", result);
		Assert.assertTrue("Empty resultSet returned", result.size()>0);
		List<Object> row = result.get(0);
		if(row!=null && row.size()>0){
			idUm = (Integer)row.get(0);
		}
		Assert.assertNotNull(idUm);
		return idUm;
	}
}
