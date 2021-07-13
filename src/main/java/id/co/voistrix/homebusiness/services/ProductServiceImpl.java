package id.co.voistrix.homebusiness.services;

import id.co.voistrix.homebusiness.dto.product.ProductRequest;
import id.co.voistrix.homebusiness.dto.product.ProductResponse;
import id.co.voistrix.homebusiness.entities.Product;
import id.co.voistrix.homebusiness.dto.WebResponse;
import id.co.voistrix.homebusiness.repositories.ProductRepository;
import id.co.voistrix.homebusiness.util.VoistrictUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class ProductServiceImpl implements ProductService {

    @Autowired
    private ProductRepository productRepository;

    @Override
    public ResponseEntity<WebResponse> saveProduct(ProductRequest productRequest) {
        WebResponse webResponse = new WebResponse();
        try{
            Optional<Product> productCheck = productRepository.findById(productRequest.getProductId());
            if (productCheck.isPresent()) {
                webResponse.setCode(HttpStatus.CONFLICT.value());
                webResponse.setStatus(HttpStatus.CONFLICT.getReasonPhrase());
                webResponse.setData("Produk dengan ID " + productRequest.getProductId() + " sudah ada.");
                return new ResponseEntity<>(webResponse, HttpStatus.CONFLICT);
            }

            /**
             * Save Product to DB
             */

            Product newProduct = new Product();
            newProduct.setProductId(productRequest.getProductId());
            newProduct.setProductName(productRequest.getProductName());
            newProduct.setProductImage(productRequest.getProductImage());
            newProduct.setProductSalePrice(new BigDecimal(productRequest.getProductSalePrice()));
            newProduct.setProductOrderCost(new BigDecimal(productRequest.getProductOrderCost()));
            newProduct.setProductQuantity(new Integer(productRequest.getProductQuantity()));
            newProduct.setProductType(productRequest.getProductType());
            newProduct.setProductColor(productRequest.getProductColor());
            newProduct.setProductDescription(productRequest.getProductDescription());
            newProduct.setCreatedAt(new Date());
            newProduct.setUpdatedAt(null);
            productRepository.save(newProduct);

            /**
             * Create Response
             */
            webResponse.setCode(HttpStatus.CREATED.value());
            webResponse.setStatus(HttpStatus.CREATED.getReasonPhrase());
            webResponse.setData(VoistrictUtil.createProductResponse(newProduct));
        } catch (Exception e) {
            if (e instanceof NumberFormatException){
                webResponse.setCode(HttpStatus.BAD_REQUEST.value());
                webResponse.setStatus(HttpStatus.BAD_REQUEST.getReasonPhrase());
                webResponse.setData("Mohon mengisi harga-harga dan kuantitas dengan angka.");
                return new ResponseEntity<>(webResponse, HttpStatus.BAD_REQUEST);
            }
        }

        return new ResponseEntity<>(webResponse, HttpStatus.CREATED);
    }

    @Override
    public ResponseEntity<WebResponse> getProduct(String productId) {
        WebResponse webResponse;
        Optional<Product> productCheck = productRepository.findById(productId);
        if (!productCheck.isPresent()) {
            webResponse = new WebResponse();
            webResponse.setCode(HttpStatus.NOT_FOUND.value());
            webResponse.setStatus(HttpStatus.NOT_FOUND.getReasonPhrase());
            webResponse.setData("Produk dengan ID " + productId + " tidak ditemukan.");
            return new ResponseEntity<>(webResponse, HttpStatus.NOT_FOUND);
        }

        /**
         * Create Response
         */
        webResponse = new WebResponse();
        webResponse.setCode(HttpStatus.FOUND.value());
        webResponse.setStatus(HttpStatus.FOUND.getReasonPhrase());
        webResponse.setData(VoistrictUtil.createProductResponse(productCheck.get()));
        return new ResponseEntity<>(webResponse, HttpStatus.FOUND);
    }

    @Override
    public ResponseEntity<WebResponse> updateProduct(ProductRequest productRequest) {
        WebResponse webResponse;
        Optional<Product> productCheck = productRepository.findById(productRequest.getProductId());
        if (!productCheck.isPresent()) {
            webResponse = new WebResponse();
            webResponse.setCode(HttpStatus.NOT_FOUND.value());
            webResponse.setStatus(HttpStatus.NOT_FOUND.getReasonPhrase());
            webResponse.setData("Produk dengan ID " + productRequest.getProductId() + " tidak ditemukan.");
            return new ResponseEntity<>(webResponse, HttpStatus.NOT_FOUND);
        }

        Product product = productCheck.get();
        product.setProductName(productRequest.getProductName());
        product.setProductImage(productRequest.getProductImage());
        product.setProductSalePrice(new BigDecimal(productRequest.getProductSalePrice()));
        product.setProductOrderCost(new BigDecimal(productRequest.getProductOrderCost()));
        product.setProductQuantity(new Integer(productRequest.getProductQuantity()));
        product.setProductType(productRequest.getProductType());
        product.setProductColor(productRequest.getProductColor());
        product.setProductDescription(productRequest.getProductDescription());
        product.setUpdatedAt(new Date());
        productRepository.save(product);

        /**
         * Create Response
         */
        webResponse = new WebResponse();
        webResponse.setCode(HttpStatus.CREATED.value());
        webResponse.setStatus(HttpStatus.CREATED.getReasonPhrase());
        webResponse.setData(VoistrictUtil.createProductResponse(product));
        return new ResponseEntity<>(webResponse, HttpStatus.CREATED);
    }

    @Override
    public ResponseEntity<WebResponse> getProductByNameLike(String productName) {
        WebResponse webResponse;
        List<Product> productList = productRepository.findProductByNameLike(productName.toLowerCase());
        if (productList==null || productList.size()<=0) {
            webResponse = new WebResponse();
            webResponse.setCode(HttpStatus.NOT_FOUND.value());
            webResponse.setStatus(HttpStatus.NOT_FOUND.getReasonPhrase());
            webResponse.setData("Produk dengan nama " + productName + " tidak ditemukan.");
            return new ResponseEntity<>(webResponse, HttpStatus.NOT_FOUND);
        };

        /**
         * Create Response
         */
        List<ProductResponse> listProductResponse = new ArrayList<>();
        for(Product product : productList) {
            listProductResponse.add(VoistrictUtil.createProductResponse(product));
        }
        webResponse = new WebResponse();
        webResponse.setCode(HttpStatus.FOUND.value());
        webResponse.setStatus(HttpStatus.FOUND.getReasonPhrase());
        webResponse.setData(listProductResponse);
        return new ResponseEntity<>(webResponse, HttpStatus.FOUND);
    }

    @Override
    public ResponseEntity<WebResponse> getProductByColor(String productColor) {
        WebResponse webResponse;
        List<Product> productList = productRepository.findProductByProductColor(productColor);

        /**
         * Create Response
         */
        List<ProductResponse> listProductResponse = new ArrayList<>();
        for(Product product : productList) {
            listProductResponse.add(VoistrictUtil.createProductResponse(product));
        }
        webResponse = new WebResponse();
        webResponse.setCode(HttpStatus.FOUND.value());
        webResponse.setStatus(HttpStatus.FOUND.getReasonPhrase());
        webResponse.setData(listProductResponse);
        return new ResponseEntity<>(webResponse, HttpStatus.FOUND);
    }


}
