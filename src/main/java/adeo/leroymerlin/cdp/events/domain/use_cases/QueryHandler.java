package adeo.leroymerlin.cdp.events.domain.use_cases;

public interface QueryHandler<QUERY extends Query, RESPONSE> {

    RESPONSE proceed(QUERY query);
}
