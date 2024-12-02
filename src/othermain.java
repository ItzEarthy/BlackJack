package defaul;

public class Main implements Runnable{
		BlackjackGUI gui=new BlackjackGUI();
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		new Thread(new Main()).start();//stars new thread to run class to run method
	}
	
	public void run() {
		
	}

}
