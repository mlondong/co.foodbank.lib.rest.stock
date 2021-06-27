package co.com.foodbank.stock.v1.controller;

import java.util.Collection;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import co.com.foodbank.stock.dto.StockDTO;
import co.com.foodbank.stock.exception.StockException;
import co.com.foodbank.stock.exception.StockNotFoundException;
import co.com.foodbank.stock.service.StockService;
import co.com.foodbank.stock.v1.model.IStock;

/**
 * @author mauricio.londono@gmail.com co.com.foodbank.stock.v1.controller
 *         21/06/2021
 */
@Controller
public class StockController {

    @Autowired
    private StockService service;



    /**
     * Method to find all stocks.
     * 
     * @return
     * @throws StockException
     */
    public Collection<IStock> findAll() throws StockException {
        return service.findAll();
    }



    /**
     * Method to find by ID
     * 
     * @param _id
     * @return {@code IStock}
     * @throws StockException
     * @throws StockNotFoundException
     */
    public IStock findById(@Valid String _id)
            throws StockNotFoundException, StockException {
        return service.finById(_id);
    }



    /**
     * Create Stock.
     * 
     * @param dto
     * @param _id
     * @return {@code IStock}
     * @throws StockException
     * @throws StockNotFoundException
     */
    public IStock create(StockDTO dto, String _id)
            throws StockNotFoundException, StockException {
        return service.create(dto, _id);
    }
}
