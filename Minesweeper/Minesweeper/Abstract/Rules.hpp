//
//  Rules.hpp
//  Minesweeper
//
//  Created by Ryan Ayers on 4/26/20.
//

#ifndef Rules_hpp
#define Rules_hpp

class Rules {
protected:
  int w;
  int h;
  int m;
public:
  virtual bool validateXY(int x, int y) = 0;
  virtual int width() = 0;
  virtual int height() = 0;
  virtual int mines() = 0;
};

#endif /* Rules_hpp */
