package com.egtinteractive.orm.test.inheritance;

import com.egtinteractive.orm.annotations.Column;
import com.egtinteractive.orm.annotations.Entity;
import com.egtinteractive.orm.annotations.Table;

@Entity
@Table(name = "primitives_and_string")
public class ChildClass extends SuperClass {

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

    public ChildClass() {
    }

    public ChildClass(final int intValue, final byte byte_,  final boolean boolean_,
	    final Short short_, final Integer integer_, final long long_, final float float_, final double double_,
	    final String string_) {
	super(intValue, short_, byte_);
	this.intValue = intValue;
	this.integer_ = integer_;
	this.long_ = long_;
	this.float_ = float_;
	this.double_ = double_;
	this.string_ = string_;
	this.boolean_ = boolean_;
    }

    public int getIntValue() {
	return super.getIntValue();
    }

    public void setIntValue(final int intValue) {
	super.setIntValue(intValue);
    }

    public byte getByte_() {
	return super.getByte_();
    }

    public String getTransient_() {
	return super.getTransient_();
    }

    public Short getShort_() {
	return super.getShort_();
    }

    public void setShort_(final Short short_) {
	super.setShort_(short_);
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

    @Override
    public String toString() {
	return "PrimitivesAndString [intValue=" + intValue + ", byte_=" + super.getByte_() + ", transient_="
		+ super.getTransient_() + ", short_=" + super.getShort_() + ", integer_=" + integer_ + ", long_="
		+ long_ + ", boolean_=" + boolean_ + ", float_=" + float_ + ", double_=" + double_ + ", string_="
		+ string_ + "]";
    }
}
