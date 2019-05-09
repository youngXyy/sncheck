package com.ruijie.sncheck.dao.po;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.Date;
import java.util.Objects;

/**
 * MaterialTablePo
 *
 * @author {yuanwei}
 * @date 2019/5/6 22:01
 */
@Entity
@Table(name = "material_table", schema = "ruijiesncheck", catalog = "")
@EntityListeners(AuditingEntityListener.class)
public class MaterialTablePo {
    private Integer id;
    private String materielCode;
    private String task;
    private String boxCode;
    private String snCode;
    private String spareCode;
    private String attributeCode;
    private String printer;
    private Timestamp printDate;
    private String printTimes;
    private String lastPrinter;
    private Timestamp lastPrintDate;
    private String lastPrintTime;
    private Date createDate;
    private String values1;
    private String values2;
    private String values3;
    private String values4;

    @Id
    @Column(name = "id", nullable = false)
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

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

    @Basic
    @Column(name = "box_code", nullable = false, length = 64)
    public String getBoxCode() {
        return boxCode;
    }

    public void setBoxCode(String boxCode) {
        this.boxCode = boxCode;
    }

    @Basic
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
    public Timestamp getPrintDate() {
        return printDate;
    }

    public void setPrintDate(Timestamp printDate) {
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
    public Timestamp getLastPrintDate() {
        return lastPrintDate;
    }

    public void setLastPrintDate(Timestamp lastPrintDate) {
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
    @Column(name = "create_date", nullable = true)
    @CreatedDate
    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
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
        return id == that.id &&
                Objects.equals(materielCode, that.materielCode) &&
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
                Objects.equals(values1, that.values1) &&
                Objects.equals(values2, that.values2) &&
                Objects.equals(values3, that.values3) &&
                Objects.equals(values4, that.values4);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, materielCode, task, boxCode, snCode, spareCode, attributeCode, printer, printDate, printTimes, lastPrinter, lastPrintDate, lastPrintTime, createDate, values1, values2, values3, values4);
    }
}
