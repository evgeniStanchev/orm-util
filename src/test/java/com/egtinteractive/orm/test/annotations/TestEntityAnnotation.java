package com.egtinteractive.orm.test.annotations;

import static com.egtinteractive.orm.resources.BundleUtils.*;
import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertFalse;
import static org.testng.Assert.assertNotNull;
import static org.testng.Assert.assertTrue;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.egtinteractive.orm.CustomORM;
import com.egtinteractive.orm.annotations.Entity;
import com.egtinteractive.orm.jdbc.DBConfiguration;

public class TestEntityAnnotation {

    @Entity
    private class EntityClass {

    }

    private class NonEntityClass {
    }

    @DataProvider
    public Object[][] orm() {
	final String driverProtocol = CONFIG.getString("driverProtocol");
	final String database = CONFIG.getString("database");
	final String username = CONFIG.getString("username");
	final String password = CONFIG.getString("password");
	return new Object[][] { { new CustomORM(new DBConfiguration(driverProtocol, database, username, password)) } };
    }

    @Test(dataProvider = "orm")
    public void testEntityClass(final CustomORM cORM) {
	assertTrue(EntityClass.class.isAnnotationPresent(Entity.class));
    }

    @Test(dataProvider = "orm")
    public void testNonEntityClass(final CustomORM cORM) {
	Throwable expectedException = null;
	assertFalse(NonEntityClass.class.isAnnotationPresent(Entity.class));
	try {
	    cORM.find(1, NonEntityClass.class);
	} catch (final Exception e) {
	    expectedException = e;
	}
	assertNotNull(expectedException);
	assertTrue(expectedException.getClass() == RuntimeException.class);
	assertEquals(expectedException.getClass(), RuntimeException.class);
    }
}