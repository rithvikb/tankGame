
import java.applet.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.geom.*;
import java.awt.image.*;
import java.util.*;

import javax.swing.JOptionPane;

public class MainGame extends Applet implements Runnable, KeyListener  {
        Thread gameloop;
        BufferedImage backbuffer;
       
        Graphics2D g2d;
        //toggle for drawing bounding boxes
        boolean showBounds = false;
        int t = 100;
      
        int BULLETS = 50;
        Bullet[] bullet = new Bullet[BULLETS];
        int currentBullet = 0;

        AffineTransform identity = new AffineTransform();
        ImageEntity tank1 = new ImageEntity(this);
        ImageEntity tank2 = new ImageEntity(this);
        int p1score = 0;
        int p2score = 0;
        int power1 = 1;
        int power2 = 1;


        public void init() {
            //create the back buffer for smooth graphics
            backbuffer = new BufferedImage(640, 480, BufferedImage.TYPE_INT_RGB);
            g2d = backbuffer.createGraphics();
            this.setSize(640, 300);
            
            tank1.setX(200);
            tank1.setY(200);
            tank2.setX(300);
            tank2.setY(200);
            tank1.load("tank1.png");
            tank2.load("tank2.png");
            tank1.setGraphics(g2d);
            tank2.setGraphics(g2d);

            //set up the bullets
            for (int n = 0; n<BULLETS; n++) {
                bullet[n] = new Bullet();
            }

            addKeyListener(this);
        }

        public void update(Graphics g) {
            //start off transforms at identity
            g2d.setTransform(identity);

            //erase the background
            g2d.setPaint(Color.BLACK);
            g2d.fillRect(0, 0, getSize().width, getSize().height);
           

            drawBackground();
            drawTanks();
            drawBullets();
            //STATUSES
            g2d.setColor(Color.BLACK);
            g2d.drawString("Tank 1 score: " + p1score, 5, 10);
            g2d.drawString("Tank 1 power: " + power1, 5, 25);
            g2d.drawString("Tank 2 score: " + p2score, 5, 40);
            g2d.drawString("Tank 2 power: " + power2, 5, 55);
            paint(g);
        }

 
        public void drawTanks() {
        	tank1.transform();
        	tank2.transform();
        	tank1.draw();
        	tank2.draw();
        	if(showBounds) 
        	{
        		g2d.setTransform(identity);
        		g2d.setColor(Color.BLUE);
        		g2d.draw(tank1.getBounds());
        		g2d.draw(tank2.getBounds());
        	}
        }

          public void drawBullets() {
            for (int n = 0; n < BULLETS; n++) {
                if (bullet[n].isAlive()) {
                    //draw the bullet
                    g2d.setTransform(identity);
                    g2d.translate(bullet[n].getX(), bullet[n].getY());
                    g2d.setColor(Color.RED);
                    g2d.draw(bullet[n].getShape());
                }
            }
        }
          public void drawBackground() 
          {
        	  	g2d.setColor(Color.CYAN);
        	  	g2d.fillRect(0, 0, getSize().width, getSize().height);
        	  	g2d.setColor(Color.GREEN);
        	  	g2d.fillRect(0, 235, getSize().width, getSize().height);
        	  	g2d.setColor(Color.YELLOW);
        	  	g2d.fillOval(500, 70, 50, 50);
          }

        public void paint(Graphics g) {
            g.drawImage(backbuffer, 0, 0, this);
        }
        public void start() {
            gameloop = new Thread(this);
            gameloop.start();
        }
 
        public void run() {
            //acquire the current thread
            Thread t = Thread.currentThread();
            //keep going as long as the thread is alive
            while (t == gameloop) {
                try
                {
                    Thread.sleep(20);
                }
                catch(InterruptedException e) {
                    e.printStackTrace();
                }
                gameUpdate();
                repaint();
            }
        }
        public void stop() {
            gameloop = null;
        }
        private void gameUpdate() {

            updateTanks();
            updateBullets();
            checkCollisions();
            
        }
        public void updateTanks() {
        	tank1.incX(tank1.getVelX());
            if (tank1.getX() < -10)
                tank1.setX(getSize().width + 10);
            else if (tank1.getX() > getSize().width + 10)
                tank1.setX(-10);
            //update ship's Y position, wrap around top/bottom
            tank1.incY(tank1.getVelY());
            if (tank1.getY() < -10)
                tank1.setY(getSize().height + 10);
            else if (tank1.getY() > getSize().height + 10)
                tank1.setY(-10);
            tank2.incX(tank2.getVelX());
            if (tank2.getX() < -10)
                tank2.setX(getSize().width + 10);
            else if (tank2.getX() > getSize().width + 10)
                tank2.setX(-10);
            //update ship's Y position, wrap around top/bottom
            tank2.incY(tank2.getVelY());
            if (tank2.getY() < -10)
                tank2.setY(getSize().height + 10);
            else if (tank2.getY() > getSize().height + 10)
                tank2.setY(-10);
        }
        
        public void updateBullets() {
            //move the bullets
            for (int n = 0; n < BULLETS; n++) {
                if (bullet[n].isAlive()) {
                    //update bullet's x position
                    bullet[n].incX(bullet[n].getVelX());
                    //bullet disappears at left/right edge
                    if (bullet[n].getX() < 0 ||
                        bullet[n].getX() > getSize().width)
                    {
                        bullet[n].setAlive(false);
                    }
                    //update bullet's y position
                    bullet[n].incVelY(0.025);
                    bullet[n].incY(bullet[n].getVelY());
                    //bullet disappears at top/bottom edge
                    if (bullet[n].getY() < 0 ||
                        bullet[n].getY() > getSize().height)
                    {
                        bullet[n].setAlive(false);
                    }
                }
            }
        }
              public void checkCollisions() {
      
                    for (int n = 0; n < BULLETS; n++) {
                        if (bullet[n].isAlive()) {
                            //perform the collision test
                            if (tank1.getBounds().contains(bullet[n].getX(), bullet[n].getY())&&bullet[n].getVelX()<0)
                            {
                                bullet[n].setAlive(false);
                                tank1.setAlive(false);
                                JOptionPane.showMessageDialog(null, "Tank 1 hit.");
                                p2score++;                              
                            }
                        }
                    }
                    	
                    for (int n = 0; n < BULLETS; n++) {
                        if (bullet[n].isAlive()) {
                            //perform the collision test
                            if (tank2.getBounds().intersects( bullet[n].getBounds())&&bullet[n].getVelX()>0)
                            {
                                bullet[n].setAlive(false);
                                tank2.setAlive(false);
                                JOptionPane.showMessageDialog(null, "Tank 2 hit.");
                                p1score++;
                                
                            }
                        }
                    }
              }
        public void keyReleased(KeyEvent k) { }
        public void keyTyped(KeyEvent k) { }
        public void keyPressed(KeyEvent k) {
            int keyCode = k.getKeyCode();

            switch (keyCode) {

            case KeyEvent.VK_LEFT:
                //left arrow rotates ship left 5 degrees
                tank2.incVelX(-1);
              break;

            case KeyEvent.VK_RIGHT:
                //right arrow rotates ship right 5 degrees
               tank2.incVelX(1);
                break;
                
            case KeyEvent.VK_A:
            		tank1.incVelX(-1);
            		break;
            case KeyEvent.VK_D:
            		tank1.incVelX(1);
            		break;
            case KeyEvent.VK_Q:
            		power1 = Math.abs((power1-1)%5);
            		break;
            case KeyEvent.VK_E:
            		power1 = Math.abs((power1+1)%5);
            		break;
            case KeyEvent.VK_CLOSE_BRACKET:
            		power2 = Math.abs((power2+1)%5);
            		break;
            case KeyEvent.VK_OPEN_BRACKET:
            		power2 = Math.abs((power2-1)%5);
            		break;

            case KeyEvent.VK_UP:
            	currentBullet++;
                if (currentBullet > BULLETS - 1) currentBullet = 0;
                bullet[currentBullet].setAlive(true);
                //point bullet in same direction ship is facing
                bullet[currentBullet].setX(tank2.getCenterX());
                bullet[currentBullet].setY(tank2.getCenterY());
                bullet[currentBullet].setVelX(-power2);
                bullet[currentBullet].setVelY(-3);
          break;
            case KeyEvent.VK_W:
                //fire a bullet
                currentBullet++;
                if (currentBullet > BULLETS - 1) currentBullet = 0;
                bullet[currentBullet].setAlive(true);
               
                bullet[currentBullet].setX(tank1.getCenterX());
                bullet[currentBullet].setY(tank1.getCenterY());
                bullet[currentBullet].setVelX(power1);
                bullet[currentBullet].setVelY(-3);
                break;

            case KeyEvent.VK_B:
                //toggle bounding rectangles
                showBounds = !showBounds;
                break;
            }
        }


}
