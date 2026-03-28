package seedu.address.model.property;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Arrays;
import java.util.Collections;

import org.junit.jupiter.api.Test;

public class PropertyMatchesFilterPredicateTest {

    private static final Property PROPERTY = new Property(
            new PropertyAddress("311 Clementi Ave 2"),
            new Price("1200000"),
            new Size("1200"));

    @Test
    public void test_matchesAddressKeyword_returnsTrue() {
        PropertyMatchesFilterPredicate predicate = new PropertyMatchesFilterPredicate(
                Collections.singletonList("Clementi"),
                0,
                Long.MAX_VALUE,
                0,
                Long.MAX_VALUE);
        assertTrue(predicate.test(PROPERTY));
    }

    @Test
    public void test_matchesPriceAndSizeRange_returnsTrue() {
        PropertyMatchesFilterPredicate predicate = new PropertyMatchesFilterPredicate(
                Collections.emptyList(),
                1100000,
                1300000,
                1000,
                1500);
        assertTrue(predicate.test(PROPERTY));
    }

    @Test
    public void test_outsideRange_returnsFalse() {
        PropertyMatchesFilterPredicate pricePredicate = new PropertyMatchesFilterPredicate(
                Collections.emptyList(),
                100000,
                200000,
                0,
                Long.MAX_VALUE);
        assertFalse(pricePredicate.test(PROPERTY));

        PropertyMatchesFilterPredicate sizePredicate = new PropertyMatchesFilterPredicate(
                Collections.emptyList(),
                0,
                Long.MAX_VALUE,
                10,
                100);
        assertFalse(sizePredicate.test(PROPERTY));
    }

    @Test
    public void test_addressAndRangeCombined_returnsFalseWhenAnyCriterionFails() {
        PropertyMatchesFilterPredicate predicate = new PropertyMatchesFilterPredicate(
                Arrays.asList("Punggol"),
                1100000,
                1300000,
                1000,
                1500);
        assertFalse(predicate.test(PROPERTY));
    }
}
