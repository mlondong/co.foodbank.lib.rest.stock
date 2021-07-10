package co.com.foodbank.stock.repository;

import java.util.Collection;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.stereotype.Repository;
import co.com.foodbank.stock.exception.StockNotFoundException;
import co.com.foodbank.stock.v1.model.Stock;

/**
 * @author mauricio.londono@gmail.com co.com.foodbank.stock.repository
 *         21/06/2021
 */
@Repository
public interface StockRepository extends MongoRepository<Stock, String>,
        QuerydslPredicateExecutor<Stock> {

    @Query("{'contribution._id': ?0}")
    Collection<Stock> searchContribution(String id)
            throws StockNotFoundException;



    @Query("{'product.name':{'$regex':'?0','$options':'i'}}")
    Collection<Stock> searchProducts(String product)
            throws StockNotFoundException;

}
