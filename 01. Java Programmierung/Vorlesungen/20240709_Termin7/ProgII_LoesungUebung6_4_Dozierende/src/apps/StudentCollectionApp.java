package apps;
import model.*;

public class StudentCollectionApp {

	public static void main(String[] args) {
		StudentCollection collection = new StudentCollection();
		System.out.println(collection + "\n\n");
		
		collection.add(new Student("Bruno Harms", new Location("Geburtsstr. 1", 38875, "Sorge"), new Location("Schlaue Str. 42", 38875, "Elend")));
	
		Student student = new Student("Julia Bach", new Location("Geburtsstr. 2", 38875, "Sorge"), new Location("Schlaue Str. 314", 38875, "Elend"));
		collection.add(student);
	
		System.out.println(collection + "\n\n");
		
		collection.remove(student);
		
		System.out.println(collection + "\n\n");
		
		
		Student myStudent2 = collection.getStudentData("Julia Bach");
		System.out.println("Name Julia Bach: " + myStudent2);
		
		Student myStudent3 = collection.getStudentData("Bruno Harms");
		System.out.println("Name Bruno Harms: " + myStudent3);
		
		System.out.println("Eintrag 10001 existiert? "+ collection.entryExists(10001));

        //Verwendung von Subject:
		System.out.println("\n Beispiel mit Verwendung von Subject:");
        collection.add(
				new Student(
						"Mathilda Schmitz",
						new Location ("Geburtsstr. 22", 87719, "Katzenhirn"),
						new Location ("Studentenstr. 42", 91586, "Schlauersbach"),
						Subject.BUSINESS_INFORMATICS
				)
		);

      	Student myStudent4 = collection.getStudentData("Mathilda Schmitz");
      	System.out.println(myStudent4);
	}

}