/**
 * 
 */
package test.common.util;

import java.sql.ResultSet;
import java.sql.SQLException;

import test.common.util.dto.EnchereUmDTO;
import test.common.util.dto.LogIntegrationDTO;

/**
 * @author <a href="mailto:jerome.karmann-partner@arcelormittal.com?subject=TableMappingUtil.java" alt="e020378">Jerome KARMANN</a>
 *
 */
public class TableMappingUtil {

	public static final LogIntegrationDTO resultSet2LogIntegrationDTO(ResultSet input) throws SQLException{
		int i=0;
		LogIntegrationDTO output = new LogIntegrationDTO();
		output.setProperty(Constant.Table.LogIntegration.ID_LOG_INTEGRATION        	,input.getInt(++i));
		output.setProperty(Constant.Table.LogIntegration.SYSTEME_EMETTEUR          	,input.getString(++i));
		output.setProperty(Constant.Table.LogIntegration.NATURE_MESSAGE            	,input.getString(++i));
		output.setProperty(Constant.Table.LogIntegration.STATUT_INTEGRATION        	,input.getString(++i));
		output.setProperty(Constant.Table.LogIntegration.ID_TYPE_ERREUR            	,input.getString(++i));
		output.setProperty(Constant.Table.LogIntegration.DESIGNATION_ANOMALIE      	,input.getString(++i));
		output.setProperty(Constant.Table.Commons.CREATIONDT                		,input.getString(++i));
		output.setProperty(Constant.Table.Commons.MODIFDT                   		,input.getString(++i));
		output.setProperty(Constant.Table.LogIntegration.MESSAGE                   	,input.getString(++i));
		output.setProperty(Constant.Table.LogIntegration.ID_LOT                    	,input.getString(++i));
		output.setProperty(Constant.Table.LogIntegration.NB_TENTATIVES_REINTEG     	,input.getString(++i));
		output.setProperty(Constant.Table.LogIntegration.TAILLE_DOCUMENT           	,input.getString(++i));
		output.setProperty(Constant.Table.LogIntegration.SYSTEME_DESTINATAIRE      	,input.getString(++i));
		output.setProperty(Constant.Table.LogIntegration.DIFFUSABLE                	,input.getString(++i));
		output.setProperty(Constant.Table.LogIntegration.TYPE_ERREUR               	,input.getString(++i));
		output.setProperty(Constant.Table.LogIntegration.IDENTIFIANT_DOCUMENT      	,input.getString(++i));
		output.setProperty(Constant.Table.LogIntegration.DATE_ACTION_BACKOFFICE    	,input.getString(++i));
		output.setProperty(Constant.Table.LogIntegration.DATE_GENERATION_BACKOFFICE	,input.getString(++i));
		output.setProperty(Constant.Table.LogIntegration.REF_METIER_1              	,input.getString(++i));
		output.setProperty(Constant.Table.LogIntegration.REF_METIER_2              	,input.getString(++i));
		output.setProperty(Constant.Table.LogIntegration.REF_TECHNIQUE_1           	,input.getString(++i));
		output.setProperty(Constant.Table.LogIntegration.SOUS_TYPE                 	,input.getString(++i));
		output.setProperty(Constant.Table.LogIntegration.MESSAGERECEPTIONDATE      	,input.getString(++i));
		output.setProperty(Constant.Table.LogIntegration.TECHNICALREFERENCE2       	,input.getString(++i));
		return output;
	}
	public static final EnchereUmDTO resultSet2EnchereUmDTO(ResultSet input) throws SQLException{
		int i=0;
		EnchereUmDTO output = new EnchereUmDTO();
		output.setProperty(Constant.Table.Enchere_UM.ID_UM							, input.getInt(++i));
		output.setProperty(Constant.Table.Enchere_UM.OFFERID						, input.getInt(++i));
		output.setProperty(Constant.Table.Enchere_UM.CODE_USINE						, input.getString(++i));
		output.setProperty(Constant.Table.Enchere_UM.IDENTIFICATION_UM				, input.getString(++i));
		output.setProperty(Constant.Table.Enchere_UM.DATE_IDENTIFICATION			, input.getDate(++i));
		output.setProperty(Constant.Table.Enchere_UM.NUM_VERSION					, input.getInt(++i));
		output.setProperty(Constant.Table.Enchere_UM.ID_ENCHERE_LOT					, input.getInt(++i));
		output.setProperty(Constant.Table.Enchere_UM.REFERENCE_UM					, input.getString(++i));
		output.setProperty(Constant.Table.Enchere_UM.ID_SOURCE						, input.getString(++i));
		output.setProperty(Constant.Table.Enchere_UM.CODE_MATERIEL					, input.getString(++i));
		output.setProperty(Constant.Table.Enchere_UM.CODE_SEGMENTATION				, input.getString(++i));
		output.setProperty(Constant.Table.Enchere_UM.CODE_LCPS						, input.getString(++i));
		output.setProperty(Constant.Table.Enchere_UM.CODE_MEMBRE_GEST				, input.getString(++i));
		output.setProperty(Constant.Table.Enchere_UM.NOM_MEMBRE_GEST				, input.getString(++i));
		output.setProperty(Constant.Table.Enchere_UM.NOMBRE_UNITE					, input.getInt(++i));
		output.setProperty(Constant.Table.Enchere_UM.POIDS_BRUT						, input.getDouble(++i));
		output.setProperty(Constant.Table.Enchere_UM.UNITE_POIDS					, input.getString(++i));
		output.setProperty(Constant.Table.Enchere_UM.ATTRIBUEE						, input.getString(++i));
		output.setProperty(Constant.Table.Enchere_UM.NOM_REAFFECTEUR				, input.getString(++i));
		output.setProperty(Constant.Table.Enchere_UM.EMAIL_REAFFECTEUR				, input.getString(++i));
		output.setProperty(Constant.Table.Enchere_UM.STATUT_UM						, input.getString(++i));
		output.setProperty(Constant.Table.Enchere_UM.CATEGORIE						, input.getString(++i));
		output.setProperty(Constant.Table.Enchere_UM.SOUS_CATEGORIE					, input.getString(++i));
		output.setProperty(Constant.Table.Enchere_UM.CHOIX							, input.getString(++i));
		output.setProperty(Constant.Table.Enchere_UM.SYSTEME_EMETTEUR				, input.getString(++i));
		output.setProperty(Constant.Table.Enchere_UM.DATE_RECEPTION					, input.getDate(++i));
		output.setProperty(Constant.Table.Enchere_UM.RESPONSEID						, input.getString(++i));
		output.setProperty(Constant.Table.Commons.MODIFDT							, input.getDate(++i));
		output.setProperty(Constant.Table.Commons.CREATIONDT						, input.getDate(++i));
		output.setProperty(Constant.Table.Commons.EVOL_TMSTP						, input.getObject(++i));
		output.setProperty(Constant.Table.Enchere_UM.POIDS_BRUT_TON					, input.getDouble(++i));
		output.setProperty(Constant.Table.Enchere_UM.DATE_CANCEL					, input.getDate(++i));
		output.setProperty(Constant.Table.Enchere_UM.DATE_PROPOSITION				, input.getDate(++i));
		output.setProperty(Constant.Table.Enchere_UM.NB_PROPOSITION					, input.getInt(++i));
		output.setProperty(Constant.Table.Enchere_UM.REFERENCE_COMMANDE_FOURNISSEUR	, input.getString(++i));
		output.setProperty(Constant.Table.Enchere_UM.REFERENCE_RECLAMATION			, input.getString(++i));
		output.setProperty(Constant.Table.Enchere_UM.VILLE							, input.getString(++i));
		output.setProperty(Constant.Table.Enchere_UM.LOCALISATION_UM				, input.getString(++i));
		output.setProperty(Constant.Table.Enchere_UM.AGE							, input.getInt(++i));
		output.setProperty(Constant.Table.Enchere_UM.TIME_OFFERED					, input.getInt(++i));
		output.setProperty(Constant.Table.Enchere_UM.STACK							, input.getString(++i));
		output.setProperty(Constant.Table.Enchere_UM.BUNDLESOURCE					, input.getString(++i));
		output.setProperty(Constant.Table.Enchere_UM.SUPPLIERGROUPID				, input.getInt(++i));
		output.setProperty(Constant.Table.Enchere_UM.LOCALMATERIALCODE				, input.getString(++i));
		output.setProperty(Constant.Table.Enchere_UM.STOCKLOCATION					, input.getString(++i));
		output.setProperty(Constant.Table.Enchere_UM.FCASALESONLY					, input.getString(++i));
		output.setProperty(Constant.Table.Enchere_UM.ORIGINALCUSTOMERID				, input.getInt(++i));
		output.setProperty(Constant.Table.Enchere_UM.ORIGINALCUSTOMERDESC			, input.getString(++i));
		output.setProperty(Constant.Table.Enchere_UM.POIDS_NET						, input.getDouble(++i));
		output.setProperty(Constant.Table.Enchere_UM.UNITE_POIDS_NET				, input.getString(++i));
		return output;
	}
}
