package apps;

public class ExceptionDemo2 {

	public static void main(String[] args) throws DivisionByOneSelfMade {
		// Demo, die bei Ausführung eine Exception bzgl. des Teilens durch 1 anzeigt
		int zaehler = 30;
		int nenner = 1;
		int ergebnis;
		
		if (nenner == 1) {
			//throw new DivisionByOneSelfMade();
			throw new DivisionByOneSelfMade("Ich verbiete jetzt, dass wir durch 1 teilen.");
		} else {	
			ergebnis = zaehler/nenner;
			System.out.println(ergebnis);
		}	
	}
}
