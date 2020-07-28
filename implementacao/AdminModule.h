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
#include <QGroupBox>
#include <QRadioButton>

#define BIGGER_THAN	0
#define LESS_THAN	1

class AdminModule: public QWidget
{
	Q_OBJECT

public:
	 explicit  AdminModule(void * previous, const QString & name, QWidget * parent = 0);
	~ AdminModule(){}

	void Execute();
	//void warningMessage(string str);
	void destroyInitialAdminScreen();
	QGroupBox * createExclusiveGroup();
	void reportResumeXPlusButtonManagement();
	void destroyResumeXPlusButtonManagement();

public slots:
	void branchManagement_clickedSlot  ();
	void pre_storeManagement_clickedSlot();
	void storeManagement_clickedSlot   ();
	void reportOrders_clickedSlot      ();
	void exit_clickedSlot              ();
	void finishOrder_clickedSlot       ();
	void reportSuperlativeSearch_clickedSlot();
	void superlativeRadioButtonBigger_onToggled(bool);
	void superlativeRadioButtonLess_onToggled(bool);
	void returnFromSuperlative_clickedSlot();
	void pre_optionsGeneralListManagement_clickedSlot();
	void destroyOptionsListManagementScreen();
	void pre_listSuperlativeManagement_clickedSlot();
	void pre_listOrdersManagement_clickedSlot();
	void listSuperlativeManagement_clickedSlot();
	void returnStoreManagement_clickedSlot_clickedSlot();
	void destroyOptionsGeneralListManagementScreen();
	void optionsGeneralListManagement_clickedSlot();
	void listOrdersManagement_clickedSlot();
	void returnSearchlistOrders_clickedSlot();
	void reportSearchlistOrders_clickedSlot();
	void pre_branchManagement_clickedSlot();
	void returnBranchManagement_clickedSlot();
	void doCreateNewBranch_clickedSlot();
	void returnlistOrdersPaymentManagement_clickedSlot();
	void listOrdersPaymentManagement_clickedSlot();
	void pre_listOrdersPaymentManagement_clickedSlot();
	void reportSearchlistOrdersPayment_clickedSlot();
	QComboBox   * mountComboBoxBranchNames();
	void pre_ResumeSperlativeManagement_clickedSlot();
	void pre_ResumeXPlusButtonManagement_clickedSlot();
	void ResumeXPlusButtonManagement_clickedSlot();
	void returnResumeXPlusButtonManagement_clickedSlot();
	void returnResumeSuperlativeManagement_clickedSlot();
	void ResumeSuperlativeManagement_clickedSlot();
	void reportResumeSperlativeManagement_clickedSlot();
	void branchChoosedChangedMountOrdersList();
	void reportResumeXPlusButtonManagement_clickedSlot();
	void clearScreenRegistreProduct_clickedSlot();
	void tryAddProductToOrder_clickedSlot();


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
	QLabel      *   generalLabel;

	QPushButton * branchManagementButton;
	QPushButton * productsManagementButton;
	QPushButton * reportsManagementButton;
	QPushButton * exitButton;
	QPushButton * finishOrderButton;
	QPushButton * listManagementButton;
	QPushButton * returnButton;
	QPushButton * execSearch;
	QPushButton * execSearchlistOrders;
	QPushButton * execTaskButton;
	QPushButton * listManagPaymentButton;
	QPushButton * reportResumeXPlusButton;
	QPushButton * resumeSperlativeButton;
	QPushButton * addMoreButton;

        QComboBox   *   branchs_comboBox;
        QComboBox   *   operator_comboBox;
        QComboBox   *   operation_comboBox;
	QComboBox   *   cboxOrder;

	QLineEdit   *   txtClientInfos;
        QLineEdit   *   descProduct;
	QLineEdit   *   sequentialProduct;
        QLineEdit   *   barcodeProduct;
        QLineEdit   *   countProduct;
        QLineEdit   *   valueProduct;
	QLineEdit   *   generalEdit;

	QGroupBox * groupBox;

	QRadioButton *radioBiggerThan;
	QRadioButton *radioLessThan;

	int superlative_radio_option;

	DataBase * db_instance;

	OrderModule *   neworder;
};
#endif
