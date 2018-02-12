//
//  main.c
//  intspew
//
//  Created by Michael Krueger on 2/11/18.
//  Copyright © 2018 Michael J. Krueger. All rights reserved.
//

#include <stdio.h>
#include <stdlib.h>
#include <time.h>

int main(int argc, const char * argv[]) {
    int n = 0;

    int arg;

    if (argc >= 2 && sscanf(argv[1], "%d", &arg) == 1) {
        n = arg;
    }

    srand(time(NULL));

    int i;

    for (i = 0; i < n; i++) {
        printf("%d\n", rand());
    }

    return 0;
}
