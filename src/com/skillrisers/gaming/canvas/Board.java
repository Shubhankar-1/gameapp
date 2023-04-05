package com.skillrisers.gaming.canvas;

import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JPanel;
import javax.swing.Timer;

import com.skillrisers.gaming.sprites.KenPlayer;
import com.skillrisers.gaming.sprites.RyuPlayer;
import com.skillrisers.gaming.utils.GameConstants;



public class Board extends JPanel implements GameConstants {
	BufferedImage imageBg;
	private RyuPlayer ryuPlayer;
	private KenPlayer kenPlayer;
	
	private Timer timer;
	
	private void gameLoop() {
		timer = new Timer(GAME_LOOP,new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				repaint();
				ryuPlayer.fall();
				kenPlayer.fall();
				
			}
		});
		timer.start();
	}
	
	
	public Board() throws IOException  {
		
		loadBackgroundImage();
		ryuPlayer = new RyuPlayer();
		kenPlayer = new KenPlayer();
		setFocusable(true);
		bindEvents();
		gameLoop();
		
		
	}
	
	private void bindEvents() {
		this.addKeyListener(new KeyAdapter() {
			
			
			
			@Override
			public void keyReleased(KeyEvent e) {
				ryuPlayer.setSpeed(0);
				
			}
			
			@Override
			public void keyPressed(KeyEvent e) {
				if(e.getKeyCode() == KeyEvent.VK_A) {
					ryuPlayer.setSpeed(-SPEED);
					ryuPlayer.move();
					//repaint();
				}
				else if (e.getKeyCode() == KeyEvent.VK_SPACE) {
					ryuPlayer.jump();
				}
				else if(e.getKeyCode() == KeyEvent.VK_D) {
					ryuPlayer.setSpeed(SPEED);
					ryuPlayer.move();
					//repaint();
				}
				// Ryu Kick
				else if (e.getKeyCode()== KeyEvent.VK_K) {
					ryuPlayer.setCurrentMove(KICK);
				}
				// Ryu Punch
				else if (e.getKeyCode()== KeyEvent.VK_P) {
					ryuPlayer.setCurrentMove(PUNCH);
				}
				// Ken 
				else if (e.getKeyCode() == KeyEvent.VK_LEFT) {
					kenPlayer.setSpeed(-SPEED);
					kenPlayer.move();
					//repaint();
				}
				else if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
					kenPlayer.setSpeed(SPEED);
					kenPlayer.move();
					//repaint();
				}
				else if (e.getKeyCode() == KeyEvent.VK_UP) {
					kenPlayer.jump();
				}
				else if (e.getKeyCode() == KeyEvent.VK_CONTROL) {
					kenPlayer.setCurrentMove(KICK);
				}
				// Ryu Punch
				else if (e.getKeyCode() == KeyEvent.VK_SHIFT) {
					kenPlayer.setCurrentMove(PUNCH);
				}
				
				
			}
		});
	}
	
	
	
	@Override
	public void paintComponent(Graphics pen) {
		// Rendering / Painting
		super.paintComponent(pen);
		printBackgroundImage(pen);
		ryuPlayer.printPlayer(pen);
		kenPlayer.printPlayer(pen);
		
		
		
	}

	
	private void printBackgroundImage(Graphics pen) {
		pen.drawImage(imageBg,0,0, 1400,900, null);
	}
	
	
	
	private void loadBackgroundImage() {
		try {
			imageBg = ImageIO.read(Board.class.getResource("bg.jpeg"));
			}
			catch(Exception ex) {
				System.out.println("Background Image Loading Fail...");
				System.exit(0);
			
			}
	}
}
