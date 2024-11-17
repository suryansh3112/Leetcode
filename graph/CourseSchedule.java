package graph;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Queue;

//https://leetcode.com/problems/course-schedule/description/

public class CourseSchedule {
    public boolean dfs(Map<Integer, List<Integer>> map, int crs, HashSet<Integer> visited) {
        List<Integer> courses = map.get(crs);
        if (courses.size() == 0)
            return true;
        if (visited.contains(crs))
            return false;

        visited.add(crs);
        for (int c : courses) {
            if (!dfs(map, c, visited))
                return false;
        }
        visited.remove(crs);
        map.put(crs, new ArrayList<Integer>());
        return true;
    }

    public boolean canFinishdfs(int numCourses, int[][] prerequisites) {
        Map<Integer, List<Integer>> map = new HashMap<>();
        HashSet<Integer> visited = new HashSet<>();
        for (int i = 0; i < numCourses; i++) {
            map.put(i, new ArrayList<Integer>());
        }
        for (int[] a : prerequisites) {
            map.get(a[0]).add(a[1]);
        }

        for (int i = 0; i < numCourses; i++) {
            if (!dfs(map, i, visited))
                return false;
        }
        return true;
    }

    // BFS approach
    public boolean canFinishbfs(int numCourses, int[][] prerequisites) {
        Map<Integer, List<Integer>> map = new HashMap<>();
        int[] inDegree = new int[numCourses];
        Queue<Integer> q = new ArrayDeque<>();
        for (int i = 0; i < numCourses; i++) {
            map.put(i, new ArrayList<Integer>());
        }
        for (int[] a : prerequisites) {
            map.get(a[1]).add(a[0]);
            inDegree[a[0]]++;
        }
        for (int i = 0; i < numCourses; i++) {
            if (inDegree[i] == 0)
                q.offer(i);
        }
        int count = 0;
        while (!q.isEmpty()) {
            int v = q.poll();
            count++;
            for (int neighbour : map.get(v)) {
                inDegree[neighbour]--;
                if (inDegree[neighbour] == 0) {
                    q.offer(neighbour);
                }
            }
        }
        return count == numCourses;
    }

    public static void main(String[] args) {

    }
}