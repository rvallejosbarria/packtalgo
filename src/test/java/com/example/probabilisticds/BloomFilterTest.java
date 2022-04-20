package com.example.probabilisticds;

import com.google.common.hash.BloomFilter;
import com.google.common.hash.Funnels;

import org.junit.Test;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

import java.util.stream.IntStream;

public class BloomFilterTest {
    @Test
    public void given_bloom_filter_when_adding_strings_to_it_then_should_not_return_any_false_positive() {
        // when
        BloomFilter<Integer> filter = BloomFilter.create(
            Funnels.integerFunnel(),
            500,
            0.01
        );

        // when
        filter.put(1);
        filter.put(2);
        filter.put(3);

        // then
        // the probability that it return true, but is actually false is 1%
        assertThat(filter.mightContain(1)).isTrue();
        assertThat(filter.mightContain(2)).isTrue();
        assertThat(filter.mightContain(3)).isTrue();

        assertThat(filter.mightContain(100)).isFalse();
    }

    @Test
    public void given_bloom_filter_when_adding_strings_to_it_more_than_defined_expected_insertions_then_it_will_return_true_for_almost_all_elements() {
        // when
        BloomFilter<Integer> filter = BloomFilter.create(
            Funnels.integerFunnel(),
            5_000_000,
            0.01
        );

        // when
        IntStream.range(0, 100_000).forEach(filter::put);

        // then
        assertThat(filter.mightContain(1)).isTrue();
        assertThat(filter.mightContain(2)).isTrue();
        assertThat(filter.mightContain(3)).isTrue();

        assertThat(filter.mightContain(1_000_000)).isFalse();
    }
}
