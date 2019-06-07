package com.egtinteractive.orm.test.biginteger.bigdecimal;

import static com.egtinteractive.orm.resources.BundleUtils.*;
import static org.testng.Assert.assertEquals;

import java.math.BigInteger;
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
@Table(name = "big_integer_decimal_types")
public class TestBigDecimalBigInteger extends TestUtils {

    @Test
    public void testBigValues() {
	List<ClassBigIntegerDecimal> actualList = new ArrayList<>();
	actualList = ORM.findAll(ClassBigIntegerDecimal.class);
	try (Connection connection = DBC.getDatasource().getConnection();
		Statement statement = connection.createStatement()) {
	    final ResultSet rs = statement.executeQuery(QUERY.getString("selectBigvalues"));
	    List<ClassBigIntegerDecimal> expectedList = new ArrayList<>();
	    while (rs.next()) {
		ClassBigIntegerDecimal cdat = new ClassBigIntegerDecimal(rs.getInt("id"),
			new BigInteger(Long.valueOf(rs.getLong("biginteger_")).toString()),
			rs.getBigDecimal("bigDecimal_"));
		expectedList.add(cdat);
	    }
	    assertEquals(actualList.toString(), expectedList.toString());
	} catch (final SQLException e) {
	    throw new RuntimeException(e);
	}
    }
}
