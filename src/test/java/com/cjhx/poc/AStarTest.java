package com.cjhx.poc;

import java.util.Arrays;
import org.junit.jupiter.api.Test;

/**
 * AStar算法测试类
 * 包含各种路径查找场景的测试用例
 */
import static org.junit.jupiter.api.Assertions.*;
import java.util.List;

public class AStarTest {
    /**
     * 测试基本路径查找功能 - 网格中有简单障碍物时应能找到路径
     */
    @Test
    public void testFindPath() {
        int[][] grid = {
            {0, 0, 0},
            {0, 1, 0},
            {0, 0, 0}
        };
        
        AStar.Node start = new AStar.Node(0, 0);
        AStar.Node end = new AStar.Node(2, 2);
        
        List<AStar.Node> path = AStar.findPath(grid, start, end);
        assertEquals(5, path.size());
        assertEquals(0, path.get(0).x);
        assertEquals(0, path.get(0).y);
        assertEquals(2, path.get(4).x);
        assertEquals(2, path.get(4).y);
    }

    /**
     * 测试无路径情况 - 当起点完全被障碍物封闭时应返回空路径
     */
    @Test
    public void testNoPath() {
        int[][] grid = {
            {0, 1, 0},
            {1, 1, 1},
            {0, 1, 0}
        };
        
        AStar.Node start = new AStar.Node(0, 0);
        AStar.Node end = new AStar.Node(2, 2);
        
        List<AStar.Node> path = AStar.findPath(grid, start, end);
        assertTrue(path.isEmpty());
    }

    /**
     * 测试起点和终点相同的情况 - 应返回只包含起点的一个节点的路径
     */
    @Test
    public void testStartIsEnd() {
        int[][] grid = {
            {0, 0, 0},
            {0, 0, 0},
            {0, 0, 0}
        };
        
        AStar.Node start = new AStar.Node(1, 1);
        AStar.Node end = new AStar.Node(1, 1);
        
        List<AStar.Node> path = AStar.findPath(grid, start, end);
        assertEquals(1, path.size());
        assertEquals(1, path.get(0).x);
        assertEquals(1, path.get(0).y);
    }

    /**
     * 测试单步路径情况 - 起点和终点相邻时应返回2个节点的路径
     */
    @Test
    public void testSingleStepPath() {
        int[][] grid = {
            {0, 0},
            {0, 0}
        };
        
        AStar.Node start = new AStar.Node(0, 0);
        AStar.Node end = new AStar.Node(0, 1);
        
        List<AStar.Node> path = AStar.findPath(grid, start, end);
        assertEquals(2, path.size());
        assertEquals(0, path.get(0).x);
        assertEquals(0, path.get(0).y);
        assertEquals(0, path.get(1).x);
        assertEquals(1, path.get(1).y);
    }

    /**
     * 测试对角路径情况 - 应找到合理长度的对角线路径
     */
    @Test
    public void testDiagonalPath() {
        int[][] grid = {
            {0, 0, 0},
            {0, 0, 0},
            {0, 0, 0}
        };
        
        AStar.Node start = new AStar.Node(0, 0);
        AStar.Node end = new AStar.Node(2, 2);
        
        List<AStar.Node> path = AStar.findPath(grid, start, end);
        assertTrue(path.size() >= 3);
    }

    /**
     * 测试无效起点情况 - 起点在网格外时应抛出异常
     */
    @Test
    public void testInvalidStart() {
        int[][] grid = {
            {0, 0},
            {0, 0}
        };
        
        AStar.Node start = new AStar.Node(-1, -1);
        AStar.Node end = new AStar.Node(0, 0);
        
        assertThrows(IllegalArgumentException.class, () -> {
            AStar.findPath(grid, start, end);
        });
    }

    /**
     * 测试边界情况网格 - 1x1网格的特殊情况测试
     */
    @Test
    public void testEdgeCaseGrid() {
        int[][] grid = {
            {0}
        };
        
        AStar.Node start = new AStar.Node(0, 0);
        AStar.Node end = new AStar.Node(0, 0);
        
        List<AStar.Node> path = AStar.findPath(grid, start, end);
        assertEquals(1, path.size());
    }

    /**
     * 测试大网格情况 - 验证算法在大网格上的性能和行为
     */
    @Test
    public void testLargeGrid() {
        int[][] grid = new int[20][20];
        for (int i = 0; i < 20; i++) {
            Arrays.fill(grid[i], 0);
        }
        
        AStar.Node start = new AStar.Node(0, 0);
        AStar.Node end = new AStar.Node(19, 19);
        
        List<AStar.Node> path = AStar.findPath(grid, start, end);
        assertTrue(path.size() > 0);
    }

    /**
     * 测试复杂障碍物情况 - 验证算法在复杂障碍物环境中的路径查找能力
     */
    @Test
    public void testComplexObstacles() {
        int[][] grid = {
            {0, 0, 0, 0, 0},
            {0, 1, 1, 1, 0},
            {0, 1, 0, 1, 0},
            {0, 1, 1, 1, 0},
            {0, 0, 0, 0, 0}
        };
        
        AStar.Node start = new AStar.Node(0, 0);
        AStar.Node end = new AStar.Node(4, 4);
        
        List<AStar.Node> path = AStar.findPath(grid, start, end);
        assertEquals(9, path.size());
    }
}
