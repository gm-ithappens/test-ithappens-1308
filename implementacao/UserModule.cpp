#include "UserModule.h"
#include "OrderModule.h"
#include <iostream>

using namespace std;

UserModule::UserModule(void * previous, const QString & name, QWidget * parent)
        : QWidget(parent)
        , mGridLayout(new QGridLayout(this))
{
	Execute();
}

void UserModule::Execute()
{
	db_instance = DataBase::getInstance();

	// New order
	newOrderButton = mountButton("Novo Pedido");
        QObject::connect(newOrderButton, SIGNAL(clicked()),this, SLOT(newOrder_clickedSlot()));

	branchLabel = new QLabel("Escolha a filial: ");


	// Options to branch company
	branchs_comboBox = new QComboBox;
        branchs_comboBox->addItem(tr(""));
        QList<QString> * ql = db_instance->getListBranchCompany();
	QString s;
        for (int i = 0; i < ql->size(); ++i)
        {
                s = ql->at(i);

                branchs_comboBox->addItem(tr(s.toStdString().c_str()));
        }

        branchs_comboBox->setSizePolicy(QSizePolicy::Expanding,QSizePolicy::Expanding);
	connect(branchs_comboBox, SIGNAL(currentIndexChanged(int)), this, SLOT(branchChoosedChanged()));


	// Operator in hardcode yet	
	operator_comboBox = new QComboBox;
	operator_comboBox->addItem(tr(""));
	operator_comboBox->addItem(tr("Operador 1"));
	operator_comboBox->addItem(tr("Operador 2"));
	operator_comboBox->addItem(tr("Operador 3"));
        operator_comboBox->setSizePolicy(QSizePolicy::Expanding,QSizePolicy::Expanding);
	connect(operator_comboBox, SIGNAL(currentIndexChanged(int)), this, SLOT(operatorChoosedChanged()));


	clientLabel = new QLabel("Entre com informações do cliente: ");
	obsLabel    = new QLabel("Alguma observação? ");
	operatorLabel    = new QLabel("Selecione o operador:");

	// Field to input client informations
	txtClientInfos = new QLineEdit(this);
        txtClientInfos->setSizePolicy(QSizePolicy::Expanding,QSizePolicy::Expanding);

	// If there is some points about order
	obsClientInfos = new QLineEdit(this);
        obsClientInfos->setSizePolicy(QSizePolicy::Expanding,QSizePolicy::Expanding);

        mGridLayout->addWidget(branchLabel);
        mGridLayout->addWidget(branchs_comboBox);
        mGridLayout->addWidget(operatorLabel);
        mGridLayout->addWidget(operator_comboBox);
        mGridLayout->addWidget(clientLabel);
        mGridLayout->addWidget(txtClientInfos);
        mGridLayout->addWidget(obsLabel);
        mGridLayout->addWidget(obsClientInfos);
        mGridLayout->addWidget(newOrderButton);

        show();

}

void UserModule::branchChoosedChanged()
{
	QString text = branchs_comboBox->currentText();
	cout << "Filial escolhida: " << text.toStdString() << endl;
}

void UserModule::operatorChoosedChanged()
{
	QString text = operator_comboBox->currentText();
	cout << "Operador escolhida: " << text.toStdString() << endl;
}

void UserModule::finishOrder_clickedSlot()
{
	// Verificar se existe produto
	if(neworder->Products.size() == 0)
	{
		warningMessage("Nenhum produto pedido.!");
		return;
	}


	// Payment method
	QMessageBox msgBox;
	msgBox.setText("Escolher a forma de pagamento!");
	msgBox.setInformativeText("Qual forma?");
	QPushButton *ccButton    = msgBox.addButton(tr("Cartão de Crédito"), QMessageBox::ActionRole);
	QPushButton *orderButton = msgBox.addButton(tr("Boleto"), QMessageBox::ActionRole);
	QPushButton *moneyButton = msgBox.addButton(tr("A vista"), QMessageBox::ActionRole);
	int ret = msgBox.exec();

	if (msgBox.clickedButton() == ccButton)
	{
		cout << "Cartão de credito " << endl;
		neworder->payment_mode = CREDITCARD_PAYMENT_MODE;
	}
	else if(msgBox.clickedButton() == orderButton)
	{
		cout << "Boleto " << endl;
		neworder->payment_mode = ORDER_PAYMENTMODE;
	}
	else if(msgBox.clickedButton() == moneyButton)
	{
		cout << "A vista " << endl;
		neworder->payment_mode = MONEY_PAYMENTMODE;
	}

	neworder->ProcessingOrder(OUTPUT_ORDER, UPDATE_PRODUCT);

	cout << "** Fim de venda! ** "  << endl;
	warningMessage("Venda concluida com sucesso!");

	destroyOrder();
	Execute();
}


void UserModule::destroyPreOrder()
{
        mGridLayout->removeWidget(branchLabel);
        mGridLayout->removeWidget(branchs_comboBox);
        mGridLayout->removeWidget(operatorLabel);
        mGridLayout->removeWidget(operator_comboBox);
        mGridLayout->removeWidget(clientLabel);
        mGridLayout->removeWidget(txtClientInfos);
        mGridLayout->removeWidget(obsLabel);
        mGridLayout->removeWidget(obsClientInfos);
        mGridLayout->removeWidget(newOrderButton);

        delete branchLabel;
        delete branchs_comboBox;
        delete operatorLabel;
        delete operator_comboBox;
        delete clientLabel;
        delete txtClientInfos;
        delete obsLabel;
        delete obsClientInfos;
        delete newOrderButton;
}

void UserModule::destroyOrder()
{
        mGridLayout->removeWidget(search_comboBox);
	mGridLayout->removeWidget(dataSearchLabel);
	mGridLayout->removeWidget(searchData);
        mGridLayout->removeWidget(newOrderButton);
	mGridLayout->removeWidget(cancellProdutLabel);
	mGridLayout->removeWidget(cancelThisProduct);
        mGridLayout->removeWidget(cancelButton);
        mGridLayout->removeWidget(finishOrderButton);
	mGridLayout->removeWidget(returnButton);
	mGridLayout->removeWidget(searchModeLabel);

	delete searchModeLabel;
        delete search_comboBox;
	delete dataSearchLabel;
	delete searchData;
        delete newOrderButton;
	delete cancellProdutLabel;
	delete cancelThisProduct;
        delete cancelButton;
        delete finishOrderButton;
	delete returnButton;
}


void UserModule::newOrder_clickedSlot()
{
	QString text = txtClientInfos->text();
	cout << "Cliente: " << text.toStdString() << endl;

	QString branchs_field = branchs_comboBox->currentText();
	if(isSettedVariable(branchs_field, "Necessário escolher uma filial!") == 0)
		return;

	QString client_field = txtClientInfos->text();
	if(isSettedVariable(client_field, "Necessário informar os dados do cliente!") == 0)
		return;

	QString operator_field = operator_comboBox->currentText();
	if(isSettedVariable(operator_field, "Necessário informar operador!") == 0)
		return;

	QString obs = obsClientInfos->text();

	neworder = new OrderModule(branchs_field, client_field, operator_field, obs, OUTPUT_ORDER);

	destroyPreOrder();

	ProcessingOrder();
}

void UserModule::ProcessingOrder()
{
	cout << "** Ordem de saida ** "  << endl;
	cout << " Filial   : "           << neworder->branchs_field.toStdString   () << endl;
	cout << " Operador : "           << neworder->operator_field.toStdString  () << endl;
	cout << " Cliente  : "           << neworder->client_field.toStdString    () << endl;
	cout << " Obs      : "           << neworder->obs_client_infos.toStdString() << endl;

	// Button to search
	newOrderButton = mountButton("Buscar produto");
        QObject::connect(newOrderButton, SIGNAL(clicked()),this, SLOT(newProductSearch_clickedSlot()));

	finishOrderButton    = mountButton("Finalizar Pedido");
	QObject::connect(finishOrderButton, SIGNAL(clicked()),this, SLOT(finishOrder_clickedSlot()));

	// Search mode
	searchModeLabel = new QLabel("Escolha a forma de procurar o produto:");

	search_comboBox = new QComboBox;
	search_comboBox->addItem(tr(""));
	search_comboBox->addItem(tr("Código do Produto"));
	search_comboBox->addItem(tr("Descrição de Produto"));
	search_comboBox->addItem(tr("Código de Barras"));
        operator_comboBox->setSizePolicy(QSizePolicy::Expanding,QSizePolicy::Expanding);
	connect(search_comboBox, SIGNAL(currentIndexChanged(int)), this, SLOT(searchModeChoosedChanged()));

	dataSearchLabel = new QLabel("Entre com a informação relacionada ao produto!");

	// Product code, bar code or sequential
	searchData = new QLineEdit(this);
        searchData->setSizePolicy(QSizePolicy::Expanding,QSizePolicy::Expanding);


	// Cancel some product
	cancellProdutLabel = new QLabel("Cancelar algum produto?");
	cancelThisProduct      = new QLineEdit(this);
        cancelThisProduct->setSizePolicy(QSizePolicy::Expanding,QSizePolicy::Expanding);

	cancelButton = mountButton("Cancelar produto");
        QObject::connect(cancelButton, SIGNAL(clicked()),this, SLOT(cancelProductSelected()));

	returnButton = mountButton("Voltar");;
        QObject::connect(returnButton, SIGNAL(clicked()),this, SLOT(returnProcessingOrder()));

	mGridLayout->addWidget(searchModeLabel);
        mGridLayout->addWidget(search_comboBox);
	mGridLayout->addWidget(dataSearchLabel);
	mGridLayout->addWidget(searchData);
        mGridLayout->addWidget(newOrderButton);
	mGridLayout->addWidget(cancellProdutLabel);
	mGridLayout->addWidget(cancelThisProduct);
        mGridLayout->addWidget(cancelButton);
        mGridLayout->addWidget(finishOrderButton);
	mGridLayout->addWidget(returnButton);

        show();
}

void UserModule::returnProcessingOrder()
{
	destroyOrder();
	Execute();
}

void UserModule::searchModeChoosedChanged()
{
	QString text = search_comboBox->currentText();
	cout << "Modo de procura escolhida: " << text.toStdString() << endl;

}

void UserModule::newProductSearch_clickedSlot()
{
	const char * translate_search_option[4] = {"","SEQUENTIAL", "DESCRIPTION", "BARCODE"};

	cout << "Procurar produto " << endl;
	QString branch = neworder->branchs_field;

	cout << " Filial   : " << branch.toStdString()          << endl;

	int idx  = search_comboBox->currentIndex();
	if(idx == 0)
	{
		warningMessage("Necessário informar o modo de procura");
		return;
	}

	QString search_mode = translate_search_option[idx];
	cout << "Modo de procura escolhida: " << search_mode.toStdString() << endl;

	QString product = searchData->text();
	cout << " Produto   : " << product.toStdString()          << endl;
	if(isSettedVariable(product, "Necessário informar o produto") == 0)
		return;


	// Verify if already exists in a hashtable Products of new order
	Product * line_product;
	if (neworder->Products.contains(product))
	{
		line_product = neworder->Products[product];
	}    
	else
	{
		if(idx == 1)
			line_product = db_instance->searchProductOnBranchSeq(branch.toStdString(), 
								search_mode.toStdString(), 
								product.toInt());
		else
			line_product = db_instance->searchProductOnBranch(branch.toStdString(), 
								search_mode.toStdString(), 
								product.toStdString());
	}

	cout << "Quantidade de " << product.toStdString() << " disponivel " << line_product->count_available  << endl;
        if(line_product->count_available == 0)
        {
                warningMessage("Produto não encontrado ou em falta!");
		return;
        }

	bool ok;
	int input_count = QInputDialog::getInt(this, tr("QInputDialog::getInt()"),
			 tr("Quantidade:"), 0, 0, 10000, 1, &ok);

	if(!ok)
		return;

	if(input_count == 0)
	{
                warningMessage("Não é possível inserir ZERO produto!");
		return;
	}

	// New requested count plus already requested cant higher than count available
	if(line_product->count_available < (line_product->count_requested + input_count))
	{
		QString msg = QString("Insuficiente em estoque. Solicitado %1 -  Disponivel %2").arg(
						line_product->count_requested + input_count).arg(
						line_product->count_available);

		warningMessage(msg.toStdString());
		return;
	}

	line_product->updateRequestedTotal(input_count);

	neworder->updateTotalValue(line_product->total_value);
	neworder->updateTotalitens(line_product->count_requested);

	line_product->status = STATUS_PRODUCT_ACTIVE;

	neworder->Products[product] = line_product;

	QString msg = "";
	msg.append(product);
	msg.append(" adicionado ao pedido");
	warningMessage(msg.toStdString().c_str());
}

void UserModule::cancelProductSelected()
{
	cout << "Cancelar produto " << endl;
	const char * translate_search_option[4] = {"","SEQUENTIAL", "DESCRIPTION", "BARCODE"};

	cout << "Procurar produto " << endl;
	QString branch = neworder->branchs_field;

	cout << " Filial   : " << branch.toStdString()          << endl;

	int idx  = search_comboBox->currentIndex();
	if(idx == 0)
	{
		warningMessage("Necessário informar o modo de procura");
		return;
	}

	QString search_mode = translate_search_option[idx];
	cout << "Modo de procura escolhida: " << search_mode.toStdString() << endl;

	QString product = searchData->text();
	cout << " Produto   : " << product.toStdString()          << endl;
	if(isSettedVariable(product, "Necessário informar o produto") == 0)
		return;


	// Verify if already exists in a hashtable Products of new order
	Product * line_product;
	if (neworder->Products.contains(product))
	{
		line_product = neworder->Products[product];
	}    
	else
	{
		QString msg = QString("Produto %1 não solicitado ainda").arg(product);
		warningMessage(msg.toStdString());
		return;
	}

	neworder->updateTotalValue(-1*line_product->total_value);

        line_product->status = STATUS_PRODUCT_CANCELED;
	line_product->updateCanceledTotal();


	neworder->Products[product] = line_product;
}
