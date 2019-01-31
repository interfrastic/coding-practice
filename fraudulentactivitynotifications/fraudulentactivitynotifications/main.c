//
//  main.c
//  fraudulentactivitynotifications
//
//  Created by Michael Krueger on 1/29/19.
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
char** split_string(char*);

////////////////////////////////////////////////////////////////////////////////
//
// HackerRank: Sorting: Fraudulent Activity Notifications
//
// https://www.hackerrank.com/challenges/fraudulent-activity-notifications/problem
//
// First attempt: simplest solution that works.
//
// Score: 0.00  Status: Terminated due to timeout
//
// https://www.hackerrank.com/challenges/fraudulent-activity-notifications/submissions/code/97447491

// Complete the activityNotifications function below.

int compar (const void * a, const void * b) {
    return ( *(int *)a - *(int *)b );
}

double getMedian(int i, int d, int * values, int * sortedValues) {
    memcpy(sortedValues, values + i, d * sizeof(int));

    qsort(sortedValues, d, sizeof(int), compar);

    double median = 0.0;
    int quotient = d / 2;
    int remainder = d % 2;

    if (remainder == 0) {
        median = (sortedValues[quotient - 1] + sortedValues[quotient])/2.0;
    } else {
        median = sortedValues[quotient];
    }

    return median;
}

int activityNotifications(int expendituresLength, int * expenditures, int d) {
    int * sortedExpenditures = malloc(d * sizeof(int));

    int notificationCount = 0;

    for (int i = 0; i < expendituresLength; i++) {
        double expenditure = expenditures[i];

        if (i >= d) {
            double medianExpenditure
                = getMedian(i - d, d, expenditures, sortedExpenditures);

            if (expenditure >= 2 * medianExpenditure) {
                notificationCount++;
            }
        }
    }

    free(sortedExpenditures);

    return notificationCount;
}

int main()
{
    char* op = getenv("OUTPUT_PATH");
    FILE* fptr = (op == NULL) ? stdout : fopen(op, "w");

    char** nd = split_string(readline());

    char* n_endptr;
    char* n_str = nd[0];
    int n = (int)strtol(n_str, &n_endptr, 10);

    if (n_endptr == n_str || *n_endptr != '\0') { exit(EXIT_FAILURE); }

    char* d_endptr;
    char* d_str = nd[1];
    int d = (int)strtol(d_str, &d_endptr, 10);

    if (d_endptr == d_str || *d_endptr != '\0') { exit(EXIT_FAILURE); }

    char** expenditure_temp = split_string(readline());

    int* expenditure = malloc(n * sizeof(int));

    for (int i = 0; i < n; i++) {
        char* expenditure_item_endptr;
        char* expenditure_item_str = *(expenditure_temp + i);
        int expenditure_item = (int)strtol(expenditure_item_str, &expenditure_item_endptr, 10);

        if (expenditure_item_endptr == expenditure_item_str || *expenditure_item_endptr != '\0') { exit(EXIT_FAILURE); }

        *(expenditure + i) = expenditure_item;
    }

    int expenditure_count = n;

    int result = activityNotifications(expenditure_count, expenditure, d);

    fprintf(fptr, "%d\n", result);

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
