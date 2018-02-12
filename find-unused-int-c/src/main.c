//
//  main.c
//  findunusedint
//
//  Created by Michael Krueger on 2/12/18.
//  Copyright © 2018 Michael J. Krueger. All rights reserved.
//

#include <stdio.h>

int main(int argc, const char * argv[]) {
    int count = 0;
    int n;

    while (scanf("%d", &n) == 1) {
        count++;
    }

    printf("Read %d integer(s).\n", count);

    return 0;
}
