//
//  main.cpp
//  Minesweeper
//
//  Created by Ryan Ayers on 4/26/20.
//

#include "Minesweeper.hpp"

using std::cin;
using std::numeric_limits;
using std::streamsize;
using std::string;

int main(int argc, const char * argv[]) {
  cout<<"Welcome to Minesweeper!"<<endl;
  int lvl;
  for (;;) {
    cout<<"Choose a level (1, 2, 3): ";
    if (cin>>lvl && lvl >= 1 && lvl <= 3) {
      break;
    } else {
      cout<<"Invalid input!"<<endl;
      cin.clear();
      cin.ignore(numeric_limits<streamsize>::max(), '\n');
    }
  }
  cout<<endl;
  Minesweeper m = Minesweeper(lvl);
  string s;
  int x;
  int y;
  for (;;) {
    cout<<"Commands: flip, flag, unflag (e.g. flip x y)"<<endl;
    cout<<"Enter a command: ";
    if (cin>>s && cin>>x && cin>>y) {
      if (s == "flip") m.flip(x,y);
      else if (s == "flag") m.flag(x,y);
      else if (s == "unflag") m.unflag(x,y);
      else cout<<"Unrecognized Command!"<<endl;
      continue;
    } else {
      cout<<"Invalid Input!"<<endl;
      cin.clear();
      cin.ignore(numeric_limits<streamsize>::max(), '\n');
    }
  }
return 0;
}
