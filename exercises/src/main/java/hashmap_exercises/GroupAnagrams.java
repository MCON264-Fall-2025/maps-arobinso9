package hashmap_exercises;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * LeetCode 49 - Group Anagrams
 *
 * Given an array of strings strs, group the anagrams together.
 * You can return the answer in any order.
 *
 * Example:
 * Input:  ["eat","tea","tan","ate","nat","bat"]
 * Output: [["bat"],["nat","tan"],["ate","eat","tea"]] (order may vary)
 */
public class GroupAnagrams {

    /**
     * Groups the given words into lists of anagrams using a canonical key (sorted string).
     *
     * @param strs array of input strings
     * @return a list of groups, where each group is a list of anagrams
     */
    public List<List<String>> groupAnagrams(String[] strs) {
        if (strs == null || strs.length == 0) {
            return Collections.emptyList();
        }

        // Map: Canonical Form (String) -> List of original strings (List<String>)
        Map<String, List<String>> map = new HashMap<>();

        for (String s : strs) {
            // Get the canonical key (e.g., "eat" -> "aet")
            String canonicalKey = getCanonicalKey(s);

            // 1. Get the list associated with the key, or create a new list if the key is absent.
            //    The computeIfAbsent method handles both the check and creation cleanly.
            List<String> anagramList = map.computeIfAbsent(canonicalKey, k -> new ArrayList<>());

            // 2. Add the original string to the list.
            anagramList.add(s);
        }

        // Return all the lists of anagrams (the values of the map).
        return new ArrayList<>(map.values());
    }

    /**
     * Generates a canonical key for a string by sorting its characters.
     * This key is the same for all anagrams.
     * @param s The input string.
     * @return The sorted version of the string.
     */
    private String getCanonicalKey(String s) {
        char[] chars = s.toCharArray();
        Arrays.sort(chars);
        return new String(chars);
    }
}