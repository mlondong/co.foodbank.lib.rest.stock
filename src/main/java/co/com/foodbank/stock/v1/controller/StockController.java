package co.com.foodbank.stock.v1.controller;

import java.util.Collection;
import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import co.com.foodbank.contribution.sdk.exception.SDKContributionNotFoundException;
import co.com.foodbank.contribution.sdk.exception.SDKContributionServiceException;
import co.com.foodbank.contribution.sdk.exception.SDKContributionServiceIllegalArgumentException;
import co.com.foodbank.contribution.sdk.exception.SDKContributionServiceNotAvailableException;
import co.com.foodbank.product.sdk.exception.SDKProductNotFoundException;
import co.com.foodbank.product.sdk.exception.SDKProductServiceException;
import co.com.foodbank.product.sdk.exception.SDKProductServiceIllegalArgumentException;
import co.com.foodbank.product.sdk.exception.SDKProductServiceNotAvailableException;
import co.com.foodbank.stock.dto.StockDTO;
import co.com.foodbank.stock.dto.interfaces.IStock;
import co.com.foodbank.stock.exception.StockException;
import co.com.foodbank.stock.exception.StockNotFoundException;
import co.com.foodbank.stock.service.StockService;

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
     * @return {@code IStock}
     * @throws StockException
     * @throws StockNotFoundException
     * @throws SDKContributionServiceIllegalArgumentException
     * @throws SDKContributionServiceException
     * @throws SDKContributionServiceNotAvailableException
     * @throws JsonProcessingException
     * @throws JsonMappingException
     * @throws SDKProductServiceIllegalArgumentException
     * @throws SDKProductServiceException
     * @throws SDKProductServiceNotAvailableException
     * @throws SDKProductNotFoundException
     * @throws SDKContributionNotFoundException
     */
    public IStock create(StockDTO dto) throws StockNotFoundException,
            StockException, JsonMappingException, JsonProcessingException,
            SDKContributionServiceNotAvailableException,
            SDKContributionServiceException,
            SDKContributionServiceIllegalArgumentException,
            SDKProductServiceNotAvailableException, SDKProductServiceException,
            SDKProductServiceIllegalArgumentException,
            SDKContributionNotFoundException, SDKProductNotFoundException {
        return service.create(dto);
    }



    /**
     * Method to update stock.
     * 
     * @param dto
     * @param _idStock
     * @return {@code IStock}
     * @throws SDKProductServiceException
     * @throws SDKProductNotFoundException
     * @throws SDKProductServiceIllegalArgumentException
     * @throws StockException
     * @throws JsonProcessingException
     * @throws StockNotFoundException
     * @throws JsonMappingException
     * @throws SDKContributionNotFoundException
     * @throws SDKContributionServiceIllegalArgumentException
     * @throws SDKContributionServiceException
     */
    public IStock update(@Valid StockDTO dto,
            @NotNull @NotBlank String _idStock) throws JsonMappingException,
            StockNotFoundException, JsonProcessingException, StockException,
            SDKProductServiceIllegalArgumentException,
            SDKProductNotFoundException, SDKProductServiceException,
            SDKContributionServiceException,
            SDKContributionServiceIllegalArgumentException,
            SDKContributionNotFoundException {
        return service.update(dto, _idStock);
    }



    /**
     * Method to search contribution in stock.
     * 
     * @param idContribution
     * @return {@code  Collection<IStock> }
     * @throws StockException
     */
    public Collection<IStock> searchContribution(
            @NotNull @NotBlank String idContribution)
            throws StockNotFoundException, StockException {
        return service.searchContribution(idContribution);
    }


    /**
     * Method to search products in stock.
     * 
     * @param idContribution
     * @return {@code  Collection<IStock> }
     */
    public Collection<IStock> searchProducts(@NotNull @NotBlank String name)
            throws StockNotFoundException {
        return service.searchProducts(name);
    }


    /**
     * Method to find products in stock by Id.
     * 
     * @param idContribution
     * @return {@code  Collection<IStock> }
     */
    public Collection<IStock> findProductById(
            @NotNull @NotBlank String product) {
        return service.findProductById(product);
    }
}
