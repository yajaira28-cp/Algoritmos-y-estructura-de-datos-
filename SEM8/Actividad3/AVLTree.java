package Actividad3;

public class AVLTree<E extends Comparable<E>> extends BSTree<E> {

    protected class NodeAVL extends Node {
        public NodeAVL left = null;
        protected int bf; // Factor de equilibrio (Balance Factor)
        public NodeAVL right;
        public AVLTree<E>.NodeAVL izquierdo;
        public AVLTree<E>.NodeAVL derecho;

        public NodeAVL(E data) {
            super(data);
            this.bf = 0;
        }

        @Override
        public String toString() {
            return data.toString() + " (BF: " + bf + ")";
        }
    }

    public static class ItemDuplicated extends RuntimeException {
        public ItemDuplicated() {
            super();
        }

        public ItemDuplicated(String message) {
            super(message);
        }
    }

    private boolean height; // Indicador de cambio de altura global

    // Método de inserción público (Línea 1 de tu foto)
    public void insert(E x) throws ItemDuplicated {
        this.height = false;
        this.raiz = insert((NodeAVL) this.raiz, x); // Usamos 'this.raiz' que viene heredada de BSTree
    }

    private NodeAVL insert(NodeAVL node, E x) throws ItemDuplicated {
        if (node == null) {
            this.height = true;
            return new NodeAVL(x);
        }
        
        int cmp = x.compareTo((E) node.data);
        
        if (cmp < 0) {
            node.izquierdo = insert((NodeAVL) node.izquierdo, x);
            if (this.height) {
                node.bf--;
                if (node.bf == -2) {
                    node = balanceToRight(node);
                }
                this.height = (node.bf != 0);
            }
        } else if (cmp > 0) {
            node.derecho = insert((NodeAVL) node.derecho, x);
            if (this.height) {
                node.bf++;
                if (node.bf == 2) {
                    node = balanceToLeft(node);
                }
                this.height = (node.bf != 0);
            }
        } else {
            throw new ItemDuplicated("Elemento duplicado: " + x);
        }
        
        return node;
    }

    private NodeAVL balanceToLeft(NodeAVL node) {
        NodeAVL hijo = (NodeAVL) node.derecho; // node.right
        
        switch (hijo.bf) {
            case -1: // Caso Derecha-Derecha: requiere rotación simple izquierda
                node.bf = 0;
                hijo.bf = 0;
                node = rotateSL(node);
                break;

            case 1: // Caso Derecha-Izquierda: requiere rotación doble (Derecha - Izquierda)
                NodeAVL nieto = (NodeAVL) hijo.izquierdo; // hijo.left
                
                // Ajustar factores de equilibrio según el estado del nieto
                switch (nieto.bf) {
                    case -1:
                        node.bf = 1;
                        hijo.bf = 0;
                        break;
                    case 0:
                        node.bf = 0;
                        hijo.bf = 0;
                        break;
                    case 1:
                        node.bf = 0;
                        hijo.bf = -1;
                        break;
                }
                nieto.bf = 0;
                
                // Aplicar la doble rotación
                node.derecho = rotateSR(hijo);
                node = rotateSL(node);
                break;
        }
        return node;
    }

    // Se ejecuta cuando el subárbol izquierdo creció de más (Simétrico al anterior)
    private NodeAVL balanceToRight(NodeAVL node) {
        NodeAVL hijo = (NodeAVL) node.izquierdo; // node.left
        
        switch (hijo.bf) {
            case 1: // Caso Izquierda-Izquierda: requiere rotación simple derecha
                node.bf = 0;
                hijo.bf = 0;
                node = rotateSR(node);
                break;

            case -1: // Caso Izquierda-Derecha: requiere rotación doble (Izquierda - Derecha)
                NodeAVL nieto = (NodeAVL) hijo.derecho; // hijo.right
                
                // Ajustar factores de equilibrio de forma simétrica
                switch (nieto.bf) {
                    case 1:
                        node.bf = -1;
                        hijo.bf = 0;
                        break;
                    case 0:
                        node.bf = 0;
                        hijo.bf = 0;
                        break;
                    case -1:
                        node.bf = 0;
                        hijo.bf = 1;
                        break;
                }
                nieto.bf = 0;
                
                // Aplicar la doble rotación simétrica
                node.izquierdo = rotateSL(hijo);
                node = rotateSR(node);
                break;
        }
        return node;
    }
    // Rotación Simple Izquierda (Rotate Single Left)
    private NodeAVL rotateSL(NodeAVL node) {
        NodeAVL hijoDer = (NodeAVL) node.derecho;
        node.derecho = hijoDer.izquierdo;
        hijoDer.izquierdo = node;
        return hijoDer; // Nueva raíz del subárbol
    }

    // Rotación Simple Derecha (Rotate Single Right)
    private NodeAVL rotateSR(NodeAVL node) {
        NodeAVL hijoIzq = (NodeAVL) node.izquierdo;
        node.izquierdo = hijoIzq.derecho;
        hijoIzq.derecho = node;
        return hijoIzq; // Nueva raíz del subárbol
    }
}