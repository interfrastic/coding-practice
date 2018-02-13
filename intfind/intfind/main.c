//
//  main.c
//  intfind
//
//  Created by Michael Krueger on 2/12/18.
//  Copyright © 2018 Michael J. Krueger. All rights reserved.
//

#include <limits.h>
#include <stdbool.h>
#include <stdio.h>
#include <stdlib.h>

typedef unsigned short bin_t;

#define BITS_PER_BIN    (sizeof (bin_t) * CHAR_BIT)
#define BINS_SIZE       ((INT_MAX / BITS_PER_BIN) + 1)

static void record_used_int(bin_t *bins, int n) {
    int bin_index = n / BITS_PER_BIN;
    bin_t bin_mask = 1ul << (n % BITS_PER_BIN);

    bins[bin_index] |= bin_mask;
}

static int find_lowest_unused_int(bin_t *bins) {
    for (int n = 0, bin_index = 0; bin_index < BINS_SIZE; bin_index++) {
        for (int bit = 0; bit < BITS_PER_BIN; bit++, n++) {
            bin_t bin_mask = 1ul << bit;

            if ((bins[bin_index] & bin_mask) == 0) {
                return n;
            }
        }
    }

    return -1;
}

int main(int argc, const char * argv[]) {
    bin_t * bins = calloc(BINS_SIZE, sizeof (bin_t));
    int n;
    long count = 0;

    while (scanf("%d", &n) == 1 && n >= 0) {
        record_used_int(bins, n);
        count++;
    }

    printf("Read %ld non-negative integer(s).\n", count);

    int found = find_lowest_unused_int(bins);

    if (found >= 0) {
        printf("The lowest unused non-negative integer is %d.\n", found);
    } else {
        printf("All possible non-negative integer values have been used.\n");
    }

    free(bins);

    return 0;
}
