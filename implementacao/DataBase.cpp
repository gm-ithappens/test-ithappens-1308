#include "DataBase.h"
#include <iostream>
#include <sys/stat.h>

using namespace std;

DataBase::DataBase()
{
	int return_code;

	/* Open database */
	return_code = sqlite3_open("database.db", &db_instance);

	if(return_code)
		cout <<  "Can't open database:" << endl;
	else 
		cout << "Opened database successfully" << endl;

}


int DataBase::createBranchCompanyTable()
{
	int return_code;
	char* messaggeError;

	return_code = sqlite3_exec(db_instance, create_branch_company_sql.c_str(), NULL, 0, &messaggeError);

	if (return_code != SQLITE_OK)
	{ 
		cerr << "Error Create Table: " << messaggeError << endl; 
		sqlite3_free(messaggeError); 
	} 
	else
		cout << "Table created Successfully" << endl; 


	return_code = sqlite3_exec(db_instance, create_store_branch_company1_sql.c_str(), NULL, 0, &messaggeError);

	if (return_code != SQLITE_OK)
	{ 
		sqlite3_free(messaggeError); 
		return;
	} 


	return_code = sqlite3_exec(db_instance, create_store_branch_company2_sql.c_str(), NULL, 0, &messaggeError);

	if (return_code != SQLITE_OK)
	{ 
		sqlite3_free(messaggeError); 
		return;
	} 


	return_code = sqlite3_exec(db_instance, create_orders_branch_company1_sql.c_str(), NULL, 0, &messaggeError);

	if (return_code != SQLITE_OK)
	{ 
		sqlite3_free(messaggeError); 
		return;
	} 


	return_code = sqlite3_exec(db_instance, create_orders_branch_company2_sql.c_str(), NULL, 0, &messaggeError);

	if (return_code != SQLITE_OK)
	{ 
		sqlite3_free(messaggeError); 
		return;
	} 


	return_code = sqlite3_exec(db_instance, create_orders_products_branch_company1_sql.c_str(), NULL, 0, &messaggeError);

	if (return_code != SQLITE_OK)
	{ 
		sqlite3_free(messaggeError); 
		return;
	} 


	return_code = sqlite3_exec(db_instance, create_orders_products_branch_company2_sql.c_str(), NULL, 0, &messaggeError);

	if (return_code != SQLITE_OK)
	{ 
		sqlite3_free(messaggeError); 
		return;
	} 
	//sqlite3_close(db_instance); 
}

void DataBase::populateBranchCompanyTable()
{
	int return_code;
	char* messaggeError;

	return_code = sqlite3_exec(db_instance, populate_branch_company1_sql.c_str(), NULL, 0, &messaggeError);

	if (return_code != SQLITE_OK)
	{ 
		sqlite3_free(messaggeError); 
		return;
	} 

	return_code = sqlite3_exec(db_instance, populate_branch_company2_sql.c_str(), NULL, 0, &messaggeError);

	if (return_code != SQLITE_OK)
	{ 
		sqlite3_free(messaggeError); 
		return;
	} 

}

vector<string> branch_company_list;

static int retrieveStringVector(void* data, int argc, char** argv, char** azColName)
{
	int i;
	for(i = 0; i < argc; i++)
	{
		cout << azColName[i] << ": " << argv[i] << endl;
		string s = argv[i];
		branch_company_list.push_back(s);
	}

	return 0;
}

vector<string> DataBase::getListBranchCompany()
{
	int return_code;
	char* messaggeError;

	return_code = sqlite3_exec(db_instance, 
			select_branch_company_sql.c_str(), 
			retrieveStringVector, 
			0,
			&messaggeError);

	if (return_code != SQLITE_OK)
	{ 
		sqlite3_free(messaggeError); 
		return branch_company_list;
	} 

	return branch_company_list;
}


void DataBase::searchProductOnBranch(string branch, string search_mode, string product)
{
	char query[100];
	snprintf(query, 100, select_product_store_branch_company_sql, branch.c_str(), search_mode.c_str(), product.c_str());

	cout << "DataBase::searchProductOnBranch: " << query << endl;
}



DataBase * DataBase::instance = 0;

DataBase * DataBase::getInstance()
{
	int need_creat_structure = 0;
	struct stat buffer;

	string database_file =  "database.db";

	if(stat(database_file.c_str(), &buffer) != 0)
		need_creat_structure = 1;

	if (instance == 0)
	{
		cout << "Vai instanciar" << endl; 
		instance = new DataBase();
	}

	if(need_creat_structure)
	{
		cout << "Creating branch mall table" << endl; 
		instance->createBranchCompanyTable();
		instance->populateBranchCompanyTable();
	}

	return instance;
}
