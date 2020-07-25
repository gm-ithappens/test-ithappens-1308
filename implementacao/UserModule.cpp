#include "UserModule.h"
#include "OrderModule.h"
#include <iostream>

using namespace std;

UserModule::UserModule(void * previous, const QString & name, QWidget * parent)
        : QWidget(parent)
        , mGridLayout(new QGridLayout(this))
        , mLabel(new QLabel(name, this))
{
	Execute();
}

void UserModule::Execute()
{
        mGridLayout->removeWidget(mLabel);
        delete mLabel;

        newOrderButton       = new QPushButton(this);
        newOrderButton->setText("Novo Pedido");

        QObject::connect(newOrderButton, SIGNAL(clicked()),this, SLOT(newOrder_clickedSlot()));

        newOrderButton->setSizePolicy(QSizePolicy::Expanding,QSizePolicy::Expanding);

        //QWidget* centralWidget = new QWidget(this);
        //centralWidget->setSizePolicy(QSizePolicy::Expanding,QSizePolicy::Expanding);

	branchLabel = new QLabel("Escolha a filial: ");

	branchs_comboBox = new QComboBox;
	branchs_comboBox->addItem(tr(""));
	branchs_comboBox->addItem(tr("Filial 1"));
	branchs_comboBox->addItem(tr("Filial 2"));
	branchs_comboBox->addItem(tr("Filial 3"));
        branchs_comboBox->setSizePolicy(QSizePolicy::Expanding,QSizePolicy::Expanding);
	connect(branchs_comboBox, SIGNAL(currentIndexChanged(int)), this, SLOT(branchChoosedChanged()));

	
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

	txtClientInfos = new QLineEdit(this);
        txtClientInfos->setSizePolicy(QSizePolicy::Expanding,QSizePolicy::Expanding);

	obsClientInfos = new QLineEdit(this);
        obsClientInfos->setSizePolicy(QSizePolicy::Expanding,QSizePolicy::Expanding);

        mGridLayout->addWidget(newOrderButton);
        mGridLayout->addWidget(branchLabel);
        mGridLayout->addWidget(branchs_comboBox);
        mGridLayout->addWidget(operatorLabel);
        mGridLayout->addWidget(operator_comboBox);
        mGridLayout->addWidget(clientLabel);
        mGridLayout->addWidget(txtClientInfos);
        mGridLayout->addWidget(obsLabel);
        mGridLayout->addWidget(obsClientInfos);

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

void UserModule::newOrder_clickedSlot()
{
	QString text = txtClientInfos->text();
	cout << "Cliente: " << text.toStdString() << endl;

	QString branchs_field = branchs_comboBox->currentText();
	if(branchs_field.isEmpty())
	{
		QMessageBox msgBox;
		msgBox.setWindowTitle("AVISO!");
		msgBox.setText("Necessário escolher uma filial!");
		msgBox.exec();
	}

	QString client_field = txtClientInfos->text();
	if(client_field.isEmpty())
	{
		QMessageBox msgBox;
		msgBox.setWindowTitle("AVISO!");
		msgBox.setText("Necessário informar os dados do cliente!");
		msgBox.exec();
	}

	QString operator_field = operator_comboBox->currentText();
	if(operator_field.isEmpty())
	{
		QMessageBox msgBox;
		msgBox.setWindowTitle("AVISO!");
		msgBox.setText("Necessário informar operador!");
		msgBox.exec();
	}

	QString obs = obsClientInfos->text();
	neworder = new OrderModule(branchs_field, client_field, operator_field, obs, 0);

	ProcessingOrder();
}


void UserModule::ProcessingOrder()
{
        mGridLayout->removeWidget(newOrderButton);
        mGridLayout->removeWidget(txtClientInfos);
        mGridLayout->removeWidget(operator_comboBox);
        mGridLayout->removeWidget(branchs_comboBox);
	mGridLayout->removeWidget(obsClientInfos);

	mGridLayout->removeWidget(branchLabel);
	mGridLayout->removeWidget(clientLabel);
	mGridLayout->removeWidget(obsLabel);
	mGridLayout->removeWidget(operatorLabel);

        delete newOrderButton;
	delete operator_comboBox;
	delete branchs_comboBox;
	delete txtClientInfos;
	delete obsClientInfos;
	delete branchLabel;
	delete clientLabel;
	delete obsLabel;
	delete operatorLabel;
	
	cout << "** Ordem de saida ** "  << endl;
	cout << " Filial   : " << neworder->branchs_field.toStdString()          << endl;
	cout << " Operador : " << neworder->operator_field.toStdString()         << endl;
	cout << " Cliente  : " << neworder->client_field.toStdString()           << endl;
	cout << " Obs      : " << neworder->obs_client_lient_infos.toStdString() << endl;
        show();

#if 0
        QPushButton * newOrderButton       = new QPushButton(this);
        newOrderButton->setText("kkkkkkkkkkkkkkkkkkkkkk");


        mGridLayout =  new QGridLayout(this);

	mGridLayout->addWidget(newOrderButton);

	setLayout(mGridLayout);

        //show();
#endif
}
