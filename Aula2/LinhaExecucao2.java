class Paralelo2 implements Runnable {
	private int loop;
	private String sFlechas;
	public void setFlechas(int nFlechas) {
		sFlechas ="";
		for (int i=0;i<nFlechas;i++) {
			sFlechas += ">";
		}
	}
	public void setLoop(int loop) {
		this.loop = loop;
	}
	public void run() {
		for (int i=0;i<loop;i++) {
			System.out.println(sFlechas + " " + i);
		}
	}
}

public class LinhaExecucao2 { // ainda sem threads
	public static void main(String[] args) {
		Paralelo2 par1 = new Paralelo2();
		Paralelo2 par2 = new Paralelo2();
		par1.setFlechas(5);
		par1.setLoop(20);
		par2.setFlechas(10);
		par2.setLoop(20);
		Thread le1 = new Thread(par1);
		Thread le2 = new Thread(par2);
		le1.start();
		le2.start();
	}
}