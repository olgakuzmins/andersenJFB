package com.kuzmins.storage;

import java.util.*;

public class CustomHashSet<T> implements Iterable<T>{

    /* Your custom HashSet list should allow a user:
     * to Put,
     * perform "Contains" check,
     * iterate,
     * Delete,
     * automatically resize
     * and keep objects uniqueness.
     */

    private LinkedList<T>[] nodes;
    private int elementsAmount;
    private int nodesAmount;

    public CustomHashSet() {
        this.nodes = new LinkedList[5];
        this.elementsAmount = 0;
        this.nodesAmount = nodes.length;
        for (int i = 0; i < nodes.length; i++) {
            nodes[i] = new LinkedList<>();
        }
    }

    public void put(T element) {

        if (contains(element)) {
            return;
        }

        if (elementsAmount * 0.8 > nodesAmount) { resize();}

        int elementHash = element.hashCode();
        int bucketNumber = elementHash % nodesAmount;

        nodes[bucketNumber].add(element);
        elementsAmount++;
    }

    public boolean contains(T element) {
        return nodes[element.hashCode() % nodesAmount].contains(element);
    }

    public void delete(T element) {
        if (contains(element)) {
            nodes[element.hashCode() % nodesAmount].remove(element);
        }
        elementsAmount--;
    }

    private void resize() {
        LinkedList<T>[] moreNodes = new LinkedList[nodesAmount * 2];

        for (int i = 0; i < moreNodes.length; i++) {
            moreNodes[i] = new LinkedList<>();
        }

        for (LinkedList<T> linkedList : nodes) {
            for (T t : linkedList) {
                moreNodes[t.hashCode() % moreNodes.length].add(t);
            }
        }
        nodes = moreNodes;
    }

    public int size() {
        return elementsAmount;
    }

    @Override
    public boolean equals(Object object) {
        if (this == object) return true;
        if (object == null || getClass() != object.getClass()) return false;
        CustomHashSet<?> that = (CustomHashSet<?>) object;
        return elementsAmount == that.elementsAmount && nodesAmount == that.nodesAmount && Objects.deepEquals(nodes, that.nodes);
    }

    @Override
    public int hashCode() {
        return Objects.hash(Arrays.hashCode(nodes), elementsAmount, nodesAmount);
    }

    @Override
    public Iterator<T> iterator() {
        return new Iterator<T>() {

            private int currentIndex = 0;
            Iterator<T> currentIterator = nodes[currentIndex].iterator();


            @Override
            public boolean hasNext() {
                if (currentIterator.hasNext()) {
                    return true;
                }

                currentIndex++;

                for ( ; currentIndex < nodes.length; currentIndex++) {
                    if (!nodes[currentIndex].isEmpty()) {
                        currentIterator = nodes[currentIndex].iterator();
                        return true;
                    }
                }
                return false;
            }

            @Override
            public T next() {
                if (currentIterator.hasNext()) {
                    T next = currentIterator.next();
                    hasNext();
                    return next;
                } else throw new NoSuchElementException("No any next element");
            }
        };
    }
}
