class Solution {

    int count = 0;
    String answer = "";

    public String getHappyString(int n, int k) {
        backtrack("", n, k);
        return answer;
    }

    private void backtrack(String cur, int n, int k) {

        if (cur.length() == n) {
            count++;
            if (count == k) {
                answer = cur;
            }
            return;
        }

        for (char ch : new char[]{'a', 'b', 'c'}) {

            if (cur.length() > 0 && cur.charAt(cur.length() - 1) == ch)
                continue;

            backtrack(cur + ch, n, k);

            if (!answer.equals("")) return; // stop early when kth found
        }
    }
}