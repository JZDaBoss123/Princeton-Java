import java.util.Iterator;
import java.util.NoSuchElementException;

public class Deque<Item> implements Iterable<Item> {
  private Node head;
  private Node tail;
  private int size;
  
  private class Node {
    Node next;
    Item item;
    
    public Node(Item item, Node next) {
      this.next = next;
      this.item = item;
    }
  }
  // construct an empty deque
  public Deque() {
    head = null;
    tail = null;
    size = 0;
  }
  
  // is the deque empty?
  public boolean isEmpty() {
    return size == 0;
  }
  
  // return the number of items on the deque
  public int size() { 
    return size;
  }
  
  // add the item to the front
  public void addFirst(Item item) {
    if (item == null) {
      throw new IllegalArgumentException("item input cannot be null");
    }
    
    if (this.isEmpty()) {
      Node newHead = new Node (item, null);
      head = newHead;
      tail = newHead;
      size++;
      return;
    }
    
    if (size == 1) {
      Node input = new Node (item, head);
      head = input;
      tail = head.next;
      size++;
      return;
    }
    
    Node newHead = new Node (item, head);
    head = newHead;
    size++;
    return;
  }
  
  // add the item to the back
  public void addLast(Item item) {
    if (item == null) {
      throw new IllegalArgumentException("item input cannot be null");
    }
    
    if (this.isEmpty()) {
      Node newHead = new Node (item, null);
      head = newHead;
      tail = newHead;
      size++;
      return;
    }
    
    else if (size == 1) {
      Node input = new Node (item, null);
      head.next = input;
      tail = input;
      size++;
      return;
    } else {
      
      Node newTail = new Node (item, null);
      tail.next = newTail;
      tail = newTail;
      size++;
    }
  }
  
  // remove and return the item from the front
  public Item removeFirst() {
    if (this.isEmpty()) {
      throw new NoSuchElementException("Deque is empty");
    }
    Item temp = head.item;
    Node holder = head;
    head = head.next;
    holder.next = null;
    size--;
    
    return temp;
  }
  
  // remove and return the item from the back
  public Item removeLast() {
    if (this.isEmpty()) {
      throw new NoSuchElementException("Deque is empty");
    }
    Item holder = tail.item;
    Node temp = head;
    for (Node curr = head; curr.next.next != null; curr = curr.next) {
      temp = temp.next;
    }
    temp.next = null;
    size--;
    
    return holder;
  }
  
  // return an iterator over items in order from front to back
  public Iterator<Item> iterator() {
    return new DequeIterator<Item>();
  }
  
  private class DequeIterator<Item> implements Iterator<Item> {
    public Node curr;
    
    public DequeIterator() {
      curr = head;
    }
    
    public boolean hasNext() {
      return curr.next != null;
    }
    
    public Item next() {
      if(!this.hasNext()) {
        throw new NoSuchElementException("No more elements in Deque");
      }
      curr = curr.next;
      return (Item) curr.item;
    }
    
    public void remove() {
      throw new UnsupportedOperationException("cannot remove");
    }
    
  }
  
  public String toString() {
    for (Node curr = head; curr != null; curr = curr.next) {
      System.out.print(curr.item);
    }
    return "";
  }
  
  // unit testing (required)
  public static void main(String[] args) {
    Deque deq = new Deque<Integer>();
    deq.addFirst(1);
    deq.addFirst(2);
    deq.addLast(3);
    System.out.println(deq.size());
    Iterator<Integer> iter = deq.iterator();
    while (iter.hasNext()){
      System.out.println(iter.next());
    }
    System.out.println(deq);
    deq.removeLast();
    System.out.println(deq);
    deq.removeFirst();
    System.out.println(deq);
  }
  
}