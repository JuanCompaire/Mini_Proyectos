package PlantasVSzombies;

import java.awt.Frame;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Rectangle;

public class Sol extends Rectangle {
      private Image imagen;
      public static final int SIZE = 35;
      private int soles_obtenidos = 50;

      public Sol(int posX, int posY, Image img) {

            super(posX, posY, SIZE, SIZE);
            imagen = img;

      }

      public void paint(Graphics g, Frame f) {
            g.drawImage(imagen, x, y, width, height, f);
      }

      public void update() {

      }

      public int getSoles_obtenidos() {
            return soles_obtenidos;
      }

      public void setSoles_obtenidos(int soles_obtenidos) {
            this.soles_obtenidos = soles_obtenidos;
      }

}
