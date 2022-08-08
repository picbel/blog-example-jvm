package cote.solution

class InvertBinaryTree {
    /**
     * Example:
     * var ti = TreeNode(5)
     * var v = ti.`val`
     * Definition for a binary tree node.
     * class TreeNode(var `val`: Int) {
     *     var left: TreeNode? = null
     *     var right: TreeNode? = null
     * }
     */
    class Solution {
        fun invertTree(root: TreeNode?): TreeNode? = root?.let {
            inverse(it)
            root
        }

        private fun inverse(root: TreeNode?) {
            root?.let {
                val l = it.left
                val r = it.right
                it.left = r
                it.right = l
                inverse(it.left)
                inverse(it.right)
            }
        }
    }


    class TreeNode(var value: Int) {
        var left: TreeNode? = null
        var right: TreeNode? = null
    }
}