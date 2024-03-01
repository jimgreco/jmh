package org.example;

import org.openjdk.jmh.annotations.Benchmark;
import org.openjdk.jmh.annotations.BenchmarkMode;
import org.openjdk.jmh.annotations.Fork;
import org.openjdk.jmh.annotations.Level;
import org.openjdk.jmh.annotations.Mode;
import org.openjdk.jmh.annotations.OutputTimeUnit;
import org.openjdk.jmh.annotations.Param;
import org.openjdk.jmh.annotations.Scope;
import org.openjdk.jmh.annotations.Setup;
import org.openjdk.jmh.annotations.State;

import java.util.Random;
import java.util.concurrent.TimeUnit;

@State(Scope.Benchmark)
public class LinkedListBenchmark2 {

    private static final int NUM_ORDERS = 10000;

    public Random random;
    private IntrusiveLinkedList list;
    private IntrusiveLinkedListItem orderToInsert;

    @Setup(Level.Iteration)
    public void initTrial() {
        random = new Random();
        list = new IntrusiveLinkedList();

        for (var i = 1; i <= NUM_ORDERS; i++) {
            var order = new IntrusiveLinkedListItem();
            order.id = i;
            order.buy = false;
            order.qty = 1000.0;
            order.price = random.nextDouble();
            list.insertOrder(order);
        }

        orderToInsert = new IntrusiveLinkedListItem();
        orderToInsert.id = NUM_ORDERS + 1;
        orderToInsert.buy = false;
        orderToInsert.qty = 1000.0;
    }

    @Fork(value = 1, warmups = 1)
    @Benchmark
    @OutputTimeUnit(TimeUnit.NANOSECONDS)
    @BenchmarkMode(Mode.AverageTime)
    public void insertFirstOrder(LinkedListBenchmark2 plan) {
        orderToInsert.price = 0.0;
        list.insertOrder(orderToInsert);
        list.removeOrder(orderToInsert);
    }

    @Fork(value = 1, warmups = 1)
    @Benchmark
    @OutputTimeUnit(TimeUnit.MICROSECONDS)
    @BenchmarkMode(Mode.AverageTime)
    public void insertLastOrder(LinkedListBenchmark2 plan) {
        orderToInsert.price = 1.0;
        list.insertOrder(orderToInsert);
        list.removeOrder(orderToInsert);
    }
}
