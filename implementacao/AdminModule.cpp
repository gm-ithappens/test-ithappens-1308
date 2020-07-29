#include "AdminModule.h"
#include <iostream>
#include "Common.h"

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

	branchManagementButton = mountButton("Gerenciar Filais");
	QObject::connect(branchManagementButton, SIGNAL(clicked()),this, SLOT(pre_branchManagement_clickedSlot()));

	productsManagementButton = mountButton("Gerenciar Estoque");
	QObject::connect(productsManagementButton, SIGNAL(clicked()),this, SLOT(pre_storeManagement_clickedSlot()));

	listManagementButton = mountButton("Listagem Geral");
	QObject::connect(listManagementButton, SIGNAL(clicked()),this, SLOT(pre_optionsGeneralListManagement_clickedSlot()));

	reportsManagementButton = mountButton("Verificar Pedidos");
	QObject::connect(reportsManagementButton, SIGNAL(clicked()),this, SLOT(reportOrders_clickedSlot()));

	exitButton = mountButton("Sair do sistema!");
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

	execTaskButton = mountButton("Criar filial!");
	QObject::connect(execTaskButton, SIGNAL(clicked()),this, SLOT(doCreateNewBranch_clickedSlot()));

	returnButton = mountButton("Voltar");
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

	QString * branch = db_instance->searchBranchCompanyTable(branch_name.toStdString());

	if(!branch->isEmpty())
	{
		warningMessage("Filial já cadastrada. Tente outro nome!");
		return;
	}

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

void AdminModule::tryAddProductToOrder_clickedSlot()
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

	if(!neworder)
		neworder = new OrderModule(branchs_field, client_field, operator_field, obs, INPUT_ORDER);

	Product * line_product = NULL;
	QString search_mode = "BARCODE";
	line_product = db_instance->searchProductOnBranch(neworder->branchs_field.toStdString(), 
							search_mode.toStdString(), 
							barcode_product.toStdString());

	neworder->processing_type = UPDATE_PRODUCT;
	if(line_product->status_in_db == NOT_FOUND_IN_DB)
	{
		cout << "Vai ser criado um novo produto!" << endl;
		Product * line_product    = new Product();
		neworder->processing_type = NEW_PRODUCT;
	}

	neworder->client_field        = client_field;
	neworder->operator_field      = operator_field;
	neworder->obs_client_infos    = "Operação de entrada!";

	line_product->description     = desc_product;
	line_product->barcode         = barcode_product;
	line_product->sequential      = seq_product.toInt();
	line_product->count_requested = count_product.toInt();
	line_product->unit_value      = value_product.toInt();
	line_product->total_value     = line_product->count_requested * line_product->unit_value;
	neworder->payment_mode        = INPUT_NO_PAYMENT;

	neworder->Products[line_product->description] = line_product;

	neworder->updateTotalValue(line_product->total_value);
	neworder->updateTotalitens(line_product->count_requested);

	clearScreenRegistreProduct_clickedSlot();

	warningMessage("Produto adicionado ao pedido com sucesso!");
}

void AdminModule::finishOrder_clickedSlot()
{
	if(!neworder)
	{
		warningMessage("Nenhum produto cadastrado!");
		return;
	}
	if(neworder->Products.size() == 0)
	{
		warningMessage("Nenhum produto cadastrado!");
		return;
	}

	neworder->ProcessingOrder(INPUT_ORDER, neworder->processing_type);

	warningMessage("Pedido registrado com sucesso!");

	delete neworder;

	returnStoreManagement_clickedSlot_clickedSlot();
}

void AdminModule::destroyOptionsGeneralListManagementScreen()
{
	mGridLayout->removeWidget(productsManagementButton);
	mGridLayout->removeWidget(listManagementButton);
	mGridLayout->removeWidget(listManagPaymentButton);
	mGridLayout->removeWidget(reportResumeXPlusButton);
	mGridLayout->removeWidget(resumeSperlativeButton);

	delete listManagPaymentButton;
	delete productsManagementButton;
	delete listManagementButton;
	delete reportResumeXPlusButton;
	delete resumeSperlativeButton;
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
	neworder = NULL;

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

	addMoreButton = mountButton("Adicionar produto ao pedido");
	QObject::connect(addMoreButton, SIGNAL(clicked()),this, SLOT(tryAddProductToOrder_clickedSlot()));

	finishOrderButton = mountButton("FECHAR PEDIDO");
	QObject::connect(finishOrderButton, SIGNAL(clicked()),this, SLOT(finishOrder_clickedSlot()));

	returnButton = mountButton("Voltar");
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
	mGridLayout->addWidget(addMoreButton);
	mGridLayout->addWidget(finishOrderButton);
	mGridLayout->addWidget(returnButton);

	show();
}

void AdminModule::clearScreenRegistreProduct_clickedSlot()
{
	descProduct->clear();
	sequentialProduct->clear();
	barcodeProduct->clear();
	countProduct->clear();
	valueProduct->clear();

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
	mGridLayout->removeWidget(addMoreButton);
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
	delete addMoreButton;

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

	superlative_radio_option = BIGGER_THAN;

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
	productsManagementButton     = mountButton("Listagem de Produtos com Quantidades Superlativas");
	QObject::connect(productsManagementButton, SIGNAL(clicked()),this, SLOT(pre_listSuperlativeManagement_clickedSlot()));

	listManagementButton         = mountButton("Listar Pedidos e Seus Itens");
	QObject::connect(listManagementButton, SIGNAL(clicked()),this, SLOT(pre_listOrdersManagement_clickedSlot()));

	listManagPaymentButton       = mountButton("Listar Pedidos e Forma de Pagamento");
	QObject::connect(listManagPaymentButton, SIGNAL(clicked()),this, SLOT(pre_listOrdersPaymentManagement_clickedSlot()));

	reportResumeXPlusButton      = mountButton("Consulta sumarizada - Total com soma");
	QObject::connect(reportResumeXPlusButton, SIGNAL(clicked()),this, SLOT(pre_ResumeXPlusButtonManagement_clickedSlot()));

	resumeSperlativeButton       = mountButton("Consulta sumarizada - Pedidos Superlativo");
	QObject::connect(resumeSperlativeButton, SIGNAL(clicked()),this, SLOT(pre_ResumeSperlativeManagement_clickedSlot()));

	mGridLayout->addWidget(productsManagementButton);
	mGridLayout->addWidget(listManagementButton);
	mGridLayout->addWidget(listManagPaymentButton);
	mGridLayout->addWidget(reportResumeXPlusButton);
	mGridLayout->addWidget(resumeSperlativeButton);

	show();
}

void AdminModule::pre_ResumeSperlativeManagement_clickedSlot()
{
	destroyOptionsGeneralListManagementScreen();
	ResumeSuperlativeManagement_clickedSlot();
}

void AdminModule::ResumeSuperlativeManagement_clickedSlot()
{
	branchLabel = new QLabel("Qual filial: ");

        // Options to branch company
	branchs_comboBox = mountComboBoxBranchNames();

	generalLabel = new QLabel("Quantidade de itens? ");

        countProduct = new QLineEdit(this);
        countProduct->setSizePolicy(QSizePolicy::Expanding,QSizePolicy::Expanding);
	countProduct->setInputMask("999");
	countProduct->setMaxLength(3);

	execSearch = mountButton("Pesquisar");
	QObject::connect(execSearch, SIGNAL(clicked()),this, SLOT(reportResumeSperlativeManagement_clickedSlot()));

	returnButton = mountButton("Voltar");
	QObject::connect(returnButton, SIGNAL(clicked()),this, SLOT(returnResumeSuperlativeManagement_clickedSlot()));

	mGridLayout->addWidget(branchLabel);
	mGridLayout->addWidget(branchs_comboBox);
	mGridLayout->addWidget(generalLabel);
	mGridLayout->addWidget(countProduct);
	mGridLayout->addWidget(execSearch);
	mGridLayout->addWidget(returnButton);

	show();
}

void AdminModule::reportResumeSperlativeManagement_clickedSlot()
{
	QList<QString> * ql;
	QString out;
	QString hashsession;

	QString branchs_field = branchs_comboBox->currentText();
	if(isSettedVariable(branchs_field, "Necessário escolher uma filial!") == 0)
		return;

	QString count_product   = countProduct->text();
	if(isSettedVariable(count_product, "Necessário informar a quantidade!")  == 0)
		return;

	ql = db_instance->searchResumedOrdersSuperlative(branchs_field.toStdString(), 
							 count_product.toInt());

	out.append("Pedidos com mais que ");
	out.append(QString("%1").arg(count_product.toStdString().c_str()));
	out.append(" itens:\n\n\n");

	out.append("PEDIDO : TOTAL ITENS\n");
	out.append("--------------------\n");
	for (int i = 0; i < ql->size(); ++i)
	{
                hashsession = ql->at(i);

                cout <<  ": " << hashsession.toStdString() << endl;
		out.append(hashsession.toStdString().c_str());

		if(i%2)
			out.append("\n");
		else
			out.append(" : ");
	}

	warningMessage(out.toStdString().c_str());

	returnResumeSuperlativeManagement_clickedSlot();
}

void AdminModule::returnResumeSuperlativeManagement_clickedSlot()
{
	mGridLayout->removeWidget(branchLabel);
	mGridLayout->removeWidget(generalLabel);
	mGridLayout->removeWidget(branchs_comboBox);
	mGridLayout->removeWidget(execSearch);
	mGridLayout->removeWidget(returnButton);
	mGridLayout->removeWidget(countProduct);

	delete countProduct;
	delete generalLabel;
	delete branchLabel;
	delete branchs_comboBox;
	delete execSearch;
	delete returnButton;

	optionsGeneralListManagement_clickedSlot();
}



void AdminModule::pre_ResumeXPlusButtonManagement_clickedSlot()
{
	destroyOptionsGeneralListManagementScreen();
	ResumeXPlusButtonManagement_clickedSlot();
}

void AdminModule::branchChoosedChangedMountOrdersList()
{
	QString s;
	QString branchs_field = branchs_comboBox->currentText();

	cboxOrder->clear();

        QList<QString> * ql = db_instance->searchOrdersHashofBranch(branchs_field.toStdString());
	for (int i = 0; i < ql->size(); ++i)
	{
                s = ql->at(i);
                cboxOrder->addItem(tr(s.toStdString().c_str()));
	}

	show();
}

void AdminModule::ResumeXPlusButtonManagement_clickedSlot()
{
	branchLabel = new QLabel("Qual filial: ");

        // Options to branch company
	branchs_comboBox = mountComboBoxBranchNames();
	connect(branchs_comboBox, SIGNAL(currentIndexChanged(int)), this, SLOT(branchChoosedChangedMountOrdersList()));

        // Options to orders in database
        cboxOrder = new QComboBox;

        cboxOrder->addItem(tr("Código do pedido (hash): "));

	execSearch = mountButton("Pesquisar");
	QObject::connect(execSearch, SIGNAL(clicked()),this, SLOT(reportResumeXPlusButtonManagement_clickedSlot()));

	returnButton = mountButton("Voltar");
	QObject::connect(returnButton, SIGNAL(clicked()),this, SLOT(returnResumeXPlusButtonManagement_clickedSlot()));

	mGridLayout->addWidget(branchLabel);
	mGridLayout->addWidget(branchs_comboBox);
	mGridLayout->addWidget(cboxOrder);
	mGridLayout->addWidget(execSearch);
	mGridLayout->addWidget(returnButton);

	show();
}


void AdminModule::reportResumeXPlusButtonManagement_clickedSlot()
{
        QHash<QString, ProductOfOrder *> HTProductOfOrder;
	Order * order;
	QString out;
	ProductOfOrder * product_order;
	int value_total = 0;
	int cancel_value_total = 0;

	QString branchs_field = branchs_comboBox->currentText();
	if(isSettedVariable(branchs_field, "Necessário escolher uma filial!")  == 0)
		return;

	QString hashsession = cboxOrder->currentText();

	// Retrieve order of hash
        order = db_instance->searchOneOrderHashofBranch(
				branchs_field.toStdString(), 
				hashsession.toStdString());
	
	// Retrieve all products of hash
	HTProductOfOrder = db_instance->searchListOrdersProductHashOnBranch(
					branchs_field.toStdString(), 
					hashsession.toStdString());

	cout  << "Valor total: " << order->total_value << endl;
	out.append("Valor total (Tabela Pedido): ");
	out.append(QString("%1\n\n").arg(order->total_value).toStdString().c_str());
	out.append("Valor dos produtos:\n");

	QHashIterator<QString, ProductOfOrder *> iter(HTProductOfOrder);

        QString key;
        while (iter.hasNext())
        {
                iter.next();
                product_order   = (ProductOfOrder *) iter.value();
                key             = (QString) iter.key();

		cout << "   ->   " << product_order->total_value << endl;
		out.append(product_order->description.toStdString().c_str());
		value_total += product_order->total_value;

		if(product_order->count_canceled)
		{
			out.append(" :  -");
			cancel_value_total += product_order->total_value;
		}
		else
		{
			out.append(" :  +");
		}
		out.append(QString("%1\n").arg(product_order->total_value).toStdString().c_str());
	}
	out.append("\nValor somado: ");
	out.append(QString("%1\n").arg(value_total).toStdString().c_str());
	out.append("\nValor cancelado: ");
	out.append(QString("%1\n").arg(cancel_value_total).toStdString().c_str());
	out.append("");

	warningMessage(out.toStdString().c_str());
}

void AdminModule::destroyResumeXPlusButtonManagement()
{
	mGridLayout->removeWidget(branchLabel);
	mGridLayout->removeWidget(branchs_comboBox);
	mGridLayout->removeWidget(cboxOrder);
	mGridLayout->removeWidget(execSearch);
	mGridLayout->removeWidget(returnButton);

	delete branchLabel;
	delete branchs_comboBox;
	delete execSearch;
	delete returnButton;
	delete cboxOrder;

}

void AdminModule::returnResumeXPlusButtonManagement_clickedSlot()
{
	destroyResumeXPlusButtonManagement();
	optionsGeneralListManagement_clickedSlot();
}


void AdminModule::pre_listOrdersPaymentManagement_clickedSlot()
{
	destroyOptionsGeneralListManagementScreen();
	listOrdersPaymentManagement_clickedSlot();
}


QComboBox * AdminModule::mountComboBoxBranchNames()
{
	QString s;

        // Options to branch company
        QComboBox * cbox = new QComboBox;

        cbox->addItem(tr(""));
        QList<QString> * ql = db_instance->getListBranchCompany();
	for (int i = 0; i < ql->size(); ++i)
	{
                s = ql->at(i);

                cbox->addItem(tr(s.toStdString().c_str()));
        }

	return cbox;
}

void AdminModule::listOrdersPaymentManagement_clickedSlot()
{
	branchLabel = new QLabel("Qual filial: ");

	branchs_comboBox = mountComboBoxBranchNames();

	execSearch = mountButton("Pesquisar");
	QObject::connect(execSearch, SIGNAL(clicked()),this, SLOT(reportSearchlistOrdersPayment_clickedSlot()));

	returnButton = mountButton("Voltar");
	QObject::connect(returnButton, SIGNAL(clicked()),this, SLOT(returnlistOrdersPaymentManagement_clickedSlot()));

	mGridLayout->addWidget(branchLabel);
	mGridLayout->addWidget(branchs_comboBox);
	mGridLayout->addWidget(execSearch);
	mGridLayout->addWidget(returnButton);

	show();
}

void AdminModule::returnlistOrdersPaymentManagement_clickedSlot()
{
	mGridLayout->removeWidget(branchLabel);
	mGridLayout->removeWidget(branchs_comboBox);
	mGridLayout->removeWidget(execSearch);
	mGridLayout->removeWidget(returnButton);

	delete branchLabel;
	delete branchs_comboBox;
	delete execSearch;
	delete returnButton;

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

	execSearch = mountButton("Pesquisar");
	QObject::connect(execSearch, SIGNAL(clicked()),this, SLOT(reportSearchlistOrders_clickedSlot()));

	returnButton = mountButton("Voltar");
	QObject::connect(returnButton, SIGNAL(clicked()),this, SLOT(returnSearchlistOrders_clickedSlot()));

	mGridLayout->addWidget(branchLabel);
	mGridLayout->addWidget(branchs_comboBox);
	mGridLayout->addWidget(sequentialLabel);
	mGridLayout->addWidget(sequentialProduct);
	mGridLayout->addWidget(execSearch);
	mGridLayout->addWidget(returnButton);
}

void AdminModule::returnSearchlistOrders_clickedSlot()
{
	mGridLayout->removeWidget(branchLabel);
	mGridLayout->removeWidget(branchs_comboBox);
	mGridLayout->removeWidget(sequentialLabel);
	mGridLayout->removeWidget(sequentialProduct);
	mGridLayout->removeWidget(execSearch);
	mGridLayout->removeWidget(returnButton);

	delete branchLabel;
	delete branchs_comboBox;
	delete sequentialLabel;
	delete sequentialProduct;
	delete execSearch;
	delete returnButton;

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

	execSearch = mountButton("Pesquisar");
	QObject::connect(execSearch, SIGNAL(clicked()),this, SLOT(reportSuperlativeSearch_clickedSlot()));

	returnButton = mountButton("Voltar");
	QObject::connect(returnButton, SIGNAL(clicked()),this, SLOT(returnFromSuperlative_clickedSlot()));

	mGridLayout->addWidget(branchLabel);
	mGridLayout->addWidget(branchs_comboBox);
	mGridLayout->addWidget(groupBox);
	mGridLayout->addWidget(countProduct);
	mGridLayout->addWidget(execSearch);
	mGridLayout->addWidget(returnButton);

}

void AdminModule::returnFromSuperlative_clickedSlot()
{
	mGridLayout->removeWidget(branchLabel);
	mGridLayout->removeWidget(branchs_comboBox);
	mGridLayout->removeWidget(groupBox);
	mGridLayout->removeWidget(countProduct);
	mGridLayout->removeWidget(execSearch);
	mGridLayout->removeWidget(returnButton);

	delete branchLabel;
	delete branchs_comboBox;
	delete groupBox;
	delete countProduct;
	delete execSearch;
	delete returnButton;

	optionsGeneralListManagement_clickedSlot();
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

	HTProductOfOrder = db_instance->searchListOrdersProductOnBranch(
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

	if(HTProducts.size() == 0)
	{
		warningMessage("Nenhum produto encontrado!");
		return;
	}

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
