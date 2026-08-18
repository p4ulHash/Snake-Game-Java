# ? Java Snake Game

> A classic Snake arcade game implemented from scratch using object-oriented principles in Java.

## ? About The Project
This project was developed as part of an Algorithms and Data Structures university course. It applies core object-oriented programming (OOP) concepts such as inheritance, abstract classes, polymorphism, and state management within a graphical game environment.

### ? The Assignment
The objective was to program a complete Snake game utilizing a provided window framework (`AudGameWindow`). The game requires managing continuous movement, user keyboard input, score tracking, and strict collision handling.

**Core Features & Architecture:**
* **Object-Oriented Structure:** Designed using clean class hierarchies, including an abstract base class `GameItem` for static entities (`Brick`, `Apple`) and dedicated classes for game state (`SnakeGame`) and coordinate tracking (`Point`).
* **Game Loop & Timing:** Implements a time-based update mechanism using system timestamps (`System.currentTimeMillis()`) to ensure smooth, frame-independent movement via `STEP_TIME` and `updateGame()`.
* **Collision Detection:** Comprehensive collision mechanics preventing the snake from leaving the bounded brick walls, running into itself (`collidesWithSelf()`), or colliding with its own body parts.
* **Dynamic Growth & Scoring:** Features random apple generation (avoiding spawn collisions with the snake), progressive scoring based on apple values, and dynamic snake body expansion (`grow()`).
* **Interactive Controls:** Real-time keyboard event handling (`handleInput()`) mapped safely to an enumeration of directions (`Direction`) while preventing instant self-collision reversals.

## ?? Built With
* **Language:** Java
* **IDE:** IntelliJ IDEA
* **Core Concepts:** Object-Oriented Programming (OOP), Inheritance, Game Loops, Collision Detection, Event Handling

## ? Getting Started
1. Clone the repository: `git clone https://github.com/p4ulHash/Java-Snake-Game.git`
2. Open the project in IntelliJ IDEA.
3. Ensure the provided window framework library is included in your project setup.
4. Run the application via the `SnakeGame` main class to start playing.

## ? Author
**Paul Lang**
* GitHub: [p4ulHash](https://github.com/p4ulHash)