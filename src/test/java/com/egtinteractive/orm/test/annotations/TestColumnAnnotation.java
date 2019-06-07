package com.egtinteractive.orm.test.annotations;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertFalse;

import java.lang.reflect.Field;

import org.testng.annotations.Test;

import com.egtinteractive.orm.annotations.Column;
import com.egtinteractive.orm.annotations.Entity;

import static com.egtinteractive.orm.ORMUtils.*;

public class TestColumnAnnotation {

    @Entity
    private class NotAnnotatedColumnClass {
	@SuppressWarnings("unused")
	public String str;
    }

    @Test
    public void testNotAnnotatedColumnClass() {
	final Class<NotAnnotatedColumnClass> theClass = NotAnnotatedColumnClass.class;
	final Field theField = theClass.getFields()[0];
	assertFalse(theField.isAnnotationPresent(Column.class));
	assertEquals(theField.getName(), getColumnName(theField));
    }
}