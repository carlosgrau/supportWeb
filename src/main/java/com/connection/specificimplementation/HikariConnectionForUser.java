/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.connection.specificimplementation;

import com.constant.ConnectionConstants;
import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import java.sql.Connection;
import java.sql.SQLException;

/**
 *
 * @author a021792876p
 */
public class HikariConnectionForUser{

    private Connection oConnectionCli;
    private HikariDataSource oConnectionPoolCli;

    public Connection newConnectionParams(String usuario, String password, String conexion) throws Exception {
        
        HikariConfig config = new HikariConfig();
        config.setJdbcUrl(conexion);
        config.setUsername(usuario);
        config.setPassword(password);
        config.setMaximumPoolSize(ConnectionConstants.getDatabaseMaxPoolSize);
        config.setMinimumIdle(ConnectionConstants.getDatabaseMinPoolSize);

        config.addDataSourceProperty("cachePrepStmts", "true");
        config.addDataSourceProperty("prepStmtCacheSize", "250");
        config.addDataSourceProperty("prepStmtCacheSqlLimit", "2048");
        config.setLeakDetectionThreshold(15000);
        config.setConnectionTestQuery("SELECT 1");
        config.setConnectionTimeout(2000);

        try {
            oConnectionPoolCli = new HikariDataSource(config);
            oConnectionCli = (Connection) oConnectionPoolCli.getConnection();
            return oConnectionCli;

        } catch (SQLException ex) {
            String msgError = this.getClass().getName() + ":" + (ex.getStackTrace()[1]).getMethodName();
            throw new Exception(msgError, ex);
        }

    }

    public void disposeConnection() throws Exception {
        
        if (oConnectionCli != null) {
            oConnectionCli.close();
        }
        if (oConnectionPoolCli != null) {
            oConnectionPoolCli.close();
        }
    }

}
