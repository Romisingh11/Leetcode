class Solution {
    public ListNode rotateRight(ListNode head, int k) {
        
        if (head == null || head.next == null || k == 0)
            return head;

        // Find length and tail
        int len = 1;
        ListNode tail = head;

        while (tail.next != null) {
            tail = tail.next;
            len++;
        }

        // Effective rotations
        k = k % len;
        if (k == 0) return head;

        // Make circular
        tail.next = head;

        // Find new tail: (len-k-1) steps
        int stepsToNewTail = len - k - 1;
        ListNode newTail = head;

        for (int i = 0; i < stepsToNewTail; i++) {
            newTail = newTail.next;
        }

        // New head
        ListNode newHead = newTail.next;

        // Break circle
        newTail.next = null;

        return newHead;
    }
}