package adeo.leroymerlin.cdp.listing.domain.model;

import java.util.Objects;
import java.util.Set;

public class Event {

    private final EventId id;
    private final String name;
    private final String pictureUrl;
    private final Integer numberOfStars;
    private final String comment;
    private final Set<Band> bands;

    private Event(EventId id, String name, String pictureUrl, Integer numberOfStars, String comment, Set<Band> bands) {
        if (Objects.isNull(name)) {
            throw new InvalidEvent("Name is mandatory");
        }

        this.id = id;
        this.name = name;
        this.pictureUrl = pictureUrl;
        this.numberOfStars = numberOfStars;
        this.comment = comment;
        this.bands = bands;
    }

    public static Builder builder() {
        return new Builder();
    }

    public EventId id() {
        return id;
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

    public Set<Band> bands() {
        return bands;
    }

    public boolean hasMemberNameMatching(String pattern) {
        return bands.stream().anyMatch(band -> band.hasMemberNameMatching(pattern));
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Event event = (Event) o;
        return Objects.equals(id, event.id) && Objects.equals(name, event.name) && Objects.equals(pictureUrl, event.pictureUrl) && Objects.equals(numberOfStars, event.numberOfStars) && Objects.equals(comment, event.comment) && Objects.equals(bands, event.bands);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, pictureUrl, numberOfStars, comment, bands);
    }

    @Override
    public String toString() {
        return "Event{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", pictureUrl='" + pictureUrl + '\'' +
                ", numberOfStars=" + numberOfStars +
                ", comment='" + comment + '\'' +
                ", bands=" + bands +
                '}';
    }

    public static class Builder {
        private EventId id;
        private String name;
        private String picture;
        private Integer numberOfStars;
        private String comment;
        private Set<Band> bands;

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

        public Builder bands(Set<Band> bands) {
            this.bands = bands;
            return this;
        }

        public Event build() {
            return new Event(this.id, this.name, this.picture, this.numberOfStars, this.comment, this.bands);
        }
    }
}
