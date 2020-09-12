//
//  SquareBoard.cpp
//  Minesweeper
//
//  Created by Ryan Ayers on 4/27/20.
//

#include "SquareBoard.hpp"

SquareBoard::SquareBoard(Rules* r) {
  this->r = r;
  // Create empty board
  b = new Tile*[r->width()];
  for (int i = 0; i < r->width(); i++) {
    b[i] = new SquareTile[r->height()];
  }
}

SquareBoard::~SquareBoard() {
  delete b;
}

Rules* SquareBoard::rules() {return r;}
Tile* SquareBoard::getTile(int x, int y) {return &b[x][y];}
