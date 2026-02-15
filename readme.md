## Environment

`Intel(R) Xeon(R) @ 2.20GHz - 4 vCPU (2 cores), 16GB RAM, Debian GNU/Linux 12 (bookworm)`

`OpenJDK Runtime Environment Temurin-25.0.2+10 (build 25.0.2+10-LTS)`

`SDKMAN!
script: 5.20.0
native: 0.7.14 (linux x86_64)`

`Scala CLI version: 1.12.1`

## Prerequisites

1. `sudo -i`
2. `apt install git -y`
3. `git clone https://github.com/zygiert1990/stubs-intrinsics-playground.git`
4. `cd stubs-intrinsics-playground/`
5. `chmod +x scripts/init.sh`
6. `source ./scripts/init.sh`

## Run Main Class
1. With displaying stubs generation time:
   `scala-cli . --main-class playground.Main --java-opt -Xlog:startuptime --power`
```
[0.005s][info][startuptime] StubRoutines generation initial stubs, 0.0005537 secs
[0.005s][info][startuptime] SharedRuntime generate_throw_exception, 0.0000390 secs
[0.019s][info][startuptime] Genesis, 0.0142378 secs
[0.019s][info][startuptime] StubRoutines generation continuation stubs, 0.0000309 secs
[0.019s][info][startuptime] SharedRuntime generate_jfr_stubs, 0.0000318 secs
[0.019s][info][startuptime] SharedRuntime generate_throw_exception, 0.0000053 secs
[0.020s][info][startuptime] SharedRuntime generate_throw_exception, 0.0000085 secs
[0.020s][info][startuptime] SharedRuntime generate_throw_exception, 0.0000038 secs
[0.020s][info][startuptime] SharedRuntime generate_throw_exception, 0.0000061 secs
[0.024s][info][startuptime] Interpreter generation, 0.0016316 secs
[0.026s][info][startuptime] StubRoutines generation final stubs, 0.0003121 secs
[0.026s][info][startuptime] MethodHandles adapters generation, 0.0000257 secs
[0.026s][info][startuptime] Start VMThread, 0.0002809 secs
[0.036s][info][startuptime] Initialize java.lang classes, 0.0097709 secs
[0.041s][info][startuptime] StubRoutines generation compiler stubs, 0.0033504 secs
[0.042s][info][startuptime] Initialize java.lang.invoke classes, 0.0006948 secs
[0.048s][info][startuptime] Initialize module system, 0.0056185 secs
[0.049s][info][startuptime] Create VM, 0.0473394 secs
```
2. With displaying generated stubs:
`scala-cli . --main-class playground.Main --java-opt -XX:+UnlockDiagnosticVMOptions --java-opt -XX:+PrintStubCode --power`
3. With printing Intrinsics:
`scala-cli . --main-class playground.Main --java-opt -XX:+UnlockDiagnosticVMOptions --java-opt -XX:+PrintIntrinsics --power`
```
@ 16   java.lang.Math::pow (6 bytes)   (intrinsic)
```
4. With printing Intrinsics and `InlineNatives` option disabled:
`scala-cli . --main-class playground.Main --java-opt -XX:+UnlockDiagnosticVMOptions --java-opt -XX:+PrintIntrinsics --java-opt -XX:-InlineNatives --power`
```
@ 2   java.lang.FdLibm$Pow::compute (1533 bytes)   failed to inline: hot method too big
@ 9   java.lang.Double::isNaN (12 bytes)   inline (hot)
@ 16   java.lang.Double::isNaN (12 bytes)   inline (hot)
@ 27   java.lang.Math::abs (12 bytes)   inline (hot)
  @ 1   java.lang.Double::doubleToRawLongBits (0 bytes)   failed to inline: native method
  @ 8   java.lang.Double::longBitsToDouble (0 bytes)   failed to inline: native method
@ 33   java.lang.Math::abs (12 bytes)   inline (hot)
  @ 1   java.lang.Double::doubleToRawLongBits (0 bytes)   failed to inline: native method
  @ 8   java.lang.Double::longBitsToDouble (0 bytes)   failed to inline: native method
@ 16   java.lang.Math::pow (6 bytes)   inline (hot)
  @ 2   java.lang.StrictMath::pow (6 bytes)   inline (hot)
    @ 2   java.lang.FdLibm$Pow::compute (1533 bytes)   failed to inline: hot method too big
```
5. With printing Intrinsics and pow function Intrinsic disabled using `ControlIntrinsic` option:
`scala-cli . --main-class playground.Main --java-opt -XX:+UnlockDiagnosticVMOptions --java-opt -XX:+PrintIntrinsics --java-opt -XX:ControlIntrinsic=-_dpow --power`
```
@ 2   java.lang.FdLibm$Pow::compute (1533 bytes)   failed to inline: hot method too big
@ 9   java.lang.Double::isNaN (12 bytes)   inline (hot)
@ 16   java.lang.Double::isNaN (12 bytes)   inline (hot)
@ 27   java.lang.Math::abs (12 bytes)   (intrinsic)
@ 33   java.lang.Math::abs (12 bytes)   (intrinsic)
@ 16   java.lang.Math::pow (6 bytes)   inline (hot)
  @ 2   java.lang.StrictMath::pow (6 bytes)   inline (hot)
    @ 2   java.lang.FdLibm$Pow::compute (1533 bytes)   failed to inline: hot method too big
```
6. To print all intrinsic specific options:
`java -XX:+UnlockDiagnosticVMOptions -XX:+UnlockExperimentalVMOptions -XX:+PrintFlagsFinal -version | grep -Ei 'use.*intrinsic'`

## Run JMH Benchmark
`scala-cli . --jmh --power`

```
Benchmark                                                                         Mode  Cnt    Score    Error  Units
IntrinsicPowerBenchmark.power_Intrinsic_Off_By_Disable_Inline_Natives             avgt   15  409.862 ? 11.196  ns/op
IntrinsicPowerBenchmark.power_Intrinsic_Off_By_Disable_Libm_Intrinsics            avgt   15   91.807 ?  0.248  ns/op
IntrinsicPowerBenchmark.power_Intrinsic_Off_By_Disable_Through_Control_Intrinsic  avgt   15   91.300 ?  0.364  ns/op
IntrinsicPowerBenchmark.power_Intrinsic_On                                        avgt   15   22.178 ?  0.050  ns/op
```

## Run JMH Benchmark with profiler
`scala-cli . --jmh --power -- -f 1 -prof "perfasm:events=cpu-clock;intel=true"`