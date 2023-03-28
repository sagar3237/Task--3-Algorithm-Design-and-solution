import java.util.*;

class Drone {
    int startX;
    int startY;
    int endX;
    int endY;
    int startTime;

    public Drone(int startX, int startY, int endX, int endY, int startTime) {
        this.startX = startX;
        this.startY = startY;
        this.endX = endX;
        this.endY = endY;
        this.startTime = startTime;
    }
}

class Node {
    int x;
    int y;
    double f;
    double g;
    double h;
    Node parent;

    public Node(int x, int y) {
        this.x = x;
        this.y = y;
    }
}

public class DronePathfinder {
    private static final int[][] directions = {{0, 1}, {1, 0}, {0, -1}, {-1, 0}, {-1, -1}, {1, 1}, {1, -1}, {-1, 1}};

    public static List<List<Node>> findPaths(List<Drone> drones) {
        List<List<Node>> paths = new ArrayList<>();
        for (Drone drone : drones) {
            List<Node> path = findPath(drone);
            paths.add(path);
        }
        return paths;
    }

    private static List<Node> findPath(Drone drone) {
        Node startNode = new Node(drone.startX, drone.startY);
        Node endNode = new Node(drone.endX, drone.endY);

        PriorityQueue<Node> openList = new PriorityQueue<>(Comparator.comparingDouble(node -> node.f));
        Set<Node> closedList = new HashSet<>();

        startNode.g = 0;
        startNode.h = heuristic(startNode, endNode);
        startNode.f = startNode.g + startNode.h;

        openList.add(startNode);

        while (!openList.isEmpty()) {
            Node currentNode = openList.poll();
            closedList.add(currentNode);

            if (currentNode.x == endNode.x && currentNode.y == endNode.y) {
                return buildPath(currentNode);
            }

            for (int[] direction : directions) {
                Node neighbor = new Node(currentNode.x + direction[0], currentNode.y + direction[1]);
                if (closedList.contains(neighbor)) {
                    continue;
                }

                double tentativeGScore = currentNode.g + 1;

                if (openList.contains(neighbor)) {
                    if (tentativeGScore < neighbor.g) {
                        neighbor.g = tentativeGScore;
                        neighbor.f = neighbor.g + neighbor.h;
                        neighbor.parent = currentNode;
                    }
                } else {
                    neighbor.g = tentativeGScore;
                    neighbor.h = heuristic(neighbor, endNode);
                    neighbor.f = neighbor.g + neighbor.h;
                    neighbor.parent = currentNode;
                    openList.add(neighbor);
                }
            }
        }

        return null; // no path found
    }

    private static List<Node> buildPath(Node node) {
        List<Node> path = new ArrayList<>();
        while (node != null) {
            path.add(node);
            node = node.parent;
        }
        Collections.reverse(path);
        return path;
    }

    private static double heuristic(Node nodeA, Node nodeB) {
        return Math.abs(nodeA.x - nodeB.x) + Math.abs(nodeA.y - nodeB.y);
    }
}
