#ifndef ADMINMODULE_H
#define ADMINMODULE_H

#include "OrderModule.h"
#include "DataBase.h"

#include <QPushButton>
#include <QMessageBox>
#include <QMainWindow>
#include <QVBoxLayout>

#include <QGridLayout>
#include <QLabel>
#include <QComboBox>
#include <QString>
#include <QLineEdit>
#include <QInputDialog>

class AdminModule: public QWidget
{
	Q_OBJECT

public:
	 explicit  AdminModule(void * previous, const QString & name, QWidget * parent = 0);
	~ AdminModule(){}

	void Execute();
	void warningMessage(string str);
	void ProcessingOrder(int ordertype);

public slots:
	void branchManagement_clickedSlot  ();
	void storeManagement_clickedSlot   ();
	void reportOrders_clickedSlot      ();
	void exit_clickedSlot              ();
	void newOrder_clickedSlot          ();


private:
	QGridLayout *   mGridLayout;
	QLabel      *   mLabel;
	QLabel      *   clientLabel;
	QLabel      *   countProdLabel;
	QLabel      *   valueProdLabel;
	QLabel      *   barcodeLabel;
	QLabel      *   branchLabel;
	QLabel      *   operatorLabel;
	QLabel      *   operationLabel;
	QLabel      *   descProdLabel;
	QLabel      *   sequentialLabel;

	QPushButton * branchManagementButton;
	QPushButton * productsManagementButton;
	QPushButton * reportsManagementButton;
	QPushButton * exitButton;
	QPushButton * finishOrderButton;

        QComboBox   *   branchs_comboBox;
        QComboBox   *   operator_comboBox;
        QComboBox   *   operation_comboBox;

	QLineEdit   *   txtClientInfos;
        QLineEdit   *   descProduct;
	QLineEdit   *   sequentialProduct;
        QLineEdit   *   barcodeProduct;
        QLineEdit   *   countProduct;
        QLineEdit   *   valueProduct;

	DataBase * db_instance;

	OrderModule *   neworder;
};
#endif
