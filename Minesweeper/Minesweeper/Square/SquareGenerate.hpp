//
//  SquareGenerate.hpp
//  Minesweeper
//
//  Created by Ryan Ayers on 4/27/20.
//

#ifndef SquareGenerate_hpp
#define SquareGenerate_hpp

#include "Generate.hpp"
#include "SquareRules.hpp"
#include "SquareBoard.hpp"

class SquareGenerate : public Generate {
private:
  void genMines();
  void incAdjacent(int x, int y);
public:
  SquareGenerate();
  ~SquareGenerate() override;
  Board* createBoard(int lvl) override;
};

#endif /* SquareGenerate_hpp */
