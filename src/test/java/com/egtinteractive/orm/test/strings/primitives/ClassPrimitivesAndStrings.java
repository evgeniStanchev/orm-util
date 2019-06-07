package com.egtinteractive.orm.test.strings.primitives;

import com.egtinteractive.orm.annotations.Column;
import com.egtinteractive.orm.annotations.Entity;
import com.egtinteractive.orm.annotations.Id;
import com.egtinteractive.orm.annotations.Table;
import com.egtinteractive.orm.annotations.Transient;

@Entity
@Table(name = "primitives_and_string")
public class ClassPrimitivesAndStrings {
    @Id
    @Column(name = "id")
    public int intValue;

    @Column
    private final byte byte_;

    @Transient
    @Column
    private final String transient_;

    @Column
    private Integer integer_;

    @Column
    private long long_;

    @Column
    private boolean boolean_;

    @Column
    private float float_;

    @Column
    double double_;

    @Column
    private String string_;

    @Column
    private Short short_;

    public ClassPrimitivesAndStrings() {
	this.byte_ = 0;
	this.transient_ = null;
    }

    public ClassPrimitivesAndStrings(final int intValue, final byte byte_, final String transient_,
	    final boolean boolean_, final Short short_, final Integer integer_, final long long_, final float float_,
	    final double double_, final String string_) {
	this.intValue = intValue;
	this.byte_ = byte_;
	this.transient_ = transient_;
	this.integer_ = integer_;
	this.long_ = long_;
	this.boolean_ = boolean_;
	this.float_ = float_;
	this.double_ = double_;
	this.string_ = string_;
	this.short_ = short_;
    }

    public int getIntValue() {
	return intValue;
    }

    public void setIntValue(final int intValue) {
	this.intValue = intValue;
    }

    public Integer getInteger_() {
	return integer_;
    }

    public void setInteger_(final Integer integer_) {
	this.integer_ = integer_;
    }

    public long getLong_() {
	return long_;
    }

    public void setLong_(final long long_) {
	this.long_ = long_;
    }

    public boolean isBoolean_() {
	return boolean_;
    }

    public void setBoolean_(final boolean boolean_) {
	this.boolean_ = boolean_;
    }

    public float getFloat_() {
	return float_;
    }

    public void setFloat_(final float float_) {
	this.float_ = float_;
    }

    public double getDouble_() {
	return double_;
    }

    public void setDouble_(final double double_) {
	this.double_ = double_;
    }

    public String getString_() {
	return string_;
    }

    public void setString_(final String string_) {
	this.string_ = string_;
    }

    public Short getShort_() {
	return short_;
    }

    public void setShort_(final Short short_) {
	this.short_ = short_;
    }

    public byte getByte_() {
	return byte_;
    }

    public String getTransient_() {
	return transient_;
    }

    @Override
    public String toString() {
	return "ClassPrimitivesAndStrings [intValue=" + intValue + ", byte_=" + byte_ + ", transient_=" + transient_
		+ ", integer_=" + integer_ + ", long_=" + long_ + ", boolean_=" + boolean_ + ", float_=" + float_
		+ ", double_=" + double_ + ", string_=" + string_ + ", short_=" + short_ + "]";
    }
}
