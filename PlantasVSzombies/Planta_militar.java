package PlantasVSzombies;

import java.awt.Color;
import java.awt.Frame;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Rectangle;

public class Planta_militar extends Rectangle {

      private Image imagen;
      public static final int SIZE = 65;
      private int coste = 150;
      private int vida = 30;
      private int ataque = 1;
      private boolean puesta = false;
      private int posicion_cesped = -1;
      private int contador_velocidad_ataque = 0;
      private int acumulador_contador_velocidad_ataque = 0;

      public Planta_militar(int posX, int posY, Image img) {

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

      public boolean isPuesta() {
            return puesta;
      }

      public void setPuesta(boolean puesta) {
            this.puesta = puesta;
      }

      public double getX() {
            return x;
      }

      public void setX(int x) {
            this.x = x;
      }

      public int getPosicion_cesped() {
            return posicion_cesped;
      }

      public void setPosicion_cesped(int posicion_cesped) {
            this.posicion_cesped = posicion_cesped;
      }

      public int getCoste() {
            return coste;
      }

      public void setCoste(int coste) {
            this.coste = coste;
      }

      public int getContador_velocidad_ataque() {
            return contador_velocidad_ataque;
      }

      public void setContador_velocidad_ataque(int contador_velocidad_ataque) {
            this.contador_velocidad_ataque = contador_velocidad_ataque;
      }

      public int getAcumulador_contador_velocidad_ataque() {
            return acumulador_contador_velocidad_ataque;
      }

      public void setAcumulador_contador_velocidad_ataque(int acumulador_contador_velocidad_ataque) {
            this.acumulador_contador_velocidad_ataque = acumulador_contador_velocidad_ataque;
      }

      public int getVida() {
            return vida;
      }

      public void setVida(int vida) {
            this.vida = vida;
      }
}
