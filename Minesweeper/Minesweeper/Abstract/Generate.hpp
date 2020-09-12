//
//  Generate.hpp
//  Minesweeper
//
//  Created by Ryan Ayers on 4/27/20.
//

#ifndef Generate_hpp
#define Generate_hpp

#include <random>
#include <algorithm>
#include "Rules.hpp"
#include "Board.hpp"

using std::random_device;
using std::mt19937;
using std::shuffle;

class Generate {
protected:
  random_device rd;
  mt19937 g;
  Board* b;
  Rules* r;
public:
  virtual ~Generate() {}
  virtual Board* createBoard(int lvl) = 0;
};

#endif /* Generate_hpp */
