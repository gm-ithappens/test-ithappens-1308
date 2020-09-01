#ifndef MANAGERWINDOW_H
#define MANAGERWINDOW_H
#include <QPushButton>
#include <QMessageBox>
#include <QMainWindow>
#include <QVBoxLayout>

#include <QMessageBox>
#include <QVBoxLayout>
#include <QStackedWidget>

class ManagerWindow: public QMainWindow
{
	Q_OBJECT

public:
	explicit ManagerWindow  (QWidget *parent = 0);
	~ ManagerWindow(){}

public:
	void callbackChangeScreen(int choosedScreen);

public slots:
	void loginUser_clickedSlot();
	void loginAdmin_clickedSlot();


private:
	QWidget         *   mCentralWidget;
	QGridLayout     *   mGridLayout;
	QStackedWidget  *   mStackedWidget;
	QPushButton     *   mPrevButton;
	QPushButton     *   mNextButton;

	int mIndex;

};
#endif
