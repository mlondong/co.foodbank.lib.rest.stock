package co.com.foodbank.stock.exception;

/**
 * @author mauricio.londono@gmail.com co.com.foodbank.stock.exception 22/06/2021
 */
public class StockException extends Exception {

    /**
     * 
     */
    private static final long serialVersionUID = 1L;

    public StockException(StockException ex) {
        super(ex);
    }

}
