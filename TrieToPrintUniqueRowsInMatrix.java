
public class TrieToPrintUniqueRowsInMatrix {

	Node root;
	int alphabet_size;
	public TrieToPrintUniqueRowsInMatrix(){
		alphabet_size=2;
		root=new Node(false, new Node[alphabet_size]);
	}
	public boolean insert(int[] array){
		Node currentNode=root;
		boolean isInserted=true;
		for(int i=0;i<array.length;i++){
			if(currentNode.children[array[i]]==null){
				currentNode.children[array[i]]=new Node(false,new Node[alphabet_size]);
				isInserted=true;
			}
			else{
				isInserted=false;
			}
			currentNode=currentNode.children[array[i]];
		}
		currentNode.isLeaf=true;
		return isInserted;
	}
	public void printMatrix(int[][] input){
		for(int i=0;i<input.length;i++){
		     if(insert(input[i])){
		    	 for(int j=0;j<input[i].length;j++){
		    		 System.out.print(input[i][j]+" ");
		    	 }
		     }
		     System.out.println();
		}
	}
	public static void main(String[] args){
		TrieToPrintUniqueRowsInMatrix trie=new TrieToPrintUniqueRowsInMatrix();
		 int[][] M = {{0, 1, 0, 0, 1},
			        {1, 0, 1, 1, 0},
			        {0, 1, 0, 0, 1},
			        {1, 0, 1, 0, 0}
			    };
		 trie.printMatrix(M);
	}
}
