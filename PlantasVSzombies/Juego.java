package PlantasVSzombies;

//https://www.spriters-resource.com/
import java.awt.Button;
import java.awt.Color;
import java.awt.Event;
import java.awt.Frame;
import java.awt.Graphics;
import java.awt.Image;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import java.util.Iterator;
import java.util.List;
import java.util.Random;
import java.util.ArrayList;
import java.util.Collections;

public class Juego extends Frame implements Runnable {

      public static final int SIZEX = 900;
      public static final int SIZEY = 500;
      public static int TIME = 75;
      private int contador = 0;
      private int contador_girasol = 0;
      Thread animacion;
      Image imagen;
      Graphics noseve;
      private Image cesped;
      private Image sol;
      private Image planta_militar;
      private Image pala;
      private Image pala_accion;
      private Image zombie;
      private Image guisante;
      private Image cortador;
      private Image cacahuete;
      private Image cacahuete1;
      private Image cacahuete2;
      private Image girasol;
      private Image lose;
      private Casilla casillas[][];
      private Boton_compra boton_compra_militar;
      private Boton_compra boton_pala;
      private Boton_compra boton_pala_accion;
      private Boton_compra boton_compra_cacahuete;
      private Boton_compra boton_girasol;
      private boolean pintar_pala = false;
      private int NUM_FILAS_CESPED = 10;
      private int NUM_COLUMNAS_CESPED = 5;
      private int NUM_CORTADORAS_CESPED = 5;
      private int NUM_SOL = 100;
      private boolean activo = false;
      private boolean activo_cacahuete = false;
      private boolean activo_girasol = false;
      private ArrayList<Planta_militar> plantasCompradas = new ArrayList<>();
      List<Planta_militar> plantasEliminar = new ArrayList<>();
      private Planta_militar planta_en_movimiento = null;
      private Tanque_cacahuete tanque_en_movimiento = null;
      private Girasol girasol_en_movimiento = null;
      private ArrayList<Zombie> zombies = new ArrayList<>();
      private ArrayList<Tanque_cacahuete> tanques_cacahuete = new ArrayList<>();
      List<Tanque_cacahuete> tanquesEliminar = new ArrayList<>();
      private ArrayList<Guisante> guisantes = new ArrayList<>();
      private ArrayList<Girasol> girasoles = new ArrayList<>();
      private ArrayList<Sol> soles = new ArrayList<>();
      private ArrayList<Sol_cielo> soles_cielo = new ArrayList<>();
      private boolean pintar_contador = false;
      private boolean pintar_contador_girasol = false;
      private boolean insuficientes_soles_planta_militar = false;
      private boolean insuficientes_soles_tanque_cacahuete = false;
      private boolean insuficientes_soles_girasol = false;
      private int acumulador_spawn_zombies = 0;
      private Cortadora_cesped cortadoras_cesped[];
      private int contador_sol_cielo = 0;
      private int num_change_militar = -1;
      private int posiciones_spawn_zombies[] = { 40 - 30, 105 - 30, 170 - 30, 235 - 30, 300 - 30 };
      private int tiempoDesdeUltimaOleada = 0;
      private int tiempoEntreOleadas = 35000;
      private int puntuacionActual = 0;
      private Random aleatorio = new Random();
      private boolean game_over = false;
      private boolean win = false;
      private int num_zombis_muertos = 0;
      private boolean fin_compras = false;
      List<Guisante> gisantesEliminar = new ArrayList<>();

      public static void main(String arg[]) {
            Juego app = new Juego();
      }

      public Juego() {
            super("Plantas VS Zombies");
            init();
            start();
      }

      public void init() {

            this.pack();
            this.setSize(SIZEX, SIZEY);
            this.setVisible(true);

            imagen = this.createImage(SIZEX, SIZEY);
            noseve = imagen.getGraphics();

            casillas = new Casilla[NUM_COLUMNAS_CESPED][NUM_FILAS_CESPED];
            cortadoras_cesped = new Cortadora_cesped[NUM_CORTADORAS_CESPED];

            try {

                  cesped = ImageIO.read(new File(
                              "/home/juan/Desktop/ASIGNATURAS/Programacion/3_EVALUACION/src/main/java/PlantasVSzombies/images/cesped.png"));

                  sol = ImageIO.read(new File(
                              "/home/juan/Desktop/ASIGNATURAS/Programacion/3_EVALUACION/src/main/java/PlantasVSzombies/images/sol.png"));

                  planta_militar = ImageIO.read(new File(
                              "/home/juan/Desktop/ASIGNATURAS/Programacion/3_EVALUACION/src/main/java/PlantasVSzombies/images/planta_militar.png"));

                  pala = ImageIO.read(new File(
                              "/home/juan/Desktop/ASIGNATURAS/Programacion/3_EVALUACION/src/main/java/PlantasVSzombies/images/pala.png"));

                  pala_accion = ImageIO.read(new File(
                              "/home/juan/Desktop/ASIGNATURAS/Programacion/3_EVALUACION/src/main/java/PlantasVSzombies/images/pala_accion.png"));

                  zombie = ImageIO.read(new File(
                              "/home/juan/Desktop/ASIGNATURAS/Programacion/3_EVALUACION/src/main/java/PlantasVSzombies/images/zombie.png"));

                  guisante = ImageIO.read(new File(
                              "/home/juan/Desktop/ASIGNATURAS/Programacion/3_EVALUACION/src/main/java/PlantasVSzombies/images/guisante.png"));

                  cortador = ImageIO.read(new File(
                              "/home/juan/Desktop/ASIGNATURAS/Programacion/3_EVALUACION/src/main/java/PlantasVSzombies/images/cortadora_cesped.png"));

                  cacahuete = ImageIO.read(new File(
                              "/home/juan/Desktop/ASIGNATURAS/Programacion/3_EVALUACION/src/main/java/PlantasVSzombies/images/cacahuete1.png"));

                  cacahuete1 = ImageIO.read(new File(
                              "/home/juan/Desktop/ASIGNATURAS/Programacion/3_EVALUACION/src/main/java/PlantasVSzombies/images/cacahuete2.png"));

                  cacahuete2 = ImageIO.read(new File(
                              "/home/juan/Desktop/ASIGNATURAS/Programacion/3_EVALUACION/src/main/java/PlantasVSzombies/images/cacahuete3.png"));

                  girasol = ImageIO.read(new File(
                              "/home/juan/Desktop/ASIGNATURAS/Programacion/3_EVALUACION/src/main/java/PlantasVSzombies/images/girasol.png"));

                  lose = ImageIO.read(new File(
                              "/home/juan/Desktop/ASIGNATURAS/Programacion/3_EVALUACION/src/main/java/PlantasVSzombies/game_over.png"));

            } catch (IOException e) {
            }
            // inicializar casillas
            for (int i = 0; i < NUM_COLUMNAS_CESPED; i++) {
                  for (int j = 0; j < NUM_FILAS_CESPED; j++) {
                        casillas[i][j] = new Casilla(100 + (j * 65), 60 + (i * 65), cesped,
                                    i * NUM_FILAS_CESPED + j + 1);
                  }
            }
            // inicializar botone_militar
            boton_compra_militar = new Boton_compra(40, 415, 150, 75);

            // inicializar boton_cacahuete
            boton_compra_cacahuete = new Boton_compra(200, 415, 150, 75);

            // inicializamos boton girasol
            boton_girasol = new Boton_compra(360, 415, 150, 75);

            // inicializamos boton pala
            boton_pala = new Boton_compra(845, 445, 55, 55);

            boton_pala_accion = new Boton_compra(845, 380, 55, 55, pala_accion);

            // inicializamos cortadoras de cesped

            for (int i = 0; i < NUM_CORTADORAS_CESPED; i++) {
                  cortadoras_cesped[i] = new Cortadora_cesped(28, 43 + (i * 65), cortador);
            }

      }

      public void start() {
            animacion = new Thread(this);
            animacion.start();
      }

      public void paint(Graphics g) {
            noseve.setColor(new Color(93, 0, 192));
            noseve.fillRect(0, 0, SIZEX, SIZEY);

            // pintar cesped
            for (int i = 0; i < NUM_COLUMNAS_CESPED; i++) {
                  for (int j = 0; j < NUM_FILAS_CESPED; j++) {
                        casillas[i][j].paint(noseve, this);
                  }
            }

            // pintar cortadoras de cesped
            for (Cortadora_cesped cortadora_cesped : cortadoras_cesped) {
                  cortadora_cesped.paint(noseve, this);
            }

            // pintar plantas compradas
            for (Planta_militar planta : plantasCompradas) {
                  planta.paint(noseve, this);
            }

            // pintar tanques de cacahuete
            for (Tanque_cacahuete tanque : tanques_cacahuete) {
                  tanque.paint(noseve, this);
            }

            // pintar girasoles
            for (Girasol girasol : girasoles) {
                  girasol.paint(noseve, this);
            }

            // pintar soles

            for (Sol sol : soles) {
                  sol.paint(noseve, this);
            }

            // pintar soles_cielo

            for (Sol_cielo sol_cielo : soles_cielo) {
                  sol_cielo.paint(noseve, this);
            }

            // INDICADOR SOLES
            noseve.setColor(Color.WHITE);
            noseve.setFont(new java.awt.Font("Arial", 1, 26));
            noseve.drawString(NUM_SOL + "", 740, 85);
            noseve.drawImage(sol, 800, 50, 50, 50, this);
            // MENU DE COMPRAS DE PLANTAS MILITARES
            boton_compra_militar.paint(noseve, this);
            noseve.setColor(Color.BLACK);
            noseve.fillRect(40, 415, 150, 75);
            noseve.drawImage(planta_militar, 140, 430, 45, 45, this);
            noseve.setColor(Color.WHITE);
            noseve.setFont(new java.awt.Font("Arial", 1, 20));
            noseve.drawString("150", 55, 465);
            noseve.drawImage(sol, 100, 445, 25, 25, this);
            if (insuficientes_soles_planta_militar) {
                  noseve.setColor(Color.RED);
                  noseve.setFont(new java.awt.Font("Arial", 1, 20));
                  noseve.drawString("Insuficientes soles", 5, 500);
                  insuficientes_soles_planta_militar = false;
            }
            // MENU DE COMPRAS DE TANQUE DE CACAHUETES
            boton_compra_cacahuete.paint(noseve, this);
            noseve.setColor(Color.BLACK);
            noseve.fillRect(200, 415, 150, 75);
            noseve.drawImage(cacahuete, 295, 425, 55, 55, this);
            noseve.setColor(Color.WHITE);
            noseve.setFont(new java.awt.Font("Arial", 1, 20));
            noseve.drawString("100", 215, 465);
            noseve.drawImage(sol, 260, 445, 25, 25, this);
            if (insuficientes_soles_tanque_cacahuete) {
                  noseve.setColor(Color.RED);
                  noseve.setFont(new java.awt.Font("Arial", 1, 20));
                  noseve.drawString("Insuficientes soles", 195, 500);
                  insuficientes_soles_tanque_cacahuete = false;
            }
            // MENU DE COMRPAS DE GIRASOL
            boton_girasol.paint(noseve, this);
            noseve.setColor(Color.BLACK);
            noseve.fillRect(360, 415, 150, 75);
            noseve.drawImage(girasol, 455, 425, 55, 55, this);
            noseve.setColor(Color.WHITE);
            noseve.setFont(new java.awt.Font("Arial", 1, 20));
            noseve.drawString("50", 385, 465);
            noseve.drawImage(sol, 415, 445, 25, 25, this);
            if (insuficientes_soles_girasol) {
                  noseve.setColor(Color.RED);
                  noseve.setFont(new java.awt.Font("Arial", 1, 20));
                  noseve.drawString("Insuficientes soles", 355, 500);
                  insuficientes_soles_girasol = false;
            }

            // MENU DE COMPRAS DE PALA
            boton_pala.paint(noseve, this);
            noseve.drawImage(pala, 845, 445, 55, 55, this);

            if (pintar_pala) {
                  boton_pala_accion.paint(noseve, this);
            }

            // pintar zombies
            for (Zombie zombie : zombies) {
                  zombie.paint(noseve, this);
            }
            // para pintar en el juego los guisantes cuando se disparen
            for (Guisante guisante : guisantes) {
                  guisante.paint(noseve, this);
            }
            // esto es para saber y adapatar la velocidad de ataque de las plantas, HAY QUE
            // QUITARLO
            for (Planta_militar planta : plantasCompradas) {

                  if (pintar_contador) {
                        noseve.setColor(Color.WHITE);
                        noseve.setFont(new java.awt.Font("Arial", 1, 20));
                        noseve.drawString(planta.getContador_velocidad_ataque() + "", planta.x + 20, planta.y - 10);

                  }
            }

            for (Girasol girasol : girasoles) {
                  if (pintar_contador_girasol) {
                        noseve.setColor(Color.WHITE);
                        noseve.setFont(new java.awt.Font("Arial", 1, 20));
                        noseve.drawString(girasol.getVelocidad_generacion_sol() + "", girasol.x + 20, girasol.y - 10);
                  }
            }

            noseve.setColor(Color.WHITE);
            noseve.setFont(new java.awt.Font("Arial", 1, 26));
            g.drawString(acumulador_spawn_zombies + "", 100, 100);

            if (game_over) {
                  noseve.setColor(Color.RED);
                  noseve.setFont(new java.awt.Font("Arial", 1, 50));
                  noseve.drawString("GAME OVER", 300, 250);
                  noseve.drawImage(lose, 415, 445, 900, 500, this);
            }

            if (win) {
                  noseve.setColor(Color.GREEN);
                  noseve.setFont(new java.awt.Font("Arial", 1, 50));
                  noseve.drawString("YOU WIN", 300, 250);
            }
            g.drawImage(imagen, 0, 0, this);
      }

      public void update(Graphics g) {
            paint(g);
      }

      public void run() {
            while (true) {
                  for (int i = 0; i < 5; i++) {
                        System.out.println(cortadoras_cesped[i].y);
                  }
                  tiempoDesdeUltimaOleada += TIME;

                  // Actualizar la puntuación (deberás implementar cómo se modifica la puntuación)

                  // Comprobar si es hora de generar una nueva oleada
                  if (tiempoDesdeUltimaOleada >= tiempoEntreOleadas) {
                        generarOleada(puntuacionActual);
                        tiempoDesdeUltimaOleada = 0; // Reiniciar el contador de tiempo
                  }

                  for (Zombie zombie : zombies) {
                        zombie.update();
                  }

                  // para que cada x tiempo, caigan soles del cielo

                  contador_sol_cielo += TIME;
                  if (contador_sol_cielo >= 15000) {
                        crearSolesCielo();
                        contador_sol_cielo = 0;
                  }

                  for (Sol_cielo sol_cielo : soles_cielo) {
                        sol_cielo.update();
                        if (sol_cielo.y >= 500) {
                              soles_cielo.remove(sol_cielo);
                        }
                  }

                  // para que cada x tiempo se cree un zombie, despues intentare hacer oleadas
                  /*
                   * acumulador_spawn_zombies += TIME;
                   * if (acumulador_spawn_zombies >= 10000) {// 25000
                   * Zombie nuevoZombie = new Zombie(850, 200, zombie);
                   * zombies.add(nuevoZombie);
                   * acumulador_spawn_zombies = 0;
                   * }
                   */
                  // para que se actualizen(movimiento) los zombies

                  for (Planta_militar planta : plantasCompradas) {
                        for (Zombie zombie1 : zombies) {
                              // esto es para que cada planta llevo su propio contador de velocidad de ataque
                              // y lanze los gisantes, cuando algun zombi este en cesped
                              for (int i = 0; i < NUM_COLUMNAS_CESPED; i++) {
                                    for (int j = 0; j < NUM_FILAS_CESPED; j++) {
                                          if (planta.isPuesta() && zombie1.intersects(casillas[i][j])) {
                                                planta.setContador_velocidad_ataque(
                                                            planta.getContador_velocidad_ataque() + 1 + contador);
                                                pintar_contador = true;
                                                if (planta.getContador_velocidad_ataque() == 800) {
                                                      guisantes.add(
                                                                  new Guisante(planta.x + 50, planta.y + 10, guisante));
                                                      planta.setContador_velocidad_ataque(0);
                                                }
                                          }
                                    }
                              }
                        }
                  }
                  // para que los guisantes se muevan
                  // importante lo del iterator, ya que es para que no de error al eliminar un
                  // guisante
                  // ya que antes estabamos influyendo en la lista de gisantes, mientras que se
                  // estaba utilizando
                  // y eso podia dar lugar a errores
                  List<Guisante> gisantesToRemove = new ArrayList<>();
                  for (Guisante guisante : guisantes) {
                        guisante.update();
                        if (guisante.x > 900) {
                              gisantesToRemove.add(guisante); // Agregar gisantes para eliminar
                        }

                        for (Zombie zombie : zombies) {
                              if (guisante.intersects(zombie)) {
                                    zombie.setVida(zombie.getVida() - 1);
                                    gisantesToRemove.add(guisante); // Agregar gisantes para eliminar

                              }
                        }
                  }
                  guisantes.removeAll(gisantesToRemove);

                  Iterator<Zombie> zombieIterator = zombies.iterator();
                  while (zombieIterator.hasNext()) {
                        Zombie zombie = zombieIterator.next();
                        if (zombie.getVida() == 0) {
                              zombieIterator.remove();
                              num_zombis_muertos++;
                              if (num_zombis_muertos >= 30) {
                                    win();
                              }
                        }
                  }
                  // para que un zombi mate a una planta
                  for (Zombie zombie : zombies) {
                        for (int i = 0; i < NUM_FILAS_CESPED; i++) {
                              Iterator<Planta_militar> plantaIterator = plantasCompradas.iterator();
                              while (plantaIterator.hasNext()) {
                                    Planta_militar planta = plantaIterator.next();
                                    if (zombie.x <= planta.x + 30 && zombie.y + 30 == planta.y) {// && zombie.y + 35 ==
                                                                                                 // planta.y) {// zombie
                                          // posY
                                          // = 200,
                                          // planta
                                          // posY =315.5
                                          zombie.x += 2;
                                          planta.setVida(planta.getVida() - 1);
                                          System.out.println("Vida de la planta: " + planta.getVida());

                                          if (planta.getVida() == 0) {
                                                plantasEliminar.add(planta);

                                                int posicionCesped = planta.getPosicion_cesped();
                                                for (int j = 0; j < NUM_COLUMNAS_CESPED; j++) {
                                                      for (int k = 0; k < NUM_FILAS_CESPED; k++) {
                                                            if (casillas[j][k]
                                                                        .getNum_identificador() == posicionCesped) {
                                                                  casillas[j][k].setOcupada(false);
                                                            }
                                                      }
                                                }
                                          }
                                    }
                              }
                              if (zombie.x <= 25) {
                                    gameOver();
                              }
                        }
                  }

                  // Elimina las plantas marcadas para eliminación
                  plantasCompradas.removeAll(plantasEliminar);
                  // codigo para que cuando un zombi toca la cortadora, esta se activa y limpia
                  // toda la linea de zombies
                  for (Cortadora_cesped cortadora_cesped : cortadoras_cesped) {
                        Iterator<Zombie> zombieIterator2 = zombies.iterator();
                        while (zombieIterator2.hasNext()) {
                              Zombie zombie = zombieIterator2.next();
                              if (zombie.x <= cortadora_cesped.x + 30 && zombie.y + 33 == cortadora_cesped.y) {
                                    zombieIterator2.remove();
                                    cortadora_cesped.setActivada(true);
                              }
                        }
                  }
                  // codigo para que el zombie le quite vida al tanque, y este vaya cambiando de
                  // forma
                  for (Zombie zombie : zombies) {
                        for (int i = 0; i < NUM_FILAS_CESPED; i++) {
                              Iterator<Tanque_cacahuete> tanqueIterator = tanques_cacahuete.iterator();
                              while (tanqueIterator.hasNext()) {
                                    Tanque_cacahuete tanque = tanqueIterator.next();
                                    if (zombie.x <= tanque.x + 30 && zombie.y + 30 == tanque.y) {
                                          zombie.x += 2;
                                          tanque.setVida(tanque.getVida() - 1);
                                          System.out.println("Vida del tanque: " + tanque.getVida());

                                          if (tanque.getVida() == 85) {
                                                tanque.setImagen(cacahuete1);
                                          } else if (tanque.getVida() == 45) {
                                                tanque.setImagen(cacahuete2);
                                          } else if (tanque.getVida() <= 0) {
                                                tanqueIterator.remove();
                                                tanque.setPuesta(false);

                                                // Encuentra la posición del césped correspondiente al tanque eliminado
                                                int posicionCesped = tanque.getPosicion_cesped();
                                                for (int j = 0; j < NUM_COLUMNAS_CESPED; j++) {
                                                      for (int k = 0; k < NUM_FILAS_CESPED; k++) {
                                                            if (casillas[j][k]
                                                                        .getNum_identificador() == posicionCesped) {
                                                                  casillas[j][k].setOcupada(false);
                                                            }
                                                      }
                                                }
                                          }
                                    }

                              }
                        }
                  }
                  // para que los zombies ataquen a los girasoles

                  for (Zombie zombie : zombies) {
                        for (int i = 0; i < NUM_FILAS_CESPED; i++) {
                              Iterator<Girasol> girasolIterator = girasoles.iterator();
                              while (girasolIterator.hasNext()) {
                                    Girasol girasol = girasolIterator.next();
                                    if (zombie.x <= girasol.x + 30 && zombie.y + 30 == girasol.y) {
                                          zombie.x += 2;
                                          girasol.setVida(girasol.getVida() - 1);
                                          System.out.println("Vida del girasol: " + girasol.getVida());

                                          if (girasol.getVida() <= 0) {
                                                girasolIterator.remove();
                                                girasol.setPuesta(false);

                                                // Encuentra la posición del césped correspondiente al girasol eliminado
                                                int posicionCesped = girasol.getPosicion_cesped();
                                                for (int j = 0; j < NUM_COLUMNAS_CESPED; j++) {
                                                      for (int k = 0; k < NUM_FILAS_CESPED; k++) {
                                                            if (casillas[j][k]
                                                                        .getNum_identificador() == posicionCesped) {
                                                                  casillas[j][k].setOcupada(false);
                                                            }
                                                      }
                                                }
                                          }
                                    }
                              }
                        }
                  }

                  // este codigo es para que cada x tiempo, cuando pones un girasol, este te da
                  // soles
                  for (Girasol girasol : girasoles) {
                        if (girasol.isPuesta())
                              girasol.setVelocidad_generacion_sol(
                                          girasol.getVelocidad_generacion_sol() + 1 + contador_girasol);
                        pintar_contador_girasol = true;
                        if (girasol.getVelocidad_generacion_sol() == 525) {
                              soles.add(new Sol(girasol.x + 15, girasol.y + 35, sol));
                              girasol.setVelocidad_generacion_sol(0);
                        }

                  }
                  for (Cortadora_cesped cortadora_cesped : cortadoras_cesped) {
                        if (cortadora_cesped.isActivada() && cortadora_cesped.x >= 1200) {

                              cortadora_cesped.setActivada(false);
                              cortadora_cesped.x = -105;
                        }
                        if (cortadora_cesped.isActivada()) {
                              cortadora_cesped.update();
                        }
                  }
                  repaint();

                  try {
                        Thread.sleep(TIME);
                  } catch (InterruptedException e) {

                  }
            }
      }

      public boolean mouseDown(Event ev, int x, int y) {
            // comprar militares
            if (boton_compra_militar.contains(x, y) && NUM_SOL >= 150 && fin_compras == false) {
                  NUM_SOL -= 150;
                  // Crear una nueva instancia de Planta_militar y agregarla a la lista
                  Planta_militar nuevaPlanta = new Planta_militar(125, 375, planta_militar);
                  plantasCompradas.add(nuevaPlanta);
            } else if (boton_compra_militar.contains(x, y) && NUM_SOL < 150) {
                  insuficientes_soles_planta_militar = true;
            }
            // asigar para que se pueda dragear la planta militar
            for (Planta_militar planta : plantasCompradas) {
                  if (planta.contains(x, y) && planta.isPuesta() != true) {
                        activo = true;
                        planta_en_movimiento = planta;

                  }
            }
            // comprar tanque de cacahuete
            if (boton_compra_cacahuete.contains(x, y) && NUM_SOL >= 100 && fin_compras == false) {
                  NUM_SOL -= 100;
                  Tanque_cacahuete nuevoTanque = new Tanque_cacahuete(275, 375, cacahuete);
                  tanques_cacahuete.add(nuevoTanque);
            } else if (boton_compra_cacahuete.contains(x, y) && NUM_SOL < 100) {
                  insuficientes_soles_tanque_cacahuete = true;
            }
            for (Tanque_cacahuete tanque : tanques_cacahuete) {
                  if (tanque.contains(x, y) && tanque.isPuesta() != true) {
                        activo_cacahuete = true;
                        tanque_en_movimiento = tanque;
                  }
            }
            // comprar girasoles
            if (boton_girasol.contains(x, y) && NUM_SOL >= 50 && fin_compras == false) {
                  NUM_SOL -= 50;
                  Girasol nuevoGirasol = new Girasol(440, 375, girasol);
                  girasoles.add(nuevoGirasol);
            } else if (boton_girasol.contains(x, y) && NUM_SOL < 50) {
                  insuficientes_soles_girasol = true;
            }
            // para asignarlos para que se pueden mover más adelante
            for (Girasol girasol : girasoles) {
                  if (girasol.contains(x, y) && girasol.isPuesta() != true) {
                        activo_girasol = true;
                        girasol_en_movimiento = girasol;
                  }
            }
            // para recoger los soles de los girasoles
            for (Sol sol : soles) {
                  if (sol.contains(x, y)) {
                        NUM_SOL += sol.getSoles_obtenidos();
                        soles.remove(sol);
                  }
            }

            // para recoger los soles del cielo
            for (Sol_cielo sol_cielo : soles_cielo) {
                  if (sol_cielo.contains(x, y)) {
                        NUM_SOL += sol_cielo.getSoles_obtenidos();
                        soles_cielo.remove(sol_cielo);
                  }
            }

            // utilizar pala
            if (boton_pala.contains(x, y)) {
                  pintar_pala = true;
            }

            return true;
      }

      // mover la planta militar
      public boolean mouseDrag(Event ev, int x, int y) {
            // mover pala cuando haya alguna planta militar

            if (activo && planta_en_movimiento != null) {
                  planta_en_movimiento.update(x, y);
                  repaint();
            }

            // mover el tanque de cacahuete
            if (activo_cacahuete && tanque_en_movimiento != null) {
                  tanque_en_movimiento.update(x, y);
                  repaint();
            }
            // mover el girasol
            if (activo_girasol && girasol_en_movimiento != null) {
                  girasol_en_movimiento.update(x, y);
                  repaint();
            }

            // mover la pala cuando haya una planta militar al menos puesta
            for (Planta_militar planta : plantasCompradas) {
                  if (pintar_pala && planta.isPuesta() != false) {
                        boton_pala_accion.update(x, y);
                        repaint();
                  } else {
                        pintar_pala = false;
                        boton_pala_accion.update(875, 415);
                  }

            }

            // mover la pala cuando haya un cacahuete al menos puesto
            for (Tanque_cacahuete tanque : tanques_cacahuete) {
                  if (pintar_pala && tanque.isPuesta() != false) {
                        boton_pala_accion.update(x, y);
                        repaint();
                  } else {
                        pintar_pala = false;
                        boton_pala_accion.update(875, 415);
                  }

            }

            // mover la pala cuando haya un girsaol al menos puesto
            for (Girasol girasol : girasoles) {
                  if (pintar_pala && girasol.isPuesta() != false) {
                        boton_pala_accion.update(x, y);
                        repaint();
                  } else {
                        pintar_pala = false;
                        boton_pala_accion.update(875, 415);
                  }

            }

            return true;
      }

      public boolean mouseUp(Event ev, int x, int y) {
            if (activo && planta_en_movimiento != null) {
                  // Buscar la casilla de césped en la que se soltó el ratón
                  for (int i = 0; i < NUM_COLUMNAS_CESPED; i++) {
                        for (int j = 0; j < NUM_FILAS_CESPED; j++) {
                              if (casillas[i][j].contains(x, y) && !casillas[i][j].isOcupada()) {
                                    planta_en_movimiento.update(casillas[i][j].x + Casilla.SIZE / 2,
                                                casillas[i][j].y + Casilla.SIZE / 2 - 20);
                                    planta_en_movimiento.setPuesta(true);
                                    planta_en_movimiento.setPosicion_cesped(casillas[i][j].getNum_identificador());
                                    System.out.println(
                                                "Planta puesta en la pos " + planta_en_movimiento.getPosicion_cesped());
                                    casillas[i][j].setOcupada(true);
                                    plantasCompradas.add(planta_en_movimiento);
                                    planta_en_movimiento = null;
                                    activo = false;

                                    break;
                              }
                        }
                  }
            }
            repaint();
            // hacer que se quede fijo en una casilla del cesped pero ahora para los
            // cacahuetes
            if (activo_cacahuete && tanque_en_movimiento != null) {
                  for (int i = 0; i < NUM_COLUMNAS_CESPED; i++) {
                        for (int j = 0; j < NUM_FILAS_CESPED; j++) {
                              if (casillas[i][j].contains(x, y) && !casillas[i][j].isOcupada()) {
                                    tanque_en_movimiento.update(casillas[i][j].x + Casilla.SIZE / 2,
                                                casillas[i][j].y + Casilla.SIZE / 2 - 20);
                                    tanque_en_movimiento.setPuesta(true);
                                    tanque_en_movimiento.setPosicion_cesped(casillas[i][j].getNum_identificador());
                                    System.out.println(
                                                "Tanque puesta en la pos " + tanque_en_movimiento.getPosicion_cesped());
                                    casillas[i][j].setOcupada(true);
                                    tanques_cacahuete.add(tanque_en_movimiento);
                                    tanque_en_movimiento = null;
                                    activo_cacahuete = false;

                                    break;
                              }
                        }
                  }
            } // hacer que se quede fijo en una casilla del cesped pero ahora para los
              // girasoles

            if (activo_girasol && girasol_en_movimiento != null) {
                  for (int i = 0; i < NUM_COLUMNAS_CESPED; i++) {
                        for (int j = 0; j < NUM_FILAS_CESPED; j++) {
                              if (casillas[i][j].contains(x, y) && !casillas[i][j].isOcupada()) {
                                    girasol_en_movimiento.update(casillas[i][j].x + Casilla.SIZE / 2,
                                                casillas[i][j].y + Casilla.SIZE / 2 - 20);
                                    girasol_en_movimiento.setPuesta(true);
                                    girasol_en_movimiento.setPosicion_cesped(casillas[i][j].getNum_identificador());
                                    System.out.println(
                                                "Girasol puesta en la pos "
                                                            + girasol_en_movimiento.getPosicion_cesped());
                                    casillas[i][j].setOcupada(true);
                                    girasoles.add(girasol_en_movimiento);
                                    girasol_en_movimiento = null;
                                    activo_girasol = false;

                                    break;
                              }
                        }
                  }
            }
            // Utilizar pala y eliminar las plantas
            for (Iterator<Planta_militar> iterador = plantasCompradas.iterator(); iterador.hasNext();) {
                  Planta_militar planta = iterador.next();
                  int x_planta = (int) planta.getX(); // Obtén la coordenada x de la planta
                  int y_planta = (int) planta.getY(); // Obtén la coordenada y de la planta

                  if (pintar_pala && planta.isPuesta() && planta.contains(x, y)) {
                        iterador.remove(); // Elimina la planta de la lista

                        // Actualiza el estado del césped y la casilla
                        int posicionCesped = planta.getPosicion_cesped();
                        for (int i = 0; i < NUM_COLUMNAS_CESPED; i++) {
                              for (int j = 0; j < NUM_FILAS_CESPED; j++) {
                                    if (casillas[i][j].getNum_identificador() == posicionCesped) {
                                          casillas[i][j].setOcupada(false);
                                    }
                              }
                        }

                        boton_pala_accion.update(875, 415);
                        NUM_SOL += planta.getCoste() / 2;
                  }
            }

            // Utilizar pala y eliminar los tanques

            for (Iterator<Tanque_cacahuete> iterador = tanques_cacahuete.iterator(); iterador.hasNext();) {
                  Tanque_cacahuete tanque = iterador.next();
                  int x_tanque = (int) tanque.getX(); // Obtén la coordenada x de la planta
                  int y_tanque = (int) tanque.getY(); // Obtén la coordenada y de la planta

                  if (pintar_pala && tanque.isPuesta() && tanque.contains(x, y)) {
                        iterador.remove(); // Elimina la planta de la lista

                        // Actualiza el estado del césped y la casilla
                        int posicionCesped = tanque.getPosicion_cesped();
                        for (int i = 0; i < NUM_COLUMNAS_CESPED; i++) {
                              for (int j = 0; j < NUM_FILAS_CESPED; j++) {
                                    if (casillas[i][j].getNum_identificador() == posicionCesped) {
                                          casillas[i][j].setOcupada(false);
                                    }
                              }
                        }

                        boton_pala_accion.update(875, 415);
                        NUM_SOL += tanque.getCoste() / 2;
                  }
            }

            // Utilizar pala y eliminar los girasoles
            for (Girasol girasol : girasoles) {
                  Iterator<Girasol> iteradorGirasoles = girasoles.iterator();
                  while (iteradorGirasoles.hasNext()) {
                        Girasol girasoles1 = iteradorGirasoles.next();
                        if (pintar_pala && girasoles1.isPuesta() && girasoles1.contains(x, y)) {
                              iteradorGirasoles.remove();
                              girasoles.remove(girasol);
                              girasoles1.setPuesta(false);
                              // casillas[girasoles1.getPosicion_cesped()].setOcupada(false);
                              boton_pala_accion.update(875, 415);
                              NUM_SOL += girasol.getCoste() / 2;
                        }
                  }
            }

            return true;
      }

      // Función para generar una oleada de zombies basada en la puntuación actual
      void generarOleada(int puntuacion) {
            int numeroZombies = calcularNumeroZombies(puntuacion); // Ejemplo: más puntuación, más zombies

            for (int i = 0; i < numeroZombies; i++) {
                  int posY = posiciones_spawn_zombies[aleatorio.nextInt(posiciones_spawn_zombies.length)];
                  Zombie nuevoZombie = new Zombie(850, posY, zombie); // Cambia la posición Y
                  zombies.add(nuevoZombie);
            }
      }

      int calcularNumeroZombies(int puntuacion) {
            // Puedes diseñar tu propia lógica aquí, por ejemplo, aumentar el número con la
            // puntuación
            int baseZombies = 3; // Número base de zombies
            int zombiesPorPuntuacion = puntuacion / 100; // Ejemplo: 1 zombie por cada 100 puntos
            return baseZombies + zombiesPorPuntuacion;
      }

      private void crearSolesCielo() {

            int x = (int) (Math.random() * SIZEX - 300);

            Sol_cielo nuevoSol_Cielo = new Sol_cielo(x, 50, sol);
            soles_cielo.add(nuevoSol_Cielo);
      }

      public void gameOver() {
            for (Zombie zombie : zombies) {
                  zombie.setVida(0);
            }
            for (Planta_militar planta : plantasCompradas) {
                  planta.setVida(0);
            }
            for (Tanque_cacahuete tanque : tanques_cacahuete) {
                  tanque.setVida(0);
            }

            game_over = true;
            TIME = 0;
            fin_compras = true;
      }

      public void win() {
            for (Zombie zombie : zombies) {
                  zombie.setVida(0);
            }
            for (Planta_militar planta : plantasCompradas) {
                  planta.setVida(0);
            }
            for (Tanque_cacahuete tanque : tanques_cacahuete) {
                  tanque.setVida(0);
            }

            win = true;
            TIME = 0;
            fin_compras = true;

      }

      // para salir del juego
      public boolean keyDown(Event ev, int tecla) {
            if (tecla == 27)
                  System.exit(0);

            return true;
      }

}
