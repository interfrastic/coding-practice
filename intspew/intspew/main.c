//
//  main.c
//  intspew
//
//  Created by Michael Krueger on 2/12/18.
//  Copyright © 2018 Michael J. Krueger. All rights reserved.
//

#include <stdint.h>
#include <stdio.h>
#include <stdlib.h>
#include <time.h>

static inline uint32_t rand_uint32(void) {
    return ((uint32_t) rand() << 1) | ((uint32_t) rand() & 1);
}

int main(int argc, const char * argv[]) {
    long count = 0;

    long arg;

    if (argc >= 2 && sscanf(argv[1], "%ld", &arg) == 1) {
        count = arg;
    }

    srand((unsigned)time(NULL));

    long i;

    for (i = 0; i < count; i++) {
        printf("%u\n", rand_uint32());
    }

    return 0;
}
