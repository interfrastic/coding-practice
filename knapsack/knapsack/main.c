//
//  main.c
//  knapsack
//
//  Created by Michael Krueger on 2/5/19.
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

char* readline(FILE*);
char** split_string(char*);

////////////////////////////////////////////////////////////////////////////////
//
// HackerRank: Dynamic Programming: Knapsack
//
// https://www.hackerrank.com/challenges/unbounded-knapsack/problem
//
// First attempt: try simplest approach that will work.
//
// Score: 38.13  Status: Terminated due to timeout
//
// https://www.hackerrank.com/challenges/unbounded-knapsack/submissions/code/98168832
//
// Second attempt: try some simple optimizations.
//
// Score: 53.75  Status: Terminated due to timeout
//
// https://www.hackerrank.com/challenges/unbounded-knapsack/submissions/code/98170359
//
// Third attempt: try to detect some impossible cases up front.
//
// Score: 53.75  Status: Terminated due to timeout
//
// https://www.hackerrank.com/challenges/unbounded-knapsack/submissions/code/98170834
//
// Fourth attempt: unlock the timeout test case 10 for a clue to optimization.
//
// Score: 50.63  Status: Terminated due to timeout
//
// https://www.hackerrank.com/challenges/unbounded-knapsack/submissions/code/98252521
//
// Fifth attempt: start over with comletely new approach.
//
// Score: 60.00  Status: Accepted
//
// https://www.hackerrank.com/challenges/unbounded-knapsack/submissions/code/98254838

// Complete the unboundedKnapsack function below.

int unboundedKnapsack(int k, int n, int * arr) {
    int maxSum = 0;

    for (int i = 0; i < n; i++) {
        int value = arr[i];

        if (value == k) {
            return k;
        }

        if (value > k) {
            continue;
        }

        int sum = value + unboundedKnapsack(k - value, n, arr);

        if (sum == k) {
            return k;
        }

        if (sum > maxSum) {
            maxSum = sum;
        }
    }

    return maxSum;
}

////////////////////////////////////////////////////////////////////////////////

int main()
{
    char* ip = getenv("INPUT_PATH");
    FILE* ifptr = (ip == NULL) ? stdin : fopen(ip, "r");
    char* op = getenv("OUTPUT_PATH");
    FILE* ofptr = (op == NULL) ? stdout : fopen(op, "w");

    char* t_endptr;
    char* t_str = readline(ifptr);
    int t = (int)strtol(t_str, &t_endptr, 10);

    if (t_endptr == t_str || *t_endptr != '\0') { exit(EXIT_FAILURE); }

    while (t-- > 0) {
        char** nk = split_string(readline(ifptr));

        char* n_endptr;
        char* n_str = nk[0];
        int n = (int)strtol(n_str, &n_endptr, 10);

        if (n_endptr == n_str || *n_endptr != '\0') { exit(EXIT_FAILURE); }

        char* k_endptr;
        char* k_str = nk[1];
        int k = (int)strtol(k_str, &k_endptr, 10);

        if (k_endptr == k_str || *k_endptr != '\0') { exit(EXIT_FAILURE); }

        char** arr_temp = split_string(readline(ifptr));

        int* arr = malloc(n * sizeof(int));

        for (int i = 0; i < n; i++) {
            char* arr_item_endptr;
            char* arr_item_str = *(arr_temp + i);
            int arr_item = (int)strtol(arr_item_str, &arr_item_endptr, 10);

            if (arr_item_endptr == arr_item_str || *arr_item_endptr != '\0') { exit(EXIT_FAILURE); }

            *(arr + i) = arr_item;
        }

        int arr_count = n;

        int result = unboundedKnapsack(k, arr_count, arr);

        fprintf(ofptr, "%d\n", result);
    }

    fclose(ofptr);

    return 0;
}

char* readline(FILE* ifptr) {
    size_t alloc_length = 1024;
    size_t data_length = 0;
    char* data = malloc(alloc_length);

    while (true) {
        char* cursor = data + data_length;
        char* line = fgets(cursor, (int)(alloc_length - data_length), ifptr);

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

char** split_string(char* str) {
    char** splits = NULL;
    char* token = strtok(str, " ");

    int spaces = 0;

    while (token) {
        splits = realloc(splits, sizeof(char*) * ++spaces);
        if (!splits) {
            return splits;
        }

        splits[spaces - 1] = token;

        token = strtok(NULL, " ");
    }

    return splits;
}
