import java.awt.*;
import java.awt.event.*;
import java.awt.geom.Line2D;
import java.awt.geom.Path2D;
import java.util.ArrayList;

import javax.swing.*;

public class MyPanel extends JPanel implements ActionListener {

  // final int PANEL_WIDTH = 728;
  // final int PANEL_HEIGHT = 408;
  final int PANEL_WIDTH = 1920;
  final int PANEL_HEIGHT = 1080;

  Image background;
  Timer timer;

  int xVel = 1;
  int yVel = 1;
  int x = 0;
  int y = 0;

  final int DROP_COUNT = 100;
  final int DROP_DRIFT = 2;
  final float DROP_ACCEL = 0.25f;

  // Raindrop state stored in arrays
  double[] dropX = new double[DROP_COUNT];
  double[] dropY = new double[DROP_COUNT];
  double[] dropSpeed = new double[DROP_COUNT];
  double[] dropAccel = new double[DROP_COUNT];
  double[] dropDrift = new double[DROP_COUNT];
  double[] dropLength = new double[DROP_COUNT];

  MyPanel() {
    this.setPreferredSize(new Dimension(PANEL_WIDTH, PANEL_HEIGHT));
    this.setBackground(Color.black);
    // background = new ImageIcon("res/bg.jpg").getImage(); // Feel Free to change to whatever you like

    // initialize raindrops with random positions and speeds
    for (int i = 0; i < DROP_COUNT; i++) {
      dropX[i] = Math.random() * PANEL_WIDTH;
      dropY[i] = Math.random() * PANEL_HEIGHT;

      dropSpeed[i] = 2 + Math.random() * 4;

      dropLength[i] = 10 + Math.random() * 30;
      dropAccel[i] = DROP_ACCEL;
      dropDrift[i] = (Math.random() - 0.5) * DROP_DRIFT;
    }

    timer = new Timer(5, this);
    timer.start();
  }

  @Override
  public void paint(Graphics g) {
    super.paint(g); // Paint background

    Graphics2D g2d = (Graphics2D) g;

    g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
    g2d.setRenderingHint(RenderingHints.KEY_RENDERING, RenderingHints.VALUE_RENDER_QUALITY);
    g2d.setRenderingHint(RenderingHints.KEY_STROKE_CONTROL, RenderingHints.VALUE_STROKE_PURE);
    
    g2d.drawImage(background, 0, 0, null);
   
    g2d.setColor(new Color(108, 116, 117));
    for (int i = 0; i < DROP_COUNT; i++) {
      double len = dropLength[i];
      double wind = dropDrift[i] * 3;
      
      // g2d.draw(new Line2D.Double(
      //   dropX[i], dropY[i],
      //   dropX[i] + wind, dropY[i] + len
      // ));

      // soft outer glow
      g2d.setStroke(new BasicStroke(4f));
      g2d.setColor(new Color(180, 180, 255, 40));
      g2d.draw(new Line2D.Double(
        dropX[i], dropY[i],
        dropX[i] + wind,
        dropY[i] + len
      ));

      // sharp core
      g2d.setStroke(new BasicStroke(2f));
      g2d.setColor(new Color(200, 200, 255, 120));
      g2d.draw(new Line2D.Double(
        dropX[i], dropY[i],
        dropX[i] + wind,
        dropY[i] + len
      ));
    }
  }

  public void actionPerformed(ActionEvent e) {
    x = x + xVel;
    y = y + yVel;

    for (int i = 0; i < DROP_COUNT; i++) {
      dropSpeed[i] += dropAccel[i];
      dropY[i] += dropSpeed[i];
      dropX[i] += dropDrift[i];

      // wrap raindrop to top when it goes off screen
      if (dropY[i] > PANEL_HEIGHT) {
        dropY[i] = -20;
        dropX[i] = Math.random() * PANEL_WIDTH; // optional: randomize new x pos
        dropSpeed[i] = 2 + Math.random() * 4;
      }
    }

    repaint();
  }
}
