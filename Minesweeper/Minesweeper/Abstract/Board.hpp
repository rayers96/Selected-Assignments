//
//  Board.hpp
//  Minesweeper
//
//  Created by Ryan Ayers on 4/26/20.
//

#ifndef Board_hpp
#define Board_hpp

#include "Tile.hpp"
#include "Rules.hpp"

class Board {
protected:
  Tile** b;
  Rules* r;
public:
  virtual ~Board() {}
  virtual Rules* rules() = 0;
  virtual Tile* getTile(int x, int y) = 0;
};

#endif /* Board_hpp */
