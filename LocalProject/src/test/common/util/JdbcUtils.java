package test.common.util;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Object for the JDBC access.
 */
public class JdbcUtils {

    /**
     * Insert an element (even NULL) in a SQL request.
     * 
     * @param ps
     *            the PreparedStatement to fill.
     * @param index
     *            the index of the field to fill.
     * @param type
     *            the type ({@see Types}) of the element to insert.
     * @param toInsert
     *            the element to insert.
     */
    public static void setAttribute(PreparedStatement ps, int index, int type, Object toInsert) throws SQLException {
        if (toInsert == null) {
            ps.setNull(index, type);
        } else {
            ps.setObject(index, toInsert, type);
        }
    }
    
    public static String setAttribute(PreparedStatement ps, int index, int type, Object toInsert, String nomParam) throws SQLException {
        if ((toInsert == null) || toInsert.toString().equals("")) {
            ps.setNull(index, type);
            return nomParam + " = null,";
        } else {
            ps.setObject(index, toInsert, type);
            return nomParam + " = '" + toInsert + "',";
        }
    }

	public static Object get(ResultSet rs, String field) {
		return get(rs, field, null);
	}
	
	/**
	 * Get a field value from a ResultSet or return a default value
	 * @param rs ResultSet
	 * @param field String Field name
	 * @param defaultValue Default Object value
	 * @return Object value.
	 */
	public static Object get(ResultSet rs, String field, Object defaultValue) {
		try {
			return rs.getObject(field);
		}catch (Exception ignore) {
			return defaultValue;
		}
	}

	public static String getString(ResultSet rs, String field, String defaultValue) {
		String value = (String)get(rs, field, defaultValue);
		return value== null?defaultValue : value;
	}

}