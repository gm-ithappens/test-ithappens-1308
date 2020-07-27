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
	QObject::connect(listManagementButton, SIGNAL(clicked()),this, SLOT(pre_optionsGeneralListManagement_clickedSlot()));


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

void AdminModule::pre_branchManagement_clickedSlot()
{
	destroyOptionsListManagementScreen();
	branchManagement_clickedSlot();
}

void AdminModule::branchManagement_clickedSlot()
{
	generalLabel      = new QLabel("Nome para nova filial: ");

        generalEdit       = new QLineEdit(this);
        generalEdit->setSizePolicy(QSizePolicy::Expanding,QSizePolicy::Expanding);

	execTaskButton                         = new QPushButton(this);
	execTaskButton->setText                ("Criar filial!");
	execTaskButton->setSizePolicy         (QSizePolicy::Expanding,QSizePolicy::Expanding);
	QObject::connect(execTaskButton, SIGNAL(clicked()),this, SLOT(doCreateNewBranch_clickedSlot()));

	returnButton                         = new QPushButton(this);
	returnButton->setText                ("Voltar");
	returnButton->setSizePolicy         (QSizePolicy::Expanding,QSizePolicy::Expanding);
	QObject::connect(returnButton, SIGNAL(clicked()),this, SLOT(returnBranchManagement_clickedSlot()));

	mGridLayout->addWidget(generalLabel);
	mGridLayout->addWidget(generalEdit);
	mGridLayout->addWidget(execTaskButton);
	mGridLayout->addWidget(returnButton);

	show();
}

void AdminModule::doCreateNewBranch_clickedSlot()
{
	QString branch_name = generalEdit->text();
	if(isSettedVariable(branch_name, "Necessário dá um nome a filial!") == 0)
		return;

	db_instance->createBranchCompanyTable(branch_name.toStdString());

	warningMessage("Filial criada com sucesso!");

	returnBranchManagement_clickedSlot();
}

void AdminModule::returnBranchManagement_clickedSlot()
{
	mGridLayout->removeWidget(generalLabel);
	mGridLayout->removeWidget(generalEdit);
	mGridLayout->removeWidget(execTaskButton);
	mGridLayout->removeWidget(returnButton);
	delete execTaskButton;
	delete returnButton;
	delete generalLabel;
	delete generalEdit;

	Execute();
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
	if(isSettedVariable(branchs_field, "Necessário escolher uma filial!") == 0)
		return;

	QString client_field = txtClientInfos->text();
	if(isSettedVariable(client_field, "Necessário informar os dados do cliente!") == 0)
		return;

	QString operation_comboBox = txtClientInfos->text();
	if(isSettedVariable(operation_comboBox, "Necessário informar o tipo de operação!") == 0)
		return;

	QString operator_field = operator_comboBox->currentText();
	if(isSettedVariable(operator_field, "Necessário informar operador!") == 0)
		return;

	QString desc_product    = descProduct->text();
	if(isSettedVariable(desc_product, "Necessário informar descrição do produto!")  == 0)
		return;

	QString seq_product    = sequentialProduct->text();
	if(isSettedVariable(seq_product, "Necessário informar sequencial do produto!") == 0)
		return;

	QString barcode_product = barcodeProduct->text();
	if(isSettedVariable(barcode_product, "Necessário informar código de barras (6 dígitos)!")  == 0)
		return;

	QString count_product   = countProduct->text();
	if(isSettedVariable(count_product, "Necessário informar a quantidade!") == 0)
		return;

	QString value_product   = valueProduct->text();
	if(isSettedVariable(value_product, "Necessário informar a quantidade!")  == 0)
		return;

	QString obs = "NECESSARIO TROCAR ISSO";

	neworder = new OrderModule(branchs_field, client_field, operator_field, obs, INPUT_ORDER);

	Product * line_product = NULL;
	QString search_mode = "BARCODE";
	line_product = db_instance->searchProductOnBranch(neworder->branchs_field.toStdString(), 
							search_mode.toStdString(), 
							barcode_product.toStdString());

	int processing_type = UPDATE_PRODUCT;
	if(line_product->status_in_db == NOT_FOUND_IN_DB)
	{
		cout << "Vai ser criado um novo produto!" << endl;
		Product * line_product = new Product();
		processing_type        = NEW_PRODUCT;
	}

	line_product->description     = desc_product;
	line_product->barcode         = barcode_product;
	line_product->sequential      = seq_product.toInt();
	line_product->count_requested = count_product.toInt();
	line_product->unit_value      = value_product.toInt();
	line_product->total_value     = line_product->count_requested * line_product->unit_value;
	neworder->payment_mode        = INPUT_NO_PAYMENT;

	neworder->Products[line_product->description] = line_product;

	neworder->ProcessingOrder(INPUT_ORDER, processing_type);
}

void AdminModule::destroyOptionsGeneralListManagementScreen()
{
	mGridLayout->removeWidget(productsManagementButton);
	mGridLayout->removeWidget(listManagementButton);
	mGridLayout->removeWidget(reportsManagementButton);
	mGridLayout->removeWidget(listManagPaymentButton);

	delete listManagPaymentButton;
	delete productsManagementButton;
	delete listManagementButton;
	delete reportsManagementButton;
}

void AdminModule::destroyOptionsListManagementScreen()
{
	mGridLayout->removeWidget(branchManagementButton);
	mGridLayout->removeWidget(productsManagementButton);
	mGridLayout->removeWidget(listManagementButton);
	mGridLayout->removeWidget(productsManagementButton);
	mGridLayout->removeWidget(exitButton);

	delete branchManagementButton;
	delete productsManagementButton;
	delete listManagementButton;
	delete reportsManagementButton;
	delete exitButton;
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
	branchs_comboBox = mountComboBoxBranchNames();

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

QGroupBox * AdminModule::createExclusiveGroup()
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

	superlative_radio_option = BIGGER_THAN;
}

void AdminModule::superlativeRadioButtonLess_onToggled(bool)
{
	cout << "Toogled Less: " << endl;
	superlative_radio_option = LESS_THAN;
}

void AdminModule::pre_optionsGeneralListManagement_clickedSlot()
{
	destroyOptionsListManagementScreen();
	optionsGeneralListManagement_clickedSlot();
}

void AdminModule::optionsGeneralListManagement_clickedSlot()
{
	productsManagementButton                      = new QPushButton(this);
	productsManagementButton->setText             ("Listagem de Produtos com Quantidades Superlativas");
	productsManagementButton->setSizePolicy       (QSizePolicy::Expanding,QSizePolicy::Expanding);
	QObject::connect(productsManagementButton, SIGNAL(clicked()),this, SLOT(pre_listSuperlativeManagement_clickedSlot()));

	listManagementButton                      = new QPushButton(this);
	listManagementButton->setText             ("Listar Pedidos e Seus Itens");
	listManagementButton->setSizePolicy       (QSizePolicy::Expanding,QSizePolicy::Expanding);
	QObject::connect(listManagementButton, SIGNAL(clicked()),this, SLOT(pre_listOrdersManagement_clickedSlot()));

	listManagPaymentButton                      = new QPushButton(this);
	listManagPaymentButton->setText             ("Listar Pedidos e Forma de Pagamento");
	listManagPaymentButton->setSizePolicy       (QSizePolicy::Expanding,QSizePolicy::Expanding);
	QObject::connect(listManagPaymentButton, SIGNAL(clicked()),this, SLOT(pre_listOrdersPaymentManagement_clickedSlot()));

	reportsManagementButton                       = new QPushButton(this);
	reportsManagementButton->setText              ("Consulta sumarizada");
	reportsManagementButton->setSizePolicy        (QSizePolicy::Expanding,QSizePolicy::Expanding);
	QObject::connect(listManagPaymentButton, SIGNAL(clicked()),this, SLOT(pre_listOrdersPaymentManagement_clickedSlot()));

	mGridLayout->addWidget(listManagPaymentButton);
	mGridLayout->addWidget(productsManagementButton);
	mGridLayout->addWidget(listManagementButton);
	mGridLayout->addWidget(reportsManagementButton);

	show();
}

void AdminModule::pre_listOrdersPaymentManagement_clickedSlot()
{
	destroyOptionsGeneralListManagementScreen();
	listOrdersPaymentManagement_clickedSlot();
}


QComboBox * AdminModule::mountComboBoxBranchNames()
{
        // Options to branch company
        QComboBox * cbox = new QComboBox;

        cbox->addItem(tr(""));
        vector<string> list = db_instance->getListBranchCompany();
        vector<string>::const_iterator iter;
        for (iter = list.begin(); iter != list.end(); ++iter)
        {
                string s;
                s = *iter;
                cbox->addItem(tr(s.c_str()));
        }
        list.clear();

	return cbox;
}

void AdminModule::listOrdersPaymentManagement_clickedSlot()
{
	branchLabel = new QLabel("Qual filial: ");

	branchs_comboBox = mountComboBoxBranchNames();

	execSearchlistOrders                         = new QPushButton(this);
	execSearchlistOrders->setText                ("Pesquisar");
	execSearchlistOrders->setSizePolicy         (QSizePolicy::Expanding,QSizePolicy::Expanding);
	QObject::connect(execSearchlistOrders, SIGNAL(clicked()),this, SLOT(reportSearchlistOrdersPayment_clickedSlot()));

	returnSearchlistOrders                         = new QPushButton(this);
	returnSearchlistOrders->setText                ("Voltar");
	returnSearchlistOrders->setSizePolicy         (QSizePolicy::Expanding,QSizePolicy::Expanding);
	QObject::connect(returnSearchlistOrders, SIGNAL(clicked()),this, SLOT(returnlistOrdersPaymentManagement_clickedSlot()));

	mGridLayout->addWidget(branchLabel);
	mGridLayout->addWidget(branchs_comboBox);
	mGridLayout->addWidget(execSearchlistOrders);
	mGridLayout->addWidget(returnSearchlistOrders);

	show();
}

void AdminModule::returnlistOrdersPaymentManagement_clickedSlot()
{
	mGridLayout->removeWidget(branchLabel);
	mGridLayout->removeWidget(branchs_comboBox);
	mGridLayout->removeWidget(execSearchlistOrders);
	mGridLayout->removeWidget(returnSearchlistOrders);

	delete branchLabel;
	delete branchs_comboBox;
	delete execSearchlistOrders;
	delete returnSearchlistOrders;

	optionsGeneralListManagement_clickedSlot();
}

void AdminModule::reportSearchlistOrdersPayment_clickedSlot()
{
	QHash<QString, ProductOfOrder *> HTOrder;
	ProductOfOrder * order;
	QString out;

	QString branchs_field = branchs_comboBox->currentText();
	if(isSettedVariable(branchs_field, "Necessário escolher uma filial!")  == 0)
		return;

	HTOrder = db_instance->searchListOrderAndPaymentOnBranch(branchs_field.toStdString());

	QHashIterator<QString, ProductOfOrder *> iter(HTOrder);

        QString key;
	out.append(" HASH   - PAGAMENTO\n");
        while (iter.hasNext())
        {
                iter.next();
                order   = (ProductOfOrder *) iter.value();
                key     = (QString) iter.key();

		out.append(order->hash_session.toStdString().c_str());
		out.append("      -     ");
		out.append(QString("%1").arg(order->payment_mode).toStdString().c_str());
		out.append("\n");
	}

	warningMessage(out.toStdString().c_str());
}


void AdminModule::pre_listOrdersManagement_clickedSlot()
{
	destroyOptionsGeneralListManagementScreen();
	listOrdersManagement_clickedSlot();
}

void AdminModule::listOrdersManagement_clickedSlot()
{
	branchLabel = new QLabel("Qual filial: ");

        // Options to branch company
	branchs_comboBox = mountComboBoxBranchNames();

	sequentialLabel = new QLabel("Entre com código sequencial: ");
        sequentialProduct = new QLineEdit(this);
	sequentialProduct->setInputMask("999999");
	sequentialProduct->setMaxLength(6);
        sequentialProduct->setSizePolicy(QSizePolicy::Expanding,QSizePolicy::Expanding);

	execSearchlistOrders                         = new QPushButton(this);
	execSearchlistOrders->setText                ("Pesquisar");
	execSearchlistOrders->setSizePolicy         (QSizePolicy::Expanding,QSizePolicy::Expanding);
	QObject::connect(execSearchlistOrders, SIGNAL(clicked()),this, SLOT(reportSearchlistOrders_clickedSlot()));

	returnSearchlistOrders                         = new QPushButton(this);
	returnSearchlistOrders->setText                ("Voltar");
	returnSearchlistOrders->setSizePolicy         (QSizePolicy::Expanding,QSizePolicy::Expanding);
	QObject::connect(returnSearchlistOrders, SIGNAL(clicked()),this, SLOT(returnSearchlistOrders_clickedSlot()));

	mGridLayout->addWidget(branchLabel);
	mGridLayout->addWidget(branchs_comboBox);
	mGridLayout->addWidget(sequentialLabel);
	mGridLayout->addWidget(sequentialProduct);
	mGridLayout->addWidget(execSearchlistOrders);
	mGridLayout->addWidget(returnSearchlistOrders);
}

void AdminModule::returnSearchlistOrders_clickedSlot()
{
	mGridLayout->removeWidget(branchLabel);
	mGridLayout->removeWidget(branchs_comboBox);
	mGridLayout->removeWidget(sequentialLabel);
	mGridLayout->removeWidget(sequentialProduct);
	mGridLayout->removeWidget(execSearchlistOrders);
	mGridLayout->removeWidget(returnSearchlistOrders);

	delete branchLabel;
	delete branchs_comboBox;
	delete sequentialLabel;
	delete sequentialProduct;
	delete execSearchlistOrders;
	delete returnSearchlistOrders;

	optionsGeneralListManagement_clickedSlot();
}

void AdminModule::pre_listSuperlativeManagement_clickedSlot()
{
	destroyOptionsGeneralListManagementScreen();
	listSuperlativeManagement_clickedSlot();
}

void AdminModule::listSuperlativeManagement_clickedSlot()
{

	branchLabel = new QLabel("Qual filial: ");

        // Options to branch company
	branchs_comboBox = mountComboBoxBranchNames();

	groupBox = createExclusiveGroup();

        countProduct = new QLineEdit(this);
        countProduct->setSizePolicy(QSizePolicy::Expanding,QSizePolicy::Expanding);
	countProduct->setInputMask("999");
	countProduct->setMaxLength(3);

	execSearchSuperlative                         = new QPushButton(this);
	execSearchSuperlative->setText                ("Pesquisar");
	execSearchSuperlative->setSizePolicy         (QSizePolicy::Expanding,QSizePolicy::Expanding);
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

	optionsGeneralListManagement_clickedSlot();
}

int AdminModule::isSettedVariable(QString var, QString msgErr)
{
	if(var.isEmpty())
	{
		warningMessage(msgErr.toStdString().c_str());
		return 0;
	}

	return 1;
}

void AdminModule::reportSearchlistOrders_clickedSlot()
{
	QHash<QString, ProductOfOrder *> HTProductOfOrder;
	ProductOfOrder * order;
	QString out;

	QString branchs_field = branchs_comboBox->currentText();
	if(isSettedVariable(branchs_field, "Necessário escolher uma filial!")  == 0)
		return;

	QString sequential   = sequentialProduct->text();
	if(isSettedVariable(sequential, "Necessário informar o código sequencial!")  == 0)
		return;

	HTProductOfOrder = db_instance->searchListOrdersOnBranch(
							branchs_field.toStdString(),
							sequential.toInt());

	QHashIterator<QString, ProductOfOrder *> iter(HTProductOfOrder);

        QString key;
	out.append(" HASH  - DESCRIÇÃO - CÓD. BARRAS - QUANT. REQUISITADA  - " 
			"QUANT. CANCELADA - VAL. TOTAL - TIPO  - PAGAMENTO\n");
        while (iter.hasNext())
        {
                iter.next();
                order   = (ProductOfOrder *) iter.value();
                key     = (QString) iter.key();

		out.append(order->hash_session.toStdString().c_str());
		out.append("      -     ");
		out.append(order->description.toStdString().c_str());
		out.append("      -     ");
		out.append(order->barcode.toStdString().c_str());
		out.append("      -     ");
		out.append(QString("%1").arg(order->count_requested).toStdString().c_str());
		out.append("      -     ");
		out.append(QString("%1").arg(order->count_canceled).toStdString().c_str());
		out.append("      -     ");
		out.append(QString("%1").arg(order->total_value).toStdString().c_str());
		out.append("      -     ");
		out.append(QString("%1").arg(order->order_type).toStdString().c_str());
		out.append("      -     ");
		out.append(QString("%1").arg(order->payment_mode).toStdString().c_str());
		out.append("\n");
	}

	QMessageBox msgBox;
	msgBox.setText(out.toStdString().c_str());
	msgBox.exec();
}

void AdminModule::reportSuperlativeSearch_clickedSlot()
{
	QHash<QString, Product *> HTProducts;

	QString branchs_field = branchs_comboBox->currentText();
	if(isSettedVariable(branchs_field, "Necessário escolher uma filial!") == 0)
		return;

	QString count_product   = countProduct->text();
	if(isSettedVariable(count_product, "Necessário informar a quantidade!")  == 0)
		return;

	if(superlative_radio_option == BIGGER_THAN)
		HTProducts = db_instance->searchSuperProductOnBranch(branchs_field.toStdString(), BIGGER_THAN, count_product.toInt());
	else
		HTProducts = db_instance->searchSuperProductOnBranch(branchs_field.toStdString(), LESS_THAN, count_product.toInt());

	QString out;

	//else
	QHashIterator<QString, Product *> iter(HTProducts);
        Product * product;
        QString key;
        while (iter.hasNext())
        {
                iter.next();
                product = (Product *) iter.value();
                key     = (QString) iter.key();

                cout << key.toStdString() << ": " << product->description.toStdString() << endl;
		out.append(product->description.toStdString().c_str());
		out.append("      -     ");
		out.append(QString("%1").arg(product->count_available).toStdString().c_str());
		out.append("\n");
	}

	warningMessage(out.toStdString().c_str());
}

void AdminModule::reportOrders_clickedSlot()
{
}

void AdminModule::exit_clickedSlot()
{
}
