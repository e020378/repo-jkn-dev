package test.common.util.dto;

import test.common.util.Constant;

/**
 * @author <a href="mailto:jerome.karmann-partner@arcelormittal.com?subject=EnchereCaractUmDTO.java" alt="e020378">Jerome KARMANN</a>
 */
public class EnchereCaractUmDTO extends TableModelDTO{

	private static final long serialVersionUID = -963986448181285831L;

	public String getName(){
		return getPropertyString(Constant.Table.Enchere_CaractUM.NAME);
	}
	public void setName(String value){
		setProperty(Constant.Table.Enchere_CaractUM.NAME, value);
	}
	public Integer getIdUm(){
		return (Integer)getProperty(Constant.Table.Enchere_CaractUM.ID_UM);
	}
	public void setIdUm(Integer value){
		setProperty(Constant.Table.Enchere_CaractUM.ID_UM, value);
	}
	public String getDisplayType(){
		return getPropertyString(Constant.Table.Enchere_CaractUM.DISPLAYTYPE);
	}
	public void setDisplayType(String value){
		setProperty(Constant.Table.Enchere_CaractUM.DISPLAYTYPE, value);
	}
	public String getTextValue(){
		return getPropertyString(Constant.Table.Enchere_CaractUM.TEXTVALUE);
	}
	public void setTextValue(String value){
		setProperty(Constant.Table.Enchere_CaractUM.TEXTVALUE, value);
	}
	public String getCodeValue(){
		return getPropertyString(Constant.Table.Enchere_CaractUM.CODE_VALUE);
	}
	public void setCodeValue(String value){
		setProperty(Constant.Table.Enchere_CaractUM.CODE_VALUE, value);
	}
}
