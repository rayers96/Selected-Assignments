//
//  LinkedList.cpp
//  
//
//  Created by Ryan Ayers on 4/13/20.
//

#include <stdio.h>
#include <iostream>
#include "Container.hpp"

using namespace std;

template<typename T>
class LinkedList: public Container<T>{
private:
  struct node{
      T data;
      node* next;
      node(int d, node* n){
          data = d;
          next = n;
      }
  };
  node* head;
  int a_size;
public:
  LinkedList(){
    head = NULL;
    a_size = 0;
  }
  int size() {
    return a_size;
  }
  void add(T elem) {
    if(head == NULL){
      head = new node(elem, NULL);
      a_size++;
      return;
    }
    node* temp = head;
    while(temp->next != NULL){
      temp = temp->next;
    }
    temp->next = new node(elem, NULL);
    a_size++;;
  }
  void remove(T elem) {
    node* temp = head;
    if (elem == temp->data) {
      if(head != NULL){
        node* temp = head;
        head = (head)->next;
        delete temp;
      }
    }
    else {
      while (temp->next != NULL && elem != temp->next->data) {
        temp = temp->next;
      }
      if (temp->next == NULL) return;
      else {
        node* temp2 = temp->next->next;
        temp->next = temp2;
      }
    }
    a_size--;
  }
  void print_container() {
    node* temp = head;
    while(temp){
      cout<<temp->data;
      cout<<" ";
      temp = temp->next;
    }
    cout<<endl;
  }
};
