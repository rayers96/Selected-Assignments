//
//  DynamicArray.cpp
//  
//
//  Created by Ryan Ayers on 4/13/20.
//

#include <stdio.h>
#include <iostream>
#include "Container.hpp"

using namespace std;

template<typename T>
class DynamicArray: public Container<T>{
private:
  int* ptr;
  int a_size;
  int a_capacity;

  void a_resize(){
    a_capacity *= 2;
    int* tmp = ptr;
    ptr = new int[a_capacity];
    for (int i = 0; i < a_size; i++) {
      ptr[i] = tmp[i];
    }
    delete[] tmp;
  }
  //This method move elements starting at index+1 and size-1 to the left by one position
  // It is not explicitly required, but it is better than writing it within remove() method.
  void shrink(int index){
    for (;index < a_size - 1; index++) {
      ptr[index] = ptr[index+1];
    }
  }
public:
  DynamicArray() {
    ptr = new int[10];
    a_size=0;
    a_capacity = 10;
  }
  int size() {
    return a_size;
  }
  void add(T elem) {
    ptr[a_size] = elem;
    a_size++;
    if (a_size == a_capacity) a_resize();
  }
  void remove(T elem) {
    for (int i = 0; i < a_size; i++) {
      if (ptr[i] == elem) {
        shrink(i);
        a_size--;
      }
    }
  }
  void print_container() {
    for (int i = 0; i < a_size; i++) {
      cout<<ptr[i];
      cout<<" ";
    }
    cout<<endl;
  }
  ~DynamicArray() {
    delete[] ptr;
  }
};
