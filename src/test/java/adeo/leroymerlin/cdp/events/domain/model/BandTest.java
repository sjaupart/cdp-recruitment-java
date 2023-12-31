package adeo.leroymerlin.cdp.events.domain.model;

import org.junit.jupiter.api.Test;

import static adeo.leroymerlin.cdp.events.fixtures.EventFixtures.BAND_ACDC;
import static org.assertj.core.api.Assertions.assertThat;

class BandTest {

    @Test
    void band_has_member_name_matching_criteria() {
        assertThat(BAND_ACDC.hasMemberNameMatching("cob")).isTrue();
        assertThat(BAND_ACDC.hasMemberNameMatching("azerty")).isFalse();
        assertThat(BAND_ACDC.hasMemberNameMatching(null)).isFalse();
    }
}