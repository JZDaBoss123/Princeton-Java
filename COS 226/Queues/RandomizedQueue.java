import java.util.NoSuchElementException;
import java.util.Iterator;

public class RandomizedQueue<Item> implements Iterable<Item> {
  public int count;
  public Item[] randoQ;
  public int capacity;
  // construct an empty randomized queue
  public RandomizedQueue() {
    capacity = 10;
    randoQ = (Item[]) new Object[capacity];
    count = 0;
  }
  
  // is the randomized queue empty?
  public boolean isEmpty() {
    return count == 0;
  }
  
  // return the number of items on the randomized queue
  public int size() {
    return count;
  }
  
  // add the item
  public void enqueue(Item item) {
    if (item == null) {
      throw new IllegalArgumentException("input item cannot be null");
    }
    randoQ[count] = item;
    count++;
    
    if (count == capacity - 1) {
      capacity *= 2;
      Item[] temp = (Item[]) new Object[capacity];
      for (int i = 0; i < randoQ.length; i++) {
        temp[i] = randoQ[i];
      }
      randoQ = temp;
    }
  }
  
  // remove and return a random item
  public Item dequeue() {
    if (this.isEmpty()) {
      throw new NoSuchElementException("queue is empty");
    }
    int index = this.randoCount();
    Item temp = randoQ[index];
    randoQ[index] = null;
    count--;
    return temp;
  }
  
  private int randoCount() {
    int output = 0;
    int temp = 0;
    for (int i = 0; i < 1000; i++) {
      temp = (int) (Math.random() * randoQ.length);
      if (randoQ[temp] != null) {
        output = (int) temp;
        break;
      }
    }
    return output;
  }
  
  // return a random item (but do not remove it)
  public Item sample() {
    if (this.isEmpty()) {
      throw new NoSuchElementException("queue is empty");
    }
    return randoQ[this.randoCount()];
  }
  
  // return an independent iterator over items in random order
  public Iterator<Item> iterator() {
    return new QueueIterator<Item>();
  }
  
  private class QueueIterator<Item> implements Iterator<Item> {
    private int index;
    
    public QueueIterator() {
      index = 0;
    }
    
    public boolean hasNext() {
      boolean output = false;
      for (int i = index; i < randoQ.length; i++) {
        if (randoQ[i] != null) {
          output = true;
        }
      }
      
      return output; 
    }
    
    public Item next() {
      if (!this.hasNext()) {
        throw new NoSuchElementException("no more elements after this");
      }
      Item temp = (Item) randoQ[index];
      index++;
      return temp;
    }
    
    public void remove() {
      throw new UnsupportedOperationException("Cannot remove using iterator");
    }
    
    
  }
  public String toString() {
    for (int i = 0; i < randoQ.length; i++) {
      if (randoQ[i] == null) {
        continue;
      }
      System.out.print(randoQ[i]);
    }
    return "";
  }
  // unit testing (required)
  public static void main(String[] args) {
    RandomizedQueue deq = new RandomizedQueue<Integer>();
    deq.enqueue(1);
    deq.enqueue(2);
    deq.enqueue(3);
    Iterator<Integer> iter = deq.iterator();
    while (iter.hasNext()){
      System.out.println(iter.next());
    }
    System.out.println(deq);
    deq.dequeue();
    System.out.println(deq);
    deq.dequeue();
    System.out.println(deq);
    deq.dequeue();
    System.out.println(deq);
  }
  
}