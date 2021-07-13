package id.co.voistrix.homebusiness.dto.product;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class ProductResponse {
    private String productId;
    private String productName;
    private String productColor;
    private String productImage;
    private String productSalePrice;
    private String productOrderCost;
    private String productQuantity;
    private String productType;
    private String productDescription;
}
