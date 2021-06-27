package co.com.foodbank.stock.exception;

public class StockNotFoundException extends RuntimeException {

    private static final long serialVersionUID = 1L;

    public StockNotFoundException(String str) {
        super(str);
    }
}
