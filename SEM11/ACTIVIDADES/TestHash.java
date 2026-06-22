public class TestHash {

    static class Register<T> {
        private int key;
        private T data;

        public Register(int key, T data) {
            this.key = key;
            this.data = data;
        }

        public int getKey() {
            return key;
        }

        public T getData() {
            return data;
        }

        public String toString() {
            return "[" + key + ", " + data + "]";
        }
    }

    static class HashC<T> {

        private Register<T>[] table;
        private int[] status;

        static final int EMPTY = 0;
        static final int OCCUPIED = 1;
        static final int DELETED = 2;

        @SuppressWarnings("unchecked")
        public HashC(int size) {
            table = (Register<T>[]) new Register[size];
            status = new int[size];
        }

        private int hash(int key) {
            return key % table.length;
        }

        public boolean insert(int key, T data) {
            int index = hash(key);
            int start = index;

            do {
                if (status[index] == EMPTY || status[index] == DELETED) {
                    table[index] = new Register<>(key, data);
                    status[index] = OCCUPIED;
                    return true;
                }

                index = (index + 1) % table.length;

            } while (index != start);

            return false;
        }

        public Register<T> search(int key) {
            int index = hash(key);
            int start = index;

            do {
                if (status[index] == EMPTY) return null;

                if (status[index] == OCCUPIED && table[index].getKey() == key)
                    return table[index];

                index = (index + 1) % table.length;

            } while (index != start);

            return null;
        }

        public boolean delete(int key) {
            int index = hash(key);
            int start = index;

            do {
                if (status[index] == EMPTY) return false;

                if (status[index] == OCCUPIED && table[index].getKey() == key) {
                    status[index] = DELETED;
                    return true;
                }

                index = (index + 1) % table.length;

            } while (index != start);

            return false;
        }

        public void showTable() {
            System.out.println("\nIndice\tEstado\t\tRegistro");

            for (int i = 0; i < table.length; i++) {

                String state;
                
                if (status[i] == EMPTY) state = "EMPTY";
                else if (status[i] == OCCUPIED) state = "OCCUPIED";
                else state = "DELETED";

                System.out.print(i + "\t" + state + "\t");

                if (status[i] == OCCUPIED)
                    System.out.println(table[i]);
                else
                    System.out.println("---");
            }
        }
    }

    public static void main(String[] args) {

        HashC<String> hash = new HashC<>(13);

        hash.insert(34, "Juan");
        hash.insert(3, "Pedro");
        hash.insert(7, "Ana");
        hash.insert(30, "Luis");
        hash.insert(11, "Maria");
        hash.insert(8, "Carlos");
        hash.insert(7, "Sofia");
        hash.insert(23, "Miguel");
        hash.insert(41, "Rosa");
        hash.insert(16, "Elena");
        hash.insert(34, "Diego");

        System.out.println("TABLA HASH INICIAL");
        hash.showTable();

        System.out.println("\nBuscando clave 23:");
        Register<String> r = hash.search(23);

        if (r != null)
            System.out.println("Encontrado: " + r);
        else
            System.out.println("No encontrado");

        System.out.println("\nEliminando clave 30...");
        hash.delete(30);

        System.out.println("\nTABLA HASH DESPUES DE ELIMINAR");
        hash.showTable();
    }
}