package com.example.probabilisticds;

import java.util.stream.LongStream;

import com.google.common.hash.HashFunction;
import com.google.common.hash.Hashing;

import org.assertj.core.data.Offset;
import org.junit.Test;

import net.agkn.hll.HLL;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class HLLLongRunningUnitTest {
    @Test
    public void given_hll_when_add_huge_amount_of_numbers_then_should_return_estimated_cardinality() {
        // given
        long numberOfElements = 100_000_000;
        long toleratedDifference = 1_000_000;
        HashFunction hashFunction = Hashing.murmur3_128();
        HLL hll = new HLL(14, 5);

        // when
        LongStream.range(0, numberOfElements).forEach(element -> {
            long hashedValue = hashFunction.newHasher().putLong(element).hash().asLong();
            hll.addRaw(hashedValue);
        });

        // then
        long cardinality = hll.cardinality();
        System.out.println(cardinality);
        assertThat(cardinality).isCloseTo(numberOfElements, Offset.offset(toleratedDifference));
    }

    @Test
    public void given_two_hll_when_add_huge_amount_of_numbers_then_should_return_estimated_cardinality() {
        // given
        long numberOfElements = 100_000_000;
        long toleratedDifference = 1_000_000;
        HashFunction hashFunction = Hashing.murmur3_128();
        HLL firstHll = new HLL(15, 5);
        HLL secondHll = new HLL(15, 5);

        // when
        LongStream.range(0, numberOfElements).forEach(element -> {
            long hashedValue = hashFunction.newHasher().putLong(element).hash().asLong();
            firstHll.addRaw(hashedValue);
        });

        LongStream.range(numberOfElements, numberOfElements * 2).forEach(element -> {
            long hashedValue = hashFunction.newHasher().putLong(element).hash().asLong();
            secondHll.addRaw(hashedValue);
        });

        // then
        firstHll.union(secondHll);
        long cardinality = firstHll.cardinality();
        System.out.println(cardinality);
        assertThat(cardinality).isCloseTo(numberOfElements * 2, Offset.offset(toleratedDifference * 2));
    }
}
