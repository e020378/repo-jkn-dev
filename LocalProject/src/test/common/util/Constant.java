package test.common.util;

/**
 * @author <a href="mailto:jerome.karmann-partner@arcelormittal.com?subject=Constant.java" alt="e020378">Jerome KARMANN</a>
 *
 */
public interface Constant {

	String JDBC_DRIVER_MSSQL = "com.microsoft.sqlserver.jdbc.SQLServerDriver";
//	
	/**
	 * Parameters: ServerName, DBName, UserName, Password
	 * synopsis: jdbc:sqlserver://[serverName[\instanceName][:portNumber]][;property=value[;property=value]]
	 */
	String DB_URL_SQLSERVER = "jdbc:sqlserver://{0};database={1};user={2};password={3}";

	String 	SERVER_NAME_DEV_MASTER	= "SQL_STEELUSER_DEV\\SqlMaster";
	String 	SERVER_NAME_PRODUCTION	= "SQL_STEELUSER";
	String 	SERVER_NAME_ACCEPTANCE	= "SQL_STEELUSER_INT";
	String	SERVER_NAME_ACCEPTANCE_HOTFIX = "SQL_STEELUSER_HOTFIX\\sqlhotfix";
	String 	DATABASE_NAME			= "steeluser";

	String 	USER_NAME_ADMIN			= "Steel_Admin";
	String 	USER_NAME_READER		= "Steel_Reader";

	String 	USER_PASSWORD_ADMIN		= "Steel_Admin_p@ssw0r3";
	String 	USER_PASSWORD_READER_DEV= "Steel_Reader_p@ssw0r2";
	String 	USER_PASSWORD_READER_ACC= "STU_Reader_pwd717";
	String	USER_PASSWORD_READER_ACC_HTX = "STU_Reader_pwd717";
	String 	USER_PASSWORD_READER_PROD= "Steel_Reader_p@sswd105";

	Object[] DB_ARGS_DEV_MASTER =		{Constant.SERVER_NAME_DEV_MASTER, Constant.DATABASE_NAME, Constant.USER_NAME_READER, Constant.USER_PASSWORD_READER_DEV};
	Object[] DB_ARGS_PRODUCTION = 		{Constant.SERVER_NAME_PRODUCTION, Constant.DATABASE_NAME, Constant.USER_NAME_READER, Constant.USER_PASSWORD_READER_PROD};
	Object[] DB_ARGS_ACCEPTANCE = 		{Constant.SERVER_NAME_ACCEPTANCE, Constant.DATABASE_NAME, Constant.USER_NAME_READER, Constant.USER_PASSWORD_READER_ACC};


	Object[] DB_ARGS_ACCEPTANCE_HOTFIX ={Constant.SERVER_NAME_ACCEPTANCE_HOTFIX, Constant.DATABASE_NAME, Constant.USER_NAME_READER, Constant.USER_PASSWORD_READER_ACC_HTX};

	final String TEST_QUERY_SYS		= "SELECT DATEADD(ms,-sample_ms,GETDATE() ) 'BOL' FROM sys.dm_io_virtual_file_stats(1,1)";
//										"SELECT create_date 'tempdb' FROM sys.databases WHERE name = 'tempdb'";
//										"SELECT login_time 'session' FROM sys.dm_exec_sessions WHERE session_id =1";


	public static final class Query{
		public static String UM_MAX_ID_FROM_VALEUR_CARACTERISTIQUE ="select max(v.id_um) FROM Valeur_Caracteristique v with(nolock)";
	}
	public static final class Table{
		public static final class Commons{
			public static String ID       		 = "ID";
			public static String CREATIONDT        = "creationDt";
			public static String MODIFDT           = "modifDt";
			public static String EVOL_TMSTP        = "EVOL_TMSTP";
		}
		public static final class LogIntegration{
			public static String ID_LOG_INTEGRATION         = "Id_Log_Integration";
			public static String SYSTEME_EMETTEUR           = "Systeme_emetteur";
			public static String NATURE_MESSAGE             = "Nature_Message";
			public static String STATUT_INTEGRATION         = "Statut_Integration";
			public static String ID_TYPE_ERREUR             = "Id_type_erreur";
			public static String DESIGNATION_ANOMALIE       = "Designation_Anomalie";
			public static String MESSAGE                    = "Message";
			public static String ID_LOT                     = "Id_Lot";
			public static String NB_TENTATIVES_REINTEG      = "Nb_Tentatives_Reinteg";
			public static String TAILLE_DOCUMENT            = "Taille_document";
			public static String SYSTEME_DESTINATAIRE       = "Systeme_destinataire";
			public static String DIFFUSABLE                 = "diffusable";
			public static String TYPE_ERREUR                = "type_erreur";
			public static String IDENTIFIANT_DOCUMENT       = "identifiant_document";
			public static String DATE_ACTION_BACKOFFICE     = "date_action_backoffice";
			public static String DATE_GENERATION_BACKOFFICE = "date_generation_backoffice";
			public static String REF_METIER_1               = "ref_metier_1";
			public static String REF_METIER_2               = "ref_metier_2";
			public static String REF_TECHNIQUE_1            = "ref_technique_1";
			public static String SOUS_TYPE                  = "sous_type";
			public static String MESSAGERECEPTIONDATE       = "messageReceptionDate";
			public static String TECHNICALREFERENCE2        = "technicalReference2";
		}
		public static final class Enchere_CaractUM{
			public static String NAME              = "Name";
			public static String ID_UM             = "Id_UM";
			public static String DISPLAYTYPE       = "displayType";
			public static String UOM               = "uom";
			public static String MINVALUE          = "minValue";
			public static String NUMVALUE          = "NumValue";
			public static String MAXVALUE          = "maxValue";
			public static String TEXTVALUE         = "TextValue";
			public static String CODE_VALUE        = "Code_Value";
			public static String CODE_TABLE_VALEUR = "Code_Table_Valeur";
			public static String NO_ORDRE_AFF      = "No_ordre_aff";
		}
		public static final class Enchere_UM{
			public static String ID_UM							= "Id_UM";
			public static String OFFERID						= "OfferId";
			public static String CODE_USINE						= "Code_Usine";
			public static String IDENTIFICATION_UM				= "Identification_UM";
			public static String DATE_IDENTIFICATION			= "Date_identification";
			public static String NUM_VERSION 					= "Num_version";
			public static String ID_ENCHERE_LOT					= "Id_Enchere_Lot";
			public static String REFERENCE_UM					= "Reference_UM";
			public static String ID_SOURCE						= "Id_Source";
			public static String CODE_MATERIEL					= "Code_Materiel";
			public static String CODE_SEGMENTATION				= "Code_Segmentation";
			public static String CODE_LCPS						= "Code_Lcps";
			public static String CODE_MEMBRE_GEST				= "Code_membre_gest";
			public static String NOM_MEMBRE_GEST				= "Nom_membre_gest";
			public static String NOMBRE_UNITE					= "Nombre_Unite";
			public static String POIDS_BRUT						= "Poids_brut";
			public static String UNITE_POIDS					= "Unite_poids";
			public static String ATTRIBUEE						= "Attribuee";
			public static String NOM_REAFFECTEUR				= "Nom_reaffecteur";
			public static String EMAIL_REAFFECTEUR				= "Email_reaffecteur";
			public static String STATUT_UM						= "Statut_UM";
			public static String CATEGORIE						= "Categorie";
			public static String SOUS_CATEGORIE					= "Sous_Categorie";
			public static String CHOIX							= "Choix";
			public static String SYSTEME_EMETTEUR				= "Systeme_emetteur";
			public static String DATE_RECEPTION					= "Date_Reception";
			public static String RESPONSEID						= "ResponseId";
			public static String POIDS_BRUT_TON					= "Poids_brut_Ton";
			public static String DATE_CANCEL					= "Date_Cancel";
			public static String DATE_PROPOSITION				= "Date_Proposition";
			public static String NB_PROPOSITION					= "Nb_proposition";
			public static String REFERENCE_COMMANDE_FOURNISSEUR	= "reference_commande_fournisseur";
			public static String REFERENCE_RECLAMATION			= "reference_reclamation";
			public static String VILLE							= "ville";
			public static String LOCALISATION_UM				= "Localisation_UM";
			public static String AGE							= "age";
			public static String TIME_OFFERED					= "time_offered";
			public static String STACK							= "stack";
			public static String BUNDLESOURCE					= "bundleSource";
			public static String SUPPLIERGROUPID				= "supplierGroupId";
			public static String LOCALMATERIALCODE				= "localMaterialCode";
			public static String STOCKLOCATION					= "stockLocation";
			public static String FCASALESONLY					= "fcaSalesOnly";
			public static String ORIGINALCUSTOMERID				= "OriginalCustomerId";
			public static String ORIGINALCUSTOMERDESC			= "OriginalCustomerDesc";
			public static String POIDS_NET						= "Poids_net";
			public static String UNITE_POIDS_NET				= "Unite_Poids_Net";
			public static String SEGCLASS						= "segClass";
			public static String SEGSTANDARD					= "segStandard";
			public static String SEGCOILTYPE					= "segCoilType";
		}
	}
}
