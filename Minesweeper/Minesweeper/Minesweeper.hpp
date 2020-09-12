//
//  Minesweeper.hpp
//  Minesweeper
//
//  Created by Ryan Ayers on 4/27/20.
//

#ifndef Minesweeper_hpp
#define Minesweeper_hpp

#include <iostream> // print()
#include <iomanip> // print()
#include "SquareGenerate.hpp"

using std::cout;
using std::endl;
using std::setw;
using std::setfill;

class Minesweeper {
private:
  Generate* g;
  Board* b;
  Rules* r;
  int flipped;
  int flagged;
  void lose();
  void win();
  void flipAdj(int x, int y);
  void print(bool revealed);
public:
  Minesweeper(int level); // 1,2,3 - Levels are defined in Rules
  ~Minesweeper();
  // Return 0 for invalid input
  bool flip(int x, int y);
  bool flag(int x, int y);
  bool unflag(int x, int y);
};

#endif /* Minesweeper_hpp */
