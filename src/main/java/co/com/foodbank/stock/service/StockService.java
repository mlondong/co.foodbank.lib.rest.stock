package co.com.foodbank.stock.service;

import java.util.Collection;
import java.util.stream.Collectors;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import co.com.foodbank.stock.dto.StockDTO;
import co.com.foodbank.stock.exception.StockException;
import co.com.foodbank.stock.exception.StockNotFoundException;
import co.com.foodbank.stock.repository.StockRepository;
import co.com.foodbank.stock.v1.model.IStock;
import co.com.foodbank.stock.v1.model.Stock;

/**
 * @author mauricio.londono@gmail.com co.com.foodbank.stock.service 21/06/2021
 */
@Service
@Transactional
public class StockService {

    @Autowired
    private StockRepository repository;

    @Autowired
    private ModelMapper modelMapper;



    /**
     * Method to find all.
     * 
     * @return {@code List<IStock>}
     * @throws StockException
     */
    public Collection<IStock> findAll() throws StockException {
        return repository.findAll().stream()
                .map(d -> modelMapper.map(d, IStock.class))
                .collect(Collectors.toList());
    }



    /**
     * Find By ID
     * 
     * @param _id
     * @return {@code IStock}
     */
    public IStock finById(String _id)
            throws StockException, StockNotFoundException {
        return repository.findById(_id)
                .orElseThrow(() -> new StockNotFoundException(_id));
    }



    /**
     * Create Stock.
     * 
     * @param dto
     * @param _id
     * @return {@code IStock}
     */
    public IStock create(StockDTO dto, String _id)
            throws StockException, StockNotFoundException {

        // find COntribution throgth SDK

        // create Product with sdk.

        return repository.save(modelMapper.map(dto, Stock.class));
    }


}
