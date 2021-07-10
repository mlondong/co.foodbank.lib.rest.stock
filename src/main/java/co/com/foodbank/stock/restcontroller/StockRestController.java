package co.com.foodbank.stock.restcontroller;

import java.util.Collection;
import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
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
import co.com.foodbank.stock.exception.StockException;
import co.com.foodbank.stock.exception.StockNotFoundException;
import co.com.foodbank.stock.v1.controller.StockController;
import co.com.foodbank.stock.v1.model.IStock;
import co.com.foodbank.stock.v1.model.Stock;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;

/**
 * @author mauricio.londono@gmail.com co.com.foodbank.stock.restcontroller
 *         21/06/2021
 */

@RestController
@RequestMapping(path = "stock/")
@Tag(name = "Stock", description = "the stock API")
@Validated
public class StockRestController {

    @Autowired
    private StockController controller;



    /**
     * Method to find by Products in Stock.
     * 
     * @return {@code Collection<IStock>}
     * @throws StockException
     */
    @Operation(summary = "Find Products in Stock.")
    @ApiResponses(
            value = {
                    @ApiResponse(responseCode = "200",
                            description = "Found the stock",
                            content = {
                                    @Content(mediaType = "application/json")}),
                    @ApiResponse(responseCode = "500",
                            description = "Service not available.",
                            content = @Content),
                    @ApiResponse(responseCode = "400",
                            description = "Bad request.", content = @Content)})
    @GetMapping(value = "/searchProducts/name/{name}")
    public Collection<IStock> searchProducts(
            @PathVariable("name") @NotNull @NotBlank String name)
            throws StockException, StockNotFoundException {
        return controller.searchProducts(name);

    }



    /**
     * Method to find by Contributions in Stock.
     * 
     * @return {@code Collection<IStock>}
     * @throws StockException
     */
    @Operation(summary = "Find Contribution in Stock.")
    @ApiResponses(
            value = {
                    @ApiResponse(responseCode = "200",
                            description = "Found the stock",
                            content = {
                                    @Content(mediaType = "application/json")}),
                    @ApiResponse(responseCode = "500",
                            description = "Service not available.",
                            content = @Content),
                    @ApiResponse(responseCode = "400",
                            description = "Bad request.", content = @Content)})
    @GetMapping(value = "/searchContribution/id/{id-contribution}")
    public Collection<IStock> searchContribution(
            @PathVariable("id-contribution") @NotNull @NotBlank String idContribution)
            throws StockException, StockNotFoundException {
        return controller.searchContribution(idContribution);

    }



    /**
     * Method to find all stocks.
     * 
     * @return {@code Collection<IStock>}
     * @throws StockException
     */
    @Operation(summary = "Find all stocks.")
    @ApiResponses(
            value = {
                    @ApiResponse(responseCode = "200",
                            description = "Found the stock",
                            content = {
                                    @Content(mediaType = "application/json")}),
                    @ApiResponse(responseCode = "500",
                            description = "Service not available.",
                            content = @Content),
                    @ApiResponse(responseCode = "400",
                            description = "Bad request.", content = @Content)})
    @GetMapping(value = "/findAll")
    public Collection<IStock> findAll() throws StockException {
        return controller.findAll();

    }



    /**
     * Method to find a stock by id.
     * 
     * @param _id
     * @return {@code IStock}
     * @throws StockException
     * @throws StockNotFoundException
     */
    @Operation(summary = "Find stock by ID")
    @ApiResponses(
            value = {
                    @ApiResponse(responseCode = "200",
                            description = "Found the stock.",
                            content = {
                                    @Content(mediaType = "application/json")}),
                    @ApiResponse(responseCode = "400",
                            description = "Bad request.", content = @Content),
                    @ApiResponse(responseCode = "404",
                            description = "Stock not found",
                            content = @Content)})
    @GetMapping(value = "/findById/{id}")
    public IStock findById(@PathVariable("id") @Valid String _id)
            throws StockNotFoundException, StockException {
        return controller.findById(_id);

    }

    /***************************************************************************************/

    /**
     * Method to Create an Stock.
     * 
     * @param dto
     * @return {@code IStock}
     * @throws StockNotFoundException
     * @throws StockException
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
    @Operation(summary = "Create an stock of contribution", description = "",
            tags = {"stock"})
    @ApiResponses(
            value = {
                    @ApiResponse(responseCode = "201",
                            description = "Stock created",
                            content = @Content(schema = @Schema(
                                    implementation = Stock.class))),
                    @ApiResponse(responseCode = "400",
                            description = "Invalid input"),
                    @ApiResponse(responseCode = "409",
                            description = "Stock already exists")})
    @PostMapping(value = "/create",
            consumes = {MediaType.APPLICATION_JSON_VALUE},
            produces = {MediaType.APPLICATION_JSON_VALUE})
    @ResponseBody
    public IStock create(@RequestBody @Valid StockDTO dto)
            throws StockNotFoundException, StockException, JsonMappingException,
            JsonProcessingException,
            SDKContributionServiceNotAvailableException,
            SDKContributionServiceException,
            SDKContributionServiceIllegalArgumentException,
            SDKProductServiceNotAvailableException, SDKProductServiceException,
            SDKProductServiceIllegalArgumentException,
            SDKContributionNotFoundException, SDKProductNotFoundException {
        return controller.create(dto);
    }



    /***************************************************************************************/
    /**
     * Method to Update an Stock.
     * 
     * @param dto
     * @return {@code IStock}
     * @throws StockNotFoundException
     * @throws StockException
     * @throws SDKProductServiceException
     * @throws SDKProductNotFoundException
     * @throws SDKProductServiceIllegalArgumentException
     * @throws JsonProcessingException
     * @throws JsonMappingException
     */
    @Operation(summary = "Update a stock", description = "", tags = {"stock"})
    @ApiResponses(
            value = {
                    @ApiResponse(responseCode = "201",
                            description = "Stock updated",
                            content = @Content(schema = @Schema(
                                    implementation = Stock.class))),
                    @ApiResponse(responseCode = "400",
                            description = "Invalid input"),
                    @ApiResponse(responseCode = "409",
                            description = "Stock already exists")})
    @PutMapping(value = "/update/id-stock/{id}",
            consumes = {MediaType.APPLICATION_JSON_VALUE},
            produces = {MediaType.APPLICATION_JSON_VALUE})
    @ResponseBody
    public IStock update(@RequestBody @Valid StockDTO dto,
            @PathVariable("id") @NotNull @NotBlank String _idStock)
            throws StockNotFoundException, StockException, JsonMappingException,
            JsonProcessingException, SDKProductServiceIllegalArgumentException,
            SDKProductNotFoundException, SDKProductServiceException {
        return controller.update(dto, _idStock);
    }


}
