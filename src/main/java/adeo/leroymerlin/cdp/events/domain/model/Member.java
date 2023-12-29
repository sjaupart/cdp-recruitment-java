package adeo.leroymerlin.cdp.events.domain.model;

public record Member(MemberId id, String name) {

    public static Member of(Long id, String name) {
        return new Member(new MemberId(id), name);
    }
}
