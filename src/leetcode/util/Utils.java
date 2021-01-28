package leetcode.util;

import java.util.List;

public class Utils {

	// 创建一个二维数组
	public static int[][] create2dArray(List<int[]> list) {
		int m = list.size();
		int n = list.get(0).length;
		int[][] arr = new int[m][n];
		for (int i = 0; i < m; i++) {
			int[] item = list.get(i);
			for (int j = 0; j < n; j++) {
				arr[i][j] = item[j];
			}
		}
		return arr;
	}
}
