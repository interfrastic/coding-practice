//
//  main.c
//  intspew
//
//  Created by Michael Krueger on 2/11/18.
//  Copyright © 2018 Michael J. Krueger. All rights reserved.
//

#include <stdio.h>

int main(int argc, const char * argv[]) {
    int i;

    for (i = 0; i < 1000000000; i++) {
        printf("%d\n", i);
    }

    return 0;
}
