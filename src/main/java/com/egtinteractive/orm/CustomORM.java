package com.egtinteractive.orm;

import static com.egtinteractive.orm.ORMUtils.*;

import java.lang.reflect.Field;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.egtinteractive.orm.jdbc.DBConfiguration;

public final class CustomORM implements ORM {
    private final DBConfiguration dbConfig;

    public CustomORM(final DBConfiguration dbConfig) {
	this.dbConfig = dbConfig;
    }

    @Override
    public <E> List<E> findAll(final Class<E> cls) {
	validation(cls);
	final List<E> theList = new ArrayList<>();
	try (final Connection connection = dbConfig.getDatasource().getConnection();
		final Statement statement = connection.createStatement()) {
	    final Field[] allFields = getAllFields(cls);
	    final List<Field> columnFields = getColumnFields(allFields);
	    final String query = getSelectQuery(columnFields, cls);
	    final ResultSet rs = statement.executeQuery(query);
	    addElementsIntoList(theList, cls, rs,columnFields);
	} catch (final Exception e) {
	    throw new RuntimeException(e);
	}
	return theList;
    }

    @Override
    public <E> E find(final Object primaryKey, final Class<E> cls) {
	validation(cls);
	try (final Connection connection = dbConfig.getDatasource().getConnection();
		final Statement statement = connection.createStatement()) {
	    E e = cls.newInstance();
	    final List<Field> columnFields = getColumnFields(cls.getDeclaredFields());
	    final String query = getSelectWhereQuery(columnFields, cls, primaryKey);

	    final ResultSet rs = statement.executeQuery(query);
	    while (rs.next())
		e = getCompletedElement(cls, rs);
	    return e;
	} catch (final Exception e) {
	    throw new RuntimeException(e);
	}
    }
}