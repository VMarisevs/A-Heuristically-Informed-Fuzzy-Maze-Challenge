### Vladislavs Marisevs
#### G00305490
##### Artificial Intelligence Project 2016
##### Due: Sunday 3rd April 2016
##### [GitHub Link](https://github.com/VMarisevs/A-Heuristically-Informed-Fuzzy-Maze-Challenge)

# A Heuristically Informed Fuzzy Maze Challenge

## How to run

To run this project you must have *JFuzzyLogic.jar* in your path. You can download [the latest version](http://downloads.sourceforge.net/project/jfuzzylogic/jfuzzylogic/jFuzzyLogic.jar?r=http%3A%2F%2Fjfuzzylogic.sourceforge.net%2Fhtml%2Findex.html&ts=1459075524&use_mirror=ufpr)

Main runner class is *ie.gmit.sw.Runner*. You can easy change the maze size in the Runner class. By default maze is 60x60 nodes.

## How to play

|           Keys           |                         Action                        |
|:------------------------:|:-----------------------------------------------------:|
|   **w** or **Up Arrow**  | move character up in zoom in and out mode             |
|  **s** or **Down Arrow** | move character down in zoom in and out mode           |
|  **a** or **Left Arrow** | move character left in zoom in and out mode           |
| **d** or **Right Arrow** | move character right in zoom in and out mode          |
|           **e**          | activate clue helper or plant the bomb                |
|           **p**          | pause the enemies, while user can run across the maze |
|           **z**          | switch the zoom in and out mode                       |
|           **[**          | select previous collected item                        |
|           **]**          | select next collected item                            |

Using this keys user can run across the maze, plant the bomb, activate helpers and fight with monster using *jfuzzy logic*. Before fight user can select collected weapon, note that weapons has different type of power even if they look the same.


## Overview

The objective of the game is to find a way out of the maze. This assignment is very similar to [Mazogs game](http://www.zx-gaming.co.uk/games/mazogs/default.htm).
- It is generating random maze with 60x60 nodes
- Allows player to move around using keys
- Maze is patrolled by 5 monsters, which uses Best First Search algorithm to find the player and move towards him
- Radar uses Depth Limited Depth First Search to display enemies within the range
- Bomb can be used as a weapon to fight with enemy or to be planted and its explosion can kill life forms in the range.

## Extras
- If one of the monster dies, it creates a new one, to keep 5 of them at all times
- **UpdateView** is a singleton class, that simplifies the view's repaint method, allows to use it without passing the view instance
- **Resources** is a singleton class, that loads images into memory just once and allows to reuse them many times
- **Fight** class generates the fight victory result based on *jfuzzy logic*
- For this game I need monsters to apply traversal algorithms before moving forward and to let helper to find the exit at the activation time, I created 2 type of *Maps*, that let me separate each of traversal algorithm mark down and node parent.
- Monsters can be paused and released again.
- Player has an item list and he can switch the current item to be used in the fight or activate it
- Used correct way of extension and encapsulation, one of good examples can be seen in *ie.gmit.sw.ai.node.items* package
 
## Map

On the full map user can see all items, such as:
- **green** - player position
- **red** - monster position
- **purple** - exit
- **orange** - gun
- **yellow** - sword
- **blue** - bomb
- **light blue** - grenade
- **gray** - clue

## Screenshots
![p001.png](https://github.com/VMarisevs/A-Heuristically-Informed-Fuzzy-Maze-Challenge/raw/images/images/sm001.png)
![p002.png](https://github.com/VMarisevs/A-Heuristically-Informed-Fuzzy-Maze-Challenge/raw/images/images/sm002.png)
![p003.png](https://github.com/VMarisevs/A-Heuristically-Informed-Fuzzy-Maze-Challenge/raw/images/images/sm003.png)
![p004.png](https://github.com/VMarisevs/A-Heuristically-Informed-Fuzzy-Maze-Challenge/raw/images/images/sm004.png)

## References

- All traversal algorithms were overridden based on in class examples
- [Weapon icons were taken from opensource collection](http://7soul1.deviantart.com/art/420-Pixel-Art-Icons-for-RPG-129892453)
- [Emotional explosives were taken from opensource collection](http://opengameart.org/content/emotional-explosives)
- [Player character and monsters](http://www.reinerstilesets.de/2d-grafiken/2d-monsters/)
- [Exit image](http://opengameart.org/content/broken-tower)