package Test;

import java.awt.Button;
import java.awt.Color;
import java.awt.Event;
import java.awt.Frame;
import java.awt.Graphics;
import java.awt.Image;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import java.util.ArrayList;

public class PictureMatch extends Frame implements Runnable {

      Thread animacion;
      Image imagen;
      Graphics noseve;
      private int delay = 200;
      public final static int SIZEX = 385;
      public final static int SIZEY = 448;
      public static final int ROWS = 3;
      public static final int COLUMNS = 3;
      private Image imagenes[];
      private Casilla casillas[][];
      private Image reverso;
      private Image cruz;
      private Image bola;
      private int visibles;
      private int contador;
      private Casilla activa1;
      int change = 0;
      int soluciones[] = { 0, 1, 2 };
      int identificador = 0;
      boolean ganan_cruz = false;
      boolean ganan_bola = false;
      private boolean juegoTerminado;

      public static void main(String args[]) {
            PictureMatch app = new PictureMatch();
      }

      public PictureMatch() {
            super("3 en raya");
            init();
            start();
      }

      public void init() {
            this.pack();
            this.setSize(SIZEX, SIZEY);
            this.setVisible(true);

            imagen = this.createImage(SIZEX, SIZEY);
            noseve = imagen.getGraphics();
            // CARGAR IMAGENES
            imagenes = new Image[ROWS * COLUMNS / 2];

            try {
                  reverso = ImageIO.read(new File(
                              "/home/juan/Desktop/ASIGNATURAS/Programacion/3_EVALUACION/src/main/java/Ejercicio_009/imagenes/casilla.png"));

                  for (int i = 0; i < imagenes.length; i++) {
                        imagenes[i] = ImageIO.read(new File(
                                    "/home/juan/Desktop/ASIGNATURAS/Programacion/3_EVALUACION/src/main/java/Ejercicio_008/imgParejas/"
                                                + i + ".png"));

                        cruz = ImageIO.read(new File(
                                    "/home/juan/Desktop/ASIGNATURAS/Programacion/3_EVALUACION/src/main/java/Test/cruz.png"));

                        bola = ImageIO.read(new File(
                                    "/home/juan/Desktop/ASIGNATURAS/Programacion/3_EVALUACION/src/main/java/Test/bola.png"));
                  }
            } catch (IOException e) {
            }
            // CREAR CASILLAS

            casillas = new Casilla[ROWS][COLUMNS];
            for (int i = 0; i < ROWS; i++)
                  for (int j = 0; j < COLUMNS; j++)
                        casillas[i][j] = new Casilla(i * Casilla.SIZE, j * Casilla.SIZE + 37,
                                    cruz, bola, reverso, i + 3 * j, 16);

            this.add("South", new Button("REINICIAR"));

      }

      public void start() {
            animacion = new Thread(this);
            animacion.start();
      }

      public void paint(Graphics g) {
            noseve.setColor(Color.ORANGE);
            noseve.fillRect(0, 0, SIZEX, SIZEY);

            visibles = 0;
            for (int i = 0; i < ROWS; i++) {
                  for (int j = 0; j < COLUMNS; j++) {
                        casillas[i][j].paint(noseve, this);
                        if (casillas[i][j].isVisible())
                              visibles++;

                  }
            }
            if (ganan_cruz) {
                  noseve.setColor(Color.WHITE);
                  noseve.setFont(new java.awt.Font("Arial", 1, 36));
                  noseve.drawString("Ganan las cruces", 35, 200);
                  // animacion.stop();
            }

            if (ganan_bola) {
                  noseve.setColor(Color.WHITE);
                  noseve.setFont(new java.awt.Font("Arial", 1, 36));
                  noseve.drawString("Ganan las bolas", 35, 200);
                  // animacion.stop();
            }

            g.drawImage(imagen, 0, 0, this);

      }

      public void win() {

            juegoTerminado = true;
            // animacion.stop();

      }

      public void update(Graphics g) {
            paint(g);
      }

      public void run() {
            while (true) {

                  repaint();
                  try {
                        Thread.sleep(50);
                  } catch (InterruptedException e) {
                  }

            }

      }

      public boolean mouseDown(Event ev, int x, int y) {

            if (juegoTerminado) {
                  return false;
            }

            for (int i = 0; i < ROWS; i++)
                  for (int j = 0; j < COLUMNS; j++)
                        if (casillas[i][j].contains(x, y) && activa1 == null && !casillas[i][j].isVisible()) {
                              casillas[i][j].setVisible(Boolean.TRUE);
                              activa1 = casillas[i][j];
                              repaint();
                              change++;
                              if (change % 2 == 0) {
                                    casillas[i][j].setImagen(bola);
                              }
                        }
            return false;
      }

      public boolean mouseUp(Event ev, int x, int y) {

            if (juegoTerminado) {
                  return false;
            }

            if (activa1.getImagen() == cruz) {
                  activa1.setIdentificador(1);
            } else {
                  activa1.setIdentificador(0);
            }

            for (int i = 0; i < ROWS; i++) {
                  for (int j = 0; j < COLUMNS; j++) {// comprobar la primera vertical cruz
                        if (casillas[0][0].getIdentificador() == 1 && casillas[0][1].getIdentificador() == 1
                                    && casillas[0][2].getIdentificador() == 1) {
                              ganan_cruz = true;
                              win();

                              // comprobar la primera vertical bola
                        } else if (casillas[0][0].getIdentificador() == 0 && casillas[0][1].getIdentificador() == 0
                                    && casillas[0][2].getIdentificador() == 0) {
                              ganan_bola = true;
                              win();
                        }
                        // comprobar la segunda vertical cruz
                        if (casillas[1][0].getIdentificador() == 1 && casillas[1][1].getIdentificador() == 1
                                    && casillas[1][2].getIdentificador() == 1) {
                              ganan_cruz = true;
                              win();
                              // comprobar la segunda vertical bola
                        } else if (casillas[1][0].getIdentificador() == 0 && casillas[1][1].getIdentificador() == 0
                                    && casillas[1][2].getIdentificador() == 0) {
                              ganan_bola = true;
                              win();
                        }
                        // comprobar la tercera vertical cruz
                        if (casillas[2][0].getIdentificador() == 1 && casillas[2][1].getIdentificador() == 1
                                    && casillas[2][2].getIdentificador() == 1) {
                              ganan_cruz = true;
                              win();
                              // comprobar la tercera vertical bola
                        } else if (casillas[2][0].getIdentificador() == 0 && casillas[2][1].getIdentificador() == 0
                                    && casillas[2][2].getIdentificador() == 0) {
                              ganan_bola = true;
                              win();
                        }
                        // comprobar la primera horizontal cruz
                        if (casillas[0][0].getIdentificador() == 1 && casillas[1][0].getIdentificador() == 1
                                    && casillas[2][0].getIdentificador() == 1) {
                              ganan_cruz = true;
                              win();
                              // comprobar la primera horizontal bola
                        } else if (casillas[0][0].getIdentificador() == 0 && casillas[1][0].getIdentificador() == 0
                                    && casillas[2][0].getIdentificador() == 0) {
                              ganan_bola = true;
                              win();
                        }
                        // comprobar la segunda horizontal cruz
                        if (casillas[0][1].getIdentificador() == 1 && casillas[1][1].getIdentificador() == 1
                                    && casillas[2][1].getIdentificador() == 1) {
                              ganan_cruz = true;
                              win();
                              // comprobar la segunda horizontal bola
                        } else if (casillas[0][1].getIdentificador() == 0 && casillas[1][1].getIdentificador() == 0
                                    && casillas[2][1].getIdentificador() == 0) {
                              ganan_bola = true;
                              win();
                        }
                        // comprobar la tercera horizontal cruz
                        if (casillas[0][2].getIdentificador() == 1 && casillas[1][2].getIdentificador() == 1
                                    && casillas[2][2].getIdentificador() == 1) {
                              ganan_cruz = true;
                              win();
                              // comprobar la tercera horizontal bola
                        } else if (casillas[0][2].getIdentificador() == 0 && casillas[1][2].getIdentificador() == 0
                                    && casillas[2][2].getIdentificador() == 0) {
                              ganan_bola = true;
                              win();
                        }
                        // comprobar la diagonal principal cruz
                        if (casillas[0][0].getIdentificador() == 1 && casillas[1][1].getIdentificador() == 1
                                    && casillas[2][2].getIdentificador() == 1) {
                              ganan_cruz = true;
                              win();
                              // comprobar la diagonal principal bola
                        } else if (casillas[0][0].getIdentificador() == 0 && casillas[1][1].getIdentificador() == 0
                                    && casillas[2][2].getIdentificador() == 0) {
                              ganan_bola = true;
                              win();
                        }
                        // comprobar la diagonal secundaria cruz
                        if (casillas[0][2].getIdentificador() == 1 && casillas[1][1].getIdentificador() == 1
                                    && casillas[2][0].getIdentificador() == 1) {
                              ganan_cruz = true;
                              win();
                              // comprobar la diagonal secundaria bola
                        } else if (casillas[0][2].getIdentificador() == 0 && casillas[1][1].getIdentificador() == 0
                                    && casillas[2][0].getIdentificador() == 0) {
                              ganan_bola = true;
                              win();
                        }

                  }

            }
            activa1 = null;

            return true;
      }

      public boolean keyDown(Event ev, int tecla) {
            if (tecla == 27)
                  System.exit(0);

            return true;

      }

      public boolean action(Event ev, Object obj) {
            if (ev.target instanceof Button) {
                  juegoTerminado = false;
                  ganan_bola = false;
                  ganan_cruz = false;

                  for (int i = 0; i < ROWS; i++) {
                        for (int j = 0; j < COLUMNS; j++) {
                              casillas[i][j].setVisible(Boolean.FALSE);
                              casillas[i][j].setIdentificador(16);
                        }
                  }

            }
            return true;
      }

}
