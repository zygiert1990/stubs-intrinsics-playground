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
public class IntrinsicsPowerBenchmark {

    @Benchmark
    public void power_Intrinsics_On(Blackhole bh) {
        bh.consume(Math.pow(2.0, 10.0));
    }
    
    @Benchmark
    @Fork(value = 3, jvmArgsAppend = {"-XX:+UnlockDiagnosticVMOptions", "-XX:-InlineNatives"})
    public void power_Intrinsics_Off_Disabled_By_Inline_Natives(Blackhole bh) {
        bh.consume(Math.pow(2.0, 10.0));
    }
    
    @Benchmark
    @Fork(value = 3, jvmArgsAppend = {"-XX:+UnlockDiagnosticVMOptions", "-XX:ControlIntrinsic=-_dpow"})
    public void power_Intrinsics_Off_Disabled_By_Through_Control_Intrinsic(Blackhole bh) {
        bh.consume(Math.pow(2.0, 10.0));
    }
    
    @Benchmark
    @Fork(value = 3, jvmArgsAppend = {"-XX:+UnlockDiagnosticVMOptions", "-XX:-UseLibmIntrinsic"})
    public void power_Intrinsics_Off_Disabled_By_Libm_Intrinsics(Blackhole bh) {
        bh.consume(Math.pow(2.0, 10.0));
    }
    
}