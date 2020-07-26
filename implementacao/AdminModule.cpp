#include "AdminModule.h"
#include <iostream>

using namespace std;

AdminModule::AdminModule(void * previous, const QString & name, QWidget * parent)
        : QWidget(parent)
        , mGridLayout(new QGridLayout(this))
{
        Execute();
}

void AdminModule::Execute()
{
	cout << "AdminModule::Execute" << endl;

	db_instance = DataBase::getInstance();

	branchManagementButton                        = new QPushButton(this);
	branchManagementButton->setText               ("Gerenciar Filais");
	branchManagementButton->setSizePolicy         (QSizePolicy::Expanding,QSizePolicy::Expanding);
	QObject::connect(branchManagementButton, SIGNAL(clicked()),this, SLOT(branchManagement_clickedSlot()));

	productsManagementButton                      = new QPushButton(this);
	productsManagementButton->setText             ("Gerenciar Estoque");
	productsManagementButton->setSizePolicy       (QSizePolicy::Expanding,QSizePolicy::Expanding);
	QObject::connect(productsManagementButton, SIGNAL(clicked()),this, SLOT(storeManagement_clickedSlot()));

	reportsManagementButton                       = new QPushButton(this);
	reportsManagementButton->setText              ("Verificar Pedidos");
	reportsManagementButton->setSizePolicy        (QSizePolicy::Expanding,QSizePolicy::Expanding);
	QObject::connect(reportsManagementButton, SIGNAL(clicked()),this, SLOT(reportOrders_clickedSlot()));

	exitButton                                    = new QPushButton(this);
	exitButton->setText                           ("Sair do sistema!");
	exitButton->setSizePolicy                     (QSizePolicy::Expanding,QSizePolicy::Expanding);
	QObject::connect(exitButton, SIGNAL(clicked()),this, SLOT(exit_clickedSlot()));

	mGridLayout->addWidget(branchManagementButton);
	mGridLayout->addWidget(productsManagementButton);
	mGridLayout->addWidget(reportsManagementButton);
	mGridLayout->addWidget(exitButton);

	setWindowTitle("Administração");
	show();
}

void AdminModule::branchManagement_clickedSlot()
{
	QMessageBox msgBox;
	msgBox.setWindowTitle("Bem vindo!");
	msgBox.setText("Nova loja:  "+ ((QPushButton*)sender())->text());
	msgBox.exec();
}

void AdminModule::warningMessage(string str)
{
        QMessageBox msgBox;
        msgBox.setWindowTitle("AVISO!");
        msgBox.setText(str.c_str());
        msgBox.exec();
}


void AdminModule::newOrder_clickedSlot()
{
	QString branchs_field = branchs_comboBox->currentText();
	if(branchs_field.isEmpty())
	{
		warningMessage("Necessário escolher uma filial!");
		return;
	}

	QString client_field = txtClientInfos->text();
        if(client_field.isEmpty())
        {
                warningMessage("Necessário informar os dados do cliente!");
                return;
        }

	QString operation_comboBox = txtClientInfos->text();
	if(operation_comboBox.isEmpty())
	{
		warningMessage("Necessário informar o tipo de operação!");
		return;
	}

	QString operator_field = operator_comboBox->currentText();
	if(operator_field.isEmpty())
	{
		warningMessage("Necessário informar operador!");
		return;
	}

	QString desc_product    = descProduct->text();
	if(desc_product.isEmpty())
	{
		warningMessage("Necessário informar descrição do produto!");
		return;
	}

	QString barcode_product = barcodeProduct->text();
	if(barcode_product.isEmpty())
	{
		warningMessage("Necessário informar código de barras (6 dígitos)!");
		return;
	}

	QString count_product   = countProduct->text();
	if(count_product.isEmpty())
	{
		warningMessage("Necessário informar a quantidade!");
		return;
	}

	QString obs = "NECESSARIO TROCAR ISSO";

	neworder = new OrderModule(branchs_field, client_field, operator_field, obs, INPUT_ORDER);

	ProcessingOrder();
}


void AdminModule::storeManagement_clickedSlot()
{
	mGridLayout->removeWidget(branchManagementButton);
	mGridLayout->removeWidget(productsManagementButton);
	mGridLayout->removeWidget(reportsManagementButton);
	mGridLayout->removeWidget(exitButton);

        delete branchManagementButton;
        delete productsManagementButton;
        delete reportsManagementButton;
        delete exitButton;

	branchLabel = new QLabel("Qual filial: ");

        // Options to branch company
        branchs_comboBox = new QComboBox;

        branchs_comboBox->addItem(tr(""));
        vector<string> list = db_instance->getListBranchCompany();
        vector<string>::const_iterator iter;
        for (iter = list.begin(); iter != list.end(); ++iter)
        {
                string s;
                s = *iter;
                branchs_comboBox->addItem(tr(s.c_str()));
        }
        list.clear();

	operatorLabel = new QLabel("Tipo de operação: ");
        // Operator in hardcode yet     
        operator_comboBox = new QComboBox;
        operator_comboBox->addItem(tr(""));
        operator_comboBox->addItem(tr("Operador 1"));
        operator_comboBox->addItem(tr("Operador 2"));
        operator_comboBox->addItem(tr("Operador 3"));
        operator_comboBox->setSizePolicy(QSizePolicy::Expanding,QSizePolicy::Expanding);

        // Operation in hardcode yet     
	operationLabel = new QLabel("Tipo de operação: ");
        operation_comboBox = new QComboBox;
        operation_comboBox->addItem(tr(""));
        operation_comboBox->addItem(tr("Adicionar Produto"));
        operation_comboBox->addItem(tr("Remover   Produto"));

	clientLabel = new QLabel("Descrição do cliente: ");
        txtClientInfos = new QLineEdit(this);
        txtClientInfos->setSizePolicy(QSizePolicy::Expanding,QSizePolicy::Expanding);

	descProdLabel = new QLabel("Descrição do produto: ");
        descProduct = new QLineEdit(this);
        descProduct->setSizePolicy(QSizePolicy::Expanding,QSizePolicy::Expanding);


	barcodeLabel = new QLabel("Entre com código de barras: ");
        barcodeProduct = new QLineEdit(this);
	barcodeProduct->setInputMask("999999");
	barcodeProduct->setMaxLength(6);
        barcodeProduct->setSizePolicy(QSizePolicy::Expanding,QSizePolicy::Expanding);

	countProdLabel = new QLabel("Entre com total de produtos: ");
        countProduct = new QLineEdit(this);
        countProduct->setSizePolicy(QSizePolicy::Expanding,QSizePolicy::Expanding);
	countProduct->setInputMask("999");
	countProduct->setMaxLength(3);

	valueProdLabel = new QLabel("Entre com preço de produtos: ");
        valueProduct = new QLineEdit(this);
        valueProduct->setSizePolicy(QSizePolicy::Expanding,QSizePolicy::Expanding);
	valueProduct->setInputMask("999");
	valueProduct->setMaxLength(3);

	finishOrderButton                         = new QPushButton(this);
	finishOrderButton->setText                ("Executar Operação");
	finishOrderButton->setSizePolicy         (QSizePolicy::Expanding,QSizePolicy::Expanding);
	//QObject::connect(finishOrderButton, SIGNAL(clicked()),this, SLOT(finishedOrder_clickedSlot()));
	QObject::connect(finishOrderButton, SIGNAL(clicked()),this, SLOT(newOrder_clickedSlot()));

	mGridLayout->addWidget(branchLabel);
	mGridLayout->addWidget(branchs_comboBox);
	mGridLayout->addWidget(operatorLabel);
	mGridLayout->addWidget(operator_comboBox);
	mGridLayout->addWidget(operationLabel);
	mGridLayout->addWidget(operation_comboBox);

	mGridLayout->addWidget(clientLabel);
	mGridLayout->addWidget(txtClientInfos);

	mGridLayout->addWidget(descProdLabel);
	mGridLayout->addWidget(descProduct);
	mGridLayout->addWidget(barcodeLabel);
	mGridLayout->addWidget(barcodeProduct);
	mGridLayout->addWidget(countProdLabel);
	mGridLayout->addWidget(countProduct);
	mGridLayout->addWidget(valueProdLabel);
	mGridLayout->addWidget(valueProduct);
	mGridLayout->addWidget(finishOrderButton);

	show();
}

void AdminModule::ProcessingOrder()
{
	warningMessage("Vai processar o pedido de entrada!");
#if 0
        //Register order in a table of orders of the branch company
        db_instance->registerOrderOnBranch(neworder->branchs_field.toStdString(),
                                        neworder->hash_session.toStdString(),
                                        neworder->order_type,
                                        neworder->payment_mode);

        //Register all products of the current order in a table especific
        // Need made a loop in a hash table of products of order
        QHashIterator<QString, Product *> iter(neworder->Products);
        Product * product;
        QString key;
        while (iter.hasNext())
        {
                iter.next();
                product = (Product *) iter.value();
                key     = (QString) iter.key();

                cout << key.toStdString() << ": " << product->count_requested << endl;

                db_instance->registerOrderProductsOnBranch(neworder->branchs_field.toStdString(),
                                                neworder->hash_session.toStdString(),
                                                product->barcode.toStdString(),
                                                product->count_requested,
                                                product->count_canceled,
                                                product->total_value);
                // Update store products
                product->updateNewCount();
                db_instance->updateProductOnBranch(neworder->branchs_field.toStdString(),
                                                   product->barcode.toStdString(),
                                                   product->count_available);

        }
#endif
}

void AdminModule::reportOrders_clickedSlot()
{
}

void AdminModule::exit_clickedSlot()
{
}
