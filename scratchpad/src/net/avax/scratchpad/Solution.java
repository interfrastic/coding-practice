package net.avax.scratchpad;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

class Solution {

    // 2018-05-09 techlet problem:
    //
    // https://leetcode.com/problems/can-place-flowers/description/
    //
    // 123 / 123 test cases passed.
    // Status: Accepted
    // Runtime: 12 ms
    // Your runtime beats 90.38 % of java submissions.
    //
    // https://leetcode.com/submissions/detail/153527199/

    public boolean canPlaceFlowers(int[] flowerbed, int n) {
        int length = flowerbed.length;
        int maxIndex = length - 1;
        int index = 0;

        while (n > 0 && index < length) {
            if ((index == 0 || flowerbed[index - 1] == 0)
                    && (flowerbed[index] == 0)
                    && (index == maxIndex || flowerbed[index + 1] == 0)) {
                flowerbed[index] = 1;
                n--;
            }

            index++;
        }

        return n == 0;
    }

    // 2018-05-09 techlet bonus problem:
    //
    // https://leetcode.com/problems/find-duplicate-file-in-system/description/
    //
    // 161 / 161 test cases passed.
    // Status: Accepted
    // Runtime: 128 ms
    // Your runtime beats 28.29 % of java submissions.
    //
    // https://leetcode.com/submissions/detail/153530161/

    public List<List<String>> findDuplicate(String[] dirListings) {
        Map<String, List<String>> pathsForContent = new HashMap<>();

        for (String dirListing : dirListings) {
            String[] dirListingElems = dirListing.split(" ");
            int dirListingElemCount = dirListingElems.length;
            String dir = dirListingElems[0];
            int index = 1;

            while (index < dirListingElemCount) {
                String entryListing = dirListingElems[index];
                String[] entryListingElems = entryListing.split("[()]");
                String file = entryListingElems[0];
                String content = entryListingElems[1];
                String path = dir + "/" + file;

//                pathsForContent.computeIfAbsent(content,
//                        k -> new ArrayList<>()).add(path);

                List<String> paths = pathsForContent.get(content);

                if (paths == null) {
                    paths = new ArrayList<>();
                    pathsForContent.put(content, paths);
                }

                paths.add(path);
                index++;
            }
        }

        List<List<String>> result = new ArrayList<>();

        for (List<String> dupPaths : pathsForContent.values()) {
            if (dupPaths.size() > 1) {
                result.add(dupPaths);
            }
        }

        return result;
    }

    // 2018-05-09 techlet special Rupi bonus problem:
    //
    // https://leetcode.com/problems/rotate-array/description/

    public void rotate(int[] nums, int k) {
    }
}
