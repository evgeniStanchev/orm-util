package com.egtinteractive.orm.test.wrappers;

import static com.egtinteractive.orm.resources.BundleUtils.QUERY;
import static com.egtinteractive.orm.test.resources.TestUtils.DBC;
import static com.egtinteractive.orm.test.resources.TestUtils.insertPrimitivesAndString;
import static org.testng.Assert.assertEquals;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import com.egtinteractive.orm.annotations.Entity;
import com.egtinteractive.orm.annotations.Table;
import com.egtinteractive.orm.test.resources.TestUtils;

@Entity
@Table(name = "primitives_and_string")
public class TestPrimitivesAndStrings extends TestUtils {

    final List<ClassWrapperPrimitives> expectedList = new ArrayList<>();
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
    public void testWrappers() {
	List<ClassWrapperPrimitives> actualList = new ArrayList<>();
	actualList = ORM.findAll(ClassWrapperPrimitives.class);
	assertEquals(actualList.toString(), expectedList.toString());
    }
}
