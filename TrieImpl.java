
public class TrieImpl {

	Node root;
	int gene_alphabet_size;
	static int level_1=0;
	public TrieImpl(){
		
		gene_alphabet_size=26;
		root=new Node(false, new Node[gene_alphabet_size]);
	}
	public void insert(String input){
		Node currentNode=root;
		for(char ch: input.toCharArray()){
			if(currentNode.children[ch-'a']==null){
				currentNode.children[ch-'a']=new Node(false,new Node[gene_alphabet_size]);
			}
			currentNode=currentNode.children[ch-'a'];
		}
		currentNode.isLeaf=true;
	}
	public boolean search(String input){
		Node currentNode=root;
		for(char ch: input.toCharArray()){
			if(currentNode.children[ch-'a']==null)
				return false;
			currentNode=currentNode.children[ch-'a'];
		}
		if(currentNode.isLeaf)
			return true;
		else
			return false;
	}
	public boolean searchPrefix(String input){
		Node currentNode=root;
		for(char ch: input.toCharArray()){
			if(currentNode.children[ch-'a']==null)
				return false;
			currentNode=currentNode.children[ch-'a'];
		}
		return true;	
	}
	public String searchPrefixesImpl(String input){
		searchPrefixes(input,input.length(),root,"",0);
		return input.substring(0, level_1);
	}
	public boolean searchPrefixes(String input,int length,Node node,String output,int level){
		if(node==null)
			return false;
		
		else{
			if(level==length){
				if(isLeafNode(node)){
					level_1=level;
					return true;
				}
			}
			else{
				if(searchPrefixes(input,length,node.children[input.charAt(level)-'a'],output,level+1)){
					if(level_1==0)
					level_1=level;
					return true;
				}
				else{
					if(isLeafNode(node)){
						level_1=level;
						return true;
					}else
						return false;
						
				}
			}
		return false;	
		}
		
	}
	public boolean isLeafNode(Node node){
		return node.isLeaf;
	}
	public boolean hasNoChildren(Node node){
		for(int i=0;i<gene_alphabet_size;i++){
			if(node.children[i]!=null)
				return false;
		}
		return true;
	}
	public boolean delete(String key,int level,int length,Node node){
		
		if(node!=null){
			if(level==length){
				if(isLeafNode(node)){
					node.isLeaf=false;
					if(hasNoChildren(node)){
						return true;
					}
				}
				return false;
			}
			else{
				if(delete(key,level+1,length,node.children[key.charAt(level)-'a'])){
					node.children[key.charAt(level)-'a']=null;
				    return(!isLeafNode(node)&&hasNoChildren(node));
				}
			}
		}
		return false;
	}
	public void deleteImpl(String key){
		if(key.length()>0){
			 delete(key,0,key.length(),root);
		}
		
	}
	public static void main(String[] args){
		TrieImpl dict=new TrieImpl();
//		trie.insert("hello");
//		trie.insert("hell");
//		trie.insert("help");
//		trie.insert("world");
//		trie.insert("work");
		
		dict.insert("are");
        dict.insert("area");
        dict.insert("base");
        dict.insert("cat");
        dict.insert("cater");
        dict.insert("basement");
        System.out.println(dict.search("base"));
        System.out.println(dict.searchPrefixesImpl("arerte"));
//		System.out.println(trie.search("hello"));
//		//System.out.println(trie.searchPrefix("hel"));
//		trie.deleteImpl("hello");
//		System.out.println(trie.search("hello"));
//		trie.insert("hello");
//		System.out.println(trie.search("hello"));
	
	}
}
class Node{
	boolean isLeaf;
	Node[] children;
	
	public Node(boolean isLeaf, Node[] children){
		this.isLeaf=isLeaf;
		this.children=children;
	}
}