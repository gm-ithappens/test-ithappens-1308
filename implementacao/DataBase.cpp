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


void DataBase::createBranchCompanyTable()
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
		cout << "erro ao criar a tabela create_orders_branch_company1_sql.c_str " << messaggeError << endl;
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


static int retrieveLineProduct(void* data, int argc, char** argv, char** azColName)
{

	Product * line_product = (Product * )data;
	QString str1, str2, tmp;


	int i;
	for(i = 0; i < argc; i++)
	{
		str1="DESCRIPTION";
		str2=azColName[i];
		cout << str1.toStdString() << " : "  << str2.toStdString() << endl;
		if(!QString::compare(str1, str2, Qt::CaseInsensitive))
		{
			line_product->status_in_db      =   FOUND_IN_DB;

			line_product->description      = argv[i];
			continue;
		}

		str1="BARCODE";
		if(!QString::compare(str1, str2, Qt::CaseInsensitive))
		{
			line_product->barcode         = argv[i];
			continue;
		}

		str1="SEQUENTIAL";
		if(!QString::compare(str1, str2, Qt::CaseInsensitive))
		{
			tmp = argv[i];
			line_product->sequential      = tmp.toInt();
			continue;
		}

		str1="COUNT_AVAILABLE";
		if(!QString::compare(str1, str2, Qt::CaseInsensitive))
		{
			tmp = argv[i];
			line_product->count_available = tmp.toInt();
			continue;
		}

		str1="UNIT_VALUE";
		if(!QString::compare(str1, str2, Qt::CaseInsensitive))
		{
			tmp = argv[i];
			line_product->unit_value      = tmp.toInt();
			continue;
		}
	}


	return 0;
}

QHash<QString, Product *> HTProducts;
QHash<QString, ProductOfOrder *> HTProductOfOrder;

static int retrieveAllLineProduct(void* data, int argc, char** argv, char** azColName)
{
	//QHash<QString, Product *> * HTProducts = (QHash<QString, Product *> * ) data;

	QString str1, str2, tmp;
	//Product * line_product = (Product * )data;
	Product * line_product = new Product;

	int i;
	for(i = 0; i < argc; i++)
	{
		str1="DESCRIPTION";
		str2=azColName[i];
		//cout << str1.toStdString() << " : "  << str2.toStdString() << endl;
		if(!QString::compare(str1, str2, Qt::CaseInsensitive))
		{
			line_product->status_in_db      =   FOUND_IN_DB;

			line_product->description      = argv[i];
			continue;
		}

		str1="BARCODE";
		if(!QString::compare(str1, str2, Qt::CaseInsensitive))
		{
			line_product->barcode         = argv[i];
			continue;
		}

		str1="SEQUENTIAL";
		if(!QString::compare(str1, str2, Qt::CaseInsensitive))
		{
			tmp = argv[i];
			line_product->sequential      = tmp.toInt();
			continue;
		}

		str1="COUNT_AVAILABLE";
		if(!QString::compare(str1, str2, Qt::CaseInsensitive))
		{
			tmp = argv[i];
			line_product->count_available = tmp.toInt();
			//cout << "COUNT_AVAILABLE -> " << line_product->count_available << endl; 
			continue;
		}

		str1="UNIT_VALUE";
		if(!QString::compare(str1, str2, Qt::CaseInsensitive))
		{
			tmp = argv[i];
			line_product->unit_value      = tmp.toInt();
			continue;
		}
	}

	HTProducts[line_product->description] = line_product;

	return 0;
}

Product * DataBase::searchProductOnBranch(string branch, string search_mode, string product)
{
	char query[100];
	int return_code;
	char* messaggeError;

	Product * line_product = new Product;
	line_product->status_in_db      =   NOT_FOUND_IN_DB;

	snprintf(query, 100, select_product_store_branch_company_sql, branch.c_str(), search_mode.c_str(), product.c_str());

	cout << "DataBase::searchProductOnBranch: " << query << endl;

	return_code = sqlite3_exec(db_instance, 
			query, 
			retrieveLineProduct, 
			(void *)line_product,
			&messaggeError);

	if (return_code != SQLITE_OK)
		sqlite3_free(messaggeError); 

	return line_product;
}


QHash<QString, Product *> DataBase::searchSuperProductOnBranch(string branch, int type, int count)
{
	char query[256];
	int return_code;
	char* messaggeError;

	HTProducts.clear();

	if(type == 0)
		snprintf(query, 256, select_product_store_branch_company_hsuper_sql, branch.c_str(), count);
	else
		snprintf(query, 256, select_product_store_branch_company_lsuper_sql, branch.c_str(), count);

	cout << "DataBase::searchProductOnBranch: " << query <<  count << endl;

	return_code = sqlite3_exec(db_instance, 
			query, 
			retrieveAllLineProduct, 
			0,
			&messaggeError);

	if (return_code != SQLITE_OK)
		sqlite3_free(messaggeError); 

	return HTProducts;
}

static int retrieveOrderListProduct(void* data, int argc, char** argv, char** azColName)
{
	QString str1, str2, tmp;
	ProductOfOrder * line_order = new ProductOfOrder;

	int i;
	for(i = 0; i < argc; i++)
	{
		str1="HASHORDER";
		str2=azColName[i];
		//cout << str1.toStdString() << " : "  << str2.toStdString() << endl;
		if(!QString::compare(str1, str2, Qt::CaseInsensitive))
		{
			line_order->hash_session      = argv[i];
			continue;
		}

		str1="BARCODE";
		if(!QString::compare(str1, str2, Qt::CaseInsensitive))
		{
			line_order->barcode         = argv[i];
			continue;
		}

		str1="DESCRIPTION";
		if(!QString::compare(str1, str2, Qt::CaseInsensitive))
		{
			line_order->description         = argv[i];
			continue;
		}

		str1="SEQUENTIAL";
		if(!QString::compare(str1, str2, Qt::CaseInsensitive))
		{
			tmp = argv[i];
			line_order->sequential      = tmp.toInt();
			continue;
		}

		str1="PROCESSED_COUNT";
		if(!QString::compare(str1, str2, Qt::CaseInsensitive))
		{
			tmp = argv[i];
			line_order->count_requested = tmp.toInt();
			//cout << "PROCESSED_COUNT -> " << line_product->count_available << endl; 
			continue;
		}
		str1="CANCELED_COUNT";
		if(!QString::compare(str1, str2, Qt::CaseInsensitive))
		{
			tmp = argv[i];
			line_order->count_canceled = tmp.toInt();
			//cout << "COUNT_AVAILABLE -> " << line_product->count_available << endl; 
			continue;
		}

		str1="TOTAL_VALUE";
		if(!QString::compare(str1, str2, Qt::CaseInsensitive))
		{
			tmp = argv[i];
			line_order->total_value      = tmp.toInt();
			continue;
		}
	}

	HTProductOfOrder[line_order->description] = line_order;

	return 0;
}

QHash<QString, ProductOfOrder *> DataBase::searchListOrdersOnBranch(string branch, int sequential)
{
	char query[256];
	int return_code;
	char* messaggeError;

	HTProductOfOrder.clear();

	snprintf(query, 256, select_order_branch_company_filter_seq_sql, branch.c_str(), sequential);
	cout << "DataBase::searchListOrdersOnBranch: " << query << endl;

	return_code = sqlite3_exec(db_instance, 
			query, 
			retrieveOrderListProduct, 
			0,
			&messaggeError);

	if (return_code != SQLITE_OK)
		sqlite3_free(messaggeError); 

	return HTProductOfOrder;
}


void DataBase::registerOrderOnBranch(string branch, string hashorder, int code, int payment_mode)
{
	char query[256];
	int return_code;
	char* messaggeError;

	Product * line_product = new Product;

	snprintf(query, 256, insert_order_store_branch_company_sql, branch.c_str(), hashorder.c_str(), code, payment_mode);

	cout << "DataBase::registerOrderOnBranch: " << query << endl;

	return_code = sqlite3_exec(db_instance, 
			query, 
			0, 
			0,
			&messaggeError);

	if (return_code != SQLITE_OK)
	{
		cout << "erro ao inserir pedido:  " << messaggeError << endl;
		sqlite3_free(messaggeError); 
	}

}

void DataBase::registerOrderProductsOnBranch(string branch, string hashorder, string barcode, string description, 
					     int sequential, int count_requested, int count_canceled, int total_value)
{
	char query[512];
	int return_code;
	char* messaggeError;

	Product * line_product = new Product;

	snprintf(query, 512, insert_order_product_store_branch_company_sql, branch.c_str(),
									    hashorder.c_str(), 
									    barcode.c_str(),
									    description.c_str(),
									    sequential,
									    count_requested, 
									    count_canceled, 
									    total_value);

	cout << "DataBase::registerOrderProductsOnBranch: " << query << endl;

	return_code = sqlite3_exec(db_instance, 
			query, 
			0, 
			0,
			&messaggeError);

	if (return_code != SQLITE_OK)
	{
		cout << "erro ao atualizar os produtos " << messaggeError << endl;
		sqlite3_free(messaggeError); 
	}

}

void DataBase::updateProductOnBranch(string branch, string barcode, int count_available)
{
	char query[256];
	int return_code;
	char* messaggeError;

	Product * line_product = new Product;

	snprintf(query, 256, update_product_store_branch_company_sql, 
							branch.c_str(),
							count_available,
							barcode.c_str());

	cout << "DataBase::updateProductOnBranch: " << query << endl;

	return_code = sqlite3_exec(db_instance, 
			query, 
			0, 
			0,
			&messaggeError);

	if (return_code != SQLITE_OK)
	{
		cout << "erro ao atualizar os produtos " << messaggeError << endl;
		sqlite3_free(messaggeError); 
	}

}

void DataBase::insertProductOnBranch(string branch, string barcode, string description, int count_available, int unit_value, int sequential)
{
	char query[512];
	int return_code;
	char* messaggeError;

	Product * line_product = new Product;

	snprintf(query, 512, insert_product_store_branch_company_sql, 
							branch.c_str(),
							description.c_str(),
							barcode.c_str(),
							sequential,
							unit_value,
							count_available);

	cout << "DataBase::insertProductOnBranch: " << query << endl;

	return_code = sqlite3_exec(db_instance, 
			query, 
			0, 
			0,
			&messaggeError);

	if (return_code != SQLITE_OK)
	{
		cout << "erro ao inserir os produtos " << messaggeError << endl;
		sqlite3_free(messaggeError); 
	}

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
