package PlantasVSzombies;

import java.awt.Frame;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Rectangle;

public class Sol_cielo extends Rectangle {
      private Image imagen;
      public static final int SIZE = 35;
      private int soles_obtenidos = 50;
      private int VelY = 2;
      private int tiempo_generacion_sol_cielo = 675;

      public Sol_cielo(int posX, int posY, Image img) {

            super(posX, posY, SIZE, SIZE);
            imagen = img;

      }

      public void paint(Graphics g, Frame f) {
            g.drawImage(imagen, x, y, width, height, f);
      }

      public void update() {
            y += VelY;

      }

      public int getSoles_obtenidos() {
            return soles_obtenidos;
      }

      public void setSoles_obtenidos(int soles_obtenidos) {
            this.soles_obtenidos = soles_obtenidos;
      }

      public int getTiempo_generacion_sol_cielo() {
            return tiempo_generacion_sol_cielo;
      }

      public void setTiempo_generacion_sol_cielo(int tiempo_generacion_sol_cielo) {
            this.tiempo_generacion_sol_cielo = tiempo_generacion_sol_cielo;
      }

}
