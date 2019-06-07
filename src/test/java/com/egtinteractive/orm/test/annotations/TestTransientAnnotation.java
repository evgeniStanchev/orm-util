package com.egtinteractive.orm.test.annotations;

import static org.testng.Assert.assertFalse;
import static org.testng.Assert.assertTrue;

import java.lang.reflect.Field;

import org.testng.annotations.Test;

import com.egtinteractive.orm.annotations.Transient;

public class TestTransientAnnotation {

    private class ClsWithTransient {
	@Transient
	private String transientField;
    }

    private class ClsWithoutTransient {
	@SuppressWarnings("unused")
	private String nonTransientField;
    }

    @Test
    public void testTransientField() {
	final Field[] field = ClsWithTransient.class.getDeclaredFields();
	field[0].setAccessible(true);
	assertTrue(field[0].isAnnotationPresent(Transient.class));
    }

    @Test
    public void testNotTransientField() {
	final Field[] field = ClsWithoutTransient.class.getDeclaredFields();
	field[0].setAccessible(true);
	assertFalse(field[0].isAnnotationPresent(Transient.class));
    }
}
