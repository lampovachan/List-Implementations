package main;

import org.junit.Test;

import java.util.*;

public class ArrayListTest {
    private static final int MAX_ELEMENTS = 500000;
    private static final int BENCHMARK_RUNS = 1000;
    String[] strings = maxArray();

    abstract class Benchmarkable {
        List<String> testList;
        ListInterface testList1;

        abstract String getName();

        abstract void setup();

        abstract void runMethod();

        public void doBenchMark() {
            int benchmarkRuns = BENCHMARK_RUNS;

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
    public void listAppendAtPosition() {
        Benchmarkable nativeArrayListBenchmark = new Benchmarkable() {
            @Override
            public String getName() {
                return "Native array list add() at position";
            }

            @Override
            void setup() {
                testList = new ArrayList<>();
                for (String string : strings)
                    testList.add(string);
            }

            @Override
            void runMethod() {
                testList.add(MAX_ELEMENTS / 2, "string");
            }
        };
        nativeArrayListBenchmark.doBenchMark();

        Benchmarkable customArrayListBenchmark = new Benchmarkable() {
            @Override
            public String getName() {
                return "Custom array list add() at position";
            }

            @Override
            void setup() {
                testList1 = new ArrayListImpl();
                for (String string : strings)
                    testList1.add(string);
            }

            @Override
            void runMethod() {
                testList1.add(MAX_ELEMENTS / 2, "string");
            }
        };
        customArrayListBenchmark.doBenchMark();
    }

    @Test
    public void testAppendAtEnd() {
        Benchmarkable nativeArrayListBenchmark = new Benchmarkable() {

            @Override
            public String getName() {
                return "Native array list add() at end";
            }

            @Override
            void setup() {
                testList = new ArrayList<String>();
            }

            @Override
            void runMethod() {
                for (String string : strings)
                    testList.add(string);
            }
        };
        nativeArrayListBenchmark.doBenchMark();

        Benchmarkable customArrayListBenchmark = new Benchmarkable() {

            @Override
            public String getName() {
                return "Custom array list add() at end";
            }

            @Override
            void setup() {
                testList1 = new ArrayListImpl();
            }

            @Override
            void runMethod() {
                for (String string : strings)
                    testList1.add(string);
            }

        };
        customArrayListBenchmark.doBenchMark();
    }

    @Test
    public void testRemoveAtPosition() {
        Benchmarkable nativeArrayListBenchmark = new Benchmarkable() {

            @Override
            public String getName() {
                return "Native array list remove() at position";
            }

            @Override
            void setup() {
                testList = new ArrayList<String>();
                for (String string : strings)
                    testList.add(string);
            }

            @Override
            void runMethod() {
                testList.remove(MAX_ELEMENTS / 2);
            }
        };
        nativeArrayListBenchmark.doBenchMark();

        Benchmarkable customArrayListBenchmark = new Benchmarkable() {

            @Override
            public String getName() {
                return "Custom array list remove() at position";
            }

            @Override
            void setup() {
                testList1 = new ArrayListImpl();
                for (String string : strings)
                    testList1.add(string);
            }

            @Override
            void runMethod() {
                testList1.removeByIndex(MAX_ELEMENTS / 2);
            }
        };
        customArrayListBenchmark.doBenchMark();
    }

    @Test
    public void testContains() {
        Benchmarkable arrayListImplBenchmark = new Benchmarkable() {

            @Override
            public String getName() {
                return "main.ArrayListImpl contains()";
            }

            @Override
            void setup() {
                testList = new ArrayList<>();
                for (int i = 0; i < MAX_ELEMENTS; i++)
                    testList.add(String.valueOf(i));
            }

            @Override
            void runMethod() {
                testList.contains(String.valueOf(MAX_ELEMENTS / 2));
            }
        };
        arrayListImplBenchmark.doBenchMark();

        Benchmarkable doublyLinked = new Benchmarkable() {
            @Override
            String getName() {
                return "DoublyLinkedList contains()";
            }

            @Override
            void setup() {
                testList1 = new ArrayListImpl();
                for (int i = 0; i < MAX_ELEMENTS; i++)
                    testList1.add(String.valueOf(i));
            }

            @Override
            void runMethod() {
                testList1.contains(String.valueOf(MAX_ELEMENTS / 2));
            }
        };
        doublyLinked.doBenchMark();
    }

    @Test
    public void testRemoveByValue() {
        Benchmarkable arrayListImplBenchmark = new Benchmarkable() {

            @Override
            public String getName() {
                return "main.ArrayListImpl remove() by value";
            }

            @Override
            void setup() {
                testList = new ArrayList<>();
                for (int i = 0; i < MAX_ELEMENTS; i++)
                    testList.add(String.valueOf(i));
            }

            @Override
            void runMethod() {
                testList.remove(String.valueOf(MAX_ELEMENTS / 2));
            }
        };
        arrayListImplBenchmark.doBenchMark();

        Benchmarkable doublyLinked = new Benchmarkable() {
            @Override
            String getName() {
                return "DoublyLinkedList remove() by value";
            }

            @Override
            void setup() {
                testList1 = new ArrayListImpl();
                for (int i = 0; i < MAX_ELEMENTS; i++)
                    testList1.add(String.valueOf(i));
            }

            @Override
            void runMethod() {
                testList1.removeByValue(String.valueOf(MAX_ELEMENTS / 2));
            }
        };
        doublyLinked.doBenchMark();
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