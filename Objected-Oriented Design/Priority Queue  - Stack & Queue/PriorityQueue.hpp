//
//  PriorityQueue.hpp
//  hw4
//
//  Created by Ryan Ayers on 4/12/20.
//

#ifndef PriorityQueue_hpp
#define PriorityQueue_hpp

#include <stdio.h>
#include "DynamicArray.cpp"

template <typename T>
class PriorityQueue {
private:
  DynamicArray<T>* q;
public:
  virtual void enqueue(T data) = 0;
  virtual T dequeue() = 0;
  virtual T peek() = 0;
  virtual bool isEmpty() = 0;
};
#endif /* PriorityQueue_hpp */
