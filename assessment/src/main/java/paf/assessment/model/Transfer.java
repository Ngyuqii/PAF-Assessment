package paf.assessment.model;

import org.springframework.util.MultiValueMap;

public class Transfer {

    private String transactionId;
    private String fromAcct; //this references to account_id of source account
    private String toAcct; //this references to account_id of destination account
    private Double transferAmt;
    private String comment;
    
    //Getters
    public String getTransactionId() {
        return transactionId;
    }
    public String getFromAcct() {
        return fromAcct;
    }
    public String getToAcct() {
        return toAcct;
    }
    public Double getTransferAmt() {
        return transferAmt;
    }
    public String getComment() {
        return comment;
    }
    
    //Setters
    public void setTransactionId(String transactionId) {
        this.transactionId = transactionId;
    }
    public void setFromAcct(String fromAcct) {
        this.fromAcct = fromAcct;
    }
    public void setToAcct(String toAcct) {
        this.toAcct = toAcct;
    }
    public void setTransferAmt(Double transferAmt) {
        this.transferAmt = transferAmt;
    }
    public void setComment(String comment) {
        this.comment = comment;
    }

    //Create Transfer object from form data retrieved via multivaluemap
    //{name1=[ckTV56axff], name2=[ckTV56axff], amount=[30], comment=[ccc]}
    public static Transfer createTransferObj(MultiValueMap<String, String> form) {
		Transfer t = new Transfer();
		t.setFromAcct(form.getFirst("name1"));
		t.setToAcct(form.getFirst("name2"));
        t.setTransferAmt(Double.parseDouble(form.getFirst("amount")));
		t.setComment(form.getFirst("comment"));
		return t;
	}
   
}