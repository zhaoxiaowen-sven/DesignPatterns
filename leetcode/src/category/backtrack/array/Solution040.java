package category.backtrack.array;

import java.util.*;

public class Solution040 {
    public List<List<Integer>> combinationSum2(int[] candidates, int target) {
        List<List<Integer>> res = new ArrayList<>();
        Deque<Integer> path = new LinkedList<>();
        Arrays.sort(candidates);
        dfs(candidates, target, path, res, 0);
        return res;
    }

    private void dfs(int[] candidates, int target, Deque<Integer> path, List<List<Integer>> res, int startIndex) {
        if (target > 0) {
            return;
        }
        if (target == 0) {
            res.add(new ArrayList<>(path));
            return;
        }
        int length = candidates.length;
        for (int i = startIndex; i < length && target >= candidates[i]; i++) { // 剪枝操作
            if (i > startIndex && candidates[i] == candidates[i - 1]) {
                continue;
            }
            path.push(candidates[i]);
            // target -= candidates[i]; 优化为
            dfs(candidates, target - candidates[i], path, res, i + 1);
            // target += candidates[i];
            path.pop();
        }
    }
}
