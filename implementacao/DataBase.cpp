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

void DataBase::createBranchsCompanyTable()
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
}

void DataBase::createBranchCompanyTable(string branch_name)
{
	int size_query = 512;
	char query[size_query];
	int return_code;
	char* messaggeError;

	snprintf(query, size_query, populate_branch_company_sql, branch_name.c_str());
	return_code = sqlite3_exec(db_instance, query, NULL, 0, &messaggeError);

	if (return_code != SQLITE_OK)
	{ 
		sqlite3_free(messaggeError); 
		return;
	} 


	snprintf(query, size_query, create_store_branch_company_sql, branch_name.c_str());

	return_code = sqlite3_exec(db_instance, query, NULL, 0, &messaggeError);
	cout << query << endl;

	if (return_code != SQLITE_OK)
	{ 
		sqlite3_free(messaggeError); 
		return;
	} 

	snprintf(query, size_query, create_orders_branch_company_sql, branch_name.c_str());
	return_code = sqlite3_exec(db_instance, query, NULL, 0, &messaggeError);

	if (return_code != SQLITE_OK)
	{ 
		sqlite3_free(messaggeError); 
		return;
	} 

	snprintf(query, size_query, create_orders_products_branch_company_sql, branch_name.c_str());
	return_code = sqlite3_exec(db_instance, query, NULL, 0, &messaggeError);

	if (return_code != SQLITE_OK)
	{ 
		sqlite3_free(messaggeError); 
		return;
	} 
}

static int retrieveQList(void* data, int argc, char** argv, char** azColName)
{
	QString tmp;
	QList<QString> * ql = (QList<QString> *) data;

	int i;
	for(i = 0; i < argc; i++)
	{
		tmp = argv[i];
		ql->append(tmp);
	}

	return 0;
}


QList<QString> * DataBase::getListBranchCompany()
{
	QList<QString> * ql = new QList<QString>();
	int return_code;
	char* messaggeError;

	return_code = sqlite3_exec(db_instance, 
			select_branch_company_sql.c_str(), 
			retrieveQList, 
			(void *)ql,
			&messaggeError);

	if (return_code != SQLITE_OK)
	{ 
		sqlite3_free(messaggeError); 
		return ql;
	} 

	return ql;
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

		str1="ORDERCODE";
		if(!QString::compare(str1, str2, Qt::CaseInsensitive))
		{
			tmp = argv[i];
			line_order->order_type      = tmp.toInt();
			continue;
		}

		str1="PAYMENT_MODE";
		if(!QString::compare(str1, str2, Qt::CaseInsensitive))
		{
			tmp = argv[i];
			line_order->payment_mode     = tmp.toInt();
			continue;
		}
	}

	HTProductOfOrder[line_order->description] = line_order;

	return 0;
}

static int retrieveOrder(void* data, int argc, char** argv, char** azColName)
{
	QString str1, str2, tmp;
	Order * order = (Order *) data;

	int i;
	for(i = 0; i < argc; i++)
	{
		str1="HASHORDER";
		str2=azColName[i];
		if(!QString::compare(str1, str2, Qt::CaseInsensitive))
		{
			order->hash_session      = argv[i];
			continue;
		}

		str1="ORDERCODE";
		if(!QString::compare(str1, str2, Qt::CaseInsensitive))
		{
			order->order_type         = argv[i];
			continue;
		}

		str1="PAYMENT_MODE";
		if(!QString::compare(str1, str2, Qt::CaseInsensitive))
		{
			tmp = argv[i];
			order->payment_mode      = tmp.toInt();
			continue;
		}

		str1="TOTAL_ITENS";
		if(!QString::compare(str1, str2, Qt::CaseInsensitive))
		{
			tmp = argv[i];
			order->total_itens      = tmp.toInt();
			continue;
		}

		str1="TOTAL_VALUE";
		if(!QString::compare(str1, str2, Qt::CaseInsensitive))
		{
			tmp = argv[i];
			order->total_value = tmp.toInt();
			continue;
		}
	}

	return 0;
}

Order * DataBase::searchOneOrderHashofBranch(string branch, string hashsession)
{
	char query[256];
	int return_code;
	char* messaggeError;
	Order * order = new Order;

	snprintf(query, 256, select_one_order_of_branch_sql, branch.c_str(), hashsession.c_str());
	cout << "DataBase::searchOneOrderHashofBranch: " << query << endl;

	return_code = sqlite3_exec(db_instance, 
			query, 
			retrieveOrder, 
			(void *)order,
			&messaggeError);

	if (return_code != SQLITE_OK)
		sqlite3_free(messaggeError); 

	return order;
}

QHash<QString, ProductOfOrder *> DataBase::searchListOrdersProductHashOnBranch(string branch, string hashsession)
{
	char query[256];
	int return_code;
	char* messaggeError;

	HTProductOfOrder.clear();

	snprintf(query, 256, select_order_branch_company_filter_hash_seq_sql, branch.c_str(), hashsession.c_str());
	cout << "DataBase::searchListOrdersProductHashOnBranch: " << query << endl;

	return_code = sqlite3_exec(db_instance, 
			query, 
			retrieveOrderListProduct, 
			0,
			&messaggeError);

	if (return_code != SQLITE_OK)
		sqlite3_free(messaggeError); 

	return HTProductOfOrder;
}

QHash<QString, ProductOfOrder *> DataBase::searchListOrdersProductOnBranch(string branch, int sequential)
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

QHash<QString, ProductOfOrder *> DataBase::searchListOrderAndPaymentOnBranch(string branch)
{
	char query[256];
	int return_code;
	char* messaggeError;

	HTProductOfOrder.clear();

	snprintf(query, 256, select_order_branch_company_filter_payment_sql, branch.c_str());
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


QList<QString> * DataBase::searchOrdersHashofBranch(string branch)
{
	char query[512];
	int return_code;
	char* messaggeError;
	QList<QString> * ql = new QList<QString>();

	snprintf(query, 512, select_orders_hash_of_branch, branch.c_str());
	cout << "DataBase::searchOrdersHashofBranch: " << query << endl;

	return_code = sqlite3_exec(db_instance, 
			query, 
			retrieveQList, 
			(void *) ql,
			&messaggeError);

	if (return_code != SQLITE_OK)
		sqlite3_free(messaggeError); 

	return ql;
}


QList<QString> * DataBase::searchResumedOrdersSuperlative(string branch, int count)
{
	char query[512];
	int return_code;
	char* messaggeError;
	QList<QString> * ql = new QList<QString>();

	snprintf(query, 512, select_resumed_orders_superlative, branch.c_str(), count);
	cout << "DataBase::earchResumedOrdersSuperlative: " << query << endl;

	return_code = sqlite3_exec(db_instance, 
			query, 
			retrieveQList, 
			(void *) ql,
			&messaggeError);

	if (return_code != SQLITE_OK)
		sqlite3_free(messaggeError); 

	return ql;
}


void DataBase::registerOrderOnBranch(string branch, string hashorder, int code, int payment_mode, int total_value, int total_itens)
{
	char query[256];
	int return_code;
	char* messaggeError;

	Product * line_product = new Product;

	snprintf(query, 256, insert_order_store_branch_company_sql, branch.c_str(), hashorder.c_str(), code, payment_mode, total_itens, total_value);

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
					     int sequential, int count_requested, int count_canceled, int total_value, int order_type, int payment_mode)
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
									    total_value,
									    order_type,
									    payment_mode
									    );

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
		instance->createBranchsCompanyTable();
	}

	return instance;
}
