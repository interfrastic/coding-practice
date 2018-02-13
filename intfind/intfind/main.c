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

int main(int argc, const char * argv[]) {
    int n = 0;
    bin_t * bins = calloc(BINS_SIZE, sizeof (bin_t));
    long count = 0;

//    while (scanf("%d", &n) == 1) {
    for (n = 0; n >= 0 && n < INT_MAX; n++) {
        int bin_index = n / BITS_PER_BIN;
        bin_t bin_mask = 1ul << (n % BITS_PER_BIN);

        bins[bin_index] |= bin_mask;
        count++;
    }

    printf("Read %ld integer(s).\n", count);

    bool found = false;
    int bin_index = 0;
    n = 0;

    while (!found && bin_index < BINS_SIZE) {
        int bit = 0;

        while (!found && bit < BITS_PER_BIN) {
            bin_t bin_mask = 1ul << bit;

            if ((bins[bin_index] & bin_mask) == 0) {
                found = true;
            } else {
                n++;
                bit++;
            }
        }

        bin_index++;
    }

    if (found) {
        printf("The lowest unused nonnegative integer is %d.\n", n);
    } else {
        printf("All possible nonnegative integer values have been used.\n");
    }

    free(bins);

    return 0;
}
