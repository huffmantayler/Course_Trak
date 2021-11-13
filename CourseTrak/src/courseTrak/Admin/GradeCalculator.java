package courseTrak.Admin;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class GradeCalculator {
	Student student;
	int classNum;
	public GradeCalculator(Student student, int classNum) {
		this.student = student;
		this.classNum = classNum;
	}
	private double calcAverageForClass()
	{
		double gradeTotal = 0;
		double gradeCount = 0;
		double avg = 0;
		try {
	        Connection con = DBConnection.getConnection();
	        PreparedStatement ps = con.prepareStatement("SELECT NumberGrade FROM Assignments WHERE StudentID = ? AND ClassNum = ?");
            ps.setString(1, Integer.toString(student.getID()));
            ps.setInt(2, classNum);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                gradeTotal = gradeTotal + rs.getInt("NumberGrade");
                gradeCount+=1;
            }
	        avg = gradeTotal / gradeCount;
            
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return avg;
	}
	public String getLetterGrade() {
		double avg = calcAverageForClass();
		if (avg >= 90)
		{
			return "A";
		}
		else if (avg >= 80)
		{
			return "B";
		}
		else if (avg >= 70)
		{
			return "C";
		}
		else if (avg >= 60)
		{
			return "D";
		}
		else
		{
			return "F";
		}
	}
	}

