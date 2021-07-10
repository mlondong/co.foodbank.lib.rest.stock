package co.com.foodbank.stock.repository;

import javax.annotation.Generated;
import com.querydsl.core.types.PathMetadata;
import com.querydsl.core.types.dsl.EntityPathBase;
import com.querydsl.core.types.dsl.StringPath;
import co.com.foodbank.stock.v1.model.Stock;

/**
 * @author https://www.baeldung.com/queries-in-spring-data-mongodb
 *         co.com.foodbank.stock.repository 10/07/2021
 */
@Generated("com.com.foodbank.stock")
public class QStockRepository extends EntityPathBase<Stock> {

    private static final long serialVersionUID = 1792630833L;

    public static final QStockRepository stock = new QStockRepository("stock");

    /** DEFINED ID STRING PATH FOR USE in CONTRIBUTION */
    public final StringPath id = createString("id");

    /** DEFINED ID STRING PATH FOR USE in CONTRIBUTION */
    public final StringPath description = createString("description");


    public QStockRepository(Class<? extends Stock> type,
            PathMetadata metadata) {
        super(type, metadata);
    }

    public QStockRepository(String variable) {
        super(Stock.class, variable);
    }

}
