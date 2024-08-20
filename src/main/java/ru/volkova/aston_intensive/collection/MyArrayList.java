package ru.volkova.aston_intensive.collection;

import java.util.Collection;
import java.util.Comparator;

public class MyArrayList <T>{
    private int size = 0;
    private int capacity;
    private final int DEFAULT_CAPACITY = 10;
    private Object[] array;
    public MyArrayList(){
        capacity=DEFAULT_CAPACITY;
        array = new Object[capacity];
    }

    public MyArrayList(int initialCapacity){
        this.capacity = initialCapacity;
        array = new Object[capacity];
    }

    private void increaseCapacity() {
        capacity = capacity * 2;
        Object[] newArray = new Object[capacity];
        for (int i = 0; i < size; i++) {
            newArray[i] = array[i];
            array[i] = null;
        }
        array = newArray;
    }

    public void add(int index, Object element) {
        if (index < 0) {
            return;
        }
        if (size + 1 >= capacity) {
            increaseCapacity();
        }
        if (index > size) {
            index = size;
        }
        for (int i = size; i >= index; i--) {
            array[i + 1] = array[i];
        }
        array[index] = element;
        size++;
    }

    public boolean add(Object o) {
        if (size >= capacity) {
            increaseCapacity();
        }
        array[size++] = o;
        return true;
    }

    public boolean addAll(Collection <T> c) {
        if (c == null) {
            return false;
        }
        if (c.isEmpty()) {
            return false;
        }
        for (Object item : c) {
            add(item);
        }
        return true;
    }

    public void clear() {
        for (int i = 0; i < size; i++) {
            array[i] = null;
        }
        size = 0;
    }

    public T get(int index) {
        if ((index < size) && (index >= 0)) {
            return (T) array[index];
        }
        return null;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    private void shiftToLeft(int start) {
        size--;
        if (size <= 0) {
            return;
        }
        if (size != start) {
            System.arraycopy(array, start + 1, array, start, size - start);
        }
        array[size] = null;
    }

    public boolean remove(Object o) {
        if ((size == 0)) {
            return false;
        }
        int i;
        for (i = 0; i < size; i++) {
            if (array[i] == null && o == null) {
                break;
            }
            if ((array[i] != null) && (array[i].equals(o))) {
                break;
            }
        }
        if (i < size) {
            shiftToLeft(i);
            return true;
        }
        return false;
    }

    public T remove(int index) {
        Object o = null;
        if ((index < size) && (index >= 0)) {
            o = get(index);
            shiftToLeft(index);
        }
        return (T) o;
    }

    public void sort(Comparator<? super T> c) {
        quickSort(array, 0, size - 1, c);
    }

    private void quickSort(Object[] arr, int low, int high, Comparator<? super T> c) {
        if (low < high) {
            int pi = partition(arr, low, high, c);

            quickSort(arr, low, pi - 1, c);
            quickSort(arr, pi + 1, high, c);
        }
    }

    private int partition(Object[] arr, int low, int high, Comparator<? super T> c) {
        Object pivot = arr[high];
        int i = (low - 1);
        for (int j = low; j < high; j++) {
            if (c.compare((T) arr[j], (T) pivot) > 0) {
                i++;


                Object temp = arr[i];
                arr[i] = arr[j];
                arr[j] = temp;
            }
        }

        Object temp = arr[i + 1];
        arr[i + 1] = arr[high];
        arr[high] = temp;

        return i + 1;
    }
}
