package test.common.util.dto;

import java.sql.Date;

import test.common.util.Constant;

/**
 * @author <a href="mailto:jerome.karmann-partner@arcelormittal.com?subject=TableModelDTO.java" alt="e020378">Jerome KARMANN</a>
 */
public class TableModelDTO extends BaseModelDTO {

	private static final long serialVersionUID = 3057926544184008667L;

	public Integer getId(){
		return (Integer)getProperty(Constant.Table.Commons.ID);
	}
	public void setId(Integer value){
		setProperty(Constant.Table.Commons.ID, value);
	}

	public Date getCreationDt(){
		return (Date)getProperty(Constant.Table.Commons.CREATIONDT);
	}
	public void setCreationDt(Date value){
		setProperty(Constant.Table.Commons.CREATIONDT, value);
	}

	public Date getModifDt(){
		return (Date)getProperty(Constant.Table.Commons.MODIFDT);
	}
	public void setModifDt(Date value){
		setProperty(Constant.Table.Commons.MODIFDT, value);
	}
}
