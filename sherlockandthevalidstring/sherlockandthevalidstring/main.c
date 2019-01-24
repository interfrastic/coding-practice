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

#define MIN_CHAR 'a'
#define MAX_CHAR 'z'
#define COUNTS_SIZE (MAX_CHAR - MIN_CHAR + 1)

bool areAllCountsEqual(char * s, size_t len, size_t ignoreIndex) {
    int counts[COUNTS_SIZE] = {0};

    for (int index = 0; index < len; index++) {
        if (index != ignoreIndex) {
            counts[s[index] - MIN_CHAR]++;
        }
    }

    int sharedCount = 0;

    for (int index = 0; index < COUNTS_SIZE; index++) {
        if (counts[index] > 0) {
            if (sharedCount == 0) {
                sharedCount = counts[index];
            } else if (counts[index] != sharedCount) {
                return false;
            }
        }
    }

    return true;
}

char * isValid(char * s) {
    size_t len = strlen(s);

    if (areAllCountsEqual(s, len, -1)) {
        return "YES";
    }

    for (int i = 0; i < len; i++) {
        if (areAllCountsEqual(s, len, i)) {
            return "YES";
        }
    }

    return "NO";
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
