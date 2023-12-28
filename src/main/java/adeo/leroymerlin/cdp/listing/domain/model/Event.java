package adeo.leroymerlin.cdp.listing.domain.model;

import java.util.Objects;

public class Event {

    private final EventId id;
    private final String name;
    private final String pictureUrl;
    private final Integer numberOfStars;
    private final String comment;

    private Event(EventId id, String name, String pictureUrl, Integer numberOfStars, String comment) {
        if (Objects.isNull(name)) {
            throw new InvalidEvent("Name is mandatory");
        }

        this.id = id;
        this.name = name;
        this.pictureUrl = pictureUrl;
        this.numberOfStars = numberOfStars;
        this.comment = comment;
    }

    public static Builder builder() {
        return new Builder();
    }

    public String name() {
        return name;
    }

    public String pictureUrl() {
        return pictureUrl;
    }

    public Integer numberOfStars() {
        return numberOfStars;
    }

    public String comment() {
        return comment;
    }

    public static class Builder {
        private EventId id;
        private String name;
        private String picture;
        private Integer numberOfStars;
        private String comment;

        public Builder id(EventId id) {
            this.id = id;
            return this;
        }

        public Builder name(String name) {
            this.name = name;
            return this;
        }

        public Builder pictureUrl(String picture) {
            this.picture = picture;
            return this;
        }

        public Builder numberOfStars(Integer numberOfStars) {
            this.numberOfStars = numberOfStars;
            return this;
        }

        public Builder comment(String comment) {
            this.comment = comment;
            return this;
        }

        public Event build() {
            return new Event(this.id, this.name, this.picture, this.numberOfStars, this.comment);
        }
    }
}
