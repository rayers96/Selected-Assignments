//
//  Minesweeper.cpp
//  Minesweeper
//
//  Created by Ryan Ayers on 4/27/20.
//

#include "Minesweeper.hpp"

/* Client Interface */

Minesweeper::Minesweeper(int level) {
  g = new SquareGenerate();
  b = g->createBoard(level);
  r = b->rules();
  flipped = 0;
  flagged = 0;
  print(false);
  delete g;
}

Minesweeper::~Minesweeper() {
  delete b;
}

bool Minesweeper::flip(int x, int y) {
  if (!r->validateXY(x, y)) {
    cout<<"Coordinates out of bounds!"<<endl;
    return false;
  }
  Tile* t = b->getTile(x, y);
  if (t->isFlipped()) {
    cout<<"Tile already flipped!"<<endl;
  } else if (t->isFlagged()) {
    cout<<"Tile is flagged! Unflag to flip."<<endl;
  } else if (t->isMine()) {
    lose();
  } else {
    t->setFlipped(true);
    flipped++;
    int unflipped = (r->width() * r->height()) - flipped;
    if (unflipped == r->mines()) win();
    else if (t->getAdj() == 0) flipAdj(x,y);
    print(false);
    return true;
  }
  return false;
}

bool Minesweeper::flag(int x, int y) {
  if (!r->validateXY(x, y)) {
    cout<<"Coordinates out of bounds!"<<endl;
    return false;
  }
  Tile* t = b->getTile(x, y);
  if (t->isFlipped()) {
    cout<<"Tile is flipped; cannot be flagged/unflagged."<<endl;
  } else if (t->isFlagged()) {
    cout<<"Tile already flagged!"<<endl;
  } else if (flagged == r->mines()) {
    cout<<"Max number of flags reached!"<<endl;
  } else {
    t->setFlagged(true);
    flagged++;
    print(false);
    return true;
  }
  return false;
}

bool Minesweeper::unflag(int x, int y) {
  if (!r->validateXY(x, y)) {
    cout<<"Coordinates out of bounds!"<<endl;
    return false;
  }
  Tile* t = b->getTile(x, y);
  if (t->isFlipped()) {
    cout<<"Tile is flipped; cannot be flagged/unflagged."<<endl;
  } else if (!t->isFlagged()) {
    cout<<"Tile not flagged!"<<endl;
  } else {
    t->setFlagged(false);
    flagged--;
    print(false);
    return true;
  }
  return false;
}

/* Helper Functions */

void Minesweeper::lose() {
  print(true);
  cout<<"Game over!"<<endl;
  exit(0);
}

void Minesweeper::win() {
  print(true);
  cout<<"You win!"<<endl;
  exit(0);
}

void Minesweeper::flipAdj(int x, int y) {
  // Assumes 8 adj Tiles
  for (int i = x - 1; i <= x + 1; i++) {
    for (int j = y - 1; j <= y + 1; j++) {
      if (i >= 0 && i < r->width() && j >= 0 && j < r->height()) {
        if (i != x || y != j) {
          Tile* t = b->getTile(i, j);
          if (t->isFlipped()) {
            continue;
          } else {
            t->setFlipped(true);
            flipped++;
            if (t->getAdj() == 0) flipAdj(i, j);
          }
        }
      }
    }
  }
}

void Minesweeper::print(bool revealed) {
  // X Label
  cout<<"   ";
  for (int i = 0; i < r->width(); i++) {
    cout<<setw(2)<<setfill(' ')<<i;;
    cout<<" ";
  }
  cout<<"(X)"<<endl;
  // Board & Y Label
  for (int j = 0; j < r->width(); j++) {
    cout<<setw(2)<<setfill(' ')<<j;;
    cout<<" ";
    for (int i = 0; i < r->height(); i++) {
      cout<<"[";
      if (revealed && b->getTile(i, j)->isMine()) {
        cout<<"X";
      } else {
        cout<<b->getTile(i, j)->getDisp();
      }
      cout<<"]";
    }
    cout<<endl;
  }
  cout<<"(Y)   ";
  cout<<"Flags remaining: "<<r->mines() - flagged<<endl<<endl;
}
