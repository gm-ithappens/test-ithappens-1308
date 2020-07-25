#include "InitialWindow.h"
#include "AdminModule.h"

#include <iostream>
using namespace std;

InitialWindow::InitialWindow(void * previous, const QString & name, QWidget * parent)
	: QWidget(parent)
	, mGridLayout(new QGridLayout(this))
	, mLabel(new QLabel(name, this))

{
	Execute();
}

void InitialWindow::Execute()
{
	cout << "InitialWindow::Execute " << endl;

#if 0
	QPushButton *adminButton = new QPushButton(this);
	QPushButton *userButton2 = new QPushButton(this);

	adminButton->setText("Administrador");
	userButton2->setText("Usuário");

	QObject::connect(adminButton, SIGNAL(clicked()),this, SLOT(login_clickedSlot()));
	QObject::connect(userButton2, SIGNAL(clicked()),this, SLOT(login_clickedSlot()));

	adminButton->setSizePolicy(QSizePolicy::Expanding,QSizePolicy::Expanding);
	userButton2->setSizePolicy(QSizePolicy::Expanding,QSizePolicy::Expanding);

	QWidget* centralWidget = new QWidget(this);
	centralWidget->setSizePolicy(QSizePolicy::Expanding,QSizePolicy::Expanding);

	QVBoxLayout* layout = new QVBoxLayout(centralWidget);

	layout->addWidget(adminButton);
	layout->addWidget(userButton2);

	//setCentralWidget(centralWidget);

	setWindowTitle("Gerenciamento e Vendas");
	show();
#endif

}

void InitialWindow::login_clickedSlot()
{

	QMessageBox msgBox;
	msgBox.setWindowTitle("Bem vindo!");
	msgBox.setText("Você está logado como:  "+ ((QPushButton*)sender())->text());
	msgBox.exec();

}
