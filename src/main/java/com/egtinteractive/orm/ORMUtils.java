package com.egtinteractive.orm;

import static com.egtinteractive.orm.resources.BundleUtils.*;

import java.lang.reflect.Field;
import java.math.BigInteger;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.egtinteractive.orm.annotations.Column;
import com.egtinteractive.orm.annotations.Entity;
import com.egtinteractive.orm.annotations.Id;
import com.egtinteractive.orm.annotations.Table;
import com.egtinteractive.orm.annotations.Transient;

public final class ORMUtils {

    private static <E> void entityAnnotationValidation(final Class<E> cls) {
	if (!isEntity(cls)) {
	    throw new RuntimeException(MESSAGE.getString("itsNotEntity"));
	}
    }

    public static <E> void validation(final Class<E> cls) {
	entityAnnotationValidation(cls);
    }

    public static String getTableName(final Class<?> cls) {
	final Table table = cls.getAnnotation(Table.class);
	return (table == null || table.name().equals("")) ? cls.getSimpleName().toLowerCase() : table.name();
    }

    public static String getColumnName(final Field field) {
	final Column column = field.getAnnotation(Column.class);
	return (column == null || column.name().equals("")) ? field.getName() : column.name();
    }

    public static List<Field> getColumnFields(final Field[] fields) {
	final List<Field> list = new ArrayList<>();
	for (Field field : fields) {
	    if (isColumn(field) && !isTransient(field)) {
		list.add(field);
	    }
	}
	return list;
    }

    public static Object getId(final List<Field> fields) {
	Field id = null;
	for (Field field : fields) {
	    if (field.isAnnotationPresent(Id.class)) {
		id = field;
	    }
	}
	if (id == null) {
	    throw new RuntimeException("You must specify @Id annotation");
	}
	return id.getName();
    }

    public static <E> E getCompletedElement(final Class<E> cls, final ResultSet rs) throws Exception {
	E instance;
	instance = cls.newInstance();
	final List<Field> columnFields = getColumnFields(cls.getDeclaredFields());
	for (Field field : columnFields) {
	    field.setAccessible(true);
	    final Object obj = rs.getObject(getColumnName(field));
	    field.set(instance, obj);
	}
	return instance;
    }

    public static <E> void addElementsIntoList(final List<E> list, Class<E> cls, final ResultSet rs,
	    final List<Field> columnFields) throws Exception {
	while (rs.next()) {
	    final E instance = cls.newInstance();
	    for (Field field : columnFields) {
		field.setAccessible(true);
		final Object obj = createObject(rs, field);
		field.set(instance, obj);
	    }
	    list.add(instance);
	}
    }

    public static String getSelectWhereQuery(final List<Field> fields, final Class<?> cls, final Object primaryKey) {
	final String selectQuery = getSelectQuery(fields, cls);
	final StringBuilder sb = new StringBuilder(selectQuery);
	sb.append(" WHERE ").append(getId(fields)).append(" = '").append(primaryKey).append("'");
	return sb.toString();
    }

    public static String getSelectQuery(final List<Field> fields, final Class<?> cls) {
	final StringBuilder sb = new StringBuilder();
	sb.append("SELECT ");
	for (Field field : fields) {
	    sb.append(getColumnName(field) + ",");
	}
	sb.deleteCharAt(sb.length() - 1).append(" FROM ").append(getTableName(cls));
	return sb.toString();
    }

    public static Field[] getAllFields(Class<?> cls) {
	final List<Field> allFields = new ArrayList<>();
	while (!cls.equals(Object.class)) {
	    final Field[] fields = cls.getDeclaredFields();
	    for (Field field : fields) {
		allFields.add(field);
	    }
	    cls = cls.getSuperclass();
	}
	final Field[] fields = new Field[allFields.size()];
	return allFields.toArray(fields);
    }

    private static boolean isTransient(final Field field) {
	return field.isAnnotationPresent(Transient.class);
    }

    private static boolean isColumn(final Field field) {
	return field.isAnnotationPresent(Column.class);
    }

    private static boolean isEntity(final Class<?> cls) {
	return cls.isAnnotationPresent(Entity.class);
    }

    private static Object createObject(final ResultSet rs, final Field field) throws SQLException {
	Object obj = null;
	if (field.getType().equals(Byte.class) || field.getType().equals(byte.class)) {
	    obj = rs.getByte(getColumnName(field));
	} else if (field.getType().equals(Short.class) || field.getType().equals(short.class)) {
	    obj = rs.getShort(getColumnName(field));
	} else if (field.getType().equals(Float.class) || field.getType().equals(float.class)) {
	    obj = rs.getFloat(getColumnName(field));
	} else if (field.getType().equals(BigInteger.class)) {
	    obj = new BigInteger(Integer.valueOf(rs.getInt(getColumnName(field))).toString());
	} else {
	    obj = rs.getObject(getColumnName(field));
	}
	return obj;
    }
}
