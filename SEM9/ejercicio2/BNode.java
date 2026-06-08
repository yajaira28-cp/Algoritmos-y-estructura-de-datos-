package ejercicio2;

public class BNode<E extends Comparable<E>> {
    private int idNode;         
    private E[] keys;           
    private BNode<E>[] children;
    private int keysCount;      
    private boolean leaf;       

    public int getIdNode() { return idNode; }
    public int getKeysCount() { return keysCount; }
    public boolean isLeaf() { return leaf; }
    
    public E getKey(int index) { 
        return keys[index]; 
    }
    
    public BNode<E> getChild(int index) { 
        return children[index]; 
    }
}
