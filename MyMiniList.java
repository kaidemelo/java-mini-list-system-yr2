import java.util.Objects;

public class MyMiniList <T> implements MiniList<T> {
    //Declarations
    private T[] objectStore;
    private int currentSize;
    private static final int DEFAULT_CAPACITY = 10;

    public MyMiniList() {
        //Constructor initializes object store and current size
        this.objectStore = (T[]) new Object[DEFAULT_CAPACITY];
        this.currentSize = 0;
    }

    @Override
    public void add(T element) {
        //Capacity same as size
        if (this.currentSize + 1 > objectStore.length) {
            //Doubles capacity
            T[] newObjectStore = (T[]) new Object[objectStore.length * 2];
            System.arraycopy(objectStore, 0, newObjectStore, 0, objectStore.length);
            this.objectStore = newObjectStore;
        }
        //Adds element to array
        this.objectStore [this.currentSize] = element;
        currentSize ++;
    }

    @Override
    public T get(int index) {
        try {
            Objects.checkIndex(index, objectStore.length);
        } catch (IndexOutOfBoundsException e) {
            //Throws index out of bounds exception if index doesn't exist
            return null;
        }
        //If index exists returns element at index
        return this.objectStore [index];
    }

    @Override
    public int getIndex(T element) {
        //Loop through the elements in list
        for (int i = 0; i < currentSize - 1; i++) {
            //If element matches the element given as argument then return its index
            if (this.objectStore [i] == element) {
                return i;
            }
        }
        //Otherwise return -1
        return -1;
    }

    @Override
    public void set(int index, T element) {
        //If index is valid
        if (get(index) != null) {
            //set the value at the index to the element given
            this.objectStore [index] = element;
        }
    }

    @Override
    //Returns size of list
    public int size() {
        return currentSize;
    }

    @Override
    public T remove(int index) {
        int elementCount = size();
        int count = index;
        T removedElement = this.objectStore[index];

        //If index valid
        if (get(index) != null) {
            //Set the value at the index to the nothing
            this.objectStore [index] = null;

            //Then loop through the list to fill gap left by removed element (starts from index removed)
            while (count < elementCount) {
                this.objectStore [count] = this.objectStore [count + 1];
                count ++;
            }
            currentSize -= 1;
            return removedElement;
        }
        return null;
    }

    @Override
    public boolean remove(T element) {
        //Loop through the elements in list
        for (int i = 0; i < currentSize - 1; i++) {
            //If element at index i matches the element given as argument then remove element at index i
            if (this.objectStore [i] == element) {
                remove(i);
                return true;
            }
        }
        return false;
    }

    @Override
    public void clear() {
        this.objectStore = (T[]) new Object[0];
        currentSize = 0;
    }
}
