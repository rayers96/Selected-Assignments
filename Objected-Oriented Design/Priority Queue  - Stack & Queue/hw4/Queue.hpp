//
//  Queue.hpp
//  hw4
//
//  Created by Ryan Ayers on 4/12/20.
//

#ifndef Queue_hpp
#define Queue_hpp

#include "PriorityQueue.hpp"
#include <stdio.h>

template <typename T>
class Queue : public PriorityQueue<T> {
private:
  DynamicArray<T>* q;
public:
  Queue();
  ~Queue();
  void enqueue(T data);
  T dequeue();
  T peek();
  bool isEmpty();
};
#endif /* Queue_hpp */
