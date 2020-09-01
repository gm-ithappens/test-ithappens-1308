#ifndef INITIALWINDOW_H
#define INITIALWINDOW_H
#include <QPushButton>
#include <QMessageBox>
#include <QMainWindow>
#include <QVBoxLayout>

#include <QMessageBox>
#include <QVBoxLayout>
#include <QStackedWidget>
#include <QWidget>
#include <QGridLayout>
#include <QLabel>


class InitialWindow: public QWidget
{
	Q_OBJECT

public:
	  explicit InitialWindow(void * previous, const QString & name, QWidget * parent = 0);
	~ InitialWindow(){}

	void Execute();

public slots:
	void login_clickedSlot();

private:
	QGridLayout *   mGridLayout;
	QLabel      *   mLabel;


};
#endif
