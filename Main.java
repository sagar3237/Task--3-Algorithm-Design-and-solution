import processing.core.PApplet;
import java.util.ArrayList;
import java.util.*;
import java.io.*;



public class Main extends PApplet {

    private static final int GRID_SIZE = 20;
    private static final int DRONE_SIZE = 10;
    private static final int DRONE_SPEED = 2;
    private static final int NUM_DRONES = 5;
    private static final int SIMULATION_DURATION = 10000;

    private ArrayList<Drone> drones;

    public void settings() {
        size(500, 500);
    }

    public void setup() {
        frameRate(60);
        drones = new ArrayList<>();
        for (int i = 0; i < NUM_DRONES; i++) {
            int startX = (int) random(width / GRID_SIZE) * GRID_SIZE;
            int startY = (int) random(height / GRID_SIZE) * GRID_SIZE;
            int endX = (int) random(width / GRID_SIZE) * GRID_SIZE;
            int endY = (int) random(height / GRID_SIZE) * GRID_SIZE;
            drones.add(new Drone(startX, startY, endX, endY));
        }
    }

    public void draw() {
        background(255);
        for (Drone drone : drones) {
            if (!drone.isDone()) {
                drone.move();
                fill(255, 0, 0);
                ellipse(drone.getX(), drone.getY(), DRONE_SIZE, DRONE_SIZE);
            } else {
                fill(0, 255, 0);
                ellipse(drone.getEndX(), drone.getEndY(), DRONE_SIZE, DRONE_SIZE);
            }
        }
        if (frameCount >= SIMULATION_DURATION) {
            noLoop();
        }
    }

    public static void main(String[] args) {
        PApplet.main("Main");
    }

    private class Drone {
        private int startX, startY, endX, endY;
        private int x, y;
        private boolean done;

        public Drone(int startX, int startY, int endX, int endY) {
            this.startX = startX;
            this.startY = startY;
            this.endX = endX;
            this.endY = endY;
            this.x = startX;
            this.y = startY;
            this.done = false;
        }

        public int getX() {
            return x;
        }

        public int getY() {
            return y;
        }

        public int getEndX() {
            return endX;
        }

        public int getEndY() {
            return endY;
        }

        public boolean isDone() {
            return done;
        }

        public void move() {
            if (!done) {
                int dx = endX - x;
                int dy = endY - y;
                float dist = sqrt(dx * dx + dy * dy);
                if (dist <= DRONE_SPEED) {
                    x = endX;
                    y = endY;
                    done = true;
                } else {
                    x += (dx / dist) * DRONE_SPEED;
                    y += (dy / dist) * DRONE_SPEED;
                }
            }
        }
    }
}
