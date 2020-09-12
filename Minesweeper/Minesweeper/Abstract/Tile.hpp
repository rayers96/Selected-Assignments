//
//  Tile.hpp
//  Minesweeper
//
//  Created by Ryan Ayers on 4/26/20.
//

#ifndef Tile_hpp
#define Tile_hpp

class Tile {
protected:
  char disp;
  bool mine;
  bool flipped;
  bool flagged;
  int adj;
public:
  virtual char getDisp() = 0;
  virtual bool isMine() = 0;
  virtual void setMine(bool b) = 0;
  virtual bool isFlipped() = 0;
  virtual void setFlipped(bool b) = 0;
  virtual bool isFlagged() = 0;
  virtual void setFlagged(bool b) = 0;
  virtual int getAdj() = 0;
  virtual void incAdj(int i) = 0;
};

#endif /* Tile_hpp */
