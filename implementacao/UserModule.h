#ifndef USERMODULE_H
#define USERMODULE_H
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

#include "OrderModule.h"

#include "DataBase.h"

class UserModule: public QWidget
{
	Q_OBJECT

public:
	explicit UserModule  (void * previous, const QString & name, QWidget * parent = 0);
	~ UserModule(){}

	void Execute();
	void ProcessingOrder();
	void warningMessage(string str);
	int hash_order();

public slots:
	void newOrder_clickedSlot          ();
	void newProductSearch_clickedSlot  ();
	void branchChoosedChanged          ();
	void operatorChoosedChanged        ();
	void searchModeChoosedChanged      ();
	void cancelProductSelected         ();
	void finishOrder_clickedSlot       ();

private:
	QGridLayout *   mGridLayout;
	QPushButton *   newOrderButton;
	QPushButton *   cancelButton;
	QPushButton *   finishOrderButton;
	QLabel      *   mLabel;
	QLabel      *   branchLabel;
	QLabel      *   clientLabel;
	QLabel      *   obsLabel;
	QLabel      *   operatorLabel;
	QLabel      *   dataSearchLabel;
	QLabel      *   cancellProdutLabel;
	QComboBox   *   branchs_comboBox;
	QComboBox   *   operator_comboBox;
	QComboBox   *   search_comboBox;
	QLineEdit   *   txtClientInfos;
	QLineEdit   *   obsClientInfos;
	QLineEdit   *   searchData;
	QLineEdit   *   cancelThisProduct;

	DataBase * db_instance;

	OrderModule *   neworder;

};
#endif
