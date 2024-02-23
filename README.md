# Lê Hoàng Minh Hà's OOP game repository

- Course: `INT2204 40`  
- Student ID: `21020621`  
- Program language: `Java`  
- IDE: `Visual Studio Code`  
- UML Diagrams: `IntelliJ IDEA Ultimate`  

## [Game: Bomberman](https://en.wikipedia.org/wiki/Bomberman "Wikipedia: Bomberman")

![Bomberman Logo](https://images-wixmp-ed30a86b8c4ca887773594c2.wixmp.com/f/91e92e8d-ef90-4143-9d07-fe6a6f2b27b3/davj5z4-1dd80d3c-2c2a-4d9e-bd2c-bd216063e07d.png/v1/fill/w_1280,h_355/bomberman_logo___vector_by_sailorbomber_davj5z4-fullview.png?token=eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJ1cm46YXBwOjdlMGQxODg5ODIyNjQzNzNhNWYwZDQxNWVhMGQyNmUwIiwiaXNzIjoidXJuOmFwcDo3ZTBkMTg4OTgyMjY0MzczYTVmMGQ0MTVlYTBkMjZlMCIsIm9iaiI6W1t7ImhlaWdodCI6Ijw9MzU1IiwicGF0aCI6IlwvZlwvOTFlOTJlOGQtZWY5MC00MTQzLTlkMDctZmU2YTZmMmIyN2IzXC9kYXZqNXo0LTFkZDgwZDNjLTJjMmEtNGQ5ZS1iZDJjLWJkMjE2MDYzZTA3ZC5wbmciLCJ3aWR0aCI6Ijw9MTI4MCJ9XV0sImF1ZCI6WyJ1cm46c2VydmljZTppbWFnZS5vcGVyYXRpb25zIl19.pewpMJCp0z6QAlImFF2poUwsDZuN0SSnTu-JDJxpW7c)

Bomberman is a video game franchise originally developed by Hudson Soft and currently owned by Konami. The original game, also known as Bakudan Otoko, was released in Japan in July 1983.

### [Gameplay](https://strategywiki.org/wiki/Bomberman "Gameplay")
![Bomber](https://cdn.wikimg.net/en/strategywiki/images/2/27/Bomberman_White.png)
-  Bomberman begins the game with ability to produce one bomb at a time with an explosion range of one square. Power-ups can be collected to increase both the number of bombs he can drop, and the range of the explosions.
- Bomberman starts each round in the upper left corner of every stage.
- Bomberman must first destroy every enemy in the stage, and second locate the exit and escape to the next stage.
- Bomberman can destroy enemies and walls with bombs. Bombs can also harm Bomberman unless he has a special power-up.
The number of bombs that Bomberman can deploy at one time, and the range of the explosions can be increased through power-ups.
- Bomberman losses one life if he is touched by an enemy, or if he is caught in a bomb explosion.
- Bombs ordinarily detonate on their own after a set amount of time. One power-up gives Bomberman the ability to detonate the bombs at will.

### [Controls](https://strategywiki.org/wiki/Bomberman "Controls")
- Right/Down/Left/Up Arrow: Move bomberman in one of the four directions.
- Space Button: Place bomb at bomberman's current location.
- Escape Button: Exit game.

### [Enemies](https://strategywiki.org/wiki/Bomberman/How_to_play "Enemies")
Enemy | Name | Points | Speed | Smart | Wallpass
:---: | :---: | :---: | :---: | :---: | :---: 
![Ballom](https://cdn.wikimg.net/en/strategywiki/images/6/61/Bomberman_Balloom.png) | Balloom | 100 | Slow | Low | No
![Oneal](https://cdn.wikimg.net/en/strategywiki/images/8/85/Bomberman_Oneal.png) | Oneal | 200 | Normal | Normal | No
![Doll](https://cdn.wikimg.net/en/strategywiki/images/f/f0/Bomberman_Doll.png) | Doll | 400 | Normal | Low | No
![Minvo](https://cdn.wikimg.net/en/strategywiki/images/f/fe/Bomberman_Minvo.png) | Minvo | 800 | Fast | Normal | No
![Kondoria](https://cdn.wikimg.net/en/strategywiki/images/3/3e/Bomberman_Kondoria.png) | Kondoria | 1000 | Slow | High | Yes

### [Power-up Items](https://strategywiki.org/wiki/Bomberman/How_to_play "Power-ups")
Item | Name | Description
:---: | :---: | ---
![Flames](https://cdn.wikimg.net/en/strategywiki/images/a/aa/Bomberman_Flames.png) | Flame Item | Increases explosion range from the bomb in four directions by 1 square.
![Speed](https://cdn.wikimg.net/en/strategywiki/images/4/40/Bomberman_Speed.png) | Speed Item | Increases movement speed.
![Bombs](https://cdn.wikimg.net/en/strategywiki/images/e/e4/Bomberman_Bombs.png) | Bomb Item | Increases the amount of bombs dropped at a time by 1; collectible multiple times.
![Wallpass](https://cdn.wikimg.net/en/strategywiki/images/7/71/Bomberman_Wallpass.png) | Wallpass Item | Gives the ability to pass through bricks, just like the enemies that have it such as Kondoria.

## Resources

### Sound Effects
- bomb ticking: Sound Effect from <a href="https://pixabay.com/sound-effects/?utm_source=link-attribution&amp;utm_medium=referral&amp;utm_campaign=music&amp;utm_content=37157">Pixabay</a>
- bomb exploding: Sound Effect from <a href="https://assets.mixkit.co/sfx/preview/mixkit-8-bit-bomb-explosion-2811.mp3">mixkit</a>
- receiving power-up items: Sound Effect from [mixkit](https://assets.mixkit.co/sfx/preview/mixkit-retro-arcade-casino-notification-211.mp3)
- next level: Sound Effect from <a href="https://pixabay.com/?utm_source=link-attribution&amp;utm_medium=referral&amp;utm_campaign=music&amp;utm_content=14575">Pixabay</a>
- win game: Sound Effect from <a href="https://pixabay.com/sound-effects/?utm_source=link-attribution&amp;utm_medium=referral&amp;utm_campaign=music&amp;utm_content=6185">Pixabay</a>
- game over: Sound Effect from <a href="https://pixabay.com/sound-effects/?utm_source=link-attribution&amp;utm_medium=referral&amp;utm_campaign=music&amp;utm_content=6008">Pixabay</a>

## UML Diagrams

### Game Process
![gameprocess](https://user-images.githubusercontent.com/40814521/198934461-98da137e-3412-4bf6-88a4-876335042ae4.png)

### Entities
![entities](https://user-images.githubusercontent.com/40814521/198057730-29a40c69-a89a-4823-a0a5-80b428a33712.png)

### Moving Entities
![movingentities](https://user-images.githubusercontent.com/40814521/198057772-8f963833-b04e-4199-96a6-5f9278272b30.png)

### Enemies
![enemies](https://user-images.githubusercontent.com/40814521/198057669-8c6c3f7d-6786-4ba8-9351-26c3d46d46ca.png)

### Items
![items](https://user-images.githubusercontent.com/40814521/198057817-86139946-47b5-4ee5-9345-9f2268920d9a.png)

## Game Images

### Start Stage
![start stage](https://user-images.githubusercontent.com/40814521/198934679-e2479dfb-3a53-427f-b5cb-74ac5566099c.png "Start Menu")
- Sound switch button: to turn on/off sound effects.
- Light switch button: to enable light mode theme/dark mode theme.

### How To Play
![how_to_play](https://user-images.githubusercontent.com/40814521/197568736-b722df3c-d8d8-4fde-ab61-e80a85571b07.png "How to Play")

### Game
![demo](https://user-images.githubusercontent.com/40814521/197496875-0f1951e9-e74d-4d04-9cb4-7fc8475ba7dd.gif "Playing level 1")

[Demo Game Bomberman](https://user-images.githubusercontent.com/40814521/197570551-7fd6d3a1-81dc-44d7-b6b9-c5fb24d4ab60.mp4)

### Winning Game
![wingame](https://user-images.githubusercontent.com/40814521/197568342-310ab7c5-1f5b-4b1a-ba33-5d98907d9bec.png "Pass All Level")

### Game Over
![gameover](https://user-images.githubusercontent.com/40814521/198935171-95739d8f-ff45-448a-90ab-b100d456e24c.png "Game Over")
