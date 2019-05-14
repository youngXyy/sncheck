package com.ruijie.sncheck.dao.po;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.Date;
import java.util.Objects;

/**
 * MaterialTablePo
 *
 * @author {yuanwei}
 * @date 2019/5/12 20:18
 */
@Entity
@Table(name = "material_table", schema = "ruijiesncheck", catalog = "")
@IdClass(MaterialTablePoPK.class)
@EntityListeners(AuditingEntityListener.class)
public class MaterialTablePo {
    private String materielCode;
    private String task;
    private String boxCode;
    private String snCode;
    private String spareCode;
    private String attributeCode;
    private String printer;
    private Date printDate;
    private String printTimes;
    private String lastPrinter;
    private Date lastPrintDate;
    private String lastPrintTime;
    private Date createDate;
    private Date updateDate;
    private String values1;
    private String values2;
    private String values3;
    private String values4;

    @Basic
    @Column(name = "materiel_code", nullable = false, length = 255)
    public String getMaterielCode() {
        return materielCode;
    }

    public void setMaterielCode(String materielCode) {
        this.materielCode = materielCode;
    }

    @Basic
    @Column(name = "task", nullable = false, length = 64)
    public String getTask() {
        return task;
    }

    public void setTask(String task) {
        this.task = task;
    }

    @Id
    @Column(name = "box_code", nullable = false, length = 64)
    public String getBoxCode() {
        return boxCode;
    }

    public void setBoxCode(String boxCode) {
        this.boxCode = boxCode;
    }

    @Id
    @Column(name = "sn_code", nullable = false, length = 64)
    public String getSnCode() {
        return snCode;
    }

    public void setSnCode(String snCode) {
        this.snCode = snCode;
    }

    @Basic
    @Column(name = "spare_code", nullable = false, length = 64)
    public String getSpareCode() {
        return spareCode;
    }

    public void setSpareCode(String spareCode) {
        this.spareCode = spareCode;
    }

    @Basic
    @Column(name = "attribute_code", nullable = false, length = 64)
    public String getAttributeCode() {
        return attributeCode;
    }

    public void setAttributeCode(String attributeCode) {
        this.attributeCode = attributeCode;
    }

    @Basic
    @Column(name = "printer", nullable = true, length = 32)
    public String getPrinter() {
        return printer;
    }

    public void setPrinter(String printer) {
        this.printer = printer;
    }

    @Basic
    @Column(name = "print_date", nullable = true)
    public Date getPrintDate() {
        return printDate;
    }

    public void setPrintDate(Date printDate) {
        this.printDate = printDate;
    }

    @Basic
    @Column(name = "print_times", nullable = true, length = 32)
    public String getPrintTimes() {
        return printTimes;
    }

    public void setPrintTimes(String printTimes) {
        this.printTimes = printTimes;
    }

    @Basic
    @Column(name = "last_printer", nullable = true, length = 32)
    public String getLastPrinter() {
        return lastPrinter;
    }

    public void setLastPrinter(String lastPrinter) {
        this.lastPrinter = lastPrinter;
    }

    @Basic
    @Column(name = "last_print_date", nullable = true)
    public Date getLastPrintDate() {
        return lastPrintDate;
    }

    public void setLastPrintDate(Date lastPrintDate) {
        this.lastPrintDate = lastPrintDate;
    }

    @Basic
    @Column(name = "last_print_time", nullable = true, length = 32)
    public String getLastPrintTime() {
        return lastPrintTime;
    }

    public void setLastPrintTime(String lastPrintTime) {
        this.lastPrintTime = lastPrintTime;
    }

    @Basic
    @Column(name = "create_date", nullable = false)
    @CreatedDate
    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    @Basic
    @Column(name = "update_date", nullable = false)
    @LastModifiedDate
    public Date getUpdateDate() {
        return updateDate;
    }

    public void setUpdateDate(Date updateDate) {
        this.updateDate = updateDate;
    }

    @Basic
    @Column(name = "values1", nullable = true, length = 255)
    public String getValues1() {
        return values1;
    }

    public void setValues1(String values1) {
        this.values1 = values1;
    }

    @Basic
    @Column(name = "values2", nullable = true, length = 255)
    public String getValues2() {
        return values2;
    }

    public void setValues2(String values2) {
        this.values2 = values2;
    }

    @Basic
    @Column(name = "values3", nullable = true, length = 255)
    public String getValues3() {
        return values3;
    }

    public void setValues3(String values3) {
        this.values3 = values3;
    }

    @Basic
    @Column(name = "values4", nullable = true, length = 255)
    public String getValues4() {
        return values4;
    }

    public void setValues4(String values4) {
        this.values4 = values4;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        MaterialTablePo that = (MaterialTablePo) o;
        return Objects.equals(materielCode, that.materielCode) &&
                Objects.equals(task, that.task) &&
                Objects.equals(boxCode, that.boxCode) &&
                Objects.equals(snCode, that.snCode) &&
                Objects.equals(spareCode, that.spareCode) &&
                Objects.equals(attributeCode, that.attributeCode) &&
                Objects.equals(printer, that.printer) &&
                Objects.equals(printDate, that.printDate) &&
                Objects.equals(printTimes, that.printTimes) &&
                Objects.equals(lastPrinter, that.lastPrinter) &&
                Objects.equals(lastPrintDate, that.lastPrintDate) &&
                Objects.equals(lastPrintTime, that.lastPrintTime) &&
                Objects.equals(createDate, that.createDate) &&
                Objects.equals(updateDate, that.updateDate) &&
                Objects.equals(values1, that.values1) &&
                Objects.equals(values2, that.values2) &&
                Objects.equals(values3, that.values3) &&
                Objects.equals(values4, that.values4);
    }

    @Override
    public int hashCode() {
        return Objects.hash(materielCode, task, boxCode, snCode, spareCode, attributeCode, printer, printDate, printTimes, lastPrinter, lastPrintDate, lastPrintTime, createDate, updateDate, values1, values2, values3, values4);
    }
}
