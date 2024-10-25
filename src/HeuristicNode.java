import java.util.Arrays;

public class HeuristicNode implements Comparable<HeuristicNode> {
    private Integer[] state;
    private int g;
    private int h;
    

    public HeuristicNode(Integer[] state, int g, int h) {
        this.state = state;
        this.g = g;
        this.h = h;
    }

    public Integer[] getState() {
        return state;
    }

    public int getG() {
        return g;
    }

    public int getH() {
        return h;
    }


    public int getF() {
        return g + h;
    }

    public int compareTo(HeuristicNode other) {
        return Integer.compare(this.getF(), other.getF());
    }

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof HeuristicNode) {
            HeuristicNode other = (HeuristicNode) obj;
            return Arrays.equals(this.state, other.getState());
        }
        return false;
    }

    @Override
    public int hashCode() {
        return Arrays.hashCode(state);
    }
}
