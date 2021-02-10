//
//  main.c
//  C Stand http://www.iso-9899.info/wiki/The_Standard
//
//  demos
//

#include <stdio.h>
#include "list.h"


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
    JSValue atom = JS_MKPTR(JS_TAG_STRING, (void *)str);
    printJSValue(atom);
}


typedef struct Book {
    int price;
    const char *name;
    struct list_head link;
} Book;

void list_test() {
    printf("\nlist test--->\n");
    struct list_head head, *sentinel = &head;
    init_list_head(sentinel);
    
    Book b1 = {1, "one"};
    init_list_head(&b1.link);
    list_add(&b1.link, sentinel);

    Book b2 = {2, "two"};
    init_list_head(&b2.link);
    list_add(&b2.link, sentinel);
    
    Book b3 = {3, "three"};
    init_list_head(&b3.link);
    list_add(&b3.link, sentinel);
    
    struct list_head *el;

    list_for_each(el, sentinel) {
        Book *book = list_entry(el, Book, link);
        
        printf("book, price:%d, name:%s \n", book->price, book->name);
    }
    
    list_for_each_prev(el, sentinel) {
        Book *book = list_entry(el, Book, link);
        
        printf("book, price:%d, name:%s \n", book->price, book->name);
    }
    
    struct list_head *el1;
    list_for_each_safe(el, el1, sentinel) {
        Book *book = list_entry(el, Book, link);
        
        printf("book, price:%d, name:%s \n", book->price, book->name);
    }
    
    list_for_each_prev_safe(el, el1, sentinel) {
        Book *book = list_entry(el, Book, link);
        
        printf("book, price:%d, name:%s \n", book->price, book->name);
    }
}

void list_test_tail() {
    printf("\nlist test tail--->\n");
    struct list_head head, *sentinel = &head;
    init_list_head(sentinel);
    
    Book b1 = {1, "one"};
    init_list_head(&b1.link);
    list_add_tail(&b1.link, sentinel);

    Book b2 = {2, "two"};
    init_list_head(&b2.link);
    list_add_tail(&b2.link, sentinel);
    
    Book b3 = {3, "three"};
    init_list_head(&b3.link);
    list_add_tail(&b3.link, sentinel);
    
    struct list_head *el;

    list_for_each(el, sentinel) {
        Book *book = list_entry(el, Book, link);
        
        printf("book, price:%d, name:%s \n", book->price, book->name);
    }
    
    list_for_each_prev(el, sentinel) {
        Book *book = list_entry(el, Book, link);
        
        printf("book, price:%d, name:%s \n", book->price, book->name);
    }
    
    struct list_head *el1;
    list_for_each_safe(el, el1, sentinel) {
        Book *book = list_entry(el, Book, link);
        
        printf("book, price:%d, name:%s \n", book->price, book->name);
    }
    
    list_for_each_prev_safe(el, el1, sentinel) {
        Book *book = list_entry(el, Book, link);
        
        printf("book, price:%d, name:%s \n", book->price, book->name);
    }
}

int main(int argc, const char * argv[]) {
//    array_1();
    
    int pSize;
    int ret = compute_stack_size(&pSize);
    if (ret) {
        printf("pSize:%d\n", pSize);
    }
    
    macro_test1();
    
    list_test();
    list_test_tail();
    
    return 0;
}

