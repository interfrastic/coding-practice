//
//  main.c
//  intfind
//
//  Created by Michael Krueger on 2/12/18.
//  Copyright © 2018 Michael J. Krueger. All rights reserved.
//

// From Programming Pearls, Second Edition, by Jon Bentley, Addison-Wesley, Inc.,
// 2000, ISBN 0-201-65788-0:
//
// Given a sequential file that contains at most four billion integers in random
// order, find a 32-bit integer that isn't in the file (and there must be at
// least one missing--why?).  How would you solve this problem with ample
// quantities of main memory? How would you solve it if you could use several
// external "scratch" files but only a few hundred bytes of main memory?

#include <limits.h>
#include <stdbool.h>
#include <stdio.h>
#include <stdlib.h>

typedef unsigned short bin_t;

#define BITS_PER_BIN    (sizeof (bin_t) * CHAR_BIT)
#define BINS_SIZE       ((((unsigned long) INT_MAX - (unsigned long) INT_MIN) / BITS_PER_BIN) + 1)

static void record_used_int(bin_t *bins, int n) {
    unsigned long offset_n = (unsigned long) n - (unsigned long) INT_MIN;
    unsigned long bin_index = offset_n / BITS_PER_BIN;
    bin_t bin_mask = 1ul << (offset_n % BITS_PER_BIN);

    bins[bin_index] |= bin_mask;
}

static int find_unused_int(bin_t *bins) {
    for (int n = INT_MIN; n < INT_MAX; n++) {
        unsigned long offset_n = (unsigned long) n - (unsigned long) INT_MIN;
        unsigned long bin_index = offset_n / BITS_PER_BIN;
        bin_t bin_mask = 1ul << (offset_n % BITS_PER_BIN);

        if ((bins[bin_index] & bin_mask) == 0) {
            return n;
        }
    }

    return INT_MAX;
}

int main(int argc, const char * argv[]) {
    bin_t * bins = calloc(BINS_SIZE, sizeof (bin_t));
    int n;
    long count = 0;

    while (scanf("%d", &n) == 1 && count < 4000000000) {
        record_used_int(bins, n);
        count++;
    }

    printf("Read %ld integer(s).\n", count);

    n = find_unused_int(bins);

    printf("%d is not in the file.\n", n);

    free(bins);

    return 0;
}
