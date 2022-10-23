# OOP's game repository using *Java* by Lê Hoàng Minh Hà.

## Game: Bomberman

Bomberman is a video game franchise originally developed by Hudson Soft and currently owned by Konami. The original game, also known as Bakudan Otoko, was released in Japan in July 1983. <a href="https://en.wikipedia.org/wiki/Bomberman">Bomberman (Wikipedia)</a>

### <a href="https://strategywiki.org/wiki/Bomberman">Gameplay:</a>
- Bomberman begins the game with ability to produce one bomb at a time with an explosion range of one square. Power-ups can be collected to increase both the number of bombs he can drop, and the range of the explosions.
- Bomberman starts each round in the upper left corner of every stage.
- Bomberman must first destroy every enemy in the stage, and second locate the exit and escape to the next stage.
- Bomberman can destroy enemies and walls with bombs. Bombs can also harm Bomberman unless he has a special power-up.
The number of bombs that Bomberman can deploy at one time, and the range of the explosions can be increased through power-ups.
- Bomberman losses one life if he is touched by an enemy, or if he is caught in a bomb explosion.
- If Bomberman bombs the exit, or if he runs out of time, it's game over.
- Bombs ordinarily detonate on their own after a set amount of time. One power-up gives Bomberman the ability to detonate the bombs at will.

### Controls:
- Right/Down/Left/Up Arrow: Move bomberman in one of the four directions.
- Space Button: Place bomb at bomberman's current location.
- Escape Button: Exit game.

### <a href=https://strategywiki.org/wiki/Bomberman/How_to_play>Enemies:</a>
Enemy | Name | Points | Speed | Smart | Wallpass
--- | --- | --- | --- | --- | --- |
![Ballom](https://cdn.wikimg.net/en/strategywiki/images/6/61/Bomberman_Balloom.png) | Ballom | 100 | Slow | Low | No

## Resources:
- bomb ticking: Sound Effect from <a href="https://pixabay.com/sound-effects/?utm_source=link-attribution&amp;utm_medium=referral&amp;utm_campaign=music&amp;utm_content=37157">Pixabay</a>
- bomb exploding: Sound Effect from <a href="https://assets.mixkit.co/sfx/preview/mixkit-8-bit-bomb-explosion-2811.mp3">mixkit</a>


### Task list:
- [x] Draw map
- [x] Make bomber moves using right/down/left/up arrow button
- [x] Bomber can place bomb
- [x] Bomb can kill enemy, destroy brick, explode automatically 2 seconds after being placed, activate other bomb in exploded range
- [x] Create enemy Balloom and make Balloom moves automatically, disappear after being killed
- [x] Create portal, make portal appears when the brick is exploded
- [x] Create speed item
- [x] Create enemy Oneal
- [x] Create flame item
- [x] Make Bomber die when being bomb, meet enemies,...
- [x] Make algorithm for Oneal to find Bomber (kinda)
- [x] Add new level
- [x] Add sound effects
- [x] Move to new level when go through portal 
- [ ] Create bomb item
