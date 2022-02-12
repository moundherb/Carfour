import java.util.Random;
import java.util.concurrent.Semaphore;


public class Main{
	public static Semaphore Feu1 = new Semaphore(1);
	public static Semaphore Feu2 = new Semaphore(0);
	public static Semaphore canpass= new Semaphore(1);
	static int nbv1 = 0;
	static int nbv2 = 0;
	public static void main(String[] args)  throws InterruptedException {
		Random random = new Random();
		int id_v1 = 1 ;
		int id_v2 = 1;
		Frame f = new Frame();
		
		Changer changer = new Changer(1);
		Thread Tchanger = new Thread(changer);
		Tchanger.start();
		
		
		while(true) {
			if(random.nextInt(2) == 0) {
				if(nbv1 < 10) {
					new CarVoie1(id_v1).start();
					id_v1  ++;
					nbv1++;
					Thread.sleep(random.nextInt(5000));
				}
			}else {
				if(nbv2 < 10) {
					new CarVoie2(id_v2).start();
					id_v2  ++;
					nbv2++;
					Thread.sleep(random.nextInt(5000));
				}
			}
		}
		
		
	}
	

	

}
