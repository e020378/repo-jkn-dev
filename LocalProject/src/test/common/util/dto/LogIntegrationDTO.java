package test.common.util.dto;

import test.common.util.Constant;

/**
 * @author <a href="mailto:jerome.karmann-partner@arcelormittal.com?subject=LogIntegrationDTO.java" alt="e020378">Jerome KARMANN</a>
 *
 */
public class LogIntegrationDTO extends TableModelDTO{

	private static final long serialVersionUID = -963986448181285831L;

	public Integer getId(){
		return (Integer)getProperty(Constant.Table.LogIntegration.ID_LOG_INTEGRATION);
	}
	public void setId(Integer value){
		setProperty(Constant.Table.LogIntegration.ID_LOG_INTEGRATION, value);
	}
	public String getMessage(){
		return getPropertyString(Constant.Table.LogIntegration.MESSAGE);
	}
	public void setMessage(String value){
		setProperty(Constant.Table.LogIntegration.MESSAGE, value);
	}
	public String getNatureMessage(){
		return getPropertyString(Constant.Table.LogIntegration.NATURE_MESSAGE);
	}
	public void setNatureMessage(String value){
		setProperty(Constant.Table.LogIntegration.NATURE_MESSAGE, value);
	}
}
