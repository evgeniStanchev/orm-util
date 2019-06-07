package com.egtinteractive.orm.test.annotations;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertFalse;
import static org.testng.Assert.assertNotEquals;
import static org.testng.Assert.assertTrue;

import org.testng.annotations.Test;

import com.egtinteractive.orm.annotations.Entity;
import com.egtinteractive.orm.annotations.Table;

import static com.egtinteractive.orm.ORMUtils.*;

public class TestTableAnnotation {

    private final static Class<Table> TABLE_CLASS = Table.class;

    @Entity
    private class NotTableClass {
    }

    @Entity
    @Table(name = "SomeTable")
    private class TableClass {
    }

    @Entity
    @Table(name = "")
    private class TableEmptyClass {
    }

    @Entity
    @Table
    private class TableWithoutArgumentsClass {
    }

    @Entity
    @Table(schema = "SomeScheme")
    private class TableWithSchemaClass {
    }

    @Entity
    @Table(schema = "")
    private class TableWithEmptySchema {
    }

    @Test
    public void testNotTableClass() {
	final Class<NotTableClass> theClass = NotTableClass.class;
	assertFalse(theClass.isAnnotationPresent(TABLE_CLASS));
	assertEquals(getTableName(theClass), theClass.getSimpleName().toLowerCase());
    }

    @Test
    public void testTableClass() {
	final Class<TableClass> theClass = TableClass.class;
	assertTrue(theClass.isAnnotationPresent(TABLE_CLASS));
	assertNotEquals(theClass.getAnnotation(TABLE_CLASS).name(), "");
	assertEquals(getTableName(theClass), theClass.getAnnotation(TABLE_CLASS).name());
    }

    @Test
    public void testTableEmptyClass() {
	final Class<TableEmptyClass> theClass = TableEmptyClass.class;
	assertTrue(theClass.isAnnotationPresent(TABLE_CLASS));
	assertEquals(theClass.getAnnotation(TABLE_CLASS).name(), "");
	assertEquals(getTableName(theClass), theClass.getSimpleName().toLowerCase());
    }

    @Test
    public void testTableWithoutArgumentsClass() {
	final Class<TableWithoutArgumentsClass> theClass = TableWithoutArgumentsClass.class;
	assertTrue(theClass.isAnnotationPresent(TABLE_CLASS));
	assertEquals(theClass.getAnnotation(TABLE_CLASS).name(), "");
	assertEquals(getTableName(theClass), theClass.getSimpleName().toLowerCase());
    }
}