package co.com.foodbank.stock.exception;

import co.com.foodbank.contribution.state.IStateContribution;

/**
 * @author mauricio.londono@gmail.com co.com.foodbank.stock.exception 22/06/2021
 */
public class StockException extends Exception {

    private static final long serialVersionUID = 1L;
    private IStateContribution state;
    private final static String MSG_STATE = "State not valid.";



    public StockException(StockException ex) {
        super(ex);
    }

    /**
     * @param id
     * @param _state
     */
    public StockException(String id, IStateContribution _state) {
        super(new String(_state.toString() + " " + MSG_STATE + " in " + id));
        this.setState(_state);
    }

    /**
     * @param collection
     */
    public StockException(String message, String err) {
        super(new String(err + " " + message));
    }



    public IStateContribution getState() {
        return state;
    }

    public void setState(IStateContribution state) {
        this.state = state;
    }



}
