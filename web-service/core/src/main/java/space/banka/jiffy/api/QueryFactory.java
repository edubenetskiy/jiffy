package space.banka.jiffy.api;

public interface QueryFactory {

    /**
     * @throws space.banka.jiffy.api.exceptions.InvalidQueryException if the query expression is invalid.
     */
    Query createQuery(String queryExpression);
}
