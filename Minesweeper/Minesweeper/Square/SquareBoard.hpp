//
//  SquareBoard.hpp
//  Minesweeper
//
//  Created by Ryan Ayers on 4/27/20.
//

#ifndef SquareBoard_hpp
#define SquareBoard_hpp

#include "Board.hpp"
#include "SquareTile.hpp"

class SquareBoard : public Board {
  friend class SquareGenerate;
private:
  SquareBoard(Rules* r);
public:
  ~SquareBoard() override;
  Rules* rules() override;
  Tile* getTile(int x, int y) override;
};
#endif /* SquareBoard_hpp */
