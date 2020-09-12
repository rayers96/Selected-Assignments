//
//  SquareTile.hpp
//  Minesweeper
//
//  Created by Ryan Ayers on 4/27/20.
//

#ifndef SquareTile_hpp
#define SquareTile_hpp

#include "Tile.hpp"

class SquareTile : public Tile {
  friend class SquareGenerate;
  friend class SquareBoard;
private:
  SquareTile();
public:
  char getDisp() override;
  bool isMine() override;
  void setMine(bool b) override;
  bool isFlipped() override;
  void setFlipped(bool b) override;
  bool isFlagged() override;
  void setFlagged(bool b) override;
  int getAdj() override;
  void incAdj(int i) override;
};

#endif /* SquareTile_hpp */
