package PlantasVSzombies;

import java.awt.Color;
import java.awt.Frame;
import java.awt.Graphics;

import java.awt.Rectangle;
import java.awt.Image;

public class Boton_compra extends Rectangle {

      public static final int SIZE = 65;
      private Image imagen;

      public Boton_compra(int posX, int posY, int x, int y) {

            super(posX, posY, x, y);

      }

      public Boton_compra(int posX, int posY, int x, int y, Image img) {

            super(posX, posY, x, y);
            imagen = img;

      }

      public void paint(Graphics g, Frame f) {
            g.setColor(new Color(93, 0, 192));

            g.drawImage(imagen, x, y, width, height, f);

      }

      public void update(int Posx, int Posy) {
            x = Posx - (SIZE / 2);
            y = Posy - (SIZE / 2);

      }
}
