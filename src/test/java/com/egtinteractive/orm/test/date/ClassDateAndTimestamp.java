package com.egtinteractive.orm.test.date;

import java.sql.Date;
import java.sql.Timestamp;

import com.egtinteractive.orm.annotations.Column;
import com.egtinteractive.orm.annotations.Entity;
import com.egtinteractive.orm.annotations.Id;
import com.egtinteractive.orm.annotations.Table;

@Entity
@Table(name = "date_and_time_types")
public class ClassDateAndTimestamp {
    @Id
    @Column(name = "id")
    public int intValue;

    @Column(name = "date_")
    public Date dateSQL_;

    @Column(name = "date_")
    public java.util.Date dateUtil_;

    @Column
    public Timestamp timestamp_;

    public ClassDateAndTimestamp() {
    }

    public ClassDateAndTimestamp(final int intValue, final java.util.Date dateUtil_, final Date dateSQL_,
	    final Timestamp timestamp_) {
	this.dateUtil_ = dateUtil_;
	this.dateSQL_ = dateSQL_;
	this.intValue = intValue;
	this.timestamp_ = timestamp_;
    }

    public int getIntValue() {
	return intValue;
    }

    public void setIntValue(final int intValue) {
	this.intValue = intValue;
    }

    public Date getDateSQL_() {
	return dateSQL_;
    }

    public void setDateSQL_(final Date dateSQL_) {
	this.dateSQL_ = dateSQL_;
    }

    public java.util.Date getDateUtil_() {
	return dateUtil_;
    }

    public void setDateUtil_(final java.util.Date dateUtil_) {
	this.dateUtil_ = dateUtil_;
    }

    public Timestamp getTimestamp_() {
	return timestamp_;
    }

    public void setTimestamp_(final Timestamp timestamp_) {
	this.timestamp_ = timestamp_;
    }

    @Override
    public String toString() {
	return "ClassDates [intValue=" + intValue + ", dateSQL_=" + dateSQL_ + ", dateUtil_=" + dateUtil_
		+ ", timestamp_=" + timestamp_ + "]";
    }
}
