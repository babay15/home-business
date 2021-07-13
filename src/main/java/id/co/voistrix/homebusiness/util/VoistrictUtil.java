package id.co.voistrix.homebusiness.util;

import id.co.voistrix.homebusiness.dto.WebResponse;
import id.co.voistrix.homebusiness.dto.product.ProductResponse;
import id.co.voistrix.homebusiness.entities.Product;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;

import java.text.DecimalFormat;
import java.text.SimpleDateFormat;

public class VoistrictUtil {
    public final static SimpleDateFormat smFormat = new SimpleDateFormat("dd-MM-yyyy");
    public final static SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS");
    public final static DecimalFormat df = new DecimalFormat("#,##0.00");

    public final static ProductResponse createProductResponse(Product product) {
        ProductResponse productResponse = new ProductResponse();
        productResponse.setProductId(product.getProductId());
        productResponse.setProductName(product.getProductName());
        productResponse.setProductImage(product.getProductImage());
        productResponse.setProductSalePrice(df.format(product.getProductSalePrice()));
        productResponse.setProductOrderCost(df.format(product.getProductOrderCost()));
        productResponse.setProductQuantity(product.getProductQuantity().toString());
        productResponse.setProductType(product.getProductType());
        productResponse.setProductColor(product.getProductColor());
        productResponse.setProductDescription(product.getProductDescription());
        return productResponse;
    }

    public final static ResponseEntity<WebResponse> createValidationErrorResponse(Errors error) {
        WebResponse webResponse = new WebResponse();
        webResponse.setCode(HttpStatus.BAD_REQUEST.value());
        webResponse.setStatus(HttpStatus.BAD_REQUEST.getReasonPhrase());
        webResponse.setData(error.getAllErrors().get(0).getDefaultMessage());
        return new ResponseEntity<>(webResponse, HttpStatus.BAD_REQUEST);
    }
}
