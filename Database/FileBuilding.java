
public interface FileBuilding {
	/*it takes the path or the name of database and create dictionary to it into databases file
	 *it return boolean that is true if possible or false if not
	 */
	public  Boolean createDB(String name) ;
	/*it takes the path or the name of database and delete dictionary to it into databases file
	 *it return boolean that is true if possible or false if not
	 */
	public  Boolean dropDB(String name) ;
	/*it takes 4 parameters 
	 * content that's the data of the table
	 * fields that's the names of fields
	 * table name 
	 * database name
	 *it return boolean that is true if possible or false if not
	 */
	public  boolean write(String [][]content,String [] fields,String TableName,String DBName);
	
public  boolean read(String TableName,String DBName);
}
