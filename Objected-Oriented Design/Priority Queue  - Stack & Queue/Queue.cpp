//
//  Queue.cpp
//  hw4
//
//  Created by Ryan Ayers on 4/12/20.
//

#include "Queue.hpp"

template <typename T>
Queue<T>::Queue() {
  q = new DynamicArray<T>;
}
template <typename T>
Queue<T>::~Queue() {
  ~q;
}
template <typename T>
void Queue<T>::enqueue(T data) {
  q->add(data);
}
template <typename T>
T Queue<T>::dequeue() {
  T val = q->get(0);
  q->remove(0);
  return val;
}
template <typename T>
T Queue<T>::peek() {
  return q->get(0);
}
template <typename T>
bool Queue<T>::isEmpty() {
  return q->size() == 0;
}
