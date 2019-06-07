package com.egtinteractive.orm.test.strings.primitives;

import static com.egtinteractive.orm.resources.BundleUtils.QUERY;
import static org.testng.Assert.assertEquals;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import org.testng.annotations.Test;

import com.egtinteractive.orm.annotations.Entity;
import com.egtinteractive.orm.annotations.Table;
import com.egtinteractive.orm.test.resources.TestUtils;

@Entity
@Table(name = "primitives_and_string")
public class TestPrimitivesAndStrings extends TestUtils {

    @Test
    public void testPrimitivesAndStrings() {
	List<ClassPrimitivesAndStrings> actualList = new ArrayList<>();
	actualList = ORM.findAll(ClassPrimitivesAndStrings.class);
	try (Connection connection = DBC.getDatasource().getConnection();
		Statement statement = connection.createStatement()) {
	    final ResultSet rs = statement.executeQuery(QUERY.getString("selectPrimitivesAndString"));
	    List<ClassPrimitivesAndStrings> expectedList = new ArrayList<>();
	    while (rs.next()) {
		ClassPrimitivesAndStrings tpas = new ClassPrimitivesAndStrings(rs.getInt("id"), rs.getByte("byte_"),
			null, rs.getBoolean("boolean_"), rs.getShort("short_"), rs.getInt("integer_"),
			rs.getLong("long_"), rs.getFloat("float_"), rs.getDouble("double_"), rs.getString("string_"));
		expectedList.add(tpas);
	    }
	    assertEquals(actualList.toString(), expectedList.toString());
	} catch (final SQLException e) {
	    throw new RuntimeException(e);
	}
    }
}
