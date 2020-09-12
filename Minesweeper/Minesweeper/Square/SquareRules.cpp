//
//  SquareRules.cpp
//  Minesweeper
//
//  Created by Ryan Ayers on 4/27/20.
//

#include "SquareRules.hpp"

SquareRules::SquareRules(int lvl) {
  if (lvl == 1) {
    w = 7;
    h = 7;
    m = 10;
  } else if (lvl == 2) {
    w = 10;
    h = 10;
    m = 20;
  } else if (lvl == 3) {
    w = 13;
    h = 13;
    m = 35;
  }
}

bool SquareRules::validateXY(int x, int y) {
  if (x >= 0 && y >= 0) {
    if (x < w && y < h) {
      return true;
    }
  }
  return false;
}

int SquareRules::width() {return w;}
int SquareRules::height() {return h;}
int SquareRules::mines() {return m;}
