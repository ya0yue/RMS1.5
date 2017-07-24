package com.restaurant;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.ResourceBundle;

import com.restaurant.model.Authority;
import com.restaurant.model.Belong;
import com.restaurant.model.Category;
import com.restaurant.model.Checkout;
import com.restaurant.model.Contain;
import com.restaurant.model.MenuItem;
import com.restaurant.model.MenuStat;
import com.restaurant.model.Order;
import com.restaurant.model.Own;
import com.restaurant.model.Staff;
import com.restaurant.model.Table;
import com.restaurant.view.OrderTabPageController;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;

public class ConnectionDB {
	private MainApp mainApp;

	public static ArrayList<Integer> ReturnBelongData_Rorderid = new ArrayList<Integer>();
	public static ArrayList<Integer> ReturnContainData_RItemid = new ArrayList<Integer>();
	public static ArrayList<Integer> ReturnMenuItemData_Rinfo = new ArrayList<Integer>();

	/**
	 * Connect to the mysql.
	 */
	public static ObservableList<Table> ConnectionTable(Integer tableID) {
		ObservableList<Table> ReturntableData = FXCollections.observableArrayList();

		try {
			Class.forName("com.mysql.jdbc.Driver");
		} catch (ClassNotFoundException e1) {
			e1.printStackTrace();
		}

		/*
		 * dynamic SQL code
		 */
		String sql = "SELECT * FROM mydb.table";
		if (tableID != 0) {
			sql = sql + " where tableid = " + tableID;
		}
		System.out.println(sql);

		try (Connection conn = DriverManager.getConnection(
				"jdbc:mysql://localhost:3306/mydb?" + "useSSL=false&characterEncoding=utf-8&user=root&password=1111");
				Statement stmt = conn.createStatement();
				ResultSet rs = stmt.executeQuery(sql);) {
			while (rs.next()) {
				String[] ShowFile = new String[4];
				ShowFile[0] = rs.getString(1); // tableid
				ShowFile[1] = rs.getString(2); // tablestatus
				ShowFile[2] = rs.getString(3); // tableinfo
				ShowFile[3] = rs.getString(4); // tableno

				Integer tableid = Integer.parseInt(ShowFile[0]);
				String tablestatus = (String) ShowFile[1];
				String tableinfo = (String) ShowFile[2];
				Integer tableno = Integer.parseInt(ShowFile[3]);
				// Create a custom response handler
				ReturntableData.add(new Table(tableid, tablestatus, tableinfo, tableno));
			}
			if (ReturntableData.isEmpty()) {
				final Alert alert = new Alert(AlertType.INFORMATION);
				alert.setTitle("message");
				alert.setContentText("Records can not be found");
				alert.showAndWait();
			}
			// Do something with the Connection
		} catch (SQLException ex) {
			// handle any errors
			System.out.println("SQLException: " + ex.getMessage());
			System.out.println("SQLState: " + ex.getSQLState());
			System.out.println("VendorError: " + ex.getErrorCode());
		}
		return ReturntableData;
	}

	/*
	 * / support two kind of seach 1.orderid || orderstatus 2.b_date && e_date
	 */
	public static ObservableList<Order> ConnectionOrder(ArrayList<Integer> Rorderid, int orderstatus,
			String Begin_orderdate, String End_orderdate) {
		ObservableList<Order> ReturnorderData = FXCollections.observableArrayList();

		try {
			Class.forName("com.mysql.jdbc.Driver");
		} catch (ClassNotFoundException e1) {
			e1.printStackTrace();
		}

		String sql = "SELECT * FROM mydb.order";
		if (!Rorderid.equals(0)) {
			sql = sql + " where orderstatus = " + orderstatus;
			int i = 0;
			while (i < Rorderid.size()) {
				if (i == 0) {
					sql = sql + " and (orderid = " + Rorderid.get(i);
				} else {
					sql = sql + " or orderid = " + Rorderid.get(i);
				}
				i++;
			}
			sql = sql + ")";
		} else {
			if (!Begin_orderdate.equals("") && !End_orderdate.equals("")) {
				sql = sql + " where order between '" + Begin_orderdate + "' and '" + End_orderdate + "')";
			} else {
				final Alert alert = new Alert(AlertType.INFORMATION);
				alert.setTitle("message");
				alert.setContentText("input error");
				alert.showAndWait();
			}

		}

		System.out.println(sql);

		try (Connection conn = DriverManager.getConnection(
				"jdbc:mysql://localhost:3306/mydb?" + "useSSL=false&characterEncoding=utf-8&user=root&password=1111");
				Statement stmt = conn.createStatement();
				ResultSet rs = stmt.executeQuery(sql);) {
			ReturnorderData.clear();
			while (rs.next()) {
				String[] ShowFile = new String[6];
				ShowFile[0] = rs.getString(1); // OrderID
				ShowFile[1] = rs.getString(2); // OrderStatus
				ShowFile[2] = rs.getString(3); // OrderDate
				ShowFile[3] = rs.getString(4); // OrderTime
				ShowFile[4] = rs.getString(5); // OrderInfo
				ShowFile[5] = rs.getString(6); // StaffID

				Integer orderID = Integer.parseInt(ShowFile[0]);
				Integer orderStatus = Integer.parseInt(ShowFile[1]);
				String orderDate = (String) ShowFile[2];
				String orderTime = (String) ShowFile[3];
				String orderInfo = (String) ShowFile[4];
				Integer staffID = Integer.parseInt(ShowFile[5]);

				// Create a custom response handler
				ReturnorderData.add(new Order(orderID, orderStatus, orderDate, orderTime, orderInfo, staffID));
			}
			/*
			 * if (ReturnorderData.isEmpty()) { final Alert alert = new
			 * Alert(AlertType.INFORMATION); alert.setTitle("message");
			 * alert.setContentText("Table is vacant"); alert.showAndWait(); }
			 */
			// Do something with the Connection
		} catch (SQLException ex) {
			// handle any errors
			System.out.println("SQLException: " + ex.getMessage());
			System.out.println("SQLState: " + ex.getSQLState());
			System.out.println("VendorError: " + ex.getErrorCode());
		}
		return ReturnorderData;
	}

	public static ObservableList<MenuItem> ConnectionMenuItem(ObservableList<Contain> ContainData) {
		ObservableList<MenuItem> ReturnmenuitemData = FXCollections.observableArrayList();

		try {
			Class.forName("com.mysql.jdbc.Driver");
		} catch (ClassNotFoundException e1) {
			e1.printStackTrace();
		}

		/*
		 * dynamic SQL code
		 */
		String sql = "SELECT * FROM mydb.menu_item";
		if (!ContainData.isEmpty()) {
			int i = 0;
			while (i < ContainData.size()) {
				if (i == 0) {
					sql = sql + " where (itemid = " + ContainData.get(i).getItemID();
				} else {
					sql = sql + " or itemid = " + ContainData.get(i).getItemID();
				}
				i++;
			}
			sql = sql + ")";
		}
		System.out.println(sql);

		try (Connection conn = DriverManager.getConnection(
				"jdbc:mysql://localhost:3306/mydb?" + "useSSL=false&characterEncoding=utf-8&user=root&password=1111");
				Statement stmt = conn.createStatement();
				ResultSet rs = stmt.executeQuery(sql);) {
			while (rs.next()) {
				String[] ShowFile = new String[7];
				ShowFile[0] = rs.getString(1); // ItemID
				ShowFile[1] = rs.getString(2); // ItemName
				ShowFile[2] = rs.getString(3); // ItemQuantity
				ShowFile[3] = rs.getString(4); // ItemPrice
				ShowFile[4] = rs.getString(5); // ItemCode
				ShowFile[5] = rs.getString(6); // ItemInfo
				ShowFile[6] = rs.getString(7); // CategoryID

				Integer ItemID = Integer.parseInt(ShowFile[0]);
				String ItemName = (String) ShowFile[1];
				Integer ItemQuantity = Integer.parseInt(ShowFile[2]);
				double ItemPrice = Double.valueOf(ShowFile[3]).doubleValue();
				String ItemCode = (String) ShowFile[4];
				String ItemInfo = (String) ShowFile[5];
				Integer CategoryID = Integer.parseInt(ShowFile[6]);
				// Create a custom response handler
				ReturnmenuitemData
						.add(new MenuItem(ItemID, ItemName, ItemQuantity, ItemPrice, ItemCode, ItemInfo, CategoryID));
			}

			if (ReturnmenuitemData.isEmpty()) {
				final Alert alert = new Alert(AlertType.INFORMATION);
				alert.setTitle("message");
				alert.setContentText("Records can not be found");
				alert.showAndWait();
			}
			// Do something with the Connection
		} catch (SQLException ex) {
			// handle any errors
			System.out.println("SQLException: " + ex.getMessage());
			System.out.println("SQLState: " + ex.getSQLState());
			System.out.println("VendorError: " + ex.getErrorCode());
		}
		return ReturnmenuitemData;
	}

	public static ArrayList<Integer> ConnectionBelong(int orderid, int tableno) {

		try {
			Class.forName("com.mysql.jdbc.Driver");
		} catch (ClassNotFoundException e1) {
			e1.printStackTrace();
		}

		/*
		 * dynamic SQL code
		 */
		String sql = "SELECT * FROM mydb.belong";
		if (orderid != 0) {
			sql = sql + " where orderid = " + orderid;
			if (tableno != 0) {
				sql = sql + " and tableno = " + tableno;
			}
		} else {
			if (tableno != 0) {
				sql = sql + " where tableno = " + tableno;
			}
		}
		System.out.println(sql);

		try (Connection conn = DriverManager.getConnection(
				"jdbc:mysql://localhost:3306/mydb?" + "useSSL=false&characterEncoding=utf-8&user=root&password=1111");
				Statement stmt = conn.createStatement();
				ResultSet rs = stmt.executeQuery(sql);) {
			ReturnBelongData_Rorderid.clear();
			while (rs.next()) {
				String[] ShowFile = new String[2];
				ShowFile[0] = rs.getString(1); // Orderid
				ShowFile[1] = rs.getString(2); // Tableno

				Integer Rorderid = Integer.parseInt(ShowFile[0]);
				// Integer Rtableno = Integer.parseInt(ShowFile[1]);
				// Create a custom response handler
				ReturnBelongData_Rorderid.add(Rorderid);
			}
			if (ReturnBelongData_Rorderid.isEmpty()) {
				final Alert alert = new Alert(AlertType.INFORMATION);
				alert.setTitle("message");
				alert.setContentText("no relative order information");
				alert.showAndWait();
			}
			// Do something with the Connection
		} catch (SQLException ex) {
			// handle any errors
			System.out.println("SQLException: " + ex.getMessage());
			System.out.println("SQLState: " + ex.getSQLState());
			System.out.println("VendorError: " + ex.getErrorCode());
		}
		return ReturnBelongData_Rorderid;
	}

	public static ObservableList<Contain> ConnectionContain(Integer Order_Rorderid) {
		ObservableList<Contain> ReturncontainData = FXCollections.observableArrayList();
		try {
			Class.forName("com.mysql.jdbc.Driver");
		} catch (ClassNotFoundException e1) {
			e1.printStackTrace();
		}

		/*
		 * dynamic SQL code
		 */
		String sql = "SELECT * FROM mydb.contain";
		if (Order_Rorderid != 0) {
			sql = sql + " where orderid = " + Order_Rorderid;
		}

		System.out.println(sql);

		try (Connection conn = DriverManager.getConnection(
				"jdbc:mysql://localhost:3306/mydb?" + "useSSL=false&characterEncoding=utf-8&user=root&password=1111");
				Statement stmt = conn.createStatement();
				ResultSet rs = stmt.executeQuery(sql);) {
			while (rs.next()) {
				String[] ShowFile = new String[3];
				ShowFile[0] = rs.getString(1); // OrderID
				ShowFile[1] = rs.getString(2); // ItemID
				ShowFile[2] = rs.getString(3); // Quantity

				Integer OrderID = Integer.parseInt(ShowFile[0]);
				Integer ItemID = Integer.parseInt(ShowFile[1]);
				Integer Quantity = Integer.parseInt(ShowFile[2]);
				// Create a custom response handler
				ReturncontainData.add(new Contain(OrderID, ItemID, Quantity));
			}
			if (ReturncontainData.isEmpty()) {
				final Alert alert = new Alert(AlertType.INFORMATION);
				alert.setTitle("message");
				alert.setContentText("Records can not be found");
				alert.showAndWait();
			}
			// Do something with the Connection
		} catch (SQLException ex) {
			// handle any errors
			System.out.println("SQLException: " + ex.getMessage());
			System.out.println("SQLState: " + ex.getSQLState());
			System.out.println("VendorError: " + ex.getErrorCode());
		}
		return ReturncontainData;
	}

	public static boolean InsertOrderTable(Integer SelectTableNo, String ItemCode, String ItemName,
			Integer ItemQuantity) {
		String tablestatus = "";
		int Add_New_signal = 0;// 0-add1-new
		int orderid = 0;
		int itemid = 0;
		int NewQuan = 0;
		Connection conn = null;

		try {
			Class.forName("com.mysql.jdbc.Driver");
		} catch (ClassNotFoundException e1) {
			e1.printStackTrace();
		}

		try {
			conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/mydb?"
					+ "useSSL=false&characterEncoding=utf-8&user=root&password=1111");
		} catch (SQLException e) {

			e.printStackTrace();
		}

		String sql = "SELECT * FROM mydb.table where tableid = " + SelectTableNo;
		System.out.println(sql);

		try (Statement stmt = conn.createStatement(); ResultSet rs = stmt.executeQuery(sql);) {
			while (rs.next()) {
				String[] ShowFile = new String[4];
				ShowFile[1] = rs.getString(2); // tablestatus
				tablestatus = (String) ShowFile[1];
			}
			// Do something with the Connection
		} catch (SQLException ex) {
			// handle any errors
			System.out.println("SQLException: " + ex.getMessage());
			System.out.println("SQLState: " + ex.getSQLState());
			System.out.println("VendorError: " + ex.getErrorCode());
			return false;
		}

		if (tablestatus.equals("occupied")) {// Place an order
			String sqlAdd = "select * from mydb.orderinfo where itemname = '" + ItemName
					+ "' and orderid = (select orderid from mydb.table_order where tableid = " + SelectTableNo
					+ " and OrderStatus = 0);";
			System.out.println("sqlAdd:" + sqlAdd);

			try (Statement stmt = conn.createStatement(); ResultSet rsCheck = stmt.executeQuery(sqlAdd);) {
				while (rsCheck.next()) {
					if (rsCheck.wasNull()) {// Add order kind
						Add_New_signal = 0;
						System.out.println("rsCheck:");
					} else {
						Add_New_signal = 1;
						orderid = Integer.parseInt(rsCheck.getString(1));
						itemid = Integer.parseInt(rsCheck.getString(2));
						NewQuan = Integer.parseInt(rsCheck.getString(9));
					}
				}
				// Do something with the Connection
			} catch (SQLException ex) {
				// handle any errors
				System.out.println("SQLException: " + ex.getMessage());
				System.out.println("SQLState: " + ex.getSQLState());
				System.out.println("VendorError: " + ex.getErrorCode());
				return false;
			}
			if (Add_New_signal == 0) {// Add order kind
				String sqlAdd1 = "insert into mydb.contain (OrderID,ItemID,Quantity) value "
						+ "((select orderid from mydb.table_order where tableid= " + SelectTableNo
						+ " and orderstatus = 0) ," + "(select itemid from mydb.menu_item where itemname= '" + ItemName
						+ "')," + ItemQuantity + ");";

				System.out.println("sqlAdd1:" + sqlAdd1);

				try (Statement stmtAdd = conn.createStatement();) {
					stmtAdd.executeUpdate(sqlAdd1);
					// Do something with the Connection
				} catch (SQLException ex) {
					// handle any errors
					System.out.println("SQLException: " + ex.getMessage());
					System.out.println("SQLState: " + ex.getSQLState());
					System.out.println("VendorError: " + ex.getErrorCode());
					return false;
				}
			} else {// Add order number
				NewQuan = NewQuan + ItemQuantity;
				String sqlAdd2 = "update mydb.contain set quantity = " + NewQuan + " where orderid = " + orderid
						+ " and itemid = " + itemid + " ;";

				System.out.println("sqlAdd2:" + sqlAdd2);
				try (Statement stmtAdd = conn.createStatement();) {
					stmtAdd.executeUpdate(sqlAdd2);
				} catch (SQLException ex) {
					// handle any errors
					System.out.println("SQLException: " + ex.getMessage());
					System.out.println("SQLState: " + ex.getSQLState());
					System.out.println("VendorError: " + ex.getErrorCode());
					return false;
				}
			}
		} else {// Open rice
			if (tablestatus.equals("vacant")) {
				String sqlOpen = "Update mydb.table set TableStatus = 'occupied' " + "where tableid = " + SelectTableNo;
				System.out.println("sqlOpen:" + sqlOpen);

				try (Statement stmt = conn.createStatement();) {
					stmt.executeUpdate(sqlOpen);
					// Do something with the Connection
				} catch (SQLException ex) {
					// handle any errors
					System.out.println("SQLException: " + ex.getMessage());
					System.out.println("SQLState: " + ex.getSQLState());
					System.out.println("VendorError: " + ex.getErrorCode());
					return false;
				}

				int maxOrderID = 0;
				String sql1 = "select max(orderid) from mydb.order";
				System.out.println(sql1);
				try (Statement stmt = conn.createStatement(); ResultSet rs = stmt.executeQuery(sql1);) {
					while (rs.next()) {
						maxOrderID = Integer.parseInt(rs.getString(1)); // CheckOutID
					}
				} catch (SQLException ex) {
					// handle any errors
					System.out.println("SQLException: " + ex.getMessage());
					System.out.println("SQLState: " + ex.getSQLState());
					System.out.println("VendorError: " + ex.getErrorCode());
					final Alert alert = new Alert(AlertType.INFORMATION);
					alert.setContentText("SQLException: " + ex.getMessage());
					alert.showAndWait();
					return false;
				}

				maxOrderID = maxOrderID + 1;

				String sqlAdd2 = "insert into mydb.`order` (OrderID,OrderStatus,OrderDate,OrderTime,OrderInfo,StaffID) value "
						+ "(" + maxOrderID + "," + 0 + ",CURDATE(),CURTIME(),''," + 1 + ");";

				System.out.println("sqlAdd1:" + sqlAdd2);

				try (Statement stmtAdd = conn.createStatement();) {
					stmtAdd.executeUpdate(sqlAdd2);
					// Do something with the Connection
				} catch (SQLException ex) {
					// handle any errors
					System.out.println("SQLException: " + ex.getMessage());
					System.out.println("SQLState: " + ex.getSQLState());
					System.out.println("VendorError: " + ex.getErrorCode());
					return false;
				}

				String sqlAdd1 = "insert into mydb.contain (OrderID,ItemID,Quantity) value " + "(" + maxOrderID
						+ ",(select itemid from mydb.menu_item where itemname= '" + ItemName + "')," + ItemQuantity
						+ ");";

				System.out.println("sqlAdd1:" + sqlAdd1);

				try (Statement stmtAdd = conn.createStatement();) {
					stmtAdd.executeUpdate(sqlAdd1);
					// Do something with the Connection
				} catch (SQLException ex) {
					// handle any errors
					System.out.println("SQLException: " + ex.getMessage());
					System.out.println("SQLState: " + ex.getSQLState());
					System.out.println("VendorError: " + ex.getErrorCode());
					return false;
				}

				String sqlAdd3 = "insert into mydb.belong (OrderID,TableNo) value " + "(" + maxOrderID + ","
						+ SelectTableNo + ");";

				System.out.println("sqlAdd1:" + sqlAdd3);

				try (Statement stmtAdd = conn.createStatement();) {
					stmtAdd.executeUpdate(sqlAdd3);
					// Do something with the Connection
				} catch (SQLException ex) {
					// handle any errors
					System.out.println("SQLException: " + ex.getMessage());
					System.out.println("SQLState: " + ex.getSQLState());
					System.out.println("VendorError: " + ex.getErrorCode());
					return false;
				}
			} else {
				final Alert alert = new Alert(AlertType.INFORMATION);
				alert.setTitle("message");
				alert.setContentText("tablestatus is abnomal");
				alert.showAndWait();
				return false;
			}
		}
		return true;
	}

	public static ObservableList<Checkout> TableCheckout(Integer tableid) {
		ObservableList<Checkout> ReturntablecheckoutData = FXCollections.observableArrayList();

		try {
			Class.forName("com.mysql.jdbc.Driver");
		} catch (ClassNotFoundException e1) {
			e1.printStackTrace();
		}
		Connection conn = null;
		try {
			conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/mydb?"
					+ "useSSL=false&characterEncoding=utf-8&user=root&password=1111");
		} catch (SQLException e) {

			e.printStackTrace();
		}
		String sqlAdd = "call Getcheckoutinfo(" + tableid + ")";
		System.out.println("sqlAdd:" + sqlAdd);

		try (Statement stmt = conn.createStatement(); ResultSet rs = stmt.executeQuery(sqlAdd);) {
			while (rs.next()) {
				String[] ShowFile = new String[12];
				ShowFile[0] = rs.getString(1); // CheckOutID
				ShowFile[1] = rs.getString(2); // Amount
				ShowFile[2] = rs.getString(3); // Payment
				ShowFile[3] = rs.getString(4); // PaymentType
				ShowFile[4] = rs.getString(5); // ChangeMoney
				ShowFile[5] = rs.getString(6); // Discount
				ShowFile[6] = rs.getString(7); // CheckoutDate
				ShowFile[7] = rs.getString(8); // CheckoutTime
				ShowFile[8] = rs.getString(9); // CheckoutInfo
				ShowFile[9] = rs.getString(10); // OrderID
				ShowFile[10] = rs.getString(11); // StaffID

				Integer CheckOutID = Integer.parseInt(ShowFile[0]);
				Double Amount = Double.valueOf(ShowFile[1]).doubleValue();
				Double Payment = Double.valueOf(ShowFile[2]).doubleValue();
				Integer PaymentType = Integer.parseInt(ShowFile[3]);
				Double ChangeMoney = Double.valueOf(ShowFile[4]).doubleValue();
				Double Discount = Double.valueOf(ShowFile[5]).doubleValue();
				String CheckoutDate = (String) ShowFile[6];
				String CheckoutTime = (String) ShowFile[7];
				String CheckoutInfo = (String) ShowFile[8];
				Integer OrderID = Integer.parseInt(ShowFile[9]);
				Integer StaffID = Integer.parseInt(ShowFile[10]);

				// Create a custom response handler
				ReturntablecheckoutData.add(new Checkout(CheckOutID, Amount, Payment, PaymentType, ChangeMoney,
						Discount, OrderID, StaffID, CheckoutDate, CheckoutTime, CheckoutInfo));
			}
			if (ReturntablecheckoutData.isEmpty()) {
				final Alert alert = new Alert(AlertType.INFORMATION);
				alert.setTitle("message");
				alert.setContentText("Records can not be found");
				alert.showAndWait();
			}
			// Do something with the Connection
		} catch (SQLException ex) {
			// handle any errors
			System.out.println("SQLException: " + ex.getMessage());
			System.out.println("SQLState: " + ex.getSQLState());
			System.out.println("VendorError: " + ex.getErrorCode());
		}
		return ReturntablecheckoutData;
	}

	public static ObservableList<MenuStat> ConnectionReportMenu(String Begin_date, String End_date) {
		ObservableList<MenuStat> ReturnReportMenuData = FXCollections.observableArrayList();

		try {
			Class.forName("com.mysql.jdbc.Driver");
		} catch (ClassNotFoundException e1) {
			e1.printStackTrace();
		}

		/*
		 * dynamic SQL code
		 */
		String sql = "SELECT itemid,itemname,sum(itemquantity),sum(ItemPrice),count(orderid)  FROM mydb.orderinfo";
		sql = sql + " where OrderDate between '" + Begin_date + "' and '" + End_date + "' group by Itemid";

		System.out.println(sql);

		try (Connection conn = DriverManager.getConnection(
				"jdbc:mysql://localhost:3306/mydb?" + "useSSL=false&characterEncoding=utf-8&user=root&password=1111");
				Statement stmt = conn.createStatement();
				ResultSet rs = stmt.executeQuery(sql);) {
			while (rs.next()) {
				String[] ShowFile = new String[5];
				ShowFile[0] = rs.getString(1); // itemid
				ShowFile[1] = rs.getString(2); // itemname
				ShowFile[2] = rs.getString(3); // Sumitemquantity
				ShowFile[3] = rs.getString(4); // SumItemPrice
				ShowFile[4] = rs.getString(5); // orderid

				Integer ItemID = Integer.parseInt(ShowFile[0]);
				String ItemName = (String) ShowFile[1];
				Integer Sumitemquantity = Integer.parseInt(ShowFile[2]);
				Double SumItemPrice = Double.parseDouble(ShowFile[3]);
				Integer orderid = Integer.parseInt(ShowFile[4]);

				// Create a custom response handler
				ReturnReportMenuData.add(new MenuStat(ItemID, ItemName, Sumitemquantity, SumItemPrice, orderid));
			}

			if (ReturnReportMenuData.isEmpty()) {
				final Alert alert = new Alert(AlertType.INFORMATION);
				alert.setTitle("message");
				alert.setContentText("Records can not be found");
				alert.showAndWait();
			}
			// Do something with the Connection
		} catch (SQLException ex) {
			// handle any errors
			System.out.println("SQLException: " + ex.getMessage());
			System.out.println("SQLState: " + ex.getSQLState());
			System.out.println("VendorError: " + ex.getErrorCode());
		}
		return ReturnReportMenuData;
	}

	public static boolean InsertCheckoutTable(Double Amount, Double Payment, Integer PaymentType, Double ChangeMoney,
			Double Discount, String CheckoutDate, String CheckoutTime, String CheckoutInfo, Integer OrderID,
			Integer StaffID) {
		Connection conn = null;

		try {
			Class.forName("com.mysql.jdbc.Driver");
		} catch (ClassNotFoundException e1) {
			e1.printStackTrace();
		}

		try {
			conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/mydb?"
					+ "useSSL=false&characterEncoding=utf-8&user=root&password=1111");
		} catch (SQLException e) {

			e.printStackTrace();
		}
		int maxCheckoutID = 0;
		String sql = "SELECT max(checkoutid) FROM mydb.checkout";
		System.out.println(sql);
		try (Statement stmt = conn.createStatement(); ResultSet rs = stmt.executeQuery(sql);) {
			while (rs.next()) {
				maxCheckoutID = Integer.parseInt(rs.getString(1)); // CheckOutID
			}
		} catch (SQLException ex) {
			// handle any errors
			System.out.println("SQLException: " + ex.getMessage());
			System.out.println("SQLState: " + ex.getSQLState());
			System.out.println("VendorError: " + ex.getErrorCode());
			final Alert alert = new Alert(AlertType.INFORMATION);
			alert.setContentText("SQLException: " + ex.getMessage());
			alert.showAndWait();
			return false;
		}

		maxCheckoutID = maxCheckoutID + 1;
		String sql2 = "insert into mydb.checkout (CheckOutID,Amount,Payment,PaymentType,ChangeMoney,Discount,CheckoutDate,"
				+ "CheckoutTime,CheckoutInfo,OrderID,StaffID) value (" + maxCheckoutID + "," + Amount + "," + Payment
				+ "," + PaymentType + "," + ChangeMoney + "," + Discount + ", '" + CheckoutDate + "' , '" + CheckoutTime
				+ "' , '" + CheckoutInfo + "' ," + OrderID + "," + StaffID + ")";
		System.out.println("sql:" + sql2);

		try (Statement stmtAdd = conn.createStatement();) {
			stmtAdd.executeUpdate(sql2);

			// Do something with the Connection
		} catch (SQLException ex) {
			// handle any errors
			System.out.println("SQLException: " + ex.getMessage());
			System.out.println("SQLState: " + ex.getSQLState());
			System.out.println("VendorError: " + ex.getErrorCode());
			final Alert alert = new Alert(AlertType.INFORMATION);
			alert.setContentText("SQLException: " + ex.getMessage());
			alert.showAndWait();
			return false;
		}

		String sql3 = "update mydb.order set OrderStatus = 1 where orderid = " + OrderID;
		System.out.println("sql:" + sql3);

		try (Statement stmtUpdate = conn.createStatement();) {
			stmtUpdate.executeUpdate(sql3);

			// Do something with the Connection
		} catch (SQLException ex) {
			// handle any errors
			System.out.println("SQLException: " + ex.getMessage());
			System.out.println("SQLState: " + ex.getSQLState());
			System.out.println("VendorError: " + ex.getErrorCode());
			final Alert alert = new Alert(AlertType.INFORMATION);
			alert.setContentText("SQLException: " + ex.getMessage());
			alert.showAndWait();
			return false;
		}

		String sql4 = "update mydb.table set TableStatus = 'vacant' where tableid = (SELECT tableno FROM mydb.belong where orderid = "
				+ OrderID + ")";
		System.out.println("sql:" + sql4);

		try (Statement stmtUpdate = conn.createStatement();) {
			stmtUpdate.executeUpdate(sql3);

			// Do something with the Connection
		} catch (SQLException ex) {
			// handle any errors
			System.out.println("SQLException: " + ex.getMessage());
			System.out.println("SQLState: " + ex.getSQLState());
			System.out.println("VendorError: " + ex.getErrorCode());
			final Alert alert = new Alert(AlertType.INFORMATION);
			alert.setContentText("SQLException: " + ex.getMessage());
			alert.showAndWait();
			return false;
		}
		return true;
	}

	public static ObservableList<Checkout> ConnectionCheckout() {
		ObservableList<Checkout> ReturncheckoutData = FXCollections.observableArrayList();
		try {
			Class.forName("com.mysql.jdbc.Driver");
		} catch (ClassNotFoundException e1) {
			e1.printStackTrace();
		}

		/*
		 * dynamic SQL code
		 */
		String sql = "SELECT * FROM mydb.checkout";

		System.out.println(sql);

		try (Connection conn = DriverManager.getConnection(
				"jdbc:mysql://localhost:3306/mydb?" + "useSSL=false&characterEncoding=utf-8&user=root&password=1111");
				Statement stmt = conn.createStatement();
				ResultSet rs = stmt.executeQuery(sql);) {
			while (rs.next()) {
				String[] ShowFile = new String[12];
				ShowFile[0] = rs.getString(1); // CheckOutID
				ShowFile[1] = rs.getString(2); // Amount
				ShowFile[2] = rs.getString(3); // Payment
				ShowFile[3] = rs.getString(4); // PaymentType
				ShowFile[4] = rs.getString(5); // ChangeMoney
				ShowFile[5] = rs.getString(6); // Discount
				ShowFile[6] = rs.getString(7); // CheckoutDate
				ShowFile[7] = rs.getString(8); // CheckoutTime
				ShowFile[8] = rs.getString(9); // CheckoutInfo
				ShowFile[9] = rs.getString(10); // OrderIDStaffID
				ShowFile[10] = rs.getString(11); // StaffID

				Integer CheckOutID = Integer.parseInt(ShowFile[0]);
				Double Amount = Double.valueOf(ShowFile[1]).doubleValue();
				Double Payment = Double.valueOf(ShowFile[2]).doubleValue();
				Integer PaymentType = Integer.parseInt(ShowFile[3]);
				Double ChangeMoney = Double.valueOf(ShowFile[4]).doubleValue();
				Double Discount = Double.valueOf(ShowFile[5]).doubleValue();
				String CheckoutDate = (String) ShowFile[6];
				String CheckoutTime = (String) ShowFile[7];
				String CheckoutInfo = (String) ShowFile[8];
				Integer OrderID = Integer.parseInt(ShowFile[9]);
				Integer StaffID = Integer.parseInt(ShowFile[10]);

				// Create a custom response handler
				ReturncheckoutData.add(new Checkout(CheckOutID, Amount, Payment, PaymentType, ChangeMoney, Discount,
						OrderID, StaffID, CheckoutDate, CheckoutTime, CheckoutInfo));
			}
			if (ReturncheckoutData.isEmpty()) {
				final Alert alert = new Alert(AlertType.INFORMATION);
				alert.setTitle("message");
				alert.setContentText("Records can not be found");
				alert.showAndWait();
			}
			// Do something with the Connection
		} catch (SQLException ex) {
			// handle any errors
			System.out.println("SQLException: " + ex.getMessage());
			System.out.println("SQLState: " + ex.getSQLState());
			System.out.println("VendorError: " + ex.getErrorCode());
		}
		return ReturncheckoutData;
	}

	public static ObservableList<Checkout> ReportCheckout(String Begin_date, String End_date) {
		ObservableList<Checkout> ReturncheckoutData = FXCollections.observableArrayList();
		try {
			Class.forName("com.mysql.jdbc.Driver");
		} catch (ClassNotFoundException e1) {
			e1.printStackTrace();
		}

		/*
		 * dynamic SQL code
		 */
		String sql = "SELECT  CheckOutID,Amount,Payment,PaymentType,ChangeMoney,Discount,CheckoutDate,CheckoutTime,CheckoutInfo,orderid,StaffID"
				+ " FROM mydb.checkout where CheckoutDate between '" + Begin_date + "' and '" + End_date + "'";

		System.out.println(sql);

		try (Connection conn = DriverManager.getConnection(
				"jdbc:mysql://localhost:3306/mydb?" + "useSSL=false&characterEncoding=utf-8&user=root&password=1111");
				Statement stmt = conn.createStatement();
				ResultSet rs = stmt.executeQuery(sql);) {
			while (rs.next()) {
				String[] ShowFile = new String[12];
				ShowFile[0] = rs.getString(1); // CheckOutID
				ShowFile[1] = rs.getString(2); // Amount
				ShowFile[2] = rs.getString(3); // Payment
				ShowFile[3] = rs.getString(4); // PaymentType
				ShowFile[4] = rs.getString(5); // ChangeMoney
				ShowFile[5] = rs.getString(6); // Discount
				ShowFile[6] = rs.getString(7); // CheckoutDate
				ShowFile[7] = rs.getString(8); // CheckoutTime
				ShowFile[8] = rs.getString(9); // CheckoutInfo
				ShowFile[9] = rs.getString(10); // OrderID
				ShowFile[10] = rs.getString(11); // StaffID

				Integer CheckOutID = Integer.parseInt(ShowFile[0]);
				Double Amount = Double.valueOf(ShowFile[1]).doubleValue();
				Double Payment = Double.valueOf(ShowFile[2]).doubleValue();
				Integer PaymentType = Integer.parseInt(ShowFile[3]);
				Double ChangeMoney = Double.valueOf(ShowFile[4]).doubleValue();
				Double Discount = Double.valueOf(ShowFile[5]).doubleValue();
				String CheckoutDate = (String) ShowFile[6];
				String CheckoutTime = (String) ShowFile[7];
				String CheckoutInfo = (String) ShowFile[8];
				Integer OrderID = Integer.parseInt(ShowFile[9]);
				Integer StaffID = Integer.parseInt(ShowFile[10]);

				// Create a custom response handler
				ReturncheckoutData.add(new Checkout(CheckOutID, Amount, Payment, PaymentType, ChangeMoney, Discount,
						OrderID, StaffID, CheckoutDate, CheckoutTime, CheckoutInfo));
			}
			if (ReturncheckoutData.isEmpty()) {
				final Alert alert = new Alert(AlertType.INFORMATION);
				alert.setTitle("message");
				alert.setContentText("Records can not be found");
				alert.showAndWait();
			}
			// Do something with the Connection
		} catch (SQLException ex) {
			// handle any errors
			System.out.println("SQLException: " + ex.getMessage());
			System.out.println("SQLState: " + ex.getSQLState());
			System.out.println("VendorError: " + ex.getErrorCode());
		}
		return ReturncheckoutData;
	}

	public static boolean ModifyMenuTable(Integer Old_ItemID, Integer ItemID, String ItemName, Integer ItemQuantity,
			Double ItemPrice, String ItemCode, String ItemInfo) {
		Connection conn = null;

		try {
			Class.forName("com.mysql.jdbc.Driver");
		} catch (ClassNotFoundException e1) {
			e1.printStackTrace();
		}

		try {
			conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/mydb?"
					+ "useSSL=false&characterEncoding=utf-8&user=root&password=1111");
		} catch (SQLException e) {

			e.printStackTrace();
		}
		String sql = "update mydb.menu_item set ItemID = " + ItemID + " , " + "ItemName = '" + ItemName + "' , "
				+ "ItemQuantity = " + ItemQuantity + " , " + "ItemPrice = " + ItemPrice + " , " + "ItemCode = '"
				+ ItemCode + "' , " + "ItemInfo = '" + ItemInfo + "'"
				+ " where ItemID = " + Old_ItemID;
		System.out.println("sql:" + sql);

		try (Statement stmtAdd = conn.createStatement();) {
			stmtAdd.executeUpdate(sql);
			// Do something with the Connection
		} catch (SQLException ex) {
			// handle any errors
			System.out.println("SQLException: " + ex.getMessage());
			System.out.println("SQLState: " + ex.getSQLState());
			System.out.println("VendorError: " + ex.getErrorCode());
			return false;
		}
		return true;
	}

	public static boolean ModifyCategory(Integer Old_CategoryID, Integer CategoryID, String CategoryName,
			String CategoryInfo) {
		Connection conn = null;

		try {
			Class.forName("com.mysql.jdbc.Driver");
		} catch (ClassNotFoundException e1) {
			e1.printStackTrace();
		}

		try {
			conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/mydb?"
					+ "useSSL=false&characterEncoding=utf-8&user=root&password=1111");
		} catch (SQLException e) {

			e.printStackTrace();
		}
		String sql = "update mydb.category set CategoryID = " + CategoryID + " , " + "CategoryName = '" + CategoryName
				+ "' , " + "CategoryInfo = '" + CategoryInfo + "'" + " where CategoryID = " + Old_CategoryID;
		System.out.println("sql:" + sql);

		try (Statement stmtAdd = conn.createStatement();) {
			stmtAdd.executeUpdate(sql);
			// Do something with the Connection
		} catch (SQLException ex) {
			// handle any errors
			System.out.println("SQLException: " + ex.getMessage());
			System.out.println("SQLState: " + ex.getSQLState());
			System.out.println("VendorError: " + ex.getErrorCode());
			return false;
		}
		return true;
	}

	public static boolean ModifyTable(Integer Old_TableID, Integer TableID, String TableStatus, String TableInfo,
			Integer TableNo) {
		Connection conn = null;

		try {
			Class.forName("com.mysql.jdbc.Driver");
		} catch (ClassNotFoundException e1) {
			e1.printStackTrace();
		}

		try {
			conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/mydb?"
					+ "useSSL=false&characterEncoding=utf-8&user=root&password=1111");
		} catch (SQLException e) {

			e.printStackTrace();
		}
		String sql = "update mydb.table set TableID = " + TableID + " , " + "TableStatus = '" + TableStatus + "' , "
				+ "TableInfo = '" + TableInfo + "' , " + "TableNo = " + TableNo + " where TableID = " + Old_TableID;
		System.out.println("sql:" + sql);

		try (Statement stmtAdd = conn.createStatement();) {
			stmtAdd.executeUpdate(sql);
			// Do something with the Connection
		} catch (SQLException ex) {
			// handle any errors
			System.out.println("SQLException: " + ex.getMessage());
			System.out.println("SQLState: " + ex.getSQLState());
			System.out.println("VendorError: " + ex.getErrorCode());
			return false;
		}
		return true;
	}

	public static boolean ModifyStaff(Integer Old_StaffID, Integer StaffID, String StaffName, Integer Age,
			Integer ContactNumber, String Role, Integer AccountNo, String Password, Integer ManagerID,
			Integer AuthorityID) {
		Connection conn = null;

		try {
			Class.forName("com.mysql.jdbc.Driver");
		} catch (ClassNotFoundException e1) {
			e1.printStackTrace();
		}

		try {
			conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/mydb?"
					+ "useSSL=false&characterEncoding=utf-8&user=root&password=1111");
		} catch (SQLException e) {

			e.printStackTrace();
		}
		String sql = "update mydb.Staff set StaffID = " + StaffID + " , " + " StaffName = '" + StaffName + "' , "
				+ " Age = " + Age + " , " + " ContactNumber = " + ContactNumber + " , " + " Role = '" + Role + "' , "
				+ " AccountNo = " + AccountNo + " , " + " Password = '" + Password + "' , " + " ManagerID = "
				+ ManagerID + " where StaffID = " + Old_StaffID;
		System.out.println("sql:" + sql);

		try (Statement stmtAdd = conn.createStatement();) {
			stmtAdd.executeUpdate(sql);
			// Do something with the Connection
		} catch (SQLException ex) {
			// handle any errors
			System.out.println("SQLException: " + ex.getMessage());
			System.out.println("SQLState: " + ex.getSQLState());
			System.out.println("VendorError: " + ex.getErrorCode());
			return false;
		}

		String sql2 = "update mydb.own set AuthorityID = " + AuthorityID + " where StaffID = " + Old_StaffID;
		System.out.println("sql:" + sql2);

		try (Statement stmtAdd = conn.createStatement();) {
			stmtAdd.executeUpdate(sql2);
			// Do something with the Connection
		} catch (SQLException ex) {
			// handle any errors
			System.out.println("SQLException: " + ex.getMessage());
			System.out.println("SQLState: " + ex.getSQLState());
			System.out.println("VendorError: " + ex.getErrorCode());
			return false;
		}
		return true;
	}

	public static boolean InsertMenuTable(Integer ItemID, String ItemName, Integer ItemQuantity, Double ItemPrice,
			String ItemCode, String ItemInfo, Integer CategoryID) {
		Connection conn = null;

		try {
			Class.forName("com.mysql.jdbc.Driver");
		} catch (ClassNotFoundException e1) {
			e1.printStackTrace();
		}

		try {
			conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/mydb?"
					+ "useSSL=false&characterEncoding=utf-8&user=root&password=1111");
		} catch (SQLException e) {

			e.printStackTrace();
		}

		String sql = "insert into mydb.menu_item (ItemID,ItemName,ItemQuantity,ItemPrice,ItemCode,ItemInfo,CategoryID) value ("
				+ ItemID + ", '" + ItemName + "' ," + ItemQuantity + "," + ItemPrice + "," + "'" + ItemCode + "' ,"
				+ "'" + ItemInfo + "' , " + CategoryID + ")";
		System.out.println("sql:" + sql);

		try (Statement stmtAdd = conn.createStatement();) {
			stmtAdd.executeUpdate(sql);
			// Do something with the Connection
		} catch (SQLException ex) {
			// handle any errors
			System.out.println("SQLException: " + ex.getMessage());
			System.out.println("SQLState: " + ex.getSQLState());
			System.out.println("VendorError: " + ex.getErrorCode());
			return false;
		}
		return true;
	}

	public static boolean InsertCategory(Integer CategoryID, String CategoryName, String CategoryInfo) {
		Connection conn = null;

		try {
			Class.forName("com.mysql.jdbc.Driver");
		} catch (ClassNotFoundException e1) {
			e1.printStackTrace();
		}

		try {
			conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/mydb?"
					+ "useSSL=false&characterEncoding=utf-8&user=root&password=1111");
		} catch (SQLException e) {

			e.printStackTrace();
		}

		String sql = "insert into mydb.Catefory (CategoryID,CategoryName,CategoryInfo) value (" + CategoryID + ", '"
				+ CategoryName + "' , '" + CategoryInfo + "' )";
		System.out.println("sql:" + sql);

		try (Statement stmtAdd = conn.createStatement();) {
			stmtAdd.executeUpdate(sql);
			// Do something with the Connection
		} catch (SQLException ex) {
			// handle any errors
			System.out.println("SQLException: " + ex.getMessage());
			System.out.println("SQLState: " + ex.getSQLState());
			System.out.println("VendorError: " + ex.getErrorCode());
			return false;
		}
		return true;
	}

	public static boolean InsertTable(Integer TableID, String TableStatus, String TableInfo, Integer TableNo) {
		Connection conn = null;

		try {
			Class.forName("com.mysql.jdbc.Driver");
		} catch (ClassNotFoundException e1) {
			e1.printStackTrace();
		}

		try {
			conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/mydb?"
					+ "useSSL=false&characterEncoding=utf-8&user=root&password=1111");
		} catch (SQLException e) {

			e.printStackTrace();
		}

		String sql = "insert into mydb.Table (TableID,TableStatus,TableInfo,TableNo) value (" + TableID + ", '"
				+ TableStatus + "' , '" + TableInfo + "' , " + TableNo + " )";
		System.out.println("sql:" + sql);

		try (Statement stmtAdd = conn.createStatement();) {
			stmtAdd.executeUpdate(sql);
			// Do something with the Connection
		} catch (SQLException ex) {
			// handle any errors
			System.out.println("SQLException: " + ex.getMessage());
			System.out.println("SQLState: " + ex.getSQLState());
			System.out.println("VendorError: " + ex.getErrorCode());
			return false;
		}
		return true;
	}

	public static boolean InsertStaff(Integer StaffID, String StaffName, Integer Age, Integer ContactNumber,
			String Role, Integer AccountNo, String Password, Integer ManagerID, Integer AuthorityID) {
		Connection conn = null;

		try {
			Class.forName("com.mysql.jdbc.Driver");
		} catch (ClassNotFoundException e1) {
			e1.printStackTrace();
		}

		try {
			conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/mydb?"
					+ "useSSL=false&characterEncoding=utf-8&user=root&password=1111");
		} catch (SQLException e) {

			e.printStackTrace();
		}

		String sql = "insert into mydb.staff (StaffID,StaffName,Age,ContactNumber,Role,AccountNo,Password,ManagerID) value ("
				+ StaffID + ", '" + StaffName + "' , " + Age + " , " + ContactNumber + " , '" + Role + "' , "
				+ AccountNo + " , '" + Password + "' , " + ManagerID + " )";
		System.out.println("sql:" + sql);

		try (Statement stmtAdd = conn.createStatement();) {
			stmtAdd.executeUpdate(sql);
			// Do something with the Connection
		} catch (SQLException ex) {
			// handle any errors
			System.out.println("SQLException: " + ex.getMessage());
			System.out.println("SQLState: " + ex.getSQLState());
			System.out.println("VendorError: " + ex.getErrorCode());
			return false;
		}
		String sql2 = "insert into mydb.own (StaffID,AuthorityID) value (" + StaffID + ", " + AuthorityID + " )";
		System.out.println("sql:" + sql2);

		try (Statement stmtAdd = conn.createStatement();) {
			stmtAdd.executeUpdate(sql2);
			// Do something with the Connection
		} catch (SQLException ex) {
			// handle any errors
			System.out.println("SQLException: " + ex.getMessage());
			System.out.println("SQLState: " + ex.getSQLState());
			System.out.println("VendorError: " + ex.getErrorCode());
			return false;
		}
		return true;
	}

	public static boolean DeleteStaff(Integer StaffID) {
		Connection conn = null;

		try {
			Class.forName("com.mysql.jdbc.Driver");
		} catch (ClassNotFoundException e1) {
			e1.printStackTrace();
		}

		try {
			conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/mydb?"
					+ "useSSL=false&characterEncoding=utf-8&user=root&password=1111");
		} catch (SQLException e) {

			e.printStackTrace();
		}

		String sql = "delete from mydb.own where StaffID = " + StaffID;
		System.out.println("sql:" + sql);

		try (Statement stmtAdd = conn.createStatement();) {
			stmtAdd.executeUpdate(sql);
			// Do something with the Connection
		} catch (SQLException ex) {
			// handle any errors
			System.out.println("SQLException: " + ex.getMessage());
			System.out.println("SQLState: " + ex.getSQLState());
			System.out.println("VendorError: " + ex.getErrorCode());
			return false;
		}

		String sql2 = "delete from mydb.staff where StaffID = " + StaffID;
		System.out.println("sql:" + sql2);

		try (Statement stmtAdd = conn.createStatement();) {
			stmtAdd.executeUpdate(sql2);
			// Do something with the Connection
		} catch (SQLException ex) {
			// handle any errors
			System.out.println("SQLException: " + ex.getMessage());
			System.out.println("SQLState: " + ex.getSQLState());
			System.out.println("VendorError: " + ex.getErrorCode());
			return false;
		}
		return true;
	}

	public static boolean DeleteMenu(Integer ItemID) {
		Connection conn = null;

		try {
			Class.forName("com.mysql.jdbc.Driver");
		} catch (ClassNotFoundException e1) {
			e1.printStackTrace();
		}

		try {
			conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/mydb?"
					+ "useSSL=false&characterEncoding=utf-8&user=root&password=1111");
		} catch (SQLException e) {

			e.printStackTrace();
		}

		String sql = "delete from mydb.menu_item where ItemID = " + ItemID;
		System.out.println("sql:" + sql);

		try (Statement stmtAdd = conn.createStatement();) {
			stmtAdd.executeUpdate(sql);
			// Do something with the Connection
		} catch (SQLException ex) {
			// handle any errors
			System.out.println("SQLException: " + ex.getMessage());
			System.out.println("SQLState: " + ex.getSQLState());
			System.out.println("VendorError: " + ex.getErrorCode());
			return false;
		}

		return true;
	}

	public static boolean DeleteCategory(Integer categoryid) {
		Connection conn = null;

		try {
			Class.forName("com.mysql.jdbc.Driver");
		} catch (ClassNotFoundException e1) {
			e1.printStackTrace();
		}

		try {
			conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/mydb?"
					+ "useSSL=false&characterEncoding=utf-8&user=root&password=1111");
		} catch (SQLException e) {

			e.printStackTrace();
		}

		String sql = "delete from mydb.category where categoryid = " + categoryid;
		System.out.println("sql:" + sql);

		try (Statement stmtAdd = conn.createStatement();) {
			stmtAdd.executeUpdate(sql);
			// Do something with the Connection
		} catch (SQLException ex) {
			// handle any errors
			System.out.println("SQLException: " + ex.getMessage());
			System.out.println("SQLState: " + ex.getSQLState());
			System.out.println("VendorError: " + ex.getErrorCode());
			return false;
		}

		return true;
	}

	public static boolean DeleteTable(Integer tableID) {
		Connection conn = null;

		try {
			Class.forName("com.mysql.jdbc.Driver");
		} catch (ClassNotFoundException e1) {
			e1.printStackTrace();
		}

		try {
			conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/mydb?"
					+ "useSSL=false&characterEncoding=utf-8&user=root&password=1111");
		} catch (SQLException e) {

			e.printStackTrace();
		}

		String sql = "delete from mydb.table where tableID = " + tableID;
		System.out.println("sql:" + sql);

		try (Statement stmtAdd = conn.createStatement();) {
			stmtAdd.executeUpdate(sql);
			// Do something with the Connection
		} catch (SQLException ex) {
			// handle any errors
			System.out.println("SQLException: " + ex.getMessage());
			System.out.println("SQLState: " + ex.getSQLState());
			System.out.println("VendorError: " + ex.getErrorCode());
			return false;
		}

		return true;
	}

	public static ObservableList<MenuItem> ConnectionMenu() {
		ObservableList<MenuItem> ReturnMenuData = FXCollections.observableArrayList();

		try {
			Class.forName("com.mysql.jdbc.Driver");
		} catch (ClassNotFoundException e1) {
			e1.printStackTrace();
		}

		/*
		 * dynamic SQL code
		 */
		String sql = "SELECT * FROM mydb.menu_item";
		System.out.println(sql);

		try (Connection conn = DriverManager.getConnection(
				"jdbc:mysql://localhost:3306/mydb?" + "useSSL=false&characterEncoding=utf-8&user=root&password=1111");
				Statement stmt = conn.createStatement();
				ResultSet rs = stmt.executeQuery(sql);) {
			while (rs.next()) {
				String[] ShowFile = new String[7];
				ShowFile[0] = rs.getString(1); // ItemID
				ShowFile[1] = rs.getString(2); // ItemName
				ShowFile[2] = rs.getString(3); // ItemQuantity
				ShowFile[3] = rs.getString(4); // ItemPrice
				ShowFile[4] = rs.getString(5); // ItemCode
				ShowFile[5] = rs.getString(6); // ItemInfo
				ShowFile[6] = rs.getString(7); // CategoryID

				Integer ItemID = Integer.parseInt(ShowFile[0]);
				String ItemName = (String) ShowFile[1];
				Integer ItemQuantity = Integer.parseInt(ShowFile[2]);
				Double ItemPrice = Double.valueOf(ShowFile[3]).doubleValue();
				String ItemCode = (String) ShowFile[4];
				String ItemInfo = (String) ShowFile[5];
				Integer CategoryID = Integer.parseInt(ShowFile[6]);
				// Create a custom response handler
				ReturnMenuData
						.add(new MenuItem(ItemID, ItemName, ItemQuantity, ItemPrice, ItemCode, ItemInfo, CategoryID));
			}
			if (ReturnMenuData.isEmpty()) {
				final Alert alert = new Alert(AlertType.INFORMATION);
				alert.setTitle("message");
				alert.setContentText("Records can not be found");
				alert.showAndWait();
			}
			// Do something with the Connection
		} catch (SQLException ex) {
			// handle any errors
			System.out.println("SQLException: " + ex.getMessage());
			System.out.println("SQLState: " + ex.getSQLState());
			System.out.println("VendorError: " + ex.getErrorCode());
		}
		return ReturnMenuData;
	}

	public static ObservableList<Category> ConnectionCategory() {
		ObservableList<Category> ReturnCategoryData = FXCollections.observableArrayList();

		try {
			Class.forName("com.mysql.jdbc.Driver");
		} catch (ClassNotFoundException e1) {
			e1.printStackTrace();
		}

		/*
		 * dynamic SQL code
		 */
		String sql = "SELECT * FROM mydb.category";
		System.out.println(sql);

		try (Connection conn = DriverManager.getConnection(
				"jdbc:mysql://localhost:3306/mydb?" + "useSSL=false&characterEncoding=utf-8&user=root&password=1111");
				Statement stmt = conn.createStatement();
				ResultSet rs = stmt.executeQuery(sql);) {
			while (rs.next()) {
				String[] ShowFile = new String[3];
				ShowFile[0] = rs.getString(1); // CategoryID
				ShowFile[1] = rs.getString(2); // CategoryName
				ShowFile[2] = rs.getString(3); // CategoryInfo

				Integer CategoryID = Integer.parseInt(ShowFile[0]);
				String CategoryName = (String) ShowFile[1];
				String CategoryInfo = (String) ShowFile[2];
				// Create a custom response handler
				ReturnCategoryData.add(new Category(CategoryID, CategoryName, CategoryInfo));
			}
			if (ReturnCategoryData.isEmpty()) {
				final Alert alert = new Alert(AlertType.INFORMATION);
				alert.setTitle("message");
				alert.setContentText("Records can not be found");
				alert.showAndWait();
			}
			// Do something with the Connection
		} catch (SQLException ex) {
			// handle any errors
			System.out.println("SQLException: " + ex.getMessage());
			System.out.println("SQLState: " + ex.getSQLState());
			System.out.println("VendorError: " + ex.getErrorCode());
		}
		return ReturnCategoryData;
	}

	public static ObservableList<Staff> ConnectionStaff(Integer staffid) {
		ObservableList<Staff> ReturnStaffData = FXCollections.observableArrayList();

		try {
			Class.forName("com.mysql.jdbc.Driver");
		} catch (ClassNotFoundException e1) {
			e1.printStackTrace();
		}

		/*
		 * dynamic SQL code
		 */
		String sql = "SELECT * FROM mydb.staff_authority";
		if (!staffid.equals(0)) {
			sql = sql + " where staffid = " + staffid;
		}
		System.out.println(sql);

		try (Connection conn = DriverManager.getConnection(
				"jdbc:mysql://localhost:3306/mydb?" + "useSSL=false&characterEncoding=utf-8&user=root&password=1111");
				Statement stmt = conn.createStatement();
				ResultSet rs = stmt.executeQuery(sql);) {
			while (rs.next()) {
				String[] ShowFile = new String[9];
				ShowFile[0] = rs.getString(1); // StaffID
				ShowFile[1] = rs.getString(2); // StaffName
				ShowFile[2] = rs.getString(3); // Age
				ShowFile[3] = rs.getString(4); // ContactNumber
				ShowFile[4] = rs.getString(5); // Role
				ShowFile[5] = rs.getString(6); // AccountNo
				ShowFile[6] = rs.getString(7); // Password
				ShowFile[7] = rs.getString(8); // ManagerID
				ShowFile[8] = rs.getString(9); // AuthorityID

				Integer StaffID = Integer.parseInt(ShowFile[0]);
				String StaffName = (String) ShowFile[1];
				Integer Age = Integer.parseInt(ShowFile[2]);
				Integer ContactNumber = Integer.parseInt(ShowFile[3]);
				String Role = (String) ShowFile[4];
				Integer AccountNo = Integer.parseInt(ShowFile[5]);
				String Password = (String) ShowFile[6];
				Integer ManagerID = Integer.parseInt(ShowFile[7]);
				Integer AuthorityID = Integer.parseInt(ShowFile[8]);
				// Create a custom response handler
				ReturnStaffData.add(new Staff(StaffID, Age, ContactNumber, AccountNo, ManagerID, StaffName, Role,
						Password, AuthorityID));
			}
			if (ReturnStaffData.isEmpty()) {
				final Alert alert = new Alert(AlertType.INFORMATION);
				alert.setTitle("message");
				alert.setContentText("Records can not be found");
				alert.showAndWait();
			}
			// Do something with the Connection
		} catch (SQLException ex) {
			// handle any errors
			System.out.println("SQLException: " + ex.getMessage());
			System.out.println("SQLState: " + ex.getSQLState());
			System.out.println("VendorError: " + ex.getErrorCode());
		}
		return ReturnStaffData;
	}

	public static List<Integer> getAuthID(Integer staffId) {
		List<Integer> userAuths = new LinkedList<Integer>();

		try {
			Class.forName("com.mysql.jdbc.Driver");
		} catch (ClassNotFoundException e1) {
			e1.printStackTrace();
		}

		/*
		 * dynamic SQL code
		 */
		String sql = "SELECT * FROM mydb.own where StaffID=" + staffId;
		System.out.println(sql);

		try (Connection conn = DriverManager.getConnection(
				"jdbc:mysql://localhost:3306/mydb?" + "useSSL=false&characterEncoding=utf-8&user=root&password=1111");
				Statement stmt = conn.createStatement();
				ResultSet rs = stmt.executeQuery(sql);) {
			while (rs.next()) {
				String[] ShowFile = new String[8];
				ShowFile[0] = rs.getString(1); // StaffID
				ShowFile[1] = rs.getString(2); // AuthorityID

				Integer AuthorityID = Integer.parseInt(ShowFile[1]);
				// Create a custom response handler
				userAuths.add(AuthorityID);
			}
			if (userAuths.isEmpty()) {
				final Alert alert = new Alert(AlertType.INFORMATION);
				alert.setTitle("message");
				alert.setContentText("Records can not be found");
				alert.showAndWait();
			}
			// Do something with the Connection
		} catch (SQLException ex) {
			// handle any errors
			System.out.println("SQLException: " + ex.getMessage());
			System.out.println("SQLState: " + ex.getSQLState());
			System.out.println("VendorError: " + ex.getErrorCode());
		}
		return userAuths;
	}

}
