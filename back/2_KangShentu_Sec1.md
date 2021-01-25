#### Author

[Kang Shentu](shentu.k@northeastern.edu)

001569432

### Part1

#### Implement Code

```java
		// 1. 
    private static long getClock() {
        // TO BE IMPLEMENTED
        return System.nanoTime();
    }
    // 2. 
    private static double toMillisecs(long ticks) {
        // TO BE IMPLEMENTED
        return ticks * 1E-6;
    }
    // 3. 
    public <T, U> double repeat(int n, Supplier<T> supplier, Function<T, U> function, UnaryOperator<T> preFunction, Consumer<U> postFunction) {
        logger.trace("repeat: with " + n + " runs");
        this.running = false;
        this.ticks = 0;
        for (int i = 0; i < n; i++) {
            T value = supplier.get();
            if(Objects.nonNull(preFunction))
                value = preFunction.apply(value);
            resume();
            U result = function.apply(value);
            pauseAndLap();
            if(Objects.nonNull(postFunction))
                postFunction.accept(result);
        }
        return meanLapTime();
    }
```

##### Passed Test

![截屏2021-01-26 上午3.13.59](https://tva1.sinaimg.cn/large/008eGmZEly1gn0kfs4e0pj31l50u0anh.jpg)

#### Part2

##### Implement Code

```java
    // 1.  
    public void sort(X[] xs, int from, int to) {
        rangeCheck(xs.length, from, to);
        final Helper<X> helper = getHelper();
        // TO BE IMPLEMENTED
        for (int i = from+1; i < to; i++) {
            for (int j = i - 1; j >= 0; j--) {
                if(helper.compare(xs, j, j+1) > 0) {
                // if (xs[j].compareTo(xs[j + 1]) > 0) {
                    helper.swap(xs, j, j + 1);
                } else {
                    break;
                }
            }
        }
    }
    // 2. 
    static void rangeCheck(int arrayLength, int fromIndex, int toIndex) {
        if (fromIndex > toIndex) {
            throw new IllegalArgumentException("fromIndex(" + fromIndex + ") > toIndex(" + toIndex + ")");
        } else if (fromIndex < 0) {
            throw new ArrayIndexOutOfBoundsException(fromIndex);
        } else if (toIndex > arrayLength) {
            throw new ArrayIndexOutOfBoundsException(toIndex);
        }
    }
		// 3. main method
public static void main(String[] args) throws IOException {
        int repeatTimes = 5;
        int n = 100;

        Config config = Config.load();
        Helper<Integer> helper = HelperFactory.create("InsertionSort", n, config);
        helper.init(n);
        Integer[] nums = helper.random(Integer.class, r -> r.nextInt(1000));
        // random
        double meanTime = new Timer().repeat(repeatTimes, () -> nums, t -> {
            SortWithHelper<Integer> sorter = new InsertionSort<>(helper);
            t = sorter.sort(t);
            assertTrue(helper.sorted(t));
            return null;
        });
        logger.info("random: "+meanTime);

        // ordered
        meanTime = new Timer().repeat(repeatTimes, () -> nums, t -> {
            SortWithHelper<Integer> sorter = new InsertionSort<>(helper);
            t = sorter.sort(t);
            assertTrue(helper.sorted(t));
            return null;
        });
        logger.info("ordered: "+meanTime);

        // partially-order
        UnaryOperator<Integer[]> partiallySort = t->{
            SortWithHelper<Integer> sorter = new InsertionSort<>(helper);
            Random random = new Random();
            int max = random.nextInt(t.length);
            int min = random.nextInt(max);
            sorter.sort(t, min, max);
            return t;
        };
        meanTime = new Timer().repeat(repeatTimes, () -> nums, t -> {
            SortWithHelper<Integer> sorter = new InsertionSort<>(helper);
            t = sorter.sort(t);
            assertTrue(helper.sorted(t));
            return null;
        }, partiallySort, null);
        logger.info("partially-order: "+meanTime);

        // reversed-order
        UnaryOperator<Integer[]> totalSort = t -> {
            return Arrays.stream(t).sorted(Comparator.reverseOrder()).toArray(Integer[]::new);
        };
        meanTime = new Timer().repeat(repeatTimes, () -> nums, t -> {
            SortWithHelper<Integer> sorter = new InsertionSort<>(helper);
            t = sorter.sort(t);
            assertTrue(helper.sorted(t));
            return null;
        }, totalSort, null);
        logger.info("reversed-order: "+meanTime);
    }

```

##### Screenshot

![截屏2021-01-26 上午3.19.46](https://tva1.sinaimg.cn/large/008eGmZEly1gn0kfnv92sj31c00u016c.jpg)

##### Passed Test

![截屏2021-01-26 上午3.24.23](https://tva1.sinaimg.cn/large/008eGmZEly1gn0kfkmuufj31c00u0h2w.jpg)

![截屏2021-01-26 上午3.17.57](https://tva1.sinaimg.cn/large/008eGmZEly1gn0kfbc05dj31ze0h0n1b.jpg)

#### Conclusion

As the screenshot shown, the reversed-order array is the most time-consuming.

The number of comparisons is not certain. The less the number of comparisons, the more the data movement after the insertion point, especially when the total amount of data is huge.

The best time complexity is O(n) when the array is in order, and the worst time complexity is O(n^2) when the array is in reverse order. It is known that the average time complexity of inserting an element into an ordered array is O(n), then n operations are performed, so the average time complexity is O(n^2).

