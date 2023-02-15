package leetcode.epi;

import java.util.List;

import leetcode.epi.test_framework.BinaryTreeUtils;
import leetcode.epi.test_framework.EpiTest;
import leetcode.epi.test_framework.GenericTest;
import leetcode.epi.test_framework.TestUtils;
import leetcode.epi.test_framework.TimedExecutor;

/*
 * find index where number of left = number of right.
 */
public class BstFromSortedArray {

    public static BstNode<Integer> buildMinHeightBSTFromSortedArray(List<Integer> A) {
        // Implement this placeholder.

        return helper(0, A.size() - 1, A);
    }

    private static BstNode<Integer> helper(int start, int end, List<Integer> A) {
        if (start > end)
            return null;
        int m = start + ((end - start) / 2);
        return new BstNode<>(A.get(m), helper(start, m - 1, A), helper(m + 1, end, A));

    }
    @EpiTest(testfile = "bst_from_sorted_array.tsv")
    public static int
    buildMinHeightBSTFromSortedArrayWrapper(TimedExecutor executor,
            List<Integer> A) throws Exception {
        BstNode<Integer> result =
                executor.run(() -> buildMinHeightBSTFromSortedArray(A));

        List<Integer> inorder = BinaryTreeUtils.generateInorder(result);

        TestUtils.assertAllValuesPresent(A, inorder);
        BinaryTreeUtils.assertTreeIsBst(result);
        return BinaryTreeUtils.binaryTreeHeight(result);
    }

    public static void main(String[] args) {
        System.exit(GenericTest
                .runFromAnnotations(
                        args, new Object() {}.getClass().getEnclosingClass())
                .ordinal());
    }
}
