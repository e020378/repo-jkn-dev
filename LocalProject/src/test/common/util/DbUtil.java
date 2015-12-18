package test.common.util;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.MessageFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Properties;

import test.common.util.dto.SqlParameterDTO;

/**
 * @author <a href="mailto:jerome.karmann-partner@arcelormittal.com?subject=DbUtil.java" alt="e020378">Jerome KARMANN</a>
 */
public final class DbUtil {

	public static Connection createConnectionDevMaster(){
		return createConnectionGeneric(Constant.DB_URL_SQLSERVER, null, Constant.DB_ARGS_DEV_MASTER);
	}

	public static Connection createConnectionAcceptance(){
		return createConnectionGeneric(Constant.DB_URL_SQLSERVER, null, Constant.DB_ARGS_ACCEPTANCE);
	}

	public static Connection createConnectionProduction(){
		return createConnectionGeneric(Constant.DB_URL_SQLSERVER, null, Constant.DB_ARGS_PRODUCTION);
	}

	public static Connection createConnectionGeneric(String dbUrl,Properties p, Object[] args){
		try {
			return DriverManager.getConnection(createURL(dbUrl, args, p));
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	public static String createURL(String url, Object[] args, Properties p){
		return MessageFormat.format(url, args);
	}

	public static boolean checkConnection(Connection connection, String testSelectQuery) throws SQLException{
		Statement statement = connection.createStatement();
		ResultSet resultSet = statement.executeQuery(testSelectQuery);
		boolean ok = resultSet.next();
		resultSet.close();
		statement.close();
		return ok;
	}
	public static void closeConnection(Connection connection) throws SQLException{
		connection.close();
		System.out.println("Connection closed");
	}

	public static List<List<Object>> executeQuery(Connection connection, String query) throws SQLException{
		List<List<Object>> output= null;
		Statement statement = connection.createStatement();
		ResultSet resultSet = statement.executeQuery(query);
		if(resultSet!=null){
			output = resultSet2List(resultSet);
			resultSet.close();
			statement.close();
		}
		return output;
	}

	public static ResultSet executePrepareStatement(Connection connection, String statementCall, SqlParameterDTO... sqlParameters) throws SQLException{
		CallableStatement cs = connection.prepareCall(statementCall);
		for(SqlParameterDTO p: sqlParameters){
			JdbcUtils.setAttribute(cs, p.getParameterIndex(), p.getParameterType(), p.getParameterValue(), p.getParameterName());
		}
		return cs.executeQuery();
	}

	public static ResultSet executeQueryResultSet(Connection connection, String query) throws SQLException{
		return connection.createStatement().executeQuery(query);
	}

	public static List<List<Object>> resultSet2List(ResultSet resultSet) throws SQLException{
		return resultSet2List(resultSet, false);
	}

	public static List<List<Object>> resultSet2List(ResultSet resultSet, boolean close) throws SQLException{
		List<List<Object>> output= null;
		if(resultSet!=null){
			output = new ArrayList<List<Object>>();
			ResultSetMetaData resultSetMetaData = resultSet.getMetaData();
			int count = resultSetMetaData.getColumnCount();
			
			while(resultSet.next()){
				List<Object> row = new ArrayList<Object>();
				for(int i=1; i<=count; i++){
					row.add(resultSet.getObject(i));
				}
				output.add(row);
			}
			if(close){
				resultSet.getStatement().close();
				resultSet.close();
			}
		}
		return output;
	}

//	exec [AUC_LEC_METALUNIT_CHARAC_DETAILS_BY_ID] @mid, 'FR', 0
	public static List<List<Object>> executeProcedureRead(Connection connection, String storedProcedureName, Object[] args) throws SQLException{
		String callPattern = "EXEC {0} {1}";
		String argsValue = "";
		for(Object o: args){
			argsValue += ", "+o;
		}
		if(argsValue.length()>0)
			argsValue = argsValue.substring(1);
		Statement statement = connection.createStatement();
		String query = MessageFormat.format(callPattern, storedProcedureName, argsValue);
		System.out.println(query);
		return resultSet2List(statement.executeQuery(query), true);
	}

	public static boolean changeDatabase(Connection connection, String databaseName) throws SQLException{
		String query = "use "+databaseName;
		Statement statement = connection.createStatement();
		boolean ok =statement.execute(query);
		statement.close();
		return ok;
	}

	public static void printDebug(List<List<Object> > result){
		for(List<Object> row: result){
			for(Object col: row)
				System.out.print(col + "\t");
			System.out.println("");
		}
	}

	public static String getDate(){
		return new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS").format(new Date());
	}

	public static SqlParameterDTO createSqlParameter(int position, int sqlType, Object value, String parameterName){
		SqlParameterDTO param = new SqlParameterDTO();
		param.setParameterIndex(position);
		param.setParameterType(sqlType);
		param.setParameterValue(value);
		param.setParameterName(parameterName);
		return param;
	}
}