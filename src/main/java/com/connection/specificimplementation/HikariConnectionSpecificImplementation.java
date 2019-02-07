package com.connection.specificimplementation;

import java.sql.Connection;
import java.sql.SQLException;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;

import com.connection.publicinterface.ConnectionInterface;
import com.constant.ConnectionConstants;
import com.constant.ConnectionConstantsClase;

public class HikariConnectionSpecificImplementation implements ConnectionInterface {

    private Connection oConnection;
    private HikariDataSource oConnectionPool;
    private Boolean clase = false;
    
    public Connection newConnection() throws Exception {

        HikariConfig config = new HikariConfig();
        if(clase){
        config.setJdbcUrl(ConnectionConstantsClase.getConnectionChain());
        config.setUsername(ConnectionConstantsClase.databaseLogin);
        config.setPassword(ConnectionConstantsClase.databasePassword);
        config.setMaximumPoolSize(ConnectionConstantsClase.getDatabaseMaxPoolSize);
        config.setMinimumIdle(ConnectionConstantsClase.getDatabaseMinPoolSize);
        }else{
        config.setJdbcUrl(ConnectionConstants.getConnectionChain());
        config.setUsername(ConnectionConstants.databaseLogin);
        config.setPassword(ConnectionConstants.databasePassword);
        config.setMaximumPoolSize(ConnectionConstants.getDatabaseMaxPoolSize);
        config.setMinimumIdle(ConnectionConstants.getDatabaseMinPoolSize);
        }
        config.addDataSourceProperty("cachePrepStmts", "true");
        config.addDataSourceProperty("prepStmtCacheSize", "250");
        config.addDataSourceProperty("prepStmtCacheSqlLimit", "2048");
        config.setLeakDetectionThreshold(15000);
        config.setConnectionTestQuery("SELECT 1");
        config.setConnectionTimeout(2000);

        try {
            oConnectionPool = new HikariDataSource(config);
            oConnection = (Connection) oConnectionPool.getConnection();
            return oConnection;

        } catch (SQLException ex) {
            String msgError = this.getClass().getName() + ":" + (ex.getStackTrace()[1]).getMethodName();
            throw new Exception(msgError, ex);
        }

    }

    public void disposeConnection() throws Exception {
        if (oConnection != null) {
            oConnection.close();
        }
        if (oConnectionPool != null) {
            oConnectionPool.close();
        }
    }

}
