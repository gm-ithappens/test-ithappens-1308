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

#define STATUS_PRODUCT_ACTIVE		0
#define STATUS_PRODUCT_CANCELED		1
#define STATUS_PRODUCT_PROCESSED	2

class Product
{
public:
	void updateTotal(int added)
	{
		count       = count + added;
		total_value = count * unit_value;
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
	int count;
	int unit_value;
	int total_value;
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
	int order_type;

};
#endif
