#ifndef ORDERMODULE_H
#define ORDERMODULE_H
#include <QPushButton>
#include <QMessageBox>
#include <QMainWindow>
#include <QVBoxLayout>

#include <QGridLayout>
#include <QLabel>
#include <QComboBox>
#include <QString>
#include <QLineEdit>
#include <QHash>

// Status of products
#define STATUS_PRODUCT_ACTIVE		0
#define STATUS_PRODUCT_CANCELED		1
#define STATUS_PRODUCT_PROCESSED	2

// Payment modes
#define CREDITCARD_PAYMENT_MODE		0
#define MONEY_PAYMENTMODE		1
#define ORDER_PAYMENTMODE		2
#define INPUT_NO_PAYMENT		4

// Order types
#define INPUT_ORDER	0
#define OUTPUT_ORDER	1


#define UPDATE_PRODUCT	0
#define NEW_PRODUCT	1

#define UNKNOW_IN_DB	0
#define NOT_FOUND_IN_DB	1
#define FOUND_IN_DB 	2

class Product
{
public:
	void updateRequestedTotal(int added)
	{
		count_requested       = count_requested + added;
		total_value           = count_requested * unit_value;
	}

	void updateNewCount(int ordertype)
	{
		if(ordertype == INPUT_ORDER)
			count_available = count_available + count_requested;
		else
			count_available = count_available - count_requested;
	}

	void updateCanceledTotal()
	{
		count_canceled        = count_requested;
		count_requested       = 0;
	}

	void cancell()
	{
		status = STATUS_PRODUCT_CANCELED;
	}

public:
	QString description;
	QString barcode;
	int sequential;

	int status;
	int count_available  = 0;
	int unit_value       = 0;
	int total_value      = 0;
	int count_requested  = 0;
	int count_canceled   = 0;

	int status_in_db     =  UNKNOW_IN_DB;
};

class OrderModule
{
public:
	explicit OrderModule(QString branchsfield, 
			     QString clientfield,
			     QString operatorfield,
			     QString obsclientlientinfos,
			     int ordertype)
	{
		branchs_field           = branchsfield;
		client_field            = clientfield;
		operator_field          = operatorfield;
		obs_client_lient_infos  = obsclientlientinfos;
		order_type              = ordertype;

		hash_session            =  QString("%1").arg(hash_order());
	}
	~ OrderModule(){}

	void Execute();

	int hash_order()
	{
	    struct timespec tm;
	    clock_gettime(CLOCK_REALTIME, &tm);
	    return tm.tv_nsec;
	}

        void updateTotalValue(int value)
	{
		total_value += value;
	}
        void updateTotalitens(int count)
	{
		total_itens += count;
	}


	void ProcessingOrder(int ordertype, int product_oper);

public:
	QString branchs_field;
	QString client_field;
	QString operator_field;
	QString obs_client_lient_infos;
	QString hash_session;
	int payment_mode= 0;
	int order_type  = 0;
	int total_value = 0;
	int total_itens = 0;

	QHash<QString, Product *> Products;

};


class Order
{
public:
	QString hash_session;
	int order_type;
	int payment_mode;
	int total_value;
	int total_itens;
};


class ProductOfOrder
{
public:
	QString hash_session;
	QString barcode;
	QString description;
	int sequential;
	int total_value      = 0;
	int count_requested  = 0;
	int count_canceled   = 0;
	int payment_mode;
	int order_type;
};


#endif
