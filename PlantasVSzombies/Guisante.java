package PlantasVSzombies;

import java.awt.Color;
import java.awt.Frame;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Rectangle;

public class Guisante extends Rectangle {
      private Image imagen;
      public static final int SIZE = 25;
      public static final int velocidad_guisante = 10;

      public Guisante(int posX, int posY, Image img) {

            super(posX, posY, SIZE, SIZE);
            imagen = img;

      }

      public void paint(Graphics g, Frame f) {
            g.drawImage(imagen, x, y, width, height, f);
      }

      public void update() {
            x += velocidad_guisante;

      }

}
