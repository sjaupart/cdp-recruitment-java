package adeo.leroymerlin.cdp.listing.domain.use_cases;

public interface CommandHandler<COMMAND extends Command, RESPONSE> {

    RESPONSE proceed(COMMAND command);
}
