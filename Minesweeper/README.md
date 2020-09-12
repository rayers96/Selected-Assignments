# Minesweeper

Terminal based Minesweeper - CS:2820 Project - Spring 2020

main() contains a basic terminal interface for playing the game

Minesweeper is the client and provides the following public interface: 
- Minesweeper(int level)
- ~Minesweeper()
- bool flip(int x, int y)
- bool flag(int x, int y)
- bool unflag(int x, int y)

The 3 command functions (flip, flag, unflag) will return false if the input provided is invalid. 
Client handles the output to the terminal when a board is created and when a command is sucessfully performed.

The usage of abstract classes by the client allows for different variations of the game to be played without change to the client itself (outside of declaring a different derived Generate class). All derived classes must implement the interface functions available to the client. As of now the only variation is Square, however, it would be quite easy to implement a variation (e.g. Triangle, Hexagon, Star) if we desired.
