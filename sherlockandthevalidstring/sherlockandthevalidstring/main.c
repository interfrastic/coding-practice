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
//
// Third attempt: clean up code and comments.
//
// Score: 35.00  Status: Accepted
//
// https://www.hackerrank.com/challenges/sherlock-and-valid-string/submissions/code/96823650
//
// Fourth attempt: fail earlier when no lone frequency found, finish cleanup.
//
// Score: 35.00  Status: Accepted
//
// https://www.hackerrank.com/challenges/sherlock-and-valid-string/submissions/code/96835133

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

    // Use an array as a 26-bin histogram of the letter frequencies.

    int freqs[26] = {0};
    char c;

    while ((c = *s++) != 0) {
        freqs[c - 'a']++;
    }

    // Now analyze the letter frequency histogram to determine whether all non-
    // zero frequencies are equal or, if they are not, whether they can be made
    // equal with a single deletion.

    int firstFreq = 0;
    int firstFreqCount = 0;
    int secondFreq = 0;
    int secondFreqCount = 0;

    for (int i = 0; i < 26; i++) {
        int freq = freqs[i];

        // Ignore empty bins.

        if (freq == 0) {
            continue;
        }

        // Keep track of the first and second distinct letter frequencies and
        // the number of different letters that share each one.

        if (firstFreq == 0 || freq == firstFreq) {
            firstFreq = freq;
            firstFreqCount++;
        } else if (secondFreq == 0 || freq == secondFreq) {
            secondFreq = freq;
            secondFreqCount++;
        } else {

            // If we see a third distinct letter frequency, then we know we
            // can't fix it with a single deletion.

            return "NO";
        }

        // If each distinct letter frequency is shared by more than one letter,
        // then there is no way to fix it with a single deletion.

        if (firstFreqCount > 1 && secondFreqCount > 1) {
            return "NO";
        }
    }

    // If there are exactly two distinct letter frequencies, then see if we can
    // fix things with a single deletion.

    if (secondFreqCount > 0) {

        // If we reach this point, then at least one of the distinct frequencies
        // occurs for exactly one letter; first, we figure out which is which.

        int loneFreq;
        int otherFreq;

        if (firstFreqCount == 1) {
            loneFreq = firstFreq;
            otherFreq = secondFreq;
        } else {
            loneFreq = secondFreq;
            otherFreq = firstFreq;
        }

        // A deletion is possible either if the lone non-matching letter occurs
        // exactly once, or if it occurs exactly once more than the other
        // letters; otherwise, the string is invalid and cannot be fixed.

        if (loneFreq != 1 && loneFreq - otherFreq != 1) {
            return "NO";
        }
    }

    // If we made it here, the string is valid!

    return "YES";
}

////////////////////////////////////////////////////////////////////////////////

int main()
{
    char* op = getenv("OUTPUT_PATH");
    FILE* fptr = (op == NULL) ? stdout : fopen(op, "w");

    char* s = readline();

    char* result = isValid(s);

    fprintf(fptr, "%s\n", result);

    if (fptr != stdout) {
        fclose(fptr);
    }

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
