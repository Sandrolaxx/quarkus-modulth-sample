package com.aktie.sale.domain.entities.dto;

import java.time.LocalDateTime;

import com.aktie.user.domain.entities.dto.UserDTO;

/**
 *
 * @author SRamos
 */
public class SaleDTO {

    private String id;
    
    private UserDTO user;

    private String userId;

    private String productId;

    // private ProductDTO product;

    private LocalDateTime createdAt;

    private LocalDateTime paidAt;

    public String getProductId() {
        return productId;
    }

    public void setProductId(String productId) {
        this.productId = productId;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public UserDTO getUser() {
        return user;
    }

    public void setUser(UserDTO user) {
        this.user = user;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public LocalDateTime getPaidAt() {
        return paidAt;
    }

    public void setPaidAt(LocalDateTime paidAt) {
        this.paidAt = paidAt;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

}
