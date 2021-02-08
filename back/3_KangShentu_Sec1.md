### Author

[Kang Shentu](shentu.k@northeastern.edu)

001569432

### Part1

#### Implementation

```java
		// 1. find method
    public int find(int p) {
        validate(p);
        int root = p;
        while (root != getParent(root)) {
            if (pathCompression) {
                doPathCompression(root);
            }
            root = getParent(root);
        }
        return root;
    }

    // 2. mergeComponents
    private void mergeComponents(int i, int j) {
        // TO BE IMPLEMENTED make shorter root point to taller one
        if (height[i] < height[j]) {
            updateParent(i, j);
            updateHeight(j, i);
        } else {
            updateParent(j, i);
            updateHeight(i, j);
        }
    }

		// 3. doPathCompression
    private void doPathCompression(int i) {
        // TO BE IMPLEMENTED update parent to value of grandparent
        updateParent(i, getParent(getParent(i)));
    }
```

#### Screenshot

![截屏2021-02-08 下午2.11.28](https://tva1.sinaimg.cn/large/008eGmZEly1gng3j9jry4j31c00u0drz.jpg)

### Part2

#### Implementation

```java
		/**
     * @param n the number of sites
     * @return the number of connections
     */
    private static int count(int n) {
        UF_HWQUPC client = new UF_HWQUPC(n);
        int count = 0;
        for (int i = 0; i < n; i++) {
            for (int j = i; j < n; j++) {
                if (!client.isConnected(i,j)){
                    client.union(i,j);
                    count++;
                }
            }
        }
        return count;
    }

    public static void main(String[] args) throws IOException {
        Random random = new Random();
        for (int times = 0; times < 100; times++) {
            int n = random.nextInt(100);
            int count = count(n);
            System.out.printf("n is %d, count is %d\n", n, count);
        }
    }
```

#### Screenshot

![截屏2021-02-08 下午2.17.04](https://tva1.sinaimg.cn/large/008eGmZEly1gng3p4kk6qj31c00u0anc.jpg)

### Part3

#### Relationship

N is the number of objects and M is the number of pairs generated to accomplish the requirement.
$$
M = N - 1
$$
For the purpose of connecting all sites, each site should connect a certain site, to more specific, the site which has the biggest value.

So the conclusion is obvious.