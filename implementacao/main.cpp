#include <QApplication>
#include <QMainWindow>
#include "ManagerWindow.h"
#include "DataBase.h"

#include <iostream>
using namespace std;

int main(int argc, char *argv[])
{
	QApplication app(argc, argv);

	// Database instance in singleton
	DataBase * r = DataBase::getInstance();

	ManagerWindow window;
	window.resize(900,600);
	window.show();

	return app.exec();

}
