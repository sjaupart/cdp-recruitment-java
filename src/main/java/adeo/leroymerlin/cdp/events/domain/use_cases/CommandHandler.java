package adeo.leroymerlin.cdp.events.domain.use_cases;

public interface CommandHandler<COMMAND extends Command, RESPONSE> {

    RESPONSE proceed(COMMAND command);
}
