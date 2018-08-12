package controller;

import java.sql.Timestamp;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSON;

import bean.Customer;
import bean.CustomerAndPrice;
import bean.Goods;
import bean.Indent;
import bean.Manager;
import service.adminservice.Adminservice;

@Controller
@RequestMapping(value = "admin")
public class Admincontroller {
	@Autowired
	Adminservice adminservice;

	@RequestMapping(value = "adminsignin", method = RequestMethod.GET)
	public String signin() {
		return "adminsignin";
	}

	@RequestMapping(value = "adminsignin", method = RequestMethod.POST)
	public String signin(Manager manager, String referrer, Model model, HttpSession session) {
		String result = adminservice.signin(manager);
		if (result.equals("ok")) {
			session.setAttribute("currentManager", manager);
			return "forward:/admin/tongji";
		}
		model.addAttribute("error", result);
		return "adminsignin";
	}

	@RequestMapping(value = "tongji")
	public String searchItems(Model model) {
		float PackageMainTypeWeight = (float) adminservice.MainTypeSaleWeight(1);
		float ClothMainTypeWeight = (float) adminservice.MainTypeSaleWeight(2);
		float FoodMainTypeWeight = (float) adminservice.MainTypeSaleWeight(3);
		float CultureMainTypeWeight = (float) adminservice.MainTypeSaleWeight(4);
		//////////////////////////////////////////////////////////////////////////
		float ManPackWeight = (float) adminservice.SecondaryTypeSaleWeight(1, 1);
		float GirlPackWeight = (float) adminservice.SecondaryTypeSaleWeight(1, 2);
		float PurseWeight = (float) adminservice.SecondaryTypeSaleWeight(1, 3);
		float CarrierWeight = (float) adminservice.SecondaryTypeSaleWeight(1, 4);
		/////////////////////////////////////////////////////////////////////////////
		float TshirtWeight = (float) adminservice.SecondaryTypeSaleWeight(2, 5);
		float PoloWeight = (float) adminservice.SecondaryTypeSaleWeight(2, 6);
		float SexySkirtWeight = (float) adminservice.SecondaryTypeSaleWeight(2, 7);
		float FashionPantWeight = (float) adminservice.SecondaryTypeSaleWeight(2, 8);
		///////////////////////////////////////////////////////////////////////////
		float SockWeight = (float) adminservice.SecondaryTypeSaleWeight(3, 9);
		float NutWeight = (float) adminservice.SecondaryTypeSaleWeight(3, 10);
		float MeatWeight = (float) adminservice.SecondaryTypeSaleWeight(3, 11);
		float DrinkWeight = (float) adminservice.SecondaryTypeSaleWeight(3, 12);
		//////////////////////////////////////////////////////////////////////
		float BeautifulWritingWeight = (float) adminservice.SecondaryTypeSaleWeight(4, 13);
		float OutDoorWeight = (float) adminservice.SecondaryTypeSaleWeight(4, 14);
		float MusicWeight = (float) adminservice.SecondaryTypeSaleWeight(4, 15);
		float FilmWeight = (float) adminservice.SecondaryTypeSaleWeight(4, 16);
		///////////////////////////////////////////////////////////////////////////
		CustomerAndPrice customerAndPrice = adminservice.getHighestCustomer();
		Goods highestgoods = adminservice.getHistoryHighestGood();

		Map<String, Object> mymap = new HashMap<>();
		mymap.put("PackageMainTypeWeight", PackageMainTypeWeight);
		mymap.put("ClothMainTypeWeight", ClothMainTypeWeight);
		mymap.put("FoodMainTypeWeight", FoodMainTypeWeight);
		mymap.put("CultureMainTypeWeight", CultureMainTypeWeight);
		mymap.put("ManPackWeight", ManPackWeight);
		mymap.put("GirlPackWeight", GirlPackWeight);
		mymap.put("PurseWeight", PurseWeight);
		mymap.put("CarrierWeight", CarrierWeight);
		mymap.put("TshirtWeight", TshirtWeight);
		mymap.put("PoloWeight", PoloWeight);
		mymap.put("SexySkirtWeight", SexySkirtWeight);
		mymap.put("FashionPantWeight", FashionPantWeight);
		mymap.put("SockWeight", SockWeight);
		mymap.put("NutWeight", NutWeight);
		mymap.put("MeatWeight", MeatWeight);
		mymap.put("DrinkWeight", DrinkWeight);
		mymap.put("BeautifulWritingWeight", BeautifulWritingWeight);
		mymap.put("OutDoorWeight", OutDoorWeight);
		mymap.put("MusicWeight", MusicWeight);
		mymap.put("FilmWeight", FilmWeight);
		mymap.put("highestGoods", highestgoods);
		mymap.put("customerAndPrice", customerAndPrice);

		model.addAttribute("mymap", mymap);
		return "next";
	}

	// 第二个页面
	@RequestMapping(value = "goodsCharge", method = RequestMethod.GET)
	public String goodsCharge(Model model, HttpSession session) {
		Manager manager = (Manager) session.getAttribute("currentManager");
		if (manager == null)
			return "adminsignin";
		List allGoodsList = adminservice.getAllGoods();
		model.addAttribute("allGoodList", allGoodsList);
		return "goodsCharge";
	}

	// 商品上下架
	@RequestMapping(value = "GoodsISsell", produces = "application/json;charset=utf-8")
	@ResponseBody
	public String GoodsISsell(String callback, HttpServletRequest request, HttpSession httpSession) throws Exception {
		int goodsID = Integer.valueOf(request.getParameter("goodsID"));// 不可以改的
		String goodsSpecify = request.getParameter("goodsSpecify");// 不可以改的
		int isSell = Integer.valueOf(request.getParameter("isSell"));
		String result = adminservice.ChangeisSellGood(goodsID, goodsSpecify, isSell);
		Map<String, String> map = new HashMap<>();
		map.put("result", result);
		String res = callback + "(" + JSON.toJSONString(map) + ")";
		return res;
	}

	@RequestMapping(value = "GoodsUpdate", produces = "application/json;charset=utf-8")
	@ResponseBody
	public String GoodsUpdate(String callback, HttpServletRequest request, HttpSession httpSession) throws Exception {
		int goodsID = Integer.valueOf(request.getParameter("goodsID"));// 不可以改的
		System.out.println(goodsID);
		String goodsName = request.getParameter("goodsName");
		System.out.println(goodsName);
		int goodsMainTypeID = Integer.valueOf(request.getParameter("goodsMainTypeID"));
		System.out.println(goodsMainTypeID);
		String goodsSpecify = request.getParameter("goodsSpecify");// 不可以改的
		System.out.println(goodsSpecify);
		String goodsBrief = request.getParameter("goodsBrief");
		System.out.println(goodsBrief);
		double goodsPrice = Double.valueOf(request.getParameter("goodsPrice"));
		System.out.println(goodsPrice);
		int goodsCount = Integer.valueOf(request.getParameter("goodsCount"));
		System.out.println(goodsCount);
		int sellCount = Integer.valueOf(request.getParameter("sellCount"));
		System.out.println(sellCount);
		Timestamp goodsDate = new Timestamp(System.currentTimeMillis());
		System.out.println(goodsDate);
		int isSell = Integer.valueOf(request.getParameter("isSell"));
		System.out.println(isSell);
		int goodsSecondaryTypeID = Integer.valueOf(request.getParameter("goodsSecondaryTypeID"));
		System.out.println(goodsSecondaryTypeID);
		Goods goods = new Goods(goodsID, goodsName, goodsMainTypeID, goodsSpecify, goodsBrief, goodsPrice, goodsCount,
				sellCount, goodsDate, isSell, goodsSecondaryTypeID, null, null);
		String result = adminservice.UpdateGood(goods);
		Map<String, String> map = new HashMap<>();
		map.put("result", result);
		String res = callback + "(" + JSON.toJSONString(map) + ")";
		return res;
	}

	@RequestMapping(value = "GoodsDelete", produces = "application/json;charset=utf-8")
	@ResponseBody
	public String GoodsDelete(String callback, HttpServletRequest request, HttpSession httpSession) throws Exception {
		int goodsID = Integer.valueOf(request.getParameter("goodsID"));
		Goods goods = new Goods();
		goods.setGoodsID(goodsID);
		String result = adminservice.DeleteGood(goods);
		Map<String, String> map = new HashMap<>();
		map.put("result", result);
		String res = callback + "(" + JSON.toJSONString(map) + ")";
		return res;
	}

	// 第三个页面
	@RequestMapping(value = "IndentCharge", method = RequestMethod.GET)
	public String IndentCharge(Model model, HttpSession session) {
		Manager manager = (Manager) session.getAttribute("currentManager");
		if (manager == null)
			return "adminsignin";
		List allIndentList = adminservice.getAllIndent();
		model.addAttribute("allIndentList", allIndentList);
		return "IndentCharge";
	}

	@RequestMapping(value = "IndentUpdate", produces = "application/json;charset=utf-8")
	@ResponseBody
	// 直接把修改后的Indent属性传给我，IndentID作为主码不能改
	public String IndentUpdate(String callback, HttpServletRequest request, HttpSession httpSession) throws Exception {
		int indentID = Integer.valueOf(request.getParameter("indentID"));
		int customerID = Integer.valueOf(request.getParameter("customerID"));
		double totalPrice = Double.valueOf(request.getParameter("totalPrice"));
		Timestamp indentTime = Timestamp.valueOf(request.getParameter("indentTime"));
		int addressID = Integer.valueOf(request.getParameter("addressID"));
		int expressCode = Integer.valueOf(request.getParameter("expressCode"));
		int indentState = Integer.valueOf(request.getParameter("indentState"));
		Indent indent = new Indent(indentID, customerID, totalPrice, indentTime, addressID, expressCode, indentState,
				null, null);
		String result = adminservice.UpdateIndent(indent);
		Map<String, String> map = new HashMap<>();
		map.put("result", result);
		String res = callback + "(" + JSON.toJSONString(map) + ")";
		return res;
	}

	@RequestMapping(value = "IndentInsert", produces = "application/json;charset=utf-8")
	@ResponseBody
	// 要传的自己看吧
	public String IndentInsert(String callback, HttpServletRequest request, HttpSession httpSession) throws Exception {
		int indentID = Integer.valueOf(request.getParameter("indentID"));
		int customerID = Integer.valueOf(request.getParameter("customerID"));
		double totalPrice = Double.valueOf(request.getParameter("totalPrice"));
		Timestamp indentTime = Timestamp.valueOf(request.getParameter("indentTime"));
		int addressID = Integer.valueOf(request.getParameter("addressID"));
		int expressCode = Integer.valueOf(request.getParameter("expressCode"));
		int indentState = Integer.valueOf(request.getParameter("indentState"));
		Indent indent = new Indent(indentID, customerID, totalPrice, indentTime, addressID, expressCode, indentState,
				null, null);
		String result = adminservice.InsertIndent(indent);
		Map<String, String> map = new HashMap<>();
		map.put("result", result);
		String res = callback + "(" + JSON.toJSONString(map) + ")";
		return res;
	}

	@RequestMapping(value = "IndentDelete", produces = "application/json;charset=utf-8")
	@ResponseBody
	// 订单删除只用传个indentID
	public String IndentDelete(String callback, HttpServletRequest request, HttpSession httpSession) throws Exception {
		int indentID = Integer.valueOf(request.getParameter("indentID"));
		Indent indent = new Indent();
		indent.setIndentID(indentID);
		String result = adminservice.DeleteIndent(indent);
		Map<String, String> map = new HashMap<>();
		map.put("result", result);
		String res = callback + "(" + JSON.toJSONString(map) + ")";
		return res;
	}

	@RequestMapping(value = "CustomerCharge", method = RequestMethod.GET)
	public String CustomerCharge(Model model, HttpSession session) {
		Manager manager = (Manager) session.getAttribute("currentManager");
		if (manager == null)
			return "adminsignin";
		List allCustomeList = adminservice.getAllCustomer();
		model.addAttribute("allCustomeList", allCustomeList);
		return "CustomerCharge";
	}

	@RequestMapping(value = "UpdateCustomerName", produces = "application/json;charset=utf-8")
	@ResponseBody
	public String CustomerUpdateName(String callback, HttpServletRequest request, HttpSession httpSession)
			throws Exception {
		int customerID = Integer.valueOf(request.getParameter("customerID"));
		String customerName = request.getParameter("customerName");
		String result = adminservice.UpdateCustomerName(customerID, customerName);
		Map<String, String> map = new HashMap<>();
		map.put("result", result);
		String res = callback + "(" + JSON.toJSONString(map) + ")";
		return res;
	}

	@RequestMapping(value = "UpdateCustomerPwd", produces = "application/json;charset=utf-8")
	@ResponseBody
	public String CustomerUpdatePWD(String callback, HttpServletRequest request, HttpSession httpSession)
			throws Exception {
		int customerID = Integer.valueOf(request.getParameter("customerID"));
		String customerPwd = request.getParameter("customerPwd");
		String result = adminservice.UpdateCustomerPassword(customerID, customerPwd);
		Map<String, String> map = new HashMap<>();
		map.put("result", result);
		String res = callback + "(" + JSON.toJSONString(map) + ")";
		return res;
	}

	@RequestMapping(value = "UpdateCustomerEmail", produces = "application/json;charset=utf-8")
	@ResponseBody
	public String CustomerUpdateEmail(String callback, HttpServletRequest request, HttpSession httpSession)
			throws Exception {
		int customerID = Integer.valueOf(request.getParameter("customerID"));
		String CustomerEmail = request.getParameter("CustomerEmail");
		String result = adminservice.UpdateCustomerEmail(customerID, CustomerEmail);
		Map<String, String> map = new HashMap<>();
		map.put("result", result);
		String res = callback + "(" + JSON.toJSONString(map) + ")";
		return res;
	}

	@RequestMapping(value = "UpdateCustomerPhone", produces = "application/json;charset=utf-8")
	@ResponseBody
	public String CustomerUpdatePhone(String callback, HttpServletRequest request, HttpSession httpSession)
			throws Exception {
		int customerID = Integer.valueOf(request.getParameter("customerID"));
		String customerPhone = request.getParameter("customerPhone");
		String result = adminservice.UpdateCustomerPhone(customerID, customerPhone);
		Map<String, String> map = new HashMap<>();
		map.put("result", result);
		String res = callback + "(" + JSON.toJSONString(map) + ")";
		return res;
	}

	@RequestMapping(value = "DeleteCustomer", produces = "application/json;charset=utf-8")
	@ResponseBody
	public String DeleteCustomer(String callback, HttpServletRequest request, HttpSession httpSession)
			throws Exception {
		int customerID = Integer.valueOf(request.getParameter("customerID"));
		String result = adminservice.DeleteCustomer(customerID);
		Map<String, String> map = new HashMap<>();
		map.put("result", result);
		String res = callback + "(" + JSON.toJSONString(map) + ")";
		return res;
	}

	@RequestMapping(value = "InsertCustomer", produces = "application/json;charset=utf-8")
	@ResponseBody
	public String InsertCustomer(String callback, HttpServletRequest request, HttpSession httpSession)
			throws Exception {
		int customerID = Integer.valueOf(request.getParameter("customerID"));
		String customerName = request.getParameter("customerName");
		String customerPwd = request.getParameter("customerPwd");
		String customerPhone = request.getParameter("customerPhone");
		String customerEmail = request.getParameter("customerEmail");
		Timestamp customerRegDate = Timestamp.valueOf(request.getParameter("customerRegDate"));
		Customer customer = new Customer(customerID, customerName, customerPwd, customerPhone, customerEmail,
				customerRegDate, null, null);
		String result = adminservice.InsertCustomer(customer);
		Map<String, String> map = new HashMap<>();
		map.put("result", result);
		String res = callback + "(" + JSON.toJSONString(map) + ")";
		return res;
	}

}
