package com.egtinteractive.orm.test.resources;

import static com.egtinteractive.orm.resources.BundleUtils.CONFIG;
import static com.egtinteractive.orm.resources.BundleUtils.QUERY;
import static com.egtinteractive.orm.test.resources.TestUtils.DBC;
import static com.egtinteractive.orm.test.resources.TestUtils.randomBoolean;
import static com.egtinteractive.orm.test.resources.TestUtils.randomByte;
import static com.egtinteractive.orm.test.resources.TestUtils.randomDouble;
import static com.egtinteractive.orm.test.resources.TestUtils.randomFloat;
import static com.egtinteractive.orm.test.resources.TestUtils.randomInteger;
import static com.egtinteractive.orm.test.resources.TestUtils.randomLong;
import static com.egtinteractive.orm.test.resources.TestUtils.randomShort;
import static com.egtinteractive.orm.test.resources.TestUtils.randomString;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;
import java.util.UUID;
import java.util.concurrent.ThreadLocalRandom;

import com.egtinteractive.orm.CustomORM;
import com.egtinteractive.orm.jdbc.DBConfiguration;
import com.egtinteractive.orm.test.inheritance.ChildClass;

public class TestUtils {
    public static final DBConfiguration DBC = new DBConfiguration(CONFIG.getString("driverProtocol"),
	    CONFIG.getString("database"), CONFIG.getString("username"), CONFIG.getString("password"));
    public static final CustomORM ORM = new CustomORM(DBC);
    
    public static final byte randomByte(){
	return (byte) ThreadLocalRandom.current().nextInt();
    }
    
    public static final short randomShort(){
	return (short) ThreadLocalRandom.current().nextInt();
    }
    
    public static final int randomInteger(){
	return ThreadLocalRandom.current().nextInt();
    }
    
    public static final long randomLong(){
	return ThreadLocalRandom.current().nextLong();
    }
    
    public static final String randomString(){
   	return UUID.randomUUID().toString();
       }
       
    public static final Boolean randomBoolean(){
	return ThreadLocalRandom.current().nextBoolean();
    }
    
    public static final char randomChar(){
	return (char) ThreadLocalRandom.current().nextInt();
    }
    
    public static final float randomFloat(){
	return ThreadLocalRandom.current().nextFloat();
    }
    
    public static final double randomDouble(){
	return ThreadLocalRandom.current().nextDouble();
    }
    
    @SuppressWarnings("unchecked")
    public static final <E> void insertPrimitivesAndString(final List<E> list, final int numberOfRows){
	try (Connection connection = DBC.getDatasource().getConnection()) {
	    for (int rowNum = 1; rowNum < numberOfRows; rowNum++) {
		final PreparedStatement preparedStatement = connection
			.prepareStatement(QUERY.getString("preparedInsertPrimitivesAndString"));
		final byte byte_ = randomByte();
		final short short_ = randomShort();
		final int integer_ = randomInteger();
		final long long_ = randomLong();
		final boolean boolean_ = randomBoolean();
		final float float_ = randomFloat();
		final double double_ = randomDouble();
		final String string_ = randomString();
		preparedStatement.setByte(1, byte_);
		preparedStatement.setShort(2, short_);
		preparedStatement.setInt(3, integer_);
		preparedStatement.setLong(4, long_);
		preparedStatement.setBoolean(5, boolean_);
		preparedStatement.setFloat(6, float_);
		preparedStatement.setDouble(7, double_);
		preparedStatement.setString(8, string_);
		preparedStatement.executeUpdate();
		list.add((E) new ChildClass(rowNum, byte_, boolean_, short_, integer_, long_, float_, double_, string_));
	    }
	} catch (final SQLException e) {
	    throw new RuntimeException(e);
	}
    }
    
}
