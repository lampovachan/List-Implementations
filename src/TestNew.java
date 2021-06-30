import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class TestNew {
    private static final int MAX_ELEMENTS = 5;
    private static final int WARMUP_RUNS = 10000;
    private static final int BENCHMARK_RUNS = 100000;
    String[] strings = maxArray();

    // Does a JIT warmup run and then a benchmark averaged over many runs
    abstract class Benchmarkable {
        List<String> stringList = Arrays.asList(strings);
        ArrayImpl testList;
        DoublyLinkedList testList1;

        abstract String getName();

        abstract void setup();

        abstract void runMethod();

        public void doBenchMark() {
            int warmupRuns = WARMUP_RUNS;
            int benchmarkRuns = BENCHMARK_RUNS;


            for (int i = 0; i < warmupRuns; i++) {
                setup();
                runMethod();
            }

            // Timing loop
            long totalTime = 0;
            for (int i = 0; i < benchmarkRuns; i++) {
                setup();
                long startTime = System.nanoTime();
                runMethod();
                long endTime = System.nanoTime();
                totalTime += (endTime - startTime);
            }
            System.out.println("Benchmark \"" + getName() + "\" took " + totalTime / benchmarkRuns + " ns/run");
        }
    }

    @Test
    public void listAppend() {
        Benchmarkable arrayImplAddBenchmark = new Benchmarkable() {
            @Override
            String getName() {
                return "ArrayImpl add() from scratch";
            }

            @Override
            void setup() {
                testList = new ArrayImpl();
            }

            @Override
            void runMethod() {
                ArrayImpl myList = testList;
                for (String string : strings)
                    testList.add(string);
            }
        };
        arrayImplAddBenchmark.doBenchMark();

        Benchmarkable doublyLinkedListBenchmark = new Benchmarkable() {
            @Override
            String getName() {
                return "DoublyLinkedList add() from scratch";
            }

            @Override
            void setup() {
                testList1 = new DoublyLinkedList();
            }

            @Override
            void runMethod() {
                DoublyLinkedList myList1 = testList1;
                for (String string : strings)
                    testList1.insertAtEnd(string);
            }
        };
        doublyLinkedListBenchmark.doBenchMark();
    }

            private String[] maxArray() {
                String[] strings = new String[MAX_ELEMENTS];
                Boolean result = Boolean.TRUE;
                for (int i = 0; i < MAX_ELEMENTS; i++) {
                    strings[i] = getString(result, i);
                    result = !result;
                }
                return strings;
            }

            private String getString(Boolean result, int i) {
                return String.valueOf(result) + i + String.valueOf(!result);
            }
    }