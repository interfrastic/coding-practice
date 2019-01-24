//
//  main.c
//  sherlockandthevalidstring
//
//  Created by Michael Krueger on 1/23/19.
//  Copyright © 2019 Michael Krueger. All rights reserved.
//

#include <assert.h>
#include <limits.h>
#include <math.h>
#include <stdbool.h>
#include <stddef.h>
#include <stdint.h>
#include <stdio.h>
#include <stdlib.h>
#include <string.h>

char* readline(void);

////////////////////////////////////////////////////////////////////////////////
//
// HackerRank: Strings: Sherlock and the Valid String
//
// https://www.hackerrank.com/challenges/sherlock-and-valid-string/problem
//
// First attempt: try boneheaded slow, simple method.
//
// Score: 28.82  Status: Terminated due to timeout
//
// https://www.hackerrank.com/challenges/sherlock-and-valid-string/submissions/code/96721966
//
// Second attempt: try counting the occurrences of each of two distinct counts.
//
// Score: 35.00  Status: Accepted
//
// https://www.hackerrank.com/challenges/sherlock-and-valid-string/submissions/code/96822028

// Complete the isValid function below.

// Please either make the string static or allocate on the heap. For example,
// static char str[] = "hello world";
// return str;
//
// OR
//
// char* str = "hello world";
// return str;
//

char * isValid(char * s) {
    int counts[26] = {0};
    char c;

    while ((c = *s++) != 0) {
        counts[c - 'a']++;
    }

    int distinctCount1 = 0;
    int distinctCount1Freq = 0;
    int distinctCount2 = 0;
    int distinctCount2Freq = 0;

    for (int i = 0; i < 26; i++) {
        int count = counts[i];

        // Ignore empty bins.

        if (count == 0) {
            continue;
        }

        // Keep track of the first and second distinct letter counts and the
        // number of times each has occurred so far.

        if (distinctCount1 == 0 || count == distinctCount1) {
            distinctCount1 = count;
            distinctCount1Freq++;
        } else if (distinctCount2 == 0 || count == distinctCount2) {
            distinctCount2 = count;
            distinctCount2Freq++;
        }

        // If we see a third distinct letter count, we know we can't fix it with
        // a single deletion.

        if (count != distinctCount1
            && distinctCount2 != 0 && count != distinctCount2) {
            return "NO";
        }
    }

    // If there are two distinct letter counts, then see if we can fix things
    // with a deletion.

    if (distinctCount2Freq > 0) {

        // If each distinct letter count is shared by more than one letter, then
        // there is no way to fix it with a single deletion.

        if (distinctCount1Freq > 1 && distinctCount2Freq > 1) {
            return "NO";
        }

        // If we reach this point, at least one of the distinct counts occurs
        // for exactly one letter; first, we figure out which count is which.

        int loneDistinctCount;
        int otherDistinctCount;

        if (distinctCount1Freq == 1) {
            loneDistinctCount = distinctCount1;
            otherDistinctCount = distinctCount2;
        } else {
            loneDistinctCount = distinctCount2;
            otherDistinctCount = distinctCount1;
        }

        // A deletion is possible either if the lone non-matching letter occurs
        // exactly once, or if it occurs exactly once more than the other
        // letters; otherwise, the string is invalid and cannot be fixed.

        if (loneDistinctCount != 1
              && loneDistinctCount - otherDistinctCount != 1) {
            return "NO";
        }
    }

    return "YES";
}

////////////////////////////////////////////////////////////////////////////////

int main()
{
//    FILE* fptr = fopen(getenv("OUTPUT_PATH"), "w");
    FILE* fptr = stdout;

    char* s = readline();

    char* result = isValid(s);

    fprintf(fptr, "%s\n", result);

    fclose(fptr);

    return 0;
}

char* readline() {
    size_t alloc_length = 1024;
    size_t data_length = 0;
    char* data = malloc(alloc_length);

    while (true) {
        char* cursor = data + data_length;
        char* line = fgets(cursor, (int)(alloc_length - data_length), stdin);

        if (!line) { break; }

        data_length += strlen(cursor);

        if (data_length < alloc_length - 1 || data[data_length - 1] == '\n') { break; }

        size_t new_length = alloc_length << 1;
        data = realloc(data, new_length);

        if (!data) { break; }

        alloc_length = new_length;
    }

    if (data[data_length - 1] == '\n') {
        data[data_length - 1] = '\0';
    }

    data = realloc(data, data_length);

    return data;
}
