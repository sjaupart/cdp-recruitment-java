package adeo.leroymerlin.cdp.listing.domain.model;

import java.util.Optional;
import java.util.Set;

public record Band(BandId id, String name, Set<Member> members) {

    public boolean hasMemberNameMatching(String pattern) {
        return Optional.ofNullable(pattern).stream()
                .anyMatch(this::memberNameMatching);
    }

    private boolean memberNameMatching(String value) {
        return members.stream()
                .anyMatch(member -> member.name().contains(value));
    }
}
