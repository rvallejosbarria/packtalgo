package com.example.blockingqueue;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

public class BlockingQueueUsage {
    public static void main(String[] args) {
        final int BOUND = 10;
        final int NUMBER_OF_PRODUCERS = 10;
        final int NUMBER_OF_CONSUMERS = Runtime.getRuntime().availableProcessors();
        int poisonPill = Integer.MAX_VALUE;
        int poisonPillPerProducer = NUMBER_OF_CONSUMERS / NUMBER_OF_PRODUCERS;

        BlockingQueue<Integer> queue = new LinkedBlockingQueue<>(BOUND);

        for (int i = 0; i < NUMBER_OF_PRODUCERS; i++) {
            new Thread(new NumbersProducer(queue, poisonPill, poisonPillPerProducer)).start();
        }

        for (int i = 0; i < NUMBER_OF_CONSUMERS; i++) {
            new Thread(new NumbersConsumer(queue, poisonPill)).start();
        }
    }
}
