public class SortedList implements List {
    private class Node{
        private int val;
        private Node left;
        private Node right;
        private Node(int val, Node l, Node r){
            this.val = val;
            left = l;
            right = r;
        }
    }

    private int size;
    private Node root;

    public SortedList(){
        root = null;
        size = 0;
    }
    @Override
    public void add(int elem) {
        root = recAdd(root, elem);
    }

    private Node recAdd(Node curr, int elem) {
        if(curr == null){
            ++size;
            return new Node(elem, null, null);
        }

        if(curr.val < elem)
            curr.right = recAdd(curr.right, elem);
        else if(curr.val > elem)
            curr.left = recAdd(curr.left, elem);

        return curr;
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    @Override
    public boolean remove(int elem) {
        int sizeBefore = size;
        root = recRemove(root, elem);

        return sizeBefore != size;
    }

    private Node recRemove(Node curr, int elem) {
        if(curr.val < elem)
            curr.right = recRemove(curr.right, elem);
        else if(curr.val > elem)
            curr.left = recRemove(curr.left, elem);
        else{
            if(curr.left == null && curr.right == null){
                --size;
                curr = null;
            }else if(curr.left == null){
                --size;
                curr = curr.right;
            }else if(curr.right == null){
                --size;
                curr = curr.left;
            }else{
                int leftMaxVal = getLeftMax(curr.left);
                curr.val = leftMaxVal;
                curr.left = recRemove(curr.left, leftMaxVal);
            }
        }
        return curr;
    }

    private int getLeftMax(Node curr) {
        return curr.right == null ? curr.val : getLeftMax(curr.right);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        recToString(root, sb);
        return sb.toString();
    }

    private void recToString(Node curr, StringBuilder sb) {
        if(curr == null) return;
        recToString(curr.left, sb);
        sb.append(sb.length() != 0 ? "," : "");
        sb.append(curr.val);
        recToString(curr.right, sb);
    }
}
