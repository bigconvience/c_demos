//
//  main.c
//  C Stand http://www.iso-9899.info/wiki/The_Standard
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

enum {
    JS_TAG_STRING      = -7,
    
    JS_TAG_INT = 0,
    JS_TAG_BOOL = 1,
    JS_TAG_NULL = 2,
};

typedef union JSValueUnion {
    int32_t int32;
    double float64;
    void *ptr;
} JSValueUnion;

typedef struct JSValue {
    JSValueUnion u;
    int64_t tag;
} JSValue;

// https://en.cppreference.com/w/c/language/struct_initialization
#define JS_MKVAL(tag, val) (JSValue){(JSValueUnion){.int32=val}, tag}
#define JS_MKPTR(tag, p) (JSValue){(JSValueUnion){.ptr=p}, tag}

#define JS_NULL JS_MKVAL(JS_TAG_NULL, 0)

void printJSValue(JSValue jsvalue) {
    int64_t tag = jsvalue.tag;
    printf("tag: %lld ", tag);
    switch (tag) {
        case JS_TAG_INT:
        case JS_TAG_NULL:
            printf("value: %d \n", jsvalue.u.int32);
            break;
        case JS_TAG_STRING:
            printf("value: %s \n", jsvalue.u.ptr);
            break;
        default:
            break;
    }
}

void macro_test1() {
    JSValue jsNull = JS_NULL;
    printJSValue(jsNull);

    const char *str= "abc";
    JSValue atom = JS_MKPTR(JS_TAG_STRING, str);
    printJSValue(atom);
}


int main(int argc, const char * argv[]) {
//    array_1();
    
    int pSize;
    int ret = compute_stack_size(&pSize);
    if (ret) {
        printf("pSize:%d\n", pSize);
    }
    
    macro_test1();
    return 0;
}

