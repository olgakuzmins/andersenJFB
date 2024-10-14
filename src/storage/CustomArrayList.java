package storage;

public class CustomArrayList<T> {

    /* Your custom ArrayList should allow a user:
     * to Put,
     * Get by index,
     * Delete by index
     * and automatically resize
     */

    private Object[] array;
    private int elementsAmount;
    private final double COEFFICIENT;

    public CustomArrayList() {
        this.array = new Object[10];
        this.elementsAmount = 0;
        this.COEFFICIENT = 1.5;
    }

    /*  TO PUT */

    //new element will be added to the end of array
    public void add(T element) {
        notNullCheck(array);
        notNullCheck(element);

        if ((elementsAmount + 1) > array.length) {
            resize();
        }

        array[elementsAmount] = element;
        elementsAmount++;
    }

    //new element will be inserted to the index place
    public void add(T element, int index) {
        notNullCheck(array);
        notNullCheck(element);

        if (index < 0 || index > elementsAmount) {
            throw new IndexOutOfBoundsException("Index must be between 0 and " + elementsAmount + ", your index is " + index);
        }

        if ((elementsAmount + 1) > array.length) {
            resize();
        }

        Object[] newArray = new Object[elementsAmount - index];

        System.arraycopy(array, index, newArray, 0, elementsAmount - index);
        array[index] = element;
        System.arraycopy(newArray, 0, array, index + 1, newArray.length);

        elementsAmount++;
    }

    //new array will be added to the end of array
    public void add(T[] elements) {

        notNullCheck(array);
        notNullCheck(elements);

        while ((elementsAmount + elements.length) > array.length) {
            resize();
        }

        System.arraycopy(elements, 0, array, elementsAmount, elements.length);
        elementsAmount = elementsAmount + elements.length;
    }

    //new array will be inserted starting at the index cell
    public void add(T[] elements, int startIndex) {

        notNullCheck(array);
        notNullCheck(elements);

        if (startIndex < 0 || startIndex > elementsAmount) {
            throw new IndexOutOfBoundsException("Index must be between 0 and " + elementsAmount + ", your index is " + startIndex);
        }

        while ((elementsAmount + elements.length) > array.length) {
            resize();
        }

        Object[] newArray = new Object[elementsAmount - startIndex];

        System.arraycopy(array, startIndex, newArray, 0, elementsAmount - startIndex);
        System.arraycopy(elements, 0, array, startIndex, elements.length);
        System.arraycopy(newArray, 0, array, startIndex+elements.length, newArray.length);

        elementsAmount = elementsAmount + elements.length;
    }

    //the value of element will be put at the index cell, if index cell has value, it will be replaced by new value
    public void put(T element, int index) {
        notNullCheck(array);
        notNullCheck(element);

        if (index >= 0 && index < elementsAmount) {
            array[index] = element;
        } else
            throw new IndexOutOfBoundsException("Index must be between 0 and " + (elementsAmount - 1) + ", your index is " + index);
    }

    //the array will be put, starting at the index cell, if index cell has value, it will be replaced by new value
    public void put(T[] elements, int startIndex) {
        notNullCheck(array);
        notNullCheck(elements);

        while ((startIndex + elements.length) > array.length) {
            resize();
        }

        if (startIndex >= 0 && startIndex < elementsAmount) {
            System.arraycopy(elements, 0, array, startIndex, elements.length);
            elementsAmount = startIndex + elements.length;
        } else
            throw new IndexOutOfBoundsException("Index must be between 0 and " + (size() - 1) + ", your index is " + startIndex);

    }

    /*  TO GET BY INDEX */

    public T getByIndex(int index) {
        if (index >= 0 && index < elementsAmount) {
            return (T) array[index];
        } else
            throw new IndexOutOfBoundsException("Index must be between 0 and " + (elementsAmount - 1) + ", your index is " + index);
    }

    /*  TO DELETE BY INDEX */

    public void deleteByIndex(int index) {
        array[index] = null;
        for (int i = index; i < size() - 1; i++) {
            array[i] = array[i + 1];
        }
        elementsAmount--;
    }

    /*  TO RESIZE */

    private void resize() {
        if ((int) Math.round(size() * COEFFICIENT) < Integer.MAX_VALUE) {
            Object[] newArray = new Object[(int) Math.round(size() * COEFFICIENT)];
            System.arraycopy(array, 0, newArray, 0, elementsAmount);
            array = newArray;
        } else throw new ArrayStoreException("The maximum amount of data storage has been reached");
    }

    public int size() {
        return elementsAmount;
    }

    private void notNullCheck(Object object) {
        if (object == null) {
            throw new RuntimeException("Object can't be null");
        }
    }

}
