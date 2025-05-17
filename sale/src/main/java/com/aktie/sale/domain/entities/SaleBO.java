package com.aktie.sale.domain.entities;

import java.time.LocalDateTime;

import com.aktie.sale.domain.entities.vo.CreatedAtVO;
import com.aktie.sale.domain.entities.vo.UuidVO;

/**
 *
 * @author SRamos
 */
public class SaleBO {

    private UuidVO id;

    private String productId;

    private String userId;

    private CreatedAtVO createdAt;

    private LocalDateTime paidAt;

    public SaleBO(UuidVO id, String productId, String userId, CreatedAtVO createdAt, LocalDateTime paidAt) {
        this.id = id;
        this.productId = productId;
        this.userId = userId;
        this.createdAt = createdAt;
        this.paidAt = paidAt;
    }

    public UuidVO getId() {
        return id;
    }

    public void setId(UuidVO id) {
        this.id = id;
    }

    public String getProductId() {
        return productId;
    }

    public void setProductId(String productId) {
        this.productId = productId;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public CreatedAtVO getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(CreatedAtVO createdAt) {
        this.createdAt = createdAt;
    }

    public LocalDateTime getPaidAt() {
        return paidAt;
    }

    public void setPaidAt(LocalDateTime paidAt) {
        this.paidAt = paidAt;
    }

}
