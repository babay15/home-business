package id.co.voistrix.homebusiness.controllers;

import id.co.voistrix.homebusiness.dto.WebResponse;
import id.co.voistrix.homebusiness.dto.product.ProductRequest;
import id.co.voistrix.homebusiness.services.ProductServiceImpl;
import id.co.voistrix.homebusiness.util.VoistrictUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/product")
public class ProductController {
    @Autowired
    ProductServiceImpl productService;

    @PostMapping(value = "/save", produces = "application/json", consumes = "application/json")
    public ResponseEntity<WebResponse> saveProduct(@Valid @RequestBody ProductRequest productRequest, Errors error) {
        /**
         * Validation Check
         */
        if(error.hasErrors()){
            return VoistrictUtil.createValidationErrorResponse(error);
        }

        /**
         * Get response from service
         */
        return productService.saveProduct(productRequest);
    }

    @GetMapping(value = "/{productId}", produces = "application/json")
    public ResponseEntity<WebResponse> getProduct(@PathVariable String productId) {
        /**
         * Get response from service
         */
        return productService.getProduct(productId);
    }

    @PostMapping(value = "/update", produces = "application/json", consumes = "application/json")
    public ResponseEntity<WebResponse> updateProduct(@Valid @RequestBody ProductRequest productRequest, Errors error) {
        /**
         * Validation Check
         */
        if(error.hasErrors()){
            return VoistrictUtil.createValidationErrorResponse(error);
        }

        /**
         * Get response from service
         */
        return productService.updateProduct(productRequest);
    }

    @GetMapping(value = "/name/{productName}", produces = "application/json")
    public ResponseEntity<WebResponse> getProductByNameLike(@PathVariable String productName) {
        /**
         * Get response from service
         */
        return productService.getProductByNameLike(productName);
    }

    @GetMapping(value = "/color/{productColor}", produces = "application/json")
    public ResponseEntity<WebResponse> getProductByColor(@PathVariable String productColor) {
        /**
         * Get response from service
         */
        return productService.getProductByColor(productColor);
    }
}
