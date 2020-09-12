//
//  SquareGenerate.cpp
//  Minesweeper
//
//  Created by Ryan Ayers on 4/27/20.
//

#include "SquareGenerate.hpp"

SquareGenerate::SquareGenerate() {
  g = mt19937(rd());
}

SquareGenerate::~SquareGenerate() {
}

// Generate an int index for each of the mines
Board* SquareGenerate::createBoard(int lvl) {
  r = new SquareRules(lvl);
  b = new SquareBoard(r);
  // Generate mine coords and incAdjacent tiles
  genMines();
  return b;
}

/* Helper Functions */
void SquareGenerate::genMines() {
  int* a = new int[r->width()*r->width()];
  for (int i = 0; i < r->width()*r->width(); i++) {
    a[i] = i;
  }
  shuffle(&a[0], &a[r->width()*r->width()], g);
  for (int i = 0; i < r->mines(); i++) {
    int x = a[i] % r->width();
    int y = a[i] / r->height();
    b->getTile(x,y)->setMine(true);
    // Increment Adjacent Tiles
    incAdjacent(x, y);
  }
  delete[] a;
}

void SquareGenerate::incAdjacent(int x, int y) {
  for (int i = x - 1; i <= x + 1; i++) {
    for (int j = y - 1; j <= y + 1; j++) {
      if (i >= 0 && i < r->width() && j >= 0 && j < r->height()) {
        if (i != x || y != j) {
          Tile* t = b->getTile(i, j);
          t->incAdj(1);
        }
      }
    }
  }
}
