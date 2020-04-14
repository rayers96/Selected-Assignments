//
//  DynamicArray.cpp
//  hw4
//

#include "DynamicArray.hpp"
#include <iostream>

using namespace std;

template <typename T>
void DynamicArray<T>::a_resize() {
  a_capacity *= 2;
  T* tmp = ptr;
  ptr = new T[a_capacity];
  for (int i = 0; i < a_size; i++) {
    ptr[i] = tmp[i];
  }
  delete[] tmp;
}
template <typename T>
void DynamicArray<T>::shrink(int i) {
  for (;i < a_size - 1; i++) {
    ptr[i] = ptr[i+1];
  }
}
template <typename T>
DynamicArray<T>::DynamicArray() {
  ptr = new T[10];
  a_size=0;
  a_capacity = 10;
}
template <typename T>
DynamicArray<T>::~DynamicArray() {
  delete[] ptr;
}
template <typename T>
int DynamicArray<T>::size() {
  return a_size;
}
template <typename T>
void DynamicArray<T>::add(T elem) {
  ptr[a_size] = elem;
  a_size++;
  if (a_size == a_capacity) a_resize();
}
template <typename T>
T DynamicArray<T>::get(int i) {
  return ptr[i];
}
template <typename T>
void DynamicArray<T>::remove(int i) {
  shrink(i);
  a_size--;
}
