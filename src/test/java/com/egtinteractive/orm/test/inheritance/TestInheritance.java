package com.egtinteractive.orm.test.inheritance;

import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import static com.egtinteractive.orm.resources.BundleUtils.*;
import static com.egtinteractive.orm.test.resources.TestUtils.*;
import static org.testng.Assert.assertEquals;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

public class TestInheritance {
    final List<ChildClass> expectedList = new ArrayList<>();
    final int numberOfRows = ThreadLocalRandom.current().nextInt(5, 10);

    @BeforeTest
    public void insertion() {
	insertPrimitivesAndString(expectedList, numberOfRows);
    }

    @AfterTest
    public void removing() {
	try (Connection connection = DBC.getDatasource().getConnection()) {
	    final PreparedStatement preparedStatement = connection
		    .prepareStatement(QUERY.getString("truncatePrimitivesAndString"));
	    preparedStatement.executeUpdate();
	} catch (final SQLException e) {
	    throw new RuntimeException(e);
	}
    }

    @Test
    public void testInheritance() {
	List<ChildClass> actualList = new ArrayList<>();
	actualList = ORM.findAll(ChildClass.class);
	assertEquals(actualList.toString(), expectedList.toString());
    }
}