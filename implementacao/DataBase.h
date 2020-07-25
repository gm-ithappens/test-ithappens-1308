#ifndef DATABASE_H
#define DATABASE_H

#include <sqlite3.h>
#include <iostream>

using namespace std;

class DataBase
{
public:
	DataBase  ();
	~ DataBase(){};

private:
	/* Here will be the instance stored. */
	static DataBase * instance;

	sqlite3 * db_instance;
	string create_branch_company_sql = "CREATE TABLE BRANCHS_COMPANY("
	      "ID INT PRIMARY KEY     NOT NULL, "
	      "NAME           TEXT    NOT NULL, "
	      ");";

private:
	int createBranchCompanyTable();

public:
	/* Static access method. */
        static DataBase* getInstance();

};
#endif
