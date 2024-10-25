/*
 * Dans ce cas, nous utilisons la somme des éléments de l'ensemble divisée par deux comme estimation de la distance restante.
 * L'heuristique de la somme consiste à calculer la somme des éléments restants et à la diviser par deux. Cette valeur peut être utilisée comme estimation de la distance restante entre l'état actuel et l'état final. Plus la somme des éléments restants est grande, plus il est probable que l'état actuel nécessite plus de mouvements pour atteindre l'état final.
 * 
 */

public class NodeAG implements Comparable<NodeAG>{
	 Integer[] chessboard;//represents VR
     int atk;
     
	public NodeAG(Integer[] chessboard,int atk) {
		super();
		this.chessboard = chessboard;
		this.atk = atk;
	}

	public int getAtk() {
		return atk;
	}

	@Override
	public int compareTo(NodeAG o) {
		return Integer.compare(this.getAtk(), o.getAtk());
    }
	
	
     
}
