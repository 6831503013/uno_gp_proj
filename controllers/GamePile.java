package controllers;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

// T is the parameter. This is Parametric Polymorphism!
public class GamePile<T> {
    private LinkedList<T> items = new LinkedList<>();

    public void push(T item) {
        items.addFirst(item);
    }

    public T pop() {
        return items.pollFirst();
    }

    public T peek() {
        return items.peekFirst();
    }

    public int size() {
        return items.size();
    }

    public boolean isEmpty() {
        return items.isEmpty();
    }

    // This method handles the logic you currently have in GameController
    public List<T> clearExceptTop() {
        T top = pop();
        List<T> rest = new ArrayList<>(items);
        items.clear();
        push(top);
        return rest;
    }
}
