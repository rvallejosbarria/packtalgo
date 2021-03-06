package com.example.sorting;

import org.junit.Test;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class SortTest {
    @Test
    public void should_sort_elements_using_bubble_sort() {
        // given
        Sort sort = new BubbleSort();
        int[] input = new int[]{4, 5, 1, 2, 10, 12, 1};

        // when
        sort.sort(input);

        // then
        assertThat(input).isEqualTo(new int[]{1, 1, 2, 4, 5, 10, 12});
    }

    @Test
    public void should_sort_elements_using_heap_sort() {
        // given
        Sort sort = new HeapSort();
        int[] input = new int[]{4, 5, 1, 2, 10, 12, 1};

        // when
        sort.sort(input);

        // then
        assertThat(input).isEqualTo(new int[]{1, 1, 2, 4, 5, 10, 12});
    }
}
