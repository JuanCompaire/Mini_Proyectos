package PlantasVSzombies;

import java.awt.Frame;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Rectangle;

public class Tanque_cacahuete extends Rectangle {
      private Image imagen;
      public static final int SIZE = 65;
      private int coste = 100;
      private int vida = 130;
      private boolean puesta = false;
      private int posicion_cesped = -1;

      public Tanque_cacahuete(int posX, int posY, Image img) {

            super(posX, posY, SIZE, SIZE);
            imagen = img;

      }

      public void update(int Posx, int Posy) {
            x = Posx - (SIZE / 2);
            y = Posy - (SIZE / 2);
      }

      public void paint(Graphics g, Frame f) {
            g.drawImage(imagen, x, y, width, height, f);
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

      public Image getImagen() {
            return imagen;
      }

      public void setImagen(Image imagen) {
            this.imagen = imagen;
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
}
