package Test;

import java.awt.Color;
import java.awt.Frame;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Rectangle;

public class Casilla extends Rectangle {
      private boolean visible;
      private Image imagen;
      private Image imagen1;
      private Image reverso;
      public int valor;
      private int identificador;
      public static final int SIZE = 128;

      public Casilla(int posX, int posY, Image img, Image img1, Image rev, int valor, int identificador) {
            super(posX, posY, SIZE, SIZE);
            visible = false;
            imagen = img;
            imagen1 = img1;
            reverso = rev;
            this.valor = valor;
            this.identificador = identificador;
      }

      public void paint(Graphics g, Frame f) {
            if (visible) {
                  g.drawImage(imagen, x, y, width, height, f);
            } else {
                  g.drawImage(reverso, x, y, width, height, f);
            }
            g.setColor(Color.black);
            g.drawRect(x, y, width, height);
      }

      public boolean isVisible() {
            return visible;
      }

      public void setVisible(boolean visible) {
            this.visible = visible;
      }

      public Image getImagen() {
            return imagen;
      }

      public void setImagen(Image imagen) {
            this.imagen = imagen;
      }

      public int getIdentificador() {
            return identificador;
      }

      public void setIdentificador(int identificador) {
            this.identificador = identificador;
      }

}
