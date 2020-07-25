#ifndef ADMINMODULE_H
#define ADMINMODULE_H
#include <QPushButton>
#include <QMessageBox>
#include <QMainWindow>
#include <QVBoxLayout>

#include <QGridLayout>
#include <QLabel>

class AdminModule: public QWidget
{
	Q_OBJECT

public:
	 explicit  AdminModule(void * previous, const QString & name, QWidget * parent = 0);
	~ AdminModule(){}

	void Execute();

public slots:
	void newbranch_clickedSlot();
	void AdminModule::deletebranch_clickedSlot();
	void AdminModule::listall_clickedSlot();
	void AdminModule::selectbranch_clickedSlot();

private:
	QGridLayout *   mGridLayout;
	QLabel      *   mLabel;
#if 0
	QPushButton * newBranchButton;
	QPushButton * deleteButton;
	QPushButton * listAllBranch;
	QPushButton * selectEspecificBranch;
#endif
};
#endif
