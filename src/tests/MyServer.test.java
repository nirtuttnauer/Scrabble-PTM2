
public static boolean testServer() {
		boolean ok=true;
		Random r=new Random();
		int port=6000+r.nextInt(1000);
		MyServer s=new MyServer(port, new ClientHandler1());
		int c = Thread.activeCount();
		s.start(); // runs in the background
		try {
			client1(port);
		}catch(Exception e) {
			System.out.println("some exception was thrown while testing your com.example.scrabble.server, cannot continue the test (-100)");
			ok=false;
		}
		s.close();

		try {Thread.sleep(2000);} catch (InterruptedException e) {}

		if (Thread.activeCount()!=c) {
			System.out.println("you have a thread open after calling close method (-100)");
			ok=false;
		}
		return ok;
	}