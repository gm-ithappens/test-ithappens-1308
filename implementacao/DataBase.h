#ifndef DATABASE_H
#define DATABASE_H

#include <sqlite3.h>
#include <iostream>
#include <vector>

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
	      "ID INT PRIMARY KEY     NOT NULL, "
	      "NAME           TEXT    NOT NULL"
	      ");";

	string select_branch_company_sql = "SELECT NAME FROM 'BRANCHS_COMPANY';";

	// Used only for tests purposes
	string populate_branch_company1_sql = "INSERT INTO BRANCHS_COMPANY ('ID', 'NAME')  VALUES (1, 'Vinhais');";
	string populate_branch_company2_sql = "INSERT INTO BRANCHS_COMPANY ('ID', 'NAME')  VALUES (2, 'Centro');";

	string create_store_branch_company1_sql = "CREATE TABLE Vinhais_STORE_BRANCHS_COMPANY("
	      "ID INT PRIMARY KEY     NOT         NULL, "
	      "DESCRIPTION            TEXT    NOT NULL,"
	      "BARCODE                TEXT    NOT NULL,"
	      "SEQUENTIAL             TEXT    NOT NULL,"
	      "UNIT_VALUE              INT    NOT NULL,"
	      "COUNT                   INT    NOT NULL"
	      ");";

	string create_store_branch_company2_sql = "CREATE TABLE Centro_STORE_BRANCHS_COMPANY("
	      "ID INT PRIMARY KEY     NOT         NULL, "
	      "DESCRIPTION            TEXT    NOT NULL,"
	      "BARCODE                TEXT    NOT NULL,"
	      "SEQUENTIAL              INT    NOT NULL,"
	      "UNIT_VALUE              INT    NOT NULL,"
	      "COUNT                   INT    NOT NULL"
	      ");";

	string create_orders_branch_company1_sql = "CREATE TABLE Vinhais_ORDERS_BRANCHS_COMPANY("
	      "ID INT PRIMARY KEY     NOT         NULL, "
	      "HASH                   TEXT    NOT NULL,"
	      "CODIGO                  INT    NOT NULL,"
	      "PAYMENT_MODE            INT    NOT NULL,"
	      ");";

	string create_orders_branch_company2_sql = "CREATE TABLE Centro_ORDERS_BRANCHS_COMPANY("
	      "ID INT PRIMARY KEY     NOT         NULL, "
	      "HASH                   TEXT    NOT NULL,"
	      "CODIGO                  INT    NOT NULL,"
	      "PAYMENT_MODE            INT    NOT NULL,"
	      ");";

	string create_orders_products_branch_company1_sql = "CREATE TABLE Vinhais_ORDERS_PRODUCTS_BRANCHS_COMPANY("
	      "ID INT PRIMARY KEY     NOT         NULL, "
	      "HASH                   TEXT    NOT NULL,"
	      "BARCODE                TEXT    NOT NULL,"
	      "PROCESSED_COUNT         INT    NOT NULL,"
	      "TOTAL_VALUE             INT    NOT NULL,"
	      ");";

	string create_orders_products_branch_company2_sql = "CREATE TABLE Centro_ORDERS_PRODUCTS_BRANCHS_COMPANY("
	      "ID INT PRIMARY KEY     NOT         NULL, "
	      "HASH                   TEXT    NOT NULL,"
	      "BARCODE                TEXT    NOT NULL,"
	      "PROCESSED_COUNT         INT    NOT NULL,"
	      "TOTAL_VALUE             INT    NOT NULL,"
	      ");";


	char * select_product_store_branch_company_sql = "SELECT * FROM '%s_ORDERS_PRODUCTS_BRANCHS_COMPANY' WHERE '%s' == '%s';";

	int createBranchCompanyTable();
	void populateBranchCompanyTable();

public:
	/* Static access method. */
        static DataBase* getInstance();
	vector<string> getListBranchCompany();
	void searchProductOnBranch(string branch, string search_mode, string product);

};
#endif
