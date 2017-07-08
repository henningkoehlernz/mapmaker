package mapmaker;

public class CDirFilter
  implements DirFilter {

  private boolean[] acceptFlags = new boolean[Dir.DIRNR];

  /** accepts all or nothing, depending on initState
   * @param initState returned by accept unless changed
   * with setAccept
   */
  public CDirFilter(boolean initState) {
    for (int i = 0; i < Dir.DIRNR; i++)
      acceptFlags[i] = initState;
  } // CDirFilter

  /** initially accepts all
   */
  public CDirFilter() {
    this(true);
  } // CDirFilter

  public boolean accept(int dir) {
    return acceptFlags[dir];
  } // accept

  /** sets wether to accept a given direction
   */
  public void setAccept(int dir, boolean state) {
    acceptFlags[dir] = state;
  } // setAccept

} // CDirFilter
