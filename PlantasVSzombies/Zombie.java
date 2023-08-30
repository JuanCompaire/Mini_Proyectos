package PlantasVSzombies;

import java.awt.Color;
import java.awt.Frame;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Rectangle;
import java.util.Timer;
import java.util.TimerTask;

public class Zombie extends Rectangle {

      private Image imagen;
      public static final int SIZE = 105;
      private int vida = 10;
      private int ataque = 1;
      private int velocidad_movimiento_zombi = 1;

      public Zombie(int posX, int posY, Image img) {

            super(posX, posY, SIZE, SIZE);
            imagen = img;

      }

      public void paint(Graphics g, Frame f) {

            g.drawImage(imagen, x, y, width, height, f);
      }

      public void update() {
            x -= velocidad_movimiento_zombi;
      }

      // funcion para que cambie de color

      public int getVida() {
            return vida;
      }

      public void setVida(int vida) {
            this.vida = vida;
      }

}
