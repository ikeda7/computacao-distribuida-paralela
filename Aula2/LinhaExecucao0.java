class Paralelo0 {
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
	public void roda() {
		for (int i=0;i<loop;i++) {
			System.out.println(sFlechas + " " + i);
		}
	}
}

public class LinhaExecucao0 { // ainda sem threads
	public static void main(String[] args) {
		Paralelo0 par1 = new Paralelo0();
		Paralelo0 par2 = new Paralelo0();
		par1.setFlechas(5);
		par1.setLoop(20);
		par2.setFlechas(10);
		par2.setLoop(20);
		par1.roda();
		par2.roda();
	}
}