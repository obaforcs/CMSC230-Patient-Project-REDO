public class Stack<T> {
        private Object[] theStack;
        private int stackPointer;

        public Stack(int size) {
            theStack = new Object[size];
            stackPointer = 0;
        }

        public void push(T item) {
            theStack[stackPointer] = item;
            stackPointer++;
        }

        @SuppressWarnings("unchecked")
        public T pop() {
            if (stackPointer == 0) {
                return null;
            } else {
                stackPointer--;
                T item = (T) theStack[stackPointer];
                theStack[stackPointer] = null;
                return item;
            }
        }

        public boolean isEmpty() {
            return stackPointer == 0;
        }
    }
