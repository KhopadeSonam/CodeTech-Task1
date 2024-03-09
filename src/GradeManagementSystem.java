import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

class Student {
	private String name;
	private int rollNumber;
	private Map<String,Integer> marks;
	
	public Student(String name,int rollNumber) {
		this.name=name;
		this.rollNumber=rollNumber;
		this.marks=new HashMap<>();
	}
	
	public String getName() {
		return name;
	}
	
	public int getRollnumber() {
		return rollNumber;
	}
	
	public void addSubjectMark(String subject,int mark) {
		marks.put(subject, mark);
	}
	
	public void updateSubjectMark(String subject,int mark) {
		if(marks.containsKey(subject)) {
			marks.put(subject, mark);
		}
		else {
			System.out.println("Subject not found for this student");
		}
	}
	
	public void deleteSubjectMark(String subject) {
		marks.remove(subject);
	}
	
	public double calculatePercentage() {
		int totalMarks=marks.values().stream().mapToInt(Integer::intValue).sum();
		return (double)totalMarks/marks.size();
	}
	
	public String calculateGrade() {
		double percentage=calculatePercentage();
		if(percentage>=90) {
			return "O";
		}
		else if(percentage>=80) {
			return "A";
		}
		else if(percentage>=70) {
			return "B";
		}
		else if(percentage>=60) {
			return "C";
		}
		else if(percentage>=50) {
			return "D";
		}
		else {
			return "Fail";
		}
	}
}
public class GradeManagementSystem {
	public static void main(String[] args) {
		Scanner sc=new Scanner(System.in);
		
		System.out.println("Enter the Student name: ");
		String name=sc.nextLine();
		
		System.out.println("Enter student roll number: ");
		int rollNumber=sc.nextInt();
		
		Student st=new Student(name,rollNumber);
		
		System.out.println("Enter number of subjects: ");
		int numSubjects=sc.nextInt();
		
		for(int i=0;i<numSubjects;i++) {
			System.out.println("Enter the subject name: ");
			String subject=sc.next();
			
			System.out.println("Enter marks for "+subject+": ");
			int marks=sc.nextInt();
			
			st.addSubjectMark(subject, marks);
		}
		
		System.out.println("Percentage: "+st.calculatePercentage());
		System.out.println("Grade: "+st.calculateGrade());
		
		System.out.println("Do you want to update and delete subject marks? (Y/N)");
		String choise=sc.next();
		
		if(choise.equalsIgnoreCase("Y")) {
			System.out.println("Enter subject name to update or delete: ");
			String subject=sc.next();
			System.out.println("Do you want to update or delete marks for "+subject+"?(U/D)");
			
			String action=sc.next();
			
			if(action.equalsIgnoreCase("U")) {
				System.out.println("Enter new marks for "+subject+": ");
				int newMark=sc.nextInt();
				st.updateSubjectMark(subject, newMark);
				System.out.println("Marks for "+subject+" updated successfully...");
			}
			
			else if(action.equalsIgnoreCase("D")) {
				st.deleteSubjectMark(subject);
				System.out.println("Marks for "+subject+" deleted successfully...");
			}
			
			else {
				System.out.println("Invalid action.");
		    }
			
			System.out.println("Updated Percentege: "+st.calculatePercentage());
			System.out.println("Updated Grade: "+st.calculateGrade());
		}
		sc.close();
		
	}
}