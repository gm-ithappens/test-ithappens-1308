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
	QObject::connect(branchManagementButton, SIGNAL(clicked()),this, SLOT(pre_branchManagement_clickedSlot()));

	productsManagementButton                      = new QPushButton(this);
	productsManagementButton->setText             ("Gerenciar Estoque");
	productsManagementButton->setSizePolicy       (QSizePolicy::Expanding,QSizePolicy::Expanding);
	QObject::connect(productsManagementButton, SIGNAL(clicked()),this, SLOT(pre_storeManagement_clickedSlot()));

	listManagementButton                      = new QPushButton(this);
	listManagementButton->setText             ("Listagem Geral");
	listManagementButton->setSizePolicy       (QSizePolicy::Expanding,QSizePolicy::Expanding);
	QObject::connect(listManagementButton, SIGNAL(clicked()),this, SLOT(pre_optionsListManagement_clickedSlot()));


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
	mGridLayout->addWidget(listManagementButton);
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

	QString seq_product    = sequentialProduct->text();
	if(desc_product.isEmpty())
	{
		warningMessage("Necessário informar sequencial do produto!");
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

	QString value_product   = valueProduct->text();
	if(value_product.isEmpty())
	{
		warningMessage("Necessário informar a quantidade!");
		return;
	}

	QString obs = "NECESSARIO TROCAR ISSO";

	neworder = new OrderModule(branchs_field, client_field, operator_field, obs, INPUT_ORDER);

	Product * line_product = NULL;
	QString search_mode = "BARCODE";
	line_product = db_instance->searchProductOnBranch(neworder->branchs_field.toStdString(), 
							search_mode.toStdString(), 
							barcode_product.toStdString());

	int processing_type = UPDATE_INPUT;
	if(line_product->status_in_db == NOT_FOUND_IN_DB)
	{
		cout << "Vai ser criado um novo produto!" << endl;
		Product * line_product = new Product();
		processing_type        = NEW_INPUT;
	}

	line_product->description     = desc_product;
	line_product->barcode         = barcode_product;
	line_product->sequential      = seq_product.toInt();
	line_product->count_available = count_product.toInt();
	line_product->unit_value      = value_product.toInt();


	neworder->Products[line_product->description] = line_product;

	neworder->ProcessingOrder(processing_type);
}

void AdminModule::destroyOptionsListManagementScreen()
{
	mGridLayout->removeWidget(productsManagementButton);
	mGridLayout->removeWidget(listManagementButton);
	mGridLayout->removeWidget(reportsManagementButton);

	delete productsManagementButton;
	delete listManagementButton;
	delete reportsManagementButton;
}

void AdminModule::pre_storeManagement_clickedSlot()
{
	destroyOptionsListManagementScreen();
	storeManagement_clickedSlot();
}

void AdminModule::storeManagement_clickedSlot()
{
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

	operatorLabel = new QLabel("Selecione operador: ");
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

	sequentialLabel = new QLabel("Entre com código sequencial: ");
        sequentialProduct = new QLineEdit(this);
	sequentialProduct->setInputMask("999999");
	sequentialProduct->setMaxLength(6);
        sequentialProduct->setSizePolicy(QSizePolicy::Expanding,QSizePolicy::Expanding);

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

	returnButton                         = new QPushButton(this);
	returnButton->setText                ("Voltar");
	returnButton->setSizePolicy         (QSizePolicy::Expanding,QSizePolicy::Expanding);
	QObject::connect(returnButton, SIGNAL(clicked()),this, SLOT(returnStoreManagement_clickedSlot_clickedSlot()));

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
	mGridLayout->addWidget(sequentialLabel);
	mGridLayout->addWidget(sequentialProduct);
	mGridLayout->addWidget(barcodeLabel);
	mGridLayout->addWidget(barcodeProduct);
	mGridLayout->addWidget(countProdLabel);
	mGridLayout->addWidget(countProduct);
	mGridLayout->addWidget(valueProdLabel);
	mGridLayout->addWidget(valueProduct);
	mGridLayout->addWidget(finishOrderButton);
	mGridLayout->addWidget(returnButton);

	show();
}

void AdminModule::returnStoreManagement_clickedSlot_clickedSlot()
{
	mGridLayout->removeWidget(branchLabel);
	mGridLayout->removeWidget(branchs_comboBox);
	mGridLayout->removeWidget(operatorLabel);
	mGridLayout->removeWidget(operator_comboBox);
	mGridLayout->removeWidget(operationLabel);
	mGridLayout->removeWidget(operation_comboBox);
	mGridLayout->removeWidget(clientLabel);
	mGridLayout->removeWidget(txtClientInfos);
	mGridLayout->removeWidget(descProdLabel);
	mGridLayout->removeWidget(descProduct);
	mGridLayout->removeWidget(sequentialLabel);
	mGridLayout->removeWidget(sequentialProduct);
	mGridLayout->removeWidget(barcodeLabel);
	mGridLayout->removeWidget(barcodeProduct);
	mGridLayout->removeWidget(countProdLabel);
	mGridLayout->removeWidget(countProduct);
	mGridLayout->removeWidget(valueProdLabel);
	mGridLayout->removeWidget(valueProduct);
	mGridLayout->removeWidget(finishOrderButton);
	mGridLayout->removeWidget(returnButton);

	delete branchLabel;
	delete branchs_comboBox;
	delete operatorLabel;
	delete operator_comboBox;
	delete operationLabel;
	delete operation_comboBox;
	delete clientLabel;
	delete txtClientInfos;
	delete descProdLabel;
	delete descProduct;
	delete sequentialLabel;
	delete sequentialProduct;
	delete barcodeLabel;
	delete barcodeProduct;
	delete countProdLabel;
	delete countProduct;
	delete valueProdLabel;
	delete valueProduct;
	delete finishOrderButton;
	delete returnButton;

	// return to administrator screen
	Execute();
}


void AdminModule::destroyInitialAdminScreen()
{
	mGridLayout->removeWidget(branchManagementButton);
	mGridLayout->removeWidget(productsManagementButton);
	mGridLayout->removeWidget(listManagementButton);
	mGridLayout->removeWidget(reportsManagementButton);
	mGridLayout->removeWidget(exitButton);

	delete exitButton;
        delete branchManagementButton;
        delete productsManagementButton;
        delete reportsManagementButton;
	delete listManagementButton;
}

QGroupBox * AdminModule::createFirstExclusiveGroup()
{
    QGroupBox *groupBox = new QGroupBox(tr("Exclusive Radio Buttons"));

    QRadioButton *radio1 = new QRadioButton(tr("Todos produtos com mais de: "), this);
    QRadioButton *radio2 = new QRadioButton(tr("Todos produtos com menos de:"), this);
    QObject::connect(radio1, SIGNAL(clicked(bool)), this, SLOT(superlativeRadioButtonBigger_onToggled(bool)));
    QObject::connect(radio2, SIGNAL(clicked(bool)), this, SLOT(superlativeRadioButtonLess_onToggled(bool)));

    radio1->setChecked(true);
    QVBoxLayout *vbox = new QVBoxLayout;
    vbox->addWidget(radio1);
    vbox->addWidget(radio2);
    vbox->addStretch(1);
    groupBox->setLayout(vbox);

    return groupBox;
}

void AdminModule::superlativeRadioButtonBigger_onToggled(bool)
{
	cout << "Toogled Bigger: " <<  endl;
}

void AdminModule::superlativeRadioButtonLess_onToggled(bool)
{
	cout << "Toogled Less: " << endl;
}

void AdminModule::pre_optionsListManagement_clickedSlot()
{
	destroyInitialAdminScreen();
	optionsListManagement_clickedSlot();
}

void AdminModule::optionsListManagement_clickedSlot()
{
	productsManagementButton                      = new QPushButton(this);
	productsManagementButton->setText             ("Listagem de Produtos com Quantidades Superlativas");
	productsManagementButton->setSizePolicy       (QSizePolicy::Expanding,QSizePolicy::Expanding);
	QObject::connect(productsManagementButton, SIGNAL(clicked()),this, SLOT(pre_listSuperlativeManagement_clickedSlot()));

	listManagementButton                      = new QPushButton(this);
	listManagementButton->setText             ("Listar Pedidos e Seus Itens");
	listManagementButton->setSizePolicy       (QSizePolicy::Expanding,QSizePolicy::Expanding);
	QObject::connect(listManagementButton, SIGNAL(clicked()),this, SLOT(pre_storeManagement_clickedSlot()));

	reportsManagementButton                       = new QPushButton(this);
	reportsManagementButton->setText              ("Consulta sumarizada");
	reportsManagementButton->setSizePolicy        (QSizePolicy::Expanding,QSizePolicy::Expanding);
	QObject::connect(reportsManagementButton, SIGNAL(clicked()),this, SLOT(reportOrders_clickedSlot()));

	mGridLayout->addWidget(productsManagementButton);
	mGridLayout->addWidget(listManagementButton);
	mGridLayout->addWidget(reportsManagementButton);

	show();
}


void AdminModule::pre_listSuperlativeManagement_clickedSlot()
{
	destroyOptionsListManagementScreen();
	listSuperlativeManagement_clickedSlot();
}

void AdminModule::listSuperlativeManagement_clickedSlot()
{

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

	groupBox = createFirstExclusiveGroup();

        countProduct = new QLineEdit(this);
        countProduct->setSizePolicy(QSizePolicy::Expanding,QSizePolicy::Expanding);
	countProduct->setInputMask("999");
	countProduct->setMaxLength(3);

	execSearchSuperlative                         = new QPushButton(this);
	execSearchSuperlative->setText                ("Pesquisar");
	execSearchSuperlative->setSizePolicy         (QSizePolicy::Expanding,QSizePolicy::Expanding);
	// method para ja fazer a busca
	QObject::connect(execSearchSuperlative, SIGNAL(clicked()),this, SLOT(reportSuperlativeSearch_clickedSlot()));

	returnSearchSuperlative                         = new QPushButton(this);
	returnSearchSuperlative->setText                ("Voltar");
	returnSearchSuperlative->setSizePolicy         (QSizePolicy::Expanding,QSizePolicy::Expanding);
	QObject::connect(returnSearchSuperlative, SIGNAL(clicked()),this, SLOT(returnFromSuperlative_clickedSlot()));

	mGridLayout->addWidget(branchLabel);
	mGridLayout->addWidget(branchs_comboBox);
	mGridLayout->addWidget(groupBox);
	mGridLayout->addWidget(countProduct);
	mGridLayout->addWidget(execSearchSuperlative);
	mGridLayout->addWidget(returnSearchSuperlative);

}

void AdminModule::returnFromSuperlative_clickedSlot()
{
	mGridLayout->removeWidget(branchLabel);
	mGridLayout->removeWidget(branchs_comboBox);
	mGridLayout->removeWidget(groupBox);
	mGridLayout->removeWidget(countProduct);
	mGridLayout->removeWidget(execSearchSuperlative);
	mGridLayout->removeWidget(returnSearchSuperlative);

	delete branchLabel;
	delete branchs_comboBox;
	delete groupBox;
	delete countProduct;
	delete execSearchSuperlative;
	delete returnSearchSuperlative;

}

void AdminModule::reportSuperlativeSearch_clickedSlot()
{
	QString branchs_field = branchs_comboBox->currentText();
	if(branchs_field.isEmpty())
	{
		warningMessage("Necessário escolher uma filial!");
		return;
	}

	//cout <<  "ID do checked: " << groupBox->checkedId() << endl;; 

	QMessageBox msgBox;
	msgBox.setText("Aqui vai ser mostrado o resultado da consulta!");
	msgBox.exec();
}

void AdminModule::reportOrders_clickedSlot()
{
}

void AdminModule::exit_clickedSlot()
{
}
