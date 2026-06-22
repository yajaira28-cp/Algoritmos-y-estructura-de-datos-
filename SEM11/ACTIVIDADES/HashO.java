public class HashO<T> {

    // ===== REGISTER =====
    static class Register<T> {
        int key;
        T data;

        public Register(int key, T data) {
            this.key = key;
            this.data = data;
        }

        public int getKey() {
            return key;
        }

        public String toString() {
            return "[" + key + ", " + data + "]";
        }
    }

    // ===== LINKED LIST PROPIA =====
    static class Node<T> {
        Register<T> value;
        Node<T> next;

        public Node(Register<T> value) {
            this.value = value;
        }
    }

    static class LinkedList<T> {
        private Node<T> head;

        public void add(Register<T> value) {
            Node<T> newNode = new Node<>(value);

            if (head == null) {
                head = newNode;
                return;
            }

            Node<T> temp = head;
            while (temp.next != null) {
                temp = temp.next;
            }
            temp.next = newNode;
        }

        public Register<T> search(int key) {
            Node<T> temp = head;

            while (temp != null) {
                if (temp.value.getKey() == key)
                    return temp.value;

                temp = temp.next;
            }
            return null;
        }

        public boolean delete(int key) {
            if (head == null) return false;

            if (head.value.getKey() == key) {
                head = head.next;
                return true;
            }

            Node<T> temp = head;

            while (temp.next != null) {
                if (temp.next.value.getKey() == key) {
                    temp.next = temp.next.next;
                    return true;
                }
                temp = temp.next;
            }
            return false;
        }

        public String toString() {
            StringBuilder sb = new StringBuilder();
            Node<T> temp = head;

            while (temp != null) {
                sb.append(temp.value).append(" -> ");
                temp = temp.next;
            }

            sb.append("null");
            return sb.toString();
        }
    }

    // ===== HASH TABLE =====
    private LinkedList<T>[] table;

    @SuppressWarnings("unchecked")
    public HashO(int size) {
        table = new LinkedList[size];

        for (int i = 0; i < size; i++) {
            table[i] = new LinkedList<>();
        }
    }

    private int hash(int key) {
        return key % table.length;
    }

    public void insert(int key, T data) {
        int index = hash(key);
        table[index].add(new Register<>(key, data));
    }

    public Register<T> search(int key) {
        int index = hash(key);
        return table[index].search(key);
    }

    public boolean delete(int key) {
        int index = hash(key);
        return table[index].delete(key);
    }

    public void showTable() {
        System.out.println("\nTABLA HASH (HASH ABIERTO)");

        for (int i = 0; i < table.length; i++) {
            System.out.println(i + " -> " + table[i]);
        }
    }

    // ===== MAIN =====
    public static void main(String[] args) {

        HashO<String> hash = new HashO<>(7);

        hash.insert(10, "Juan");
        hash.insert(3, "Pedro");
        hash.insert(17, "Ana");
        hash.insert(24, "Luis");
        hash.insert(31, "Maria");
        hash.insert(10, "Carlos");
        hash.insert(17, "Sofia");

        hash.showTable();

        System.out.println("\nBuscar clave 24:");
        System.out.println(hash.search(24));

        System.out.println("\nEliminar clave 3:");
        hash.delete(3);

        hash.showTable();
    }
}