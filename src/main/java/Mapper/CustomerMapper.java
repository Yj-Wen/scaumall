package Mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import bean.Customer;
import bean.CustomerAndPrice;


public interface CustomerMapper {

	@Transactional(propagation = Propagation.REQUIRED, isolation = Isolation.READ_COMMITTED)
	Customer findBycustomerID(int customerID);
	
	@Transactional(propagation = Propagation.REQUIRED, isolation = Isolation.READ_COMMITTED)
	Customer findBycustomerNameAndcustomerPwd(@Param("customerName") String customerName,@Param("customerPwd") String customerPwd);
	
	@Transactional(propagation = Propagation.REQUIRED, isolation = Isolation.READ_COMMITTED)
	List<CustomerAndPrice> getHighestCustomer();

	@Transactional(propagation = Propagation.REQUIRED, isolation = Isolation.READ_COMMITTED)
	int IsCustomerNameExist(String customerName);//找到返回一个用户的ID,找不到抛出异常

	@Transactional(propagation = Propagation.REQUIRED, isolation = Isolation.SERIALIZABLE)
	int insert(Customer customer);

	@Transactional(propagation = Propagation.REQUIRED, isolation = Isolation.REPEATABLE_READ)
	int update(Customer customer);
	
	@Transactional(propagation = Propagation.REQUIRED, isolation = Isolation.REPEATABLE_READ)
	int updatecustomerNameBycustomerID(@Param("customerID") int customerID,@Param("customerName") String customerName);
	
	@Transactional(propagation = Propagation.REQUIRED, isolation = Isolation.REPEATABLE_READ)
	int updatecustomerPasswordBycustomerID(@Param("customerID") int customerID,@Param("customerPassword") String customerPassword);

	@Transactional(propagation = Propagation.REQUIRED, isolation = Isolation.REPEATABLE_READ)
	int delete(Customer customer);
}
