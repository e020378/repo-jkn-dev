package test.common.util.dto;

import test.common.util.Constant;

/**
 * @author <a href="mailto:jerome.karmann-partner@arcelormittal.com?subject=EnchereUmDTO.java" alt="e020378">Jerome KARMANN</a>
 */
public class EnchereUmDTO extends TableModelDTO{

	private static final long serialVersionUID = 7789633932816078860L;

	public Integer getId(){
		return (Integer)getProperty(Constant.Table.Enchere_UM.ID_UM);
	}
	public void setId(Integer value){
		setProperty(Constant.Table.Enchere_UM.ID_UM, value);
	}
	public Integer getOfferId(){
		return (Integer)getProperty(Constant.Table.Enchere_UM.OFFERID);
	}
	public void setOfferId(Integer value){
		setProperty(Constant.Table.Enchere_UM.OFFERID, value);
	}
	public String getReferenceUm(){
		return getPropertyString(Constant.Table.Enchere_UM.REFERENCE_UM);
	}
	public void setReferenceUm(String value){
		setProperty(Constant.Table.Enchere_UM.REFERENCE_UM, value);
	}
	public String getIdentificationUm(){
		return getPropertyString(Constant.Table.Enchere_UM.IDENTIFICATION_UM);
	}
	public void setIdentificationUm(String value){
		setProperty(Constant.Table.Enchere_UM.IDENTIFICATION_UM, value);
	}
	public String getCodeUsine(){
		return getPropertyString(Constant.Table.Enchere_UM.CODE_USINE);
	}
	public void setCodeUsine(String value){
		setProperty(Constant.Table.Enchere_UM.CODE_USINE, value);
	}
	public String getAttribuee(){
		return getPropertyString(Constant.Table.Enchere_UM.ATTRIBUEE);
	}
	public void setAttribuee(String value){
		setProperty(Constant.Table.Enchere_UM.ATTRIBUEE, value);
	}
}
