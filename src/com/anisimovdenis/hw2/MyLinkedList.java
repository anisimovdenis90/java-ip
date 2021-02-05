package com.anisimovdenis.hw2;

public class MyLinkedList<T> implements MyList<T> {

    private int size;
    private Node<T> first;
    private Node<T> last;

    @Override
    public boolean add(T value) {
        Node<T> l = last;
        Node<T> newNode = new Node<>(l, value, null);
        last = newNode;
        if (l == null) {
            first = newNode;
        } else {
            l.next = newNode;
        }
        size++;
        return true;
    }

    @Override
    public void add(int index, T value) {
        checkIndexRange(index);
        if (index == size) {
            add(value);
        } else {
            Node<T> node = getNode(index);
            Node<T> prev = node.prev;
            Node<T> newNode = new Node<>(prev, value, node);
            node.prev = newNode;
            if (prev == null) {
                first = newNode;
            } else {
                prev.next = newNode;
            }
            size++;
        }
    }

    @Override
    public T get(int index) {
        checkIndexRange(index);
        return getNode(index).item;
    }

    @Override
    public T set(int index, T value) {
        checkIndexRange(index);
        Node<T> node = getNode(index);
        T oldValue = node.item;
        node.item = value;
        return oldValue;
    }

    @Override
    public T remove(int index) {
        checkIndexRange(index);
        Node<T> removedNode = getNode(index);
        T value = removedNode.item;
        Node<T> prev = removedNode.prev;
        Node<T> next = removedNode.next;

        if (prev == null) {
            first = next;
        } else {
            prev.next = next;
            removedNode.prev = null;
        }

        if (next == null) {
            last = prev;
        } else {
            next.prev = prev;
            removedNode.next = null;
        }
        prev.next = next;
        next.prev = prev;
        removedNode.item = null;
        size--;
        return value;
    }

    @Override
    public boolean remove(T value) {
        int index = indexOf(value);
        if (index < 0) {
            return false;
        }
        remove(index);
        return true;
    }

    @Override
    public int indexOf(T value) {
        int index = 0;
        if (value == null) {
            for (Node<T> node = first; node != null; node = node.next) {
                if (node.item == null) {
                    return index;
                } else {
                    index++;
                }
            }
        } else {
            for (Node<T> node = first; node != null; node = node.next) {
                if (node.item.equals(value)) {
                    return index;
                } else {
                    index++;
                }
            }
        }
        return -1;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    @Override
    public boolean contains(T value) {
        return indexOf(value) >= 0;
    }

    private void checkIndexRange(int index) {
        if (index < 0 || index > size) {
            throw new IndexOutOfBoundsException("Index: " + index + " Size: " + size);
        }
    }

    private Node<T> getNode(int index) {
        Node<T> node;
        if (index < (size << 1)) {
            node = first;
            for (int i = 0; i < index; i++) {
                node = node.next;
            }
        } else {
            node = last;
            for (int i = size - 1; i > index ; i--) {
                node = node.prev;
            }
        }
        return node;
    }

    private static class Node<T> {
        T item;
        Node<T> next;
        Node<T> prev;

        public Node(Node<T> prev, T item, Node<T> next) {
            this.item = item;
            this.next = next;
            this.prev = prev;
        }
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder("[");
        if (!isEmpty()) {
            for (Node<T> node = first; node != null; node = node.next) {
                sb.append(node.item);
                if (node != last) {
                    sb.append(",").append(" ");
                }
            }
        }
        sb.append("]");
        return "MyLinkedList{" + sb + '}';
    }
}
