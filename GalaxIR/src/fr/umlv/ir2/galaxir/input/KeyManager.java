package fr.umlv.ir2.galaxir.input;

import javax.swing.JFrame;

import fr.umlv.remix.KeyHandler;

public class KeyManager implements KeyHandler{
	JFrame frame;
	public KeyManager(JFrame frame) {
		this.frame = frame;
	}
	@Override
	public void keyPressed(char arg0) {
		// do nothing
	}

	@Override
	public void keyReleased(char arg0) {
		// do nothing
	}

	@Override
	public void keyTyped(char arg0) {            
		switch (arg0) {
		default:
			//do nothing
			break;
		}
	}
	
	@Override
	public JFrame getParentFrame() {
		return frame;
	}
}
