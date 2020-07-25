#include "AdminModule.h"


#include <iostream>
using namespace std;

AdminModule::AdminModule(void * previous, const QString & name, QWidget * parent)
        : QWidget(parent)
        , mGridLayout(new QGridLayout(this))
        , mLabel(new QLabel(name, this))
{
        Execute();
}

void AdminModule::Execute()
{
	cout << "AdminModule::Execute" << endl;

	mGridLayout->removeWidget(mLabel);
	delete mLabel;

	QPushButton * newBranchButton       = new QPushButton(this);
	QPushButton * deleteButton          = new QPushButton(this);
	QPushButton * listAllBranch         = new QPushButton(this);
	QPushButton * selectEspecificBranch = new QPushButton(this);

	newBranchButton->setText("Nova Filial");
	deleteButton->setText("Del. Filial");
	listAllBranch->setText("Listar Filiais");
	selectEspecificBranch->setText("Selecionar Filial");

	QObject::connect(newBranchButton, SIGNAL(clicked()),this, SLOT(newbranch_clickedSlot()));

	newBranchButton->setSizePolicy      (QSizePolicy::Expanding,QSizePolicy::Expanding);
	deleteButton->setSizePolicy         (QSizePolicy::Expanding,QSizePolicy::Expanding);
	listAllBranch->setSizePolicy        (QSizePolicy::Expanding,QSizePolicy::Expanding);
	selectEspecificBranch->setSizePolicy(QSizePolicy::Expanding,QSizePolicy::Expanding);

	QWidget* centralWidget = new QWidget(this);
	centralWidget->setSizePolicy(QSizePolicy::Expanding,QSizePolicy::Expanding);

	QVBoxLayout* layout = new QVBoxLayout(centralWidget);

	mGridLayout->addWidget(newBranchButton);
	mGridLayout->addWidget(deleteButton);
	mGridLayout->addWidget(listAllBranch);
	mGridLayout->addWidget(selectEspecificBranch);

	setWindowTitle("Administração");
	show();
}

void AdminModule::newbranch_clickedSlot()
{
	QMessageBox msgBox;
	msgBox.setWindowTitle("Bem vindo!");
	msgBox.setText("Nova loja:  "+ ((QPushButton*)sender())->text());
	msgBox.exec();
}

void AdminModule::deletebranch_clickedSlot()
{
}

void AdminModule::listall_clickedSlot()
{
}

void AdminModule::selectbranch_clickedSlot()
{
}
