//
//  Container.hpp
//  
//
//  Created by Ryan Ayers on 4/13/20.
//

#ifndef Container_hpp
#define Container_hpp

#include <stdio.h>

template<typename T>
class Container{
public:
    virtual int size()=0;
    virtual void add(T elem)=0;
    virtual void remove(T elem)=0;
    virtual void print_container()=0;
};

#endif /* Container_hpp */
