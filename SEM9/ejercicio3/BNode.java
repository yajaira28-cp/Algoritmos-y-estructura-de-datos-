package ejercicio3;

public class BNode<E extends Comparable<E>> {
    private int idNode;         
    private E[] keys;           
    private BNode<E>[] children;
    private int keysCount;      
    private boolean leaf;       

    @SuppressWarnings("unchecked")
    public BNode(int idNode, boolean leaf) {
        this.idNode = idNode;
        this.leaf = leaf;
        this.keys = (E[]) new Comparable[3];
        this.children = new BNode[4];
        this.keysCount = 0;
    }

    public int getIdNode() { return idNode; }
    public int getKeysCount() { return keysCount; }
    public void setKeysCount(int keysCount) { this.keysCount = keysCount; }
    public boolean isLeaf() { return leaf; }
    public void setLeaf(boolean leaf) { this.leaf = leaf; }
    
    public E getKey(int index) { 
        return keys[index]; 
    }
    public void setKey(int index, E key) {
        this.keys[index] = key;
    }
    
    public BNode<E> getChild(int index) { 
        return children[index]; 
    }
    public void setChild(int index, BNode<E> child) {
        this.children[index] = child;
    }
}