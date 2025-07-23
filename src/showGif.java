import javax.swing.ImageIcon;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;

import java.util.Timer;
import java.util.TimerTask;

public class showGif {
  private Timer timer;

  // -- MOSTRA A END GAME SCREEN
  public void showGifDialog(JFrame parent) {
    // -- CRIA TIMER PARA FINALIZAR JOGO
    this.timer = new Timer();
    timer.scheduleAtFixedRate(new endGame(), 1750, 5000);

    // CRIA DISPLAY
    JDialog dialog = new JDialog(parent, "teste", true);
    ImageIcon gifIcon = new ImageIcon(getClass().getResource("/world.gif"));
    JLabel gifLabel = new JLabel(gifIcon);
    dialog.add(gifLabel);
    dialog.pack();
    dialog.setLocationRelativeTo(parent);
    dialog.setVisible(true);
  }

  // TIMER PARA FINALIZAR JOGO
  class endGame extends TimerTask {
    @Override
    public void run() {
      System.exit(0);
    }
  }
}
