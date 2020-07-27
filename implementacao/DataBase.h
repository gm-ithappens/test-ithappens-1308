#ifndef DATABASE_H
#define DATABASE_H

#include <sqlite3.h>
#include <iostream>
#include <vector>
#include "OrderModule.h"

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

	// Used only for tests purposes
	string populate_branch_company1_sql = "INSERT INTO BRANCHS_COMPANY ('NAME')  VALUES ('Vinhais');";
	string populate_branch_company2_sql = "INSERT INTO BRANCHS_COMPANY ('NAME')  VALUES ('Centro');";

	// Querys to create Store in each branch company  (hardcode)
	// TODO: Implent screen and tools for registre new branchs company
	string create_store_branch_company1_sql = "CREATE TABLE Vinhais_STORE_BRANCHS_COMPANY("
	      "ID INTEGER PRIMARY KEY     AUTOINCREMENT, "
	      "DESCRIPTION            TEXT    NOT NULL,"
	      "BARCODE                TEXT    NOT NULL,"
	      "SEQUENTIAL             TEXT    NOT NULL,"
	      "UNIT_VALUE              INT    NOT NULL,"
	      "COUNT_AVAILABLE         INT    NOT NULL"
	      ");";

	string create_store_branch_company2_sql = "CREATE TABLE Centro_STORE_BRANCHS_COMPANY("
	      "ID INTEGER PRIMARY KEY     AUTOINCREMENT, "
	      "DESCRIPTION            TEXT    NOT NULL,"
	      "BARCODE                TEXT    NOT NULL,"
	      "SEQUENTIAL              INT    NOT NULL,"
	      "UNIT_VALUE              INT    NOT NULL,"
	      "COUNT_AVAILABLE         INT    NOT NULL"
	      ");";

	// Querys to create Orders in each branch company (hardcode)
	// TODO: Implent screen and tools for registre new order at branchs company
	string create_orders_branch_company1_sql = "CREATE TABLE Vinhais_ORDERS_BRANCHS_COMPANY("
	      "ID INTEGER PRIMARY KEY     AUTOINCREMENT, "
	      "HASHORDER              TEXT    NOT NULL,"
	      "ORDERCODE               INT    NOT NULL,"
	      "PAYMENT_MODE            INT    NOT NULL"
	      ");";

	string create_orders_branch_company2_sql = "CREATE TABLE Centro_ORDERS_BRANCHS_COMPANY("
	      "ID INTEGER PRIMARY KEY     AUTOINCREMENT, "
	      "HASHORDER               TEXT    NOT NULL,"
	      "ORDERCODE               INT    NOT NULL,"
	      "PAYMENT_MODE            INT    NOT NULL"
	      ");";

	// Querys to create Orders in each branch company (hardcode)
	// TODO: Implent screen and tools for registre new products list used in orders at branchs company
	string create_orders_products_branch_company1_sql = "CREATE TABLE Vinhais_ORDERS_PRODUCTS_BRANCHS_COMPANY("
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

	string create_orders_products_branch_company2_sql = "CREATE TABLE Centro_ORDERS_PRODUCTS_BRANCHS_COMPANY("
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
	const char * select_product_store_branch_company_hsuper_sql   = "SELECT * FROM '%s_STORE_BRANCHS_COMPANY' WHERE COUNT_AVAILABLE > %d;";
	const char * select_product_store_branch_company_lsuper_sql   = "SELECT * FROM '%s_STORE_BRANCHS_COMPANY' WHERE COUNT_AVAILABLE < %d;";
	const char * select_order_branch_company_filter_seq_sql       = "SELECT * FROM '%s_ORDERS_PRODUCTS_BRANCHS_COMPANY' WHERE SEQUENTIAL == %d;" ;

	const char * insert_order_store_branch_company_sql           = "INSERT   INTO '%s_ORDERS_BRANCHS_COMPANY' ('HASHORDER','ORDERCODE','PAYMENT_MODE') "
									"VALUES ('%s', %d, %d);";

	const char * insert_order_product_store_branch_company_sql   = "INSERT   INTO '%s_ORDERS_PRODUCTS_BRANCHS_COMPANY' "
									"(HASHORDER,BARCODE,DESCRIPTION,SEQUENTIAL, PROCESSED_COUNT,CANCELED_COUNT,TOTAL_VALUE,ORDERCODE,PAYMENT_MODE)"
								       	"VALUES ('%s', '%s', '%s', %d, %d, %d, %d, %d, %d);";

	const char * update_product_store_branch_company_sql         = "UPDATE  '%s_STORE_BRANCHS_COMPANY' SET COUNT_AVAILABLE = %d WHERE BARCODE == '%s';";
	const char * insert_product_store_branch_company_sql         = "INSERT INTO '%s_STORE_BRANCHS_COMPANY' ('DESCRIPTION', 'BARCODE', 'SEQUENTIAL', 'UNIT_VALUE', 'COUNT_AVAILABLE' ) VALUES ('%s', '%s', %d, %d, %d);";

	void createBranchCompanyTable();
	void populateBranchCompanyTable();

public:
	/* Static access method. */
        static DataBase* getInstance();
	vector<string> getListBranchCompany();
	Product * searchProductOnBranch(string branch, string search_mode, string product);
	void registerOrderOnBranch(string branch, string hashorder, int code, int payment_mode);
	void registerOrderProductsOnBranch(string branch, string hashorder, string barcode, string description, 
                                            int sequential, int count_requested, int count_canceled, 
					    int total_value, int order_type, int payment_mode);
	void updateProductOnBranch(string branch, string barcode, int count_available);
	void insertProductOnBranch(string branch, string barcode, string description,
                                     int count_available, int unit_value, int sequential);
	QHash<QString, Product *> searchSuperProductOnBranch(string branch, int type, int count);
	QHash<QString, ProductOfOrder *> DataBase::searchListOrdersOnBranch(string branch, int sequential);

};
#endif
