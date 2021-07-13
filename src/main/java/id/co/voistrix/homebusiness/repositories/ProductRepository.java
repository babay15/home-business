package id.co.voistrix.homebusiness.repositories;

import id.co.voistrix.homebusiness.entities.Product;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import javax.websocket.server.PathParam;
import java.util.List;

public interface ProductRepository extends CrudRepository<Product, String> {

    @Query("SELECT p FROM Product p WHERE LOWER(p.productName) LIKE %:productName%")
    public List<Product> findProductByNameLike(@PathParam("productName") String productName);

    @Query("SELECT p FROM Product p WHERE p.productColor = :productColor")
    public List<Product> findProductByProductColor(@PathParam("productColor") String productColor);
}
