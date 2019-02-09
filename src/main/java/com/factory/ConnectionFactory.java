package com.factory;


import com.connection.publicinterface.ConnectionInterface;
import com.connection.specificimplementation.BoneCPConnectionSpecificImplementation;
import com.connection.specificimplementation.C3P0ConnectionSpecificImplementation;
import com.connection.specificimplementation.DBCPConnectionSpecificImplementation;
import com.connection.specificimplementation.HikariConnectionSpecificImplementation;
import com.connection.specificimplementation.ViburConnectionSpecificImplementation;
import com.constant.ConnectionConstants;

public class ConnectionFactory {
	public static ConnectionInterface getConnection(ConnectionConstants.EnumConstans enumConnection) {
		ConnectionInterface oConnectionInterface = null;
		switch (enumConnection) {
		case Hikari:
			oConnectionInterface = new HikariConnectionSpecificImplementation();
			break;
		default:
			oConnectionInterface = new HikariConnectionSpecificImplementation();
			break;
		}
		return oConnectionInterface;

	}
}
