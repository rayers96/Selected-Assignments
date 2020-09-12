//
//  Stack.cpp
//  hw4
//
//  Created by Ryan Ayers on 4/12/20.
//

#include "Stack.hpp"

template <typename T>
Stack<T>::Stack() {
  q = new DynamicArray<T>;
}
template <typename T>
Stack<T>::~Stack() {
  ~q;
}
template <typename T>
void Stack<T>::enqueue(T data) {
  q->add(data);
}
template <typename T>
T Stack<T>::dequeue() {
  T val = q->get(q->size()-1);
  q->remove(q->size()-1);
  return val;
}
template <typename T>
T Stack<T>::peek() {
  return q->get(q->size()-1);
}
template <typename T>
bool Stack<T>::isEmpty() {
  return q->size() == 0;
}
