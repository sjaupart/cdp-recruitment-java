package adeo.leroymerlin.cdp.listing.domain.use_cases.list_events;

import adeo.leroymerlin.cdp.listing.domain.model.SearchCriteria;
import adeo.leroymerlin.cdp.listing.domain.use_cases.Query;

import java.util.Objects;
import java.util.Optional;

public class ListEvents implements Query {

    private SearchCriteria criteria;

    public ListEvents() {
    }

    public ListEvents(SearchCriteria criteria) {
        this.criteria = criteria;
    }

    public static ListEvents noCriteria() {
        return new ListEvents();
    }

    public Optional<SearchCriteria> criteria() {
        return Optional.ofNullable(criteria);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ListEvents that = (ListEvents) o;
        return Objects.equals(criteria, that.criteria);
    }

    @Override
    public int hashCode() {
        return Objects.hash(criteria);
    }
}
