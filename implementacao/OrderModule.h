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

// Order types
#define INPUT_ORDER	0
#define OUTPUT_ORDER	1

class Product
{
public:
	void updateRequestedTotal(int added)
	{
		count_requested       = count_requested + added;
		total_value           = count_requested * unit_value;
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
	}
	~ OrderModule(){}

	void Execute();

public:
	QString branchs_field;
	QString client_field;
	QString operator_field;
	QString obs_client_lient_infos;
	QString hash_session;
	int payment_mode;
	int order_type;

	QHash<QString, Product *> Products;

};
#endif
