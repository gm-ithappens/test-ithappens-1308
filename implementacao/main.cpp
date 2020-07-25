#include <QApplication>
#include <QMainWindow>
#include "ManagerWindow.h"

int main(int argc, char *argv[])
{
  QApplication app(argc, argv);

  ManagerWindow window;
  window.resize(900,600);
  window.show();

  return app.exec();

}
