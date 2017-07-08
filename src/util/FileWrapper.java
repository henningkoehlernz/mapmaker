package util;

import java.io.*;

public class FileWrapper
  extends MyObservable {
  
  private File file;

  public FileWrapper() {
  } // FileWrapper

  public FileWrapper(File file) {
    set(file);
  } // FileWrapper

  public File get() {
    return file;
  } // get;

  public void set(File file) {
    this.file = file;
    notifyObservers(file);
  } // set

} // FileWrapper
