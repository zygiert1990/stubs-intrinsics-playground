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
`scala-cli . --main-class playground.Main --java-opt -XX:+PrintCompilation --power`

## Run JMH Benchmark
`scala-cli . --jmh --power`

```
Benchmark                                    Mode  Cnt    Score   Error  Units
IntrinsicPowerBenchmark.power_Intrinsic_Off  avgt   15  401.869 ? 3.443  ns/op
IntrinsicPowerBenchmark.power_Intrinsic_On   avgt   15   22.362 ? 0.099  ns/op
```

## Run JMH Benchmark with profiler
`scala-cli . --jmh --power -- -f 1 -prof "perfasm:events=cpu-clock;intel=true"`