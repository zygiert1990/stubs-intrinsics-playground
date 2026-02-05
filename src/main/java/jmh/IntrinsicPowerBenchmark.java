package jmh;

import org.openjdk.jmh.annotations.*;
import org.openjdk.jmh.infra.Blackhole;

import java.util.concurrent.TimeUnit;

@BenchmarkMode(Mode.AverageTime)
@OutputTimeUnit(TimeUnit.NANOSECONDS)
@State(Scope.Benchmark)
@Fork(3)
@Warmup(iterations = 5, time = 1, timeUnit = TimeUnit.SECONDS)
@Measurement(iterations = 5, time = 1, timeUnit = TimeUnit.SECONDS)
public class IntrinsicPowerBenchmark {

    @Benchmark
    public void power_Intrinsic_On(Blackhole bh) {
        bh.consume(Math.pow(2.0, 10.0));
    }
    
    @Benchmark
    @Fork(value = 3, jvmArgsAppend = "-XX:+UnlockDiagnosticVMOptions -XX:-InlineNatives")
    public void power_Intrinsic_Off(Blackhole bh) {
        bh.consume(Math.pow(2.0, 10.0));
    }
    
}