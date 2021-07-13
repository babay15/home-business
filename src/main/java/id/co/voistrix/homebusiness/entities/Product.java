package id.co.voistrix.homebusiness.entities;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

@Data
@Entity
@Table(name = "tbl_product")
public class Product implements Serializable {
    @Id
    @Column(nullable = false, length = 5)
    private String productId;

    @Column(nullable = false)
    private String productName;

    @Column(nullable = false)
    private String productColor;

    private String productImage;

    @Column(nullable = false)
    private BigDecimal productSalePrice;

    @Column(nullable = false)
    private BigDecimal productOrderCost;

    @Column(nullable = false)
    private Integer productQuantity;

    @Column(nullable = false)
    private String productType;

    private String productDescription;

    @Column(nullable = false)
    private Date createdAt;

    private Date updatedAt;
}
