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

//private:
public:
	QString branchs_field;
	QString client_field;
	QString operator_field;
	QString obs_client_lient_infos;
	int order_type;

};
#endif
