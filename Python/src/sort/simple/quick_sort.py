from random import randint


class QuickSort(object):
    def __init__(self, array):
        self.array = array

    def get_array(self):
        return self.array

    def shuffle(self):
        n = len(self.array)
        for i in range(n):
            r = randint(0, i)
            self.array[i], self.array[r] = self.array[r], self.array[i]

    @staticmethod
    def partition(array, low, high):
        # print("partition")
        i = low
        j = high
        while True:
            while array[i] < array[low]:
                i += 1
                if i == high:
                    break
            while array[low] < array[j]:
                j -= 1
                if j == low:
                    break
            if i >= j:
                break
            array[i], array[j] = array[j], array[i]
        array[low], array[j] = array[j], array[low]
        return j

    def sort(self):
        self.shuffle()
        self.sort_helper(self.array, 0, len(self.array) - 1)

    def sort_helper(self, array, low, high):
        # print("sort")
        if high <= low:
            return
        j = self.partition(array, low, high)
        self.sort_helper(array, low, j - 1)
        self.sort_helper(array, j + 1, high)

# x = QuickSort([2,3,4,1,8,7,5,9,6])

# x.sort()
# print(x.get_array())
