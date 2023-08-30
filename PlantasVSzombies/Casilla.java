package PlantasVSzombies;

import java.awt.Color;
import java.awt.Frame;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Rectangle;

public class Casilla extends Rectangle {

      private Image imagen;
      private boolean ocupada;
      private int num_identificador;
      public static final int SIZE = 65;

      public Casilla(int posX, int posY, Image img, int num) {

            super(posX, posY, SIZE, SIZE);
            ocupada = false;
            imagen = img;
            num_identificador = num;
      }

      public void paint(Graphics g, Frame f) {
            g.drawImage(imagen, x, y, width, height, f);
            g.setColor(new Color(160, 255, 99, 50));
            g.drawRect(x, y, width, height);

      }

      public boolean isOcupada() {
            return ocupada;
      }

      public void setOcupada(boolean ocupada) {
            this.ocupada = ocupada;
      }

      public int getNum_identificador() {
            return num_identificador;
      }

      public void setNum_identificador(int num_identificador) {
            this.num_identificador = num_identificador;
      }

}
