package co.com.foodbank.stock.service;

import java.util.Collection;
import java.util.Date;
import java.util.stream.Collectors;
import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import co.com.foodbank.contribution.dto.IContribution;
import co.com.foodbank.contribution.sdk.exception.SDKContributionNotFoundException;
import co.com.foodbank.contribution.sdk.exception.SDKContributionServiceException;
import co.com.foodbank.contribution.sdk.exception.SDKContributionServiceIllegalArgumentException;
import co.com.foodbank.contribution.sdk.exception.SDKContributionServiceNotAvailableException;
import co.com.foodbank.contribution.sdk.model.ResponseContributionData;
import co.com.foodbank.contribution.sdk.service.SDKContributionService;
import co.com.foodbank.contribution.state.InHouse;
import co.com.foodbank.product.dto.IProduct;
import co.com.foodbank.product.dto.ProductData;
import co.com.foodbank.product.sdk.exception.SDKProductNotFoundException;
import co.com.foodbank.product.sdk.exception.SDKProductServiceException;
import co.com.foodbank.product.sdk.exception.SDKProductServiceIllegalArgumentException;
import co.com.foodbank.product.sdk.exception.SDKProductServiceNotAvailableException;
import co.com.foodbank.product.sdk.model.ResponseProductData;
import co.com.foodbank.product.sdk.service.SDKProductService;
import co.com.foodbank.stock.dto.IStock;
import co.com.foodbank.stock.dto.StockDTO;
import co.com.foodbank.stock.exception.StockException;
import co.com.foodbank.stock.exception.StockNotFoundException;
import co.com.foodbank.stock.repository.StockRepository;
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

    @Autowired
    @Qualifier("sdkContribution")
    private SDKContributionService sdkContribution;

    @Autowired
    @Qualifier("sdkProduct")
    private SDKProductService sdkProduct;

    private final static String MSG_CONTRIBUTION =
            "Contributions to uptade isnt equals then exist in db.";


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
     * @throws SDKContributionServiceIllegalArgumentException
     * @throws SDKContributionServiceException
     * @throws SDKContributionServiceNotAvailableException
     * @throws JsonProcessingException
     * @throws JsonMappingException
     * @throws SDKProductServiceIllegalArgumentException
     * @throws SDKProductServiceException
     * @throws SDKProductServiceNotAvailableException
     * @throws SDKContributionNotFoundException
     * @throws SDKProductNotFoundException
     * @throws StockStateException
     */
    public IStock create(StockDTO dto)
            throws StockException, StockNotFoundException, JsonMappingException,
            JsonProcessingException,
            SDKContributionServiceNotAvailableException,
            SDKContributionServiceException,
            SDKContributionServiceIllegalArgumentException,
            SDKProductServiceNotAvailableException, SDKProductServiceException,
            SDKProductServiceIllegalArgumentException,
            SDKContributionNotFoundException, SDKProductNotFoundException {


        IContribution contribution = findContribution(dto);
        checkStateInHouse(contribution);
        IProduct product = findProduct(dto);

        return repository.save(buildStock(dto, contribution, product));
    }



    /**
     * Check if the contribution has the state iNHouse
     * 
     * @param contribution
     * @throws StockException
     */
    private void checkStateInHouse(IContribution contribution)
            throws StockException {
        if (!(contribution.getState() instanceof InHouse)) {
            throw new StockException(contribution.getId(),
                    contribution.getState());
        }
    }



    /**
     * Method to build an Stock.
     * 
     * @param dto
     * @param contribution
     * @param product
     * @return {@code Stock}
     */
    private Stock buildStock(StockDTO dto, IContribution contribution,
            IProduct product) {
        Stock stock = new Stock();
        stock.setContribution(contribution);
        stock.setDateStock(dto.getDateStock());
        stock.setProduct(product);
        stock.setQuantity(Long.valueOf(dto.getQuantity()));
        return stock;
    }



    /**
     * Find Product
     * 
     * @param dto
     * @return {@code ResponseProductData}
     * @throws SDKProductServiceException
     * @throws SDKProductServiceIllegalArgumentException
     * @throws JsonMappingException
     * @throws JsonProcessingException
     * @throws SDKProductNotFoundException
     */
    private IProduct findProduct(StockDTO dto)
            throws SDKProductServiceException,
            SDKProductServiceIllegalArgumentException, JsonMappingException,
            JsonProcessingException, SDKProductNotFoundException {

        ResponseProductData resProduct =
                sdkProduct.findProductById(dto.getProduct().getProduct());

        return modelMapper.map(resProduct, ProductData.class);
    }



    /**
     * Find Contribution.
     * 
     * @param dto
     * @return {@code ResponseContributionData}
     * @throws JsonMappingException
     * @throws JsonProcessingException
     * @throws SDKContributionServiceException
     * @throws SDKContributionServiceIllegalArgumentException
     * @throws SDKContributionNotFoundException
     */
    private IContribution findContribution(StockDTO dto)
            throws JsonMappingException, JsonProcessingException,
            SDKContributionServiceException,
            SDKContributionServiceIllegalArgumentException,
            SDKContributionNotFoundException {

        ResponseContributionData resContribution = sdkContribution
                .findContributionById(dto.getContribution().getContribution());

        return modelMapper.map(resContribution, IContribution.class);
    }



    /**
     * Method to update an stock.
     * 
     * @param dto
     * @param _idStock
     * @return {@code IStock}
     * @throws StockException
     * @throws StockNotFoundException
     * @throws SDKContributionNotFoundException
     * @throws SDKContributionServiceIllegalArgumentException
     * @throws SDKContributionServiceException
     * @throws JsonProcessingException
     * @throws JsonMappingException
     * @throws SDKProductNotFoundException
     * @throws SDKProductServiceIllegalArgumentException
     * @throws SDKProductServiceException
     */
    public IStock update(@Valid StockDTO dto,
            @NotNull @NotBlank String _idStock)
            throws StockNotFoundException, StockException, JsonMappingException,
            JsonProcessingException, SDKProductServiceIllegalArgumentException,
            SDKProductNotFoundException, SDKProductServiceException,
            SDKContributionServiceException,
            SDKContributionServiceIllegalArgumentException,
            SDKContributionNotFoundException {

        Stock result = modelMapper.map(this.finById(_idStock), Stock.class);
        result.setQuantity(Long.valueOf(dto.getQuantity()));

        IContribution contribution = findContribution(dto);
        if (!contribution.getId().toString()
                .equals(dto.getContribution().getContribution())) {
            throw new StockException(contribution.getId(), MSG_CONTRIBUTION);
        }

        IProduct product = findProduct(dto);
        result.setProduct(product);
        result.setDateStock(new Date());

        return repository.save(result);
    }



    /**
     * Method to search contribution in stock.
     * 
     * @param idContribution
     * @return {@code  Collection<IStock> }
     * @throws StockException
     */
    public Collection<IStock> searchContribution(String idContribution)
            throws StockNotFoundException, StockException {

        return repository.findContribution(idContribution).stream()
                .filter(d -> d.getContribution().getId().equals(idContribution))
                .collect(Collectors.toList());

    }



    /**
     * Method to search products in stock.
     * 
     * @param idContribution
     * @return {@code  Collection<IStock> }
     */
    public Collection<IStock> searchProducts(String name)
            throws StockNotFoundException {
        return repository.searchProducts(name).stream()
                .map(d -> modelMapper.map(d, IStock.class))
                .collect(Collectors.toList());
    }

    /**
     * Method to find products in stock by Id.
     * 
     * @param idContribution
     * @return {@code  Collection<IStock> }
     */
    public Collection<IStock> findProductById(String product) {
        return repository.findProduct(product).stream()
                .filter(d -> d.getProduct().getId().equals(product))
                .collect(Collectors.toList());
    }


}
