package com.egtinteractive.orm.test.biginteger.bigdecimal;

import java.math.BigDecimal;
import java.math.BigInteger;

import com.egtinteractive.orm.annotations.Column;
import com.egtinteractive.orm.annotations.Entity;
import com.egtinteractive.orm.annotations.Id;
import com.egtinteractive.orm.annotations.Table;

@Entity
@Table(name = "big_integer_decimal_types")
public class ClassBigIntegerDecimal {
    @Id
    @Column(name = "id")
    public int intValue;

    @Column(name = "biginteger_")
    public BigInteger bigInteger_;

    @Column(name = "bigdecimal_")
    public BigDecimal bigDecimal_;

    public ClassBigIntegerDecimal() {
    }

    public ClassBigIntegerDecimal(final int intValue, final BigInteger bigInteger_, final BigDecimal bigDecimal_) {
	this.intValue = intValue;
	this.bigInteger_ = bigInteger_;
	this.bigDecimal_ = bigDecimal_;
    }

    public int getIntValue() {
	return intValue;
    }

    public void setIntValue(final int intValue) {
	this.intValue = intValue;
    }

    public BigInteger getBigInteger_() {
	return bigInteger_;
    }

    public void setBigInteger_(final BigInteger bigInteger_) {
	this.bigInteger_ = bigInteger_;
    }

    public BigDecimal getBigDecimal_() {
	return bigDecimal_;
    }

    public void setBigDecimal_(final BigDecimal bigDecimal_) {
	this.bigDecimal_ = bigDecimal_;
    }

    @Override
    public String toString() {
	return "ClassBigIntegerDecimal [intValue=" + intValue + ", bigInteger_=" + bigInteger_ + ", bigDecimal_="
		+ bigDecimal_ + "]";
    }
}
