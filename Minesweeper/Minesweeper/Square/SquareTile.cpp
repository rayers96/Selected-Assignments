//
//  SquareTile.cpp
//  Minesweeper
//
//  Created by Ryan Ayers on 4/27/20.
//

#include "SquareTile.hpp"

SquareTile::SquareTile() {
  disp = ' ';
  mine = false;
  flipped = false;
  flagged = false;
  adj = 0;
}
char SquareTile::getDisp() {return disp;}
bool SquareTile::isMine() {return mine;}
void SquareTile::setMine(bool b) {mine = b;}
bool SquareTile::isFlipped() {return flipped;}
void SquareTile::setFlipped(bool b) {
  flipped = b;
  if (b && adj == 0) {
    disp = '-';
  }
  else if (b && adj > 0) {
    disp = '0' + adj;
  }
}
bool SquareTile::isFlagged() {return flagged;}
void SquareTile::setFlagged(bool b) {
  flagged = b;
  if (b) {
    disp = '*';
  }
  else if (!b) {
    disp = ' ';
  }
}
int SquareTile::getAdj() {return adj;}
void SquareTile::incAdj(int i) {adj += i;}
