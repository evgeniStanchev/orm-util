package com.egtinteractive.orm.test.inheritance;

import com.egtinteractive.orm.annotations.Column;
import com.egtinteractive.orm.annotations.Entity;
import com.egtinteractive.orm.annotations.Id;
import com.egtinteractive.orm.annotations.Transient;

@Entity
public class SuperClass {
    @Id
    @Column(name = "id")
    public int intValue;

    @Column
    private final byte byte_;

    @Transient
    @Column
    private String transient_;

    @Column
    private Short short_;

    public SuperClass() {
	this.byte_ = 0;
	this.transient_ = null;
    }

    public SuperClass(final int intValue_, final short short_, final byte byte_) {
	this.intValue = intValue_;
	this.short_ = short_;
	this.byte_ = byte_;
    }

    public int getIntValue() {
	return this.intValue;
    }

    public void setIntValue(final int intValue) {
	this.intValue = intValue;
    }

    public byte getByte_() {
	return byte_;
    }

    public String getTransient_() {
	return transient_;
    }

    public Short getShort_() {
	return short_;
    }

    public void setShort_(final Short short_) {
	this.short_ = short_;
    }
}