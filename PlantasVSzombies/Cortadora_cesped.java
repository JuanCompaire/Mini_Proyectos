package PlantasVSzombies;

import java.awt.Frame;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Rectangle;

public class Cortadora_cesped extends Rectangle {
      private Image imagen;
      public static final int SIZE = 70;
      private boolean activada = false;
      public static final int velocidad_cortadora = 10;

      public Cortadora_cesped(int posX, int posY, Image img) {

            super(posX, posY, SIZE, SIZE);
            imagen = img;

      }

      public void paint(Graphics g, Frame f) {
            g.drawImage(imagen, x, y, width, height, f);
      }

      public void update() {
            x += velocidad_cortadora;

      }

      public boolean isActivada() {
            return activada;
      }

      public void setActivada(boolean activada) {
            this.activada = activada;
      }

}
