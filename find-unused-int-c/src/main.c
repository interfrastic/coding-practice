//
//  main.c
//  findunusedint
//
//  Created by Michael Krueger on 2/12/18.
//  Copyright © 2018 Michael J. Krueger. All rights reserved.
//

#include <limits.h>
#include <malloc.h>
#include <stdbool.h>
#include <stdio.h>

typedef unsigned long bin_t;

#define BIN_BIT sizeof (bin_t) * CHAR_BIT
#define SEEN_SIZE INT_MAX / BIN_BIT

int main(int argc, const char * argv[]) {
    long count = 0;

    bin_t * seen = malloc(SEEN_SIZE * sizeof (bin_t));

    int n;

    while (scanf("%d", &n) == 1) {
        seen[n / BIN_BIT] |= (1ul << (n % BIN_BIT));
        count++;
    }

    printf("Read %ld integer(s).\n", count);

    bool taken;

    n = 0;

    while ((taken = (seen[n / BIN_BIT] & (1ul << (n % BIN_BIT)))) != 0
            && ++n / BIN_BIT < SEEN_SIZE);

    if (taken) {
        printf("All possible integer values have been used.\n");
    } else {
        printf("The lowest unused integer is %d.\n", n);
    }

    free(seen);

    return 0;
}
