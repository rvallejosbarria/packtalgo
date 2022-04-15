package com.example;

import com.example.hashtable.CustomHashTable;

import org.junit.Test;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

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
}
