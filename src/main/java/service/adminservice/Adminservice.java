package service.adminservice;


import java.util.List;

import bean.Customer;
import bean.CustomerAndPrice;
import bean.Goods;
import bean.GoodsSecondaryType;
import bean.Indent;
import bean.Manager;

public interface Adminservice {
	//管理员账户密码管理
	public String signin(Manager manager);
    public String changeManagerName(int managerID,String managerName);
    public String changeManagerPassword(int managerID,String newPassword);
    public String createAdmin(Manager manager);//创建新的管理员
    //商品管理
    public String InsertGood(Goods goods);
    public String DeleteGood(Goods goods);
    public String UpdateGood(Goods goods);
    public String ChangeisSellGood(int goodsID,String goodsSpecify,int yesno);//商品上下架
    public List<Goods> getAllGoods();//获取所有商品
    public List<Goods> getgoodsByMaintypeIDAndSecondTypeID(int mid,int sid);
    
    //统计
    public double MainTypeSaleWeight(int goodsMainTypeID);//主分类销售比重
    public double SecondaryTypeSaleWeight(int goodsMainTypeID,int goodsSecondaryTypeID);//次分类占主分类销售比重
    public Goods getHistoryHighestGood();
    public CustomerAndPrice getHighestCustomer();//获取消费最高的用户名字和消费总额
    public double getHighestSalePerDay();//单日营业额最高
    public Integer getHighestSaleCountPerDay();//单日成交数最高
    public GoodsSecondaryType getMostPopularSecondaryType();//最受欢迎种类
    
//    //订单管理
    public List<Indent> getAllIndent();
    public String InsertIndent(Indent indent);
    public String DeleteIndent(Indent indent);
    public String UpdateIndent(Indent indent);

    
//    //用户管理
    public List<Customer> getAllCustomer();
    public String UpdateCustomerName(int customerID,String customerName);
    public String UpdateCustomerPassword(int customerID,String customerPwd);
    public String UpdateCustomerPhone(int customerID,String customerPhone);
    public String UpdateCustomerEmail(int customerID,String CustomerEmail);
    public String InsertCustomer(Customer customer);
    public String DeleteCustomer(int customerID);
}
