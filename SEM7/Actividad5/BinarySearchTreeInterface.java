package Actividad5;

import Actividad3.ExceptionIsEmpty;
import Actividad3.ItemDuplicated;
import Actividad3.ItemNoFound;

public interface BinarySearchTreeInterface<E> {
    void insert(E data) throws ItemDuplicated;
    E search(E data) throws ItemNoFound;
    void delete(E data) throws ExceptionIsEmpty;
    boolean isEmpty();
}
