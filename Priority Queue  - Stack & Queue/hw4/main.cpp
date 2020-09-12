//
//  main.cpp
//  hw4
//
//  Created by Ryan Ayers on 4/12/20.
//

#include <stdio.h>
#include "Stack.cpp"
#include "Queue.cpp"

//int main(int argc, char* args[]) {
//  cout<<"Stack"<<endl;
//  PriorityQueue<int>* s = new Stack<int>();
//  cout<<s->isEmpty()<<endl;
//  s->enqueue(3);
//  cout<<s->isEmpty()<<endl;
//  s->enqueue(5);
//  s->enqueue(1);
//  cout<<s->peek()<<endl;
//  cout<<s->dequeue()<<endl;
//  cout<<s->dequeue()<<endl;
//  cout<<s->dequeue()<<endl;
//  cout<<s->isEmpty()<<endl;
//
//  cout<<"Queue"<<endl;
//  PriorityQueue<char>* q = new Queue<char>();
//  cout<<q->isEmpty()<<endl;
//  q->enqueue('a');
//  cout<<q->isEmpty()<<endl;
//  q->enqueue('c');
//  q->enqueue('f');
//  cout<<q->peek()<<endl;
//  cout<<q->dequeue()<<endl;
//  cout<<q->dequeue()<<endl;
//  cout<<q->dequeue()<<endl;
//  cout<<q->isEmpty()<<endl;
//
//  return 0;
//}


int main(int argc, char* args[]) {
  int** a = new int*[3];
  for (int i = 0; i < 3; i++) {
    a[i] = new int[3];
  }
  a[0][2] = 69;
  cout<<a[0][2]<<endl;
  return 0;
}

