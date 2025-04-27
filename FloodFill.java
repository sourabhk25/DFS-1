// Time Complexity : O(n) where n = no of pixels i.e. rows * columns
// Space Complexity : O(1)
// Did this code successfully run on Leetcode : Yes
// Any problem you faced while coding this : No
// Approach -
//   - Perform DFS traversal starting from the source pixel (sr, sc).
//   - At each step, if the current pixel is within bounds and has the old color, paint it with the new color.
//   - Recursively apply the same to its 4 neighboring pixels (up, down, left, right).
//   - Stop recursion when pixel is out of bounds or already has the new color.

public class FloodFill {
    public int[][] floodFill(int[][] image, int sr, int sc, int color) {
        if(image[sr][sc]==color) {  //new color is same as old color
            return image;
        }
        dfsFloodFill(image, sr, sc, image[sr][sc], color);
        return image;
    }

    public void dfsFloodFill(int[][] image, int sr, int sc, int oldColor, int color) {
        if(sr < 0 || sc < 0 || sr >= image.length || sc >= image[0].length || image[sr][sc]==color ) {
            return;
        }
        image[sr][sc] = color;
        dfsFloodFill(image, sr-1, sc, oldColor, color);
        dfsFloodFill(image, sr+1, sc, oldColor, color);
        dfsFloodFill(image, sr, sc-1, oldColor, color);
        dfsFloodFill(image, sr, sc+1, oldColor, color);
    }

    public static void printImage(int[][] image) {
        for (int[] row : image) {
            for (int pixel : row) {
                System.out.print(pixel + " ");
            }
            System.out.println();
        }
    }
    public static void main(String[] args) {
        FloodFill solution = new FloodFill();

        int[][] image = {
                {1,1,1},
                {1,1,0},
                {1,0,1}
        };

        int sr = 1; // starting row
        int sc = 1; // starting column
        int newColor = 2;

        System.out.println("Original Image:");
        printImage(image);

        int[][] result = solution.floodFill(image, sr, sc, newColor);

        System.out.println("\nImage after Flood Fill:");
        printImage(result);
    }
}
