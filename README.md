# Task--3-Algorithm-Design-and-solution
High Level Overview of the code  -

1 - Initially, the Simulation class is defined, which is an extension of the Processing library's PApplet class. When the programme is launched, the Processing library automatically calls the setup and draw methods provided by the PApplet class.

2 - A drones array is an input to the function Object() { [native code] } of the Simulation class. The drones, grid, and Processing library are all initialised in the function Object() { [native code] }.

3 - When the application is launched, the setup() function of the Simulation class is called. The window's size, the backdrop colour, the grid, and the drones are all initialised using this method.

4 - The draw() function of the Simulation class is frequently used by the Processing library. The drones are updated using this technique, and their locations are displayed on the screen. Additionally, the technique looks for drone collisions and modifies the placements of the drones accordingly.

5 - The move() method of the Simulation class is in charge of moving the drones. This technique determines a drone's future position by factoring in its speed and direction, as well as the drone's present position and its target position. The procedure also determines whether the subsequent location is accurate before updating the drone's position.

6 - The checkCollision() method of the Simulation class looks for drone collisions. In the event of a collision, the positions of the two drones are changed to avoid an overlap.

7- The drawGrid() function of the Simulation class is in charge of rendering the grid on the screen. The technique creates a rectangle for each grid cell and colours the cells according on their status (occupied or unoccupied).

8 - The drawDrones() function of the Simulation class is in charge of rendering the drones on the screen. Each drone is represented by a circle, which is then given a colour based on the drone's ID.
 

9- The Main class, which is in charge of reading the input data and constructing an instance of the Simulation class, is defined last. The Simulation class's function Object() { [native code] } receives the input data from the Main class, which reads it from a file. The runSketch() method of the Processing library is likewise launched by the Main class.

