package id.co.voistrix.homebusiness.dto.product;

import lombok.Data;
import org.springframework.format.annotation.NumberFormat;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;
import java.math.BigDecimal;

@Data
public class ProductRequest {
    @NotEmpty(message = "ID Produk harus diisi.")
    @Size(min = 5, max = 5, message = "ID Produk hanya boleh 5 karakter.")
    private String productId;
    @NotEmpty(message = "Nama produk harus diisi.")
    @Size(min=5, message = "Nama produk minimal 5 karakter")
    private String productName;
    @NotEmpty(message = "Warna produk harus diisi.")
    private String productColor;
    private String productImage;
    @NotEmpty(message = "Harga jual harus diisi.")
    private String productSalePrice;
    @NotEmpty(message = "Harga modal harus diisi.")
    private String productOrderCost;
    @NotEmpty(message = "Kuantitas harus diisi.")
    private String productQuantity;
    @NotEmpty(message = "Tipe produk harus diisi.")
    private String productType;
    private String productDescription;
}
