/**
 * 
 */
package test.common.util.dto;

/**
 * @author <a href="mailto:jerome.karmann-partner@arcelormittal.com?subject=SqlParameterDTO.java" alt="e020378">Jerome KARMANN</a>
 *
 */
public class SqlParameterDTO extends BaseModelDTO {

	private static final long serialVersionUID = 7642063312637932796L;

	public static final String SQL_PARMETER_TYPE = "sqlParamterType";
	public static final String SQL_PARAMETER_INDEX= "sqlParamterIndex";
	public static final String SQL_PARAMETER_VALUE= "sqlParamterValue";
	public static final String SQL_PARAMETER_NAME= "sqlParamterName";

	public void setParameterType(int sqlType){
		setProperty(SQL_PARMETER_TYPE, sqlType);
	}
	public int getParameterType(){
		return (Integer)getProperty(SQL_PARMETER_TYPE);
	}

	public void setParameterIndex(int sqlType){
		setProperty(SQL_PARAMETER_INDEX, sqlType);
	}
	public int getParameterIndex(){
		return (Integer)getProperty(SQL_PARAMETER_INDEX);
	}

	public void setParameterValue(Object value){
		setProperty(SQL_PARAMETER_VALUE, value);
	}
	public Object getParameterValue(){
		return getProperty(SQL_PARAMETER_VALUE);
	}

	public void setParameterName(Object value){
		setProperty(SQL_PARAMETER_NAME, value);
	}
	public String getParameterName(){
		return getPropertyString(SQL_PARAMETER_NAME);
	}

}
