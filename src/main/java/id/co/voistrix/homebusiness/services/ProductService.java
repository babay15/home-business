package id.co.voistrix.homebusiness.services;

import id.co.voistrix.homebusiness.dto.product.ProductRequest;
import id.co.voistrix.homebusiness.dto.WebResponse;
import org.springframework.http.ResponseEntity;

public interface ProductService {
    public ResponseEntity<WebResponse> saveProduct(ProductRequest productRequest);

    public ResponseEntity<WebResponse> getProduct(String productId);

    public ResponseEntity<WebResponse> updateProduct(ProductRequest productRequest);

    public ResponseEntity<WebResponse> getProductByNameLike(String productName);

    public ResponseEntity<WebResponse> getProductByColor(String productColor);
}
