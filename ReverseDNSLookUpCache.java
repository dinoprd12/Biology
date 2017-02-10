
public class ReverseDNSLookUpCache {

	IPNode root;
	int ip_alphabet_size;
	static int level_1=0;
	public ReverseDNSLookUpCache(){
		
		ip_alphabet_size=11;
		root=new IPNode(false, new IPNode[ip_alphabet_size]);
	}
	public int getIndex(char ch){
		if(ch=='.'){
			return 10;
		}else{
			return ch-'0';
		}
	}
	public void insert(String input,String input_domain){
		IPNode currentNode=root;
		for(char ch:input.toCharArray()){
			int index=getIndex(ch);
			if(currentNode.children[index]==null){
				currentNode.children[index]=new IPNode(false,new IPNode[ip_alphabet_size]);
			}
			currentNode=currentNode.children[index];
		}
		currentNode.isLeaf=true;
		currentNode.domain_name=input_domain;
	}

	public String search(String input){
		IPNode currentNode=root;
		for(char ch: input.toCharArray()){
			int index=getIndex(ch);
			if(currentNode.children[index]==null)
				return "Nothing with this IP";
			currentNode=currentNode.children[index];
		}
		if(currentNode.isLeaf)
			return currentNode.domain_name;
		else
			return "Nothing with this IP";
	}
	
//	public boolean searchPrefixes(String input,int length,Node node,String output,int level){
//		if(node==null)
//			return false;
//		
//		else{
//			if(level==length){
//				if(isLeafNode(node)){
//					level_1=level;
//					return true;
//				}
//			}
//			else{
//				if(searchPrefixes(input,length,node.children[input.charAt(level)-'a'],output,level+1)){
//					if(level_1==0)
//					level_1=level;
//					return true;
//				}
//				else{
//					if(isLeafNode(node)){
//						level_1=level;
//						return true;
//					}else
//						return false;
//						
//				}
//			}
//		return false;	
//		}
//		
//	}
//	public boolean isLeafNode(Node node){
//		return node.isLeaf;
//	}
//	public boolean hasNoChildren(Node node){
//		for(int i=0;i<ip_alphabet_size;i++){
//			if(node.children[i]!=null)
//				return false;
//		}
//		return true;
//	}
//	public boolean delete(String key,int level,int length,Node node){
//		
//		if(node!=null){
//			if(level==length){
//				if(isLeafNode(node)){
//					node.isLeaf=false;
//					if(hasNoChildren(node)){
//						return true;
//					}
//				}
//				return false;
//			}
//			else{
//				if(delete(key,level+1,length,node.children[key.charAt(level)-'a'])){
//					node.children[key.charAt(level)-'a']=null;
//				    return(!isLeafNode(node)&&hasNoChildren(node));
//				}
//			}
//		}
//		return false;
//	}
//	public void deleteImpl(String key){
//		if(key.length()>0){
//			 delete(key,0,key.length(),root);
//		}
//		
//	}
	public static void main(String[] args){
           
		ReverseDNSLookUpCache rdl=new ReverseDNSLookUpCache();
		rdl.insert("107.108.11.123", "www.samsung.com");
		rdl.insert("107.109.123.255", "www.samsung.net");
		rdl.insert("74.125.200.106", "www.google.com");
		System.out.println(rdl.search("107.109.123.252"));
	}
}
class IPNode{
	boolean isLeaf;
	IPNode[] children;
	String domain_name;
	public IPNode(boolean isLeaf, IPNode[] children){
		this.isLeaf=isLeaf;
		this.children=children;
	}
}