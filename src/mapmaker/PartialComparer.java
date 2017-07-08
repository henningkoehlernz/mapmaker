package mapmaker;

/** performs comparisons for elements of a partialy ordered set
 */
public abstract class PartialComparer {

  public abstract boolean less(Object a, Object b);

  public boolean equals(Object a, Object b) {
    return a.equals(b);
  } // equals

} // PartialComparer
