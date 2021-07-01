# List-Implementations

Benchmarks on:

java version "1.8.0_60"

Java(TM) SE Runtime Environment (build 1.8.0_60-b27)

Java HotSpot(TM) 64-Bit Server VM (build 25.60-b23, mixed mode)

Intel Core i5-7200U, 8GB RAM


**With MAX_ELEMENTS = 500000**

```
Benchmark "Native array list remove() by value" took 3131573 ns/run

Benchmark "Custom array list remove() by value" took 2151648 ns/run

Benchmark "Native array list remove() at position" took 354435 ns/run

Benchmark "Custom array list remove() at position" took 339544 ns/run

Benchmark "Native array list contains()" took 2226010 ns/run

Benchmark "Custom array list contains()" took 2605347 ns/run

Benchmark "Native array list add() at position" took 336370 ns/run

Benchmark "Custom array list add() at position" took 3333 ns/run  //don't know what's the reason of this

Benchmark "Native array list add() at end" took 12390709 ns/run

Benchmark "Custom array list add() at end" took 6503922 ns/run
```



```
Benchmark "Native linked list remove() by index" took 855798 ns/run

Benchmark "Custom linked list remove() by index" took 663042 ns/run

Benchmark "Native linked list remove() by value" took 2019434 ns/run

Benchmark "Custom linked list remove() by value" took 3725264 ns/run

Benchmark "Native linked list contains()" took 1909084 ns/run

Benchmark "Custom linked list contains()" took 2433710 ns/run

Benchmark "Native linked list add() by index" took 1005515 ns/run

Benchmark "Native linked list add() by index" took 732466 ns/run

Benchmark "Native linked list add() at end" took 4939845 ns/run

Benchmark "Custom linked list add() at end" took 4778479 ns/run
```






