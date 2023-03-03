package paf.assessment.repository;

public class SqlQueries {

	/*-- Retrieve required data from table
	SELECT * FROM accounts;
	SELECT * FROM accounts WHERE account_id = 'V9L3Jd1BBI';

	-- Update balance field of the 2 accounts
	UPDATE accounts
	SET balance = balance - 10
	WHERE account_id = 'V9L3Jd1BBI';

	UPDATE accounts
	SET balance = balance + 50
	WHERE account_id = 'V9L3Jd1BBI';
	*/

	public static final String SQL_SELECTALL = """
		SELECT * FROM accounts
		""";

	public static final String SQL_SELECTBYACCTID = """
		SELECT * FROM accounts WHERE account_id = ?
		""";
	
	public static final String SQL_TRANSFERFROM = """
		UPDATE accounts	SET balance = balance - ? WHERE account_id = ?
		""";

	public static final String SQL_TRANSFERTO = """
		UPDATE accounts SET balance = balance + ? WHERE account_id = ?;
		""";
}