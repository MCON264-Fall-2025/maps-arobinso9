package hashmap_exercises;

import java.util.HashMap;
import java.util.Map;

/**
 * LeetCode 2342 - Max Sum of a Pair With Equal Sum of Digits
 */
public class MaxSumPairEqualDigitSum {

    /**
     * @param nums input array
     * @return maximum sum of a pair of numbers sharing the same digit sum,
     * or -1 if no such pair exists
     */
    public int maximumSum(int[] nums) {
        // Map: Key = Digit Sum, Value = The largest number seen so far with this digit sum
        Map<Integer, Integer> maxSumForDigit = new HashMap<>();

        // Initialize the result to -1, as required if no pair is found
        int maxPairSum = -1;

        for (int num : nums) {
            // 1. Calculate the canonical key (the digit sum)
            int dSum = digitSum(num);

            // 2. Check if we have already seen a number with this digit sum
            if (maxSumForDigit.containsKey(dSum)) {

                // If the key exists, it means we have a potential pair (num, bestSoFar).
                int bestSoFar = maxSumForDigit.get(dSum);

                // Calculate the sum of the potential pair
                int currentPairSum = num + bestSoFar;

                // Update the overall maximum pair sum
                maxPairSum = Math.max(maxPairSum, currentPairSum);

                // IMPORTANT: Update the bestSoFar value for this digit sum.
                // Since 'num' might be larger than 'bestSoFar', we must store the larger
                // of the two so that the next number with this digit sum forms the largest
                // possible pair with the number we store here.
                maxSumForDigit.put(dSum, Math.max(bestSoFar, num));

            } else {
                // If the key does not exist, this is the first number seen with this digit sum.
                // Store it as the 'bestSoFar' (which is just the number itself).
                maxSumForDigit.put(dSum, num);
            }
        }

        return maxPairSum;
    }

    /**
     * Helper method: Computes the sum of digits of a non-negative integer.
     */
    int digitSum(int x) {
        int sum = 0;
        // The standard way to get digits: repeatedly take modulo 10 and integer division by 10.
        while (x > 0) {
            sum += x % 10;
            x /= 10;
        }
        return sum;
    }
}