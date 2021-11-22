import java.awt.Color; // import des couleurs
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Graphics;
import java.util.Timer;
import java.util.TimerTask;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;

public class FourmiDeLangton {

  // On utilise swing pour l'interface graphique.
  private JFrame frame;
  private JPanel antPanel;
  private Monde monde;
  private Fourmi fourmi;

  public class AntPanel extends JPanel {
    private final int dotSize;
  
    public AntPanel(int dotSize) {
      int pixels = dotSize * monde.size;
      this.dotSize = dotSize;
      setPreferredSize(new Dimension(pixels, pixels));
      setBackground(Color.WHITE);
    }
  
    @Override
    protected void paintComponent(Graphics g) {
      super.paintComponent(g);
      // On dessine les cases
      for (int x = 0; x < monde.size; x++) {
        for (int y = 0; y < monde.size; y++) {
          if (monde.getCellColor(x, y) == Monde.Color.NOIR) {
            g.setColor(Color.BLACK);
            g.fillRect(dotSize * x, dotSize * y, dotSize, dotSize);
          }
        }
      }
    }
  }

  public FourmiDeLangton() {
    // On crée le monde avec une largeur et longueur de 200 
    monde = new Monde(200);
    // on configure la couleur de la cellule centrale
    monde.setCellColor(100,  100, Monde.Color.NOIR);
    // on initialise la position de la fourmi.
    fourmi = new Fourmi(100, 100, Fourmi.Direction.SUD);

    frame = new JFrame("Fourmi de Langton");
    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    frame.setLayout(new FlowLayout());
    antPanel = new AntPanel(5);
    frame.add(antPanel);
    frame.pack();
    frame.setVisible(true);
  }

  // method pour démarrer la création du monde
  public void run(int maxSteps, long delay, long period) {
    Timer timer = new Timer();
    timer.schedule(new TimerTask() {

      @Override
      public void run() {
        // On stop la fourmi quand elle dépasse la taille du monde
        if (!(fourmi.steps < maxSteps  && fourmi.estDansMonde(monde))) {
          timer.cancel();
        } else {
          
          SwingUtilities.invokeLater(() -> {
            antPanel.repaint();
          });

          // la fourmi fait un pas dans le monde selon les règles de la fourmi de Langton
          fourmi.step(monde);
        }
      }
    }, delay, period);
  }

  public static void main(String[] args) {
    // affichage de l'interface egraphique.
    SwingUtilities.invokeLater(() -> {
      new FourmiDeLangton().run(15000, 400, 5);
    });
  }

}