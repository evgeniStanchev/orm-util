package com.egtinteractive.orm.test.date;

import static com.egtinteractive.orm.resources.BundleUtils.*;
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
@Table(name = "date_and_time_types")
public class TestDateAndTimestamp extends TestUtils {

    @Test
    public void testDateAndTime() {
	List<ClassDateAndTimestamp> actualList = new ArrayList<>();
	actualList = ORM.findAll(ClassDateAndTimestamp.class);
	try (Connection connection = DBC.getDatasource().getConnection();
		Statement statement = connection.createStatement()) {
	    final ResultSet rs = statement.executeQuery(QUERY.getString("selectDateTime"));
	    List<ClassDateAndTimestamp> expectedList = new ArrayList<>();
	    while (rs.next()) {
		ClassDateAndTimestamp cdat = new ClassDateAndTimestamp(rs.getInt("id"), rs.getDate("date_"),
			rs.getDate("date_"), rs.getTimestamp("timestamp_"));
		expectedList.add(cdat);
	    }
	    assertEquals(actualList.toString(), expectedList.toString());
	} catch (final SQLException e) {
	    throw new RuntimeException(e);
	}
    }
}