package ie.gmit.sw.ai;

import java.awt.Component;

public class UpdateView {

	private static UpdateView instance;
	
	public static UpdateView getInstance(){
		if (instance == null){
			instance = new UpdateView();
		}
		
		return instance;
	}
	
	private UpdateView() {
	}
	
	private Component view;
	
	public void setView(Component view){
		this.view = view;
	}
	
	public void repaint(){
		this.view.repaint();
	}
}
