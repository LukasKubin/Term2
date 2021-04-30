package DSA.BST;

public class Sample3 {
    
  public static void main(String[] args) {
    Bst<IntString> t1 = new Empty<IntString>();
    System.out.println(t1);

    Bst<IntString> t2 = t1.insert(new IntString(30,"abc"));  // t1 is unchanged.
    System.out.println(t2);

    Bst<IntString> t3 = t2.insert(new IntString(30,"def"));  // t1 and t2 are unchanged.
    System.out.println(t3);

    Bst<IntString> t4 = t3.insert(new IntString(4,"xyz"));
    System.out.println(t4);

    Bst<IntString> t5 = t4.insert(new IntString(4,"john"));
    System.out.println(t5);

    Bst<IntString> t6 = t5.insert(new IntString(3, "mary"))
				  .insert(new IntString(7, "peter"))
				  .insert(new IntString(7, "mark"))
				  .insert(new IntString(40, "tom"))
				  .insert(new IntString(40, "helen"))
				  .insert(new IntString(10, "barbara"))
				  .insert(new IntString(43, "martin"));

    System.out.println(t6.fancyToString());

    Bst<IntString> t10 = t6.delete(new IntString(3, "mary")); 
    System.out.println(t10.fancyToString());


    Bst<IntString> t11 = t10.delete(new IntString(4, "john")); 
    System.out.println(t11.fancyToString());

    Bst<IntString> t12 = t11.delete(new IntString(30, "abc")); 
    System.out.println(t12.fancyToString());

  }
    
}
