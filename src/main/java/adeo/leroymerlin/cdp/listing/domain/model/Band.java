package adeo.leroymerlin.cdp.listing.domain.model;

import java.util.Set;

public record Band(BandId id, String name, Set<Member> members) {
}
