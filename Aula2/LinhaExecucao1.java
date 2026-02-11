class Paralelo1 extends Thread {
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

public class LinhaExecucao1 { // ainda sem threads
	public static void main(String[] args) {
		Paralelo1 par1 = new Paralelo1();
		Paralelo1 par2 = new Paralelo1();
		par1.setFlechas(5);
		par1.setLoop(20);
		par2.setFlechas(10);
		par2.setLoop(20);
		par1.start();
		par2.start();
	}
}