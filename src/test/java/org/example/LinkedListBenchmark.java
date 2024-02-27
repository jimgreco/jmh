package org.example;

import org.openjdk.jmh.annotations.Benchmark;
import org.openjdk.jmh.annotations.BenchmarkMode;
import org.openjdk.jmh.annotations.Fork;
import org.openjdk.jmh.annotations.Level;
import org.openjdk.jmh.annotations.Mode;
import org.openjdk.jmh.annotations.Param;
import org.openjdk.jmh.annotations.Scope;
import org.openjdk.jmh.annotations.Setup;
import org.openjdk.jmh.annotations.State;

import java.util.Random;

@State(Scope.Benchmark)
public class LinkedListBenchmark {

    @Param({ "10000", "100000" })
    public int iterations;

    public Random random;

    @Setup(Level.Trial)
    public void initTrial() {
        random = new Random();
    }

    @Fork(value = 1, warmups = 1)
    @Benchmark
    @BenchmarkMode(Mode.AverageTime)
    public void insertOrder(LinkedListBenchmark plan) {
        IntrusiveLinkedList list = new IntrusiveLinkedList();

        for (var i = 1; i <= plan.iterations; i++) {
            var order = new IntrusiveLinkedListItem();
            order.id = i + 1;
            order.buy = false;
            order.qty = 1000.0;
            order.price = random.nextDouble();
            list.insertOrder(order);
        }
    }
}
