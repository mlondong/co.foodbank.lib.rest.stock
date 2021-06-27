package co.com.foodbank.stock.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;
import co.com.foodbank.stock.v1.model.Stock;

/**
 * @author mauricio.londono@gmail.com co.com.foodbank.stock.repository
 *         21/06/2021
 */
@Repository
public interface StockRepository extends MongoRepository<Stock, String> {

}
