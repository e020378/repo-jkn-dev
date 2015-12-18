package test.auction.um.sql;

import static test.common.util.StringHelper.quote;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.MessageFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Row;
import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import test.common.util.DbUtil;
import test.common.util.ExcelUtil;
import test.common.util.FileUtil;
import test.common.util.TableMappingUtil;
import test.common.util.dto.EnchereCaractUmDTO;
import test.common.util.dto.EnchereUmDTO;

/**
 * @author <a href="mailto:jerome.karmann-partner@arcelormittal.com?subject=EnchereCaractUmTest.java" alt="e020378">Jerome KARMANN</a>
 */
public class EnchereCaractUmTestCase {

	public static File FILE_SQL_EXPORT		= new File("L:/pro/workflow/15.4.0/10327/migration/ADM_Migration_v15.4_Auction_Enchere_CaractUM.PRC");
	public static File FILE_SQL_EXPORT_TEMP	= new File("L:/pro/workflow/15.4.0/10327/migration/ADM_Migration_v15.4_Auction_Enchere_CaractUM_TEMP.PRC");
	public static File FILE_EXCEL_EXPORT;//	= new File("L:/pro/workflow/15.4.0/10327/migration/exportEnchereCaractUm.xls");
	public static File FILE_BIN_EXPORT		= new File("L:/pro/workflow/15.4.0/10327/migration/exportEnchereCaractUm.bin");
	public static final String XLS_SHEETNAME ="caractByUmId";
	private static int ROW_COUNT = -1;

	public static List<EnchereCaractUmDTO> ENCHERE_UM_DATA_XLS;
	public static List<EnchereUmDTO> ENCHERE_UM_DATA_DB;

	public static final String NPSTEELGRADESTANDARD	= "NPSteelGradeStandard";
	public static final String QUACOM_NUANCE		= "Quacom_Nuance";
	public static final String PRIVATETRADEMARKGRADE1="PrivateTradeMarkGrade1";


	public static final String patternUpdateEnchereCaractUm	= "IF EXISTS(SELECT * FROM enchere_CaractUM WITH(nolock) WHERE ID_UM={1} AND name={0})\n\t"
																+ "UPDATE enchere_CaractUM SET textValue={3}, Code_Value={4}, modifDt={5} WHERE ID_UM={1} AND name={0}\n"
																+ "ELSE\n\t"
																+ "INSERT INTO enchere_CaractUM (Name, ID_UM, displayType, TextValue, Code_Value, creationDt, modifDt) VALUES ({0}, {1}, {2}, {3}, {4}, {5}, {5})\n";

	public static final String DISPLAY_TYPE_TEXT = quote("TEXT");
	public static String QUERY_UM_CARAC_NOT_SOLD;
	public static String QUERY_UM_ENCHERE_CARAC_UM_NOT_SOLD;

	public static final int COL_ID_UM = 0;
	public static final int COL_IDENTIFICATION_UM = 1;
	public static final int COL_REFERENCE_UM = 2;
	public static final int COL_NPSTEELGRADESTANDARD_TEXT_VALUE = 3;
	public static final int COL_NPSTEELGRADESTANDARD_CODE_VALUE = 4;
	public static final int COL_NONPRIMESTEELGRADE_TEXT_VALUE = 5;
	public static final int COL_NONPRIMESTEELGRADE_CODE_VALUE = 6;
	public static final int COL_PRIVATETRADEMARKGRADE1_TEXT_VALUE = 7;
	public static final int COL_PRIVATETRADEMARKGRADE1_CODE_VALUE = 8;

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		String timestamp = new SimpleDateFormat("yyyyMMdd").format(new Date());
		String fileName = MessageFormat.format("L:/pro/workflow/15.4.0/10327/migration/exportEnchereCaractUm{0}{1}.xls", "~", timestamp);
		FILE_EXCEL_EXPORT= new File(fileName);
		QUERY_UM_CARAC_NOT_SOLD		= FileUtil.readFile(new File("L:/pro/workflow/15.4.0/10327/migration/queryEnchereUmNotSold.txt"));
		QUERY_UM_ENCHERE_CARAC_UM_NOT_SOLD = FileUtil.readFile(new File("L:/pro/workflow/15.4.0/10327/migration/queryEnchereCaractUm.txt"));
	}

	@Before
	public void setUp() throws Exception {
		ENCHERE_UM_DATA_DB	= dbSelectEnchereUmNotSold();
	}

	@Test
	public void testExportEnchereCaractUm() throws IOException{
        HSSFWorkbook wb = new HSSFWorkbook();
        FileOutputStream fileOut = new FileOutputStream(FILE_EXCEL_EXPORT);
        HSSFSheet sheet = wb.createSheet(XLS_SHEETNAME);
//        createRowHeader1(sheet, new String[]{
//        		"SteelUser","Identification_UM","Reference_UM",
//        		"NPSteelGradeStandard","", 
//        		"QUACOM_NUANCE","",
//        		"PrivateTradeMarkGrade1","",
//        });
        ExcelUtil.createRowHeader1(sheet, new String[]{
        		"Comet", "Material", "Material",
        		"NonPrimeSteelGradeStandard","",	//NPSteelGradeStandard
        		"NonPrimeSteelGrade","",			//QUACOM_NUANCE
        		"SteelGrade","",					//PrivateTradeMarkGrade1 
        }, ++ROW_COUNT);
        ExcelUtil.createRowHeader2(sheet, new String[]{
        		"Id_UM", "MaterialIdentifier", "MaterialReferenceNumber",			//Identification_UM, Reference_UM
        		"NonPrimeSteelGradeStandardName","NonPrimeSteelGradeStandardCode",	//TextValue, Code_Value
        		"NonPrimeSteelGradeName","NonPrimeSteelGradeCode",					//TextValue, Code_Value
        		"SteelGradeName","SteelGradeCode"									//TextValue, Code_Value
        }, ++ROW_COUNT);
        for(EnchereUmDTO um:  ENCHERE_UM_DATA_DB){
        	List<String> data = new ArrayList<String>();
        	String[] enchereUmData = {String.valueOf(um.getId()),um.getIdentificationUm(),um.getReferenceUm()};
        	data.addAll(Arrays.asList(enchereUmData));
        	String[] mockCaractData= mockCaractData();
        	data.addAll(Arrays.asList(mockCaractData));
        	ExcelUtil.createRowData(sheet, data.toArray(new String[data.size()]), ++ROW_COUNT);
        }
        wb.write(fileOut);
        fileOut.close();
	}
	@Test
	public void testImportEnchereCaractUm() throws IOException{
		ENCHERE_UM_DATA_XLS	= doImportEnchereCaractUm();
	}

//	@Test
	public void testSerialize() throws IOException, Exception {
		List<EnchereUmDTO> l = dbSelectEnchereUmNotSoldCaract();
		serialize(l, FILE_BIN_EXPORT);
	}

//	@Test
//	public void testExportEnchereCaractUmBeforeUpdate() throws IOException{
//        for(EnchereUmDTO um:  ENCHERE_UM_DATA_DB){
//        	List<String> data = new ArrayList<String>();
//        	String[] enchereUmData = {String.valueOf(um.getId()),um.getIdentificationUm(),um.getReferenceUm()};
//        	data.addAll(Arrays.asList(enchereUmData));
//        	String[] mockCaractData= mockCaractData();
//        	data.addAll(Arrays.asList(mockCaractData));
//        	createRowData(sheet, data.toArray(new String[data.size()]));
//        }
//        wb.write(fileOut);
//        fileOut.close();
//	}

//	@Test
	public void testUnserialize() throws IOException, Exception {
		List<EnchereUmDTO> listAfter = unserialize(FILE_BIN_EXPORT);
		Assert.assertTrue("Wrong size after serialization", listAfter.size()>0);
	}

	public static  List<EnchereCaractUmDTO> doImportEnchereCaractUm() throws IOException {
		List<EnchereCaractUmDTO> output = new ArrayList<EnchereCaractUmDTO>();
        FileInputStream inputStream = new FileInputStream(FILE_EXCEL_EXPORT);
        HSSFWorkbook workbook = new HSSFWorkbook(inputStream);
        HSSFSheet firstSheet = workbook.getSheet(XLS_SHEETNAME);
        Iterator<Row> it = firstSheet.iterator();
        int i = 0;
        while (it.hasNext()) {
        	Row r = it.next();
        	if(i>=2){
	        	List<EnchereCaractUmDTO> enchereCaractUm = hssfRow2EnchereCaractUm(r);
	        	output.addAll(enchereCaractUm);
        	}
        	i++;
        }
		return output;
	}

	public static String[] mockCaractData() {
		String  pattern = "{0}_{1}";
		String rowValue = String.valueOf(ROW_COUNT);
		return new String[]{
				MessageFormat.format(pattern, "NPSteelGradeStd.text", rowValue),
				MessageFormat.format(pattern, "NPSteelGradeStd.code", rowValue),
				MessageFormat.format(pattern, "QuaCom.text", rowValue),
				MessageFormat.format(pattern, "QuaCom.code", rowValue),
				MessageFormat.format(pattern, "PrivateTradeMarkGrade1.text", rowValue),
				MessageFormat.format(pattern, "PrivateTradeMarkGrade1.code", rowValue),
		};
	}

	@Test
	public void testExportSqlEnchereCaractUm() throws IOException {
		StringBuffer query = new StringBuffer(0);
		String currentDate	= quote(DbUtil.getDate());
		for(EnchereCaractUmDTO um: ENCHERE_UM_DATA_XLS){
			Integer idUm 		= um.getId();
			String textValue	= quote(um.getTextValue());
			String codeValue	= quote(um.getCodeValue());
			String name			= quote(um.getName());
//			Name, ID_UM, displayType, TextValue, Code_Value, creationDt
			Object[] data		= {name, String.valueOf(idUm), DISPLAY_TYPE_TEXT, textValue, codeValue, currentDate};
			query.append(MessageFormat.format(patternUpdateEnchereCaractUm, data));
		}
		writeFile(FILE_SQL_EXPORT, query.toString());
		System.out.println("@see File: "+FILE_SQL_EXPORT.getCanonicalPath());
//		String select = "SELECT * FROM enchere_CaractUM WITH(nolock) WHERE modifdt>="+currentDate+" ORDER BY ID_UM desc, NAME\n";
		exportSqlTempIfExists(query.toString());
	}

	public static void exportSqlTempIfExists(String input) throws IOException{
		String selectIfExistPattern = "IF EXISTS\\((.*)\\)";
		Matcher m = Pattern.compile(selectIfExistPattern).matcher(input);
		StringBuffer result = new StringBuffer(0);
		while(m.find()){
			result.append("UNION ").append(m.group(1)).append("\n");
		}
		String output = "BEGIN TRAN\n{1}\n{0}\n{1}\nROLLBACK";
		String p1 = result.toString().replaceFirst("UNION ", "")+" ORDER BY ID_UM desc, NAME";
		writeFile(FILE_SQL_EXPORT_TEMP, MessageFormat.format(output, input, p1));
		System.out.println("@see File: "+FILE_SQL_EXPORT_TEMP.getCanonicalPath());
	}

	public static void writeFile(File f, String content) throws IOException{
		FileWriter fw = new FileWriter(f);
		fw.write(content);
		fw.close();
	}

	public static List<EnchereUmDTO> dbSelectEnchereUmNotSold() throws SQLException{
		List<EnchereUmDTO> output = new ArrayList<EnchereUmDTO>();
		ResultSet resultSet = DbUtil.executeQueryResultSet(DbUtil.createConnectionAcceptance(), QUERY_UM_CARAC_NOT_SOLD);
		while(resultSet.next()){
			output.add(TableMappingUtil.resultSet2EnchereUmDTO(resultSet));
		}
		return output;
	}

	public static List<EnchereCaractUmDTO> hssfRow2EnchereCaractUm(Row row){
		return ExcelUtil.hssfRow2EnchereCaractUm(row,
				NPSTEELGRADESTANDARD, COL_NPSTEELGRADESTANDARD_TEXT_VALUE, COL_NPSTEELGRADESTANDARD_CODE_VALUE,
				QUACOM_NUANCE, COL_NONPRIMESTEELGRADE_TEXT_VALUE, COL_NONPRIMESTEELGRADE_CODE_VALUE,
				PRIVATETRADEMARKGRADE1, COL_PRIVATETRADEMARKGRADE1_TEXT_VALUE, COL_PRIVATETRADEMARKGRADE1_CODE_VALUE,
				COL_ID_UM, COL_IDENTIFICATION_UM, COL_REFERENCE_UM
		);

	}

	public static List<EnchereUmDTO> dbSelectEnchereUmNotSoldCaract() throws SQLException{
		List<EnchereUmDTO> output = new ArrayList<EnchereUmDTO>();
		ResultSet resultSet = DbUtil.executeQueryResultSet(DbUtil.createConnectionAcceptance(), QUERY_UM_ENCHERE_CARAC_UM_NOT_SOLD);
		while(resultSet.next()){
			output.add(TableMappingUtil.resultSet2EnchereUmDTO(resultSet));
		}
		return output;
	}

	public static void serialize(Object o, File output) throws IOException{
	    FileOutputStream fout = new FileOutputStream(output, true);
	    ObjectOutputStream oos= new ObjectOutputStream(fout);
	    oos.writeObject(o);
	    oos.close();
	}

	public static List<EnchereUmDTO> unserialize(File intput) throws IOException, ClassNotFoundException{
	    FileInputStream fin = new FileInputStream(intput );
	    ObjectInputStream oos= new ObjectInputStream(fin);
	    List<EnchereUmDTO> o= (List<EnchereUmDTO>)oos.readObject();
	    oos.close();
	    return o;
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
		List<EnchereUmDTO> listBefore = dbSelectEnchereUmNotSoldCaract();
		int expectedSize = listBefore.size();
		serialize(listBefore, FILE_BIN_EXPORT);
		List<EnchereUmDTO> listAfter = unserialize(FILE_BIN_EXPORT);
		int finalSize = listAfter.size();
		Assert.assertEquals("Wrong size after serialization", expectedSize, finalSize);
	}
}
