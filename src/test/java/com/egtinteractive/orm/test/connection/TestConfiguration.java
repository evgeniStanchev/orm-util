package com.egtinteractive.orm.test.connection;

import static com.egtinteractive.orm.resources.BundleUtils.*;
import static org.testng.Assert.*;

import java.net.UnknownHostException;
import java.sql.SQLException;
import java.util.UUID;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.egtinteractive.orm.jdbc.DBConfiguration;
import com.mysql.jdbc.exceptions.jdbc4.MySQLSyntaxErrorException;

public class TestConfiguration {

    @DataProvider
    public Object[][] myDataProvider() {
	final String randomStr = UUID.randomUUID().toString();
	final String database = CONFIG.getString("database");
	final String driverProtocol = CONFIG.getString("driverProtocol");
	final String username = CONFIG.getString("username");
	final String password = CONFIG.getString("password");

	return new Object[][] { { randomStr, database, username, password, UnknownHostException.class },
		{ driverProtocol, randomStr, username, password, MySQLSyntaxErrorException.class },
		{ driverProtocol, database, randomStr, password, SQLException.class },
		{ driverProtocol, database, username, randomStr, SQLException.class } };
    }

    @Test(dataProvider = "myDataProvider")
    public void testIllegalInfo(final String host, final String database, final String username, final String password,
	    final Class<?> expectedException) {
	Throwable actualException = null;
	try {
	    new DBConfiguration(host, database, username, password);
	} catch (final Exception e) {
	    actualException = e;
	    while (actualException.getCause() != null) {
		actualException = actualException.getCause();
	    }
	}
	assertNotNull(actualException);
	assertEquals(actualException.getClass(), expectedException);
    }
}