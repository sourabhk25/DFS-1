// Time Complexity : O(m*n)
// Space Complexity : O(m*n)
// Did this code successfully run on Leetcode : Yes
// Any problem you faced while coding this : No
// Approach -
//   - Perform DFS traversal starting from every cell with 1.
//   - If neighbor is 0, return distance 1.
//   - Otherwise, recursively explore all 4 directions.
//   - Use memoization by storing already computed distances in the result matrix to avoid recomputation.
//   - Calculate minimum distance to the nearest 0 for each cell.
public class ZeroOneMatrix {
    int[][] dirs;
    int m,n;
    // public int[][] updateMatrix(int[][] mat) {
    //     this.dirs = new int[][]{{-1,0}, {1,0}, {0,1}, {0,-1}};
    //     this.m = mat.length;
    //     this.n = mat[0].length;

    //     Queue<int[]> q = new LinkedList<>();    //stores index of 0s

    //     for(int i = 0; i < m; i++) {
    //         for(int j = 0; j < n; j++) {
    //             if(mat[i][j] == 0) {
    //                 q.add(new int[]{i,j});
    //             } else {
    //                 mat[i][j] *= -1;
    //             }
    //         }
    //     }

    //     int dist = 1;

    //     while(!q.isEmpty()) {
    //         int[] curr = q.poll();
    //         for(int[] dir: dirs) {
    //             int r = dir[0] + curr[0];
    //             int c = dir[1] + curr[1];

    //             if(r >= 0 && c >= 0 && r <m && c < n && mat[r][c] == -1) { //if valid cell
    //                 q.add(new int[]{r, c});
    //                 mat[r][c] = mat[curr[0]][curr[1]] + 1; //distance will be +1 than the neighbor
    //             }
    //         }
    //     }

    //     return mat;
    // }

    int[][] result;
    public int[][] updateMatrix(int[][] mat) {
        this.dirs = new int[][]{{-1,0}, {1,0}, {0,1}, {0,-1}};
        this.m = mat.length;
        this.n = mat[0].length;
        this.result = new int[m][n];

        for(int i = 0; i < m; i++) {
            for(int j = 0; j < n; j++) {
                if(mat[i][j] == 1) {
                    result[i][j] =  dfs(mat, i, j);
                }
            }
        }

        return result;
    }

    private int dfs(int[][] mat, int i, int j) {
        //neigbor is valid and is 0 then distance 1
        if(i > 0 && mat[i-1][j] == 0) {
            return 1;
        }
        if(i < m-1 && mat[i+1][j] == 0) {
            return 1;
        }
        if(j > 0 && mat[i][j-1] == 0) {
            return 1;
        }

        if(j < n-1 && mat[i][j+1] == 0) {
            return 1;
        }

        //keep 4 variables for storing neighbor distances;
        int top = 9999, left = 9999, bottom = 9999, right = 9999;

        //use memoization
        if(i > 0 && result[i-1][j] != 0) {
            top = result[i-1][j];
        }

        if(j > 0 && result[i][j-1] != 0) {
            left = result[i][j-1];
        }

        if(j < n-1) {
            if(result[i][j+1] == 0) {   //if not calculated already do recursion and calculate value
                result[i][j+1] = dfs(mat, i, j+1);
            }
            right = result[i][j+1]; //else use old value
        }

        if(i < m-1) {
            if(result[i+1][j] == 0) {   //if not calculated already do recursion and calculate value
                result[i+1][j] = dfs(mat, i+1, j);
            }
            bottom = result[i+1][j]; //else use old value
        }

        return Math.min(top, Math.min(left, Math.min(right, bottom))) + 1;
    }

    public static void printMatrix(int[][] matrix) {
        for (int[] row : matrix) {
            for (int val : row) {
                System.out.print(val + " ");
            }
            System.out.println();
        }
    }
    public static void main(String[] args) {
        ZeroOneMatrix sol = new ZeroOneMatrix();

        int[][] mat = {
                {0,0,0},
                {0,1,0},
                {1,1,1}
        };

        System.out.println("Original Matrix:");
        printMatrix(mat);

        int[][] updatedMatrix = sol.updateMatrix(mat);

        System.out.println("\nUpdated Matrix:");
        printMatrix(updatedMatrix);
    }
}
