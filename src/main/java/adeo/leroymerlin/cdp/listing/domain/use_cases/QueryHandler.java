package adeo.leroymerlin.cdp.listing.domain.use_cases;

public interface QueryHandler<QUERY extends Query, RESPONSE> {

    RESPONSE proceed(QUERY query);
}
