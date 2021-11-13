package courseTrak.Admin;

/**
 *
 * @author caityln
 */
public class ClassObj {
	String className;
	int classNumber;
	int credit;
	String description;
	String type;
	public ClassObj(String name, int classNum, int credit, String description, String type)
	{
		this.className = name;
		this.classNumber = classNum;
		this.credit = credit;
		this.description = description;
		this.type = type;
	}
	
	public String getClassName()
	{
		return className;
	}
	
	public int getClassNumber()
	{
		return classNumber;
	}
	
	public int getCredit()
	{
		return credit;
	}
	
	public String getDescription()
	{
		return description;
	}
	
	public String getType()
	{
		return type;
	}
	

}