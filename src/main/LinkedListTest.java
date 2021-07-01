package main;

import org.junit.Test;

import java.util.LinkedList;
import java.util.List;

public class LinkedListTest {
    private static final int MAX_ELEMENTS = 500000;
    private static final int BENCHMARK_RUNS = 1000;
    String[] strings = maxArray();

    abstract class Benchmarkable {
        List<String> testList;
        ListInterface testList2;

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
        Benchmarkable arrayListImplAddBenchmark = new Benchmarkable() {
            @Override
            String getName() {
                return "Native linked list add() by index";
            }

            @Override
            void setup() {
                testList = new LinkedList<>();
                for (String string : strings)
                    testList.add(string);
            }

            @Override
            void runMethod() {
                testList.add(MAX_ELEMENTS / 2, "string");
            }
        };
        arrayListImplAddBenchmark.doBenchMark();

        Benchmarkable doublyLinkedListAddBenchmark = new Benchmarkable() {
            @Override
            String getName() {
                return "Native linked list add() by index";
            }

            @Override
            void setup() {
                testList2 = new LinkedListImpl();
                for (String string : strings)
                    testList2.add(string);
            }

            @Override
            void runMethod() {
                testList2.add(MAX_ELEMENTS / 2, "string");
            }
        };
        doublyLinkedListAddBenchmark.doBenchMark();
    }

    @Test
    public void testAppendAtEnd() {
        Benchmarkable arrayImplAddBenchmark = new Benchmarkable() {
            @Override
            String getName() {
                return "Native linked list add() at end";
            }

            @Override
            void setup() {
                testList = new LinkedList<>();
            }

            @Override
            void runMethod() {
                for (String string : strings)
                    testList.add(string);
            }
        };
        arrayImplAddBenchmark.doBenchMark();

        Benchmarkable doublyLinkedListBenchmark = new Benchmarkable() {
            @Override
            String getName() {
                return "Custom linked list add() at end";
            }

            @Override
            void setup() {
                testList2 = new LinkedListImpl();
            }

            @Override
            void runMethod() {
                for (String string : strings)
                    testList2.add(string);
            }
        };
        doublyLinkedListBenchmark.doBenchMark();
    }

    @Test
    public void testRemoveByIndex() {
        Benchmarkable arrayListImplBenchmark = new Benchmarkable() {

            @Override
            public String getName() {
                return "Native linked list remove() by index";
            }

            @Override
            void setup() {
                testList = new LinkedList<>();
                for (String string : strings)
                    testList.add(string);
            }

            @Override
            void runMethod() {
                testList.remove(MAX_ELEMENTS / 2);
            }
        };
        arrayListImplBenchmark.doBenchMark();

        Benchmarkable doublyLinked = new Benchmarkable() {
            @Override
            String getName() {
                return "Custom linked list remove() by index";
            }

            @Override
            void setup() {
                testList2 = new LinkedListImpl();
                for (String string : strings)
                    testList2.add(string);
            }

            @Override
            void runMethod() {
                testList2.removeByIndex(MAX_ELEMENTS / 2);
            }
        };
        doublyLinked.doBenchMark();
    }

    @Test
    public void testContains() {
        Benchmarkable arrayListImplBenchmark = new Benchmarkable() {

            @Override
            public String getName() {
                return "Native linked list contains()";
            }

            @Override
            void setup() {
                testList = new LinkedList<>();
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
                return "Custom linked list contains()";
            }

            @Override
            void setup() {
                testList2 = new LinkedListImpl();
                for (int i = 0; i < MAX_ELEMENTS; i++)
                    testList2.add(String.valueOf(i));
            }

            @Override
            void runMethod() {
                testList2.contains(String.valueOf(MAX_ELEMENTS / 2));
            }
        };
        doublyLinked.doBenchMark();
    }

    @Test
    public void testRemoveByValue() {
        Benchmarkable arrayListImplBenchmark = new Benchmarkable() {

            @Override
            public String getName() {
                return "Native linked list remove() by value";
            }

            @Override
            void setup() {
                testList = new LinkedList<>();
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
                return "Custom linked list remove() by value";
            }

            @Override
            void setup() {
                testList2 = new LinkedListImpl();
                for (int i = 0; i < MAX_ELEMENTS; i++)
                    testList2.add(String.valueOf(i));
            }

            @Override
            void runMethod() {
                testList2.removeByValue(String.valueOf(MAX_ELEMENTS / 2));
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