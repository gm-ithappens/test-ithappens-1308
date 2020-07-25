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
	//string select_branch_company_sql = "SELECT * FROM 'BRANCHS_COMPANY'";

	// Used only for tests purposes
	string populate_branch_company1_sql = "INSERT INTO BRANCHS_COMPANY ('ID', 'NAME')  VALUES (1, 'Vinhais');";
	string populate_branch_company2_sql = "INSERT INTO BRANCHS_COMPANY ('ID', 'NAME')  VALUES (2, 'Centro');";
	string populate_branch_company3_sql = "INSERT INTO BRANCHS_COMPANY ('ID', 'NAME')  VALUES (3, 'Cohab');";

	int createBranchCompanyTable();
	void populateBranchCompanyTable();

public:
	/* Static access method. */
        static DataBase* getInstance();
	vector<string> getListBranchCompany();

};
#endif
