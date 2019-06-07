package com.egtinteractive.orm.test.annotations;

import static org.testng.Assert.assertFalse;
import static org.testng.Assert.assertTrue;

import java.lang.reflect.Field;

import org.testng.annotations.Test;

import com.egtinteractive.orm.annotations.Column;
import com.egtinteractive.orm.annotations.Id;

public class TestIdAnnotation {

    private class ClsWithIdField {
	@Id
	@Column
	private String id;
    }

    private class ClsWithoutIdField {
	@Column
	private String id;
    }

    @Test
    public void testClassWithId() {
	Field[] field = ClsWithIdField.class.getDeclaredFields();
	field[0].setAccessible(true);
	assertTrue(field[0].isAnnotationPresent(Id.class));
    }

    @Test
    public void testClassWithoutsId() {
	Field[] field = ClsWithoutIdField.class.getDeclaredFields();
	field[0].setAccessible(true);
	assertFalse(field[0].isAnnotationPresent(Id.class));
    }
}
