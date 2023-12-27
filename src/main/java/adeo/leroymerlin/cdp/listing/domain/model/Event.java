package adeo.leroymerlin.cdp.listing.domain.model;

import java.util.Objects;

public class Event {

    private final String name;
    private final String comment;

    private Event(String name, String comment) {
        if (Objects.isNull(name)) {
            throw new InvalidEvent("Name is mandatory");
        }

        this.name = name;
        this.comment = comment;
    }

    public static Builder builder() {
        return new Builder();
    }

    public String name() {
        return name;
    }

    public String comment() {
        return comment;
    }

    public static class Builder {
        private String name;
        private String comment;

        public Builder name(String name) {
            this.name = name;
            return this;
        }

        public Builder comment(String comment) {
            this.comment = comment;
            return this;
        }

        public Event build() {
            return new Event(this.name, this.comment);
        }
    }
}
