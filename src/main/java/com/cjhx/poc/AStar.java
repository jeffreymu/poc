package com.cjhx.poc;

import java.util.*;

public class AStar {
    /**
     * A*算法中的节点类
     */
    public static class Node {
        public int x, y; // 节点坐标
        public double g, h; // g:从起点到当前节点的代价，h:当前节点到终点的启发式估计代价
        public Node parent; // 父节点

        /**
         * 节点构造函数
         * @param x 节点的x坐标
         * @param y 节点的y坐标
         */
        public Node(int x, int y) {
            this.x = x;
            this.y = y;
        }

        /**
         * 计算节点的f值(f = g + h)
         * @return 节点的f值
         */
        public double f() {
            return g + h;
        }
    }

    /**
     * 使用A*算法寻找从起点到终点的路径
     * @param grid 二维网格，0表示可通过，1表示障碍物
     * @param start 起点节点
     * @param end 终点节点
     * @return 从起点到终点的路径节点列表，如果找不到路径返回空列表
     * @throws IllegalArgumentException 如果参数无效或起点/终点不在网格内
     */
    public static List<Node> findPath(int[][] grid, Node start, Node end) {
        if (grid == null || start == null || end == null) {
            throw new IllegalArgumentException("Arguments cannot be null");
        }
        if (start.x < 0 || start.y < 0 || end.x < 0 || end.y < 0 ||
            start.x >= grid.length || start.y >= grid[0].length ||
            end.x >= grid.length || end.y >= grid[0].length) {
            throw new IllegalArgumentException("Start or end node out of grid bounds");
        }
        if (grid[start.x][start.y] != 0 || grid[end.x][end.y] != 0) {
            throw new IllegalArgumentException("Start or end node is blocked");
        }

        PriorityQueue<Node> openList = new PriorityQueue<>(Comparator.comparingDouble(Node::f));
        Set<Node> closedList = new HashSet<>();
        
        start.g = 0;
        start.h = heuristic(start, end);
        openList.add(start);

        while (!openList.isEmpty()) {
            Node current = openList.poll();
            
            if (current.x == end.x && current.y == end.y) {
                return reconstructPath(current);
            }

            closedList.add(current);

            for (Node neighbor : getNeighbors(grid, current)) {
                if (closedList.contains(neighbor)) continue;

                double tentativeG = current.g + 1;
                
                if (!openList.contains(neighbor) || tentativeG < neighbor.g) {
                    neighbor.parent = current;
                    neighbor.g = tentativeG;
                    neighbor.h = heuristic(neighbor, end);
                    
                    if (!openList.contains(neighbor)) {
                        openList.add(neighbor);
                    }
                }
            }
        }
        
        return Collections.emptyList(); // No path found
    }

    /**
     * 从终点节点回溯重建完整路径
     * @param node 终点节点
     * @return 从起点到终点的路径节点列表
     */
    private static List<Node> reconstructPath(Node node) {
        List<Node> path = new ArrayList<>();
        while (node != null) {
            path.add(node);
            node = node.parent;
        }
        Collections.reverse(path);
        return path;
    }

    /**
     * 获取当前节点的所有有效邻居节点
     * @param grid 二维网格
     * @param node 当前节点
     * @return 邻居节点列表
     */
    private static List<Node> getNeighbors(int[][] grid, Node node) {
        List<Node> neighbors = new ArrayList<>();
        int[] dx = {-1, 1, 0, 0};
        int[] dy = {0, 0, -1, 1};
        
        for (int i = 0; i < 4; i++) {
            int nx = node.x + dx[i];
            int ny = node.y + dy[i];
            
            if (nx >= 0 && nx < grid.length && ny >= 0 && ny < grid[0].length && grid[nx][ny] == 0) {
                neighbors.add(new Node(nx, ny));
            }
        }
        
        return neighbors;
    }

    /**
     * 启发式函数，计算两个节点之间的曼哈顿距离
     * @param a 节点a
     * @param b 节点b
     * @return 两个节点之间的曼哈顿距离
     */
    private static double heuristic(Node a, Node b) {
        return Math.abs(a.x - b.x) + Math.abs(a.y - b.y); // Manhattan distance
    }
}
