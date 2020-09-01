#include "Common.h"

using namespace std;

void warningMessage(string str)
{
        QMessageBox msgBox;
        msgBox.setWindowTitle("AVISO!");
        msgBox.setText(str.c_str());
        msgBox.exec();
}

QPushButton * mountButton(string msg)
{
        QPushButton * btn;
        btn               = new QPushButton();
        btn->setText      (msg.c_str());
        btn->setSizePolicy(QSizePolicy::Expanding,QSizePolicy::Expanding);

        return btn;
}

int isSettedVariable(QString var, QString msgErr)
{
        if(var.isEmpty())
        {
                warningMessage(msgErr.toStdString().c_str());
                return 0;
        }

        return 1;
}

