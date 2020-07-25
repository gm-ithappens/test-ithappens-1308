#include "DataBase.h"
#include <iostream>

using namespace std;

DataBase::DataBase()
{
	int return_code;

	/* Open database */
	return_code = sqlite3_open("database.db", &db_instance);

	if(return_code)
	{
		fprintf(stderr, "Can't open database: %s\n", sqlite3_errmsg(db_instance));
	}
	else 
	{
		fprintf(stderr, "Opened database successfully\n");
	}
}


int DataBase::createBranchCompanyTable()
{
	int return_code;
	char* messaggeError;

	return_code = sqlite3_exec(db_instance, create_branch_company_sql.c_str(), NULL, 0, &messaggeError);

	if (return_code != SQLITE_OK)
	{ 
		cerr << "Error Create Table" << endl; 
		sqlite3_free(messaggeError); 
	} 
	else
		cout << "Table created Successfully" << endl; 

	sqlite3_close(db_instance); 
}

DataBase * DataBase::instance = 0;

DataBase * DataBase::getInstance()
{
    if (instance == 0)
    {
        instance = new DataBase();
    }

    return instance;
}
