package co.com.foodbank.stock.v1.model;

import java.util.Date;
import co.com.foodbank.contribution.dto.IContribution;
import co.com.foodbank.product.dto.IProduct;

/**
 * @author mauricio.londono@gmail.com co.com.foodbank.stock.v1.model 21/06/2021
 */
public interface IStock {

    public String getId();

    public IContribution getContribution();

    public IProduct getProduct();

    public Date getDateStock();

    public Long getQuantity();


}
