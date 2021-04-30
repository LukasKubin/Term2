package DSA.BST;

public class Sample2 {
    
  public static void main(String[] args) {
    Bst<String> t1 = new Empty<String>();
    System.out.println(t1);

    Bst<String> t2 = t1.insert("abc");  // t1 is unchanged.
    System.out.println(t2);

    Bst<String> t3 = t2.insert("def");  // t1 and t2 are unchanged.
    System.out.println(t3);

    Bst<String> t4 = t3.insert("xyz");
    System.out.println(t4);

    Bst<String> t5 = t4.insert("john");
    System.out.println(t5);

    Bst<String> t6 = t5.insert("mary")
                       .insert("peter")
                       .insert("mark")
                       .insert("tom")
                       .insert("helen")
                       .insert("barbara")
                       .insert("martin");

    System.out.println(t6.fancyToString());

    Bst<String> t10 = t6.delete("mary"); 
    System.out.println(t10.fancyToString());

    Bst<String> t11 = t10.delete("john"); 
    System.out.println(t11.fancyToString());

    Bst<String> t12 = t11.delete("abc"); 
    System.out.println(t12.fancyToString());
  }
}
