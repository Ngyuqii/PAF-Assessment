package paf.assessment.repository;

public class SqlQueries {

	/*-- Retrieve required data from table
	SELECT * FROM accounts;
	SELECT * FROM accounts WHERE account_id = 'V9L3Jd1BBI';
	*/

	public static final String SQL_SELECTALL = """
		SELECT * FROM accounts
		""";

	public static final String SQL_SELECTBYACCTID = """
		SELECT * FROM accounts WHERE account_id = ?
		""";
	
}
