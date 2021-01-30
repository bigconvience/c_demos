//
//  main.c
//  Test
//
//  Created by benpeng.jiang on 2020/11/11.
//

#include <stdio.h>


void array_1() {
    const int d= 3;
    int k[26] = {1, 2, 3, [d ... 25]=7};
    for (int i = 0; i < 26; i++) {
        printf("%d %d\n", i, k[i]);
    }
}

int compute_stack_size(int *pSize) {
    *pSize = 5;
    
    return 1;
}


int main(int argc, const char * argv[]) {
//    array_1();
    
    int pSize;
    int ret = compute_stack_size(&pSize);
    if (ret) {
        printf("pSize:%d\n", pSize);
    }
    return 0;
}

