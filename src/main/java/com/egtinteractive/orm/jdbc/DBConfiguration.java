package com.egtinteractive.orm.jdbc;

import java.sql.SQLException;

import com.mysql.jdbc.jdbc2.optional.MysqlDataSource;

public final class DBConfiguration {

    private final String driverProtocol;
    private final String database;
    private final String username;
    private final String password;
    private final String url;
    private final MysqlDataSource dataSource;

    public DBConfiguration(final String driverProtocol, final String database, final String username,
	    final String password) {
	this.driverProtocol = driverProtocol;
	this.database = database;
	this.username = username;
	this.password = password;
	this.url = this.driverProtocol + this.database;
	this.dataSource = createDatasource();
    }

    public String getDatabaseName() {
	return this.database;
    }

    public String getUrl() {
	return this.url;
    }

    public MysqlDataSource getDatasource() {
	return this.dataSource;
    }

    private MysqlDataSource createDatasource() {
	final MysqlDataSource dataSource = new MysqlDataSource();
	dataSource.setDatabaseName(this.database);
	dataSource.setServerName(this.driverProtocol);
	dataSource.setUser(this.username);
	dataSource.setPassword(this.password);

	try {
	    dataSource.getConnection();
	    System.out.println("Connection created!");
	} catch (SQLException e) {
	    throw new RuntimeException(e);
	}
	return dataSource;
    }
}