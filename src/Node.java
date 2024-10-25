public class Node {
    private Integer[] state;
    private int cost;

    public Node(Integer[] state, int cost) {
        this.state = state;
        this.cost = cost;
    }

    public Integer[] getState() {
        return state;
    }

    public int getCost() {
        return cost;
    }

}
