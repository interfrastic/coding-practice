//
//  main.c
//  intfind
//
//  Created by Michael Krueger on 2/12/18.
//  Copyright © 2018 Michael J. Krueger. All rights reserved.
//

// From Programming Pearls, Second Edition, by Jon Bentley, Addison-Wesley,
// Inc., 2000, ISBN 0-201-65788-0:
//
// Given a sequential file that contains at most four billion integers in
// random order, find a 32-bit integer that isn't in the file (and there must
// be at least one missing--why?).  How would you solve this problem with ample
// quantities of main memory? How would you solve it if you could use several
// external "scratch" files but only a few hundred bytes of main memory?

#include <limits.h>
#include <stdbool.h>
#include <stdint.h>
#include <stdio.h>
#include <stdlib.h>

typedef uint64_t bin_t;

#define COUNT_MAX       (4000000000)
#define BITS_PER_BIN    (sizeof (bin_t) * CHAR_BIT)
#define BINS_SIZE       ((UINT32_MAX / BITS_PER_BIN) + 1)

static inline void record_used_int(bin_t * bins, uint32_t n) {
    bins[n / BITS_PER_BIN] |= 1 << (n % BITS_PER_BIN);
}

static inline uint32_t find_lowest_unused_int(bin_t *bins) {
    uint32_t n = 0;

    while ((bins[n / BITS_PER_BIN] & (1 << (n % BITS_PER_BIN))) != 0
            && ++n < UINT32_MAX);

    return n;
}

int main(int argc, const char * argv[]) {
    bin_t * bins = calloc(BINS_SIZE, sizeof (bin_t));
    uint32_t n;
    long count = 0;

    while (scanf("%u", &n) == 1 && count < COUNT_MAX) {
        record_used_int(bins, n);
        count++;
    }

    printf("Read %ld 32-bit unsigned integer(s).\n", count);

    n = find_lowest_unused_int(bins);

    printf("%d is the lowest unused integer.\n", n);

    free(bins);

    return 0;
}
