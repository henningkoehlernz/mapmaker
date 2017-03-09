package mapmaker;

import java.awt.event.*;

public  class HotkeyListener extends KeyAdapter  {
  
  private DescViewer descViewer;

  public HotkeyListener(DescViewer descViewer) {
    this.descViewer = descViewer;
  } // HotkeyListener

  // NOT keyTyped!
  public void keyReleased(KeyEvent e) {
    if (e.getKeyCode() == KeyEvent.VK_ENTER &&
	descViewer.isEditable()) {
      descViewer.requestFocus();
    }
  } // keyTyped

} // HotkeyListener

