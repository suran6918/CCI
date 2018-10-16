import java.util.EmptyStackException;

public class MyStack<T> {

    private MyStackNode<T> top;

    public T pop() {
        if (top == null) throw new EmptyStackException();
        T retval = top.data;
        return retval;
    }

    public void push(T item) {
        MyStackNode<T> newNode = new MyStackNode<>(item);
        newNode.next = top;
        top = newNode;
    }

    public T peek(){
        if (top == null) throw new EmptyStackException();
        return top.data;
    }

    public boolean isEmpty(){
        return (top == null);
    }

    // stack class
    public class MyStackNode<T> {
        private T data;
        private MyStackNode<T> next;

        MyStackNode(T d) {
            data = d;
        }
    }

}
