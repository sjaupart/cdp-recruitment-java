package adeo.leroymerlin.cdp.events.domain.model;

import java.util.Objects;

public class SearchCriteria {

    private String pattern;

    private SearchCriteria(String pattern) {
        this.pattern = pattern;
    }

    public static SearchCriteria of(String pattern) {
        return new SearchCriteria(pattern);
    }

    public String pattern() {
        return pattern;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        SearchCriteria that = (SearchCriteria) o;
        return Objects.equals(pattern, that.pattern);
    }

    @Override
    public int hashCode() {
        return Objects.hash(pattern);
    }
}
