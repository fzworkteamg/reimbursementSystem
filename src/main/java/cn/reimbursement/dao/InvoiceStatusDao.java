package cn.reimbursement.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

@Repository
public interface InvoiceStatusDao {
	List<String> selectInvoiceStatusName();
}
