package com.constant;

public class ConnectionConstantsClase {
   
	public static enum EnumConstans  {
		Hikari,
		DBCP,
		BoneCP,
		C3P0,
		Vibur
	};
        
        
       
	public static final EnumConstans connectionPool = EnumConstans.Hikari;
	public static final String databaseName = "conexion";
	public static final String databaseLogin = "root";
	public static final String databasePassword = "bitnami";
	public static final String databasePort = "3306";
	public static final String databaseHost = "localhost";
	public static final int getDatabaseMaxPoolSize = 10;
	public static final int getDatabaseMinPoolSize = 5;

	public static String getConnectionChain() {
		return "jdbc:mysql://" + ConnectionConstantsClase.databaseHost + ":" + ConnectionConstantsClase.databasePort + "/"
				+ ConnectionConstantsClase.databaseName;
	}

}
