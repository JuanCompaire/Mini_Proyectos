package PlantasVSzombies;

import java.awt.Frame;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Rectangle;

public class Girasol extends Rectangle {
      private Image imagen;
      public static final int SIZE = 65;
      private int coste = 50;
      private int vida = 20;
      private boolean puesta = false;
      private int posicion_cesped = -1;
      private int velocidad_generacion_sol = 1;

      public Girasol(int posX, int posY, Image img) {

            super(posX, posY, SIZE, SIZE);
            imagen = img;

      }

      public void paint(Graphics g, Frame f) {
            g.drawImage(imagen, x, y, width, height, f);
      }

      public void update(int Posx, int Posy) {
            x = Posx - (SIZE / 2);
            y = Posy - (SIZE / 2);
      }

      public int getCoste() {
            return coste;
      }

      public void setCoste(int coste) {
            this.coste = coste;
      }

      public int getVida() {
            return vida;
      }

      public void setVida(int vida) {
            this.vida = vida;
      }

      public boolean isPuesta() {
            return puesta;
      }

      public void setPuesta(boolean puesta) {
            this.puesta = puesta;
      }

      public int getPosicion_cesped() {
            return posicion_cesped;
      }

      public void setPosicion_cesped(int posicion_cesped) {
            this.posicion_cesped = posicion_cesped;
      }

      public int getVelocidad_generacion_sol() {
            return velocidad_generacion_sol;
      }

      public void setVelocidad_generacion_sol(int velocidad_generacion_sol) {
            this.velocidad_generacion_sol = velocidad_generacion_sol;
      }

}
