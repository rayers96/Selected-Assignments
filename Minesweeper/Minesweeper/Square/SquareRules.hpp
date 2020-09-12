//
//  SquareRules.hpp
//  Minesweeper
//
//  Created by Ryan Ayers on 4/27/20.
//

#ifndef SquareRules_hpp
#define SquareRules_hpp

#include "Rules.hpp"

class SquareRules : public Rules {
  friend class SquareGenerate;
private:
  SquareRules(int lvl);
public:
  bool validateXY(int x, int y) override;
  int width() override;
  int height() override;
  int mines() override;
};

#endif /* SquareRules_hpp */
