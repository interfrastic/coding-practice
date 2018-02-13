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

typedef unsigned long bin_t;

#define BITS_PER_BIN    (sizeof (bin_t) * CHAR_BIT)
#define MAX_BIN_INDEX   (INT_MAX / BITS_PER_BIN)
#define BINS_SIZE       (MAX_BIN_INDEX + 1)
#define bin_index(n)    (n / BITS_PER_BIN)
#define bin_mask(n)     (1ul << (n % BITS_PER_BIN))

int main(int argc, const char * argv[]) {
    long count = 0;

    bin_t * bins = malloc(BINS_SIZE * sizeof (bin_t));

    int n;

    while (scanf("%d", &n) == 1) {
        bins[bin_index(n)] |= bin_mask(n);
        count++;
    }

    printf("Read %ld integer(s).\n", count);

    bool taken;

    n = 0;

    while ((taken = (bins[bin_index(n)] & bin_mask(n))) != 0
           && bin_index(++n) < BINS_SIZE);

    if (taken) {
        printf("All possible integer values have been used.\n");
    } else {
        printf("The lowest unused integer is %d.\n", n);
    }

    free(bins);

    return 0;
}
