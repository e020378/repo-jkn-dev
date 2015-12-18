package test.common.util;

import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.List;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.util.CellRangeAddress;

import test.common.util.dto.EnchereCaractUmDTO;

/**
 * @author <a href="mailto:jerome.karmann-partner@arcelormittal.com?subject=ExcelUtil.java" alt="e020378">Jerome KARMANN</a>
 */
public final class ExcelUtil {

	public static final String PATTERN_ENCHERE_UM_PROPERTY_NULL= "MaterialReferenceNumber=[{3}] with property [{0}.{1}]\t\t\tnull for ID_UM={2}";

	private ExcelUtil(){}

	public static void createRowHeader1(HSSFSheet sheet, String[] cellValues, int rowIndex){
		HSSFRow row = sheet.createRow(rowIndex);
		for(int i=0; i<cellValues.length; i++){
			HSSFCell cell =  createStringCell(row, i, cellValues[i]);
			cell.setCellStyle(createCellStyleCenter(sheet.getWorkbook()));
		}
		mergeCell(sheet, rowIndex, rowIndex, 1, 2);
		mergeCell(sheet, rowIndex, rowIndex, 3, 4);
		mergeCell(sheet, rowIndex, rowIndex, 5, 6);
		mergeCell(sheet, rowIndex, rowIndex, 7, 8);
	}

	public static void createRowHeader2(HSSFSheet sheet, String[] cellValues, int rowIndex){
		HSSFRow row = sheet.createRow(rowIndex);
		for(int i=0; i<cellValues.length; i++){
			HSSFCell cell =  createStringCell(row, i, cellValues[i]);
			cell.setCellStyle(createCellStyleCenter(sheet.getWorkbook()));
		}
	}

	public static final void mergeCell(final HSSFSheet sheet, int rowStart, int rowEnd, int colStart, int colEnd){
		sheet.addMergedRegion(new CellRangeAddress(rowStart, rowEnd, colStart, colEnd));
	}

	public static CellStyle createCellStyleCenter(HSSFWorkbook wb){
		CellStyle result = wb.createCellStyle();
		result.setAlignment(CellStyle.ALIGN_CENTER);
		return result;
	}

	public static HSSFCell createStringCell(final HSSFRow row, int columnIndex, String value){
		HSSFCell cell = row.createCell(columnIndex);
		if(StringHelper.isEmpty(value)){
			cell.setCellType(HSSFCell.CELL_TYPE_BLANK);
		}else{
			cell.setCellType(HSSFCell.CELL_TYPE_STRING);
			cell.setCellValue(value);
		}
		return cell;
	}

	public static void createRowData(HSSFSheet sheet, String[] cellValues, int rowIndex){
		HSSFRow row = sheet.createRow(rowIndex);
		for(int i=0; i<cellValues.length; i++){
			createStringCell(row, i, cellValues[i]);
		}
	}

	public static List<EnchereCaractUmDTO> hssfRow2EnchereCaractUm(Row row,
			String caracName1, int colText1, int colVal1,
			String caracName2, int colText2, int colVal2,
			String caracName3, int colText3, int colVal3,
			int columnIdUm, int columnIdentificationUm, int columnReferenceUm)
	{
		List<EnchereCaractUmDTO> result = new ArrayList<EnchereCaractUmDTO>();
		addEntry(result, hssfRow2EnchereCaractUm(row, caracName1, colText1, colVal1, columnIdUm, columnIdentificationUm, columnReferenceUm));
		addEntry(result, hssfRow2EnchereCaractUm(row, caracName2, colText2, colVal2, columnIdUm, columnIdentificationUm, columnReferenceUm));
		addEntry(result, hssfRow2EnchereCaractUm(row, caracName3, colText3, colVal3, columnIdUm, columnIdentificationUm, columnReferenceUm));
		return result;
	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	public static void addEntry(final List source, Object o){
		if(o!=null){
			source.add(o);
		}
	}

	public static EnchereCaractUmDTO hssfRow2EnchereCaractUm(Row row, String charactName, int columnTextValue, int columnCodeValue,
			int columnIdUm, int columnIdentificationUm, int columnReferenceUm)
	{
		String idUm = row.getCell(columnIdUm).getStringCellValue();
		String identificationUm = row.getCell(columnIdentificationUm).getStringCellValue();
		String referenceUm = row.getCell(columnReferenceUm).getStringCellValue();
		String property = null;
		if(idUm!=null){
			if(!StringHelper.isEmpty(identificationUm)){
				if(!StringHelper.isEmpty(referenceUm)){
					String textValue = row.getCell(columnTextValue)==null?null: row.getCell(columnTextValue).getStringCellValue();
					String codeValue = row.getCell(columnCodeValue)==null?null:row.getCell(columnCodeValue).getStringCellValue();
					if(!StringHelper.isEmpty(textValue)){
						if(!StringHelper.isEmpty(codeValue)){
							EnchereCaractUmDTO dto = new EnchereCaractUmDTO();
							dto.setId(new Integer(idUm));
							dto.setProperty(Constant.Table.Enchere_UM.IDENTIFICATION_UM, identificationUm);
							dto.setProperty(Constant.Table.Enchere_UM.REFERENCE_UM, referenceUm);
							dto.setCodeValue(codeValue);
							dto.setTextValue(textValue);
							dto.setName(charactName);
							return dto;
						}else{
							property = Constant.Table.Enchere_CaractUM.CODE_VALUE;
						}
					}else{
						property = Constant.Table.Enchere_CaractUM.TEXTVALUE;
					}
				}else{
					property = Constant.Table.Enchere_UM.REFERENCE_UM;
				}
			}else{
				property = Constant.Table.Enchere_UM.IDENTIFICATION_UM;
			}
			System.err.println(MessageFormat.format(PATTERN_ENCHERE_UM_PROPERTY_NULL, charactName, property, idUm, referenceUm));
		}
		System.exit(-1);
		return null;
	}
}