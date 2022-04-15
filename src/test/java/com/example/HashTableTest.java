package com.example;

import com.example.hashtable.CustomHashTable;

import org.junit.Test;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class HashTableTest {
    @Test
    public void when_add_element_to_custom_hash_table_then_should_retrieve_it() {
        // given
        CustomHashTable<String, Integer> customHashTable = new CustomHashTable<>();

        // when
        customHashTable.put("A", 1);
        customHashTable.put("B", 1);
        customHashTable.put("C", 1);

        // then
        assertThat(customHashTable.size()).isEqualTo(3);
        assertThat(customHashTable.get("A")).isEqualTo(1);
        assertThat(customHashTable.containsKey("A")).isTrue();
        assertThat(customHashTable.containsKey("D")).isFalse();
    }

    @Test
    public void add_multiple_elements_to_the_hash_table() {
        // given
        CustomHashTable<String, Integer> customHashTable = new CustomHashTable<>();

        // when
        IntStream.range(0, 10_000).forEach(i ->
            customHashTable.put(String.valueOf(i), i)
        );

        // then
        assertThat(customHashTable.size()).isEqualTo(10_000);
    }

    @Test
    public void when_using_object_with_incorrect_hash_code_then_all_elements_will_land_in_the_same_bucket() {
        // given
        CustomHashTable<BrokenHashCode, Integer> customHashTable = new CustomHashTable<>();

        // when
        IntStream.range(0, 10_000).forEach(i ->
            customHashTable.put(new BrokenHashCode(i), i)
        );

        // then
        assertThat(
            customHashTable.getHashCodesDistribution()
                            .stream()
                            .filter(v -> v.equals(10_000))
                            .collect(Collectors.toList())
                            .size()
        ).isEqualTo(1);
        assertThat(customHashTable.size()).isEqualTo(10_000);
    }

    @Test
    public void when_using_object_with_correct_hash_code_then_data_shold_be_distributed_equally_in_all_buckets() {
        // given
        CustomHashTable<ProperHashCode, Integer> customHashTable = new CustomHashTable<>();

        // when
        IntStream.range(0, 10_000).forEach(i ->
            customHashTable.put(new ProperHashCode(i), i)
        );

        // then
        assertThat(
            customHashTable.getHashCodesDistribution()
                            .stream()
                            .filter(v -> v.equals(1_000))
                            .collect(Collectors.toList())
                            .size()
        ).isEqualTo(10);
        assertThat(customHashTable.size()).isEqualTo(10_000);
    }

    @Test
    public void when_add_element_to_map_then_should_retrieve_it() {
        // given
        Map<String, Integer> map = new HashMap<>();

        // when
        map.put("A", 1);
        map.put("B", 1);
        map.put("C", 1);

        // then
        assertThat(map.size()).isEqualTo(3);
        assertThat(map.get("A")).isEqualTo(1);
        assertThat(map.containsKey("A")).isTrue();
        assertThat(map.containsKey("D")).isFalse();
    }

    private class BrokenHashCode {
        private final int value;

        public BrokenHashCode(int value) { this.value = value; }

        public int getValue() { return value; }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;

            BrokenHashCode that = (BrokenHashCode) o;

            return value == that.value;
        }

        @Override
        public int hashCode() { return 1; /* broker hash code */ }
    }

    private class ProperHashCode {
        private final int value;

        public ProperHashCode(int value) { this.value = value; }

        public int getValue() { return value; }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;

            BrokenHashCode that = (BrokenHashCode) o;

            return value == that.value;
        }

        @Override
        public int hashCode() { return value; }
    }
}
