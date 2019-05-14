package com.ruijie.sncheck.dao.po;

import javax.persistence.Column;
import javax.persistence.Id;
import java.io.Serializable;
import java.util.Objects;

/**
 * MaterialTablePoPK
 *
 * @author {yuanwei}
 * @date 2019/5/12 20:18
 */
public class MaterialTablePoPK implements Serializable {
    private String boxCode;
    private String snCode;

    @Column(name = "box_code", nullable = false, length = 64)
    @Id
    public String getBoxCode() {
        return boxCode;
    }

    public void setBoxCode(String boxCode) {
        this.boxCode = boxCode;
    }

    @Column(name = "sn_code", nullable = false, length = 64)
    @Id
    public String getSnCode() {
        return snCode;
    }

    public void setSnCode(String snCode) {
        this.snCode = snCode;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        MaterialTablePoPK that = (MaterialTablePoPK) o;
        return Objects.equals(boxCode, that.boxCode) &&
                Objects.equals(snCode, that.snCode);
    }

    @Override
    public int hashCode() {
        return Objects.hash(boxCode, snCode);
    }
}
