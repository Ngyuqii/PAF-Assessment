package paf.assessment.repository;

import java.util.LinkedList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Repository;

import paf.assessment.model.Account;
import paf.assessment.model.Transfer;

import static paf.assessment.repository.SqlQueries.*;

@Repository
public class AccountsRepository {

    @Autowired
    private JdbcTemplate template;

    //Method to retrieve all account records from accounts table
    //SQL_SELECTALL = "SELECT * FROM accounts"
	public List<Account> getAllAccounts() {

		List<Account> accountsList = new LinkedList<>();

		//SqlRowSet object holds the result from a queryForRowSet()
        //Convert to to model object and add to List<object>
		SqlRowSet rs = template.queryForRowSet(SQL_SELECTALL);
		while (rs.next()) {
			accountsList.add(Account.createAccount(rs));
        }

		return accountsList;

	}

	//Method to retrieve account details by accountid
	//SQL_SELECTBYACCTID = "SELECT * FROM accounts WHERE account_id = ?"
	public Account getAccountById(String acctId) {

		SqlRowSet rs = template.queryForRowSet(SQL_SELECTBYACCTID, acctId);
		if (!rs.next()) {
			return null;
		}
		else {
			return Account.createAccount(rs);
		}
	}

    //Method to perform funds transfer
    // public Boolean transferFrom(Transfer tObj, String ) {
    //     Integer updated = jdbcTemplate.update(UPDATE_RSVP_SQL, rsvp.getName(), 
    //         rsvp.getPhone(),
    //         rsvp.getConfirmationDate(),
    //         rsvp.getComments(),
    //         email);

    //     return updated > 0;
    // }

    
}