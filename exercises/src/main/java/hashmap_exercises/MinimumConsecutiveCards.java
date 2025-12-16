package hashmap_exercises;

import java.util.HashMap;
import java.util.Map;

/**
 * LeetCode 2260 - Minimum Consecutive Cards to Pick Up
 */
public class MinimumConsecutiveCards {

    /**
     * @param cards array of card values
     * @return minimum length of a contiguous subarray containing two equal cards,
     * or -1 if no such subarray exists
     */
    public int minimumCardPickup(int[] cards) {
        // Map: Key = Card Value, Value = Last Index where this card value was seen
        Map<Integer, Integer> lastSeen = new HashMap<>();

        // Initialize the minimum length to a value larger than any possible result.
        // Since the max length is cards.length + 1, we use Integer.MAX_VALUE.
        int minLength = Integer.MAX_VALUE;

        // Iterate through the array, using 'i' as the current index
        for (int i = 0; i < cards.length; i++) {
            int currentCard = cards[i];

            // 1. Check if the current card has been seen before
            if (lastSeen.containsKey(currentCard)) {

                // If the card was seen before, its previous index is stored as 'j'
                int j = lastSeen.get(currentCard);

                // Calculate the length of the current subarray containing a pair.
                // The length is (current index i) - (previous index j) + 1
                int currentLength = i - j + 1;

                // Update the overall minimum length found so far
                minLength = Math.min(minLength, currentLength);
            }

            // 2. Always update the map to store the current index 'i' as the most recent sighting
            lastSeen.put(currentCard, i);
        }

        // 3. Return the result
        // If minLength is still Integer.MAX_VALUE, no pair was ever found.
        return (minLength == Integer.MAX_VALUE) ? -1 : minLength;
    }
}