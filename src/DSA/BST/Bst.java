package DSA.BST;

/*
 * Interface for immutable binary search trees.
 * Interface is a way of to achieve abstraction in java.
 * Interface just contains methods with empty bodies that need to be defined by the class
 * that implements it.
 * Used to: achieve security -> hide certain details.
 */
// Type E for the nodes -> type/label of the nodes (Integer,String).
// Extends Comparable<E> -> the parameter must support comparison with other instances of its own type
// via the Comparable interface
public interface Bst <E extends Comparable<E>> {
  public boolean isEmpty();
  public E       smallest();   // Returns smallest node (=left-most node).
  public E       largest();    // Returns largest node (=right-most node).
  public boolean smaller(E e); // Checks whether all nodes smaller than e.
  public boolean bigger(E e);  // Checks whether all nodes bigger than e.
  public boolean has(E e);     // Checks whether e occurs in "this".
  public Bst<E>  find(E e);    // Returns subtree of "this" with e at root (or null).
  public Bst<E>  insert(E e);  // Returns a copy of "this" with e inserted.  
  public Bst<E>  delete(E e);  // Returns a copy of "this" with e deleted.
  public Bst<E>  deleteSmallest();// Return new tree with smallest element deleted.
  public Bst<E>  deleteLargest();// Return new tree with largest element deleted.
  public String  fancyToString();// 2-dimensional, rotated tree printing.
  public String  fancyToString(int d);// Starting at a given position d.
  public Bst<E>  getLeft(); // Returns the left subtree
  public Bst<E>  getRight(); // Returns the right subtree
}
