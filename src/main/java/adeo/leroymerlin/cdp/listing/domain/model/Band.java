package adeo.leroymerlin.cdp.listing.domain.model;

import java.util.Optional;
import java.util.Set;

public record Band(BandId id, String name, Set<Member> members) {

    public boolean hasMemberNameMatching(String pattern) {
        if (Optional.ofNullable(pattern).isEmpty()) {
            return false;
        }

        return members.stream()
                .anyMatch(member -> member.name().contains(pattern));
    }
}
