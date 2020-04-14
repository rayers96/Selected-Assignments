//
//  DynamicArray.hpp
//  hw4
//

#ifndef DynamicArray_hpp
#define DynamicArray_hpp

#include <stdio.h>

template <typename T>
class DynamicArray {
private:
  T* ptr;
  int a_size;
  int a_capacity;
  void a_resize();
  void shrink(int i);
public:
  DynamicArray();
  ~DynamicArray();
  int size();
  void add(T elem);
  T get(int i);
  void remove(int i);
};

#endif /* DynamicArray_hpp */
