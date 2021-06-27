package co.com.foodbank.stock.v1.model;

import java.util.Date;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import co.com.foodbank.contribution.dto.IContribution;
import co.com.foodbank.product.dto.IProduct;

@Document(collection = "Stock")
public class Stock implements IStock {

    @Id
    private String id;
    private IContribution contribution;
    private IProduct product;
    private Date dateStock;
    private Long quantity;


    /**
     * Default constructor.
     */
    public Stock() {}

    @Override
    public Long getQuantity() {
        return quantity;
    }

    public void setQuantity(Long quantity) {
        this.quantity = quantity;
    }



    public void setId(String id) {
        this.id = id;
    }

    public void setContribution(IContribution contribution) {
        this.contribution = contribution;
    }

    public void setProduct(IProduct product) {
        this.product = product;
    }

    public void setDateStock(Date dateStock) {
        this.dateStock = dateStock;
    }



    @Override
    public String getId() {
        return id;
    }

    @Override
    public IContribution getContribution() {
        return contribution;
    }

    @Override
    public IProduct getProduct() {
        return product;
    }

    @Override
    public Date getDateStock() {
        return dateStock;
    }



}
