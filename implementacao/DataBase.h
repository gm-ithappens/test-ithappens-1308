#ifndef DATABASE_H
#define DATABASE_H

#include <sqlite3.h>
#include <iostream>
#include <vector>
#include "OrderModule.h"
#include <QList>

using namespace std;

class DataBase
{
public:
	DataBase  ();
	~ DataBase(){};

private:
	// Here will be the instance stored.
	static DataBase * instance;

	sqlite3 * db_instance;
	string create_branch_company_sql = "CREATE TABLE BRANCHS_COMPANY("
	      "ID INTEGER PRIMARY KEY     AUTOINCREMENT, "
	      "NAME           TEXT    NOT NULL"
	      ");";

	string select_branch_company_sql = "SELECT NAME FROM 'BRANCHS_COMPANY';";
	const char * select_branch_company_filtered_sql = "SELECT NAME FROM 'BRANCHS_COMPANY' WHERE NAME == '%s';";

	const char * populate_branch_company_sql = "INSERT INTO BRANCHS_COMPANY ('NAME')  VALUES ('%s');";

	const char * create_store_branch_company_sql = "CREATE TABLE %s_STORE_BRANCHS_COMPANY("
	      "ID INTEGER PRIMARY KEY     AUTOINCREMENT, "
	      "DESCRIPTION            TEXT    NOT NULL,"
	      "BARCODE                TEXT    NOT NULL,"
	      "SEQUENTIAL             TEXT    NOT NULL,"
	      "UNIT_VALUE              INT    NOT NULL,"
	      "COUNT_AVAILABLE         INT    NOT NULL"
	      ");";

	const char * create_orders_branch_company_sql = "CREATE TABLE %s_ORDERS_BRANCHS_COMPANY("
	      "ID INTEGER PRIMARY KEY     AUTOINCREMENT, "
	      "HASHORDER              TEXT    NOT NULL,"
	      "ORDERCODE               INT    NOT NULL,"
	      "PAYMENT_MODE            INT    NOT NULL,"
	      "TOTAL_ITENS             INT    NOT NULL,"
	      "TOTAL_VALUE             INT    NOT NULL,"
	      "OPERATOR               TEXT    NOT NULL,"
	      "CLIENT                 TEXT    NOT NULL,"
	      "CLIENTOBS              TEXT    NOT NULL"
	      ");";

	const char * create_orders_products_branch_company_sql = "CREATE TABLE %s_ORDERS_PRODUCTS_BRANCHS_COMPANY("
	      "ID INTEGER PRIMARY KEY     AUTOINCREMENT, "
	      "HASHORDER              TEXT    NOT NULL,"
	      "BARCODE                TEXT    NOT NULL,"
	      "DESCRIPTION            TEXT    NOT NULL,"
	      "SEQUENTIAL              INT    NOT NULL,"
	      "PROCESSED_COUNT         INT    NOT NULL,"
	      "CANCELED_COUNT          INT    NOT NULL,"
	      "TOTAL_VALUE             INT    NOT NULL,"
	      "ORDERCODE               INT    NOT NULL,"
	      "PAYMENT_MODE            INT    NOT NULL"
	      ");";


	const char * select_product_store_branch_company_sql          = "SELECT * FROM '%s_STORE_BRANCHS_COMPANY' WHERE %s == '%s';";
	const char * select_product_store_branch_company_seq_sql      = "SELECT * FROM '%s_STORE_BRANCHS_COMPANY' WHERE %s == %d;";
	const char * select_product_store_branch_company_hsuper_sql   = "SELECT * FROM '%s_STORE_BRANCHS_COMPANY' WHERE COUNT_AVAILABLE > %d;";
	const char * select_product_store_branch_company_lsuper_sql   = "SELECT * FROM '%s_STORE_BRANCHS_COMPANY' WHERE COUNT_AVAILABLE < %d;";
	const char * select_order_branch_company_filter_seq_sql       = "SELECT * FROM '%s_ORDERS_PRODUCTS_BRANCHS_COMPANY' WHERE SEQUENTIAL == %d;" ;
	const char * select_order_branch_company_filter_hash_seq_sql  = "SELECT * FROM '%s_ORDERS_PRODUCTS_BRANCHS_COMPANY' WHERE HASHORDER  == '%s';" ;

	const char * select_order_branch_company_filter_payment_sql   = "SELECT HASHORDER,PAYMENT_MODE FROM '%s_ORDERS_BRANCHS_COMPANY';";

	const char * insert_order_store_branch_company_sql           = "INSERT   INTO '%s_ORDERS_BRANCHS_COMPANY' "
									"('HASHORDER','ORDERCODE','PAYMENT_MODE', "
									"'TOTAL_ITENS','TOTAL_VALUE','OPERATOR','CLIENT','CLIENTOBS') "
									"VALUES ('%s', %d, %d, %d, %d, '%s','%s','%s');";

	const char * insert_order_product_store_branch_company_sql   = "INSERT   INTO '%s_ORDERS_PRODUCTS_BRANCHS_COMPANY' "
									"(HASHORDER,BARCODE,DESCRIPTION,SEQUENTIAL, PROCESSED_COUNT,CANCELED_COUNT,TOTAL_VALUE,ORDERCODE,PAYMENT_MODE)"
								       	"VALUES ('%s', '%s', '%s', %d, %d, %d, %d, %d, %d);";

	const char * update_product_store_branch_company_sql         = "UPDATE  '%s_STORE_BRANCHS_COMPANY' SET COUNT_AVAILABLE = %d WHERE BARCODE == '%s';";
	const char * insert_product_store_branch_company_sql         = "INSERT INTO '%s_STORE_BRANCHS_COMPANY' "
									"('DESCRIPTION', 'BARCODE', 'SEQUENTIAL', 'UNIT_VALUE', 'COUNT_AVAILABLE' ) "
									"VALUES ('%s', '%s', %d, %d, %d);";

	const char * select_resumed_orders_superlative             = "SELECT HASHORDER, COUNT(*) FROM '%s_ORDERS_PRODUCTS_BRANCHS_COMPANY' GROUP BY HASHORDER HAVING COUNT(*) > %d;";
	const char * select_orders_hash_of_branch                  = "SELECT HASHORDER FROM '%s_ORDERS_PRODUCTS_BRANCHS_COMPANY';";
	const char * select_one_order_of_branch_sql                = "SELECT * FROM '%s_ORDERS_BRANCHS_COMPANY' WHERE HASHORDER == '%s';";

public:
	/* Static access method. */
        static DataBase* getInstance();
	void createBranchCompanyTable(string branch_name);
	void createBranchsCompanyTable();
	QList<QString> *  getListBranchCompany();
	Product * searchProductOnBranch(string branch, string search_mode, string product);
	void registerOrderOnBranch(string branch, string hashorder, int code, int payment_mode, 
					    int total_value, int total_itens, string operator_user, 
					    string client, string obs_client);
	void registerOrderProductsOnBranch(string branch, string hashorder, string barcode, string description, 
                                            int sequential, int count_requested, int count_canceled, 
					    int total_value, int order_type, int payment_mode);
	void updateProductOnBranch(string branch, string barcode, int count_available);
	void insertProductOnBranch(string branch, string barcode, string description,
                                     int count_available, int unit_value, int sequential);
	QHash<QString, Product *> searchSuperProductOnBranch(string branch, int type, int count);
	QHash<QString, ProductOfOrder *> searchListOrdersProductOnBranch(string branch, int sequential);
	QHash<QString, ProductOfOrder *> searchListOrderAndPaymentOnBranch(string branch);
	QList<QString> * searchResumedOrdersSuperlative(string branch, int count);
	QList<QString> * searchOrdersHashofBranch(string branch);
	QHash<QString, ProductOfOrder *> searchListOrdersProductHashOnBranch(string branch, string hashsession);
	Order * searchOneOrderHashofBranch(string branch, string hashsession);
	Product * searchProductOnBranchSeq(string branch, string search_mode, int product);
	QString * DataBase::searchBranchCompanyTable(string branch);

};
#endif
