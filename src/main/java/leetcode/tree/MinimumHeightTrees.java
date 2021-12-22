package leetcode.tree;

import com.google.common.collect.Lists;
import lombok.val;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class MinimumHeightTrees {
    int N;
    boolean[][] relationMatrix;
    boolean[] visited;

    void constructRelationMatrix(int[][] edges) {
        for (int[] edge : edges) {
            relationMatrix[edge[0]][edge[1]] = true;
            relationMatrix[edge[1]][edge[0]] = true;
        }
    }

    int findHeight(int idx, int[] heights) {
        if (heights[idx] > 0)
            return heights[idx];
        visited[idx] = true;
        int height = 1;
        // Check children
        int h = 0;
        for (int j = 0; j < N; j++) {
            if (idx != j && relationMatrix[idx][j] && !visited[j])
                h = Math.max(h, findHeight(j, heights));
        }
        visited[idx] = false;
        heights[idx] = height + h;
        return height + h;
    }

    public List<Integer> findMinHeightTrees1(int n, int[][] edges) {
        this.N = n;
        int[] heights = new int[n];
        relationMatrix = new boolean[n][n];
        visited = new boolean[n];
        constructRelationMatrix(edges);

        for (int i = 0; i < n; i++) {
            heights[i] = findHeight(i, new int[n]);
        }
        int minHeight = Arrays.stream(heights).min().getAsInt();
        var answer = new ArrayList<Integer>();
        for (int i = 0; i < n; i++) {
            if (heights[i] == minHeight)
                answer.add(i);
        }
        return answer;
    }

    public List<Integer> findMinHeightTrees(int n, int[][] edges) {
        Set<Integer>[] sets = new Set[n];
        for(int i=0;i<n;i++)
            sets[i] = new HashSet<>();

        for(int[] edge : edges){
            sets[ edge[0] ].add( edge[1] );
            sets[ edge[1] ].add( edge[0] );
        }

        Queue<Integer> q = new LinkedList<>();
        for(int i=0;i<n;i++){
            if(sets[i].size()<2)
                q.offer(i);
        }
        while(n>2) {
            int size = q.size();
            for (int i = 0; i < size; i++) {
                int curr = q.poll();
                int next = sets[curr].iterator().next();
                sets[next].remove(curr);
                if (sets[next].size() == 1)
                    q.offer(next);
            }
            n -= size;
        }
        return new ArrayList<>(q);
    }

    @Test
    void test1() {
        val answer = Lists.newArrayList(1);
        assertEquals(answer, findMinHeightTrees(4, new int[][] { {1,0}, {1,2}, {1,3} }));
    }
}
