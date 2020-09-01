#include "ManagerWindow.h"
#include "AdminModule.h"
#include "UserModule.h"
#include "InitialWindow.h"

#include <iostream>
using namespace std;

ManagerWindow::ManagerWindow(QWidget * parent)
	: QMainWindow(parent)
	, mCentralWidget(new QWidget(this))
	, mGridLayout(new QGridLayout(mCentralWidget))
	, mStackedWidget(new QStackedWidget(this))
	, mPrevButton(new QPushButton("Administrador", this))
	, mNextButton(new QPushButton("Usuário", this))
	,mIndex(0)
{
	mGridLayout->addWidget(mStackedWidget, 0, 0, 1, 2);
	mGridLayout->addWidget(mPrevButton,    1, 0, 1, 1);
	mGridLayout->addWidget(mNextButton,    1, 1, 1, 1);

	mCentralWidget->setLayout(mGridLayout);
	setCentralWidget(mCentralWidget);


	mStackedWidget->addWidget(new InitialWindow(this,"Inicial"));

	mStackedWidget->setCurrentIndex(mIndex);

	connect(mPrevButton, SIGNAL(clicked()), SLOT(loginAdmin_clickedSlot()));
	connect(mNextButton, SIGNAL(clicked()), SLOT(loginUser_clickedSlot()));
}


void ManagerWindow::callbackChangeScreen(int choosedScreen)
{
	mStackedWidget->setCurrentIndex(choosedScreen);
}


void ManagerWindow::loginAdmin_clickedSlot()
{

	mGridLayout->removeWidget(mStackedWidget);
	mGridLayout->removeWidget(mNextButton);
	mGridLayout->removeWidget(mPrevButton);
	delete mPrevButton;
	delete mNextButton;
	delete mStackedWidget;

	mStackedWidget = new QStackedWidget(this);
	mStackedWidget->addWidget(new AdminModule(this,"Instan Admin"));
	mGridLayout->addWidget(mStackedWidget, 0, 0, 1, 2);

	mCentralWidget->setLayout(mGridLayout);
	setCentralWidget(mCentralWidget);


	mStackedWidget->setCurrentIndex(0);
}

void ManagerWindow::loginUser_clickedSlot()
{
	mGridLayout->removeWidget(mStackedWidget);
	mGridLayout->removeWidget(mPrevButton);
	mGridLayout->removeWidget(mNextButton);
	delete mPrevButton;
	delete mNextButton;
	delete mStackedWidget;

	mStackedWidget = new QStackedWidget(this);
	mStackedWidget->addWidget(new UserModule(this,"Instanc User"));
	mGridLayout->addWidget(mStackedWidget, 0, 0, 1, 2);

	mCentralWidget->setLayout(mGridLayout);
	setCentralWidget(mCentralWidget);

	mStackedWidget->setCurrentIndex(0);
}
