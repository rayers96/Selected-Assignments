//
//  Stack.hpp
//  hw4
//
//  Created by Ryan Ayers on 4/12/20.
//

#ifndef Stack_hpp
#define Stack_hpp

#include "PriorityQueue.hpp"
#include <stdio.h>

template <typename T>
class Stack : public PriorityQueue<T> {
private:
  DynamicArray<T>* q;
public:
  Stack();
  ~Stack();
  void enqueue(T data);
  T dequeue();
  T peek();
  bool isEmpty();
};
#endif /* Stack_hpp */
