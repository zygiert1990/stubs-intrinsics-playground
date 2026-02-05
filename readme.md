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